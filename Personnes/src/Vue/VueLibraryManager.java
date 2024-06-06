package Vue;

import Contrôleur.Contrôleur;

public interface VueLibraryManager
{
    void loadDataFromFile(String filePath);

    void setContrôleurManager(Contrôleur contrôleur);

}
