package com.fullstack.umsa.parejas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class ParejasSeniorActivity extends AppCompatActivity {
    private int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parejas);
        final String[] questionsParejas=  getResources().getStringArray(R.array.list_question_three_years);
        final Map<Integer, String> resultados= new HashMap<Integer,String>();
        final TextView txtQuestion=findViewById(R.id.text_question);
        final EditText editInput=findViewById(R.id.editTextInput);
        final ImageView image=findViewById(R.id.imgP);

        final Button btnNext=findViewById(R.id.btn_send_one_year);
        final LinearLayout linear=findViewById(R.id.linearLayoutInput);
        final RelativeLayout relativeDias=findViewById(R.id.relativeLayoutDias);
        final RelativeLayout relativeMeses=findViewById(R.id.relativeLayoutMeses);
        final TextView txtBases=findViewById(R.id.aboutBases);
        txtQuestion.setText(questionsParejas[cont]);
        image.setBackgroundResource(R.drawable.mono);
        relativeDias.setVisibility(View.GONE);
        relativeMeses.setVisibility(View.GONE);
        linear.setVisibility(View.VISIBLE);
        editInput.setHint("Los conoci en...");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont==0)
                {
                    resultados.put(cont,editInput.getText().toString());
                    editInput.setText("");
                }

                if(cont<7)
                {
                    cont++;

                    txtQuestion.setText(questionsParejas[cont]);
                    switch (cont)
                    {
                        case 1:
                            resultados.put(cont,editInput.getText().toString());
                            editInput.setText("");
                            editInput.setHint("plato favorito");
                            image.setBackgroundResource(R.drawable.babas);

                            break;
                        case 2:
                            resultados.put(cont,editInput.getText().toString());
                            editInput.setText("");
                            editInput.setHint("estatura");
                            image.setBackgroundResource(R.drawable.estatura);
                            break;
                        case 3:
                            resultados.put(cont,editInput.getText().toString());
                            editInput.setText("");
                            editInput.setHint("lo mejor de el/ella es...");
                            image.setBackgroundResource(R.drawable.corazon);

                            break;
                        case 4:
                            resultados.put(cont,editInput.getText().toString());
                            editInput.setText("");
                            editInput.setHint("llegamos a...");
                            txtBases.setText(Html.fromHtml(getResources().getString(R.string.aboutBases)));
                            txtBases.setVisibility(View.VISIBLE);
                            txtBases.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Uri uri=Uri.parse("https://es.wikipedia.org/wiki/Met%C3%A1foras_de_b%C3%A9isbol_para_el_sexo");
                                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                                    startActivity(intent);
                                }
                            });
                            image.setBackgroundResource(R.drawable.emoticono);
                            break;
                        case 5:
                            resultados.put(cont,editInput.getText().toString());
                            editInput.setText("");
                            txtBases.setVisibility(View.GONE);
                            editInput.setHint("mas o menos unas...");
                            image.setBackgroundResource(R.drawable.te_amo);

                            break;
                        case 6:
                            resultados.put(cont,editInput.getText().toString());
                            editInput.setText("");
                            editInput.setHint("le encanta...");
                            image.setBackgroundResource(R.drawable.fecha);

                            break;

                        case 7:
                            resultados.put(cont,editInput.getText().toString());
                            editInput.setText("");
                            editInput.setHint("fue con...");
                            image.setBackgroundResource(R.drawable.hot);

                            break;

                    }



                }else{

                    resultados.put(cont+1,editInput.getText().toString());
                    editInput.setText("");
                    Intent i = new Intent(ParejasSeniorActivity.this, ResultParejasActivity.class);
                    i.putExtra("nameJuego","3_year");
                    for (Map.Entry<Integer, String> entry : resultados.entrySet()) {
                        i.putExtra(String.valueOf(entry.getKey()),entry.getValue());
                    }
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Volver");
        builder.setMessage("Desea volver al menu principal? se perderan los datos actuales");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
