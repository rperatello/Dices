package br.edu.ifsp.scl.ads.s5.pdm.dices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Random;

import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private final String SETTINGS = "SETTINGS";
    private final String DOUBLE_DICES = "DOUBLE_DICES";
    private final String NUMBER_OF_FACES = "NUMBER_OF_FACES";
    public static final String EXTRA_SETTINGS = "EXTRA_SETTINGS";
    private final int SETTINGS_REQUEST_CODE = 0;

    private Settings settings = new Settings(false, 6);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(DOUBLE_DICES, settings.getDoubleDices());
        outState.putInt(NUMBER_OF_FACES, settings.getNumFaces());

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(settings != null){
            settings.setNumFaces(savedInstanceState.getInt(NUMBER_OF_FACES));
            if (savedInstanceState.getBoolean(DOUBLE_DICES)){
                settings.setDoubleDices(savedInstanceState.getBoolean(DOUBLE_DICES));
                findViewById(R.id.dice2Iv).setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settingsMi:
                Intent settingsIntent = new Intent(SETTINGS);
                settingsIntent.putExtra(EXTRA_SETTINGS, settings);
                startActivityForResult(settingsIntent, SETTINGS_REQUEST_CODE);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            settings = data.getParcelableExtra(EXTRA_SETTINGS);
            if (settings != null && settings.getDoubleDices()) {
                findViewById(R.id.dice2Iv).setVisibility(View.VISIBLE);
            }
            else {
                findViewById(R.id.dice2Iv).setVisibility(View.GONE);
            }
        }
    }

    public void onClick(View view) {
        if(view == activityMainBinding.playBt){
            Integer result1 = new Random(System.currentTimeMillis()).nextInt(settings.getNumFaces()) + 1;
            Log.v(getString(R.string.app_name), "Dado 1: " + result1.toString());
            activityMainBinding.resultTv.setText(result1.toString());

            String dice1Image = "dice_" + result1;
            activityMainBinding.dice1Iv.setImageResource(
                    getResources().getIdentifier(dice1Image,"drawable", getPackageName())
            );

            if(settings != null && settings.getDoubleDices()){
                Integer result2 = new Random(System.currentTimeMillis()).nextInt(settings.getNumFaces()) + 1;
                Log.v(getString(R.string.app_name), "Dado 2: " + result2.toString());
                activityMainBinding.resultTv.setText(activityMainBinding.resultTv.getText() + " " + result2.toString());

                String dice2Image = "dice_" + result2;
                activityMainBinding.dice2Iv.setImageResource(
                        getResources().getIdentifier(dice2Image, "drawable", getPackageName())
                );
            }

        }
    }
}