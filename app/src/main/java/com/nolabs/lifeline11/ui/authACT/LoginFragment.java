package com.nolabs.lifeline11.ui.authACT;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nolabs.lifeline11.MainActivity;
import com.nolabs.lifeline11.NewUserActivity;
import com.nolabs.lifeline11.R;

public class LoginFragment extends Fragment {

    private ProgressBar progressBar;
    private TextInputEditText email;
    private TextInputEditText password;
    private ImageView loginbtn;
    private MaterialTextView forgotpwbtn;

    FirebaseAuth fauth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final NavController navigator = Navigation.findNavController(getActivity(), R.id.auth_nav_host_fragment);
        View root = inflater.inflate(R.layout.auth_fragment_login, container, false);

        fauth = FirebaseAuth.getInstance();

        email = root.findViewById(R.id.auth_fragment_login_input_email);
        password = root.findViewById(R.id.auth_fragment_login_input_password);
        loginbtn = root.findViewById(R.id.auth_fragment_login_btn_login);
        forgotpwbtn = root.findViewById(R.id.auth_fragment_login_btntxt_forgot_password);
        progressBar = root.findViewById(R.id.auth_fragment_login_progressbar);

        forgotpwbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigate(R.id.action_navigation_loginFragment_to_navigation_forgotPasswordFragment);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String demail = email.getText().toString().trim();
                String dpassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(demail)){
                    email.setError("Email required");
                    return;
                }
                if (TextUtils.isEmpty(dpassword)){
                    password.setError("Password required");
                    return;
                }
                showProgressBar();
                fauth.signInWithEmailAndPassword(demail, dpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            boolean newUser = task.getResult().getAdditionalUserInfo().isNewUser();

                            if (newUser) {
                                startActivity(new Intent(getActivity(), NewUserActivity.class));
                                getActivity().finish();
                            } else {
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                            }
                        }
                         else {
                            Toast.makeText(getActivity(), "Error: " +task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            hideProgressBar();

                        }


                    }
                });

            }
        });



        return root;
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);

    }
}
