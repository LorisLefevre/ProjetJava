import javax.swing.*;
import java.awt.event.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginAdminButton;
    private JButton saveAdminButton;
    private JButton loginUserButton;
    private JButton saveUserButton;
    private JPanel mainPanel;

    public LoginWindow() {
        super("Login...");
        setSize(350, 150);

        // Initialisation du panneau principal
        mainPanel = new JPanel();

        // Initialisation des autres composants
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginAdminButton = new JButton("Login Admin");
        saveAdminButton = new JButton("Save Admin");
        loginUserButton = new JButton("Login User");
        saveUserButton = new JButton("Save User");

        // Ajout des composants au panneau principal
        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(loginAdminButton);
        mainPanel.add(saveAdminButton);
        mainPanel.add(loginUserButton);
        mainPanel.add(saveUserButton);

        loginAdminButton.addActionListener(e -> showDialog("Bienvenue cher administrateur"));
        loginAdminButton.addActionListener(e -> showDialog("Le nom d'utilisateur et/ou le mot de passe ne sont pas valides"));
        loginUserButton.addActionListener(e -> showDialog("Bienvenue cher client"));
        loginUserButton.addActionListener(e -> showDialog("Le nom d'utilisateur et/ou le mot de passe ne sont pas valides"));


        // Définition du panneau principal comme contenu de la fenêtre
        setContentPane(mainPanel);

        setVisible(true);
    }

    private void showDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginWindow::new);
    }
}
