package evoteam.ir.dbproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Mostafa" ;
    public static DBLabDBmanage mDataBase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataBase = new DBLabDBmanage(this) ;
///////////////////////////////////////////////////////////////////////////////////////
////        Adding product to product List
//        Bundle bundle = new Bundle() ;
//        bundle.putInt(Constant.OrderITME_IID_KEY, 9511001);
//        bundle.putInt(Constant.OrderITME_OID_KEY , 9510001);
//        bundle.putInt(Constant.OrderITME_QTY_KEY, 2);
//        bundle.putInt(Constant.Product_PID_KEY,1375521);
//        mDataBase.add(bundle);
//        Log.d(TAG, "onCreate: third added \n" + bundle.toString());
//////////////////////////////////////////////////////////////////////////////////////
        final Context context = this ;
        Button currentOrder = (Button) findViewById(R.id.ShowOrdersbutton) ;
        Button currentProduct = (Button) findViewById(R.id.ShowProductsbutton) ;
        Button addOrder = (Button) findViewById(R.id.AddOrderbutton) ;
        Button exit = (Button) findViewById(R.id.Exit) ;

        currentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(MainActivity.this , OrdersActivity.class) ;
                startActivityForResult(intent , 0);
                Log.d(TAG, "onClick: currentOrder button" );
            }
        });
        currentProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent( MainActivity.this , ProductActivity.class ) ;
                startActivityForResult(intent , 0);
                Log.d(TAG, "onClick: currentProduct button" );
            }
        });
        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent( MainActivity.this , addOrderActivity.class ) ;
                startActivityForResult(intent , 0);
                Log.d(TAG, "onClick: addOrder button" );
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                finish();
                Log.d(TAG, "onClick: exit button" );
            }
        });

    }


}
