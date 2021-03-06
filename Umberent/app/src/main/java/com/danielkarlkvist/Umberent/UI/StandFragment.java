package com.danielkarlkvist.Umberent.UI;

import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.danielkarlkvist.Umberent.Database.DatabaseHandler;
import com.danielkarlkvist.Umberent.Model.IRental;
import com.danielkarlkvist.Umberent.Model.IStand;

import androidx.fragment.app.Fragment;

import com.danielkarlkvist.Umberent.Model.Umberent;
import com.danielkarlkvist.Umberent.R;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * Fragment that handles popup-windows for opening stands, also handles the rental process
 */

public class StandFragment extends Fragment {

    //components for Stand Window
    private Button rent_button;
    private TextView locationTextView;
    private TextView priceTextView;
    private TextView amountTextView;

    //components for Rental Window
    private Button end_rent_button;
    private Button start_rent_button;
    private Chronometer rentalTimeElapsedChronometer;
    private TextView currentPriceTextView;
    private TextView confirmRental;

    private ImageView umbrella;

    //components for Minimized Rental Window
    private ImageButton maximizeRentButton;

    //components for Receipt Window
    private TextView finishedCostTextView;
    private TextView finishedTimeTextView;

    // Umberent instance
    private Umberent umberent = Umberent.getInstance();
    private IRental rental = umberent.getRental();
    private IStand stand;

    private long difference;
    private DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

    private boolean running = false;
    PopupWindow popupWindow;
    View popupView;

    private MapFragment mapFragment;

    /**
     * Method for opening a stand window
     *
     * @param view
     */
    public void showStandWindow(final View view) {

        //Create a View object through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.stand_card, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the animation of the window
        popupWindow.setAnimationStyle(R.style.AnimationPopUp);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //Initialize the elements of our window, install the handler
        initializeStandView(popupView);

        //Initialize rent button
        rent_button = popupView.findViewById(R.id.rent_button);
        rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(stand.getCapacity() != 0) {
                    // Remove stand window
                    popupWindow.dismiss();
                    // Open Rental window
                    openRentalWindow(view);
                }else{
                    System.out.println("Can't rent since the stand has no umbrella in it");}

            }
        });

        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
    }

    void setStandInfo(IStand stand) {
        locationTextView.setText(stand.getTitle());
        this.stand = stand;

        if (stand.getAmountOfUmbrellas() > 1)
            amountTextView.setText(stand.getAmountOfUmbrellas() + " / " + stand.getCapacity() + " lediga");
        else
            amountTextView.setText(stand.getAmountOfUmbrellas() + " / " + stand.getCapacity() + " ledig");
        priceTextView.setText("2kr + 2kr / min");
    }

    private void initializeStandView(View v) {
        locationTextView = v.findViewById(R.id.location_textView);
        priceTextView = v.findViewById(R.id.price_textView);
        amountTextView = v.findViewById(R.id.amount_textView);
    }

    private void openRentalWindow(final View view) {

        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        final View rentalView = inflater.inflate(R.layout.fragment_rental, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow rentalWindow = new PopupWindow(rentalView, width, height, focusable);

        //Set the animation of the window
        rentalWindow.setAnimationStyle(R.style.AnimationPopUp);

        //Set the location of the window on the screen
        rentalWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        initializeRentalViews(rentalView);
        initializeRentalButtonListeners();

        end_rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //"Open receipt"-statement here
                if (umberent.rentalIsActive()) {

                    // set rental to inactive
                    umberent.setRentalIsActive(false);

                    rental.setEndTime(System.currentTimeMillis());
                    rental.setDate(LocalDate.now());
                    rental.setCost((int) rental.calculatePrice(rental.getStartTime(), rental.getEndTime()));
                    rental.setTotalTime(rental.calculateRentalTime(rental.getStartTime(), rental.getEndTime()));

                    rentalWindow.dismiss();
                    openReceiptView(v);

                } else {
                    rentalWindow.dismiss();
                }

                System.out.println(rental.toString());

                // reset chronometer
                resetChronometer(v);
                databaseHandler.addRental(rental);


            }
        });

    }

    private void initializeRentalViews(View view) {
        start_rent_button = view.findViewById(R.id.start_rent_button);
        end_rent_button = view.findViewById(R.id.end_rent_button);
        confirmRental = view.findViewById(R.id.confirmRental);
        umbrella = view.findViewById(R.id.umbrella_imageView2);
        currentPriceTextView = view.findViewById(R.id.currentPriceTextView);
        rentalTimeElapsedChronometer = view.findViewById(R.id.rentalTimeElapsedChronometer);

        if (!umberent.rentalIsActive()) {
            start_rent_button.setVisibility(View.VISIBLE);
            end_rent_button.setText("Avbryt");
            currentPriceTextView.setVisibility(View.INVISIBLE);
            rentalTimeElapsedChronometer.setVisibility(View.INVISIBLE);
        }

        // initiates stopwatch and sets in it so every 60 seconds price is updated on view
        rentalTimeElapsedChronometer.setFormat("Hyrningstid: %s");
        rentalTimeElapsedChronometer.setBase(SystemClock.elapsedRealtime());
        rentalTimeElapsedChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                currentPriceTextView.setText("Aktuellt pris: " + rental.calculatePrice(rental.getStartTime(), System.currentTimeMillis()) + "kr");
            }
        });
    }

    private void initializeRentalButtonListeners() {
        // initialize rent button
        start_rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start a new rental

                // makes rental active
                umberent.setRentalIsActive(true);

                // set user for rental
                rental.setUser(umberent.getProfile());

                // set umbrella for rental
                rental.setUmbrella(stand.getLastUmbrella());
                stand.getUmbrellas().remove(stand.getUmbrellas().size() - 1);

                // set start time for rental
                rental.setStartTime(System.currentTimeMillis());

                // start ticking stopwatch
                startChronometer(view);

                start_rent_button.setVisibility(View.INVISIBLE);
                rentalTimeElapsedChronometer.setVisibility(View.VISIBLE);
                currentPriceTextView.setVisibility(View.VISIBLE);
                end_rent_button.setText("Avsluta hyrning");
                confirmRental.setVisibility(View.INVISIBLE);

            }
        });
    }

    // Methods for Chronometer/Stopwatch
    private void startChronometer(View view) {
        if (!running) {
            rentalTimeElapsedChronometer.setBase(SystemClock.elapsedRealtime());
            rentalTimeElapsedChronometer.start();
            running = true;
        }
    }

    private void resetChronometer(View view) {
        if (running) {
            rentalTimeElapsedChronometer.setBase(SystemClock.elapsedRealtime());
            rentalTimeElapsedChronometer.stop();
            running = false;
        }
    }

    private void openReceiptView(final View view) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View receiptView = inflater.inflate(R.layout.receipt_for_rental, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow receiptWindow = new PopupWindow(receiptView, width, height, focusable);


        //Set the animation of the window
        receiptWindow.setAnimationStyle(R.style.AnimationPopUp);

        //Set the location of the window on the screen
        receiptWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        initializeReceiptViews(receiptView);

        //Handler for clicking on the inactive zone of the window

        receiptView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked

                receiptWindow.dismiss();
                return true;
            }
        });
    }

    private void initializeReceiptViews(View view) {

        long time = rental.getTotalTime();

        long minutes = TimeUnit.MILLISECONDS
                .toMinutes(time);
        time -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS
                .toSeconds(time);

        finishedCostTextView = view.findViewById(R.id.finishedCostTextView);
        finishedTimeTextView = view.findViewById(R.id.finishedtTimeTextView);
        finishedCostTextView.setText("Totalt pris: " + rental.getCost() + "kr");

        if (rental.getTotalTime() >= 60000) {
            finishedTimeTextView.setText("Total tid: " + minutes + "min " + seconds + "s");
        } else {
            finishedTimeTextView.setText("Total tid: " + seconds + " sekunder");
        }
    }
}
