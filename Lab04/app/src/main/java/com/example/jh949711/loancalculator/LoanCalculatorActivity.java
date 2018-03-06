package com.example.jh949711.loancalculator;
/*
    Application: Loan Calculator
    Authors: Kyra Belanger, James Hund
    Description: This app takes in input from the user in form of loan amount, APR, and the
                 term of the loan in years. It then will calculate the amount you must pay
                 each month. It will also generate a table that tells the interest that is gained
                 over each year.
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
    Button Calculate,Reset,Table;
    Bundle b = new Bundle();
    double interest;

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
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment.setText("");
                loan.setText("");
                term.setText("");
                APR.setText("");
                Reset.setClickable(false);
                Table.setClickable(false);
                Reset.setTextColor(Color.GRAY);
                Table.setTextColor(Color.GRAY);
                paymentText.setTextColor(Color.GRAY);
                payment.setTextColor(Color.GRAY);
            }
        });
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
                startActivity(tableScreen);
            }
        });
    }

 /*   @Override
    protected void onResume() {
        super.onResume();
        Bundle c = this.getIntent().getExtras();
        interest = c.getDouble("totalInterest");
        loanPayment.setText("Over the period of the loan, interest paid is " + String.format("$%,.2f", interest));
    }*/
}
