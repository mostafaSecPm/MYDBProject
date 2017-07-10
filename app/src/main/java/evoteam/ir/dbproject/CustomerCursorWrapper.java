package evoteam.ir.dbproject;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;

/**
 * Created by programmer on 7/8/2017.
 */

public class CustomerCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CustomerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Bundle getCustomers  () {
        Bundle data = new Bundle() ;

        int cid = getInt(getColumnIndex(DBLabSchema.CustomerTable.Cols.cid)) ;
        String cname = getString(getColumnIndex(DBLabSchema.CustomerTable.Cols.cname)) ;

        data.putInt(Constant.Customer_CID_KEY , cid);
        data.putString(Constant.Customer_cname_KEY , cname);

        return data ;
    }
}
