package com.nolabs.lifeline11.ui.lifeline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.nolabs.lifeline11.R;

import static android.provider.LiveFolders.INTENT;

public class LifelineFragment extends Fragment {

    private LifelineViewModel dashboardViewModel;
    private ImageView btnfacebook;
    private MaterialCardView aboutus;

    public static String FACEBOOK_URL = "https://www.facebook.com/Lifeline-108139684196159/";
    public static String FACEBOOK_PAGE_ID = "Lifeline";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(LifelineViewModel.class);
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        View root = inflater.inflate(R.layout.main_fragment_lifeline, container, false);

        aboutus = root.findViewById(R.id.main_fragment_lifeline_clickablecard_about_us);
        btnfacebook = root.findViewById(R.id.main_fragment_lifeline_link_facebook);

        btnfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookUrl(getActivity());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_lifeline_to_navigation_aboutUsFragment);
            }
        });

        return root;
    }

    public String getFacebookUrl(Context context){
        PackageManager packageManager = context.getPackageManager();

        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        }
        catch (PackageManager.NameNotFoundException e){
            return FACEBOOK_URL;
        }
    }

}