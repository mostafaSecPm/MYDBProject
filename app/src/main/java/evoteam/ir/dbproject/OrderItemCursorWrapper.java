package evoteam.ir.dbproject;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;

/**
 * Created by programmer on 7/8/2017.
 */

public class OrderItemCursorWrapper extends CursorWrapper {

    public OrderItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Bundle getOrderItemss() {
        Bundle data = new Bundle();

        int iid = getInt(getColumnIndex(DBLabSchema.OrderItemTable.Cols.iid));
        int oid = getInt(getColumnIndex(DBLabSchema.OrderItemTable.Cols.oid));
        int qty = getInt(getColumnIndex(DBLabSchema.OrderItemTable.Cols.qty));
        int pid = getInt(getColumnIndex(DBLabSchema.OrderItemTable.Cols.pid));

        data.putInt(Constant.OrderITME_IID_KEY , iid);
        data.putInt(Constant.OrderITME_OID_KEY , oid);
        data.putInt(Constant.OrderITME_QTY_KEY , qty);
        data.putInt(Constant.OrderITME_PID_KEY , pid);

        return data;
    }
}
