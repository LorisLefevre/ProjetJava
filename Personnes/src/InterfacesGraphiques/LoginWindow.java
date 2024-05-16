package InterfacesGraphiques;

import ClassesMétier.Client;
import Contrôleur.LoginContrôleur;
import Contrôleur.LoginContrôleur;

import javax.swing.*;
import java.awt.event.*;

public class LoginWindow extends JFrame
{
    private LoginContrôleur loginContrôleur;

    private Client client;
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
        //LoginWindow loginWindow = new LoginWindow();

        setSize(350, 150);

        // Initialisation du panneau principal
        mainPanel = new JPanel();

        // Initialisation des autres composants
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginAdminButton = new JButton("Login Admin");
        loginUserButton = new JButton("Login User");

        // Ajout des composants au panneau principal
        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(loginAdminButton);
        mainPanel.add(loginUserButton);

        // Définition du panneau principal comme contenu de la fenêtre
        setContentPane(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(LoginWindow::new);
        LoginWindow loginWindow = new LoginWindow();
        LibraryManager libraryManager = new LibraryManager();
        LibraryClient libraryClient = new LibraryClient();
        LoginContrôleur loginContrôleur = new LoginContrôleur(loginWindow, libraryManager, libraryClient);
    }
}
