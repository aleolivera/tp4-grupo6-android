package com.example.tp4_grupo6.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp4_grupo6.entidades.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataListarCategorias extends AsyncTask<String,Void,String> {

    private Context context;
    private Spinner spCategorias;
    private ArrayList<Categoria>listaCategoria;

    public DataListarCategorias(Context context,Spinner spinner){
        this.context=context;
        this.spCategorias=spinner;
        this.listaCategoria=new ArrayList<Categoria>();
    }

    @Override
    protected String doInBackground(String... strings) {
        String response="";

        String query="SELECT * FROM categoria";
        try
        {
            Class.forName(DataDB.DRIVER);
            Connection con= DriverManager.getConnection(DataDB.URLMYSQL,DataDB.USER,DataDB.PASS);
            Statement statement= con.createStatement();
            ResultSet resultSet= statement.executeQuery(query);

            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String descripcion=resultSet.getString("descripcion");
                listaCategoria.add(new Categoria(id,descripcion));
            }

            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response="Error en la conexion";
        }
        return response;
    }
    @Override
    protected void onPostExecute(String response){
        if(response!="")
            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
        else{
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(context, android.R.layout.simple_spinner_item,listaCategoria);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spCategorias.setAdapter(adapter);
        }
    }
}
