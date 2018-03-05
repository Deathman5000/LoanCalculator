package com.example.kb974609.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LoanCalculatorActivity extends AppCompatActivity {

    TextView loan, apr, term;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_calculator);

        loan = findViewById(R.id.loanAmount);
        apr = findViewById(R.id.apr);
        term = findViewById(R.id.loanTerm);
        calculate = findViewById(R.id.calculateButton);
    }
}
