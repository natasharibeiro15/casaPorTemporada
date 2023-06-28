package br.edu.ufam.casaportemporada.activity.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.edu.ufam.casaportemporada.R;
import br.edu.ufam.casaportemporada.activity.MainActivity;
import br.edu.ufam.casaportemporada.databinding.ActivityLoginBinding;
import br.edu.ufam.casaportemporada.helper.FirebaseHelper;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configCliques();
    }


    private void configCliques(){
       binding.textCriarConta.setOnClickListener(view ->
                startActivity(new Intent(this, CriarContaActivity.class)) );

        binding.textRecuperaConta.setOnClickListener(view ->
                startActivity(new Intent(this, RecuperarContaActivity.class)));
    }

    public void validaDados(View view){
        String email = binding.editEmailL.getText().toString();
        String senha = binding.editSenhaL.getText().toString();

        if(!email.isEmpty()){
            if(!senha.isEmpty()){
                binding.progressBarL.setVisibility(View.VISIBLE);
                logar(email,senha);
            }else{
                binding.editSenhaL.requestFocus();
               binding.editSenhaL.setError("Informe sua senha");
            }
        }else{
            binding.editEmailL.requestFocus();
            binding.editEmailL.setError("Informe seu e-mail");
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

}