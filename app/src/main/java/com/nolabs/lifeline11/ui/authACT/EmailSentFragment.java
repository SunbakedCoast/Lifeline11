package com.nolabs.lifeline11.ui.authACT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textview.MaterialTextView;
import com.nolabs.lifeline11.R;

public class EmailSentFragment extends Fragment {
    MaterialTextView loginbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.auth_nav_host_fragment);
        View root = inflater.inflate(R.layout.auth_fragment_email_sent, container, false);

        loginbtn = root.findViewById(R.id.auth_fragment_email_sent_btn_login);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_emailSentFragment_to_navigation_loginFragment);
            }
        });

        return root;
    }
}
