package com.lorsku.pennapps2017hack;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etFname = (EditText) findViewById(R.id.etFname);
        final EditText etLname = (EditText) findViewById(R.id.etLname);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etGender = (EditText) findViewById(R.id.etGender);
        final EditText etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        final EditText etSchool = (EditText) findViewById(R.id.etSchool);
        final EditText etTshirtSize = (EditText) findViewById(R.id.etTshirtSize);
        final EditText etEssay1 = (EditText) findViewById(R.id.etEssay1);




        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(getApplicationContext(), MainActivity.class);
                getApplicationContext().startActivity(registerIntent);


                final String firstname = etFname.getText().toString();
                final String lastname = etLname.getText().toString();
                final String email = etEmail.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String password = etPassword.getText().toString();
                final String gender = etGender.getText().toString();
                final int phonenumber = Integer.parseInt(etPhoneNumber.getText().toString());
                final String school = etSchool.getText().toString();
                final String tshirtsize = etTshirtSize.getText().toString();
                final String essay1 = etEssay1.getText().toString();

               /* Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(firstname, lastname, email, age, password, gender, phonenumber, school, tshirtsize, essay1, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);*/
            }
        });
    }
}