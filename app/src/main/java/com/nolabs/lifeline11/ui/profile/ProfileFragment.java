package com.nolabs.lifeline11.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nolabs.lifeline11.AuthActivity;
import com.nolabs.lifeline11.R;
import com.nolabs.lifeline11.Utilities.utilities.Model.PostRequest;
import com.nolabs.lifeline11.ui.home.PostViewHolder;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    MaterialTextView biotbn;
    MaterialTextView aboutmebtn;
    ImageView profilesettings;
    CircleImageView profilepic;

    FirebaseAuth fauth;
    String userID;

    MaterialTextView fullname;

    private RecyclerView recyclerView;

    //Firebase
    FirebaseRecyclerOptions<PostRequest> options;
    FirebaseRecyclerAdapter<PostRequest, ProfilePostViewHoolder> adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        View root = inflater.inflate(R.layout.main_fragment_profile, container, false);
        fauth = FirebaseAuth.getInstance();
        userID = fauth.getCurrentUser().getUid();
        BottomNavigationView activitynav = getActivity().findViewById(R.id.nav_view);
        activitynav.setVisibility(View.VISIBLE);


        //Views
        fullname = root.findViewById(R.id.main_fragment_profile_data_profile_name);
        profilepic = root.findViewById(R.id.main_fragment_profile_data_profile_img);
        profilesettings = root.findViewById(R.id.main_fragment_profile_btn_profile_settings);
        recyclerView = root.findViewById(R.id.main_fragment_profile_recyclerview);

        profilesettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_profile_to_navigation_profileSettings);
            }
        });






        profileViewModel.getDataFullName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                fullname.setText(s);
            }
        });


      //  profileViewModel.getProfileImg().observe(getViewLifecycleOwner(), new Observer<String>() {
       //     @Override
       //     public void onChanged(String s) {
       //         Picasso.with(getActivity()).load(s).into(profilepic);
       //         Log.i(TAG, "DATA: "+ s );
       //     }
    //    });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        getData();





        return root;
    }

    private void getData(){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("Users").child(userID).child("UserPosts");

        options = new FirebaseRecyclerOptions.Builder<PostRequest>().setQuery(query, PostRequest.class).build();
        adapter = new FirebaseRecyclerAdapter<PostRequest, ProfilePostViewHoolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ProfilePostViewHoolder holder, final int position, @NonNull final PostRequest model) {
                holder.title.setText(model.getTitle());
                holder.body.setText(model.getBody());
                holder.status.setText(model.getStatus());
                holder.date.setText(model.getTimestamp());
                holder.authorname.setText(model.getAuthorname());
                holder.needs.setText(model.getNeeds());
                holder.city.setText(model.getLocation());
                holder.quantity.setText(model.getQuantity());
                holder.progressBar.setMax(Integer.parseInt(model.getQuantity()));

                //DatabaseReference donorsref = reference.child("Users").child(userID).child("UserPosts").child(model.getHashkey()).child("Donors");
                DatabaseReference donorsref = reference.child("Donations").child(model.getHashkey());

                donorsref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int snap = (int) dataSnapshot.getChildrenCount();

                        holder.donated.setText(String.valueOf(snap));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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
            public ProfilePostViewHoolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.card_feed_profile_item, parent, false);

                return new ProfilePostViewHoolder(view);
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
