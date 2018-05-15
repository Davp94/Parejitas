package com.fullstack.umsa.parejas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class SicaActivity extends AppCompatActivity  {

    private int counter = 0;
    private int pointCurrent = 0;
    private RadioButton opOne;
    private RadioButton opTwo;
    private RadioButton opThree;
    private RadioButton opFour;
    private RadioGroup content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_sica_friendzone);
        final String [] questionsSica = getResources().getStringArray(R.array.list_question_sica);
        final String [] answersSica = getResources().getStringArray(R.array.list_answer_sica);
        final TextView textQuestion = findViewById(R.id.text_question);
        opOne = findViewById(R.id.answer_one);
        opTwo = findViewById(R.id.answer_two);
        opThree = findViewById(R.id.answer_three);
        opFour = findViewById(R.id.answer_four);
        content = findViewById(R.id.content_answers);
        Button btnNext = findViewById(R.id.btn_send_sica);

        String [] tempAnswers = answersSica[counter].split("-");
        textQuestion.setText(questionsSica[counter]);
        opOne.setText(tempAnswers[0]);
        opTwo.setText(tempAnswers[1]);
        opThree.setText(tempAnswers[2]);
        opFour.setText(tempAnswers[3]);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointCurrent += getPoint();
                if (counter < 5){
                    content.clearCheck();
                    counter++;
                    textQuestion.setText(questionsSica[counter]);
                    String [] temp = answersSica[counter].split("-");
                    opOne.setText(temp[0]);
                    opTwo.setText(temp[1]);
                    opThree.setText(temp[2]);
                    opFour.setText(temp[3]);

                }else {

                    Intent i = new Intent(SicaActivity.this, ResultActivity.class);
                    i.putExtra("puntaje", String.valueOf(pointCurrent));
                    i.putExtra("nameJuego","sica");
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    private int getPoint() {
        if (opOne.isChecked()) return 20;
        if (opTwo.isChecked()) return 10;
        if (opThree.isChecked()) return 5;
        if (opFour.isChecked()) return 1;
        return 0;
    }

}
