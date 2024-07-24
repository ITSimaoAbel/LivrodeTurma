package com.jcompany.livrodeturma;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrarDisciplinaActivity extends AppCompatActivity {

    Spinner spinneranolectivo;
    EditText tnome, tdescricao;
    ArrayList<String> anolectivo = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_disciplina);

        spinneranolectivo = findViewById(R.id.spinneranolectivo);
        tnome = findViewById(R.id.tnome);
        tdescricao = findViewById(R.id.tdescricao);

        Database sqllite = new Database(RegistrarDisciplinaActivity.this);
        Cursor data = sqllite.getAllAnoLectivo();
        while (data.moveToNext()) {
            //anolectivo.add(data.getString(data.getColumnIndex("nome")));
            try {
                anolectivo.add(data.getString(data.getColumnIndexOrThrow("anolectivo")));
            }catch (Exception e){
                Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
            }

        }

        //ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(RegistrarDisciplinaActivity.this, , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //createFromResource.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, anolectivo);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinneranolectivo.setAdapter((SpinnerAdapter) arrayAdapter);

    }




    public void registrar(View view) {

        int id = spinneranolectivo.getSelectedItemPosition() + 1;
        String nome = tnome.getText().toString();
        String descricao = tdescricao.getText().toString();

       // Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();
        if(nome.isEmpty()){
            Toast.makeText(this, "Informe o Nome da disciplina", Toast.LENGTH_SHORT).show();
        }else{
            Database sqllite = new Database(RegistrarDisciplinaActivity.this);

            try {
                long rg= sqllite.RegisterDisciplina(String.valueOf(id), nome, descricao);

                if(rg == 1){
                    Toast.makeText(this, rg+" Registrado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(this, " Falha ao tentar registrar", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(this, e+"", Toast.LENGTH_LONG).show();
            }

        }

    }
}