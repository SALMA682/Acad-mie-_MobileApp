package com.example.academiplus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProfesseur extends AppCompatActivity {

    private EditText emailEditText, nomEditText, prenomEditText, passwordEditText, telephoneEditText;
    private Button btnAddProfesseur;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_professeur); // Assure-toi que ton layout contient tous ces champs

        emailEditText = findViewById(R.id.editTextEmailProfesseur);
        nomEditText = findViewById(R.id.editTextNomProfesseur);
        prenomEditText = findViewById(R.id.editTextPrenomProfesseur);
        passwordEditText = findViewById(R.id.editTextPasswordProfesseur);
        telephoneEditText = findViewById(R.id.editTextTelephoneProfesseur);
        btnAddProfesseur = findViewById(R.id.btnAddProfesseur);

        dbRef = FirebaseDatabase.getInstance().getReference("Users");

        btnAddProfesseur.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String nom = nomEditText.getText().toString().trim();
            String prenom = prenomEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String telephone = telephoneEditText.getText().toString().trim();

            if (email.isEmpty() || nom.isEmpty() || prenom.isEmpty() || password.isEmpty() || telephone.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            String id = dbRef.push().getKey();
            User professeur = new User(id, email, "Professeur", nom, prenom, password, telephone);

            dbRef.child(id).setValue(professeur)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Professeur ajouté avec succès", Toast.LENGTH_SHORT).show();
                        emailEditText.setText("");
                        nomEditText.setText("");
                        prenomEditText.setText("");
                        passwordEditText.setText("");
                        telephoneEditText.setText("");
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show()
                    );
        });
    }
}
