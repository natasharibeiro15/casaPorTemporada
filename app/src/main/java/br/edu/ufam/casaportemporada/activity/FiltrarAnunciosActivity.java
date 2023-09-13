package br.edu.ufam.casaportemporada.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.edu.ufam.casaportemporada.R;
import br.edu.ufam.casaportemporada.databinding.ActivityFiltrarAnunciosBinding;
import br.edu.ufam.casaportemporada.databinding.ActivityMainBinding;

public class FiltrarAnunciosActivity extends AppCompatActivity {

    private ActivityFiltrarAnunciosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFiltrarAnunciosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}