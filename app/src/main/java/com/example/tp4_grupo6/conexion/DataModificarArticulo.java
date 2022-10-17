package com.example.tp4_grupo6.conexion;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tp4_grupo6.entidades.Articulo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataModificarArticulo extends AsyncTask<String,Void,String> {
    private Context context;
    private Articulo articulo;

    public DataModificarArticulo(Context context, Articulo articulo){
        this.context=context;
        this.articulo=articulo;
    }
    @Override
    protected String doInBackground(String... strings) {
        String response="";
        int filas=0;
        String query="update articulo " +
                "set nombre = '"+this.articulo.getNombre()+"'," +
                " stock ='"+this.articulo.getStock()+"',"+
                " idCategoria ='"+this.articulo.getCategoria().getId()+"' "+
                " where Id ="+this.articulo.getId()+";";
        try {
            Class.forName(DataDB.DRIVER);
            Connection con= DriverManager.getConnection(DataDB.URLMYSQL,DataDB.USER,DataDB.PASS);
            Statement statement= con.createStatement();
            filas= statement.executeUpdate(query);
            con.close();
            if(filas>0)
                response="Articulo modificado";
            else
                response="No se pudo modificar el Articulo";
        }
        catch (Exception e){
            e.printStackTrace();
            response=e.getMessage();

        }

        return response;
    }

    @Override
    protected void onPostExecute(String response){
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
    }
}
