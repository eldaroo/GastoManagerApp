package com.example.matiasyco.gastomanager.Visual;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matiasyco.gastomanager.Controller.ExpensesManager;
import com.example.matiasyco.gastomanager.Model.Expense;
import com.example.matiasyco.gastomanager.Model.ExpenseContract;
import com.example.matiasyco.gastomanager.Model.ExpenseDBHelper;
import com.example.matiasyco.gastomanager.R;

public class AddExpenseActivity extends AppCompatActivity {
    private EditText name;
    private EditText amount;
    private FloatingActionButton btnAdd;
    ExpensesManager expensesManager = ExpensesManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        name = findViewById(R.id.txtName);
        amount = findViewById(R.id.txtAmount);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addExpense();
            }
        });
    }
    public void addExpense ()
    {
        ExpenseDBHelper expenseDBHelper = new ExpenseDBHelper(this);
        expenseDBHelper.createOrOpenDB();
        SQLiteDatabase db = expenseDBHelper.getWritableDatabase();
        Expense expense  = new Expense(name.getText().toString(),Integer.parseInt(amount.getText().toString()));

        db.beginTransaction();
        //Si hemos abierto correctamente la base de datos
        //if(db != null) {
            //Insertamos los datos en la tabla Usuarios
            //try {
                db.insert(ExpenseContract.ExpenseEntry.TABLE_NAME, null, expense.toContentValues());
            //} catch (Exception e) {
              //  System.out.print("big wrong baby " + e);
            //}

            //Cerramos la base de datos
            db.close();
        //}


        //expensesManager.addExpense(expenseDBHelper,name.getText().toString(), Integer.parseInt(amount.getText().toString()));
        Toast.makeText(AddExpenseActivity.this, "Gasto agregado", Toast.LENGTH_SHORT).show();

        Intent next = new Intent(AddExpenseActivity.this, MainActivity.class);
        startActivity(next);
    }


}
