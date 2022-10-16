package com.example.tp4_grupo6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tp4_grupo6.conexion.DataAltaGuardar;
import com.example.tp4_grupo6.conexion.DataListarCategorias;
import com.example.tp4_grupo6.entidades.Articulo;
import com.example.tp4_grupo6.entidades.Categoria;

public class Alta extends Fragment {
    private EditText etId;
    private EditText etNombre;
    private EditText etStock;
    private Spinner spCategoria;
    private Button btnAceptar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alta, container, false);

        etId=(EditText)view.findViewById(R.id.etId);
        etNombre=(EditText) view.findViewById(R.id.etNombreModificar);
        etStock=(EditText) view.findViewById(R.id.etStockModificar);
        spCategoria=(Spinner)view.findViewById(R.id.spCategoriaModificar);

        iniciarSpinnerCategorias();
        btnAceptar=(Button)view.findViewById(R.id.btnModificar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarArticulo();
                limpiarUI();
            }
        });

        return view;
    }

    private void guardarArticulo(){
        Integer id=Integer.parseInt(this.etId.getText().toString());
        String nombre=this.etNombre.getText().toString();
        Integer stock=Integer.parseInt(this.etStock.getText().toString());
        Categoria categoria= (Categoria)this.spCategoria.getSelectedItem();
        Articulo articulo=new Articulo(id,categoria,nombre,stock);

        DataAltaGuardar task = new DataAltaGuardar(getContext(),articulo);
        task.execute();
    }
    private void iniciarSpinnerCategorias(){
        DataListarCategorias task= new DataListarCategorias(getContext(),spCategoria);
        task.execute();
    }

    private void limpiarUI(){
        etId.setText("");
        etNombre.setText("");
        etStock.setText("");
        Categoria categoria= (Categoria)this.spCategoria.getSelectedItem();
        spCategoria.setSelection(getSpinnerIndex(spCategoria,categoria.getDescripcion()));
    }

    private int getSpinnerIndex(Spinner spinner, String value){
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(value)){
                index = i;
            }
        }
        return index;
    }
}