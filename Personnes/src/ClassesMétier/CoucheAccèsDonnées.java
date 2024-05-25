package ClassesMétier;
import ClassesMétier.*;

import java.util.*;

public interface CoucheAccèsDonnées
{
    int AjoutLivre(Livre livre);
    int AjoutAuteur(Auteur auteur);
    int AjoutEditeur(Editeur editeur);

    boolean ModifierLivre(Livre livre);

    boolean ModifierAuteur(Auteur auteur);

    boolean ModifierEditeur(Editeur editeur);

    boolean SupprimerLivre(int idLivre);

    boolean SupprimerLivre(Livre livre);

    boolean SupprimerAuteur(int idAuteur);
    boolean SupprimerAuteur(Auteur auteur);

    boolean SupprimerEditeur(int idEditeur);

    boolean SupprimerEditeur(Editeur editeur);

    Livre RechercherLivre(int idLivre);
    List<Livre> ListerLivres();

    List<Auteur> ListerAuteurs();

    List<Editeur> ListerEditeurs();
}
