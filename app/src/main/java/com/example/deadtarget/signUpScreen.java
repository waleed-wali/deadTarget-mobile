package com.example.deadtarget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUpScreen extends AppCompatActivity {
    TextView AlreadyRegistered;
    EditText email;
    EditText password;
    Button btnSignup;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        AlreadyRegistered = (TextView)findViewById(R.id.AlreadyRegistered);
        email = (EditText) findViewById(R.id.userEmailField);
        password = (EditText) findViewById(R.id.userPasswordField);
        btnSignup = (Button) findViewById(R.id.SignUpButton);

        AlreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signUpScreen.this,SignInScreen.class));
            }
        });
        btnSignup.setOnClickListener(view -> {
            signupUser();
        });
    }

    private void signupUser() {
        String _email = email.getText().toString();
        String _password = password.getText().toString();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(_email, _password).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(signUpScreen.this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signUpScreen.this, SignInScreen.class));
                    } else {
                        Toast.makeText(signUpScreen.this, "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}