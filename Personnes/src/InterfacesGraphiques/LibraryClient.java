package InterfacesGraphiques;

import ClassesMétier.Client;
import Contrôleur.LibraryClientControleur;
import Contrôleur.LoginContrôleur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryClient extends JFrame
{

    private Client client;

    private LibraryClientControleur libraryClientControleur;
    private JTextField usernameField;
    private JButton emprunterButton;
    private JButton rendreButton;
    private JButton rechercherButton;
    private JButton quitterButton;

    public JTextField getUsernameField()
    {
        return usernameField;
    }
    public void setUsername(String username)
    {
        usernameField.setText(username);
    }
    public LibraryClient()
    {

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Gestionnaire de bibliothèque");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panneau principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Première ligne
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        usernameField = new JTextField(15);
        usernameField.setEditable(false);
        topPanel.add(usernameField);

        emprunterButton = new JButton("EMPRUNTER");
        rendreButton = new JButton("RENDRE");
        rechercherButton = new JButton("RECHERCHER");
        quitterButton = new JButton("QUITTER XXXXX");
        topPanel.add(emprunterButton);
        topPanel.add(rendreButton);
        topPanel.add(rechercherButton);
        topPanel.add(quitterButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Deuxième ligne avec une JTable
        String[] columnNames = {"Auteur", "Livre", "Editeur", "Quantité"};
        Object[][] data = {
                {"Auteur1", "Livre1", "Editeur1", 5},
                {"Auteur2", "Livre2", "Editeur2", 3},
                {"Auteur3", "Livre3", "Editeur3", 7},
                {"Auteur4", "Livre4", "Editeur4", 2},
                {"Auteur5", "Livre5", "Editeur5", 9},
                {"Auteur6", "Livre6", "Editeur6", 4},
                {"Auteur7", "Livre7", "Editeur7", 6},
                {"Auteur8", "Livre8", "Editeur8", 1}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        /*emprunterButton.addActionListener(e -> showDialog("Vous avez emprunté un livre."));
        emprunterButton.addActionListener(e -> showDialog("Vous ne pouvez pas emprunter ce livre."));
*/
        rendreButton.addActionListener(e -> showDialog("Vous avez rendu un livre."));
        rendreButton.addActionListener(e -> showDialog("Vous n'avez pas de livre à rendre."));
        rendreButton.addActionListener(e -> {
            // Récupérer l'index de la ligne sélectionnée
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                showDialog("Veuillez sélectionner un élément à rendre.");
                return; // Sortir de la méthode si aucune ligne n'est sélectionnée
            }

            // Récupérer la quantité actuelle dans la JTable
            int currentQuantity = (int) table.getValueAt(selectedRow, 3);

            // Demander à l'utilisateur la quantité à rendre
            String input = JOptionPane.showInputDialog(null, "Entrez la quantité à rendre:", "Rendre",
                    JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return; // L'utilisateur a annulé, sortir de la méthode
            }

            try {
                int quantityToReturn = Integer.parseInt(input);
                if (quantityToReturn <= 0) {
                    showDialog("Veuillez saisir une quantité valide à rendre.");
                    return; // Sortir de la méthode si la quantité à rendre est invalide
                }

                if (quantityToReturn > currentQuantity) {
                    showDialog("La quantité à rendre ne peut pas dépasser la quantité actuelle.");
                    return; // Sortir de la méthode si la quantité à rendre est supérieure à la quantité actuelle
                }

                // Mettre à jour la quantité dans la JTable
                table.setValueAt(currentQuantity - quantityToReturn, selectedRow, 3);
                showDialog("Vous avez rendu " + quantityToReturn + " exemplaires du livre.");
            } catch (NumberFormatException ex) {
                showDialog("Veuillez saisir un nombre valide pour la quantité à rendre.");
            }
        });

        rechercherButton.addActionListener(e -> showDialog("Ce livre est disponible."));
        rechercherButton.addActionListener(e -> showDialog("Nous n'avons pas ce livre"));

        //quitterButton.addActionListener(e -> libraryClientContrôleur.exitApplication());

        getContentPane().add(mainPanel);
    }

    private void showDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public JButton getEmprunterButton()
    {
        return emprunterButton;
    }

    public JButton getRendreButton()
    {
        return rendreButton;
    }

    public JButton getRechercherButton()
    {
        return rechercherButton;
    }

    public JButton getQuitterButton()
    {
        return quitterButton;
    }



    public class Main
    {
        public static void main(String[] args)
        {
            SwingUtilities.invokeLater(LibraryClient::new);
            Client client = new Client();
            LoginWindow loginWindow = new LoginWindow();
            LibraryClient libraryClient = new LibraryClient();
            LibraryClientControleur libraryClientControleur = new LibraryClientControleur(libraryClient);

        }
    }

}