package com.example.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.auth.R;
import com.example.auth.view.viewmodel.AuthViewModel;
import com.example.main.view.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.nio.charset.StandardCharsets;

public class VerifyOtpActivity extends AppCompatActivity {
   private EditText otpCode;
   private Button btn_otpLogin;
   private AuthViewModel viewModel;
   private final String TAG ="VerifyOTPActivity";
   private final EditText[] optEditTexts = new EditText[6];
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        btn_otpLogin = findViewById(R.id.btn_loginOtp);
//        optEditTexts[0]= findViewById(R.id.inputCode1);
//        optEditTexts[1]=findViewById(R.id.inputCode2);
//        optEditTexts[2]=findViewById(R.id.inputCode3);
//        optEditTexts[3]=findViewById(R.id.inputCode4);
//        optEditTexts[4]=findViewById(R.id.inputCode5);
//        optEditTexts[5]=findViewById(R.id.inputCode6);
        otpCode=findViewById(R.id.phone_otp_et);


////        firebaseAuth.setLanguageCode("fr");
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        viewModel.setActivity(this);

        btn_otpLogin.setOnClickListener(view -> {
            String otp = otpCode.getText().toString().trim();
            if(TextUtils.isEmpty(otp.getBytes().toString())){
                Toast.makeText(getApplicationContext(),"Please enter Otp",Toast.LENGTH_SHORT).show();
            }else {
                viewModel.verifyOtp(this,otp);
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
                Log.w(TAG,"Unable to open Activity");
            }
        });

    }
    private String getOtpFromEditTexts() {
        StringBuilder otpStringBuilder = new StringBuilder();
        for (EditText editText : optEditTexts) {
            otpStringBuilder.append(editText.getText().toString().trim());
        }
        return otpStringBuilder.toString().trim();
    }
}