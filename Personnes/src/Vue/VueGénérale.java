package Vue;

import Modèle.ClassesMétier.Bibliothecaire;
import Modèle.ClassesMétier.Client;
import Modèle.ClassesMétier.Livre;
import Modèle.ClassesMétier.*;
import Contrôleur.*;
import java.util.ArrayList;

public interface VueGénérale
{
    void setContrôleur(Contrôleur contrôleur);

    void run();

    Bibliothecaire LoginAdmin();

    Client LoginClient();

    void AfficheMessage(String message);

    void AfficheMessageErreur(String messageErreur);

    void MontrerLivres(ArrayList<Livre> livres);

    Livre promptforNewLivre(Livre livre);

    Livre promptforUpdateLivre(Livre livre);

    Integer promptforLivreID();

    Livre promptforDeleteLivre(Livre livre);

    Livre promptforViewLivre(Livre livre);

}
