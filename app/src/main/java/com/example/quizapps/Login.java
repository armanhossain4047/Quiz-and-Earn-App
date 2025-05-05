package com.example.quizapps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizapps.Home;
import com.example.quizapps.Signup;


/** @noinspection ALL*/

public class Login extends AppCompatActivity {
    Button SingIn;
    TextView signUPText;
    EditText emailorusername,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FindId();
        SingIn.setOnClickListener(this::onClick);
        if (signUPText != null) {
            signUPText.setOnClickListener(this::onClick);
        }

        // ***** Password Show and Hide Code ***** //
        EditText passwordEditText = findViewById(R.id.password);
        ImageButton eyeIcon = findViewById(R.id.eye_icon_id);

        if (passwordEditText != null && eyeIcon != null) {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());

            // Set click listener on eye icon
            eyeIcon.setOnClickListener(new View.OnClickListener() {
                boolean isPasswordVisible = false;

                @Override
                public void onClick(View v) {
                    if (isPasswordVisible) {
                        // Hide the password
                        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        eyeIcon.setImageResource(R.drawable.eye_vector); // Set to the closed eye icon
                    } else {
                        // Show the password
                        passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        eyeIcon.setImageResource(R.drawable.eye_vector); // Set to the open eye icon (you need this drawable)
                    }
                    // Toggle the flag
                    isPasswordVisible = !isPasswordVisible;

                    // Move the cursor to the end of the text
                    passwordEditText.setSelection(passwordEditText.getText().length());
                }
            });
        }
    }

    private void FindId() {
      signUPText  = findViewById(R.id.sign_in_text);
      SingIn = findViewById(R.id.sing_in_btn_id);
      emailorusername = findViewById(R.id.username_or_email);
      password = findViewById(R.id.password);
    }

    private void onClick(View v) {
        if(v.equals(SingIn)) {

            if(emailorusername.getText().toString().equals(com.example.androidapps.LoginArray.Username)){
                if(com.example.androidapps.LoginArray.Password.equals(password.getText().toString())){
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }

            }else if(emailorusername.getText().toString().isEmpty() && password.getText().toString().isEmpty()){
                Toast.makeText(this, "Invalid Username & Password", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Wrong Username",Toast.LENGTH_SHORT).show();
            }

        }else if(v.equals(signUPText)){
            signUPText.setTextColor(getResources().getColor(R.color.purple));
            new Handler().postDelayed(() -> signUPText.setTextColor(getResources().getColor(R.color.link)), 100);
            Intent intent = new Intent(Login.this, Signup.class);
            startActivity(intent);
        }
    }
}