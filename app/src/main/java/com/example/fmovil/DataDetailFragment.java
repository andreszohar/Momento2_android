package com.example.fmovil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fmovil.models.MovilModels;

public class DataDetailFragment extends Fragment {

    private static String serial, descripcion,brand;
    private boolean active;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_data_detail_serial,tv_data_detail_descripcion,tv_data_detail_brand;

        tv_data_detail_serial=view.findViewById(R.id.tv_data_detail_serial);
        tv_data_detail_descripcion=view.findViewById(R.id.tv_data_detail_descripcion);
        tv_data_detail_brand=view.findViewById(R.id.tv_data_detail_brand);

        tv_data_detail_serial.setText(serial);
        tv_data_detail_descripcion.setText(descripcion);
        tv_data_detail_brand.setText(brand);


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DataDetailFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    static void reciveData(Bundle bundle){
        MovilModels models= (MovilModels) bundle.getSerializable("models");
        if(models!=null){
            serial=models.getSerial();
            descripcion=models.getDescripcion();
            brand=models.getBrand();

        }
    }
}