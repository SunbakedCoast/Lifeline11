package com.nolabs.lifeline11.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nolabs.lifeline11.R;
import com.nolabs.lifeline11.Utilities.utilities.Model.Notifications;
import com.nolabs.lifeline11.ui.profile.ProfileViewModel;
import com.squareup.picasso.Picasso;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NotificationsFragment extends Fragment {

    ProfileViewModel profileViewModel;
    String userID;
    FirebaseAuth fauth;

    //Views
    private RecyclerView recyclerView;

    //Firebase
    FirebaseRecyclerOptions<Notifications> options;
    FirebaseRecyclerAdapter<Notifications, NotificationsViewHolder> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        fauth = FirebaseAuth.getInstance();
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.main_fragment_notifications, container, false);

        recyclerView = root.findViewById(R.id.main_fragment_notification_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();

        return root;
    }


    private void getData(){

        userID = fauth.getCurrentUser().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference notifref = reference.child("Users").child(userID);
        Query query = notifref.child("Notifications");

        Log.i(TAG, "DATA: " +query);


        options = new FirebaseRecyclerOptions.Builder<Notifications>().setQuery(query, Notifications.class).build();

        adapter = new FirebaseRecyclerAdapter<Notifications, NotificationsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull NotificationsViewHolder holder, final int position, @NonNull final Notifications model) {
               holder.title.setText(model.getTitle());
               holder.timestamp.setText(model.getTimestamp());
             //   Picasso.with(getActivity()).load(model.getDonorimg()).into(holder.userimg);

            }

            @NonNull
            @Override
            public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.card_notification_item, parent, false);
                return new NotificationsViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}