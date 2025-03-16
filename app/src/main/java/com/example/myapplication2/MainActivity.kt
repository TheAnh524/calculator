package com.example.myapplication2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtResult: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstNumber = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtResult = findViewById(R.id.txtResult)
    }

    fun onNumber(view: View) {
        val button = view as Button
        currentInput += button.text.toString()
        txtResult.text = currentInput
    }

    fun onOperator(view: View) {
        val button = view as Button
        operator = button.text.toString()
        firstNumber = currentInput.toDouble()
        currentInput = ""
    }

    fun onEqual(view: View) {
        if (currentInput.isEmpty()) return

        val secondNumber = currentInput.toDouble()
        var result = 0.0

        when (operator) {
            "+" -> result = firstNumber + secondNumber
            "-" -> result = firstNumber - secondNumber
            "x" -> result = firstNumber * secondNumber
            "/" -> {
                result = if (secondNumber != 0.0) firstNumber / secondNumber else {
                    txtResult.text = "Lỗi"
                    return
                }
            }
        }

        txtResult.text = result.toString()
        currentInput = result.toString()
    }

    fun onClear(view: View) {
        txtResult.text = "0"
        currentInput = ""
        firstNumber = 0.0
        operator = ""
    }

    fun onBackspace(view: View) {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            txtResult.text = if (currentInput.isEmpty()) "0" else currentInput
        }
    }

    fun onToggleSign(view: View) {
        if (currentInput.isNotEmpty()) {
            currentInput = (-currentInput.toDouble()).toString()
            txtResult.text = currentInput
        }
    }
}