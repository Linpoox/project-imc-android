package projet.Dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import projet.DAO.DonneeDAOImpl;
import projet.DAO.UtilisateurDAOImpl;

public class DbAdapteur {
	static final String DATABASE_NAME = "database.db";
	static final int DATABASE_VERSION = 1;

	static final String UTILISATEUR_TABLE_NAME = "utilisateurs";
	static final String DONNEE_TABLE_NAME = "donnees";

	static final String UTILISATEUR_ID = "id";
	static final String DONNEE_ID = "id";

	static final String UTILISATEUR_PSEUDO = "pseudo";
	static final String UTILISATEUR_SEXE = "sexe";

	static final String UTILISATEUR_TAILLE = "taille";
	static final String DONNEE_POIDS = "poids";
	static final String DONNEE_UTILISATEUR_ID = "utilisateur_id";
	static final String DONNEE_DATE = "date";

	static final String UTILISATEUR_TABLE_CREATE = "CREATE TABLE "
			+ UTILISATEUR_TABLE_NAME + " (" + UTILISATEUR_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + UTILISATEUR_PSEUDO
			+ " TEXT, "  + UTILISATEUR_TAILLE + " text not null, " +UTILISATEUR_SEXE+" TEXT);";

	static final String DONNEE_TABLE_CREATE = "CREATE TABLE "
			+ DONNEE_TABLE_NAME + " (" + DONNEE_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + DONNEE_UTILISATEUR_ID
			+ " integer, " + DONNEE_POIDS
			+ " text not null, " + DONNEE_DATE + " text not null, "
			+ "FOREIGN KEY (" + DONNEE_UTILISATEUR_ID + ") REFERENCES "
			+ UTILISATEUR_TABLE_NAME + " (" + UTILISATEUR_ID + "));";

	static final String UTILISATEUR_DROP = "DROP TABLE IF EXISTS "
			+ UTILISATEUR_TABLE_NAME;
	static final String DONNEE_DROP = "DROP TABLE IF EXISTS "
			+ DONNEE_TABLE_NAME;

	private SQLiteDatabase db;
	private BDAppli dbAplli;

	public DbAdapteur(Context context) {
		this.dbAplli = new BDAppli(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public void open() throws android.database.SQLException {
		this.db = this.dbAplli.getWritableDatabase();
	}

	public void close() {
		this.db.close();
	}

	public long ajouter(String pseudo,String taille,String sexe) {
		long id;
		ContentValues newLine = new ContentValues();
		newLine.put(UTILISATEUR_PSEUDO, pseudo);
        newLine.put(UTILISATEUR_TAILLE,taille);
		newLine.put(UTILISATEUR_SEXE, sexe);
		id = this.db.insert(UTILISATEUR_TABLE_NAME, null, newLine);
		return id;
	}

	public boolean supprimer(long id) {
		int indiceSetup = db.delete(UTILISATEUR_TABLE_NAME, UTILISATEUR_ID
				+ "=" + id, null);
		return indiceSetup > 0;
	}

    public long ajoutDonne(String poids){
        ContentValues newLine = new ContentValues();
        newLine.put(DONNEE_POIDS,poids);
        newLine.put(DONNEE_DATE, GregorianCalendar.getInstance().getTime().toString());
        return this.db.insert(DONNEE_TABLE_NAME,null,newLine);
    }

    public boolean supprimerDonnee(long id){
        int indice = db.delete(DONNEE_TABLE_NAME,DONNEE_ID+"="+id,null);
        return indice > 0;
    }

    public List<DonneeDAOImpl> getAllDonneFromUtil(long id){
        List<DonneeDAOImpl> listDonnee = new ArrayList<DonneeDAOImpl>();
        Cursor curseur = db.query(DONNEE_TABLE_NAME, new String[] {DONNEE_ID, DONNEE_POIDS, DONNEE_DATE,DONNEE_UTILISATEUR_ID},DONNEE_UTILISATEUR_ID+"="+id,null,null,null,null);
        if(curseur.moveToFirst()){
            do{
                DonneeDAOImpl donne = new DonneeDAOImpl(curseur.getInt(curseur.getColumnIndex(DONNEE_ID)),curseur.getString(curseur.getColumnIndex(DONNEE_POIDS)),curseur.getString(curseur.getColumnIndex(DONNEE_DATE)));
                listDonnee.add(donne);
            }while (curseur.moveToNext());
        }
        return listDonnee;
    }

	public List<UtilisateurDAOImpl> getAllUtilisateur() {
		List<UtilisateurDAOImpl> listUtil = new ArrayList<UtilisateurDAOImpl>();
		Cursor curseur = db.query(UTILISATEUR_TABLE_NAME, new String[] {
				UTILISATEUR_ID, UTILISATEUR_PSEUDO,UTILISATEUR_TAILLE, UTILISATEUR_SEXE }, null,
				null, null, null, null);
		if (curseur.moveToFirst()) {
			do {
				UtilisateurDAOImpl util = new UtilisateurDAOImpl(curseur.getInt(curseur
						.getColumnIndex(UTILISATEUR_ID)),
						curseur.getString(curseur
								.getColumnIndex(UTILISATEUR_PSEUDO)),curseur.getString(curseur.getColumnIndex(UTILISATEUR_TAILLE)),
						curseur.getString(curseur
								.getColumnIndex(UTILISATEUR_SEXE)));
				listUtil.add(util);
			} while (curseur.moveToNext());
		}
		return listUtil;
	}

	class BDAppli extends SQLiteOpenHelper {

		public BDAppli(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(UTILISATEUR_TABLE_CREATE);
			db.execSQL(DONNEE_TABLE_CREATE);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(UTILISATEUR_DROP);
			db.execSQL(DONNEE_DROP);
			onCreate(db);

		}

	}
}
