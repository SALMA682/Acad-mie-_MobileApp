package com.example.academiplus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button registerButton;
    private FirebaseAuth auth;
    Spinner spinnerNiveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        spinnerNiveau = findViewById(R.id.spinner_classes);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.class_list,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNiveau.setAdapter(adapter);


        auth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String selectedNiveau = spinnerNiveau.getSelectedItem().toString();

            if (!email.isEmpty() && !password.isEmpty()) {
                Toast.makeText(this, "Role choisi : " + selectedNiveau, Toast.LENGTH_SHORT).show();
                registerUser(email, password);
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser(String email, String password) {
        String selectedRole = spinnerNiveau.getSelectedItem().toString();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(this, "Inscription réussie : " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        switch (selectedRole) {
                            case "Etudiant":
                                startActivity(new Intent(Register.this, EtudiantActivity.class));
                                break;
                            case "Professeur":
                                startActivity(new Intent(Register.this, ProfesseurActivity.class));
                                break;
                            case "Surveillant":
                                startActivity(new Intent(Register.this, SurveillantActivity.class));
                                break;
                            case "Administrateur":
                                startActivity(new Intent(Register.this, AdminActivity.class));
                                break;
                            default:
                                Toast.makeText(this, "Rôle non reconnu", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Erreur : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

