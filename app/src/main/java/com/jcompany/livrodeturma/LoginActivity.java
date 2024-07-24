package com.jcompany.livrodeturma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText temail, tsenha;
   // List<String> id_professores;
    //List<String> id_professores;
    //List<String> id_professores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        temail = findViewById(R.id.temail);
        tsenha = findViewById(R.id.tsenha);
    }

    public void entrar(View view) {

        String email = temail.getText().toString();
        String senha = tsenha.getText().toString();

        if(email.isEmpty() || senha.isEmpty() ){
            Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }else{

            if(email.equals("Admin") && senha.equals("Admin123")){
                startActivity(new Intent(LoginActivity.this, MenuAdmActivity.class));
                finish();
            }else{
                //senao for admin

                try {

                    Database sqlLite = new Database(this);
                    Cursor data = sqlLite.getAllProfessor();
                    while (data.moveToNext()) {

                        String tempusuario = data.getString(data.getColumnIndexOrThrow("usuario"));
                        String tempsenha = data.getString(data.getColumnIndexOrThrow("senha"));

                        if(tempusuario.equals(email) && tempsenha.equals(senha)){
                            Intent intent = new Intent(this, MenuProfessorActivity.class);
                            intent.putExtra("professor", data.getString(data.getColumnIndexOrThrow("id_professor")));
                            intent.putExtra("disciplina", data.getString(data.getColumnIndexOrThrow("id_disciplina")));
                            intent.putExtra("nome", data.getString(data.getColumnIndexOrThrow("nome")));
                            startActivity(intent);
                            finish();
                        }
                    }

                   // Database sqlLite1 = new Database(this);
                    Cursor data1 = sqlLite.getAllAluno();
                    while (data1.moveToNext()) {

                        String tempusuario1 = data1.getString(data1.getColumnIndexOrThrow("usuario"));
                        String tempsenha1 = data1.getString(data1.getColumnIndexOrThrow("senha"));

                        if(tempusuario1.equals(email) && tempsenha1.equals(senha)){
                            Intent intent = new Intent(this, MenuAlunoActivity.class);
                            intent.putExtra("aluno", data1.getString(data1.getColumnIndexOrThrow("id_aluno")));
                            // intent.putExtra("disciplina", data.getString(data.getColumnIndexOrThrow("id_disciplina")));
                            startActivity(intent);
                            finish();
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
                }



                Toast.makeText(this, "Usuario ou senha eradas", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void registraraluno(View view) {

        startActivity(new Intent(this, RegistrarAlunoActivity.class));
    }
}