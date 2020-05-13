package com.danielkarlkvist.Umberent.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.danielkarlkvist.Umberent.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DamageReportFragment extends Fragment {

    CheckBox checkBoxStand;
    CheckBox checkBoxUmberlla;
    CheckBox checkBoxOther;

    EditText editTextSubject;
    EditText editTextStand;
    EditText editTextUmbrella;
    EditText editTextDescription;

    Button buttonSend;


    public DamageReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_damage_report, container, false);

        initializeViews(v);
        initializeButtonListeners();

        return v;
    }

    private void sendMail() {
        String[] recipent = {"marcusaxelsson52@gmail.com"};
        String subject = editTextSubject.getText().toString();
        String message = initializeStringDescription();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipent);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client:"));
    }

    private String initializeStringDescription() {
        String stand = editTextStand.getText().toString();
        String umbrella = editTextUmbrella.getText().toString();
        String description = editTextDescription.getText().toString();

        String fullMessage = "Stand: " + stand + "\n" + "Umbrella: " + umbrella + "\n" + description;

        return fullMessage;
    }

    private void initializeViews(View v) {
        checkBoxStand = v.findViewById(R.id.damage_stand_checkbox);
        checkBoxUmberlla = v.findViewById(R.id.damage_umbrella_checkbox);
        checkBoxOther = v.findViewById(R.id.damage_other_checkbox);

        editTextSubject = v.findViewById(R.id.damage_subject_editText);
        editTextStand = v.findViewById(R.id.damage_stand_editText);
        editTextUmbrella = v.findViewById(R.id.damage_umbrella_editText);
        editTextDescription = v.findViewById(R.id.damage_description_editText);

        buttonSend = v.findViewById(R.id.damage_send_button);
    }


    private void initializeButtonListeners() {
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }


}
