package com.marshlandbiomes101.interestcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marshlandbiomes101.interestcalculator.databinding.ActivityMainBinding
//import kotlin.math.pow
//import android.widget.EditText
//import android.widget.Button
//import androidx.activity.enableEdgeToEdge
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import android.util.Log

//@Suppress("IMPLICIT_CAST_TO_ANY")

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submit.setOnClickListener{
            calculateSimpleInterest(1)
        }
        
        binding.submit.setOnClickListener {
            Toast.makeText(this, "Please input values", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateSimpleInterest(operation: Int) {
        val principalAmountCheck = binding.principalAmount.text.toString()
        val yearsCheck = binding.years.text.toString()
        val interestRateCheck = binding.interestRate.text.toString()

        if (principalAmountCheck.isEmpty() || yearsCheck.isEmpty() || interestRateCheck.isEmpty()) {
            Toast.makeText(this, "Please input values", Toast.LENGTH_SHORT).show()
            return
        }

        val principalAmount = principalAmountCheck.toIntOrNull()
        val years = yearsCheck.toIntOrNull()
        val interestRate = interestRateCheck.toIntOrNull()

        if (principalAmount != null && years != null && interestRate != null) {
            val result = when (operation) {
                1 -> principalAmount * years * interestRate
//                2 -> principalAmount * (Math.pow(1))
                else -> 0
            }

        } else {
            Toast.makeText(this, "Please re-input your values", Toast.LENGTH_SHORT).show()
            return
        }
    }
}


    //        val years = findViewById<EditText>(R.id.years)

    //      enableEdgeToEdge()
//            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets

//        val btnSubmit = findViewById<Button>(R.id.submit)
//        btnSubmit.setOnClickListener {
//            print("message")
//            Toast.makeText(baseContext,"Calculating total amount", Toast.LENGTH_SHORT).show()
//            calculateSimpleInterest()
//        }
//    }
    
