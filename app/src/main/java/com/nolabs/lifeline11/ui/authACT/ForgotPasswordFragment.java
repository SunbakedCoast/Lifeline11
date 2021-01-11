package com.nolabs.lifeline11.ui.authACT;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
import com.google.firebase.auth.FirebaseAuth;
import com.nolabs.lifeline11.R;

public class ForgotPasswordFragment extends Fragment {
    EditText email;
    MaterialButton resetbtn;

    FirebaseAuth fauth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fauth = FirebaseAuth.getInstance();
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.auth_nav_host_fragment);
        View root = inflater.inflate(R.layout.auth_fragment_forgotpw, container, false);


        email = root.findViewById(R.id.auth_fragment_forgotpw_input_email);
        resetbtn = root.findViewById(R.id.auth_fragment_forgotpw_btn_reset);

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String emaildata = email.getText().toString().trim();

                if (TextUtils.isEmpty(emaildata)){
                    email.setError("Plase enter your email");
                    return;
                }

                fauth.sendPasswordResetEmail(emaildata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        navigator.navigate(R.id.action_navigation_forgotPasswordFragment_to_navigation_emailSentFragment);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(v, "Email does not exist", Snackbar.LENGTH_LONG).show();
                    }
                });

            }
        });

        return root;
    }


}
