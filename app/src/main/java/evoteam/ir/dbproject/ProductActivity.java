package evoteam.ir.dbproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by programmer on 7/7/2017.
 */

public class ProductActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.product_activity);
        if (MainActivity.mDataBase == null)
            Log.d("zekiiiiii", "onCreate: zekiiiiiiiii");
        Log.d("zekooooooooo", "onCreate: zekoooooooo");


        List<Bundle> data = MainActivity.mDataBase.getTotalProducts() ;
        String [] products = new String [data.size()-3] ;
        for ( int i = 3 ; i < data.size() ; i++ )
            if (data.get(i).getString(Constant.Product_PNAME_KEY) != null) {

                products[i - 3] = String.format ("%d ,%s\t\t%d  Rials" , data.get(i).getInt(Constant.Product_PID_KEY) ,
                        data.get(i).getString(Constant.Product_PNAME_KEY) ,
                        data.get(i).getInt(Constant.Product_PRICE_KEY)  );
            }
            else
                products[i] = "mostafa" ;


        this.setListAdapter(new ArrayAdapter<String>(this , R.layout.list_layout , R.id.Products , products ));






    }
}
