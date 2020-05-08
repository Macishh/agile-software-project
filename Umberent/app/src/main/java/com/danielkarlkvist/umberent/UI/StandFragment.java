package com.danielkarlkvist.umberent.UI;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.danielkarlkvist.umberent.Model.IStand;
import com.danielkarlkvist.umberent.R;

public class StandFragment {


    private TextView locationTextView;
    private TextView priceTextView;
    private TextView amountTextView;


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

        //Initialize the elements of our window, install the handler
        initializeViews(popupView);

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
         if (stand.getAmountOfUmbrellas() > 1) amountTextView.setText(stand.getAmountOfUmbrellas() + " / " + stand.getCapacity() + " lediga");
         else amountTextView.setText(stand.getAmountOfUmbrellas() + " / " + stand.getCapacity() + " ledig");
       priceTextView.setText("2kr / min");

    }

    private void initializeViews(View v) {
        locationTextView = v.findViewById(R.id.location_textView);
        priceTextView = v.findViewById(R.id.price_textView);
        amountTextView = v.findViewById(R.id.amount_textView);
    }

}
