import java.util.Scanner;

public class Client extends Personne {
    private String Username;
    private String Password;

    public Client() {
        super();
        Username = "User";
        Password = "abcd";
    }

    public Client(String Nom, String Prenom, String Username, String Password) {
        super(Nom, Prenom);
        this.Username = Username;
        this.Password = Password;
    }

    // Getters and setters
    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    // Méthode pour afficher les informations du client
    @Override
    public void Afficher() {
        super.Afficher();
        System.out.println("Username : " + Username);
        System.out.println("Password : " + Password);
    }

    // Méthode pour saisir les informations du client
    @Override
    public void Saisir() {
        Scanner scanner = new Scanner(System.in);
        super.Saisir();

        System.out.println("Entrez le nom d'utilisateur : ");
        this.Username = scanner.nextLine();

        System.out.println("Entrez le mot de passe : ");
        this.Password = scanner.nextLine();

    }

    // Surcharge de la méthode toString
    @Override
    public String toString() {
        return super.toString() +
                "\nUsername : " + Username +
                "\nPassword : " + Password;
    }

    // Méthode equals pour comparer deux clients
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Username.equals(client.Username) && Password.equals(client.Password);
    }

    public static void main(String[] args) {
        String Nom = "NULL";
        String Prenom = "NULL";
        String Username = "USER";
        String Password = "ABCD";

        Client client = new Client(Nom, Prenom, Username, Password);

        client.Saisir();

        client.Afficher();

        Client client2 = new Client();

        client2.Afficher();

        if (client == client2) {
            System.out.println("Ce sont les mêmes clients");
        } else {
            System.out.println("Ce sont des clients différents");
        }

        Client client3 = new Client("Toto", Prenom, Username, "1234");

        client3.Saisir();

        client3.Afficher();

        // Nouveaux tests
        System.out.println("Test de la méthode toString :");
        System.out.println(client.toString());
        System.out.println(client2.toString());
        System.out.println(client3.toString());

        System.out.println("Test de la méthode equals :");
        System.out.println("client.equals(client2) : " + client.equals(client2));
        System.out.println("client.equals(client3) : " + client.equals(client3));
    }
}
