package com.danielkarlkvist.umberent.UI;


import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.danielkarlkvist.umberent.Model.IStand;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTabHost;
import androidx.fragment.app.FragmentTransaction;

import com.danielkarlkvist.umberent.Model.Rental;
import com.danielkarlkvist.umberent.Model.Umberent;
import com.danielkarlkvist.umberent.Model.Umbrella;
import com.danielkarlkvist.umberent.R;

import java.time.LocalDate;
import java.util.Objects;

public class StandFragment extends Fragment {


    private Button rent_button;
    private Button end_rent_button;
    private Button start_rent_button;
    private Chronometer rentalTimeElapsedChronometer;
    private TextView currentPriceTextView;
    private boolean running = false;
    private Umberent umberent = Umberent.getInstance();
    private Umbrella umbrella = new Umbrella(1, true);
    private Rental rental = new Rental(System.currentTimeMillis(), System.currentTimeMillis(),  LocalDate.now(), umberent.getProfile(), umbrella);
    private ImageView umbrella2;
    private long difference;



    private TextView locationTextView;
    private TextView priceTextView;
    private TextView amountTextView;
    View popupView;
    PopupWindow popupWindow;

    //PopupWindow display method
    public void showPopupWindow(final View view) {

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
        initializeViews(popupView);

        // initialize rent button
        rent_button = popupView.findViewById(R.id.rent_button);
        rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Remove stand window
                popupWindow.dismiss();

                // Open Rental window
                openRentalWindow(view);

            }
        });


        //Handler for clicking on the inactive zone of the window
/*
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked

                popupWindow.dismiss();
                return true;
            }
        });
*/


    }

     void setStandInfo(IStand stand) {
         locationTextView.setText(stand.getTitle());
         if (stand.getAmountOfUmbrellas() > 1)
             amountTextView.setText(stand.getAmountOfUmbrellas() + " / " + stand.getCapacity() + " lediga");
         else
             amountTextView.setText(stand.getAmountOfUmbrellas() + " / " + stand.getCapacity() + " ledig");
         priceTextView.setText("2kr / min");
     }

    private void initializeViews(View v) {
        locationTextView = v.findViewById(R.id.location_textView);
        priceTextView = v.findViewById(R.id.price_textView);
        amountTextView = v.findViewById(R.id.amount_textView);
    }

    private void openRentalWindow(final View view) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View rentalView = inflater.inflate(R.layout.fragment_rental, null);

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
    }

    private void initializeRentalViews(View view) {
        start_rent_button = view.findViewById(R.id.start_rent_button);
        end_rent_button = view.findViewById(R.id.end_rent_button);
        umbrella2 = view.findViewById(R.id.umbrella_imageView2);
        currentPriceTextView = view.findViewById(R.id.currentPriceTextView);

        // initiates stopwatch and sets in it so every 60 seconds price is updated on view
        rentalTimeElapsedChronometer = view.findViewById(R.id.rentalTimeElapsedChronometer);
        rentalTimeElapsedChronometer.setFormat("Hyrningstid: %s");
        rentalTimeElapsedChronometer.setBase(SystemClock.elapsedRealtime());
        rentalTimeElapsedChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - rentalTimeElapsedChronometer.getBase()) >= 60000) {
                    currentPriceTextView.setText("Totalt pris: " + calculatePrice(rental.getStartTime(), System.currentTimeMillis()) + "kr");
                }
            }
        });
    }

    private void initializeRentalButtonListeners() {
        // initialize rent button
        start_rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start a new rental

                rental.setStartTime(System.currentTimeMillis());
                //currentPriceTextView.setText("Totalt pris: " + calculatePrice(rental.getStartTime(), System.currentTimeMillis()));
                System.out.println("Rental start time is: " + rental.getStartTime());
                // start ticking stopwatch
                startChronometer(view);

                start_rent_button.setVisibility(View.INVISIBLE);
                umbrella2.setVisibility(View.VISIBLE);

            }
        });

        end_rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rental.setEndTime(System.currentTimeMillis());
                calculateRentalTime(rental.getStartTime(), rental.getEndTime());
                calculatePrice(rental.getStartTime(), rental.getEndTime());
                rental.setDate(LocalDate.now());

                // reset chronometer
                resetChronometer(v);

                //"Open receipt"-statement here

            }
        });

    }

    private long calculateRentalTime(long startTime, long endTime) {

        difference = endTime - startTime;
        System.out.println("Time of rental: " + difference/1000 + " seconds");
        return difference;
    }

    private long calculatePrice(long startTime, long endTime){

        difference = endTime - startTime;
        long totalCost = (2*((difference)/1000))/60;
        System.out.println("Total cost is: " + totalCost + "kr");
        return totalCost;

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
}
