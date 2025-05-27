package com.example.academiplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

import com.example.academiplus.Login;
import com.example.academiplus.Logout;
import com.example.academiplus.R;
import com.example.academiplus.Register;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xml, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.drawer_layout) {
            Toast.makeText(this, "Accueil sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.connexion) {
            startActivity(new Intent(this, Login.class));
            return true;
        } else if (id == R.id.inscription) {
            startActivity(new Intent(this, Register.class));
            return true;
        } else if (id == R.id.deconnexion) {
            startActivity(new Intent(this, Logout.class));
            return true;
        }
            return super.onOptionsItemSelected(item);

    }
}
