package br.com.boa50.kinkake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.boa50.kinkake.application.AppListeners;
import br.com.boa50.kinkake.application.ConfiguracaoFirebase;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        ConfiguracaoFirebase.getReferenciaMusica().addListenerForSingleValueEvent(AppListeners.getListenerPreencherMusicas(intent, SplashActivity.this));
        ConfiguracaoFirebase.getReferenciaPessoa().addListenerForSingleValueEvent(AppListeners.getListenerPreencherPessoas());
    }
}
