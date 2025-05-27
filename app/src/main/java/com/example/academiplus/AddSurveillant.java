package com.example.academiplus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSurveillant extends AppCompatActivity {

    private EditText nomEditText, prenomEditText, emailEditText, passwordEditText, telephoneEditText;
    private Button btnAddSurveillant;

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_surveillant);

        nomEditText = findViewById(R.id.editTextNomSurveillant);
        prenomEditText = findViewById(R.id.editTextPrenomSurveillant);
        emailEditText = findViewById(R.id.editTextEmailSurveillant);
        passwordEditText = findViewById(R.id.editTextPasswordSurveillant);
        telephoneEditText = findViewById(R.id.editTextTelephoneSurveillant);
        btnAddSurveillant = findViewById(R.id.btnAddSurveillant);

        dbRef = FirebaseDatabase.getInstance().getReference("Users");

        btnAddSurveillant.setOnClickListener(v -> {
            String nom = nomEditText.getText().toString().trim();
            String prenom = prenomEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String motDePasse = passwordEditText.getText().toString().trim();
            String telephone = telephoneEditText.getText().toString().trim();

            if (email.isEmpty() || nom.isEmpty() || prenom.isEmpty() || motDePasse.isEmpty() || telephone.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            String id = dbRef.push().getKey();
            User surveillant = new User(id, email, "Surveillant", nom, prenom, motDePasse, telephone);

            dbRef.child(id).setValue(surveillant)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Surveillant ajouté avec succès", Toast.LENGTH_SHORT).show();
                        nomEditText.setText("");
                        prenomEditText.setText("");
                        emailEditText.setText("");
                        passwordEditText.setText("");
                        telephoneEditText.setText("");
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}
