import javax.swing.*;
import java.awt.*;

public class LibraryClient extends JFrame {
    private JLabel labelNomClient, labelTitreLivre, labelQuantite;
    private JTextField textFieldNomClient, textFieldTitreLivre, textFieldQuantite;
    private JButton buttonEmprunter, buttonRendre, buttonRechercher;

    public LibraryClient() {
        // Titre de la fenêtre
        super("Bibliothèque");

        // Initialisation des composants
        labelNomClient = new JLabel("Nom du Client:");
        labelTitreLivre = new JLabel("Titre du livre:");
        labelQuantite = new JLabel("Quantité:");

        textFieldNomClient = new JTextField(20);
        textFieldTitreLivre = new JTextField(20);
        textFieldQuantite = new JTextField(5);

        buttonEmprunter = new JButton("Emprunter");
        buttonRendre = new JButton("Rendre");
        buttonRechercher = new JButton("Rechercher");

        // Création du conteneur principal et des panneaux pour organiser les composants
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayout(3, 1));
        labelsPanel.add(labelNomClient);
        labelsPanel.add(labelTitreLivre);
        labelsPanel.add(labelQuantite);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(3, 1));
        fieldsPanel.add(textFieldNomClient);
        fieldsPanel.add(textFieldTitreLivre);
        fieldsPanel.add(textFieldQuantite);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(buttonEmprunter);
        buttonsPanel.add(buttonRendre);
        buttonsPanel.add(buttonRechercher);

        // Ajout des panneaux au conteneur principal
        mainPanel.add(labelsPanel, BorderLayout.WEST);
        mainPanel.add(fieldsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Ajout du conteneur principal à la fenêtre
        getContentPane().add(mainPanel);

        buttonEmprunter.addActionListener(e-> showDialog("Vous avez emprunté un livre."));
        buttonEmprunter.addActionListener(e-> showDialog("Vous ne pouvez pas emprunter ce livre."));

        buttonRendre.addActionListener(e-> showDialog("Vous avez rendu un livre."));
        buttonRendre.addActionListener(e-> showDialog("Vous n'avez pas de livre à rendre."));

        buttonRechercher.addActionListener(e-> showDialog("Ce livre est disponible."));
        buttonRechercher.addActionListener(e-> showDialog("Nous n'avons pas ce livre"));

        // Configuration de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setVisible(true);
    }

    private void showDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(LibraryClient::new);
    }
}

