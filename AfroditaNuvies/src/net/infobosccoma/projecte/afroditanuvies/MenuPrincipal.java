package net.infobosccoma.projecte.afroditanuvies;

import net.infobosccoma.projecte.afroditanuvies.mides.PitActivity;
import net.infobosccoma.projecte.afroditanuvies.mides.ResumMidesActivity;
import net.infobosccoma.projecte.afroditanuvies.model.ConjuntMides;
import net.infobosccoma.projecte.afroditanuvies.model.ConjuntMidesPreferences;
import net.infobosccoma.projecte.afroditanuvies.utils.AppConstant;
import net.infobosccoma.projecte.afroditanuvies.utils.JSonResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class MenuPrincipal extends Activity implements OnClickListener {
	ListView llista;
	String direccioWeb = "www.afroditanuvies.com";
	private ImageButton menuPrincipalTemporades, menuPrincipalVestits,
	menuPrincipalMides, menuPrincipalQuiSom, menuPrincipalDades;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		// Obtenir els botons
				menuPrincipalTemporades = (ImageButton) findViewById(R.id.menuPrincipalTemporades);
				menuPrincipalVestits = (ImageButton) findViewById(R.id.menuPrincipalVestits);
				menuPrincipalMides = (ImageButton) findViewById(R.id.menuPrincipalMides);
				menuPrincipalQuiSom = (ImageButton) findViewById(R.id.menuPrincipalQuiSom);
				menuPrincipalDades = (ImageButton) findViewById(R.id.menuPrincipalDades);
				
				// Assingar els listeners dels clicks
				menuPrincipalTemporades.setOnClickListener(this);
				menuPrincipalVestits.setOnClickListener(this);
				menuPrincipalMides.setOnClickListener(this);
				menuPrincipalQuiSom.setOnClickListener(this);
				menuPrincipalDades.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}

//	@Override
//	public void onItemClick(AdapterView<?> adapter, View view, int position,
//			long id) {
//		switch (position) {
//		case 0:
//			Intent mostrartemporades = new Intent(getApplicationContext(),
//					LlistarTemporades.class);
//			startActivity(mostrartemporades);
//			break;
//		case 1:			
//			ConjuntMidesPreferences preferencies = new ConjuntMidesPreferences(getBaseContext());
//			ConjuntMides conjuntmides = preferencies.getConjuntMides();
//			if(conjuntmides==null){
//				conjuntmides=new ConjuntMides();
//				Intent prendrelesmides = new Intent(getBaseContext(), PitActivity.class);
//				Bundle paquet = new Bundle();
//				paquet.putSerializable("mides", conjuntmides);
//				prendrelesmides.putExtras(paquet);
//				startActivity(prendrelesmides);				
//			}else{
//				Intent mostrarlesmides = new Intent(getBaseContext(), ResumMidesActivity.class);
//				Bundle paquet = new Bundle();
//				paquet.putSerializable("mides", conjuntmides);
//				mostrarlesmides.putExtras(paquet);
//				startActivity(mostrarlesmides);		
//			}
//			break;
//		case 2:
//			try{
//				new connexioHTTPPost().execute(AppConstant.URL
//					+ "/json/info_personal.php");
//			}catch(Exception e){
//			Intent anarweb = new Intent(Intent.ACTION_VIEW);
//			anarweb.setData(Uri.parse("http://"+direccioWeb));
//			startActivity(anarweb);
//			}
//			break;
//		case 3:
//			Intent mostrarinfopers = new Intent(getApplicationContext(), Mostrarinfopersonal.class);
//			startActivity(mostrarinfopers);
//			break;
//		}
//	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menuPrincipalTemporades:
			Intent mostrartemporades = new Intent(getApplicationContext(),
					LlistarTemporades.class);
			startActivity(mostrartemporades);
			break;
		case R.id.menuPrincipalMides:
			ConjuntMidesPreferences preferencies = new ConjuntMidesPreferences(getBaseContext());
			ConjuntMides conjuntmides = preferencies.getConjuntMides();
			if(conjuntmides==null){
				conjuntmides=new ConjuntMides();
				Intent prendrelesmides = new Intent(getBaseContext(), PitActivity.class);
				Bundle paquet = new Bundle();
				paquet.putSerializable("mides", conjuntmides);
				prendrelesmides.putExtras(paquet);
				startActivity(prendrelesmides);				
			}else{
				Intent mostrarlesmides = new Intent(getBaseContext(), ResumMidesActivity.class);
				Bundle paquet = new Bundle();
				paquet.putSerializable("mides", conjuntmides);
				mostrarlesmides.putExtras(paquet);
				startActivity(mostrarlesmides);		
			}
			break;
		case R.id.menuPrincipalQuiSom:
			try {
				new connexioHTTPPost().execute(AppConstant.URL
						+ "/json/info_personal.php");
			} catch (Exception e) {
				Intent anarweb = new Intent(Intent.ACTION_VIEW);
				anarweb.setData(Uri.parse("http://" + direccioWeb));
				startActivity(anarweb);
			}
			break;
		case R.id.menuPrincipalDades:
			Intent mostrarinfopers = new Intent(getApplicationContext(),
					Mostrarinfopersonal.class);
			startActivity(mostrarinfopers);
			break;
		case R.id.menuPrincipalVestits:
			Intent mostrarvestits = new Intent(getApplicationContext(), GaleriaDeVestits.class);
			startActivity(mostrarvestits);
		}

	}

	private class connexioHTTPPost extends AsyncTask<String, Void, JSONArray> {

		Intent anarweb;

		private ProgressDialog dialog = new ProgressDialog(MenuPrincipal.this);

		/**
		 * S'executa aquest mètode abans del mètode doInBackground
		 */
		protected void onPreExecute() {
			dialog.setMessage(getString(R.string.carregantWeb));
			dialog.show();
		}

		/**
		 * Executa en segon pla
		 * 
		 * Aquí dins no s'interactua amb el GUI
		 */
		protected JSONArray doInBackground(String... url) {
			JSonResponse jsonResponse = new JSonResponse();
			// Assignar la URL
			jsonResponse.setURL(url[0]);
			// Crear un POST amb els paràmetres corresponents
			// ArrayList<NameValuePair> postParameters = new
			// ArrayList<NameValuePair>();
			// postParameters.add(new BasicNameValuePair("idTemporada", "36"));
			// Executar la consulta
			// return jsonResponse.ejectuarConsultaPost(postParameters);
			return jsonResponse.ejecutarConsultaGet();
		}

		/**
		 * S'executa després del mètode doInBackground
		 */
		protected void onPostExecute(JSONArray jArray) {
			System.out.println(jArray);
			// Comprovar que hi hagi valors
			if (jArray != null) {
				try {
					JSONObject jSonObject = new JSONObject();
					for (int i = 0; i < jArray.length(); i++) {

						jSonObject = jArray.getJSONObject(i);

						direccioWeb = jSonObject.getString("web");
					}
					anarweb = new Intent(Intent.ACTION_VIEW);
					anarweb.setData(Uri.parse("http://"+direccioWeb));
					dialog.dismiss();
					startActivity(anarweb);

				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
			}

		}

	}
}
