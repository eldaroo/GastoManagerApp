package com.example.matiasyco.gastomanager.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.matiasyco.gastomanager.Controller.ExpensesManager;
import com.example.matiasyco.gastomanager.Visual.AddExpenseActivity;

public class ExpenseDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Expenses.db";
    public ExpenseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL("CREATE TABLE IF NOT EXISTS "+ ExpenseContract.ExpenseEntry.TABLE_NAME +" ("
            + ExpenseContract.ExpenseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ExpenseContract.ExpenseEntry.NAME + " TEXT, "
            + ExpenseContract.ExpenseEntry.AMOUNT + " INTEGER)"
    );
            Log.i("onClick()", "Se creo la tabla con exito");

        }catch (Exception e)
{
    Log.i("onClick()", "Error al intentar crear la base de datos");
}

        mockData(db);

    }

    private void mockData (SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.beginTransaction();
        mockExpense(sqLiteDatabase, new Expense("Gimnasio", 20));
        mockExpense(sqLiteDatabase, new Expense("chocolate", 40));
        sqLiteDatabase.endTransaction();
        Log.i("onClick()", "Se agrego la información de prueba");

    }

    public long mockExpense(SQLiteDatabase db, Expense expense) {
        return db.insert(
                ExpenseContract.ExpenseEntry.TABLE_NAME,
                null,
                expense.toContentValues());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ ExpenseContract.ExpenseEntry.TABLE_NAME);


        db.execSQL("CREATE TABLE "+ ExpenseContract.ExpenseEntry.TABLE_NAME +"("
                + ExpenseContract.ExpenseEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ExpenseContract.ExpenseEntry.NAME + "TEXT NOT NULL, "
                + ExpenseContract.ExpenseEntry.AMOUNT + "INTEGER)"
        );
    }


     public void createOrOpenDB()
    {
//        SQLiteDatabase myDB  = SQLiteDatabase.openOrCreateDatabase("Expenses.db", null, null );
    }

    public Cursor getAllExpenses()
    {

        return getReadableDatabase().query(ExpenseContract.ExpenseEntry.TABLE_NAME,null,null,null,null,null,null);
        /*SQLiteDatabase myDB = getReadableDatabase();

        ExpensesManager expensesManager = ExpensesManager.getInstance();
        expensesManager.getExpensesList().clear();
        Cursor myCursor = myDB.rawQuery("select name, amount from expenses",null);
        while(myCursor.moveToNext())
        {
            String name = myCursor.getString(0);
            int amount = myCursor.getInt(1);
            expensesManager.recoverExpense(name, amount);
        }*/
    }
    public void cleanDB()
    {
        SQLiteDatabase myDB = getWritableDatabase();

        myDB.execSQL("DELETE FROM expenses ");
    }
}
