package evoteam.ir.dbproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by programmer on 7/8/2017.
 */

public class DBLabHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1 ;
    private static final String DATABASE_NAME = "TaxiDriver.db" ;

    public DBLabHelper (Context context) {super (context ,  DATABASE_NAME , null , VERSION ) ;}

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + DBLabSchema.CustomerTable.NAME  + " ( " +
                DBLabSchema.CustomerTable.Cols.cid + " integer primary key  , " +
                DBLabSchema.CustomerTable.Cols.cname  +
                ")"
        );

        db.execSQL("create table " + DBLabSchema.ProductTable.NAME  + " ( " +
                DBLabSchema.ProductTable.Cols.pid + " integer primary key , " +
                DBLabSchema.ProductTable.Cols.pname + "  string , " +
                DBLabSchema.ProductTable.Cols.price + " integer " +

                " ) "
        );




        db.execSQL("create table " + DBLabSchema.OrderItemTable.NAME  + " ( " +
                DBLabSchema.OrderItemTable.Cols.iid + " integer  , " +
                DBLabSchema.OrderItemTable.Cols.oid + " integer , " +
                DBLabSchema.OrderItemTable.Cols.pid + " integer , " +
                DBLabSchema.OrderItemTable.Cols.qty + " integer , " +
                "FOREIGN KEY(oid) REFERENCES order1(oid) ," +
                "FOREIGN KEY(pid) REFERENCES product(pid) " +

                ")"
        );

        db.execSQL("create table " + DBLabSchema.OrderTable.NAME  + " ( " +
                DBLabSchema.OrderTable.Cols.oid+ " integer  , " +
                DBLabSchema.OrderTable.Cols.odate + " string , " +
                DBLabSchema.OrderTable.Cols.cid + " integer  ," +
                "FOREIGN KEY(cid) REFERENCES customer(cid) " +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
// " FOREIGN KEY(oid) REFERENCES order1(oid) " +
  //" FOREIGN KEY(pid) REFERENCES product(pid) " +
//                " FOREIGN KEY(cid) REFERENCES customer(cid) " +

