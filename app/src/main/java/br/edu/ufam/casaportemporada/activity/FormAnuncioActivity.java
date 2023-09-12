package br.edu.ufam.casaportemporada.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
import java.util.List;

import br.edu.ufam.casaportemporada.databinding.ActivityFormAnuncioBinding;
import br.edu.ufam.casaportemporada.helper.FirebaseHelper;
import br.edu.ufam.casaportemporada.model.Anuncio;

public class FormAnuncioActivity extends AppCompatActivity {

    private ActivityFormAnuncioBinding binding;
    private String caminhoImagem;
    private Bitmap imagem;
    private static final int REQUES_GALERIA = 100;
    private Anuncio anuncio= new Anuncio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormAnuncioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configCliques();
        iniciaComponentes();
    }

    public void verificaPermissaoGaleria(View view){
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                abrirGaleria();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(FormAnuncioActivity.this,"Permissão Negada", Toast.LENGTH_LONG).show();
            }
        };

        showDialogPermission(permissionListener, new String[]{Manifest.permission.READ_MEDIA_IMAGES});
    }

    private void showDialogPermission(PermissionListener listener, String[] permissoes) {
        TedPermission.create()
                .setPermissionListener(listener)
                .setDeniedTitle("Permissões Negadas")
                .setDeniedMessage("Você negou as permissões para acessar a galeria do dispositivo, deseja permitir?")
                .setDeniedCloseButtonText("Não")
                .setGotoSettingButtonText("Sim")
                .setPermissions(permissoes)
                .check();


    }

    private void abrirGaleria(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUES_GALERIA);
    }

    private void configCliques(){
        binding.include2.ibSalvar.setOnClickListener(v-> validaDados());
    }

    private void salvarImagemAnuncio(){
        StorageReference storageReference = FirebaseHelper.getStorageReference()
                .child("imagens")
                .child("anuncios")
                .child(anuncio.getId()+".jpeg");

        UploadTask uploadTask = storageReference.putFile(Uri.parse(caminhoImagem));
        uploadTask.addOnSuccessListener(taskSnapshot ->storageReference.getDownloadUrl().addOnCompleteListener(task->{
            String urlImagem = task.getResult().toString();
            anuncio.setUrlImagem(urlImagem);
            anuncio.salvar();

           // finish();

                })).addOnFailureListener(e-> Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show());

    }


    private void iniciaComponentes(){
        binding.include2.textTitulo.setText("Form Anúncio");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == REQUES_GALERIA){

                assert data != null;
                Uri localImagemSelecionada = data.getData();
                caminhoImagem = localImagemSelecionada.toString();

                if(Build.VERSION.SDK_INT<28){
                    try {
                        imagem = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(),localImagemSelecionada);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    ImageDecoder.Source source = ImageDecoder.createSource(getBaseContext().getContentResolver(), localImagemSelecionada);
                    try {
                        imagem = ImageDecoder.decodeBitmap(source);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                binding.imgAnuncio.setImageBitmap(imagem);
            }
        }
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

                            if(anuncio == null) anuncio = new Anuncio();
                           anuncio.setTitulo(titulo);
                            anuncio.setDescricao(descricao);
                            anuncio.setQuartos(quartos);
                            anuncio.setGaragem(garagem);
                            anuncio.setStatus(binding.cbStatusF.isChecked());

                            if(caminhoImagem!=null){
                                salvarImagemAnuncio();
                            }else {
                                if (anuncio.getUrlImagem() != null) {
                                    anuncio.salvar();
                                } else {
                                    Toast.makeText(this, "Selecione uma imagem para o anúncio.", Toast.LENGTH_SHORT).show();
                                }
                            }
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