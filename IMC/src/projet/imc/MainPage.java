package projet.imc;

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
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import projet.DAO.UtilisateurDAOImpl;
import projet.Dal.DbAdapteur;

public class MainPage extends ActionBarActivity implements OnClickListener {

	TableLayout tab;
	DbAdapteur db;
	Button creer = null;
	Button connection = null;
    Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        creer = (Button) findViewById(R.id.boutonCreerUtil);
        creer.setOnClickListener(this);
        connection = (Button) findViewById(R.id.boutonConnection);
        connection.setOnClickListener(this);
        db = new DbAdapteur(this);
        db.open();
        List<UtilisateurDAOImpl> util = db.getAllUtilisateur();
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> spinnerArray = new ArrayList<String>();
        if(util.size() == 0){
            spinnerArray.add("-- pas d'utilisateur --");
            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
            spinner.setAdapter(spinnerArrayAdapter);
        }else {
            for (UtilisateurDAOImpl utilisateur : util) {
                spinnerArray.add(utilisateur.getPseudo());
            }
            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
            spinner.setAdapter(spinnerArrayAdapter);

        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
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
		if (v.getId() == R.id.boutonConnection) {
			Intent intent = new Intent(MainPage.this, MainActivity.class);
			this.startActivity(intent);
		}
		if (v.getId() == R.id.boutonCreerUtil) {
			Intent intent = new Intent(MainPage.this, CreationUtilisateur.class);
			this.startActivity(intent);
		}

	}
}
