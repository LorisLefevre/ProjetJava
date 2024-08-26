package Modèle.ClassesMétier;

import java.util.Scanner;

public class Editeur extends Livre {
    private String rue;
    private int numero;
    private String codePostal;
    private String ville;
    private String pays;

    // Constructeurs

    public Editeur() {
        super();
        this.rue = "";
        this.numero = 0;
        this.codePostal = "";
        this.ville = "";
        this.pays = "";
    }

    public Editeur(int ID, String Titre, String Auteur, String Editeur, int Annee, String ISBN, int NombreCopies, String rue, int numero, String codePostal, String ville, String pays) {
        super(ID, Titre, Auteur, Editeur, Annee, ISBN, NombreCopies);
        this.rue = rue;
        this.numero = numero;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    // Méthodes getters et setters pour l'adresse de l'éditeur

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    // Méthode pour afficher les informations de l'éditeur

    @Override
    public void Afficher() {
        //super.Afficher();
        System.out.println("Adresse de l'éditeur:");
        System.out.println("Rue: " + rue);
        System.out.println("Numéro: " + numero);
        System.out.println("Code postal: " + codePostal);
        System.out.println("Ville: " + ville);
        System.out.println("Pays: " + pays);
    }

    // Méthode pour saisir les informations de l'éditeur

    @Override
    public void Saisir() {
        //super.Saisir( );
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez la rue de l'éditeur: ");
        this.rue = scanner.nextLine();
        System.out.println("Entrez le numéro de l'éditeur: ");
        this.numero = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character
        System.out.println("Entrez le code postal de l'éditeur: ");
        this.codePostal = scanner.nextLine();
        System.out.println("Entrez la ville de l'éditeur: ");
        this.ville = scanner.nextLine();
        System.out.println("Entrez le pays de l'éditeur: ");
        this.pays = scanner.nextLine();
    }

    // Surcharge de la méthode toString

    @Override
    public String toString() {
        return super.toString() +
                "\nAdresse de l'éditeur:" +
                "\nRue: " + rue +
                "\nNuméro: " + numero +
                "\nCode postal: " + codePostal +
                "\nVille: " + ville +
                "\nPays: " + pays;
    }

    // Méthode equals pour comparer deux éditeurs

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Editeur)) return false;
        if (!super.equals(o)) return false;
        Editeur editeur = (Editeur) o;
        return numero == editeur.numero &&
                rue.equals(editeur.rue) &&
                codePostal.equals(editeur.codePostal) &&
                ville.equals(editeur.ville) &&
                pays.equals(editeur.pays);
    }

    // Méthode main pour tester la classe

    public static void main(String[] args) {
        // Test des constructeurs
        Editeur editeur1 = new Editeur();
        Editeur editeur2 = new Editeur(1, "Titre", "Modèle.CoucheAccèsDonnées.ClassesMétier.Auteur", "Modèle.CoucheAccèsDonnées.ClassesMétier.Editeur", 2022, "ISBN", 5,
                "Rue de l'Éditeur", 123, "12345", "Ville de l'Éditeur", "Pays de l'Éditeur");

        // Test de la méthode Afficher
        editeur2.Afficher();

        // Test de la méthode Saisir
        Editeur editeur3 = new Editeur();
        editeur3.Saisir();
        editeur3.Afficher();

        // Test de la méthode toString
        System.out.println(editeur2.toString());

        // Test de la méthode equals
        System.out.println(editeur2.equals(editeur3));

        Editeur editeur4 = new Editeur();
        editeur4.Afficher();

        editeur3 = editeur4;

        System.out.println(editeur3.equals(editeur4));
    }
}
