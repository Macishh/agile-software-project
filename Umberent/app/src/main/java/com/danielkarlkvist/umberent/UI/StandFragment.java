package com.danielkarlkvist.umberent.UI;

import android.icu.util.Calendar;
import android.icu.util.LocaleData;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;

import com.danielkarlkvist.umberent.Model.Rental;
import com.danielkarlkvist.umberent.Model.Umberent;
import com.danielkarlkvist.umberent.Model.Umbrella;
import com.danielkarlkvist.umberent.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class StandFragment extends Fragment {


    private Button rent_button;
    private Button end_rent_button;
    private Umberent umberent = Umberent.getInstance();
    private Umbrella umbrella = new Umbrella(1, true);
    private Rental rental = new Rental(System.currentTimeMillis(), System.currentTimeMillis(),  LocalDate.now(), umberent.getProfile(), umbrella);

    //PopupWindow display method

    public void showPopupWindow(final View view) {



        //Create a View object through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.stand_card, null);


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


        Button rent_button = popupView.findViewById(R.id.rent_button);
        rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rental.setStartTime(System.currentTimeMillis());
                System.out.println(rental.getStartTime());
            }
        });
        //Initialize the elements of our window, install the handler
/*

        TextView test2 = popupView.findViewById(R.id.textView3);
        test2.setText("hello");


        Button buttonEdit = popupView.findViewById(R.id.messageButton);


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //As an example, display the message
                Toast.makeText(view.getContext(), "Wow, popup action button", Toast.LENGTH_SHORT).show();

            }
        });

*/

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
/*
    private void initializeButtonListeners() {
        rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rental.setStartDate(LocalDate.now());
                System.out.println(rental.getStartDate());
            }
        });
    }

    private void initializeViews(View popupView){
        rent_button = popupView.findViewById(R.id.rent_button);
        end_rent_button = popupView.findViewById(R.id.end_rent_button);

    }
*/
}
