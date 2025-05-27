package com.example.academiplus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EtudiantActivity extends AppCompatActivity {

    Button emploiButton, notesButton, absencesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etudiant_main);

        emploiButton = findViewById(R.id.emploiButton);
        notesButton = findViewById(R.id.notesButton);
        absencesButton = findViewById(R.id.absencesButton);

        emploiButton.setOnClickListener(v ->
                startActivity(new Intent(this, EtudiantEmploiActivity.class))
        );

        notesButton.setOnClickListener(v ->
                startActivity(new Intent(this, EtudiantNotesActivity.class))
        );

        absencesButton.setOnClickListener(v ->
                startActivity(new Intent(this, EtudiantAbsencesActivity.class))
        );
    }
}
