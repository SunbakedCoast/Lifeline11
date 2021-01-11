package com.nolabs.lifeline11.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nolabs.lifeline11.R;
import com.nolabs.lifeline11.Utilities.utilities.Model.User;

public class SearchProfileFragment extends Fragment {

    private RecyclerView recyclerView;

    //Firebase
    FirebaseRecyclerOptions<User> options;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment_search_profile, container, false);

        return root;
    }
}
