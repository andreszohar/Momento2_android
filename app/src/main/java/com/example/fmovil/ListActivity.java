package com.example.fmovil;

import android.os.Bundle;

import com.example.fmovil.adaters.MovilAdapter;
import com.example.fmovil.models.MovilModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {

    private FloatingActionButton fab_list_create;
    private ListView tv_list_movil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();


        fab_list_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCrate();
            }
        });

        tv_list_movil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                models=modelsArrayList.get(i);
                goToDetail(models);

            }
        });

    }

    protected void init(){
        fab_list_create=findViewById(R.id.fab_list_create);
        tv_list_movil=findViewById(R.id.tv_list_movil);
    }
    protected void getMovil(){
        if(collectionReference!=null){
            collectionReference.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                if(task.getResult()!=null){
                                        modelsArrayList=new ArrayList<>();
                                        for (QueryDocumentSnapshot snapshot : task.getResult()){
                                            models=snapshot.toObject(MovilModels.class);
                                            modelsArrayList.add(models);
                                        }
                                        if(modelsArrayList.size()>0){
                                            pintarMovil(modelsArrayList);

                                        }else {
                                            maleSimpleAlertDialog("Alerta","El movil no se existe");

                                        }
                                }else {
                                    maleSimpleAlertDialog("Peligro","El movil no se encuentra");
                                }

                            }else {
                                maleSimpleAlertDialog("Error",task.getException().getMessage());
                            }

                        }
                    });

        }else {
            maleSimpleToast("Error en Base de datos",1);
        }

    }

    private void pintarMovil(ArrayList<MovilModels> modelsArrayList) {
        adapter=new MovilAdapter(this,modelsArrayList);
        tv_list_movil.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMovil();
    }
}