package com.zingbyte.batmanfoodorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zingbyte.batmanfoodorder.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
    public void sign(View view){

        Intent intent = new Intent(SignupActivity.this,SignInActivity.class);
        startActivity(intent);
    }
}
