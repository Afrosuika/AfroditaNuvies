package net.infobosccoma.projecte.afroditanuvies;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.infobosccoma.projecte.afroditanuvies.model.Coleccio;
import net.infobosccoma.projecte.afroditanuvies.utils.AppConstant;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MostrarColeccioActivity extends Activity implements
		OnClickListener {
	ImageView imatge;
	Coleccio aquestaColeccio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_coleccio);
		aquestaColeccio = (Coleccio) getIntent().getSerializableExtra(
				"coleccio");
		setTitle(aquestaColeccio.getNom());
		imatge = (ImageView) findViewById(R.id.imatgeColeccio);
		new TascaDescarrega().execute(AppConstant.URL
				+ aquestaColeccio.getImatgeurl() + aquestaColeccio.getImatge());
		TextView text = (TextView) findViewById(R.id.descripcioColeccio);
		Button bototeixits = (Button) findViewById(R.id.botoTeixits);
		Button botovestits = (Button) findViewById(R.id.botoVestits);
		text.setText(aquestaColeccio.getDescripcio());
		bototeixits.setOnClickListener(this);
		botovestits.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_coleccio, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.botoTeixits:
			Intent llancarActivitatTeixits = new Intent(getBaseContext(),
					LlistaTeixits.class);
			llancarActivitatTeixits.putExtra("idColeccio",
					aquestaColeccio.getId());
			startActivity(llancarActivitatTeixits);
			break;
		case R.id.botoVestits:
			Intent llancarActivitatVestits = new Intent(getBaseContext(),
					GaleriaDeVestits.class);
			llancarActivitatVestits.putExtra("idColeccio",
					aquestaColeccio.getId());
			startActivity(llancarActivitatVestits);
			break;
		}

	}

	class TascaDescarrega extends AsyncTask<String, Void, Bitmap> {
		ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = new ProgressDialog(MostrarColeccioActivity.this);
			dialog.setMessage(getString(R.string.carregantImatge));
			dialog.show();

		}

		@Override
		protected Bitmap doInBackground(String... params) {
			return loadImageFromNetwork(params[0]);
		}

		protected void onPostExecute(Bitmap result) {
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			int width = dm.widthPixels;
//			int width = (int) Math.ceil(dm.widthPixels
//					* (dm.densityDpi / 160.0));

			if (result.getWidth() > width) {
				float ratio = (float) width / (float) result.getWidth();
				result = Bitmap.createScaledBitmap(result, width,
						(int) ((float) result.getHeight() * (float) ratio),
						true);
				
			}
			dialog.dismiss();		
			imatge.setImageBitmap(result);
		}

	}

	private Bitmap loadImageFromNetwork(String url) {

		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream((InputStream) new URL(url)
					.getContent());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

}
