package com.danielkarlkvist.umberent.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.danielkarlkvist.umberent.Model.Rental;
import com.danielkarlkvist.umberent.Model.Umberent;
import com.danielkarlkvist.umberent.Model.Umbrella;
import com.danielkarlkvist.umberent.R;

import java.time.LocalDate;

public class RentalFragment extends Fragment {

    private Button end_rent_button;
    private Chronometer rentalTimeElapsedChronometer;
    private ImageView umbrella_ImageView;
    private Button confirm_rent_button;
    private Umberent umberent = Umberent.getInstance();
    private Umbrella umbrella = new Umbrella(1, true);
    private Rental rental = new Rental(System.currentTimeMillis(), System.currentTimeMillis(),  LocalDate.now(), umberent.getProfile(), umbrella);


    public RentalFragment() {
    }

    public void showRentalWindow(final View view) {

        //Create a View object through inflater
        final LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.fragment_rental, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the animation of the window
        popupWindow.setAnimationStyle(R.style.AnimationPopUp);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        initializeViews(popupView);
        initializeButtonListeners();

    }
    private void initializeViews(View view) {
        end_rent_button = view.findViewById(R.id.end_rent_button);
        rentalTimeElapsedChronometer = view.findViewById(R.id.rentalTimeElapsedChronometer);
        confirm_rent_button = view.findViewById(R.id.confirm_rent_button);
    }

    private void initializeButtonListeners() {
        confirm_rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rental.setStartTime(System.currentTimeMillis());
                System.out.println("Rental start time is: " + rental.getStartTime());
                hideRentButton();

            }
        });
        end_rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rental.setEndTime(System.currentTimeMillis());
                rentalTime(rental.getStartTime(), rental.getEndTime());
                calculatePrice(rental.getStartTime(), rental.getEndTime());
                rental.setDate(LocalDate.now());

            }
        });
    }

    private long rentalTime(long startTime, long endTime) {

        long difference = endTime - startTime;
        System.out.println("Time of rental: " + difference/1000);
        return difference;
    }

    private long calculatePrice(long startTime, long endTime){

        long totalCost = (2*((endTime - startTime)/1000))/60;
        System.out.println("Total cost is: " + totalCost);
        return totalCost;

    }

    private void hideRentButton() {
        confirm_rent_button.setVisibility(View.INVISIBLE);

    }
}
