package com.example.auth.view.Repository;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class PhoneAuthRepository  {

    public FirebaseAuth firebaseAuth;
    private String phoneNumber;
    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private final MutableLiveData<Activity> activityLiveData = new MutableLiveData<>();
    private final String TAG = "PhoneAuthRepository";
    private String verifyId;
    private Activity activity;
    private WeakReference<Activity> activityRef;
//    private MutableLiveData<Activity> activityLiveData = new MutableLiveData<>();


    public PhoneAuthRepository(Application application){
        firebaseAuth = FirebaseAuth.getInstance();
        mCallbacks= getPhoneAuthCallbacks();
    }
    public void setActivity(Activity activity) {
        activityLiveData.setValue(activity);
    }
    private void observeActivity(LifecycleOwner owner) {
        activityLiveData.observe(owner, new Observer<Activity>() {
            @Override
            public void onChanged(Activity activity) {
                // Handle the activity reference change here if needed
                // For example, you can check for null or perform other actions.
                Log.w(TAG,"ActivityLiveData:IsNull");
            }
        });
    }
    private Activity getActivity() {
        return activityLiveData.getValue();
    }
//    void FirebaseAuthMissingActivityForRecaptchaException()
//public void setActivity(Activity activity) {
//     activityRef = new WeakReference<>(activity);
//}


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks getPhoneAuthCallbacks() {
        return new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
                   final String otp = phoneAuthCredential.getSmsCode();
                   if(otp!=null){  verifyCode(activity,otp);}


                signInWithPhoneAuthCredential(activity,phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Log.w(TAG, "onVerificationFailed:InvalidRequest");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Log.d(TAG, "onVerificationFailed:signInWithCredential:The SMS quota for the project has been exceeded");
                } else if (e instanceof FirebaseAuthMissingActivityForRecaptchaException) {
                    // reCAPTCHA verification attempted with null Activity
                    Log.w(TAG, "onVerificationFailed:reCAPTCHA verification attempted with null Activity");
                }
            }
            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                PhoneAuthRepository.this.verifyId = verificationId;
            }
        };
    }




    private void signInWithPhoneAuthCredential(Activity activity ,PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Log.w(TAG, "signInWithCredential:Invalid", task.getException());
                            }
                        }
                    }
                });
    }
    public void verifyCode(Activity activity,String code) {
        Log.d(TAG, "Verification ID: " + verifyId);
        Log.d(TAG, "Verification Code: " + code);
        Log.d(TAG,"Activity: "+ activity);
        if (verifyId != null && !verifyId.isEmpty() && activity!=null) {
            PhoneAuthRepository.this.activity = activity;
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyId, code);
            signInWithPhoneAuthCredential(activity, credential);
        } else {
            Log.e(TAG, "Verification ID is null or empty. Unable to create PhoneAuthCredential.");
        }
    }
    public void sendVerficationCode(String phoneNumber){
        Activity activity = getActivity();
        if (activity != null) {
            PhoneAuthOptions options =
                    PhoneAuthOptions.newBuilder(firebaseAuth)
                            .setPhoneNumber(phoneNumber)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(activity)
                            .setCallbacks(mCallbacks)
                            .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        } else {
            Log.e(TAG, "Activity is null. Unable to initiate phone number verification.");
            // Handle the case where the activity is null (not available).
        }
    }

//    public void checkPhoneNumberRegistration(String phoneNumber) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        CollectionReference usersRef = db.collection("users");
//
//        usersRef.whereEqualTo("phoneNumber", phoneNumber)
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        QuerySnapshot querySnapshot = task.getResult();
//                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
//                            // Phone number is already registered
//                            // Handle the case here, e.g., show an error message
//                            // or prevent login with OTP.
//                            // You can also get the user data from the querySnapshot if needed.
//                            Log.d(TAG, "Phone number is already registered.");
//                        } else {
//                            // Phone number is not registered
//                            // Proceed with the OTP login process.
//                            // Call the sendVerificationCode() method here.
//                            sendVerficationCode(phoneNumber);
//                        }
//                    } else {
//                        // Error occurred while checking the phone number
//                        Log.e(TAG, "Error checking phone number: " + task.getException());
//                    }
//                });
//    }



}
