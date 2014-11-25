package com.zurce.calterial.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ResultsDataSource {

    /// Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_RESULTS };

    public ResultsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Result createComment(String result) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_RESULTS, result);
        long insertId = database.insert(MySQLiteHelper.TABLE_RESULTS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_RESULTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Result newResult = cursorToResult(cursor);
        cursor.close();
        return newResult;
    }

    public void deleteComment(Result result) {
        long id = result.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_RESULTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Result> getAllComments() {
        List<Result> comments = new ArrayList<Result>();



        Cursor cursor = database.query(MySQLiteHelper.TABLE_RESULTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Result comment = cursorToResult(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Result cursorToResult(Cursor cursor) {
        Result comment = new Result();
        comment.setId(cursor.getLong(0));
        comment.setResult(cursor.getString(1));
        return comment;
    }

}