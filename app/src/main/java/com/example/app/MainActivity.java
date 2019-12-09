package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button search, language; EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadLocale();
        setContentView(R.layout.activity_main);

        search=(Button)findViewById(R.id.button);
        language=(Button)findViewById(R.id.button2);
        id=(EditText)findViewById(R.id.editText);
        id.setText("");

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        language.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changeLanguage();
            }
        });


    }

    public void login() {
        if (id.getText().toString().equals("1234")) {
            Toast.makeText(getApplicationContext(),"Redirecting...", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Search.class);
            startActivity(intent);

        }
        else {
            Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private void changeLanguage(){
        final String[] languages={"English", "Turkish"};
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    //English
                    setLocale("en");
                    recreate();
                }
                if(i==1){
                    //Turkish
                    setLocale("tr");
                    recreate();
                }

                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog=builder.create();
        builder.show();
    }

    private void setLocale(String lang){
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor=getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }


    public void loadLocale(){
        SharedPreferences pref=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String Lang=pref.getString("My_Lang", "");
        setLocale(Lang);
    }
}



