package evoteam.ir.dbproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by programmer on 7/10/2017.
 */

public class addOrderActivity extends AppCompatActivity implements View.OnClickListener   {

    EditText cidText ;
    EditText pidText ;
    EditText qtyText ;
    Button addButton ;
    TextView oidText ;

    int lastOid = 0 ;
    int iid = 0 ;
    String [] cids ;
    String [] pids ;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addorder_layout);

        cidText = (EditText) findViewById(R.id.cidNumberText) ;
        cidText.setTextColor(getResources().getColor(R.color.BLACK));
        pidText = (EditText) findViewById(R.id.pidNumberText) ;
        pidText.setTextColor(getResources().getColor(R.color.BLACK));
        qtyText = (EditText) findViewById(R.id.qtyNumberText) ;
        qtyText.setTextColor(getResources().getColor(R.color.BLACK));
        oidText = (TextView) findViewById(R.id.oidNumberText) ;
        addButton = (Button) findViewById(R.id.okAddButton) ;

        List<Bundle> full = MainActivity.mDataBase.getTotalOrder(null) ;
        int [] oids = new int [full.size()] ;
        for ( int i = 0 ; i < full.size() ; i++ ){
            oids[i] = full.get(i).getInt (Constant.Order_OID_KEY) ;
        }
        for ( int i = 0 ; i < oids.length-1 ; i++ )
            for ( int j = i+1 ; j  < oids.length ; j++ )
                if ( oids[i] > oids[j]){
                    int temp = oids [j] ;
                    oids[j] = oids[i] ;
                    oids[i] = temp ;
                }

        lastOid = oids[oids.length-1] +1 ;
        oidText.setText(String.format("%d" , lastOid));

        List<Bundle> full1 = MainActivity.mDataBase.getTotalOrderItems(null) ;
        int [] iids = new int [full1.size()] ;
        for ( int i = 0 ; i < full1.size() ; i++ ){
            iids[i] = full1.get(i).getInt (Constant.OrderITME_IID_KEY) ;
        }
        for ( int i = 0 ; i < iids.length-1 ; i++ )
            for ( int j = i+1 ; j  < iids.length ; j++ )
                if ( iids[i] > iids[j]){
                    int temp = iids [j] ;
                    iids[j] = iids[i] ;
                    iids[i] = temp ;
                }
        iid = iids[iids.length-1] +1 ;


        final List<Bundle> cidList = MainActivity.mDataBase.getTotalCustomers(null) ;
        cids = new String [cidList.size()] ;
        for ( int i = 0 ; i < cidList.size() ; i++ )
            cids[i] = String.format("%d" ,cidList.get(i).getInt(Constant.Customer_CID_KEY));

        cidText.addTextChangedListener(new TextWatcher() {
            boolean inIt = false ;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = s.toString() ;
                if ( ss.equals("") == false) {
                    for ( int i = 0 ; i < cids.length ; i++) {
                        if (Integer.parseInt(ss) == Integer.parseInt(cids[i])) {
                            inIt = true;
                            break ;
                        } else {
                            inIt = false;
                        }
                    }
                } //End of for
                Log.d("s", "onTextChanged: " + inIt);
                if ( inIt == true )
                    cidText.setTextColor(getResources().getColor(R.color.GREEN));
                else
                    cidText.setTextColor(getResources().getColor(R.color.RED));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final List<Bundle> pidList = MainActivity.mDataBase.getTotalProducts() ;
        pids = new String [pidList.size()] ;
        for ( int i = 0 ; i < pidList.size() ; i++ )
            pids[i] = String.format("%d" ,pidList.get(i).getInt(Constant.Product_PID_KEY));

        pidText.addTextChangedListener(new TextWatcher() {
            boolean inIt = false ;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = s.toString() ;
                if ( ss.equals("") == false) {
                    for ( int i = 0 ; i < pids.length ; i++) {
                        if (Integer.parseInt(ss) == Integer.parseInt(pids[i])) {
                            inIt = true;
                            break ;
                        } else {
                            inIt = false;
                        }
                    }
                } //End of for
                Log.d("s", "onTextChanged: " + inIt);
                if ( inIt == true )
                    pidText.setTextColor(getResources().getColor(R.color.GREEN));
                else
                    pidText.setTextColor(getResources().getColor(R.color.RED));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        addButton.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        int cid = Integer.parseInt(cidText.getText().toString()) ;
        int pid = Integer.parseInt(pidText.getText().toString()) ;
        int qty = Integer.parseInt(qtyText.getText().toString()) ;

        Bundle order = new Bundle() ;
        order.putInt(Constant.Order_CID_KEY , cid );
        order.putInt(Constant.Order_OID_KEY , lastOid );
        MainActivity.mDataBase.addOrder(order);

        Bundle orderItem = new Bundle( ) ;
        orderItem.putInt(Constant.OrderITME_IID_KEY , iid);
        orderItem.putInt(Constant.OrderITME_OID_KEY , lastOid);
        orderItem.putInt(Constant.OrderITME_PID_KEY , pid);
        orderItem.putInt(Constant.OrderITME_QTY_KEY , qty);
        MainActivity.mDataBase.addOrderItem(orderItem);

        Toast.makeText(this , "please add more items in show orders section." , Toast.LENGTH_SHORT).show();

        finishActivity(0);

    }
}
