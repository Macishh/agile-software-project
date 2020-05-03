package com.danielkarlkvist.umberent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    private ImageButton editButton;
    private ImageButton confirmButton;

    private TextView cardNumberTextView;
    private TextView expirationDateTextView;
    private TextView cvcTextView;

    private Umberent umberent = Umberent.getInstance();
    private EditText cardNumberEditText;
    private EditText expirationDateEditText;
    private EditText cvcEditText;

    private IProfile user;

    boolean isInEditingMode = false;

    public PaymentFragment() {
        this.user = umberent.getProfile();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_payment, container, false);

        initializeViews(v);
        initializeButtonListeners();

        cardNumberTextView.setText(user.getCardNumber());
        expirationDateTextView.setText(user.getExpirationDate());
        cvcTextView.setText(user.getCvc());

        return v;
    }

    private void initializeButtonListeners() {
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isInEditingMode) {
                    isInEditingMode = true;
                    editProfile();
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInEditingMode) {
                    isInEditingMode = false;
                    saveProfile();
                    hideKeyboard(view);
                }
            }
        });
    }
    // finds the correct view in the xml file and connects it to the instance variables
    private void initializeViews(View v) {
        cardNumberTextView= v.findViewById(R.id.payment_cardNumber);
        expirationDateTextView = v.findViewById(R.id.payment_expirationDate);
        cvcTextView = v.findViewById(R.id.payment_cvc);

        editButton = v.findViewById(R.id.payment_edit_button);
        confirmButton=v.findViewById(R.id.payment_confirm_button);
        cardNumberEditText= v.findViewById(R.id.payment_edit_cardNumber);
        expirationDateEditText = v.findViewById(R.id.payment_edit_expirationDate);
        cvcEditText = v.findViewById(R.id.payment_edit_cvc);
    }

    private void editProfile() {
        editButton.setVisibility(View.INVISIBLE);
        confirmButton.setVisibility(View.VISIBLE);

        editUserInformation();
        changeVisibilityForEditMode();

        placeCursorAfterText(cardNumberEditText);
    }

    private void saveProfile() {
        editButton.setVisibility(View.VISIBLE);
        confirmButton.setVisibility(View.INVISIBLE);

        placeNewUserInformation();
        changeVisibilityForStandardMode();
    }

    private void editUserInformation() {
        cardNumberEditText.setText(user.getCardNumber());
        expirationDateEditText.setText(user.getExpirationDate());
        cvcEditText.setText(user.getCvc());
    }

    private void changeVisibilityForEditMode() {
        cardNumberTextView.setVisibility(View.INVISIBLE);
        expirationDateTextView.setVisibility(View.INVISIBLE);
        cvcTextView.setVisibility(View.INVISIBLE);


        cardNumberEditText.setVisibility(View.VISIBLE);
        expirationDateEditText.setVisibility(View.VISIBLE);
        cvcEditText.setVisibility(View.VISIBLE);

    }

    private void changeVisibilityForStandardMode() {
        cardNumberTextView.setVisibility(View.VISIBLE);
        expirationDateTextView.setVisibility(View.VISIBLE);
        cvcTextView.setVisibility(View.VISIBLE);


        cardNumberEditText.setVisibility(View.INVISIBLE);
        expirationDateEditText.setVisibility(View.INVISIBLE);
        cvcEditText.setVisibility(View.INVISIBLE);

    }
    private void placeCursorAfterText(EditText editText) {
        int textLength = editText.getText().toString().length();
        editText.setSelection(textLength);
    }
    private void placeNewUserInformation() {
        user.setCardNumber(cardNumberEditText.getText().toString());
        cardNumberTextView.setText(user.getCardNumber());
        user.setExpirationDate(expirationDateEditText.getText().toString());
        expirationDateTextView.setText(user.getExpirationDate());

        user.setCvc(cvcEditText.getText().toString());
        cvcTextView.setText(user.getCvc());
    }


    private void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
