package com.example.deadtarget;

import static com.example.deadtarget.R.id.AlreadyRegistered;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    ImageButton getStartedButton;
    String email = "";
    String password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firestore=FirebaseFirestore.getInstance();
                Map<String,Object> user=new HashMap<>();
                user.put("firstName","Easy");
                user.put("lastName","Tuto");
                user.put("description","Subscribe");

                firestore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_LONG).show();

                    }
                });

        getStartedButton = findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignInScreen.class));
            }
        });
    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser currentUser = auth.getCurrentUser();
//        if(currentUser != null) {
//            startActivity(new Intent(MainActivity.this, HomeActivity.class));
//        }
//
//    }
}