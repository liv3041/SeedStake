package com.example.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.auth.R;
import com.example.auth.view.viewmodel.AuthViewModel;
import com.example.main.view.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class LoginOtpActivity extends AppCompatActivity {
    public static Activity getInstance;
    private Button btn_Otp;
    private EditText phoneNumber;
    private CountryCodePicker ccp;
    FirebaseAuth firebaseAuth;
    private AuthViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);
        btn_Otp=findViewById(R.id.getOtp);
        ccp = findViewById(R.id.country);
        phoneNumber =findViewById(R.id.phone_otp);
        ccp.registerCarrierNumberEditText(phoneNumber);
////        firebaseAuth.setLanguageCode("fr");
//        viewModel.setActivity(this);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
       btn_Otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(phoneNumber.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Please Enter Phone Number",Toast.LENGTH_SHORT).show();

                }else{
                    String number = phoneNumber.getText().toString().trim();
                    String getNo = ccp.getDefaultCountryCodeWithPlus().replace(" ", "");
                    viewModel.sendCode(getNo+number);
                    Intent inntent = new Intent(getApplicationContext(),VerifyOtpActivity.class);
                    startActivity(inntent);
                    finish();

                }

            }
        });
    }

}