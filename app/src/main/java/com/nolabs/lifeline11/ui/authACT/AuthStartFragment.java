package com.nolabs.lifeline11.ui.authACT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.nolabs.lifeline11.R;

public class AuthStartFragment extends Fragment {

    //Views Fields
    private ImageView loginbtn;
    private ImageView registerbtn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.auth_nav_host_fragment);
        View root = inflater.inflate(R.layout.auth_fragment_start, container, false);

        //Views
        loginbtn = root.findViewById(R.id.auth_fragment_start_loginbtn);
        registerbtn = root.findViewById(R.id.auth_fragment_start_registerbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_authStartFragment_to_navigation_loginFragment);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_authStartFragment_to_navigation_registerFragment);
            }
        });





        return root;
    }
}
