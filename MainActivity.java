package com.brettdavis.calculator_mk2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //--------- Our boxes at the top
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    //Variables to hold the operands (+, -, /, *)
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //----Assigned our buttons to our code references
        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        //----Assigned our number and decimal buttons to code references
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button8);
        Button buttonDot = (Button) findViewById(R.id.buttonDecimal);

        //----Assigned our operand buttons to code references
        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);

        //----When a button is tapped, we add it to the newNumber box
        View.OnClickListener listener = new View.OnClickListener() {//fire up our code to look for a button to be clicked
            @Override
            public void onClick(View v) {
                Button button = (Button) v; //recognize the button
                newNumber.append(button.getText().toString()); //convert toString
            }
        };

        //----Assign our buttons (in the code) to be attached to this OnClickListener called listener (from above)
        //----If you're doing a full keyboard, you'll probably want to do some sort of loop instead
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);


        //----Our listener for our operands
        View.OnClickListener operandListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view; //activates our button
                String op = b.getText().toString(); //retrieves the text from the button
                String value = newNumber.getText().toString();  //gets the number in NewNumber

                //-----Below is an alternative form to catch the "." error and have the program
                //still function.
                //Before this error, if you inputted just a "." without any numbers, the program
                //would crash.
                //Now, we're checking for a value of a number but if it's a ".", we continue the program
                //because we "caught" (try, catch) the error :)
                try{
                    Double doubleValue = Double.valueOf(value); //get the value from the string
                    performOperation(doubleValue, op); //perform the calculation
                }catch (NumberFormatException e){ //look for the decimal error
                    newNumber.setText(""); //clear the text
                }

                //-----------------------------------------------
                pendingOperation = op; //set the pendingOpeation to the text from the button
                displayOperation.setText(pendingOperation); //set our operation box to the pending operation text
            }
        };

        buttonEquals.setOnClickListener(operandListener);
        buttonPlus.setOnClickListener(operandListener);
        buttonMinus.setOnClickListener(operandListener);
        buttonMultiply.setOnClickListener(operandListener);
        buttonDivide.setOnClickListener(operandListener);

        //----Our CLEAR button functionality
        Button buttonClear = (Button) findViewById(R.id.clearButton);

        View.OnClickListener clearListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Button b = (Button) view;
                String clear = "";
                newNumber.setText(clear);
                result.setText(clear);
                displayOperation.setText(clear);
            }
        };

        buttonClear.setOnClickListener(clearListener);


    }

    private void performOperation(Double value, String operand){
        if(null == operand1){ //if operand1 is empty...
            operand1 = value; //set operand1 to the number
        }else{
            operand2 = value; //set operand2 to the number

            if(pendingOperation.equals("=")){
                pendingOperation = operand;
            }
            switch(pendingOperation){
                case "=":
                    operand1 = operand2;
                    break;
                case "/":
                    if(operand2 == 0){
                        operand1 = 0.0;
                    }else{
                        operand1 /= operand2;
                    }
                    break;
                case "*":
                    operand1 *= operand2;
                    break;
                case "-":
                    operand1 -= operand2;
                    break;
                case "+":
                    operand1 += operand2;
                    break;
            }

        }

        result.setText(operand1.toString());
        newNumber.setText(""); //clear
    }
}
