package br.edu.ufam.casaportemporada.activity.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.edu.ufam.casaportemporada.R;
import br.edu.ufam.casaportemporada.activity.MainActivity;
import br.edu.ufam.casaportemporada.helper.FirebaseHelper;

public class LoginActivity extends AppCompatActivity {


        private EditText edit_email, edit_senha;
        private ProgressBar progressBar;

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

    public void validaDados(View view){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        if(!email.isEmpty()){
            if(!senha.isEmpty()){
                progressBar.setVisibility(View.VISIBLE);
                logar(email,senha);
            }else{

            }
        }else{

        }

    }

    private void logar(String email, String senha){
        FirebaseHelper.getAuth().signInWithEmailAndPassword(
          email,senha
        ).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }else{
                String error = task.getException().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void iniciaComponentes(){
        edit_email = findViewById(R.id.edit_emailL);
        edit_senha = findViewById(R.id.edit_senhaL);
        progressBar = findViewById(R.id.progressBarL);
    }
}