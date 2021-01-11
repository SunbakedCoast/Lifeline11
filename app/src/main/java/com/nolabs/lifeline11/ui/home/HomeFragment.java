package com.nolabs.lifeline11.ui.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nolabs.lifeline11.R;
import com.nolabs.lifeline11.Utilities.utilities.Model.Notifications;
import com.nolabs.lifeline11.Utilities.utilities.Model.PostRequest;
import com.nolabs.lifeline11.Utilities.utilities.Model.User;
import com.nolabs.lifeline11.ui.profile.ProfileViewModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {

    ProfileViewModel profileViewModel;
    PostCounterViewmodel postCounterViewmodel;
    String close = "Closed";



    //Views
    private FloatingActionButton extendedfabrequest;
    private RecyclerView recyclerView;

    //Firebase
     FirebaseRecyclerOptions<PostRequest> options;
     FirebaseRecyclerAdapter<PostRequest, PostViewHolder> adapter;





    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        postCounterViewmodel = ViewModelProviders.of(this).get(PostCounterViewmodel.class);
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        View root = inflater.inflate(R.layout.main_fragment_home, container, false);
        BottomNavigationView activitynav = getActivity().findViewById(R.id.nav_view);
        activitynav.setVisibility(View.VISIBLE);

        //Views
        extendedfabrequest = root.findViewById(R.id.main_fragment_home_fab_request);





        extendedfabrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_home_to_navigation_requestIssueFragment);
            }
        });

        postCounterViewmodel.getPostOount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer < 3){
                    extendedfabrequest.setVisibility(View.VISIBLE);
                }
            }
        });

        //options = new FirebaseRecyclerOptions.Builder<PostRequest>().setQuery(databaseReference, PostRequest.class).build();
       // adapter = new FirebaseRecyclerAdapter<PostRequest, PostViewHolder>(options)
        recyclerView = root.findViewById(R.id.main_fragment_home_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();



        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        Log.i(TAG, "Started");

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        Log.i(TAG, "DESTROYED");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "PAUSED");
    }

    private void getData(){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Posts");

        Log.i(TAG, "data: " +query);

        options = new FirebaseRecyclerOptions.Builder<PostRequest>().setQuery(query, PostRequest.class ).build();

        adapter = new FirebaseRecyclerAdapter<PostRequest, PostViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final PostViewHolder holder, final int position, @NonNull final PostRequest model) {
                holder.title.setText(model.getTitle());
                holder.body.setText(model.getBody());
                holder.status.setText(model.getStatus());
                holder.date.setText(model.getTimestamp());
                holder.authorname.setText(model.getAuthorname());
                holder.needs.setText(model.getNeeds());
                holder.city.setText(model.getLocation());
                holder.quantity.setText(model.getQuantity());
               // holder.progressBar.setMax(Integer.parseInt(model.getQuantity()));
                //holder.progressBar.setProgress(model.getQuantitydonated());
                //holder.donated.setText(String.valueOf(model.getQuantitydonated()));

                DatabaseReference donorsref = reference.child("Donations").child(model.getHashkey());

                donorsref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int snap = (int) dataSnapshot.getChildrenCount();

                         holder.donated.setText(String.valueOf(snap));

                         if (snap == Integer.parseInt(model.getQuantity())){
                             DatabaseReference posts = reference.child("Posts").child(model.getHashkey()).child("status");
                             posts.setValue(close);
                         }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





             //   Picasso.with(getActivity()).load(model.getAuthorimg()).into(holder.authorimg);
                holder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.getItem(position);

                        //solution pass the post id as bundle then retrieve the additional data to another fragment using the post hashkey
                        Bundle hashkey = new Bundle();
                        String id = model.getHashkey();
                        String title = model.getTitle();
                        String body = model.getBody();
                        String author = model.getAuthorname();
                        //String authorimg = model.getAuthorimg();
                        String status = model.getStatus();
                        String date = model.getTimestamp();
                        String needs = model.getNeeds();
                        String notifID = model.getNotificationID();
                        String location = model.getLocation();
                        String donadress = model.getDonationaddress();

                        String quantity = model.getQuantity();



                        hashkey.putString("hash", id );
                        hashkey.putString("title", title );
                        hashkey.putString("body", body);
                        hashkey.putString("date", date);
                        hashkey.putString("author", author);
                        hashkey.putString("quantity", quantity);
                       // hashkey.putString("authorimg", authorimg);
                        hashkey.putString("status", status);
                        hashkey.putString("needs", needs);
                        hashkey.putString("notificationidcard", notifID);
                        hashkey.putString("location", location);
                        hashkey.putString("donationaddress", donadress);
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_global_postFragment, hashkey);
                    }
                });

            }

            @NonNull
            @Override
            public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.card_feed_item, parent, false);
            return new PostViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);


    }



}