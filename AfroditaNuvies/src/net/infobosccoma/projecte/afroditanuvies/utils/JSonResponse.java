package net.infobosccoma.projecte.afroditanuvies.utils;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class JSonResponse {

	private String url;
	
	public JSonResponse(){
	}
	
	public void setURL(String url){
		this.url = url;
	}
	
	public JSONArray ejectuarConsultaPost(ArrayList<NameValuePair> postParameters){
		String resposta = null;
		
		try {
			resposta = CustomHttpClient.executarHttpPost(url, postParameters);
			// Comprovar que el script PHP devuelva resultados
			String resultat = resposta.toString();
			if (resposta != "ERROR") {
				// Poner los resultados en un array JSON i devolver objecto
				try {
					JSONArray jArray = new JSONArray(resultat);
					return jArray;
				} catch (JSONException e) {
					Log.e("JSON Error",
							"Error al analitzar el JSON " + e.toString());
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			Log.e("HTTP error",
					"Causa: " + e.getCause() + " | Mis. error: "
							+ e.getMessage() + " | Mis. Loc: "
							+ e.getLocalizedMessage());
			return null;
		}
	}

	public JSONArray ejecutarConsultaGet() {
		String resposta = null;

		// Executar el m�tode executarHttpPost passant-li els par�metres
		// necessaris
		try {
			resposta = CustomHttpClient.executarHttpGet(url);
			// Comprovar que el script PHP devuelva resultados
			String resultat = resposta.toString();
			if (resposta != "ERROR") {
				// Poner los resultados en un array JSON i devolver objecto
				try {
					JSONArray jArray = new JSONArray(resultat);
					return jArray;
				} catch (JSONException e) {
					Log.e("JSON Error",
							"Error al analitzar el JSON " + e.toString());
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			Log.e("HTTP error",
					"Causa: " + e.getCause() + " | Mis. error: "
							+ e.getMessage() + " | Mis. Loc: "
							+ e.getLocalizedMessage());
			return null;
		}
	}
	
	public void ejecutarConsultaSinRespuesta() {
		try {
			CustomHttpClient.executarHttpGet(url);

		} catch (Exception e) {
			Log.e("HTTP error",
					"Causa: " + e.getCause() + " | Mis. error: "
							+ e.getMessage() + " | Mis. Loc: "
							+ e.getLocalizedMessage());
		}
	}

}
