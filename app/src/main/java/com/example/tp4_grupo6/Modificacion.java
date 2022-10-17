package com.example.tp4_grupo6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp4_grupo6.conexion.DataAltaGuardar;
import com.example.tp4_grupo6.conexion.DataBuscarArticulo;
import com.example.tp4_grupo6.conexion.DataListarCategorias;
import com.example.tp4_grupo6.conexion.DataModificarArticulo;
import com.example.tp4_grupo6.entidades.Articulo;
import com.example.tp4_grupo6.entidades.Categoria;

public class Modificacion extends Fragment {
    private EditText etId;
    private EditText etNombre;
    private EditText etStock;
    private Spinner spCategoria;
    private Button btnModificar;
    private Button btnBuscar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modificacion, container, false);
        etId=(EditText)view.findViewById(R.id.etIdModificar);
        etNombre=(EditText) view.findViewById(R.id.etNombreModificar);
        etStock=(EditText) view.findViewById(R.id.etStockModificar);
        spCategoria=(Spinner)view.findViewById(R.id.spCategoriaModificar);
        iniciarSpinnerCategorias();

        btnBuscar=(Button)view.findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarArticulo();
            }
        });

        btnModificar=(Button)view.findViewById(R.id.btnModificar);
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarArticulo();
            }
        });

        return view;
    }

    private void iniciarSpinnerCategorias(){
        DataListarCategorias task= new DataListarCategorias(getContext(),spCategoria);
        task.execute();
    }

    private void buscarArticulo(){
        Integer id=Integer.parseInt(etId.getText().toString());
        DataBuscarArticulo task= new DataBuscarArticulo(getContext(),etNombre,etStock,spCategoria);
        task.execute(id);
    }

    private void modificarArticulo(){

        Integer id=Integer.parseInt(this.etId.getText().toString());
        String nombre=this.etNombre.getText().toString();
        Integer stock=Integer.parseInt(this.etStock.getText().toString());
        Categoria categoria= (Categoria)this.spCategoria.getSelectedItem();
        Articulo articulo=new Articulo(id,categoria,nombre,stock);

        DataModificarArticulo task = new DataModificarArticulo(getContext(),articulo);
        task.execute();
    }
}