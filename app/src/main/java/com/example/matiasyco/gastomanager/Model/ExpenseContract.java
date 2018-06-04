package com.example.matiasyco.gastomanager.Model;

import android.provider.BaseColumns;

public class ExpenseContract {
    public static abstract class ExpenseEntry implements BaseColumns{
        public static final String TABLE_NAME = "expenses";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AMOUNT = "amount";
    }
}
