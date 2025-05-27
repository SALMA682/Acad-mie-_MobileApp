package com.example.academiplus;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEtu extends AppCompatActivity {

    private EditText editNom, editEmail;
    private Spinner spinnerNiveau;
    private Button btnAjouter;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_etudiant);

        editNom = findViewById(R.id.editNom);
        editEmail = findViewById(R.id.editEmail);
        spinnerNiveau = findViewById(R.id.spinner_level);
        btnAjouter = findViewById(R.id.btnAjouter);

        databaseRef = FirebaseDatabase.getInstance().getReference("etudiants");

        // Remplissage du Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.class_list, // Ajoute ce tableau dans strings.xml
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNiveau.setAdapter(adapter);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterEtudiant();
            }
        });
    }

    private void ajouterEtudiant() {
        String nom = editNom.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String niveau = spinnerNiveau.getSelectedItem().toString();

        if (nom.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = databaseRef.push().getKey(); // ID unique
        Etudiant etudiant = new Etudiant(nom, email, niveau);

        databaseRef.child(id).setValue(etudiant)
                .addOnSuccessListener(aVoid ->
                        Toast.makeText(this, "Étudiant ajouté", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
