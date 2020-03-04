package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputEditText;



public class LoginActivity extends AppCompatActivity {

    RelativeLayout RelativeLayout_sign_in_btn;
    TextInputEditText TextInputEditText_email;
    TextInputEditText TextInputEditText_password;
    String email;
    String password ;
    String converter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RelativeLayout_sign_in_btn =findViewById(R.id.RelativeLayout_sign_in_btn);
        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password = findViewById(R.id.TextInputEditText_password);

       /* converter = String.valueOf(notNull());
          Log.d("Kichan", converter);*/

       TextInputEditText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( s != null){
                    email = s.toString();
                    RelativeLayout_sign_in_btn.setClickable(notNull());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextInputEditText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( s != null){
                    password = s.toString();
                    RelativeLayout_sign_in_btn.setClickable(notNull());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

            RelativeLayout_sign_in_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = TextInputEditText_email.getText().toString();
                    String password = TextInputEditText_password.getText().toString();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    Log.d("Kichan", String.valueOf(converter));

                }
            });
            RelativeLayout_sign_in_btn.setClickable(false);
    }

    public Boolean notNull() {
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password);
    }
}