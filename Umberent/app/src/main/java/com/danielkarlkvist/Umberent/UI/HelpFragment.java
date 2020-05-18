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

    private Button reportUmbrellaBugs;

    private DamageReportFragment damageReportFragment= new DamageReportFragment();

    public HelpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_help, container, false);

        initializeViews(v);
        initializeButtonListeners();

        return v;
    }

    private void initializeViews(View v) {
        reportUmbrellaBugs = v.findViewById(R.id.umbrellaBugReportButton);
    }

    private void initializeButtonListeners() {
        reportUmbrellaBugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, damageReportFragment).commit();
            }
        });
    }
}
