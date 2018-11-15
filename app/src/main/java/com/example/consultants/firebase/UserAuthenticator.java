package com.example.consultants.firebase;

import android.app.Activity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAuthenticator {


    private static final String TAG = UserAuthenticator.class.getSimpleName() + "_TAG";
    FirebaseAuth mAuth;
    Callback callback;
    Activity activity;
    CompleteListener completeListener;
    private GoogleSignInClient mGoogleSignInClient;


    public UserAuthenticator(Activity activity, CompleteListener completeListener) {
        mAuth = FirebaseAuth.getInstance();
        this.callback = (Callback) activity;
        this.activity = activity;
        this.completeListener = completeListener;
    }


    public void signIn(String userEmail, String password) {

        completeListener.setType(0);
        mAuth.signInWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(activity, completeListener);


    }


    public void signUp(String userEmail, String password) {

        completeListener.setType(1);
        mAuth.createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(activity, completeListener);
    }


    public void checkSession() {

    }

    public void signOut() {

        mAuth.signOut();
    }


    interface Callback {


        void onUserValidated(FirebaseUser user);


        void onUserInvalidated();
    }
}