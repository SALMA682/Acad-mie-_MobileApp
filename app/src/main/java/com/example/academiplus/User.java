package com.example.academiplus;

public class User {
    public String id;
    public String email;
    public String role;
    public String nom;
    public String prenom;
    public String motDePasse;
    public String telephone;

    public User() {
        // Constructeur vide requis pour Firebase
    }

    public User(String id, String email, String role, String nom, String prenom, String motDePasse, String telephone) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
    }
}
