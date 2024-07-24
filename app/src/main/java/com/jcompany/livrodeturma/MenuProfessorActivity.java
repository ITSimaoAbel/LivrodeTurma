package com.jcompany.livrodeturma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuProfessorActivity extends AppCompatActivity {

    Intent intent;
    String id_professor;
    String id_disciplina;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_professor);

        intent = getIntent();

        id_professor = intent.getStringExtra("professor");
        id_disciplina = intent.getStringExtra("disciplina");
        nome = intent.getStringExtra("nome");

        TableLayout tableLayout = findViewById(R.id.tablelayout);
        Database sqllite = new Database(this);
        Cursor data = sqllite.getAllAulas();
        while (data.moveToNext()) {
            //anolectivo.add(data.getString(data.getColumnIndex("nome")));
            try {

                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView textView1 = new TextView(this);
                textView1.setText(data.getString(data.getColumnIndexOrThrow("nome")));
                TextView textView2 = new TextView(this);
                textView2.setText(data.getString(data.getColumnIndexOrThrow("sumario")));
                TextView textView3= new TextView(this);
                textView3.setText(data.getString(data.getColumnIndexOrThrow("data")));
                TextView textView4 = new TextView(this);
                textView4.setText(data.getString(data.getColumnIndexOrThrow("bibliografia")));

                row.addView(textView1);
                row.addView(textView2);
                row.addView(textView3);
                row.addView(textView4);
                tableLayout.addView(row, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));


            }catch (Exception e){
                Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void addaula(View view) {
       // startActivity(new);
        Intent intent1 = new Intent(this, RegistrarAulaActivity.class);
        intent1.putExtra("professor", id_professor);
        intent1.putExtra("disciplina", id_disciplina);
        intent1.putExtra("nome", nome);
        startActivity(intent1);
    }
}