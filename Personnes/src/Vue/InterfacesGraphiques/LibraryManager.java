package Vue.InterfacesGraphiques;

import Contrôleur.ActionsContrôleur;
import Vue.VueLibraryManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LibraryManager extends JFrame implements VueLibraryManager
{
    private JTable table;
    private DefaultTableModel model;


    private JTextField adminNameField;

    public JTextField getAdminNameField()
    {
        return adminNameField;
    }

    public void setAdminName(String adminName)
    {
        adminNameField.setText(adminName);
    }

    private JButton addButton, deleteButton, viewButton, editButton, clearButton, exitButton;

    public JButton getAddButton()
    {
        return addButton;
    }

    public JButton getDeleteButton()
    {
        return deleteButton;
    }

    public JButton getViewButton()
    {
        return viewButton;
    }

    public JButton getEditButton()
    {
        return editButton;
    }

    public JButton getClearButton()
    {
        return clearButton;
    }

    public JButton getExitButton()
    {
        return exitButton;
    }
    public LibraryManager()
    {
        String filePath = "C:\\Users\\Loris\\Personnes\\Books.txt";
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
        for (int row = 0; row < numberOfRows; row++)
        {
            Object[] textFields = new Object[labels.length];
            for (int i = 0; i < labels.length; i++)
            {
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
            public TableCellEditor getCellEditor(int row, int column)
            {
                return new DefaultCellEditor(new JTextField());
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column)
            {
                return new TableCellRenderer()
                {
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
        clearButton = new JButton("Effacer");
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
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        addButton.setActionCommand(ActionsContrôleur.AJOUT);
        deleteButton.setActionCommand(ActionsContrôleur.SUPPRIMER);
        viewButton.setActionCommand(ActionsContrôleur.MODIFIER);
        editButton.setActionCommand(ActionsContrôleur.AFFICHER);
        clearButton.setActionCommand(ActionsContrôleur.NETTOYER);
        exitButton.setActionCommand(ActionsContrôleur.EXIT);

        // Création d'un panneau principal avec une disposition BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.NORTH); // Boutons au-dessus
        mainPanel.add(tablePanel, BorderLayout.CENTER); // Table en dessous

        // Ajout du panneau principal à la JFrame
        getContentPane().add(mainPanel);

        loadDataFromFile(filePath);
    }


    public void addButtonListener(ActionListener listener)
    {
        addButton.addActionListener(listener);
    }

    public void deleteButtonListener(ActionListener listener)
    {
        deleteButton.addActionListener(listener);
    }

    public void editButtonListener(ActionListener listener)
    {
        editButton.addActionListener(listener);
    }

    public void viewButtonListener(ActionListener listener)
    {
        viewButton.addActionListener(listener);
    }

    public void clearButtonListener(ActionListener listener)
    {
        clearButton.addActionListener(listener);
    }

    public void exitButtonListener(ActionListener listener)
    {
        exitButton.addActionListener(listener);
    }

    public void adminNameFieldListener(ActionListener listener)
    {
        adminNameField.addActionListener(listener);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    private void add()
    {
        JFrame tableFrame = new JFrame("Contenu de la JTable");
        tableFrame.setSize(600, 240);

        // Création d'un JScrollPane pour la JTable
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Ajout du JScrollPane à la JFrame
        tableFrame.getContentPane().add(tableScrollPane);

        // Rendre la JFrame visible
        tableFrame.setVisible(true);
    }

    private void clear()
    {
        for (int i = 0; i < model.getRowCount(); i++)
        {
            model.setValueAt("", i, 1);
        }
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
        }
        catch (IOException e)
        {
            showMessage("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }
}
