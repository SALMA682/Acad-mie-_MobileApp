package com.example.academiplus;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EtudiantEmploiActivity extends AppCompatActivity {

    TextView resultatEmploi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etudiant_emploi);

        resultatEmploi = findViewById(R.id.resultatEmploi);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference("emplois").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        StringBuilder builder = new StringBuilder();

                        for (DataSnapshot jourSnap : snapshot.getChildren()) {
                            String jour = jourSnap.getKey();

                            builder.append("🗓 ").append(jour).append(" :\n");

                            for (DataSnapshot horaireSnap : jourSnap.getChildren()) {
                                String heure = horaireSnap.getKey();
                                String matiere = horaireSnap.getValue(String.class);

                                builder.append("   ")
                                        .append(heure)
                                        .append(" - ")
                                        .append(matiere)
                                        .append("\n");
                            }

                            builder.append("\n");
                        }

                        if (builder.length() == 0) {
                            builder.append("Aucun emploi du temps trouvé.");
                        }

                        resultatEmploi.setText(builder.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        resultatEmploi.setText("Erreur de chargement.");
                    }
                });
    }
}
