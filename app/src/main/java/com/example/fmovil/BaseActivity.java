package com.example.fmovil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fmovil.adaters.MovilAdapter;
import com.example.fmovil.conection.firebaseConection;
import com.example.fmovil.models.MovilModels;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    protected MovilModels models;
    protected ArrayList<MovilModels> modelsArrayList;
    protected MovilAdapter adapter;

    protected FirebaseFirestore db;
    protected FirebaseAuth mAuth;
    protected FirebaseStorage mFirebaseStorage;

    protected Query query;
    protected CollectionReference collectionReference;
    protected StorageReference mStorageReference,fileReference;

    protected final String COLLECTION_NAME = "movil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init(){
        models = new MovilModels();
        db = firebaseConection.ConectionFirestore();
        mAuth = firebaseConection.ConectionAuth();
        mFirebaseStorage = firebaseConection.ConectionStorage();
        collectionReference = db.collection(COLLECTION_NAME);


    }
    protected void maleSimpleToast(String message, int duration){
        Toast.makeText(this,message,duration).show();
    }
    protected void maleSimpleAlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    protected void goToList(){
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }protected void goToCrate(){
        Intent intent = new Intent(this,CreateActivity.class);
        startActivity(intent);
    }protected void goToEdit(){
        Intent intent = new Intent(this,BaseActivity.class);
        startActivity(intent);
    }
    protected void goToSearch(){
        Intent intent = new Intent(this,BaseActivity.class);
        startActivity(intent);
    }
    protected void goToDetail(MovilModels models){
        Intent intent = new Intent(this,DetalleActivity.class);
        intent.putExtra("models",models);
        startActivity(intent);
    }


}