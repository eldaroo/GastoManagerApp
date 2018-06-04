package com.example.matiasyco.gastomanager.Visual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.matiasyco.gastomanager.Controller.ExpensesManager;
import com.example.matiasyco.gastomanager.Model.Expense;
import com.example.matiasyco.gastomanager.Model.ExpenseDBHelper;
import com.example.matiasyco.gastomanager.R;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private TextView txtDetails;
    ExpensesManager expensesManager;
    ArrayList<Expense> expensesList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        txtDetails = findViewById(R.id.txtDetails);

        ExpenseDBHelper expenseDBHelper = new ExpenseDBHelper(this);

        expensesManager = ExpensesManager.getInstance();
        expensesManager.recoverExpenses(expenseDBHelper);
        expensesList = expensesManager.getExpensesList();
        txtDetails.setText(expensesList.get(0).getName());
    }


}
