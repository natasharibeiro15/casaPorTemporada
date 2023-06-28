package br.edu.ufam.casaportemporada.activity.autenticacao;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import br.edu.ufam.casaportemporada.R;
import br.edu.ufam.casaportemporada.databinding.ActivityRecuperarContaBinding;
import br.edu.ufam.casaportemporada.helper.FirebaseHelper;

public class RecuperarContaActivity extends AppCompatActivity {

    private ActivityRecuperarContaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperarContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configCliques();
        iniciaComponentes();
    }

    public void validaDados(){
        String email = binding.editEmailR.getText().toString();
        if(!email.isEmpty()){
            binding.progressBar2.setVisibility(View.VISIBLE);
            recuperaSenha(email);
        }else{
            binding.editEmailR.requestFocus();
            binding.editEmailR.setError("Informe seu e-mail");
        }
    }

    private void recuperaSenha(String email){
        FirebaseHelper.getAuth().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(this, "E-mail enviado com sucesso!", Toast.LENGTH_LONG).show();
                binding.progressBar2.setVisibility(View.GONE);
                new Handler(getMainLooper()).postDelayed(() -> {
                    finish();
                    startActivity(new Intent(this,LoginActivity.class));
                },3000);

            }else{
                String error = task.getException().getMessage();
                Toast.makeText(this,error, Toast.LENGTH_LONG).show();
            }
        });
    }




    private void configCliques(){
        findViewById(R.id.ib_voltar).setOnClickListener(view -> finish());
        binding.btnRecuperaSenha.setOnClickListener(v-> validaDados());
    }

    private void iniciaComponentes(){
        binding.include.textTitulo.setText("Recuperar Conta");
    }
}