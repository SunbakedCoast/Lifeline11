package com.nolabs.lifeline11.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.nolabs.lifeline11.AuthActivity;
import com.nolabs.lifeline11.R;

public class ProfileSettings extends Fragment {

    FirebaseAuth fauth;
    MaterialButton logoutbtn;
    MaterialCardView security;
    MaterialCardView contactus;
    MaterialCardView terms;
    MaterialCardView help;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        View root = inflater.inflate(R.layout.main_fragment_profile_settings, container, false);
        fauth = FirebaseAuth.getInstance();

        logoutbtn = root.findViewById(R.id.main_fragment_profile_settings_btn_logout);
        security = root.findViewById(R.id.main_fragment_profile_settings_btn_security);
        contactus = root.findViewById(R.id.main_fragment_profile_settings_btn_contactus);
        terms = root.findViewById(R.id.main_fragment_profile_settings_btn_terms);
        help = root.findViewById(R.id.main_fragment_profile_settings_btn_helpcenter);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_profileSettings_to_navigation_helpCenterFragment);
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_profileSettings_to_navigation_termsFragment);
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"Lifelineunityxx@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(intent, ""));
            }
        });

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_profileSettings_to_navigation_securityFragment);
            }
        });



        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fauth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), AuthActivity.class));
            }
        });

        return root;
    }
}
