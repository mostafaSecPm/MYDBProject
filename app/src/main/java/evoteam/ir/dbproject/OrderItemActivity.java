package evoteam.ir.dbproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

/**
 * Created by programmer on 7/9/2017.
 */

public class OrderItemActivity extends AppCompatActivity implements ListView.OnItemLongClickListener  , View.OnClickListener{

    String [] orders = null ;
    int lastIID = 0 ;
    int OID = 0 ;
    ListView list = null ;
    EditText addText = null ;
    Button addButton = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderitemback_layout);

        int postion = OrdersActivity.position;
        String [] products = OrdersActivity.products ;
        Log.d("item", "onCreate: " + products[postion]);
        String []  orderItems = products[postion].split("\t") ;
        Log.d("item", "onCreate: " + orderItems[0]);
        String orderItem = orderItems[0] ;
        OID = Integer.parseInt(orderItem) ;

        List<Bundle> full = MainActivity.mDataBase.getTotalOrderItems(null) ;
        int [] iids = new int [full.size()] ;
        for ( int i = 0 ; i < full.size() ; i++ ){
            iids[i] = full.get(i).getInt (Constant.OrderITME_IID_KEY) ;
        }
        for ( int i = 0 ; i < iids.length-1 ; i++ )
            for ( int j = i+1 ; j  < iids.length ; j++ )
                if ( iids[i] > iids[j]){
                    int temp = iids [j] ;
                    iids[j] = iids[i] ;
                    iids[i] = temp ;
                }
        lastIID = iids[iids.length-1]+1 ;

        String WhereClause = String.format ("%s = %s" , Constant.Order_OID_KEY , orderItem) ;

        List<Bundle> items = MainActivity.mDataBase.getTotalOrderItems(WhereClause) ;
        Log.d("query", "onCreate: " + items);
        orders = new String [items.size()] ;

        for ( int i = 0 ; i < items.size() ; i++ ){
            Bundle bundle = items.get(i) ;
            orders[i]= String.format("%d  %d  %d  %d" , bundle.getInt(Constant.OrderITME_IID_KEY) ,
                    bundle.getInt(Constant.OrderITME_OID_KEY) , bundle.getInt(Constant.OrderITME_PID_KEY) ,
                    bundle.getInt(Constant.OrderITME_QTY_KEY)) ;
        }


        addText = (EditText) findViewById(R.id.addText) ;
        addButton = (Button) findViewById(R.id.okButton) ;
        addButton.setOnClickListener(this);

        list = (ListView) findViewById(R.id.OrderList) ;
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orders);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        String iid = orders[position].split("  ")[0] ;
        Log.d("iid", "onItemLongClick: " + iid);

        String [] temp = new String [orders.length-1] ;

        for ( int i = 0 ; i < position ; i++ )
            temp[i] = orders[i] ;
        for ( int i = position+1 ; i < orders.length ; i++ )
            temp[i-1] = orders[i] ;

        orders = temp ;

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orders);
        list.setAdapter(adapter);

        String query = String.format("%s = %s" , Constant.OrderITME_IID_KEY , iid);
        MainActivity.mDataBase.delete(query);

        return false ;
    }

    @Override
    public void onClick(View v) {
        String addString = new String () ;
        String listAdd = new String () ;

        addString = addText.getText().toString() ;
        Log.d("ok", "onClick: ok " + addString);

        listAdd = String.format("%d  %d  %s" , lastIID , OID , addString) ;

        String [] temp = new String[orders.length+1] ;
        for (int i = 0 ; i < orders.length ; i++)
            temp[i] = orders[i] ;
        temp[temp.length-1] = listAdd ;

        orders = temp ;
        for ( int i = 0 ; i < orders.length ; i++ )
            Log.d("ok", "onClick: ok " + orders[i]);


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orders);
        list.setAdapter(adapter);

        String [] addParas = addString.split(" ") ;
        Bundle add = new Bundle() ;
        add.putInt(Constant.OrderITME_IID_KEY , lastIID );
        add.putInt(Constant.OrderITME_OID_KEY , OID );
        add.putInt(Constant.OrderITME_PID_KEY , Integer.parseInt( addParas[0]) );
        add.putInt(Constant.OrderITME_QTY_KEY , Integer.parseInt( addParas[1]) );
        Log.d("add", "onClick: " + add);
        MainActivity.mDataBase.addOrderItem(add);

        addText.setText("");
        addText.setHint("Enter pid qty seprated by space ;");


    }
}
