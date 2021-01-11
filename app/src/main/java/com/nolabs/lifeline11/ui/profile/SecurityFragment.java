package com.nolabs.lifeline11.ui.profile;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nolabs.lifeline11.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SecurityFragment extends Fragment {

    TextInputEditText currentpw;
    TextInputEditText newpw;
    MaterialButton donebtn;


    //Firebase
    FirebaseAuth fauth;
    FirebaseUser user;
    AuthCredential credential;
    String useremail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        View root = inflater.inflate(R.layout.main_fragment_security, container, false);
        fauth = FirebaseAuth.getInstance();
        user = fauth.getCurrentUser();
        useremail = fauth.getCurrentUser().getEmail();


        currentpw = root.findViewById(R.id.main_fragment_security_data_currentpw);
        newpw = root.findViewById(R.id.main_fragment_security_data_newpw);
        donebtn = root.findViewById(R.id.main_Fragment_security_btn_done);


        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String currentpwdata = currentpw.getText().toString().trim();
                final String newpwdata = newpw.getText().toString().trim();


                if (TextUtils.isEmpty(currentpwdata)){
                    currentpw.setError("Enter your current password");
                    return;
                }

                if (TextUtils.isEmpty(newpwdata)){
                    newpw.setError("Enter your new password!");
                    return;
                }

                credential = EmailAuthProvider.getCredential(useremail, currentpwdata);

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            user.updatePassword(newpwdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Snackbar.make(v, "Password Updated", Snackbar.LENGTH_LONG).show();
                                        navigator.navigate(R.id.action_navigation_securityFragment_to_navigation_profileSettings);
                                    } else {
                                        Toast.makeText(getActivity(), "Error: " +task.getException().toString(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Snackbar.make(v, "Password incorrect", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });

        return root;
    }
}
