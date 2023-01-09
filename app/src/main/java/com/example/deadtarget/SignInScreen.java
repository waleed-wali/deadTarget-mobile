package com.example.deadtarget;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignInScreen extends AppCompatActivity {
    TextView createAccount;
    EditText email;
    EditText password;
    Button btnLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);


        createAccount= (TextView)findViewById(R.id.create);
        email = (EditText) findViewById(R.id.userNameField);
        password = (EditText) findViewById(R.id.passwordField);
        btnLogin = (Button) findViewById(R.id.logIn_Button);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInScreen.this,signUpScreen.class));
            }
        });

        btnLogin.setOnClickListener(view -> {
           login();
        });
    }

    private void login() {
        String _email = email.getText().toString();
        String _password = password.getText().toString();
        System.out.println(_password);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(_email, _password).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInScreen.this, HomeActivity.class));
                    } else {
                        Toast.makeText(this, "Failed " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}