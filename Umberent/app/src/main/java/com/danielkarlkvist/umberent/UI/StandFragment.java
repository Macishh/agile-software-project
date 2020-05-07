package com.danielkarlkvist.umberent.UI;


import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

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
    RentalFragment rentalFragment;

    //PopupWindow display method
    public void showPopupWindow(final View view) {

        //Create a View object through inflater
        final LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.stand_card, null);


        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

       //Set the animation of the window
        popupWindow.setAnimationStyle(R.style.AnimationPopUp);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


        rent_button = popupView.findViewById(R.id.rent_button);
        rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fr = fragmentManager.beginTransaction();
                RentalFragment rentalFragment = new RentalFragment();
                fr.replace(R.id.fragment_container, rentalFragment);
                fr.commit(); */

               rentalFragment.showRentalWindow(getView());
            }
        });


        //Initialize the elements of our window, install the handler


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




}
