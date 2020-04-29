package com.danielkarlkvist.umberent;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Button editButton;

    private TextView fullNameHintTextView;
    private TextView userNameHintTextView;
    private TextView mailHintTextView;

    private TextView fullNameTextView;
    private TextView userNameTextView;
    private TextView mailTextView;

    private TextView editFirstNameTextView;
    private EditText firstNameEditText;
    private TextView editLastNameTextView;
    private EditText lastNameEditText;
    private TextView editUserNameTextView;
    private EditText userNameEditText;
    private TextView editMailTextView;
    private EditText mailEditText;

    private Umberent umberent;
    private IProfile user;

    boolean isInEditingMode = false;

    public ProfileFragment(Umberent umberent) {
        this.umberent = umberent;
        this.user = umberent.getProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        initializeViews(v);
        initializeButtonListeners();

        fullNameTextView.setText(user.getFullName());
        userNameTextView.setText(user.getUsername());
        mailTextView.setText(user.getMail());

        return v;
    }

    private void initializeButtonListeners() {
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isInEditingMode) {
                    isInEditingMode = true;
                    editProfile();
                } else if (!firstNameEditText.getText().toString().equals("")) {
                    isInEditingMode = false;
                    hideKeyboard(view);
                    saveProfile();
                }
            }
        });
    }

    private void initializeViews(View v) {
        fullNameHintTextView = v.findViewById(R.id.profile_name_hint);
        userNameHintTextView = v.findViewById(R.id.profile_username_hint);
        mailHintTextView = v.findViewById(R.id.profile_mail_hint);

        fullNameTextView = v.findViewById(R.id.profile_name);
        userNameTextView = v.findViewById(R.id.profile_username);
        mailTextView = v.findViewById(R.id.profile_mail);

        editButton = v.findViewById(R.id.profile_edit_button);
        editFirstNameTextView = v.findViewById(R.id.profile_edit_firstname_hint);
        firstNameEditText = v.findViewById(R.id.profile_edit_firstname);
        editLastNameTextView = v.findViewById(R.id.profile_edit_lastname_hint);
        lastNameEditText = v.findViewById(R.id.profile_edit_lastname);
        editUserNameTextView = v.findViewById(R.id.profile_edit_username_hint);
        userNameEditText = v.findViewById(R.id.profile_edit_username);
        editMailTextView = v.findViewById(R.id.profile_edit_mail_hint);
        mailEditText = v.findViewById(R.id.profile_edit_mail);
    }

    private void editProfile() {
        editButton.setText("Spara");

        editUserInformation();
        changeVisibilityForEditMode();

        placeCursorAfterText(firstNameEditText);
    }

    private void saveProfile() {
        editButton.setText("Ändra");

        placeNewUserInformation();
        changeVisibilityForStandardMode();
    }

    private void editUserInformation() {
        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());
        userNameEditText.setText(user.getUsername());
        mailEditText.setText(user.getMail());
    }

    private void changeVisibilityForEditMode() {
        fullNameHintTextView.setVisibility(View.INVISIBLE);
        fullNameTextView.setVisibility(View.INVISIBLE);
        userNameHintTextView.setVisibility(View.INVISIBLE);
        userNameTextView.setVisibility(View.INVISIBLE);
        mailHintTextView.setVisibility(View.INVISIBLE);
        mailTextView.setVisibility(View.INVISIBLE);

        editFirstNameTextView.setVisibility(View.VISIBLE);
        firstNameEditText.setVisibility(View.VISIBLE);
        editLastNameTextView.setVisibility(View.VISIBLE);
        lastNameEditText.setVisibility(View.VISIBLE);
        editUserNameTextView.setVisibility(View.VISIBLE);
        userNameEditText.setVisibility(View.VISIBLE);
        editMailTextView.setVisibility(View.VISIBLE);
        mailEditText.setVisibility(View.VISIBLE);
    }

    private void changeVisibilityForStandardMode() {
        fullNameHintTextView.setVisibility(View.VISIBLE);
        fullNameTextView.setVisibility(View.VISIBLE);
        userNameHintTextView.setVisibility(View.VISIBLE);
        userNameTextView.setVisibility(View.VISIBLE);
        mailHintTextView.setVisibility(View.VISIBLE);
        mailTextView.setVisibility(View.VISIBLE);

        editFirstNameTextView.setVisibility(View.INVISIBLE);
        firstNameEditText.setVisibility(View.INVISIBLE);
        editLastNameTextView.setVisibility(View.INVISIBLE);
        lastNameEditText.setVisibility(View.INVISIBLE);
        editUserNameTextView.setVisibility(View.INVISIBLE);
        userNameEditText.setVisibility(View.INVISIBLE);
        editMailTextView.setVisibility(View.INVISIBLE);
        mailEditText.setVisibility(View.INVISIBLE);
    }

    private void placeCursorAfterText(EditText editText) {
        int textLength = editText.getText().toString().length();
        editText.setSelection(textLength);
    }

    private void placeNewUserInformation() {
        user.setFirstName(firstNameEditText.getText().toString());
        user.setLastName(lastNameEditText.getText().toString());
        fullNameTextView.setText(user.getFullName());

        user.setUsername(userNameEditText.getText().toString());
        userNameTextView.setText(user.getUsername());
        user.setMail(mailEditText.getText().toString());
        mailTextView.setText(user.getMail());
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
