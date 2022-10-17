package com.example.tp4_grupo6.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp4_grupo6.entidades.Articulo;
import com.example.tp4_grupo6.entidades.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataListarArticulos extends AsyncTask<String,Void,String> {

    private Context context;
    private ListView articuloLV;
    private ArrayList<Articulo> listaArticulo;


    public DataListarArticulos(Context context, ListView ListViewArticulos){
        this.context=context;
        this.articuloLV = ListViewArticulos;
        this.listaArticulo = new ArrayList<Articulo>();
    }

    @Override
    protected String doInBackground(String... strings) {
        String response="";

        String query="SELECT a.Id, a.nombre, a.Stock, a.idCategoria, c.descripcion FROM " +
                "articulo a join " +
                "categoria c on c.id = a.IdCategoria";
        try
        {
            Class.forName(DataDB.DRIVER);
            Connection con= DriverManager.getConnection(DataDB.URLMYSQL,DataDB.USER,DataDB.PASS);
            Statement statement= con.createStatement();
            ResultSet resultSet= statement.executeQuery(query);

            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String nombre=resultSet.getString("nombre");
                int stock =resultSet.getInt("stock");

                int idcat=resultSet.getInt("idCategoria");
                String categoriaDescripcion = resultSet.getString("Descripcion");

                //Cargo la cat y el articulo
                Categoria cata = new Categoria(idcat,categoriaDescripcion);
                Articulo Art = new Articulo(id,cata,nombre,stock);

                listaArticulo.add(Art);
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
            ArrayAdapter<Articulo> adapter = new ArrayAdapter<Articulo>(context, android.R.layout.simple_list_item_1, listaArticulo);
            articuloLV.setAdapter(adapter);
            //Toast para que se muestren los datos del art
            articuloLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                  @Override
                                                  public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l)
                                                  {
                                                      String Info = "Articulo id: " + listaArticulo.get(pos).getId()+ "\n";
                                                      Info += "Nombre: "+ listaArticulo.get(pos).getNombre()+ "\n";
                                                      Info += "Categoria: "+ listaArticulo.get(pos).getCategoria()+ "\n";
                                                      Info += "Stock: "+ listaArticulo.get(pos).getStock()+ "\n";

                                                      Toast.makeText(context, Info,Toast.LENGTH_SHORT).show();
                                                  }

                                              }
            ); ;
        }
    }


}
