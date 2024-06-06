package Vue.InterfacesGraphiques;

import Contrôleur.Contrôleur;
import Contrôleur.ActionsContrôleur;
import Modèle.ClassesMétier.Bibliothecaire;
import Modèle.ClassesMétier.Client;
import Vue.VueLogin;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements VueLogin
{
    private LoginWindow loginWindow;

    private LibraryClient libraryClient;

    private LibraryManager libraryManager;

    private Contrôleur contrôleur;
    public Contrôleur getContrôleur()
    {
        return contrôleur;
    }


    private JTextField usernameField;

    public JTextField getUsernameField()
    {
        return usernameField;
    }
    private JPasswordField passwordField;
    public JPasswordField getPasswordField()
    {
        return passwordField;
    }
    private JButton loginAdminButton;
    public JButton getLoginAdminButton()
    {
        return loginAdminButton;
    }
    private JButton loginUserButton;
    public JButton getLoginUserButton()
    {
        return loginUserButton;
    }
    private JPanel mainPanel;



    public LoginWindow()
    {
        super("Login...");
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        libraryClient = LibraryClient.getLibraryClient();
        libraryManager = new LibraryManager();


        // Initialisation du panneau principal
        mainPanel = new JPanel();

        // Initialisation des autres composants
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginAdminButton = new JButton("Login Admin");
        loginUserButton = new JButton("Login User");

        // Définir les actions de commande pour les boutons
        loginAdminButton.setActionCommand(ActionsContrôleur.LOGINADMIN);
        loginUserButton.setActionCommand(ActionsContrôleur.LOGINUSER);

        // Ajout des composants au panneau principal
        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(loginAdminButton);
        mainPanel.add(loginUserButton);

        // Définition du panneau principal comme contenu de la fenêtre
        setContentPane(mainPanel);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginAdminListener(ActionListener listener)
    {
        loginAdminButton.addActionListener(listener);
    }

    public void addLoginUserListener(ActionListener listener)
    {

        loginUserButton.addActionListener(listener);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void setContrôleur(Contrôleur Contrôleur)
    {
        this.loginWindow = this;
        loginAdminButton.setActionCommand(ActionsContrôleur.LOGINADMIN);
        loginUserButton.setActionCommand(ActionsContrôleur.LOGINUSER);
        loginWindow.addLoginAdminListener(Contrôleur);
        loginWindow.addLoginUserListener(Contrôleur);


    }

    @Override
    public void run()
    {
        this.loginWindow = this;
        this.setVisible(true);

    }

    @Override
    public Bibliothecaire LoginAdmin()
    {
        // TODO Auto-generated method stub
        System.out.println("Connexion admin");
        String username = loginWindow.getUsername();
        String password = loginWindow.getPassword();
        Bibliothecaire bibliothecaire = Bibliothecaire.seConnecter(username, password);
        if (bibliothecaire != null)
        {
            loginWindow.showMessage("Connexion admin réussie");

            if (this.libraryManager == null)
            {
                System.out.println("Nouveau manager");
                this.libraryManager = new LibraryManager();
            }
            this.libraryManager = LibraryManager.getLibraryManager();
            libraryManager.setAdminName(username);
            libraryManager.setVisible(true);
            libraryManager.setContrôleurManager(contrôleur);
            return bibliothecaire;
        }
        else
        {
            loginWindow.showMessage("Nom d'utilisateur ou mot de passe utilisateur incorrect.");
        }
        return null;

    }

    @Override
    public Client LoginClient()
    {
        // TODO Auto-generated method stub
        System.out.println("Connexion client");
        String username = loginWindow.getUsername();
        String password = loginWindow.getPassword();
        Client client = Client.seConnecter(username, password);

        if (client != null)
        {
            showMessage("Connexion utilisateur réussie!");
            if (this.libraryClient == null)
            {
                System.out.println("Nouveau client");
                this.libraryClient = new LibraryClient();
            }
            this.libraryClient = libraryClient.getLibraryClient();
            libraryClient.setUsername(username);
            libraryClient.setVisible(true);
            libraryClient.setContrôleurClient(contrôleur);
            return client;
        }
        else
        {
            showMessage("Nom d'utilisateur ou mot de passe utilisateur incorrect.");
        }
        return null;


    }
}
