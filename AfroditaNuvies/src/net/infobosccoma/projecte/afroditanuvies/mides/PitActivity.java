package net.infobosccoma.projecte.afroditanuvies.mides;

import net.infobosccoma.projecte.afroditanuvies.PrendreMidesActivity;
import net.infobosccoma.projecte.afroditanuvies.R;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class PitActivity extends PrendreMidesActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		Bitmap laimatge = BitmapFactory.decodeResource(getResources(),R.drawable.imatgemidapit);
		this.assignarImatgeEscalada(laimatge);
		this.getText().setText(R.string.descripcioPit);
		if(this.getAquestesMides().getMidaPit()!=0){
			this.getEdittext().setText(""+this.getAquestesMides().getMidaPit());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pit, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		float midaacanviar = Float.parseFloat(this.getEdittext().getText().toString());
		this.getAquestesMides().setMidaPit(midaacanviar);
		Intent passaralseguent = new Intent(getBaseContext(),CinturaActivity.class);
		Bundle paquet = new Bundle();
		paquet.putSerializable("mides", this.getAquestesMides());		
		passaralseguent.putExtras(paquet);
		startActivity(passaralseguent);		
	}	

}
