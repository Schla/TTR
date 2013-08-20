package com.beppo.AnTTRoid;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AnTTRoidActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		calculator = new TTRCalc();

		OldTtr = (EditText) findViewById(R.id.contentMyTtr);
		AeK = (EditText) findViewById(R.id.contentChangeConstant);
		Opp1Ttr = (EditText) findViewById(R.id.contentOpp1Ttr);
		Opp2Ttr = (EditText) findViewById(R.id.contentOpp2Ttr);
		Opp3Ttr = (EditText) findViewById(R.id.contentOpp3Ttr);
		NewTtr = (EditText) findViewById(R.id.contentNewTtr);
		Won1 = (CheckBox) findViewById(R.id.checkBoxWon1);
		Won2 = (CheckBox) findViewById(R.id.checkBoxWon2);
		Won3 = (CheckBox) findViewById(R.id.checkBoxWon3);

		SharedPreferences settings = getPreferences(0);

		OldTtr.setText(settings.getString("CurrTtr", "1600"));
		AeK.setText(settings.getString("AeK", "16"));
		Opp1Ttr.setText(settings.getString("Opp1Ttr", "1620"));
		Opp2Ttr.setText(settings.getString("Opp2Ttr", "0"));
		Opp3Ttr.setText(settings.getString("Opp3Ttr", "0"));
		Won1.setChecked(settings.getBoolean("Won1", true));
		Won2.setChecked(settings.getBoolean("Won2", false));
		Won3.setChecked(settings.getBoolean("Won3", false));
		NewTtr.setText(settings.getString("NewTtr", ""));

	}

	@Override
	protected void onStop() {
		super.onStop();

		// We need an Editor object to make preference changes.
		// All objects are from android.context.Context
		SharedPreferences settings = getPreferences(0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("CurrTtr", OldTtr.getText().toString());
		editor.putString("AeK", AeK.getText().toString());
		editor.putString("Opp1Ttr", Opp1Ttr.getText().toString());
		editor.putString("Opp2Ttr", Opp2Ttr.getText().toString());
		editor.putString("Opp3Ttr", Opp3Ttr.getText().toString());
		editor.putBoolean("Won1", Won1.isChecked());
		editor.putBoolean("Won2", Won2.isChecked());
		editor.putBoolean("Won3", Won3.isChecked());
		editor.putString("NewTtr", NewTtr.getText().toString());

		// Commit the edits!
		editor.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.close_clear_cancel:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.ttrmenu, menu);
	    return true;
	}

	TTRCalc calculator;

	EditText OldTtr;
	EditText AeK;
	EditText Opp1Ttr;
	EditText Opp2Ttr;
	EditText Opp3Ttr;
	EditText NewTtr;
	CheckBox Won1;
	CheckBox Won2;
	CheckBox Won3;
	int change;

	// This method is called at button click because we assigned the name to the
	// "On Click property" of the button
	public void ButtonCalculateHandler(View view) {

		Day thisDay = calculator.getPlayer().getDay(0);

		try {
			thisDay.setTtr(Integer.valueOf(OldTtr.getText().toString()));
			thisDay.setChangeConstant(Integer.valueOf(AeK.getText().toString()));
		} catch (NumberFormatException ex) {
			Context context = getApplicationContext();
			CharSequence text = "Geben Sie einen gültigen TTR und eine gültige Änderungskonstante (z.B. 16) ein!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}

		try {
			thisDay.getGame(0).setOpponentTTR(
					Integer.valueOf(Opp1Ttr.getText().toString()));
			thisDay.getGame(1).setOpponentTTR(
					Integer.valueOf(Opp2Ttr.getText().toString()));
			thisDay.getGame(2).setOpponentTTR(
					Integer.valueOf(Opp3Ttr.getText().toString()));
		} catch (NumberFormatException ex) {
			Context context = getApplicationContext();
			CharSequence text = "Geben Sie einen gültigen TTR für Ihre Gegner ein! Alternativ können Sie 0 eingeben, um die Berechnung zu stoppen.";
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}

		thisDay.getGame(0).setWon(Won1.isChecked());
		thisDay.getGame(1).setWon(Won2.isChecked());
		thisDay.getGame(2).setWon(Won3.isChecked());

		change = calculator.getChange();

		NewTtr.setText(Integer.toString(thisDay.getTtr() + change));

	}

	public void buttonChangeConstantHandler(View view) {
		Context context = getApplicationContext();
		CharSequence text = "Wie lautet meine Änderungskonstante?\n\n"
				+ "Über 21 Jahre: 16\n"
				+ "16 - 21 Jahre: 20\n"
				+ "Jünger als 16 Jahre: 24\n\n"
				+ "Zusätzlich\n"
				+ "- über ein Jahr nicht gespielt (gilt nur für die ersten 15 Spiele): +4\n"
				+ "- insgesamt weniger als 30 Spiele: +4\n";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}