package com.example.tp4_grupo6.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp4_grupo6.entidades.Articulo;
import com.example.tp4_grupo6.entidades.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBuscarArticulo extends AsyncTask<Integer,Void,String> {
    private Context context;
    private EditText etNombre;
    private EditText etStock;
    private Spinner spinner;
    private Articulo articulo;

    public DataBuscarArticulo(Context context, EditText etNombre, EditText etStock, Spinner spinner) {
        this.context = context;
        this.etNombre = etNombre;
        this.etStock = etStock;
        this.spinner = spinner;
    }


    @Override
    protected String doInBackground(Integer... params) {
        String response;
        String query="SELECT * FROM articulo WHERE id="+params[0];

        try
        {
            Class.forName(DataDB.DRIVER);
            Connection con= DriverManager.getConnection(DataDB.URLMYSQL,DataDB.USER,DataDB.PASS);
            Statement statement= con.createStatement();
            ResultSet resultSet= statement.executeQuery(query);

            if(resultSet.next()){
                Categoria categoria= new Categoria();
                int id=resultSet.getInt("id");
                String nombre=resultSet.getString("nombre");
                categoria.setId(resultSet.getInt("idCategoria"));
                int stock= resultSet.getInt("stock");
                articulo= new Articulo(id,categoria,nombre,stock);
                response="Busqueda Exitosa";
            }
            else {
                con.close();
                response="No se encontro articulo";
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            response="Error de conexion";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response){
        if(articulo!=null){
            etNombre.setText(articulo.getNombre());
            etStock.setText(String.valueOf(articulo.getStock()));
            int index=(articulo.getCategoria().getId()-1);
            spinner.setSelection(index);
        }

        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
    }
}
