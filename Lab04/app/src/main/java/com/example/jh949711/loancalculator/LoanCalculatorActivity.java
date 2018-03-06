package com.example.jh949711.loancalculator;
/*
    Application: Loan Calculator
    Authors: Kyra Belanger, James Hund
    Description: This app takes in input from the user in form of loan amount, APR, and the
                 term of the loan in years. It then will calculate the amount you must pay
                 each month. It will also generate a table that tells the interest that is gained
                 over each year along with the amount you are paying toward the loan as well as
                 how much interest is paid.
    Grade: Kyra:  Developed the table along with the button for it. Calculated everything needed
                  to be put in the table. Developed the XML file needed for the table activity.

           James: Developed the interface of the app along with the calculate and reset buttons.
                  Also coded the part that displays the amount of interest paid in the main
                  screen. Developed the XML file needed for the main interface.

    Grade Deserved: Kyra-  100%
                    James- 100%
 */
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoanCalculatorActivity extends AppCompatActivity {
    EditText APR,loan,term,payment;
    TextView loanPayment, paymentText;
    double textPayment;
    Button Calculate,Reset,Table;
    Bundle b = new Bundle();


    /*
        This method is used for getting the value for the total interest paid. It grabs the
        value from the table activity and inserts it into the main activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                textPayment = data.getExtras().getDouble("totalInterest");
                loanPayment.setText("Over the period of the loan, interest paid is " +
                        String.format("$%,.2f", textPayment));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_calculator);
        loan = findViewById(R.id.loanAmount);
        APR = findViewById(R.id.APR);
        term = findViewById(R.id.term);
        payment = findViewById(R.id.totalPayment);
        loanPayment = findViewById(R.id.interestPaid);
        paymentText = findViewById(R.id.paymentText);
        Calculate = findViewById(R.id.calculate);
        Reset = findViewById(R.id.reset);
        Table = findViewById(R.id.table);

        /*
            Method for the Calculate button. This will calculate what is entered in the blanks
            in the first screen. It also guards against putting empty values in the blanks.
         */
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loan.getText().toString().equals("")){
                    Toast.makeText(LoanCalculatorActivity.this, "No Loan Amount Entered",
                            Toast.LENGTH_SHORT).show();
                }
                else if(APR.getText().toString().equals("")){
                    Toast.makeText(LoanCalculatorActivity.this, "No APR Entered",
                            Toast.LENGTH_SHORT).show();
                }
                else if(term.getText().toString().equals("")){
                    Toast.makeText(LoanCalculatorActivity.this, "No Term Entered",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    double LOAN = Double.parseDouble(loan.getText().toString());
                    double apr = Double.parseDouble(APR.getText().toString());
                    double TERM = Double.parseDouble(term.getText().toString());
                    double rate = (apr/100)/12;
                    double months = TERM*12;
                    double result = LOAN*(rate+(rate/(Math.pow((1+rate),months)-1)));
                    payment.setText(String.format("$%,.2f", result));
                    loan.setText(String.format("$%,.2f", LOAN));
                    term.setText(String.format("%.0f", TERM));
                    APR.setText(String.format("%.2f%%", apr));
                    Reset.setClickable(true);
                    Table.setClickable(true);
                    Reset.setTextColor(Color.BLACK);
                    Table.setTextColor(Color.BLACK);
                    paymentText.setTextColor(Color.DKGRAY);
                    payment.setTextColor(Color.BLACK);
                }
            }
        });

        /*
            Clears the data that was previously entered so new values can be calculated
         */
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment.setText("");
                loan.setText("");
                term.setText("");
                APR.setText("");
                loanPayment.setTextColor(Color.GRAY);
                Reset.setClickable(false);
                Table.setClickable(false);
                Reset.setTextColor(Color.GRAY);
                Table.setTextColor(Color.GRAY);
                paymentText.setTextColor(Color.GRAY);
                payment.setTextColor(Color.GRAY);

            }
        });

        /*
            Gets the values needed for the calculations for the table. It then sends them to the
            next activity so they can be used.
         */
        Table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double LOAN = Double.parseDouble(loan.getText().toString().replace("$", "").replace(",", ""));
                double apr = Double.parseDouble(APR.getText().toString().replace("%",""));
                int TERM = Integer.parseInt(term.getText().toString());
                int months = TERM * 12;

                Intent tableScreen = new Intent(LoanCalculatorActivity.this, LoanTableActivity.class);
                b.putDouble("loan", LOAN);
                b.putDouble("apr", apr);
                b.putInt("months", months);

                tableScreen.putExtras(b);
                startActivityForResult(tableScreen,1);
            }
        });
    }

}
