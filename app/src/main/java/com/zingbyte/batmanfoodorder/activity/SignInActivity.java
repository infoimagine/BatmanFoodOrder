package com.zingbyte.batmanfoodorder.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zingbyte.batmanfoodorder.R;
import com.zingbyte.batmanfoodorder.utility.Constants;

import net.bohush.geometricprogressview.GeometricProgressView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class SignInActivity extends AppCompatActivity {

    Button login;
    EditText email, password;
    String str_email, str_password;
    Dialog dialog;
    TextInputLayout pass_input_layout, email_input_layout;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        login = (Button) findViewById(R.id.btn_signup);

        pass_input_layout = (TextInputLayout) findViewById(R.id.pass_input_layout);
        email_input_layout = (TextInputLayout) findViewById(R.id.email_input_layout);

        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_pass);

        password.addTextChangedListener(new SignInActivity.MyTextWatcher(password));
        email.addTextChangedListener(new SignInActivity.MyTextWatcher(email));


    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_email:
                    validateEmail();
                    break;
                case R.id.et_pass:
                    validatePassword();
                    break;
            }
        }
    }

    private boolean validateEmail() {
        String email1 = email.getText().toString().trim();

        if (email1.isEmpty() || !isValidEmail(email1)) {
            email_input_layout.setError(getString(R.string.err_msg_email));
            return false;
        } else {
            email_input_layout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            pass_input_layout.setError(getString(R.string.err_msg_password));
            return false;
        } else {
            pass_input_layout.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void submitForm() {

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        dialog = new Dialog(SignInActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.geometric_dialog);
        dialog.setCancelable(true);
        GeometricProgressView progressView = (GeometricProgressView) dialog.findViewById(R.id.progressView);
        dialog.show();
        Toast.makeText(SignInActivity.this, "Processing !!!", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                //onLoadingDataEnded();
                Intent i = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(i);
                //  overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();

            }
        }, 300);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        //Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        Toasty.info(this, "Please click BACK again to exit", Toast.LENGTH_SHORT, true).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void signI(View view){

        submitForm();

    }

    private void sendRequest() {
        String JSON_URL = Constants.URL + "user/login/";
        RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);

        StringRequest req = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Log.d("REsponse", obj.getString("message"));
                            //Toast.makeText(Login.this, obj.getString("message"), Toast.LENGTH_SHORT).show();

                            if (obj.getString("status").matches("true")) {
                                Toasty.success(SignInActivity.this, obj.getString("message"), Toast.LENGTH_SHORT, true).show();

                                Intent mainIntent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(mainIntent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                finish();

                            } else {
                                Toasty.error(SignInActivity.this, obj.getString("message"), Toast.LENGTH_SHORT, true).show();
                            }


                        } catch (Throwable t) {
                            Log.e("REsponse", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                        //Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(Login.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        if (error instanceof NetworkError) {
                            //Toast.makeText(Login.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                            Toasty.error(SignInActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT, true).show();
                        } else if (error instanceof ServerError) {
                            //Toast.makeText(Login.this, "Server Error. Please try again later.", Toast.LENGTH_SHORT).show();
                            Toasty.error(SignInActivity.this, "Server Error. Please try again later.", Toast.LENGTH_SHORT, true).show();
                        } else if (error instanceof AuthFailureError) {
                            //Toast.makeText(Login.this, "Please enter correct username and password.", Toast.LENGTH_SHORT).show();
                            Toasty.error(SignInActivity.this, "Please enter correct username and password.", Toast.LENGTH_SHORT, true).show();
                        } else if (error instanceof ParseError) {
                            //Toast.makeText(Login.this, "Json Parse Error. Please try again later.", Toast.LENGTH_SHORT).show();
                            Toasty.error(SignInActivity.this, "Json Parse Error. Please try again later.", Toast.LENGTH_SHORT, true).show();
                        } else if (error instanceof NoConnectionError) {
                            //Toast.makeText(Login.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                            Toasty.error(SignInActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT, true).show();
                        } else if (error instanceof TimeoutError) {
                            //Toast.makeText(Login.this, "Timeout Error. Please try again later.", Toast.LENGTH_SHORT).show();
                            Toasty.error(SignInActivity.this, "Timeout Error. Please try again later.", Toast.LENGTH_SHORT, true).show();
                        }
                    }
                }) {

            @Override
            public byte[] getBody() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("email", str_email);
                params.put("password", str_password);
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String credentials = "edudas:1361";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", auth);
                return headers;
            }
        };

        queue.add(req);
    }

}
