package net.infobosccoma.projecte.afroditanuvies;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import net.infobosccoma.projecte.afroditanuvies.model.LlistaVestits;
import net.infobosccoma.projecte.afroditanuvies.model.Vestit;
import net.infobosccoma.projecte.afroditanuvies.utils.AppConstant;
import net.infobosccoma.projecte.afroditanuvies.utils.JSonResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

public class GaleriaDeVestits extends Activity {
	
	private static final String URL_IMATGES_VESTITS = AppConstant.URL+"/img/vestits/";
	private static final String URL_VESTITS = AppConstant.URL+"/json/vestits.php";
	private static final String URL_TOTS_VESTITS = AppConstant.URL+"/json/tots_vestits.php";
	private GridView gridView;
	public ProgressDialog dialog;
	private int coleccio;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		coleccio = getIntent().getIntExtra("idColeccio", 0);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.galeria_de_vestis);
		
		// Obtenir la galeria
		gridView = (GridView) findViewById(R.id.galeriaVesits);
		
		// Inicialitzar les llistes estàtiques on es guardarà la informació
		LlistaVestits.setBitmapVestits(new ArrayList<Bitmap>());
		LlistaVestits.setVestits(new ArrayList<Vestit>());
		
		// Inicialitzar el dialeg
		dialog = new ProgressDialog(this);
		
		// Obtenir la informació dels vestits del servidor
		if(coleccio == 0){
			new HttpPost().execute(URL_TOTS_VESTITS);
		} else {
			new HttpPost().execute(URL_VESTITS);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.galeria_de_vestis, menu);
		return true;
	}
	
	/**
	 * Classe encarregada de obtenir la informació dels vestits d'una col·lecció
	 * determinada
	 * 
	 * @author marc
	 * 
	 */
	class HttpPost extends AsyncTask<String, Void, JSONArray> {

		private JSonResponse jSonResponse = new JSonResponse();

		/**
		 * S'executa aquest mètode abans del mètode doInBackground
		 */
		protected void onPreExecute() {
			dialog.setMessage(getResources().getString(R.string.carregantVestits));
			dialog.show();
		}

		/**
		 * Executa en segon pla Aquí dins no s'interactua amb el GUI
		 */
		protected JSONArray doInBackground(String... url) {
			
			jSonResponse.setURL(url[0]);
			
			// Assignar la URL
			if(coleccio == 0){
				return jSonResponse.ejecutarConsultaGet();
			} else {
				// Crear un POST amb els paràmetres corresponents
				ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
				postParameters.add(new BasicNameValuePair("idColeccio", ""+coleccio));
				// Executar la consulta
				return jSonResponse.ejectuarConsultaPost(postParameters);
			}
			
		}

		/**
		 * S'executa després del mètode doInBackground
		 */
		protected void onPostExecute(JSONArray jArray) {
			// Comprovar que hi hagi valors
			if (jArray != null) {
				try {
					JSONObject jSonObject = new JSONObject();
					for (int i = 0; i < jArray.length(); i++) {

						jSonObject = jArray.getJSONObject(i);

						String imatge = jSonObject.getString("imatge");

						LlistaVestits.getVestits().add(
								new Vestit(jSonObject.getInt("id"), jSonObject
										.getString("nom"), jSonObject
										.getString("descripcio"), imatge));

						new DescarregaImatges().execute(URL_IMATGES_VESTITS+imatge);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				dialog.setMessage("CONSULTA SENSE RESULTATS");
			}

		}
	}

	/**
	 * Classe encarregada de crear bitmaps a través de direccions URL,
	 * afegint-les a una llista
	 * 
	 * @author marc
	 * 
	 */
	class DescarregaImatges extends AsyncTask<String, Void, Void> {
		
		/**
		 * Obtenir les imatges i trasformar-les en bitmaps
		 */
		@Override
		protected Void doInBackground(String... urls) {
			for (String url : urls) {
				LlistaVestits.getBitmapVestits().add(loadImageFromNetwork(url));
			}
			return null;
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

		@Override
		protected void onPostExecute(Void result) {
			// Assignar l'adapador
			gridView.setAdapter(new ImageAdapter(GaleriaDeVestits.this));
			
			// Quan acabi la operació, fer desapareixer el dialeg
			if(LlistaVestits.getVestits().size() == LlistaVestits.getBitmapVestits().size()){
				if (dialog.isShowing()) {
					dialog.dismiss();
				}
			}
		}
	}

}
