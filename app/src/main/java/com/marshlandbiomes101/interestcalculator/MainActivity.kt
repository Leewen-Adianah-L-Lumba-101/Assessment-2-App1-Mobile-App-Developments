package com.marshlandbiomes101.interestcalculator

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.window.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Calendar

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // Create instance of the activity_main xml to showcase on the emulator
        super.onCreate(savedInstanceState)

        // Delay the execution of the next thread to ensure transition from
        // the splash screen to the main activity view
        Thread.sleep(800)

        installSplashScreen()

        // Set emulator view to the main activity
        setContentView(R.layout.activity_main)

        // Fills up to the corner of the screen
        enableEdgeToEdge()

        // Main view, single view app
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initializing variables to submit, increase and decrease values
        val submitBtn = findViewById<Button>(R.id.submit)

        val increaseRateBtn = findViewById<Button>(R.id.increaseRate)
        val decreaseRateBtn = findViewById<Button>(R.id.decreaseRate)

        // Interest and Year are kept separate to avoid overlap
        val increaseYearBtn = findViewById<Button>(R.id.increaseYear)
        val decreaseYearBtn = findViewById<Button>(R.id.decreaseYear)

        // Submit results to calculate the new balance of the money along with other useful
        // statistics
        submitBtn.setOnClickListener{calculateSimpleInterest()}

        // Call increase/decrease functions for interest/year
        increaseRateBtn.setOnClickListener{increaseRateFun()}
        decreaseRateBtn.setOnClickListener{decreaseRateFun()}

        increaseYearBtn.setOnClickListener{increaseYearFun()}
        decreaseYearBtn.setOnClickListener{decreaseYearFun()}
    }

    // Function for the logic of Interest Calculator
    @SuppressLint("InflateParams", "DefaultLocale", "MissingInflatedId", "CutPasteId")
    private fun calculateSimpleInterest() {

        // First value is for calculation, second is for checking if fields are empty
        val principalAmount = findViewById<EditText>(R.id.principal_amount).text.toString().toFloat()
        val principalAmountCheck = findViewById<EditText>(R.id.principal_amount).text.toString()

        val years = findViewById<EditText>(R.id.years).text.toString().toFloat()
        val yearsCheck = findViewById<EditText>(R.id.years).text.toString()

        val interestRate = findViewById<EditText>(R.id.interest_rate).text.toString().toFloat()
        val interestRateCheck = findViewById<EditText>(R.id.interest_rate).text.toString()

        if(principalAmountCheck.isEmpty() || yearsCheck.isEmpty() || interestRateCheck.isEmpty()) {
            // Console should print out that the fields are empty
            println("Value Null/Empty!")
            Toast.makeText(this, "Please enter a value for every field", Toast.LENGTH_SHORT).show()

        // If all fields have entered values the program will run normally
        } else {
            println("Value Found")

            val calendar: Calendar = Calendar.getInstance()

            // Inflate popupsheet from the bottom to show results after calculation
            val view: View = layoutInflater.inflate(R.layout.popupsheet, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()

            val nextYear = ((calendar[Calendar.YEAR]) + years).toString().dropLast(2)
            val interestRateDecimal = interestRate / 100
            val totalBalance = principalAmount * (1 + (interestRateDecimal * years))
            val addedBalance = totalBalance - principalAmount

            //val roundedResult: Double = String.format("%.2f", totalBalance).toDouble()

            // Change text view of the popupsheet to display the results
            val closeBtn = view.findViewById<Button>(R.id.closeButton)
            val displayBalance = view.findViewById<TextView>(R.id.newBalance)
            val displayPrincipalAmount = view.findViewById<TextView>(R.id.principalAmount)
            val displayNextYear = view.findViewById<TextView>(R.id.balanceDue)
            val displayAddedBalance = view.findViewById<TextView>(R.id.addedBalance)

            displayBalance.text = "$totalBalance"
            displayPrincipalAmount.text = "$principalAmount"
            displayNextYear.text = nextYear
            displayAddedBalance.text = "$addedBalance"

            Toast.makeText(this, "Results Calculated!", Toast.LENGTH_SHORT).show()

            // Pressing close button also hides the dialog
            closeBtn.setOnClickListener{dialog.hide()}
        }
    }

    // Functions for increasing/decreasing values of the editText fields
    private fun increaseRateFun () {
        val interestRate = findViewById<EditText>(R.id.interest_rate).text.toString().toFloat()
        updateInterest(interestRate + 1)
    }

    private fun decreaseRateFun () {
        val interestRate = findViewById<EditText>(R.id.interest_rate).text.toString().toFloat()
        updateInterest(interestRate - 1)
    }

    private fun increaseYearFun () {
        val years = findViewById<EditText>(R.id.years).text.toString().toFloat()
        updateYear(years + 1)
    }

    private fun decreaseYearFun () {
        val years = findViewById<EditText>(R.id.years).text.toString().toFloat()
        updateYear(years - 1)
    }

    // Separate update fields in order to not miss up numbers
    private fun updateInterest(number: Float) {
        val interestRateChange = findViewById<EditText>(R.id.interest_rate)
        interestRateChange.setText("$number")
    }

    private fun updateYear(number: Float) {
        val yearChange = findViewById<EditText>(R.id.years)
        yearChange.setText("$number")
    }
}