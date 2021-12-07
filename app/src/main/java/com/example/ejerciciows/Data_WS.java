package com.example.ejerciciows;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Data_WS extends AppCompatActivity {
    RequestQueue rq;
    TextView textView;
    public static final String URL1 = "https://gorest.co.in/public/v1/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_ws);
        rq = Volley.newRequestQueue(this);
        initUI();
        jsonObjectRequest();
    }
    private void StringRqst(){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
            }
        }
        );
        rq.add(request);
    }

    private void initUI(){
        textView = findViewById(R.id.textView);
    }

    //MÉTODO PARA JSON SIMPLE, SIN NODO PADRE
    private void jsonObjectRequest(){
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                            textView.append("USUARIOS: \n");
                            try {
                                JSONArray jsonArray = response.getJSONArray("data");
                                int x = jsonArray.length();
                                for (int i = 0; i < x; i++ ) {
                                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                    String dataT = jsonObject.getString("id");
                                    String dataN = jsonObject.getString("name");
                                    String dataE = jsonObject.getString("email");
                                    textView.append("id : " + dataT + "\n");
                                    textView.append("Nombre : " + dataN + "\n");
                                    textView.append("Email : " + dataE + "\n");
                                    textView.append("\n\n");
                                }
                                }catch (JSONException ex){
                                textView.append(ex.toString());
                            }
                        }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        rq.add(jsonArrayRequest);
    }
}