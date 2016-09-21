package com.mac.training.collectioner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mac.training.collectioner.R;
import com.mac.training.collectioner.activity.collection.ViewCollectionActivity;
import com.mac.training.collectioner.activity.login.LoginActivity;

public class MainActivity extends AppCompatActivity {


    private static final int LOGIN_RESULT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (checkForUser()) {
            startActivity(new Intent(this, ViewCollectionActivity.class));
        }
    }

    public void login(View view) {
        startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_RESULT);
    }

    private boolean checkForUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
            return true;
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_RESULT) {
            if (resultCode == RESULT_OK) {
                startActivity(new Intent(this, ViewCollectionActivity.class));
            }
        }
    }
}
