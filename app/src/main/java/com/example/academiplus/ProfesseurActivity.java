package com.example.academiplus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ProfesseurActivity extends AppCompatActivity {

    EditText etudiantUidInput, matiereInput, noteInput, coefficientInput;
    Button ajouterNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professeur_main);

        etudiantUidInput = findViewById(R.id.etudiantUidInput);
        matiereInput = findViewById(R.id.matiereInput);
        noteInput = findViewById(R.id.noteInput);
        coefficientInput = findViewById(R.id.coefficientInput);
        ajouterNoteButton = findViewById(R.id.ajouterNoteButton);

        ajouterNoteButton.setOnClickListener(v -> enregistrerNote());
    }

    private void enregistrerNote() {
        String uid = etudiantUidInput.getText().toString().trim();
        String matiere = matiereInput.getText().toString().trim();
        String note = noteInput.getText().toString().trim();
        String coefficient = coefficientInput.getText().toString().trim();

        if (uid.isEmpty() || matiere.isEmpty() || note.isEmpty() || coefficient.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference dbRef = FirebaseDatabase.getInstance()
                .getReference("notes")
                .child(uid)
                .child(matiere);

        HashMap<String, Object> data = new HashMap<>();
        data.put("note", Float.parseFloat(note));
        data.put("coefficient", Integer.parseInt(coefficient));

        dbRef.setValue(data).addOnSuccessListener(unused ->
                Toast.makeText(this, "Note enregistrée", Toast.LENGTH_SHORT).show()
        ).addOnFailureListener(e ->
                Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show()
        );
    }
}
