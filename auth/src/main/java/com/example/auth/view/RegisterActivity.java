package com.example.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auth.R;
import com.example.auth.view.viewmodel.AuthViewModel;
//import com.google.android.ads.mediationtestsuite.activities.HomeActivity;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class RegisterActivity extends AppCompatActivity {
    EditText user_name, pass_word,cnf,nameI,nameL,phone;
    private Button register;
    private TextView login;
    private AuthViewModel viewModel;
    private CountryCodePicker ccp ;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_name = findViewById(R.id.email_register);
        pass_word = findViewById(R.id.password_register);
        register = findViewById(R.id.btn_register);
        login = findViewById(R.id.login_register);
        cnf = findViewById(R.id.confirmPassword_register);
        nameI=findViewById(R.id.name_register);
        nameL=findViewById(R.id.last_name);
        phone=findViewById(R.id.phone_register);
        ccp = findViewById(R.id.ccp);
//        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
//                .getInstance(this.getApplication())).get(AuthViewModel.class);
//        viewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
//            @Override
//            public void onChanged(FirebaseUser firebaseUser) {
//                if(firebaseUser != null){
//                           startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                }
//            }
//        });

        register.setOnClickListener(view -> {
            String email = user_name.getText().toString().trim();
            String password = pass_word.getText().toString().trim();
            String last_name = nameL.getText().toString().trim();
            String name_user = nameI.getText().toString().trim();
            String phone_no = phone.getText().toString().trim();
            String cnfrm = cnf.getText().toString().trim();
            String cc = ccp.getSelectedCountryCodeWithPlus().trim();
            if (email.isEmpty()) {
                user_name.setError("Email is Empty.");
                user_name.requestFocus();
                return;
            }
            if (name_user.isEmpty()) {
                nameI.setError("Name of the User is Empty.");
                nameI.requestFocus();
                return;
            }
            if (last_name.isEmpty()) {
                nameL.setError("Last Name is Empty.");
                nameL.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                user_name.setError("Enter the valid email address.");
                user_name.requestFocus();
                return;
            }
            if (phone_no.isEmpty()){
                phone.setError("Phone Number is Empty.");
                phone.requestFocus();
                return;
            }
            if (phone_no.length()>10 || phone_no.length()<10){
                phone.setError("Phone Number should be of 10 digits.");
                phone.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                pass_word.setError("Password is Empty.");
                pass_word.requestFocus();
                return;
            }
            if (password.length() < 6) {
                pass_word.setError("Password length should be more than 6.");
                pass_word.requestFocus();
                return;
            }
            if (!cnfrm.equals(password)) {
                Toast.makeText(RegisterActivity.this, "The password and confirm password does not matches", Toast.LENGTH_SHORT).show();
                pass_word.setError("Please Enter Again");
                cnf.setError("The password and confirm password does not matches.");
                pass_word.requestFocus();
                return;
            }
             viewModel.register(email,password);
        });
        login.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
    }
}