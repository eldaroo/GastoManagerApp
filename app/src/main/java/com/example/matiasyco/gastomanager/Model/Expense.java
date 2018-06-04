package com.example.matiasyco.gastomanager.Model;

import android.content.ContentValues;

public class Expense {
    private String name;
    private int amount;

    public Expense(String name, int amount)
    {
        this.amount = amount;
        this.name = name;
    }

    public int getAmount()
    {
        return amount;
    }

    public String getName() {
        return name;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(ExpenseContract.ExpenseEntry.NAME, name);
        values.put(ExpenseContract.ExpenseEntry.AMOUNT, amount);

        return values;
    }
}
