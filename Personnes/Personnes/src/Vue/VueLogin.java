package Vue;

import Modèle.ClassesMétier.Bibliothecaire;
import Modèle.ClassesMétier.Client;
import Contrôleur.*;
import Vue.InterfacesGraphiques.LoginWindow;

public interface VueLogin
{
    void run();
    void setContrôleur(Contrôleur contrôleur);
    Client LoginClient();
    Bibliothecaire LoginAdmin();
}
