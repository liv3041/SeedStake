package com.example.auth.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.auth.R;
import com.example.auth.view.viewmodel.AuthViewModel;
import com.example.main.view.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText user_name;
    private EditText pass_word;
    private Button btn_login;
    private TextView register;
    private AuthViewModel viewModel;
    private TextView btn_forgot;
    private TextView btn_loginOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_name = findViewById(R.id.email_login);
        pass_word = findViewById(R.id.pass_login);
        btn_login = findViewById(R.id.btn_login);
        register = findViewById(R.id.register_login);
        btn_forgot=findViewById(R.id.txtLostpassword);
        btn_loginOtp = findViewById(R.id.loginOtp);
//        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
//                .getInstance(this.getApplication())).get(AuthViewModel.class);
//        viewModel.getUserData().observe(this, firebaseUser -> {
//            if(firebaseUser != null){
//                   startActivity(new Intent(
//                           this, com.example.main.view.HomeActivity.class
//                   ));
//                   finish();
//            }
//        });
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        btn_login.setOnClickListener(view -> {
            String email = user_name.getText().toString().trim();
            String password = pass_word.getText().toString().trim();
            if(email.isEmpty()){
                user_name.setError("Email is Empty.");
                user_name.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                user_name.setError("Enter valid Email Address.");
                user_name.requestFocus();
                return;
            }
            if(password.isEmpty()){
                pass_word.setError("Password is Empty.");
                pass_word.requestFocus();
                return;
            }
            if(pass_word.length()<6){
                pass_word.setError("Length of Password be more than 6.");
                pass_word.requestFocus();
                return;
            }
            viewModel.login(email,password);
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        });

        btn_forgot.setOnClickListener(view -> {
            if(user_name.length()==0){
                Toast.makeText(this,"Enter Email ID ",Toast.LENGTH_SHORT).show();
            }
            else{
                FirebaseAuth.getInstance().setLanguageCode("en");
                FirebaseAuth.getInstance().sendPasswordResetEmail(user_name.getText().toString());
                Toast.makeText(this,"Reset request sent to email",Toast.LENGTH_SHORT).show();
            }
        });

        btn_loginOtp.setOnClickListener(view -> {startActivity(new Intent(getApplicationContext(),LoginOtpActivity.class)); finish();});
        register.setOnClickListener(view -> {startActivity(new Intent(this,RegisterActivity.class)); finish();});

    }
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1 = sh.getString("user_name","");
        String s2 = sh.getString("pass_word","");
        user_name.setText(s1);
        pass_word.setText(s2);
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1 = sh.getString("user_name","");
        String s2 = sh.getString("pass_word","");
        user_name.setText(s1);
        pass_word.setText(s2);
    }
}