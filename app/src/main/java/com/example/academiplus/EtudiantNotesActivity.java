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

public class EtudiantNotesActivity extends AppCompatActivity {

    TextView resultatNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etudiant_notes);

        resultatNotes = findViewById(R.id.resultatNotes);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference("notes").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        StringBuilder builder = new StringBuilder();

                        for (DataSnapshot matiereSnap : snapshot.getChildren()) {
                            String matiere = matiereSnap.getKey();
                            float note = matiereSnap.child("note").getValue(Float.class);
                            int coef = matiereSnap.child("coefficient").getValue(Integer.class);

                            builder.append(matiere)
                                    .append(" : ")
                                    .append(note)
                                    .append(" / 20 (coef ")
                                    .append(coef)
                                    .append(")\n");
                        }

                        if (builder.length() == 0) {
                            builder.append("Aucune note trouvée.");
                        }

                        resultatNotes.setText(builder.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        resultatNotes.setText("Erreur de chargement.");
                    }
                });
    }
}
