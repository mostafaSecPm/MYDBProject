package evoteam.ir.dbproject;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;

/**
 * Created by programmer on 7/8/2017.
 */

public class ProductCursorWrapper extends CursorWrapper {
    public ProductCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Bundle getProducts() {
        Bundle data = new Bundle();

        int pid = getInt(getColumnIndex(DBLabSchema.ProductTable.Cols.pid));
        String pname = getString(getColumnIndex(DBLabSchema.ProductTable.Cols.pname));
        int price = getInt(getColumnIndex(DBLabSchema.ProductTable.Cols.price));

        data.putInt(Constant.Product_PID_KEY, pid);
        data.putString(Constant.Product_PNAME_KEY, pname);
        data.putInt(Constant.Product_PRICE_KEY, price);

        return data;
    }
}
