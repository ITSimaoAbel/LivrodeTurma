package com.jcompany.livrodeturma;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MenuAdmActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout fragment_container;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_adm);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);

        fragment_container = findViewById(R.id.framelayout);
        textView = findViewById(R.id.theader);


        getWindow().setStatusBarColor(ContextCompat.getColor(MenuAdmActivity.this, R.color.blue));

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new FragmentRegistrarAulas()).commit();

    }

    public void opendrawer(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        Fragment selectedFragment = null;

        switch (item.getItemId()){
            case R.id.aulas:
                selectedFragment = new FragmentRegistrarAulas();
                textView.setText("Aulas");
                break;
            case R.id.anolectivo:
                selectedFragment = new FragmentRegistrarAnoLetivo();
                textView.setText("Ano Lectivo");
                break;
           // case R.id.turma:
             //   selectedFragment = new FragmentRegistrarAnoLetivo();
               // break;
            case R.id.disciplina:
                selectedFragment = new FragmentRegistrarDisciplina();
                textView.setText("Disciplinas");
                break;
            case R.id.professores:
                selectedFragment = new FragmentRegistrarProfessor();
                textView.setText("Professores");
                break;
            case R.id.alunos:
                selectedFragment = new FragmentRegistrarAluno();
                textView.setText("Alunos");
                break;
            case R.id.logout:
                startActivity(new Intent(MenuAdmActivity.this, LoginActivity.class));
                finish();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();
        drawerLayout.close();
        return true;
    }
}