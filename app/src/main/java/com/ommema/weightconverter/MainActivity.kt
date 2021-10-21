package com.ommema.weightconverter

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.ommema.weightconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //    Data Binding
    private lateinit var binding: ActivityMainBinding

    //    OnCreate Callback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //    Get Units from String Array
        val massUnits = resources.getStringArray(R.array.mass_units)

//        Holder for Weight Converter
        var convertedWeight: Double = 0.0


        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, massUnits)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.massUnitSpinner.adapter = arrayAdapter
        binding.convertedWeightUnitSpinner.adapter = arrayAdapter

        var selectedMassUnitValue = massUnits.first()
        var selectedConvertedUnitValue = massUnits.last()

        val defaultSelectedMassUnit = binding.massUnitSpinner.setSelection(0)
        val defaultSelectedConvertedUnit = binding.convertedWeightUnitSpinner.setSelection(1)


//        MassUnit Item Select Listener
        binding.massUnitSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedMassUnitValue = p0?.getItemAtPosition(p2).toString()
                    println("Mass Unit Selected: $selectedMassUnitValue")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

//        Converted Mass Unit Item Select Listener
        binding.convertedWeightUnitSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedConvertedUnitValue = p0?.getItemAtPosition(p2).toString()

                    println("Converted Unit Selected: $selectedConvertedUnitValue")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        //        Click Listener for Exchanging Units and Figures
        binding.exchangeButton.setOnClickListener {
//            Get the Values of the Views
//            Conversion Values
            val enterMassValue = binding.enterMassInKg.text
            val convertedMassValue = binding.displayWeightConverted.text

//            Get the item Position in Spinner
            val selectedMassUnitPosition = binding.massUnitSpinner.selectedItemPosition
            val selectedConvertedUnitPosition =
                binding.convertedWeightUnitSpinner.selectedItemPosition

//            Interchange the Values
//            Spinners
            binding.massUnitSpinner.setSelection(selectedConvertedUnitPosition)
            binding.convertedWeightUnitSpinner.setSelection(selectedMassUnitPosition)

//            Conversion Values
            binding.enterMassInKg.setText(convertedMassValue.toString())
            binding.displayWeightConverted.text = enterMassValue.toString()
        }

//        Click Listener for conversion
        binding.convertButton.setOnClickListener {

            val enterMassInKg = binding.enterMassInKg.text.toString().toDouble()
            val inputMassUnit = binding.massUnitSpinner.selectedItem


//            Convert from Kilograms to Pounds based on String Conditions
            if (inputMassUnit.toString() == "Kilograms") {
                convertedWeight = convertKgToPounds(enterMassInKg)
                binding.displayWeightConverted.text = convertedWeight.toString()

//                Convert from pounds to kilograms based on the string condition
            } else if (inputMassUnit.toString() == "Pounds") {
                convertedWeight = convertPoundsToKg(enterMassInKg)
                binding.displayWeightConverted.text = convertedWeight.toString()
            }

        }

    }

    private fun convertKgToPounds(mass: Double, baseNum: Double = 2.205): Double {
        return mass * baseNum
    }

    private fun convertPoundsToKg(mass: Double, baseNum: Double = 2.205): Double {
        return mass / baseNum
    }
}