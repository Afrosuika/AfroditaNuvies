package net.infobosccoma.projecte.afroditanuvies.mides;

import net.infobosccoma.projecte.afroditanuvies.MenuPrincipal;
import net.infobosccoma.projecte.afroditanuvies.PrendreMidesActivity;
import net.infobosccoma.projecte.afroditanuvies.R;
import net.infobosccoma.projecte.afroditanuvies.model.ConjuntMidesPreferences;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class AlturaActivity extends PrendreMidesActivity {
	ConjuntMidesPreferences preferencies;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		Bitmap laimatge = BitmapFactory.decodeResource(getResources(),R.drawable.imatgemidaaltura);
		this.assignarImatgeEscalada(laimatge);
		this.getText().setText(R.string.descripcioAltura);
		preferencies = new ConjuntMidesPreferences(getBaseContext());
		if(this.getAquestesMides().getMidaAltura()!=0){
			this.getEdittext().setText(""+this.getAquestesMides().getMidaAltura());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.altura, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		float midaacanviar = Float.parseFloat(this.getEdittext().getText().toString());
		this.getAquestesMides().setMidaAltura(midaacanviar);
		preferencies.setUser(this.getAquestesMides());	
		Toast.makeText(getBaseContext(), R.string.midesGuardades, Toast.LENGTH_SHORT).show();
		Intent anaramostrar = new Intent(getBaseContext(),ResumMidesActivity.class);
		Bundle paquet = new Bundle();
		paquet.putSerializable("mides", this.getAquestesMides());
		anaramostrar.putExtras(paquet);
		startActivity(anaramostrar);
	}	

}
