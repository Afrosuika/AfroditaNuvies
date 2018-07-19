package net.infobosccoma.projecte.afroditanuvies;

import java.util.ArrayList;

import net.infobosccoma.projecte.afroditanuvies.model.Element;
import net.infobosccoma.projecte.afroditanuvies.model.Teixit;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LlistaTeixits extends Activity implements OnItemClickListener {
	ListView llista;
	private net.infobosccoma.projecte.afroditanuvies.utils.JSonResponse jsonResponse;
	private ArrayList<Element> teixits;
	int laColeccio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		laColeccio = getIntent().getIntExtra("idColeccio", 0);
		setContentView(R.layout.activity_llista_opcions);
		llista = (ListView) findViewById(R.id.listView1);

		// Crear l'objecte apuntant a la URL
		jsonResponse = new JSonResponse();

		// Crear la llista on acabarà la informació
		teixits = new ArrayList<Element>();

		// Obtenir la informació
		new connexioHTTPPost()
				.execute("http://afroditanuvies.bugs3.com/json/teixits_coleccio.php");
		llista.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.llista_coleccions, menu);
		return true;
	}

	private class connexioHTTPPost extends AsyncTask<String, Void, JSONArray> {
		
		private ProgressDialog dialog = new ProgressDialog(LlistaTeixits.this);

		/**
		 * S'executa aquest mètode abans del mètode doInBackground
		 */
		protected void onPreExecute() {
			dialog.setMessage(getString(R.string.carregantTeixits));
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
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("idColeccio", ""
					+ laColeccio));
			// Executar la consulta
			return jsonResponse.ejectuarConsultaPost(postParameters);
			// return jsonResponse.ejecutarConsultaGet();
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

						teixits.add(new Teixit(jSonObject.getInt("id"),
								jSonObject.getString("nom"), jSonObject
										.getString("descripcio"), jSonObject
										.getString("imatge")));
					}
					LlistaElementAdapter adapter = new LlistaElementAdapter(
							getBaseContext(), teixits);
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
		Intent llancaActivitat = new Intent(getBaseContext(), MostrarTeixitActivity.class);
		Bundle elteixit = new Bundle();
		elteixit.putSerializable("teixit", (Teixit)teixits.get(arg2));
		llancaActivitat.putExtras(elteixit);
		startActivity(llancaActivitat);		
	}

}