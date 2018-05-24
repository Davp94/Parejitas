package com.fullstack.umsa.parejas;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ResultActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private String tipo;
    private String point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        point = getIntent().getExtras().getString("puntaje","error");
        tipo = getIntent().getExtras().getString("nameJuego");
        int p = Integer.valueOf(point);
        String m = setMsg(p);
        TextView txtPoint = findViewById(R.id.text_point);
        TextView msg = findViewById(R.id.msg_result);
        Button btn = findViewById(R.id.btnAgain);

        txtPoint.setText(point);
        msg.setText(m);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarJuego();
                Intent i = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

    }

    private String setMsg(int p) {
        String m = "";
        if(tipo.equals("friendzone")){
            if (p >= 75) m = getString(R.string.fz_one);
            if (p >= 50 && p <= 74) m = getString(R.string.fz_two);
            if (p >= 25 && p <= 49) m = getString(R.string.fz_three);
            if (p <= 24) m = getString(R.string.fz_four);
        }
        if (tipo.equals("sica")) {
            if (p >= 75) m = getString(R.string.text_one);
            if (p >= 50 && p <= 74) m = getString(R.string.text_two);
            if (p >= 25 && p <= 49) m = getString(R.string.text_three);
            if (p <= 24) m = getString(R.string.text_four);
        }
        return m;
    }
    private void registrarJuego(){
        String url="http://esertec61.000webhostapp.com/onePlaye.php?tipo="+
                    tipo+"&puntaje="+Integer.parseInt(point);
        url=url.replace(" ","&20");
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        RequestQueue request= Volley.newRequestQueue(this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se subio los datos "+error.toString(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(),"se ha guardado los datos",Toast.LENGTH_LONG).show();
    }
}
