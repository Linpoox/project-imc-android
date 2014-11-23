package projet.DAO;



public class UtilisateurDAOImpl implements UtilisateurDAO {

    private long id;
    private String pseudo;
    private String sexe;
    private String taille;

	@Override
	public void ajouterUtilisateur() {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerUtilisateur(long id) {
		// TODO Auto-generated method stub

	}

	@Override
    public void modifierUtilisateur() {
		// TODO Auto-generated method stub

	}

    public UtilisateurDAOImpl(int id, String pseudo,String sexe,String taille) {
        super();
        this.id = id;
        this.pseudo = pseudo;
        this.sexe = sexe;
        this.taille = taille;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getTaille(){
        return taille;
    }
}
