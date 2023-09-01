package com.example.auth.view.viewmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.auth.view.Repository.AuthenticationRepository;
import com.example.auth.view.Repository.PhoneAuthRepository;
import com.google.firebase.auth.FirebaseUser;


public class AuthViewModel extends AndroidViewModel {
    private final AuthenticationRepository repository;
    private Activity activity;
//    @SuppressLint("StaticFieldLeak")
    private final PhoneAuthRepository phoneAuthRepository;
    private final String TAG = "AuthViewModel";
    public AuthViewModel(@NonNull Application application) {
        super(application);
        repository = new AuthenticationRepository();
        phoneAuthRepository =new PhoneAuthRepository(application);

    }
   public void setActivity(Activity activity){
        this.activity = activity;
        phoneAuthRepository.setActivity(activity);
}

    public void register(String email, String password){
        repository.register(email,password);
    }
    public void  login(String email , String pass){
        repository.login(email, pass);
    }
    public void verifyOtp(Activity activity,String code){
        if (activity != null) {
            phoneAuthRepository.verifyCode(activity, code);
        } else {
            // Handle the case where the activity reference is null.
            Log.e(TAG ,"Activity is null");
        }}
    public void sendCode(String phoneNumber){phoneAuthRepository.sendVerficationCode(phoneNumber);}
}
