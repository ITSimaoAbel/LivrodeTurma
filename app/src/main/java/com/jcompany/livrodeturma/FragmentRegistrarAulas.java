package com.jcompany.livrodeturma;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentRegistrarAulas extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_registraraulas, container , false);


        TableLayout tableLayout = view.findViewById(R.id.tablelayout);
        Database sqllite = new Database(getContext());
        Cursor data = sqllite.getAllAulas();
        while (data.moveToNext()) {
            //anolectivo.add(data.getString(data.getColumnIndex("nome")));
            try {

                TableRow row = new TableRow(getContext());
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView textView1 = new TextView(getContext());
                textView1.setText(data.getString(data.getColumnIndexOrThrow("nome")));
                TextView textView2 = new TextView(getContext());
                textView2.setText(data.getString(data.getColumnIndexOrThrow("sumario")));
                TextView textView3= new TextView(getContext());
                textView3.setText(data.getString(data.getColumnIndexOrThrow("data")));
                TextView textView4 = new TextView(getContext());
                textView4.setText(data.getString(data.getColumnIndexOrThrow("bibliografia")));

                row.addView(textView1);
                row.addView(textView2);
                row.addView(textView3);
                row.addView(textView4);
                tableLayout.addView(row, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));


            }catch (Exception e){
                Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
            }

        }


        return view;
    }
}
