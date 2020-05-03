package com.danielkarlkvist.umberent;

import android.net.IpPrefix;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView fullNameTextView;
    private TextView usernameTextView;
    private TextView mailTextView;

    private Umberent umberent = Umberent.getInstance();
    private IProfile user;

    public ProfileFragment() {
        this.user = umberent.getProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        initializeViews(v);

        fullNameTextView.setText(user.getFullName());
        usernameTextView.setText(user.getUsername());
        mailTextView.setText(user.getMail());

        return v;
    }

    // finds the correct view in the xml file and connects it to the instance variables
    private void initializeViews(View v) {
        fullNameTextView = v.findViewById(R.id.profile_name);
        usernameTextView = v.findViewById(R.id.profile_username);
        mailTextView = v.findViewById(R.id.profile_mail);
    }
}
