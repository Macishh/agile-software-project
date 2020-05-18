package com.danielkarlkvist.Umberent.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.danielkarlkvist.Umberent.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {

    private Button aboutUsButton;
    private Button whereCanIUseButton;
    private Button cantStartRentalButton;
    private Button cantEndRentalButton;
    private Button howStartButton;
    private Button howEndButton;
    private Button howChangeBillingInfoButton;
    private Button chargedInexorableRentalButton;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_help, container, false);
        return v;
    }
}
