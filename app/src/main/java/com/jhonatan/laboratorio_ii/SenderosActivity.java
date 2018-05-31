package com.jhonatan.laboratorio_ii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jhonatan.laboratorio_ii.Modelo.Senderos;

import java.util.ArrayList;

public class SenderosActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Senderos> senderosList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senderos);

        senderosList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("Senderos").child("Informacion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                senderosList.clear();
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Senderos senderos= snapshot.getValue(Senderos.class);
                        senderosList.add(senderos);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onButtonClicked(View view) {

        int id = view.getId();
        if(id==R.id.bRegistrar){
            Intent intent = new Intent(SenderosActivity.this,RegistrarSenderoActivity.class);
            intent.putExtra("senderos",senderosList);
            startActivity(intent);
            finish();
        }else if(id == R.id.bSenderos){
            Intent intent = new Intent(SenderosActivity.this,MostrarSenderosActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
