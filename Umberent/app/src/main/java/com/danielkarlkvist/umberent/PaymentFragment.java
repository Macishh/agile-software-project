package com.danielkarlkvist.umberent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    private TextView cardNumberTextView;
    private TextView expirationDateTextView;
    private TextView cvcTextView;

    private Umberent umberent;
    private IProfile user;

    public PaymentFragment(Umberent umberent) {
        this.umberent = umberent;
        this.user = umberent.getProfile();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_payment, container, false);

        initializeViews(v);

        cardNumberTextView.setText(user.getCardNumber());
        expirationDateTextView.setText(user.getExpirationDate());
        cvcTextView.setText(user.getCvc());

        return v;
    }

    // finds the correct view in the xml file and connects it to the instance variables
    private void initializeViews(View v) {
        cardNumberTextView= v.findViewById(R.id.payment_cardNumber);
        expirationDateTextView = v.findViewById(R.id.payment_expirationDate);
        cvcTextView = v.findViewById(R.id.payment_cvc);
    }
}
