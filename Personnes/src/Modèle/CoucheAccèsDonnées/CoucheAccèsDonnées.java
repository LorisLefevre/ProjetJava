package Modèle.CoucheAccèsDonnées;

import Modèle.ClassesMétier.Auteur;
import Modèle.ClassesMétier.Editeur;
import Modèle.ClassesMétier.Livre;

import java.util.*;

public interface CoucheAccèsDonnées
{

    String GenererISBN();
    void AjouteLivre(String filePath, Livre livre);

    boolean supprimerLivre(int Id, String filePath);

    boolean modifierLivre(int id, String nouveauTitre, String nouvelAuteur, String nouvelEditeur, int nouvelleAnnee, String filePath);
    void affiche(String filePath);

    boolean emprunterLivre(int id);

    boolean rendreLivre(int id);

    List<Livre> rechercherLivre(String motCle);

}
