package com.fullstack.umsa.parejas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONObject;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSica = findViewById(R.id.btn_sica);
        Button btnFriend = findViewById(R.id.btn_friendzone);
        Button btnOne = findViewById(R.id.btn_one_year);
        Button btnThree = findViewById(R.id.btn_three_year);

        btnSica.setOnClickListener(this);
        btnFriend.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnThree.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sica:
                Intent i = new Intent(this, SicaActivity.class);
                startActivity(i);
                break;
            case R.id.btn_friendzone:
                Intent a = new Intent(this, FriendzoneActivity.class);
                startActivity(a);
                break;
            case R.id.btn_one_year:
                Intent p= new Intent(this, ParejasActivity.class);
                startActivity(p);
                break;
            case R.id.btn_three_year:
                Intent pp= new Intent(this, ParejasSeniorActivity.class);
                startActivity(pp);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Salir");
        builder.setMessage("Desea salir del juego?");
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
