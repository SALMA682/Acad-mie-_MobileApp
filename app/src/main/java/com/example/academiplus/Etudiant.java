package com.example.academiplus;

public class Etudiant {
    public int id=0;
    public String nom;
    public String email;
    public String niveau;

    public Etudiant() {
        // Constructeur vide requis par Firebase
    }

    public Etudiant(String nom, String email, String niveau) {
        id++;
        this.nom = nom;
        this.email = email;
        this.niveau = niveau;
    }
}
