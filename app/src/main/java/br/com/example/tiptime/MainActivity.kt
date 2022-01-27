package br.com.example.tiptime

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import br.com.example.tiptime.R.id.option_twenty_percent
import br.com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import android.util.Log.d as d1

class MainActivity : AppCompatActivity() {
    //lateinit faz com que inicie a variavel antes de usá-la
    lateinit var binding: ActivityMainBinding
    var tipPercetage: Double = 0.0
    var tip:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inicialização do objeto binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        //take the typed number
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDouble()

        //inicialize the selected radio button
        val selectedId = binding.tipOptions.checkedRadioButtonId

        defineTipPercetage(selectedId)
        calculateTip(cost)

        //format the tip to currency
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        //define the tip result text
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }

    private fun calculateTip(cost: Double): Double {
        tip = tipPercetage * cost
        return tip
    }

    private fun defineTipPercetage(selectedId: Int): Double {
        if (selectedId == option_twenty_percent) {
            tipPercetage = 0.2
        } else if (selectedId == R.id.option_eighteen_percent) {
            tipPercetage = 0.18
        } else {
            tipPercetage = 0.15
        }
        return tipPercetage
    }
}