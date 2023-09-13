package br.edu.ufam.casaportemporada.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.edu.ufam.casaportemporada.R;
import br.edu.ufam.casaportemporada.databinding.ActivityMainBinding;
import br.edu.ufam.casaportemporada.databinding.ActivityMeusAnunciosBinding;

public class MeusAnunciosActivity extends AppCompatActivity {
    private ActivityMeusAnunciosBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeusAnunciosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}