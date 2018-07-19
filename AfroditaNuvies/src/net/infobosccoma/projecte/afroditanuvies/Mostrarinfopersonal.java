package net.infobosccoma.projecte.afroditanuvies;

import net.infobosccoma.projecte.afroditanuvies.model.Temporada;
import net.infobosccoma.projecte.afroditanuvies.utils.JSonResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Mostrarinfopersonal extends Activity {
	
	private net.infobosccoma.projecte.afroditanuvies.utils.JSonResponse jsonResponse;
	TextView textFacebook;
	TextView textGoogle;
	TextView textTwitter;
	TextView textInstagram;
	TextView textCorreu;
	TextView textWeb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrarinfopersonal);
		textFacebook = (TextView) findViewById(R.id.textFacebook);
		textGoogle = (TextView) findViewById(R.id.textGoogle);
		textTwitter = (TextView) findViewById(R.id.textTwitter);
		textInstagram = (TextView) findViewById(R.id.textInstagram);
		textCorreu = (TextView) findViewById(R.id.textCorreu);
		textWeb = (TextView) findViewById(R.id.textWeb);
		// Crear l'objecte apuntant a la URL
				jsonResponse = new JSonResponse();
		new connexioHTTPPost()
		.execute("http://afroditanuvies.bugs3.com/json/info_personal.php");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrarinfopersonal, menu);
		return true;
	}
	
private class connexioHTTPPost extends AsyncTask<String, Void, JSONArray> {

		/**
		 * S'executa aquest mètode abans del mètode doInBackground
		 */
		protected void onPreExecute() {
			
		}

		/**
		 * Executa en segon pla
		 * 
		 * Aquí dins no s'interactua amb el GUI
		 */
		protected JSONArray doInBackground(String... url) {
			// Assignar la URL
			jsonResponse.setURL(url[0]);
			// Crear un POST amb els paràmetres corresponents
//			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
//			postParameters.add(new BasicNameValuePair("idTemporada", "36"));
			// Executar la consulta
//			return jsonResponse.ejectuarConsultaPost(postParameters);
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

						textFacebook.setText(jSonObject.getString("facebook"));
						textGoogle.setText(jSonObject.getString("google"));
						textTwitter.setText(jSonObject.getString("twitter"));
						textInstagram.setText(jSonObject.getString("instagram"));
						textCorreu.setText(jSonObject.getString("mail"));
						textWeb.setText(jSonObject.getString("web"));
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
			}
		}

	}

}
