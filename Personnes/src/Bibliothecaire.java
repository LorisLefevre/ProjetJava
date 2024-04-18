import java.util.Scanner;

public class Bibliothecaire extends Personne {
    private String AdminName;
    private String AdminPassword;

    public Bibliothecaire() {
        super();
        AdminName = "Admin";
        AdminPassword = "1234Admin";
    }

    public Bibliothecaire(String Nom, String Prenom, String AdminName, String AdminPassword) {
        super(Nom, Prenom);
        this.AdminName = AdminName;
        this.AdminPassword = AdminPassword;
    }

    // Getters and setters
    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String AdminName) {
        this.AdminName = AdminName;
    }

    public String getAdminPassword() {
        return AdminPassword;
    }

    public void setAdminPassword(String AdminPassword) {
        this.AdminPassword = AdminPassword;
    }

    // Méthode pour afficher les informations du bibliothécaire
    @Override
    public void Afficher() {
        super.Afficher();
        System.out.println("AdminName : " + AdminName);
        System.out.println("AdminPassword : " + AdminPassword);
    }

    // Méthode pour saisir les informations du bibliothécaire
    @Override
    public void Saisir() {
        Scanner scanner = new Scanner(System.in);
        super.Saisir();

        System.out.println("Entrez le nom d'utilisateur Admin: ");
        this.AdminName = scanner.nextLine();

        System.out.println("Entrez le mot de passe Admin: ");
        this.AdminPassword = scanner.nextLine();
    }

    // Surcharge de la méthode toString
    @Override
    public String toString() {
        return super.toString() +
                "\nAdminName : " + AdminName +
                "\nAdminPassword : " + AdminPassword;
    }

    // Méthode equals pour comparer deux bibliothécaires
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bibliothecaire)) return false;
        if (!super.equals(o)) return false;
        Bibliothecaire that = (Bibliothecaire) o;
        return AdminName.equals(that.AdminName) && AdminPassword.equals(that.AdminPassword);
    }

    public static void main(String[] args) {
        String Nom = "";
        String Prenom = "";
        String AdminName = "";
        String AdminPassword = "";

        Bibliothecaire bibliothecaire = new Bibliothecaire(Nom, Prenom, AdminName, AdminPassword);

        bibliothecaire.Saisir();

        bibliothecaire.Afficher();

        Bibliothecaire bibliothecaire1 = new Bibliothecaire();

        bibliothecaire1.Afficher();

        if (bibliothecaire == bibliothecaire1) {
            System.out.println("Ce sont les mêmes bibliothecaires");
        } else {
            System.out.println("Ce sont des bibliothécaires différents");
        }

        Bibliothecaire bibliothecaire2 = new Bibliothecaire("Toto", "Pierre", "TotoAdmin", "TotoWord");

        bibliothecaire2.Afficher();

        // Nouveaux tests
        System.out.println("Test de la méthode toString :");
        System.out.println(bibliothecaire.toString());
        System.out.println(bibliothecaire1.toString());
        System.out.println(bibliothecaire2.toString());

        System.out.println("Test de la méthode equals :");
        System.out.println("bibliothecaire.equals(bibliothecaire1) : " + bibliothecaire.equals(bibliothecaire1));
        System.out.println("bibliothecaire.equals(bibliothecaire2) : " + bibliothecaire.equals(bibliothecaire2));
    }
}
