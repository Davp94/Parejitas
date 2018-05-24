package com.fullstack.umsa.parejas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class ParejasActivity extends AppCompatActivity {
    private int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parejas);

        final String[] questionsParejas=  getResources().getStringArray(R.array.list_question_one_year);
        final Map<Integer, String> resultados= new HashMap<Integer,String>();
        final TextView txtQuestion=findViewById(R.id.text_question);

        final Spinner spinnerDia=findViewById(R.id.spinnerDia);
        final Spinner spinnerMes=findViewById(R.id.spinnerMes);
        final Button btnNext=findViewById(R.id.btn_send_one_year);
        final RelativeLayout relativeDias=findViewById(R.id.relativeLayoutDias);
        final RelativeLayout relativeMeses=findViewById(R.id.relativeLayoutMeses);
        final TextView textDias=findViewById(R.id.textViewDias);
        final LinearLayout linear=findViewById(R.id.linearLayoutInput);
        final EditText editInput=findViewById(R.id.editTextInput);
        final ImageView image=findViewById(R.id.imgP);
        final TextView txtBases=findViewById(R.id.aboutBases);

        ArrayAdapter<CharSequence> adapterDia=ArrayAdapter
                .createFromResource(this,R.array.days,android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterMes=ArrayAdapter
                .createFromResource(this,R.array.months,android.R.layout.simple_spinner_item);
        adapterDia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterMes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDia.setAdapter(adapterDia);
        spinnerMes.setAdapter(adapterMes);

        txtQuestion.setText(questionsParejas[cont]);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont==0)
                {

                   resultados.put(cont,spinnerDia.getSelectedItem().toString()+"-"+spinnerMes.getSelectedItem().toString());

                }

                if(cont<7)
                {
                    cont++;

                        txtQuestion.setText(questionsParejas[cont]);


                    switch (cont)
                    {
                        case 1:
                            resultados.put(cont,spinnerMes.getSelectedItem().toString());
                            image.setBackgroundResource(R.drawable.beso);
                            textDias.setText("Dias");
                            relativeDias.setVisibility(View.GONE);
                            relativeMeses.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            resultados.put(cont,spinnerMes.getSelectedItem().toString());

                            relativeDias.setVisibility(View.VISIBLE);
                            image.setBackgroundResource(R.drawable.cumpleanos);
                            break;
                        case 3:
                            resultados.put(cont,spinnerDia.getSelectedItem().toString()+"-"+spinnerMes.getSelectedItem().toString());

                            relativeDias.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            resultados.put(cont,spinnerDia.getSelectedItem().toString()+"-"+spinnerMes.getSelectedItem().toString());

                            textDias.setText("Veces");
                            relativeMeses.setVisibility(View.GONE);
                            image.setBackgroundResource(R.drawable.romper);
                            break;
                        case 5:
                            resultados.put(cont,spinnerDia.getSelectedItem().toString());

                            textDias.setText("Dias");
                            relativeDias.setVisibility(View.GONE);
                            image.setBackgroundResource(R.drawable.te_amo);
                            relativeMeses.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            resultados.put(cont,spinnerMes.getSelectedItem().toString());
                            relativeDias.setVisibility(View.GONE);
                            image.setBackgroundResource(R.drawable.fecha);
                            relativeMeses.setVisibility(View.VISIBLE);
                            break;

                        case 7:
                            resultados.put(cont,spinnerMes.getSelectedItem().toString());

                            relativeDias.setVisibility(View.GONE);
                            relativeMeses.setVisibility(View.GONE);
                            image.setBackgroundResource(R.drawable.emoticono);
                            linear.setVisibility(View.VISIBLE);
                            txtBases.setVisibility(View.VISIBLE);
                            txtBases.setText(Html.fromHtml(getResources().getString(R.string.aboutBases)));
                            txtBases.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Uri uri=Uri.parse("https://es.wikipedia.org/wiki/Met%C3%A1foras_de_b%C3%A9isbol_para_el_sexo");
                                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                                    startActivity(intent);
                                }
                            });
                            break;

                    }



                }else{

                    resultados.put(cont+1,editInput.getText().toString());
                    Intent i = new Intent(ParejasActivity.this, ResultParejasActivity.class);
                    i.putExtra("nameJuego","1_year");
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
