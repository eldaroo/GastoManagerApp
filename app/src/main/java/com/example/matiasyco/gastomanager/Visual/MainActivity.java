package com.example.matiasyco.gastomanager.Visual;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.matiasyco.gastomanager.Controller.ExpensesManager;

import com.example.matiasyco.gastomanager.Model.ExpenseDBHelper;
import com.example.matiasyco.gastomanager.R;

public class MainActivity extends AppCompatActivity {

    TextView totalExpenses;
    Button details;
    ExpensesManager expensesManager = ExpensesManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Conexion con la base de datos
         final ExpenseDBHelper expenseDBHelper = new ExpenseDBHelper(this);
        SQLiteDatabase db = expenseDBHelper.getWritableDatabase();


        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Nuevo gasto", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent next = new Intent(MainActivity.this, AddExpenseActivity.class);
                startActivity(next);
            }
        });

        totalExpenses = findViewById(R.id.txtTotalExpenses);


        details = findViewById(R.id.btnDetails);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expensesManager.recoverExpenses(expenseDBHelper);
                totalExpenses.setText("gastos "+ expensesManager.total());
                //Intent next  = new Intent(MainActivity.this, DetailsActivity.class);
                //startActivity(next);
            }
        });

        FloatingActionButton fabReset = findViewById(R.id.fabReset);
        fabReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expensesManager.cleanExpenses();
  //              totalExpenses.setText("gastos "+ expensesManager.total());

            }
        });

    }

}
