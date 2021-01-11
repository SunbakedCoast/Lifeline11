package com.nolabs.lifeline11.ui.authACT;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nolabs.lifeline11.MainActivity;
import com.nolabs.lifeline11.NewUserActivity;
import com.nolabs.lifeline11.R;
import com.nolabs.lifeline11.Utilities.utilities.Model.User;

public class RegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    //Views
    private TextInputEditText firstname;
    private TextInputEditText lastname;
    private TextInputEditText email;
    private TextInputEditText password;
    private Spinner genderspinner;
    private CheckBox checkboxtermsxconditions;
    private MaterialTextView termsxconditions;
    private MaterialTextView policy;
    private ImageView registerbtn;
    private ProgressBar progressBar;

    //BackEnd
    private String userID;
    private FirebaseAuth fauth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.auth_fragment_register, container, false);
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.auth_nav_host_fragment);

        //BackEnd
        fauth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");


        //Views
        firstname = root.findViewById(R.id.auth_fragment_register_input_first_name);
        lastname = root.findViewById(R.id.auth_fragment_register_input_last_name);
        email = root.findViewById(R.id.auth_fragment_register_input_email);
        password = root.findViewById(R.id.auth_fragment_register_input_password);
        genderspinner = root.findViewById(R.id.auth_fragment_register_spinner_gender);
        checkboxtermsxconditions = root.findViewById(R.id.auth_fragment_register_checkbox_termsxconditions);
        termsxconditions = root.findViewById(R.id.auth_fragment_register_btn_termsxconditions);
        policy = root.findViewById(R.id.auth_fragment_register_btn_policy);
        registerbtn = root.findViewById(R.id.auth_fragment_register_registerbtn);
        progressBar = root.findViewById(R.id.auth_fragment_register_progressbar);

        //termsxconditions here
        termsxconditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_registerFragment_to_navigation_termsFragment);
            }
        });

        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_registerFragment_to_navigation_policyFragment);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(adapter);
        genderspinner.setOnItemSelectedListener(this);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String dfirstname = firstname.getText().toString().trim();
                final String dlastname = lastname.getText().toString().trim();

                final String dfullname = (dfirstname+ " " +dlastname);

                final String demail = email.getText().toString().trim();
                String dpassword = password.getText().toString().trim();
                final String dgender = genderspinner.getSelectedItem().toString();
                final boolean dpermission = true;
                final boolean dverified = false;



                if (TextUtils.isEmpty(dfirstname)){
                    firstname.setError("First name is required");
                    return;
                }

                if (TextUtils.isEmpty(dlastname)){
                    lastname.setError("Last name is required");
                    return;
                }

                if (TextUtils.isEmpty(demail)){
                    email.setError("Email required");
                    return;
                }

                if (TextUtils.isEmpty(dpassword)){
                    password.setError("Please input your password");
                    return;
                }

                if (dpassword.length() < 6){
                    password.setError("Password must be greater than 6 characters");
                    return;
                }


                if (checkboxtermsxconditions.isChecked()){
                    showProgressbar();
                    fauth.createUserWithEmailAndPassword(demail, dpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                boolean newUser = task.getResult().getAdditionalUserInfo().isNewUser();


                                if (newUser){
                                    startActivity(new Intent(getActivity(), NewUserActivity.class));
                                    getActivity().finish();
                                }
                                else {
                                    startActivity(new Intent(getActivity(), MainActivity.class));
                                    getActivity().finish();
                                }
                                userID = fauth.getCurrentUser().getUid();
                                User user = new User(dfirstname, dlastname, dfullname, demail, dgender, dpermission, dverified);
                                DatabaseReference userdata = databaseReference.child(userID);
                                userdata.setValue(user);
                                Snackbar.make(v, "user created", Snackbar.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), "Error: " +task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                hideProgressBar();

                            }
                        }
                    });
                } else {
                    checkboxtermsxconditions.setError("Accept");
                    Snackbar.make(v, "Please accept Terms and Conditions", Snackbar.LENGTH_LONG).show();


                }

            }
        });

        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showProgressbar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }
}
