package com.example.academiplus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Logout extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button logoutBtn;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.logout);

            logoutBtn = findViewById(R.id.logoutBtn);

            logoutBtn.setOnClickListener(v -> {
                FirebaseAuth.getInstance().signOut(); // déconnexion Firebase
                Toast.makeText(Logout.this, "Déconnecté avec succès", Toast.LENGTH_SHORT).show();

                // Rediriger vers la page principale (login)
                Intent intent = new Intent(Logout.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Fermer l'activité actuelle
            });
        }
}