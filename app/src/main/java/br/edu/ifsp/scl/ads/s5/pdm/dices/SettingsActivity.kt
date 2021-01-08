package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.title = "Configurações"

        settings = intent.getParcelableExtra(MainActivity.EXTRA_SETTINGS) ?: Settings(false)
        numOfDicesRG.check(if(settings.doubleDices) R.id.twoDicesRb else R.id.oneDiceRb)

    }

    fun onClick(view: View){
        if (view.id == R.id.salvarBt){
            settings.doubleDices = twoDicesRb.isChecked
            var resultIntent = Intent()
            resultIntent.putExtra(MainActivity.EXTRA_SETTINGS, settings)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }
}