package com.example.academiplus;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    private Button btnAddSurveillant, btnAddProfesseur, btnAddEtudiant, btnGererEmploi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main); // Le fichier XML que tu as créé

        btnAddSurveillant = findViewById(R.id.btnAddSurveillant);
        btnAddProfesseur = findViewById(R.id.btnAddProfesseur);
        btnAddEtudiant = findViewById(R.id.btnAddEtudiant);
        btnGererEmploi = findViewById(R.id.btnGererEmploi);

        btnAddSurveillant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddSurveillant.class);
                startActivity(intent);
            }
        });

        btnAddProfesseur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddProfesseur.class);
                startActivity(intent);
            }
        });

        btnAddEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddEtu.class);
                startActivity(intent);
            }
        });

        btnGererEmploi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, EmploiActivity.class);
                startActivity(intent);
            }
        });
    }
}
