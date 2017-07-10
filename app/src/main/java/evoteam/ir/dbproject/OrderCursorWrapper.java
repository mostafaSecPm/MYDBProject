package evoteam.ir.dbproject;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;

/**
 * Created by programmer on 7/8/2017.
 */

public class OrderCursorWrapper extends CursorWrapper {

    public OrderCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Bundle getOrders() {
        Bundle data = new Bundle();

        int oid = getInt(getColumnIndex(DBLabSchema.OrderTable.Cols.oid));
        String odate = getString(getColumnIndex(DBLabSchema.OrderTable.Cols.odate));
        int cid = getInt(getColumnIndex(DBLabSchema.OrderTable.Cols.cid));

        data.putInt(Constant.Order_OID_KEY, oid);
        data.putString(Constant.Order_ODATE_KEY, odate);
        data.putInt(Constant.Order_CID_KEY, cid);

        return data;
    }
}
