package com.example.academiplus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SurveillantActivity extends AppCompatActivity {

    EditText etudiantUidInput, matiereInput, dateInput, heureInput,etudiantNomInput,etudiantPrenomInput;
    Button ajouterAbsenceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surveillant_main);

        etudiantUidInput = findViewById(R.id.etudiantUidInput);
        etudiantNomInput = findViewById(R.id.etudiantNomInput);
        etudiantPrenomInput = findViewById(R.id.etudiantPrenomInput);
        matiereInput = findViewById(R.id.matiereInput);
        dateInput = findViewById(R.id.dateInput);
        heureInput = findViewById(R.id.heureInput);
        ajouterAbsenceButton = findViewById(R.id.ajouterAbsenceButton);

        ajouterAbsenceButton.setOnClickListener(v -> enregistrerAbsence());
    }

    private void enregistrerAbsence() {
        String uid = etudiantUidInput.getText().toString().trim();
        String nom = etudiantNomInput.getText().toString().trim();
        String prenom = etudiantPrenomInput.getText().toString().trim();
        String matiere = matiereInput.getText().toString().trim();
        String date = dateInput.getText().toString().trim();
        String heure = heureInput.getText().toString().trim();

        if (uid.isEmpty() || matiere.isEmpty() || date.isEmpty() || heure.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference dbRef = FirebaseDatabase.getInstance()
                .getReference("absences")
                .child(nom)
                .child(prenom)
                .child(uid)
                .child(date);

        HashMap<String, String> data = new HashMap<>();
        data.put("matiere", matiere);
        data.put("heure", heure);

        dbRef.setValue(data).addOnSuccessListener(unused ->
                Toast.makeText(this, "Absence enregistrée", Toast.LENGTH_SHORT).show()
        ).addOnFailureListener(e ->
                Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show()
        );
    }
}
