package com.fullstack.umsa.parejas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        Button btnNext=findViewById(R.id.btn_send_one_year);
        final RelativeLayout relativeDias=findViewById(R.id.relativeLayoutDias);
        final RelativeLayout relativeMeses=findViewById(R.id.relativeLayoutMeses);

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

                if(cont<6)
                {
                    cont++;
                    txtQuestion.setText(questionsParejas[cont]);
                    switch (cont)
                    {
                        case 1:  case 5:
                            resultados.put(cont,spinnerMes.getSelectedItem().toString());
                            relativeDias.setVisibility(View.INVISIBLE);
                            relativeMeses.setVisibility(View.VISIBLE);
                            break;
                        case 2: case 3:
                            resultados.put(cont,spinnerDia.getSelectedItem().toString()+"-"+spinnerMes.getSelectedItem().toString());
                            relativeDias.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            resultados.put(cont,spinnerDia.getSelectedItem().toString());
                            relativeMeses.setVisibility(View.INVISIBLE);
                            break;
                    }


                }else{
                    resultados.put(cont,spinnerMes.getSelectedItem().toString());
                    Intent i = new Intent(ParejasActivity.this, ResultParejasActivity.class);
                    i.putExtra("nameJuego","one_year");
                    for (Map.Entry<Integer, String> entry : resultados.entrySet()) {
                        i.putExtra(String.valueOf(entry.getKey()),entry.getValue());
                    }
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
