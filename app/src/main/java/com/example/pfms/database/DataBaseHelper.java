package com.example.pfms.database;// package name

// Java Libraries

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

// Andriod Libraries
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.pfms.models.Expense;
import com.example.pfms.models.Field;
import com.example.pfms.models.Pair;
import com.example.pfms.models.User;

// import Field class
// import User class
// import Expense class

/**
 * Please write javadoc for this class
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "database.db", null, 3);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /**
         * This function creates the two tables user and exp
         * @author Zarif and Fida
         * @param an SQLiteDatabase
         */

        //Create table tb_user (id_user integer primary key, name_user text, passwd text,init_capital real,budget real);
        String CREATE_TB_USER = String.format("Create table %s (%s integer primary key, %s text, %s text,%s real,%s real,%s text,%s text,%s integer);", Field.TB_USER, Field.ID_USER, Field.NAME_USER, Field.PASSWD_USER, Field.INIT_CAPITAL_USER, Field.BUDGET_USER, Field.START_DATE_USER, Field.EMAIL_USER, Field.LOGGED_IN_STATUS_USER);

        //Create table tb_exp (category text,amount real,date text);
        String CREATE_TB_EXP = String.format("Create table %s (%s text,%s real,%s text);", Field.TB_EXP, Field.CAT_EXP, Field.AMOUNT_EXP, Field.DATE_EXP);

        // Execute DDL
        db.execSQL(CREATE_TB_USER);
        db.execSQL(CREATE_TB_EXP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void dropUser () {

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.delete(Field.TB_USER, null, null);
            db.delete(Field.TB_EXP, null, null);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "User Dropping Error!", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void createUser (User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues cv = new ContentValues();
            cv.put(Field.ID_USER,user.getId());
            cv.put(Field.NAME_USER,user.getUsername());
            cv.put(Field.PASSWD_USER,user.getPassword());
            cv.put(Field.INIT_CAPITAL_USER,user.getInitCapital());
            cv.put(Field.BUDGET_USER,user.getBudget());
            cv.put(Field.START_DATE_USER,user.getStartDate());
            cv.put(Field.EMAIL_USER,user.getEmail());
            cv.put(Field.LOGGED_IN_STATUS_USER,user.getLoggedIn());

            db.insert(Field.TB_USER, null, cv);

            Toast.makeText(context, "User Creation Successful", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "User Creation Error", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void setUserStartDate (String startDate) {


        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Field.START_DATE_USER, startDate);
            db.update(Field.TB_USER, cv, Field.ID_USER + " = ?", new String[]{"1"});
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Initialization Error", Toast.LENGTH_SHORT).show();
        }

    }

    public void setUserNewCapital (float value) {


        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Field.INIT_CAPITAL_USER, value);
            db.update(Field.TB_USER, cv, Field.ID_USER + " = ?", new String[]{"1"});
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Update Capital Error", Toast.LENGTH_SHORT).show();
        }

    }

    public void setUserNewBudget (float value) {


        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Field.BUDGET_USER, value);
            db.update(Field.TB_USER, cv, Field.ID_USER + " = ?", new String[]{"1"});
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Update Budget Error", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isLoggedIn () {

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String query = String.format("select %s from %s;", Field.LOGGED_IN_STATUS_USER, Field.TB_USER);
            Cursor cursor = db.rawQuery(query, null);

            int loggedInStatus = 0;
            if (cursor.moveToFirst()) {
                loggedInStatus = cursor.getInt(0);
            }

            cursor.close();
            db.close();

            return loggedInStatus==1;

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Initialization Error", Toast.LENGTH_SHORT).show();
        }
        return false;

    }

    public void setUserLoggedIn () {


        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Field.LOGGED_IN_STATUS_USER, 1);
            db.update(Field.TB_USER, cv, Field.ID_USER + " = ?", new String[]{"1"});
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Log In Error", Toast.LENGTH_SHORT).show();
        }

    }

    public void setUserLoggedOut () {


        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Field.LOGGED_IN_STATUS_USER, 0);
            db.update(Field.TB_USER, cv, Field.ID_USER + " = ?", new String[]{"1"});
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Log Out Error", Toast.LENGTH_SHORT).show();
        }

    }

    public String fetchMail () {

        String mail = " ";

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String query = String.format("select %s from %s;", Field.EMAIL_USER, Field.TB_USER);
            Cursor cursor = db.rawQuery(query, null);



            if (cursor.moveToFirst()) {
                mail = cursor.getString(0);
            }

            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Send Mail Error", Toast.LENGTH_SHORT).show();
        }

        return mail;
    }

    public String fetchUsername () {

        String mail = " ";

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String query = String.format("select %s from %s;", Field.NAME_USER, Field.TB_USER);
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                mail = cursor.getString(0);
            }

            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Send Mail Error", Toast.LENGTH_SHORT).show();
        }

        return mail;
    }


    public void addExpense (Expense expense)
    {


        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Field.CAT_EXP, expense.getCategory());
            cv.put(Field.AMOUNT_EXP, expense.getAmount());
            cv.put(Field.DATE_EXP, expense.getDate());

            db.insert(Field.TB_EXP, null, cv);
            db.close();

            Toast.makeText(context, "Expense Added", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Expense Insertion Error!", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean hasUserStarted () {

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String query = String.format("select %s from %s;", Field.START_DATE_USER, Field.TB_USER);
            Cursor cursor = db.rawQuery(query, null);

            String date = " ";

            if (cursor.moveToFirst()) {
                date = cursor.getString(0);
            }

            cursor.close();
            db.close();

            return !date.equals("XXX");

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Start Date Fetching Error!", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public boolean isAnyUserAvailable () {

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String query = String.format("select count(*) from %s;", Field.TB_USER);
            Cursor cursor = db.rawQuery(query, null);

            int userCount = 0;
            if (cursor.moveToFirst()) {
                userCount = cursor.getInt(0);
            }

            cursor.close();
            db.close();

            return userCount>0;

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Verification Error", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean verifyUser (User user) {
        try {

            if (!isAnyUserAvailable()) {
                Toast.makeText(context, "User doesn't Exist", Toast.LENGTH_SHORT).show();
                return false;
            }

            SQLiteDatabase db = this.getReadableDatabase();

            String query = String.format("select %s, %s from %s;", Field.NAME_USER, Field.PASSWD_USER, Field.TB_USER);
            Cursor cursor = db.rawQuery(query, null);

            String username = " ",password = " ";

            if (cursor.moveToFirst()) {
                username = cursor.getString(0);
                password = cursor.getString(1);
            }

            cursor.close();
            db.close();

            if (!user.getUsername().equals(username)) {
                Toast.makeText(context, "User doesn't Exist", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (!user.getPassword().equals(password)) {
                Toast.makeText(context, "Invalid Password", Toast.LENGTH_SHORT).show();
                return false;
            }

            else {
                setUserLoggedIn();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Verification Error", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public int fetchPassedDays () {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = String.format("select %s from %s;", Field.START_DATE_USER, Field.TB_USER);
            Cursor cursor = db.rawQuery(query, null);

            String strStartDate="";

            if (cursor.moveToFirst()) {
                strStartDate = cursor.getString(0);
            }

            cursor.close();
            db.close();

            @SuppressLint("SimpleDateFormat") Date startDate = new SimpleDateFormat("dd-MMM-yyyy").parse(strStartDate);
            @SuppressLint("SimpleDateFormat") Date currentDate = new SimpleDateFormat("dd-MMM-yyyy").parse(new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date()));

            long passedDays = currentDate.getTime() - startDate.getTime();

            Log.d("Passed Days:", String.valueOf(TimeUnit.DAYS.convert(passedDays, TimeUnit.MILLISECONDS)));

            return (int) TimeUnit.DAYS.convert(passedDays, TimeUnit.MILLISECONDS);
        } catch (ParseException pe) {
            pe.printStackTrace();
            Toast.makeText(context, "Parse Error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error while fetching Passed Days", Toast.LENGTH_SHORT).show();
        }

        return -1;
    }

//    public int fetchPassedDays() {
//
//        /**
//         *	This function returns the passed days since the beginning of the month
//         *	by counting the number of distinct date entires
//         *    @author Fida
//         *    @return number of days passed
//         */
//
//        // return variable
//        int answer = 0;
//
//        //Select count (distinct date) from tb_exp;
//        String query = String.format("Select count (distinct %s) from %s;", Field.DATE_EXP, Field.TB_EXP);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        // Query is expected to return only one integer value
//        if (cursor.moveToFirst()) answer = cursor.getInt(0);
//
//        db.close();
//        cursor.close();
//
//        return answer;
//
//    }

    public int fetchRemainingDays() {
        /**
         *	This function returns the number of days to reach the end of the month
         *	All months are 30 days long
         *    @author Fida
         *    @return the number of days remainings
         */

        return 30 - fetchPassedDays();
    }

    public float fetchTotalDebit() {

        /**
         *	This function returns the total amount of debit by summing all expenses in sha Allah
         *    @author Fida
         *    @return the total debit as a floating point
         *
         */

        try {
            //Select Sum(amount) from tb_exp;
            String query = String.format("Select Sum(%s) from %s;", Field.AMOUNT_EXP, Field.TB_EXP);

            //return variable
            float result = 0.0F;

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            //Query is expected to return only one float value
            if (cursor.moveToFirst()) result = cursor.getFloat(0);

            cursor.close();
            db.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error while fetching Total Debit", Toast.LENGTH_SHORT).show();
        }

        return -1.0F;

    }

    public ArrayList<Pair> fetchTotalDebitByCategory() {

        /**
         *	This function returns a map of (date,debit) pairs where debit is the amount spent on that day
         *    @author Fida
         *    @return Map<String, Float> of type HashMap (Date,Debit) pairs
         */

        try {
            // Query = Select date,Sum(amount) from tb_exp group by date ;
            // 1$ means first argument in the argument list
            String query = String.format("Select %s,Sum(%s) from %s group by %1$s ;", Field.CAT_EXP, Field.AMOUNT_EXP, Field.TB_EXP);

            ArrayList<Pair> results = new ArrayList<>();

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext())
                results.add(new Pair(cursor.getString(0), cursor.getFloat(1)));

            cursor.close();
            db.close();

            return results;

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error while fetching Daily Debits", Toast.LENGTH_SHORT).show();
        }

        return null;

    }

    public ArrayList<Pair> fetchHistory() {

        /**
         *	This function returns a map of (date,debit) pairs where debit is the amount spent on that day
         *    @author Fida
         *    @return Map<String, Float> of type HashMap (Date,Debit) pairs
         */

        try {
            // Query = Select date,Sum(amount) from tb_exp group by date ;
            // 1$ means first argument in the argument list
            String query = String.format("Select %s,%s from %s;", Field.DATE_EXP, Field.AMOUNT_EXP, Field.TB_EXP);

            ArrayList<Pair> results = new ArrayList<>();

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext())
                results.add(new Pair(cursor.getString(0), cursor.getFloat(1)));

            cursor.close();
            db.close();

            return results;

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error while fetching Daily Debits", Toast.LENGTH_SHORT).show();
        }

        return null;

    }

    public float fetchBudget () {

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String query = String.format("Select %s from %s;", Field.BUDGET_USER, Field.TB_USER);
            Cursor cursor = db.rawQuery(query, null);

            float budget = 0.0F;
            if (cursor.moveToFirst()) {
                budget = cursor.getFloat(0);
            }

            cursor.close();
            db.close();
            return budget;

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error while fetching Budget", Toast.LENGTH_SHORT).show();
        }

        return -1.0F;

    }

    public float fetchRRE() {

        /**
         *	This function returns the Required Rate of Expenditure
         *	RRE=(Budget-TotalDebit)/Remaining days
         *	if budget>TotalDebit then 0 is returned
         *
         *    @author Fida
         *    @return RRE as a floating point
         *    @exception ArithemticException when Remaining days is zero
         */

        try {
            //Query=Select budget from tb_user;
            String query = String.format("Select %s from %s;", Field.BUDGET_USER, Field.TB_USER);

            // total debit
            float totalDebit = fetchTotalDebit();

            //user budget
            float budget = 0.0F;

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            //Query is expected to return only one float value
            if (cursor.moveToFirst())
                budget = cursor.getFloat(0);

            cursor.close();
            db.close();


            if (budget > totalDebit) {
                return (budget - totalDebit) / fetchRemainingDays();
            }
            // Total Expenses greater than budget so return 0
            else return 0.0F;

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error while fetching RRE", Toast.LENGTH_SHORT).show();
        }

        return -1.0F;
    }


    public float fetchCRE() {
        /**
         *	This function calculates the Current Rate of Expenditure
         *	CRE=TotalDebit/PassedDays
         *
         *    @author Fida
         *    @return CRE as a floating point
         *    @exception ArithmeticException when passed days is zero
         */

        return fetchTotalDebit() / (fetchPassedDays()+1);
    }

    public boolean isAnyExpense () {

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String query = String.format("select count(*) from %s;", Field.TB_EXP);
            Cursor cursor = db.rawQuery(query, null);

            int expenseCount = 0;
            if (cursor.moveToFirst()) {
                expenseCount = cursor.getInt(0);
            }

            cursor.close();
            db.close();

            return expenseCount>0;

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Expense Error", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public List<String> fetchTopCategories() {

        /**
         *	This function returns the category/s which has the most expenses, if there are more than one all of them are returned in sha Allah
         *
         *    @author Fida
         *    @return List<String> which has the names of the category/s
         *
         */


        try {
            // The query first calculates a table which is ranked by sum of expenses per category and then all categories having rank 1 are fetched in the second query
            //Query=with Categorical_expenses as (Select category as Category,sum(amount) as Expenses,rank() over(order by sum(amount) desc) as Rank from tb_exp group by category) select Category from Categorical_expenses where Rank=1;

            if (!isAnyExpense())
                return null;

//            String query = String.format("with Categorical_expenses as (Select %s as Category,sum(%s) as Expenses,rank() over(order by sum(%2$s) desc) as Rank from %s group by %1$s) select Category from Categorical_expenses where Rank=?;", Field.CAT_EXP, Field.AMOUNT_EXP, Field.TB_EXP);

            //return variable
            List<String> categories = new ArrayList<>();

            SQLiteDatabase db = this.getReadableDatabase();

            String query = String.format("select * from (select %s, sum(%s) as summ from %s GROUP by %s) WHERE summ = (SELECT max(summm) from (select %s, sum(%s) as summm from %s GROUP by %s));",
                    Field.CAT_EXP, Field.AMOUNT_EXP, Field.TB_EXP, Field.CAT_EXP, Field.CAT_EXP, Field.AMOUNT_EXP, Field.TB_EXP, Field.CAT_EXP);

            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {
                Log.d("Zariff catt", cursor.getString(0));
                categories.add(cursor.getString(0));
            }

            cursor.close();
            db.close();

            return categories;

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error while fetching Top Categories", Toast.LENGTH_SHORT).show();
        }

        return null;
    }

    public float fetchExpectedSavings() {

        /**
         *	This function returns the amount of savings expected at the end of the month if the RRE remains the same till the end of the month
         *	ExpectedSavings=intitialCapital-totalDebit+CRE*remainingDays
         *
         *    @author Fida
         *    @return expected savings as a floating point
         */

        try {
            // Query= select init_capital from tb_user;
            String query = String.format("select %s from %s;", Field.INIT_CAPITAL_USER, Field.TB_USER);

            //return variable
            float initialCapital = 0.0F;

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            //Query is expected to return only one float value
            if (cursor.moveToFirst())
                initialCapital = cursor.getFloat(0);

            cursor.close();
            db.close();

            Log.d("Zarif save save 122", Float.toString(initialCapital - fetchTotalDebit() - (fetchCRE() * fetchRemainingDays())));

            return initialCapital - fetchTotalDebit() - (fetchCRE() * fetchRemainingDays());

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error while fetching Expected Savings", Toast.LENGTH_SHORT).show();
        }

        return -1.0F;

    }


    public boolean fetchRemarks() {
        return fetchCRE() <= fetchRRE();
    }


}