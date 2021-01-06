package br.edu.ifsp.scl.ads.s5.pdm.dices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    }

    public void onClick(View view) {
        if(view == activityMainBinding.jogarBt){
            Integer resultado = new Random(System.currentTimeMillis()).nextInt(6) + 1;
            activityMainBinding.resultadoTv.setText(resultado.toString());

            String dice1Imagem = "dice_" + resultado;
            activityMainBinding.dice1Iv.setImageResource(
                    getResources().getIdentifier(dice1Imagem,"drawable", getPackageName())
            );
        }
    }
}