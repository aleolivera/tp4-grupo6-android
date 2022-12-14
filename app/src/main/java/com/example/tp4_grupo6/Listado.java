package com.example.tp4_grupo6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.tp4_grupo6.conexion.DataBuscarArticulo;
import com.example.tp4_grupo6.conexion.DataListarArticulos;
import com.example.tp4_grupo6.entidades.Articulo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Listado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Listado extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Listado() {
        // Required empty public constructor
    }

    ListView ListViewArticulos;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Listado.
     */
    // TODO: Rename and change types and number of parameters
    public static Listado newInstance(String param1, String param2) {
        Listado fragment = new Listado();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_listado, container, false);

        View view = inflater.inflate(R.layout.fragment_listado, container, false);
        ListViewArticulos=(ListView)view.findViewById(R.id.ListViewArticulos);

        ArrayList<String> ListaInfo;
        ArrayList<Articulo> ListaArticulos;

        DataListarArticulos task= new DataListarArticulos(getContext(),ListViewArticulos);
        task.execute();


        return view;
    }
}