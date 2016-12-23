package br.edu.devmedia.graph;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class GoogleGraph {
	
	public Map<String, String> getGraphDados(String graph) {
		Map<String, String> perfil = new HashMap<String, String>();
		
		try {
			JSONObject json = new JSONObject(graph);
			perfil.put("primeiro_nome", json.getString("given_name"));
			perfil.put("ultimo_nome", json.getString("family_name"));
			perfil.put("email", json.getString("email"));
			perfil.put("imagem", json.getString("picture"));
			perfil.put("sexo", json.getString("gender"));
			
			return perfil;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return perfil;
	}

}
