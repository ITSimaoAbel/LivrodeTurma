package com.jcompany.livrodeturma;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentRegistrarAnoLetivo extends Fragment {

    Button badicionar;
    TableLayout tableLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragement_registraranoletivo, container , false);

        tableLayout = view.findViewById(R.id.tablelayout);


        badicionar = view.findViewById(R.id.adicionar);

        badicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RegistrarAnoLectivoActivity.class));
            }
        });


        Database sqllite = new Database(getContext());
        Cursor data = sqllite.getAllAnoLectivo();
        while (data.moveToNext()) {
            //anolectivo.add(data.getString(data.getColumnIndex("nome")));
            try {

                TableRow row = new TableRow(getContext());
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView textView1 = new TextView(getContext());
                textView1.setText(data.getString(data.getColumnIndexOrThrow("anolectivo")));
                TextView textView2 = new TextView(getContext());
                textView2.setText(data.getString(data.getColumnIndexOrThrow("descricao")));
                row.addView(textView1);
                row.addView(textView2);
                tableLayout.addView(row, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));


            }catch (Exception e){
                Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
            }

        }



        return view;
    }
}
