package com.lorsku.pennapps2017hack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Guilherme Borges Bastos on 02/17/2016.
 * guilhermeborgesbastos@gmail.com
 */

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private SlideCardFragment slideCardFragment;
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        super.onCreate(savedInstanceState);

        slideCardFragment = new SlideCardFragment(this);

        FragmentManager fm = getSupportFragmentManager();
        fragment = fm.findFragmentByTag("SlideCardFragment");

        if (fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.content, slideCardFragment, "SlideCardFragment");
            ft.commit();
        }


        setContentView(R.layout.main_activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
