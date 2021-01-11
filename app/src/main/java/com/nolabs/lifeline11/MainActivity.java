package com.nolabs.lifeline11;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nolabs.lifeline11.ui.notifications.NotificationsViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    NotificationsViewModel notificationsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView navView = findViewById(R.id.nav_view);

        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);


        notificationsViewModel.getNotificationCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                navView.getOrCreateBadge(R.id.navigation_notifications).setNumber(integer);
                if (integer == 0){
                    navView.removeBadge(R.id.navigation_notifications);
                }

            }
        });
        


    }

}
