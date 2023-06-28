    package br.edu.ufam.casaportemporada.activity.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ufam.casaportemporada.R;
import br.edu.ufam.casaportemporada.activity.MainActivity;
import br.edu.ufam.casaportemporada.databinding.ActivityCriarContaBinding;
import br.edu.ufam.casaportemporada.helper.FirebaseHelper;
import br.edu.ufam.casaportemporada.model.Usuario;

    public class CriarContaActivity extends AppCompatActivity {

        private ActivityCriarContaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCriarContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configCliques();
    }


    public void validaDados(){
        String nome = binding.editNomeC.getText().toString();
        String email = binding.editEmailC.getText().toString();
        String telefone = binding.editTelefoneC.getText().toString();
        String senha = binding.editSenhaC.getText().toString();

        if(!nome.isEmpty()){
            if(!email.isEmpty()){
                if(!telefone.isEmpty()){
                    if (!senha.isEmpty()){

                        binding.progressBarC.setVisibility(View.VISIBLE);

                        Usuario usuario = new Usuario();
                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setTelefone(telefone);
                        usuario.setSenha(senha);

                        cadastrarUsuario(usuario);


                    }else{
                        binding.editSenhaC.requestFocus();
                        binding.editSenhaC.setError("Informe uma senha");
                    }
                }else{
                    binding.editTelefoneC.requestFocus();
                    binding.editTelefoneC.setError("Informe seu telefone");
                }
            }else{
                binding.editEmailC.requestFocus();
                binding.editEmailC.setError("Informe seu e-mail");
            }
        }else{
            binding.editNomeC.requestFocus();
            binding.editNomeC.setError("Informe seu nome");
        }
    }

    private void cadastrarUsuario(Usuario usuario){
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(task ->{
            if(task.isSuccessful()){

                String idUser = task.getResult().getUser().getUid();
                usuario.setId(idUser);
                usuario.salvar();
                finish();
                startActivity(new Intent(this, MainActivity.class));

            }else{
                String error = task.getException().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
                });
    }
    private void configCliques(){
        findViewById(R.id.ib_voltar).setOnClickListener(view -> finish());
        binding.btnCadastro.setOnClickListener(view -> validaDados());
    }


}