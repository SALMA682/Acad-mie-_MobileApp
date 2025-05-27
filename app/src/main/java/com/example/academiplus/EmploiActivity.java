package com.example.academiplus;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EmploiActivity extends AppCompatActivity {
    TableLayout tableLayout;
    String[] days = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    String[] times = {"08:00-10:00", "10:00-12:00", "PAUSE", "14:00-16:00", "16:00-18:00"};
    boolean isAdmin = true; // simulate user role, replace with actual logic
    HashMap<String, Spinner> emploiSpinners = new HashMap<>();
    DatabaseReference emploiRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emploi);
        emploiRef = FirebaseDatabase.getInstance().getReference("emplois");

        tableLayout = findViewById(R.id.timetable_table);
        buildTable();
        findViewById(R.id.save_emploi_btn).setOnClickListener(v -> saveEmploiToFirebase());

    }

    private void buildTable() {
        // En-tête
        TableRow headerRow = new TableRow(this);
        headerRow.addView(createCell("Jour / Heure", true));

        for (String time : times) {
            headerRow.addView(createCell(time, true));
        }

        tableLayout.addView(headerRow);

        // Jours
        for (String day : days) {
            TableRow row = new TableRow(this);
            row.addView(createCell(day, true));

            for (String time : times) {
                if (time.equals("PAUSE")) {
                    row.addView(createCell("Pause", false));
                } else {
                    if (isAdmin) {
                        row.addView(createDropdownCell(day, time));
                    } else {
                        row.addView(createCell("Libre", false));
                    }
                }
            }

            tableLayout.addView(row);
        }
    }

    private TextView createCell(String text, boolean isHeader) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(16, 16, 16, 16);
        tv.setBackgroundColor(isHeader ? Color.parseColor("#2c3e50") : Color.parseColor("#ecf0f1"));
        tv.setTextColor(isHeader ? Color.WHITE : Color.BLACK);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    private Spinner createDropdownCell(String day, String time) {
        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Maths", "Physique", "SVT", "Français", "Anglais", "Histoire"}
        );
        spinner.setAdapter(adapter);
        String key = day + "_" + time;
        emploiSpinners.put(key, spinner);
        return spinner;
    }
    private void saveEmploiToFirebase() {
        for (String key : emploiSpinners.keySet()) {
            Spinner spinner = emploiSpinners.get(key);
            String matiere = spinner.getSelectedItem().toString();

            if (!matiere.isEmpty()) {
                String[] parts = key.split("_");
                String day = parts[0];
                String time = parts[1];

                Emploi emploi = new Emploi(day, time, matiere);
                emploiRef.child(day).child(time).setValue(emploi);
            }
        }
    }

}
