package br.edu.ufam.casaportemporada.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.PopupMenu;

import br.edu.ufam.casaportemporada.R;
import br.edu.ufam.casaportemporada.databinding.ActivityFormAnuncioBinding;
import br.edu.ufam.casaportemporada.databinding.ActivityMainBinding;

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

                }
                return true;
            });

            popupMenu.show();
        });


    }
}