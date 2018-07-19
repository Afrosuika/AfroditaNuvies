package net.infobosccoma.projecte.afroditanuvies.mides;

import net.infobosccoma.projecte.afroditanuvies.MenuPrincipal;
import net.infobosccoma.projecte.afroditanuvies.R;
import net.infobosccoma.projecte.afroditanuvies.model.ConjuntMides;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResumMidesActivity extends Activity implements OnClickListener {
	TextView valorPit;
	TextView valorCintura;
	TextView valorMaluc;
	TextView valorAltura;
	ConjuntMides lesMides;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resum_mides);
		lesMides = (ConjuntMides) getIntent().getSerializableExtra("mides");
		valorPit = (TextView) findViewById(R.id.txtValorPit);
		valorCintura = (TextView) findViewById(R.id.txtValorCintura);
		valorMaluc = (TextView) findViewById(R.id.txtValorMaluc);
		valorAltura = (TextView) findViewById(R.id.txtValorAltura);
		valorPit.setText(""+lesMides.getMidaPit());
		valorCintura.setText(""+lesMides.getMidaCintura());
		valorMaluc.setText(""+lesMides.getMidaMaluc());
		valorAltura.setText(""+lesMides.getMidaAltura());
		Button botoModificar = (Button) findViewById(R.id.btnModificarMides);
		botoModificar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resum_mides, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		Intent prendrelesmides = new Intent(getBaseContext(), PitActivity.class);
		Bundle paquet = new Bundle();
		paquet.putSerializable("mides", lesMides);
		prendrelesmides.putExtras(paquet);
		startActivity(prendrelesmides);		
		
	}
	
	@Override
	public void onBackPressed() {
		Intent tornaralmenu = new Intent(getBaseContext(), MenuPrincipal.class);
		this.finish();
		startActivity(tornaralmenu);
	}

}
