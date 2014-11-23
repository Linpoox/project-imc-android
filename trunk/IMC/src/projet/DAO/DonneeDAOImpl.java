package projet.DAO;

/**
 * Created by Emeric on 22-11-14.
 */
public class DonneeDAOImpl implements  DonneeDAO {

    public long id;
    public String poids;
    public String date;

    public DonneeDAOImpl(long id, String poids, String date) {
        this.id = id;
        this.poids = poids;
        this.date = date;
    }

    @Override
    public void ajoutDonnee() {

    }

    @Override
    public void supprimerDonne() {

    }

    public long getId() {
        return id;
    }

    public String getPoids() {
        return poids;
    }

    public String getDate() {
        return date;
    }
}
