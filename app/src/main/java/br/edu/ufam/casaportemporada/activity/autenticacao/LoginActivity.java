package br.edu.ufam.casaportemporada.activity.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import br.edu.ufam.casaportemporada.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        configCliques();
    }


    private void configCliques(){
        findViewById(R.id.text_criar_conta).setOnClickListener(view ->
                startActivity(new Intent(this, CriarContaActivity.class)) );

        findViewById(R.id.text_recupera_conta).setOnClickListener(view ->
                startActivity(new Intent(this, RecuperarContaActivity.class)));
    }
}