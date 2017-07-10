package evoteam.ir.dbproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by programmer on 7/8/2017.
 */

public class OrdersActivity extends ListActivity {

    public static int position = 0 ;
    public static String[] products = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.product_activity);
        if (MainActivity.mDataBase == null)
            Log.d("zekiiiiii", "onCreate: zekiiiiiiiii");
        Log.d("zekooooooooo", "onCreate: zekoooooooo");


        List<Bundle> data = MainActivity.mDataBase.getTotalOrder(null) ;
        String [] products = new String [data.size()] ;
        for ( int i = 0 ; i < data.size() ; i++ )
            if (data.get(i).getInt(Constant.Order_OID_KEY) != 0) {
                products[i] = String.format ("%s\t\tOrdered By ID: %d " , data.get(i).getInt(Constant.Order_OID_KEY) ,
                        data.get(i).getInt(Constant.Order_CID_KEY)  );
            }
        this.products = products ;

        this.setListAdapter(new ArrayAdapter<String>(this , R.layout.list_layout , R.id.Products , products ));

    }
    @Override
    public void onListItemClick(ListView lv , View v , int position , long id) {
        Toast.makeText(this , "Hello"+position , Toast.LENGTH_SHORT).show();
        this.position = position ;
        Intent intent = new Intent(OrdersActivity.this , OrderItemActivity.class) ;
        startActivityForResult(intent , 0);
    }
}
