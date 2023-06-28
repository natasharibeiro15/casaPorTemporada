package br.edu.ufam.casaportemporada.activity;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ufam.casaportemporada.databinding.ActivityFormAnuncioBinding;
import br.edu.ufam.casaportemporada.model.Produto;

public class FormAnuncioActivity extends AppCompatActivity {

    private ActivityFormAnuncioBinding binding;
    private String caminhoImagem;
    private Bitmap imagem;
    private static final int REQUES_GALERIA = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormAnuncioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configCliques();
        iniciaComponentes();
    }

    private void configCliques(){
        binding.include2.ibSalvar.setOnClickListener(v-> validaDados());
    }

    private void iniciaComponentes(){
        binding.include2.textTitulo.setText("Form Anúncio");
    }

    private void validaDados(){
        String titulo = binding.editTitulo.getText().toString();
        String descricao = binding.editDescricao.getText().toString();
        String quartos = binding.editQuarto.getText().toString();
        String banheiros = binding.editBanheiro.getText().toString();
        String garagem = binding.editGaragem.getText().toString();

        if (!titulo.isEmpty()){
            if(!descricao.isEmpty()){
                if(!quartos.isEmpty()){
                    if(!banheiros.isEmpty()){
                        if(!garagem.isEmpty()){

                            Produto produto = new Produto();
                            produto.setTitulo(titulo);
                            produto.setDescricao(descricao);
                            produto.setQuartos(quartos);
                            produto.setGaragem(garagem);
                            produto.setStatus(binding.cbStatusF.isChecked());

                        }else{
                         binding.editGaragem.requestFocus();
                         binding.editGaragem.setError("Informação obrigatória");
                        }
                    }else{
                      binding.editBanheiro.requestFocus();
                      binding.editBanheiro.setError("Informação obrigatória");
                    }
                }else{
                    binding.editQuarto.requestFocus();
                    binding.editQuarto.setError("Informação obrigatória");
                }
            }else{
                binding.editDescricao.requestFocus();
                binding.editDescricao.setError("Informe uma descrição");
            }
        }else{
            binding.editTitulo.requestFocus();
            binding.editTitulo.setError("Informe um título");
        }
    }
}