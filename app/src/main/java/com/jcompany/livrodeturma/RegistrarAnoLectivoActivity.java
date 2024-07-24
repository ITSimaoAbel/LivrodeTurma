package com.jcompany.livrodeturma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarAnoLectivoActivity extends AppCompatActivity {

    EditText tanolectivo, tdescricao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_ano_lectivo);

        tanolectivo = findViewById(R.id.tanolectivo);
        tdescricao = findViewById(R.id.tdescricao);

    }

    public void goback(View view) {
        finish();
    }

    public void registrar(View view) {
        String anolectivo = tanolectivo.getText().toString();
        String descricao = tdescricao.getText().toString();

        if(anolectivo.isEmpty()){
            Toast.makeText(this, "Informe o Ano Lectivo", Toast.LENGTH_SHORT).show();
        }else{
            Database sqllite = new Database(RegistrarAnoLectivoActivity.this);

            try {
                long rg= sqllite.RegisterAnoLectivo(anolectivo, descricao);

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