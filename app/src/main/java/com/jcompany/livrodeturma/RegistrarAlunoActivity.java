package com.jcompany.livrodeturma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarAlunoActivity extends AppCompatActivity {

    EditText tsenha, tsenhanovamente, temail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_aluno);
        tsenha = findViewById(R.id.tsenha);
        tsenhanovamente = findViewById(R.id.tsenhanovamente);
        temail = findViewById(R.id.temail);
    }

    public void goback(View view) {
        finish();
    }

    public void registrar(View view) {
        String senha = tsenha.getText().toString();
        String senhanovamente = tsenhanovamente.getText().toString();
        String email = temail.getText().toString();

        if(senha.isEmpty() || senhanovamente.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Preencha os dados", Toast.LENGTH_SHORT).show();
        }else{
            Database sqllite = new Database(RegistrarAlunoActivity.this);

            try {
                long rg= sqllite.RegistrarAluno(email, senha);

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