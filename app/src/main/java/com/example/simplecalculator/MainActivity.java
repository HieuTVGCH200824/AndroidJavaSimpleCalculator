package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Declare variables to store operands and the operator
    private double operand1 = Double.NaN;
    private double operand2 = Double.NaN;
    private String operator = "";
    private boolean isNewCalculation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the UI elements
        Button btn0 = findViewById(R.id.button0);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);
        Button btnAdd = findViewById(R.id.buttonAdd);
        Button btnSub = findViewById(R.id.buttonSubtract);
        Button btnMul = findViewById(R.id.buttonMultiply);
        Button btnDiv = findViewById(R.id.buttonDivide);
        Button btnClear = findViewById(R.id.buttonClear);
        Button btnEqual = findViewById(R.id.buttonEquals);
        TextView result = findViewById(R.id.result);
        TextView operand1View = findViewById(R.id.operand1);
        TextView operatorView = findViewById(R.id.operator);
        TextView operand2View = findViewById(R.id.operand2);

        // Set click listeners for number buttons (0-9)
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();

                if (isNewCalculation) {
                    operand1View.setText("");
                    operatorView.setText("");
                    operand2View.setText("");
                    result.setText("");
                    isNewCalculation = false;
                }
                if(operatorView.getText().toString().equals("")) {
                    String currentText = operand1View.getText().toString();
                    operand1View.setText(currentText + buttonText);
                }
                else {
                    String currentText = operand2View.getText().toString();
                    operand2View.setText(currentText + buttonText);
                }

            }
        };

        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);

        // Set click listener for operator buttons (+, -, *, /)
        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                operator = button.getText().toString();
                String operand1Text = operand1View.getText().toString();
                try{
                operand1 = Double.parseDouble(operand1Text);
                operatorView.setText(operator);
                }catch (NumberFormatException e) {
                    operand1View.setText("");
                    operatorView.setText("");
                    operand2View.setText("");
                    isNewCalculation = true;
                }
            }
        };

        btnAdd.setOnClickListener(operatorClickListener);
        btnSub.setOnClickListener(operatorClickListener);
        btnMul.setOnClickListener(operatorClickListener);
        btnDiv.setOnClickListener(operatorClickListener);

        // Set click listener for the equals button (=)
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNewCalculation && !operatorView.getText().toString().equals("") && !operand2View.getText().toString().equals("")) {
                    operand2 = Double.parseDouble(operand2View.getText().toString());
                    double resultValue = calculateResult(operand1View, operand2View, result);
                    result.setText(String.valueOf(resultValue));
                    isNewCalculation = true;
                }
            }
        });

        // Set click listener for the clear button (C)
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operand1 = Double.NaN;
                operator = "";
                operand1View.setText("");
                operatorView.setText("");
                operand2View.setText("");
                result.setText("");
                isNewCalculation = true;
            }
        });
    }

    private double calculateResult(TextView operand1View, TextView operand2View, TextView result) {
        double resultValue = 0;
        switch (operator) {
            case "+":
                resultValue = operand1 + operand2;
                break;
            case "-":
                resultValue = operand1 - operand2;
                break;
            case "*":
                resultValue = operand1 * operand2;
                break;
            case "/":
                if (operand2 != 0) {
                    resultValue = operand1 / operand2;
                } else {
                    result.setText("Error: Division by zero");
                }
                break;
        }

        operand2View.setText(String.valueOf(operand2));
        return resultValue;
    }
}
