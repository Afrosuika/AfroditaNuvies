package net.infobosccoma.projecte.afroditanuvies.mides;

import net.infobosccoma.projecte.afroditanuvies.PrendreMidesActivity;
import net.infobosccoma.projecte.afroditanuvies.R;
import net.infobosccoma.projecte.afroditanuvies.R.layout;
import net.infobosccoma.projecte.afroditanuvies.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;

public class CinturaActivity extends PrendreMidesActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		Bitmap laimatge = BitmapFactory.decodeResource(getResources(),R.drawable.imatgemidacintura);
		this.assignarImatgeEscalada(laimatge);
		this.getText().setText(R.string.descripcioCintura);
		if(this.getAquestesMides().getMidaCintura()!=0){
			this.getEdittext().setText(""+this.getAquestesMides().getMidaCintura());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cintura, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		float midaacanviar = Float.parseFloat(this.getEdittext().getText().toString());
		this.getAquestesMides().setMidaCintura(midaacanviar);
		Intent passaralseguent = new Intent(getBaseContext(),MalucActivity.class);
		Bundle paquet = new Bundle();
		paquet.putSerializable("mides", this.getAquestesMides());		
		passaralseguent.putExtras(paquet);
		startActivity(passaralseguent);		
	}	

}
