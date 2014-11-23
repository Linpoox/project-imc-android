package projet.imc;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import projet.Dal.DbAdapteur;

public class CreationUtilisateur extends ActionBarActivity implements OnClickListener {

	Spinner spinner;
	Button enregist;
	TextView nom;
	DbAdapteur db;
    TextView taille;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creation_utilisateur);
		db = new DbAdapteur(this);
		db.open();
		spinner = (Spinner) findViewById(R.id.sexSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		enregist = (Button) findViewById(R.id.creaEnreg);
		enregist.setOnClickListener(this);
		nom = (TextView) findViewById(R.id.textNom);
        taille = (TextView) findViewById(R.id.textTaille);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creation_utilisateur, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if(nom.getText().length()==0 && spinner.getSelectedItemPosition() == 0 ){
			message("Erreur", "Veuillez introduire votre nom et choisir votre sexe");
			return;
		}
		if(nom.getText().length()==0 ){
			message("Erreur", "Veuillez introduire votre nom");
			return;
		}
		if(spinner.getSelectedItemPosition() == 0){
			message("Erreur","Veuillez choisir votre sexe");
			return;
		}else{
			db.ajouter(nom.getText().toString(),taille.getText().toString(), spinner.getSelectedItem().toString());
			Intent intent = new Intent(CreationUtilisateur.this,MainPage.class);
			this.startActivity(intent);
			db.close();
			return;
		}
		
	}
	
	private void message(String titre, String message) {
		Builder erreur = new AlertDialog.Builder(CreationUtilisateur.this);
		erreur.setTitle(titre);
		erreur.setMessage(message);
		erreur.setNeutralButton("Valider", null);
		erreur.show();

	}
}
