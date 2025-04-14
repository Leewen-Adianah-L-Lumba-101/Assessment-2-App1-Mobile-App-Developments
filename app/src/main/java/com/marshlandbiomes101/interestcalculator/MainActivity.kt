package com.marshlandbiomes101.interestcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn = findViewById<Button>(R.id.submit)
        btn.setOnClickListener {
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateSimpleInterest(operation: Int) {
        val principalAmountCheck = (findViewById<EditText>(R.id.principal_amount)).toString()
        val yearsCheck = (findViewById<EditText>(R.id.years)).toString()
        val interestRateCheck = (findViewById<EditText>(R.id.interest_rate)).toString()

        if (principalAmountCheck.isEmpty() || yearsCheck.isEmpty() || interestRateCheck.isEmpty()) {
            Toast.makeText(this, "Please input values", Toast.LENGTH_SHORT).show()
            return
        }
    }
}

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            InterestCalculatorTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    InterestCalculatorTheme {
//        Greeting("Android")
//    }
//}