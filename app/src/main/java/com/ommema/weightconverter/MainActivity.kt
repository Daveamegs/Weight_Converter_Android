package com.ommema.weightconverter

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ommema.weightconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    Data Binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        Holder for Weight Converter
        var convertedWeight : Double = 0.0

//        Click Listener for Exchanging Units and Figures
        binding.exchangeButton.setOnClickListener {
//            Get the Values of the Views
            val enterMassUnit = binding.massUnit.text
            val convertedMassUnit = binding.convertedWeightUnit.text
            val enterMassValue = binding.enterMassInKg.text
            val convertedMassValue = binding.displayWeightConverted.text

//            Interchange the Values
            binding.convertedWeightUnit.text = enterMassUnit.toString()
            binding.massUnit.text = convertedMassUnit.toString()
            binding.enterMassInKg.setText(convertedMassValue.toString())
            binding.displayWeightConverted.text = enterMassValue.toString()
        }

//        Click Listener for conversion
        binding.convertButton.setOnClickListener {

            val enterMassInKg = binding.enterMassInKg.text.toString().toDouble()
            var inputMassUnit = binding.massUnit.text

//            Convert from Kilograms to Pounds based on String Conditions
            if (inputMassUnit.toString() == "Kilograms") {
                convertedWeight = convertKgToPounds(enterMassInKg)
                binding.displayWeightConverted.text = convertedWeight.toString()

//                Convert from pounds to kilograms based on the string condition
            }else if (inputMassUnit.toString() == "Pounds"){
                convertedWeight = convertPoundsToKg(enterMassInKg)
                binding.displayWeightConverted.text = convertedWeight.toString()
            }
        }


    }

    private fun convertKgToPounds(mass: Double, baseNum: Double = 2.205): Double {
        return mass * baseNum
    }

    private fun convertPoundsToKg(mass: Double, baseNum: Double = 2.205) : Double {
        return mass / baseNum
    }
}