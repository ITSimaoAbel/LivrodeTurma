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

public class RegistrarProfessorActivity extends AppCompatActivity {

    Spinner spinnerdisciplina;
    EditText tnome, tusuario, tsenha;
    ArrayList<String> disciplina = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_professor);

        spinnerdisciplina = findViewById(R.id.spinnerdisciplina);
        tnome = findViewById(R.id.tnome);
        tusuario = findViewById(R.id.temail);
        tsenha = findViewById(R.id.tsenha);

        Database sqllite = new Database(RegistrarProfessorActivity.this);
        Cursor data = sqllite.getAllDisciplina();
        while (data.moveToNext()) {
            //anolectivo.add(data.getString(data.getColumnIndex("nome")));
            try {
                disciplina.add(data.getString(data.getColumnIndexOrThrow("nome")));
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, disciplina);
                arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                spinnerdisciplina.setAdapter((SpinnerAdapter) arrayAdapter);
            }catch (Exception e){
                Toast.makeText(this, e+"", Toast.LENGTH_LONG).show();
            }

        }

        //ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(RegistrarDisciplinaActivity.this, , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //createFromResource.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);



    }

    public void registrar(View view) {

        int id = spinnerdisciplina.getSelectedItemPosition() + 1;
        String nome = tnome.getText().toString();
        String usuario = tusuario.getText().toString();
        String senha = tsenha.getText().toString();

        // Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();
        if(nome.isEmpty()){
            Toast.makeText(this, "Informe o Nome da disciplina", Toast.LENGTH_SHORT).show();
        }else{
            Database sqllite = new Database(RegistrarProfessorActivity.this);

            try {
                long rg= sqllite.RegisterProfessor(String.valueOf(id), nome, usuario, senha);

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