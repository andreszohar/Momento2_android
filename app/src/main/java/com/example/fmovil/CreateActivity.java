package com.example.fmovil;

import android.os.Bundle;

import com.example.fmovil.models.MovilModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateActivity extends BaseActivity {

    FloatingActionButton fab_create_save,fab_create_clear,fab_create_back;
    ImageView tv_create_img_movil;
    TextView tv_create_click_img;
    EditText et_create_descripcion;
    EditText et_create_brand;
    EditText et_create_serial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();
        fab_create_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });


        fab_create_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clear();
            }
        });

        fab_create_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serial,descripcion,brand;
                boolean active;

                serial=et_create_serial.getText().toString();
                descripcion=et_create_descripcion.getText().toString();
                brand=et_create_brand.getText().toString();

                if(serial.isEmpty() || descripcion.isEmpty() || brand.isEmpty()){
                        maleSimpleAlertDialog("info","Por favor llene todos los campos");
                }else {
                    models=new MovilModels();
                    models.setActive(true);
                    models.setDescripcion(descripcion);
                    models.setBrand(brand);
                    models.setSerial(serial);

                    save(models);


                }


            }
        });
    }

    private void save(MovilModels models) {
        if(collectionReference!=null){
           collectionReference.add(models)
                   .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                       @Override
                       public void onComplete(@NonNull Task<DocumentReference> task) {
                           if(task.isSuccessful()){
                               if(task.getResult()!=null){
                                        maleSimpleAlertDialog("Success","Movil guardado");
                                        clear();
                               }else {
                                    maleSimpleAlertDialog("Warning","Movil No se guardo ");
                               }

                           }else {
                                maleSimpleAlertDialog("Error",task.getException().getMessage());
                           }

                       }
                   });
        }else {
            maleSimpleAlertDialog("Error","No hay conexion a la base de datos ");
        }
    }

    protected void init(){
        fab_create_save=findViewById(R.id.fab_create_save);
        fab_create_clear=findViewById(R.id.fab_create_clear);
        fab_create_back=findViewById(R.id.fab_create_back);
        tv_create_img_movil=findViewById(R.id.tv_create_img_movil);
        tv_create_click_img=findViewById(R.id.tv_create_click_img);
        et_create_descripcion=findViewById(R.id.et_create_descripcion);
        et_create_brand=findViewById(R.id.et_create_brand);
        et_create_serial=findViewById(R.id.et_create_serial);
    }

    private void clear(){
        et_create_brand.setText("");
        et_create_descripcion.setText("");
        et_create_serial.setText("");
        et_create_serial.requestFocus();

        tv_create_img_movil.setImageResource(R.drawable.ic_mobile_friendly_black_18dp);



    }

}