package net.infobosccoma.projecte.afroditanuvies;

import net.infobosccoma.projecte.afroditanuvies.model.ConjuntMides;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class PrendreMidesActivity extends Activity implements
		OnClickListener {
	ImageView imatge;
	TextView text;
	EditText edittext;
	ConjuntMides aquestesMides;
	Button boto;

	public ConjuntMides getAquestesMides() {
		return aquestesMides;
	}

	public void setAquestesMides(ConjuntMides aquestesMides) {
		this.aquestesMides = aquestesMides;
	}

	public ImageView getImatge() {
		return imatge;
	}

	public void setImatge(ImageView imatge) {
		this.imatge = imatge;
	}

	public TextView getText() {
		return text;
	}

	public void setText(TextView text) {
		this.text = text;
	}

	public EditText getEdittext() {
		return edittext;
	}

	public void setEdittext(EditText edittext) {
		this.edittext = edittext;
	}

	public Button getBoto() {
		return boto;
	}

	public void setBoto(Button boto) {
		this.boto = boto;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mides);
		imatge = (ImageView) findViewById(R.id.imatgeMida);
		text = (TextView) findViewById(R.id.explicacioMida);
		edittext = (EditText) findViewById(R.id.txtMida);
		boto = (Button) findViewById(R.id.btnMida);
		boto.setOnClickListener(this);
		aquestesMides = (ConjuntMides) getIntent().getSerializableExtra("mides");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prendre_mides, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		Intent tornaralmenu = new Intent(getBaseContext(), MenuPrincipal.class);
		this.finish();
		startActivity(tornaralmenu);
	}
	
	public void assignarImatgeEscalada(Bitmap laimatge){
		int width = this.obtenirAmpladaPantalla();
		if (laimatge.getWidth() > width) {
			float ratio = (float) width / (float) laimatge.getWidth();
			laimatge = Bitmap.createScaledBitmap(laimatge, width,
					(int) ((float) laimatge.getHeight() * (float) ratio),true);
		}
		this.imatge.setImageBitmap(laimatge);		
	}

	public int obtenirAmpladaPantalla() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		return width;
	}

}
