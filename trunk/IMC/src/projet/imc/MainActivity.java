package projet.imc;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private Button b = null;
	private EditText textPoids;
	private EditText textTaille;
	Editable poids, editTaille;
	private double resultat;
	double taille;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textPoids = (EditText) findViewById(R.id.textPoids);
		textTaille = (EditText) findViewById(R.id.textTaille);
		b = (Button) findViewById(R.id.calculer);
		b.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	private void message(String titre, String message) {
		Builder erreur = new AlertDialog.Builder(MainActivity.this);
		erreur.setTitle(titre);
		erreur.setMessage(message);
		erreur.setNeutralButton("Valider", null);
		erreur.show();

	}

	@Override
	public void onClick(View v) {
		if (textTaille.length() == 0 && textPoids.length() == 0) {
			message("Erreur", "Veuillez rentrer une taille et un poids");
			return;
		}
		if (textTaille.length() == 0) {
			message("Erreur", "Veuillez rentrer une taille");
			return;
		}
		if (textPoids.length() == 0) {
			message("Erreur", "Veuillez rentrer un poids");
			return;
		} else {
			poids = textPoids.getText();
			editTaille = textTaille.getText();
			taille = Double.parseDouble(editTaille.toString());
			resultat = Double.parseDouble(poids.toString()) / (taille * taille);
			if (taille > 3)
				resultat = resultat * 10000;
			DecimalFormat dec = new DecimalFormat("0.00");
			Intent defineIntent = new Intent(MainActivity.this, Calcul.class);
			defineIntent.putExtra("calcul", dec.format(resultat));
			this.startActivity(defineIntent);
			return;
		}
	}
}
