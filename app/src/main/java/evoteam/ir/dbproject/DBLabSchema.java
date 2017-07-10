package evoteam.ir.dbproject;

/**
 * Created by programmer on 7/8/2017.
 */

public class DBLabSchema {
    public static final class CustomerTable {
        public static final String NAME = "customer" ;

        public static final class Cols {
            public static final String cid = "cid" ;
            public static final String cname = "cname" ;
        }
    }

    public static final class OrderTable {
        public static final String NAME = "order1" ;

        public static final class Cols {
            public static final String oid = "oid" ;
            public static final String odate = "odate" ;
            public static final String cid = "cid" ;
        }
    }

    public static final class OrderItemTable {
        public static final String NAME = "orderItem" ;

        public static final class Cols {
            public static final String iid = "iid" ;
            public static final String oid = "oid" ;
            public static final String qty = "qty" ;
            public static final String pid = "pid" ;
        }
    }

    public static final class ProductTable {
        public static final String NAME = "product" ;

        public static final class Cols {
            public static final String pid = "pid" ;
            public static final String pname = "pname" ;
            public static final String price = "price" ;
        }
    }
}
