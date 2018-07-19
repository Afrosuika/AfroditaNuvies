package net.infobosccoma.projecte.afroditanuvies;

import java.util.ArrayList;

import net.infobosccoma.projecte.afroditanuvies.model.Element;
import net.infobosccoma.projecte.afroditanuvies.model.Temporada;
import net.infobosccoma.projecte.afroditanuvies.utils.JSonResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class LlistarTemporades extends Activity implements OnItemClickListener{
	ListView llista;
	private net.infobosccoma.projecte.afroditanuvies.utils.JSonResponse jsonResponse;
	private ArrayList<Element> temporades;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_llista_opcions);
		llista = (ListView) findViewById(R.id.listView1);

		// Crear l'objecte apuntant a la URL
		jsonResponse = new JSonResponse();

		// Crear la llista on acabarà la informació
		temporades = new ArrayList<Element>();

		// Obtenir la informació
		new connexioHTTPPost()
				.execute("http://afroditanuvies.bugs3.com/json/temporades.php");
		llista.setOnItemClickListener(this);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.llistar_temporades, menu);
		return true;
	}
	
	private class connexioHTTPPost extends AsyncTask<String, Void, JSONArray> {

		
		private ProgressDialog dialog = new ProgressDialog(LlistarTemporades.this);

		/**
		 * S'executa aquest mètode abans del mètode doInBackground
		 */
		protected void onPreExecute() {
			dialog.setMessage(getString(R.string.carregantTemporades));
			dialog.show();
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

						temporades.add(new Temporada(jSonObject.getInt("id"),
								jSonObject.getString("nom"), jSonObject
										.getString("descripcio"), jSonObject
										.getString("imatge")));	
					}
					LlistaElementAdapter adapter = new LlistaElementAdapter(getBaseContext(), temporades);
					llista.setAdapter(adapter);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
			}
			dialog.dismiss();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int idTemporada = temporades.get(arg2).getId();
		Intent mostrarcoleccions = new Intent(getBaseContext(),LlistaColeccionsActivity.class);
		mostrarcoleccions.putExtra("idTemporada", idTemporada);
		startActivity(mostrarcoleccions);
		// TODO Auto-generated method stub
		
	}

}
