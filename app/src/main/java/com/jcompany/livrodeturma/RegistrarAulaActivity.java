package com.jcompany.livrodeturma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegistrarAulaActivity extends AppCompatActivity {

    EditText tdata, tsumario, tbibliografia;
    Intent intent;

    String id_professor;
    String id_disciplina;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_aula);


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        String currentDate = sdf.format(new Date());

        tdata = findViewById(R.id.tdata);
        tsumario = findViewById(R.id.tsumario);
        tbibliografia = findViewById(R.id.tbibliografia);

        intent = getIntent();

        id_professor = intent.getStringExtra("professor");
        id_disciplina = intent.getStringExtra("disciplina");
        nome = intent.getStringExtra("nome");

        tdata.setText(currentDate);
    }

    public void registrar(View view) {
        String data = tdata.getText().toString();
        String sumario = tsumario.getText().toString();
        String bibliografia = tbibliografia.getText().toString();

        if(data.isEmpty() || sumario.isEmpty()){
            Toast.makeText(this, "Informe os dados", Toast.LENGTH_SHORT).show();
        }else{
            Database sqllite = new Database(RegistrarAulaActivity.this);

            try {
                long rg= sqllite.RegisterAula(id_professor, data, sumario, bibliografia);

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