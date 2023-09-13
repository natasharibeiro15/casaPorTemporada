package br.edu.ufam.casaportemporada.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.edu.ufam.casaportemporada.R;
import br.edu.ufam.casaportemporada.databinding.ActivityMainBinding;
import br.edu.ufam.casaportemporada.databinding.ActivityMeusAnunciosBinding;
import br.edu.ufam.casaportemporada.databinding.ActivityMinhaContaBinding;

public class MinhaContaActivity extends AppCompatActivity {

    private ActivityMinhaContaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMinhaContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}