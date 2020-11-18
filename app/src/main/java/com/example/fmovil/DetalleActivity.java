package com.example.fmovil;

import android.os.Bundle;

import com.example.fmovil.models.MovilModels;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class DetalleActivity extends BaseActivity {

    private FloatingActionButton fab_detail_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        models= (MovilModels) getIntent().getSerializableExtra("models");


        if(models!=null){
//            maleSimpleAlertDialog("Éxito  ","Modelos :  "+models.getSerial());
            Bundle bundle=new Bundle();
            bundle.putSerializable("models",models);
            DataDetailFragment.reciveData(bundle);
        }else {
            maleSimpleAlertDialog("Error ","Modelos vacíos  ");
        }
        fab_detail_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });

    }
    protected void init(){
        fab_detail_list=findViewById(R.id.fab_detail_list);
    }
}