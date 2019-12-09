package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.example.*;

public class Personal extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    Client client;

    TextView name, surname, id, deviceid, time, heartpulse, spo2, temperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toolbar toolbar_back = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar_back.setNavigationIcon(R.drawable.ic_back);

        toolbar_back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Error is coming from the code below
                startActivity(new Intent(getApplicationContext(), Search.class));
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Personal.this, Personal_Map.class);
                startActivity(intent);
            }
        });


        SharedPreferences pref=getSharedPreferences("SelectedClient", Activity.MODE_PRIVATE);
        String Index=pref.getString("My_Client", "");
        final int ClientIndex=Integer.valueOf(Index);

        name= (TextView)findViewById(R.id.textView_name);
        surname= (TextView)findViewById(R.id.textView_surname);
        id= (TextView)findViewById(R.id.textView_ID);
        deviceid= (TextView)findViewById(R.id.textView_deviceid);
        time= (TextView)findViewById(R.id.textView_time);
        heartpulse= (TextView)findViewById(R.id.textView_Heart_rate);
        spo2= (TextView)findViewById(R.id.textView_Spo2);
        temperature= (TextView)findViewById(R.id.textView_temperature);


        database= FirebaseDatabase.getInstance();
        ref=database.getReference("");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    if(i==ClientIndex) {  //where i==index of client
                        client = ds.getValue(Client.class);
                        //System.out.println("This is "+client.getID());
                        break;
                    }
                    i++;
                }
                name.setText(getString(R.string.name)+"  "+client.getName());
                surname.setText(getString(R.string.surname)+"  "+client.getSurname());
                id.setText(getString(R.string.id)+"  "+String.valueOf(client.getID()));
                System.out.println("This is....."+client.getID());
                deviceid.setText(getString(R.string.client_id)+"  "+String.valueOf(client.getDevice_ID()));
                time.setText(getString(R.string.title_activity_time)+"  "+client.getTime());
                heartpulse.setText(getString(R.string.title_activity_heart_rate)+"  "+String.valueOf(client.getHeart_Rate()));
                spo2.setText(getString(R.string.spo2)+"  "+String.valueOf(client.getSpo2()));
                temperature.setText(getString(R.string.temperature)+"  "+String.valueOf(client.getTemperature()));
                //System.out.println("My Key is here and it is "+client);
                //spo2.setText(getString(R.string.spo2)+"\t"+String.valueOf(client.getSpo2()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });





    }

}
