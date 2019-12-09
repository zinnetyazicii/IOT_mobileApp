package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    private static final String TAG = "Search";
    ListView Client_list;
    FirebaseDatabase database;
    DatabaseReference ref;

    //ArrayList<Client> Clients_obj;
    ArrayList<String> Clients;
    ArrayAdapter<String> adapter;
    int i;

    View updateview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, General_Map.class);
                startActivity(intent);
            }
        });


        Client_list = (ListView) findViewById(R.id.searchlist);
        EditText Filter = (EditText) findViewById(R.id.editText2);



        database= FirebaseDatabase.getInstance();
        ref=database.getReference("");
        Clients = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Clients);

        //System.out.println("Here...");
        if(isConnected()){
            Toast.makeText(getApplicationContext(),"connecting to Server...", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"No Internet...", Toast.LENGTH_SHORT).show();
            return;
        }



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Clients.clear();
                i=0;
                //Client_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Client client=ds.getValue(Client.class);
                    //System.out.println(client.getName());
                    client.setIndex(i);
                    Clients.add(client.getName()+" "+client.getSurname());
                    Client_list.setAdapter(adapter);
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (Search.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Client_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String client_name=(String) adapterView.getItemAtPosition(i);

                SharedPreferences.Editor editor=getSharedPreferences("SelectedClient", MODE_PRIVATE).edit();
                editor.putString("My_Client", String.valueOf(clientIndex(client_name)));
                editor.apply();

                Intent intent = new Intent(Search.this, Personal.class);
                startActivity(intent);

                /*updateview=Client_list.getChildAt(3);
                updateview.setBackgroundColor(Color.RED);*/
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.Logout){
            Intent intent = new Intent(Search.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.Refresh){
            finish();
            startActivity(getIntent());
        }
        return super.onOptionsItemSelected(item);
    }

    public int clientIndex(String Name){
        int j;
        for(j=0; j<Clients.size(); j++){
            if(Clients.get(j).equals(Name)){
                break;
            }
        }
        return j;
    }

    public boolean isConnected() {
        boolean internet_aval = false;
        try {
            ConnectivityManager con_man = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo net_info = con_man.getActiveNetworkInfo();
            internet_aval = (net_info != null) && net_info.isAvailable() && net_info.isConnected();
            return internet_aval;
        }
        catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return internet_aval;
    }


}