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
import org.w3c.dom.Text;

public class ResultParejasActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String[] questionsParejas=  getResources().getStringArray(R.array.list_question_one_year);
        final String[] questionsParejas2=  getResources().getStringArray(R.array.list_question_three_years);
        setContentView(R.layout.activity_result_parejas);
        TextView ans1=findViewById(R.id.textViewAnswer1);
        TextView ans2=findViewById(R.id.textViewAnswer2);
        TextView ans3=findViewById(R.id.textViewAnswer3);
        TextView ans4=findViewById(R.id.textViewAnswer4);
        TextView ans5=findViewById(R.id.textViewAnswer5);
        TextView ans6=findViewById(R.id.textViewAnswer6);
        TextView ans7=findViewById(R.id.textViewAnswer7);
        TextView ans8=findViewById(R.id.textViewAnswer8);
        TextView que1=findViewById(R.id.textViewQuestion1);
        TextView que2=findViewById(R.id.textViewQuestion2);
        TextView que3=findViewById(R.id.textViewQuestion3);
        TextView que4=findViewById(R.id.textViewQuestion4);
        TextView que5=findViewById(R.id.textViewQuestion5);
        TextView que6=findViewById(R.id.textViewQuestion6);
        TextView que7=findViewById(R.id.textViewQuestion7);
        TextView que8=findViewById(R.id.textViewQuestion8);
        tipo=getIntent().getExtras().getString("nameJuego");
        if(tipo.equals("1_year"))
        {
            que1.setText(questionsParejas[0]);
            que2.setText(questionsParejas[1]);
            que3.setText(questionsParejas[2]);
            que4.setText(questionsParejas[3]);
            que5.setText(questionsParejas[4]);
            que6.setText(questionsParejas[5]);
            que7.setText(questionsParejas[6]);
            que8.setText(questionsParejas[7]);
            ans1.setText(getIntent().getExtras().getString("0"));
            ans2.setText(getIntent().getExtras().getString("2"));
            ans3.setText(getIntent().getExtras().getString("3"));
            ans4.setText(getIntent().getExtras().getString("4"));
            ans5.setText(getIntent().getExtras().getString("5"));
            ans6.setText(getIntent().getExtras().getString("6"));
            ans7.setText(getIntent().getExtras().getString("7"));
            ans8.setText(getIntent().getExtras().getString("8"));
        }else{
            que1.setText(questionsParejas2[0]);
            que2.setText(questionsParejas2[1]);
            que3.setText(questionsParejas2[2]);
            que4.setText(questionsParejas2[3]);
            que5.setText(questionsParejas2[4]);
            que6.setText(questionsParejas2[5]);
            que7.setText(questionsParejas2[6]);
            que8.setText(questionsParejas2[7]);
            ans1.setText(getIntent().getExtras().getString("0"));
            ans2.setText(getIntent().getExtras().getString("2"));
            ans3.setText(getIntent().getExtras().getString("3"));
            ans4.setText(getIntent().getExtras().getString("4"));
            ans5.setText(getIntent().getExtras().getString("5"));
            ans6.setText(getIntent().getExtras().getString("6"));
            ans7.setText(getIntent().getExtras().getString("7"));
            ans8.setText(getIntent().getExtras().getString("8"));

        }
        Button entendido=findViewById(R.id.btnEntendido);
        entendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarJuego();
                Intent i = new Intent(ResultParejasActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });





    }
    private void registrarJuego(){
        String url="http://esertec61.000webhostapp.com/parejitas.php?tipo_juego="+tipo+"&preg1="+getIntent().getExtras().getString("0")
                +"&preg2="+getIntent().getExtras().getString("2")+"&preg3="+getIntent().getExtras().getString("3")+
                "&preg4="+getIntent().getExtras().getString("4")+"&preg5="+getIntent().getExtras().getString("5")+
                "&preg6="+getIntent().getExtras().getString("6")+"&preg7="+getIntent().getExtras().getString("7")+
                "&preg8="+getIntent().getExtras().getString("8");
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
