package com.example.jh949711.loancalculator;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Kyra on 3/6/18.
 */

public class LoanTableActivity extends LoanCalculatorActivity {
    double loan, apr, totalInterest;
    int months;
    TextView totalLoan, numPay;
    Bundle c = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan_table_activity);
        TableLayout table = findViewById(R.id.tableLayout);

        totalLoan = findViewById(R.id.totalLoanAmount);
        numPay = findViewById(R.id.numPayments);

        Bundle b = this.getIntent().getExtras();
        loan = b.getDouble("loan");
        apr = b.getDouble("apr");
        months = b.getInt("months");

        double rate = apr / 100 / 12;
        double result = loan*(rate+(rate/(Math.pow((1+rate),months)-1)));
        double amount = loan;
        //double rate2 = apr / 12;

        totalLoan.setText("Your loan amount " + String.format("$%,.2f", loan));
        numPay.setText("Your number of payments " + months);

        for ( int i = 1; i <= months; i++ ){
            double interest = amount * rate;
            double column4 = result - interest;
            amount = amount - column4;

            TableRow rows = new TableRow(this);
            TextView c1 = new TextView(this);
            TextView c2 = new TextView(this);
            TextView c3 = new TextView(this);
            TextView c4 = new TextView(this);
            TextView c5 = new TextView(this);

            c1.setText(Integer.toString(i));
            c2.setText(String.format("$%,.2f", result));
            c3.setText(String.format("$%,.2f", interest));
            c4.setText(String.format("$%,.2f", column4));
            c5.setText(String.format("$%,.2f", amount));

            c1.setPadding(10,0,20,0);
            c2.setPadding(20,0,20,0);
            c3.setPadding(20,0,20,0);
            c4.setPadding(20,0,20,0);
            c5.setPadding(20,0,50,0);

            c1.setTypeface(Typeface.DEFAULT_BOLD);
            c2.setTypeface(Typeface.DEFAULT_BOLD);
            c3.setTypeface(Typeface.DEFAULT_BOLD);
            c4.setTypeface(Typeface.DEFAULT_BOLD);
            c5.setTypeface(Typeface.DEFAULT_BOLD);

            rows.addView(c1);
            rows.addView(c2);
            rows.addView(c3);
            rows.addView(c4);
            rows.addView(c5);
            table.addView(rows);

            totalInterest += interest;

        }
    /*    Intent home = new Intent(LoanTableActivity.this, LoanCalculatorActivity.class);
        c.putDouble("totalInterest", totalInterest);
        home.putExtras(c);
        startActivity(home);*/
    }
}
