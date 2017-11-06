package com.zingbyte.batmanfoodorder.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zingbyte.batmanfoodorder.R;



public class SignupActivity extends AppCompatActivity {

    TextView underline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

     //   underline = (TextView)findViewById(R.id.sign_up_text);
       // underline.setPaintFlags(underline.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

    }


    public void sign(View view){

        Intent intent = new Intent(SignupActivity.this,SignInActivity.class);
        startActivity(intent);
    }
}
