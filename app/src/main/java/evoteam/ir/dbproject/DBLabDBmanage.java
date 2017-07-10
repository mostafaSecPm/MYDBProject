package evoteam.ir.dbproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by programmer on 7/8/2017.
 */

public class DBLabDBmanage {

    private static Context mContext ;
    private static SQLiteDatabase mDatabase ;

    private static DBLabDBmanage dbmanageInstance = new DBLabDBmanage() ;

    public DBLabDBmanage () {
        super();
    }

    public DBLabDBmanage (Context context) {
        mContext  = context;
        try {
            mDatabase = new DBLabHelper(mContext).getWritableDatabase() ;
        }
        catch (SQLiteCantOpenDatabaseException e) {}
    }

    public static DBLabDBmanage getDBLabDBInstance(Context context)
    {
        mContext  = context;
        try {
            mDatabase = new DBLabHelper(mContext).getWritableDatabase() ;
        }
        catch (SQLiteCantOpenDatabaseException e)
        {

        }
        return dbmanageInstance ;
    }


    private static ContentValues getProductValue (Bundle state) {
        ContentValues value = new ContentValues() ;

        value.put(DBLabSchema.OrderItemTable.Cols.iid , state.getInt(Constant.OrderITME_IID_KEY));
        value.put(DBLabSchema.OrderItemTable.Cols.oid , state.getInt(Constant.OrderITME_OID_KEY));
        value.put(DBLabSchema.OrderItemTable.Cols.pid , state.getInt(Constant.OrderITME_PID_KEY));
        value.put(DBLabSchema.OrderItemTable.Cols.qty , state.getInt(Constant.OrderITME_QTY_KEY));

        return value ;
    }

    public static void addOrderItem (Bundle product ){
        ContentValues values = getProductValue(product) ;
        Log.d("test",values.toString());
        mDatabase.insert(DBLabSchema.OrderItemTable.NAME , null , values) ;
    }
    public List<Bundle> getTotalCustomers (String WhereClause) {
        List<Bundle> customers = new ArrayList<>() ;

        CustomerCursorWrapper cursor = queryCustomr(WhereClause , null) ;

        try {
            cursor.moveToFirst() ;
            while (!cursor.isAfterLast()){
                customers.add(cursor.getCustomers());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return customers ;
    }

    private CustomerCursorWrapper queryCustomr (String WhereClause , String [] whereArgs) {
        Cursor cursor = mDatabase.query(DBLabSchema.CustomerTable.NAME ,
                null ,
                WhereClause ,
                whereArgs ,
                null,
                null,
                null
        ) ;
        return new CustomerCursorWrapper(cursor) ;
    }

    public List<Bundle> getTotalOrder (String WhereClause) {
        List<Bundle> orders = new ArrayList<>() ;

        OrderCursorWrapper cursor = queryOrder(WhereClause , null) ;

        try {
            cursor.moveToFirst() ;
            while (!cursor.isAfterLast()){
                orders.add(cursor.getOrders());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return orders ;
    }

    private OrderCursorWrapper queryOrder (String WhereClause , String [] whereArgs) {
        Cursor cursor = mDatabase.query(DBLabSchema.OrderTable.NAME ,
                null ,
                WhereClause ,
                whereArgs ,
                null,
                null,
                null
        ) ;
        return new OrderCursorWrapper(cursor) ;
    }

    public List<Bundle> getTotalOrderItems (String WhereClause) {
        List<Bundle> orderItems = new ArrayList<>() ;

        OrderItemCursorWrapper cursor = queryOrderItem (WhereClause , null) ;

        try {
            cursor.moveToFirst() ;
            while (!cursor.isAfterLast()){
                orderItems.add(cursor.getOrderItemss());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return orderItems ;
    }

    private OrderItemCursorWrapper queryOrderItem (String WhereClause , String [] whereArgs) {
        Cursor cursor = mDatabase.query(DBLabSchema.OrderItemTable.NAME ,
                null ,
                WhereClause ,
                whereArgs ,
                null,
                null,
                null
        ) ;
        return new OrderItemCursorWrapper(cursor) ;
    }

    public List<Bundle> getTotalProducts () {
        List<Bundle> products = new ArrayList<>() ;

        ProductCursorWrapper cursor = queryProduct (null , null) ;

        try {
            cursor.moveToFirst() ;
            while (!cursor.isAfterLast()){
                products.add(cursor.getProducts());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return products ;
    }

    private ProductCursorWrapper queryProduct (String WhereClause , String [] whereArgs) {
        Cursor cursor = mDatabase.query(DBLabSchema.ProductTable.NAME ,
                null ,
                WhereClause ,
                whereArgs ,
                null,
                null,
                null
        ) ;
        return new ProductCursorWrapper(cursor) ;
    }

    public void delete (String whereClause) {
        mDatabase.delete(DBLabSchema.OrderItemTable.NAME , whereClause , null) ;
    }

    private static ContentValues getOrderValue (Bundle state) {
        ContentValues value = new ContentValues() ;

        value.put(DBLabSchema.OrderTable.Cols.oid , state.getInt(Constant.Order_OID_KEY));
        value.put(DBLabSchema.OrderTable.Cols.odate , "11 July 2017");
        value.put(DBLabSchema.OrderTable.Cols.cid , state.getInt(Constant.Order_CID_KEY));


        return value ;
    }

    public static void addOrder (Bundle product ) {
        ContentValues values = getOrderValue(product);
        Log.d("test", values.toString());
        mDatabase.insert(DBLabSchema.OrderTable.NAME, null, values);
    }
}
