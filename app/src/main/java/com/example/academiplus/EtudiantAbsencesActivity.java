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

public class EtudiantAbsencesActivity extends AppCompatActivity {

    TextView resultatAbsences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etudiant_absences);

        resultatAbsences = findViewById(R.id.resultatAbsences);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference("absences").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        StringBuilder builder = new StringBuilder();

                        for (DataSnapshot dateSnap : snapshot.getChildren()) {
                            String date = dateSnap.getKey();
                            String raison = dateSnap.getValue(String.class);

                            builder.append("- ")
                                    .append(date)
                                    .append(" : ")
                                    .append(raison)
                                    .append("\n");
                        }

                        if (builder.length() == 0) {
                            builder.append("Aucune absence enregistrée.");
                        }

                        resultatAbsences.setText(builder.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        resultatAbsences.setText("Erreur de chargement.");
                    }
                });
    }
}
