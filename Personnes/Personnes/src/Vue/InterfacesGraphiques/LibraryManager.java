package Vue.InterfacesGraphiques;

import Contrôleur.ActionsContrôleur;
import Contrôleur.Contrôleur;
import Modèle.ClassesMétier.Livre;
import Modèle.CoucheAccèsDonnées.*;
import Vue.VueLibraryManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.JColorChooser.showDialog;

public class LibraryManager extends JFrame implements VueLibraryManager
{
    private JTable table;
    private DefaultTableModel model;

    private JTextField adminNameField;

    public JTextField getAdminNameField() {
        return adminNameField;
    }

    public void setAdminName(String adminName) {
        adminNameField.setText(adminName);
    }

    private JButton addButton, deleteButton, viewButton, editButton, clearButton, exitButton;

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    private static LibraryManager instance;

    public static LibraryManager getLibraryManager() {
        if (instance == null) {
            instance = new LibraryManager();
        }
        return instance;
    }

    private CoucheAccèsDonnées coucheAccèsDonnées;

    public LibraryManager() {
        coucheAccèsDonnées = new CoucheAccèsDonnéesDAO();

        String filePath = "C:\\Users\\Loris\\Personnes\\Books.txt";
        String filePathStream = "livres.dat";
        setTitle("Gestion de la bibliothèque de Sclessin");
        setSize(700, 270);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Création du modèle de la table avec les colonnes
        model = new DefaultTableModel();
        String[] labels = {"ID du livre", "Titre du livre", "Auteur", "Editeur", "Année de publication", "Numéro ISBN", "Nombre de copies"};

        // Ajout des colonnes au modèle
        for (String label : labels) {
            model.addColumn(label);
        }

        // Nombre de lignes souhaitées
        int numberOfRows = 15; // Vous pouvez modifier ce nombre selon vos besoins

        // Ajout de plusieurs lignes avec des JTextField vides
        for (int row = 0; row < numberOfRows; row++) {
            Object[] textFields = new Object[labels.length];
            for (int i = 0; i < labels.length; i++) {
                textFields[i] = "";
            }
            model.addRow(textFields);
        }

        // Création de la table avec le modèle
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                return new DefaultCellEditor(new JTextField());
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new TableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        return new JTextField((String) value);
                    }
                };
            }
        };
        table.setRowHeight(25); // Hauteur des lignes

        // Ajout de la table à un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 100)); // Ajustement de la taille du JScrollPane

        // Ajout des boutons
        adminNameField = new JTextField(20);
        adminNameField.setEditable(false);
        addButton = new JButton("Ajouter");
        deleteButton = new JButton("Supprimer");
        viewButton = new JButton("Regarder");
        editButton = new JButton("Modifier");
        exitButton = new JButton("Quitter");

        // Création d'un panneau pour la table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Création d'un panneau pour les boutons avec un espace vertical en haut
        JPanel buttonPanel = new JPanel(new GridLayout(1, 7, 10, 10)); // 6 boutons avec un espacement de 10 pixels
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Ajout d'un espace vertical en bas
        buttonPanel.add(adminNameField);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(exitButton);

        addButton.setActionCommand(ActionsContrôleur.AJOUT);
        deleteButton.setActionCommand(ActionsContrôleur.SUPPRIMER);
        viewButton.setActionCommand(ActionsContrôleur.MODIFIER);
        editButton.setActionCommand(ActionsContrôleur.AFFICHER);
        exitButton.setActionCommand(ActionsContrôleur.EXIT);

        // Création d'un panneau principal avec une disposition BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.NORTH); // Boutons au-dessus
        mainPanel.add(tablePanel, BorderLayout.CENTER); // Table en dessous

        // Ajout du panneau principal à la JFrame
        getContentPane().add(mainPanel);

        loadDataFromFile(filePath);
    }

    public void addButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void deleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void editButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void viewButtonListener(ActionListener listener) {
        viewButton.addActionListener(listener);
    }

    public void exitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    public void adminNameFieldListener(ActionListener listener) {
        adminNameField.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void setContrôleurManager(Contrôleur Contrôleur) {
        addButton.setActionCommand(ActionsContrôleur.AJOUT);
        deleteButton.setActionCommand(ActionsContrôleur.SUPPRIMER);
        editButton.setActionCommand(ActionsContrôleur.MODIFIER);
        viewButton.setActionCommand(ActionsContrôleur.AFFICHER);
        exitButton.setActionCommand(ActionsContrôleur.EXIT);

        addButton.addActionListener(Contrôleur);
        System.out.println("Listener ajouté pour Ajout");
        deleteButton.addActionListener(Contrôleur);
        System.out.println("Listener ajouté pour Suppression");
        editButton.addActionListener(Contrôleur);
        System.out.println("Listener ajouté pour Modification");
        viewButton.addActionListener(Contrôleur);
        System.out.println("Listener ajouté pour Affichage");
        exitButton.addActionListener(Contrôleur);
        System.out.println("Listener ajouté pour Quitter");
    }

    private void add() {
        JFrame tableFrame = new JFrame("Contenu de la JTable");
        tableFrame.setSize(600, 240);

        // Création d'un JScrollPane pour la JTable
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Ajout du JScrollPane à la JFrame
        tableFrame.getContentPane().add(tableScrollPane);

        // Rendre la JFrame visible
        tableFrame.setVisible(true);
    }

    private void clear() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt("", i, 1);
        }
    }

    private void showDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void loadDataFromFile(String filePath)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            model.setRowCount(0); // Clear existing rows
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                model.addRow(data);
            }
        } catch (IOException e)
        {
            showMessage("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    @Override
    public void loadDataFromFileStream(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath)))
        {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof String[]) {
                    String[] data = (String[]) obj;
                    model.addRow(data);
                } else {
                    System.err.println("Erreur : Format de données invalide dans le fichier.");
                }
            }
        } catch (EOFException e) {
            // Fin du fichier atteinte
        } catch (IOException | ClassNotFoundException e) {
            showMessage("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public void Ajoute()
    {
        // Create a JDialog for data entry
        JDialog dialog = new JDialog(this, "Ajouter un Livre", true);
        dialog.setLayout(new GridLayout(10, 3));

        JTextField idField = new JTextField();
        JTextField titreField = new JTextField();
        JTextField auteurField = new JTextField();
        JTextField editeurField = new JTextField();
        JTextField anneeField = new JTextField();
        JTextField isbnField = new JTextField();
        JTextField nombreCopiesField = new JTextField();

        // Add labels and fields to the dialog, aligning labels to the left and text fields to the right
        dialog.add(new JLabel("ID du Livre:", SwingConstants.CENTER));
        dialog.add(idField);
        dialog.add(new JLabel("Titre du Livre:", SwingConstants.CENTER));
        dialog.add(titreField);
        dialog.add(new JLabel("Auteur:", SwingConstants.CENTER));
        dialog.add(auteurField);
        dialog.add(new JLabel("Editeur:", SwingConstants.CENTER));
        dialog.add(editeurField);
        dialog.add(new JLabel("Année de Publication:", SwingConstants.CENTER));
        dialog.add(anneeField);
        dialog.add(new JLabel("ISBN:", SwingConstants.CENTER));
        dialog.add(isbnField);
        isbnField.setEditable(false);
        dialog.add(new JLabel("Nombre de Copies:", SwingConstants.CENTER));
        dialog.add(nombreCopiesField);

        JButton submitButton = new JButton("Ajouter");
        submitButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            String titre = titreField.getText();
            String auteur = auteurField.getText();
            String editeur = editeurField.getText();
            int annee = Integer.parseInt(anneeField.getText());
            String isbn = isbnField.getText();
            int nombreCopies = Integer.parseInt(nombreCopiesField.getText());

            Livre nouveauLivre = new Livre(id, titre, auteur, editeur, annee, isbn, nombreCopies);
            coucheAccèsDonnées.AjouteLivre("C:\\Users\\Loris\\Personnes\\Books.txt",nouveauLivre);
            coucheAccèsDonnées.ajouterLivre(nouveauLivre);
            dialog.dispose();
        });

        dialog.add(submitButton);

        dialog.pack();
        dialog.setVisible(true);
    }

    public void Supprime() {
        String filePath = "C:\\Users\\Loris\\Personnes\\Books.txt";
        String input = JOptionPane.showInputDialog(null, "Entrez l'ID du livre à rendre:", "Rendre Livre",
                JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.isEmpty()) {
            showDialog("ID du livre non valide.");
            return;
        }

        try
        {
            int id = Integer.parseInt(input);
            coucheAccèsDonnées.SupprimerLivre(id);
            if (coucheAccèsDonnées.supprimerLivre(id, filePath))
            {
                showDialog("Vous avez supprimé le livre avec succès.");
            }
            else {
                showDialog("Impossible de supprimer le livre. Vérifiez l'ID.");
            }
        }
        catch (NumberFormatException e)
        {
            showDialog("ID du livre non valide.");
        }
    }

    public void Modifier() {
        // Create a JDialog for data entry
        JDialog dialog = new JDialog(this, "Modifier un Livre", true);
        dialog.setLayout(new GridLayout(10, 2));

        JTextField idField = new JTextField();
        JTextField titreField = new JTextField();
        JTextField auteurField = new JTextField();
        JTextField editeurField = new JTextField();
        JTextField anneeField = new JTextField();

        dialog.add(new JLabel("ID du Livre:"));
        dialog.add(idField);
        dialog.add(new JLabel("Titre du Livre:"));
        dialog.add(titreField);
        dialog.add(new JLabel("Auteur:"));
        dialog.add(auteurField);
        dialog.add(new JLabel("Editeur:"));
        dialog.add(editeurField);
        dialog.add(new JLabel("Année de Publication:"));
        dialog.add(anneeField);

        JButton submitButton = new JButton("Modifier");
        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String titre = titreField.getText();
                String auteur = auteurField.getText();
                String editeur = editeurField.getText();
                int annee = Integer.parseInt(anneeField.getText());

                Livre livreModifié = new Livre(id, titre, auteur, editeur, annee, "", 0);
                boolean success = coucheAccèsDonnées.modifierLivre(id, titre,auteur, editeur, annee, "C:\\Users\\Loris\\Personnes\\Books.txt");
                if (success) {
                    showMessage("Livre modifié avec succès !");
                } else {
                    showMessage("Erreur : Livre non trouvé avec l'ID spécifié.");
                }
            } catch (NumberFormatException ex) {
                showMessage("Erreur : Veuillez entrer des valeurs valides.");
            } catch (Exception ex) {
                showMessage("Erreur : " + ex.getMessage());
            }
            dialog.dispose();
        });

        dialog.add(submitButton);

        dialog.pack();
        dialog.setVisible(true);
    }

    public void Afficher() {
        String filePath = "C://Users//Loris//Personnes//Books.txt";
        loadDataFromFile(filePath);
    }
}
