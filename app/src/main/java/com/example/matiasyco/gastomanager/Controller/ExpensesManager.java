package com.example.matiasyco.gastomanager.Controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.matiasyco.gastomanager.Model.Expense;
import com.example.matiasyco.gastomanager.Model.ExpenseContract;
import com.example.matiasyco.gastomanager.Model.ExpenseDBHelper;

import java.util.ArrayList;

public class ExpensesManager {
    private static final ExpensesManager ourInstance = new ExpensesManager();
    ExpenseDBHelper expenseDBHelper;
    private ArrayList<Expense> expensesList = new ArrayList<Expense>();

    public static ExpensesManager getInstance() {
        return ourInstance;
    }

    private ExpensesManager() {
       //expenseDBHelper = new ExpenseDBHelper(AddExpenseActivity.class);
        //expenseDBHelper.createOrOpenDB();

        //expensesList = new ArrayList<Expense>();
    }

    public void recoverExpenses(ExpenseDBHelper expenseDBHelper)
    {
        expensesList.clear();
        SQLiteDatabase db = expenseDBHelper.getReadableDatabase();
    Cursor myCursor;
        try {
            myCursor = db.rawQuery("select "
                + ExpenseContract.ExpenseEntry.NAME+" , "
                + ExpenseContract.ExpenseEntry.AMOUNT +" from "
                + ExpenseContract.ExpenseEntry.TABLE_NAME,null);
            Log.i("onClick()", "Se recupero la tabla con exito");

            while(myCursor.moveToNext())
            {
                String name = myCursor.getString(0);
                int amount = myCursor.getInt(1);
                Expense expense = new Expense (name, amount);
                expensesList.add (expense);
            }
        }catch (Exception e)
        {
            Log.i("onClick()", "Error al intentar recuperar los datos de la tabla");
        }

    }

    public int total()
    {


        int total = 0;
        for (Expense exp :expensesList) {
            total = total + exp.getAmount();
        }
        return total;
    }

    public void cleanExpenses ()
    {
        expenseDBHelper.cleanDB();
        }
   public ArrayList<Expense> getExpensesList()
    {
        return expensesList;
    }

}
