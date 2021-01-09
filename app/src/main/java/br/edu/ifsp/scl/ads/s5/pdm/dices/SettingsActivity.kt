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

        settings = intent.getParcelableExtra(MainActivity.EXTRA_SETTINGS) ?: Settings(false, 6)
        numOfDicesRG.check(if(settings.doubleDices) R.id.twoDicesRb else R.id.oneDiceRb)
        setNumberOfFaces(settings.numFaces)
    }

    fun setNumberOfFaces(value: Int){
        when (value) {
            1 -> numOfFacesRG.check(R.id.oneFaceRb)
            2 -> numOfFacesRG.check(R.id.twoFacesRb)
            3 -> numOfFacesRG.check(R.id.threeFacesRb)
            4 -> numOfFacesRG.check(R.id.fourFacesRb)
            5 -> numOfFacesRG.check(R.id.fiveFacesRb)
            6 -> numOfFacesRG.check(R.id.sixFacesRb)
            else -> {
                numOfFacesRG.check(R.id.sixFacesRb)
            }
        }
    }

    fun selectNumberOfFacesChecked() : Int {
        var num : Int = when (numOfFacesRG.checkedRadioButtonId) {
            R.id.oneFaceRb -> 1
            R.id.twoFacesRb -> 2
            R.id.threeFacesRb -> 3
            R.id.fourFacesRb -> 4
            R.id.fiveFacesRb -> 5
            R.id.sixFacesRb -> 6
            else -> 6
        }
        println("Dices - checkedRBId: " + numOfFacesRG.checkedRadioButtonId.toString())
        println("Dices - NumResult: $num")
        return num
    }

    fun onClick(view: View){
        if (view.id == R.id.salvarBt){
            settings.doubleDices = twoDicesRb.isChecked
            settings.numFaces = selectNumberOfFacesChecked()

            var resultIntent = Intent()
            resultIntent.putExtra(MainActivity.EXTRA_SETTINGS, settings)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }
}