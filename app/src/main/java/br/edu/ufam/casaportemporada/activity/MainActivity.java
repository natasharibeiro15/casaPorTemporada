package br.edu.ufam.casaportemporada.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.PopupMenu;

import br.edu.ufam.casaportemporada.R;
import br.edu.ufam.casaportemporada.activity.autenticacao.LoginActivity;
import br.edu.ufam.casaportemporada.databinding.ActivityFormAnuncioBinding;
import br.edu.ufam.casaportemporada.databinding.ActivityMainBinding;
import br.edu.ufam.casaportemporada.helper.FirebaseHelper;

//Inicio 15/03/2023
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configCliques();
    }

    private void configCliques(){

        binding.layoutMain.ibMenu.setOnClickListener(view->{
            PopupMenu popupMenu = new PopupMenu(this, binding.layoutMain.ibMenu);
            popupMenu.getMenuInflater().inflate(R.menu.menu_home, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(menuItem->{
                if(menuItem.getItemId()==R.id.menu_filtrar){
                    startActivity(new Intent(this, FiltrarAnunciosActivity.class));
                }else if(menuItem.getItemId()==R.id.menu_meus_anuncios){
                    if(FirebaseHelper.getAutenticado()){
                        startActivity(new Intent(this, MeusAnunciosActivity.class));
                    }else{
                        showDialogLogin();
                    }

                }else{
                    if(FirebaseHelper.getAutenticado()){
                        startActivity(new Intent(this, MinhaContaActivity.class));
                    }else{
                       showDialogLogin();
                    }

                }
                return true;
            });

            popupMenu.show();
        });


    }

    private void showDialogLogin(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Autenticação");
        builder.setMessage("Você não está autenticado no app, deseja fazer isso agora?");
        builder.setCancelable(false);
        builder.setNegativeButton("Não",(dialog,which)->dialog.dismiss());
        builder.setPositiveButton("Sim",(dialog,which)->{
            startActivity(new Intent(this, LoginActivity.class));
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}