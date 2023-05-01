Please provide review-comments for the code below:

```
@Component
public class MyAction {
	 // Please check indentations in general
	 public boolean debug = true;
    @Autowired
	 public DataSource ds;
	
	 // Instead of so many arguments, you can create a class like CustomerSearch and send the object as an argument
    public Collection getCustomers(String firstName, String lastName, String address, String zipCode, String city) throws SQLException { 
        // My take is to handle this transaction block inside a try-catch-finally structure 
        // and close the connection inside finally
        Connection conn = ds.getConnection(); 
        
        // What if all arguments are null? Returns all customers?
        String query = new String("SELECT * FROM customers where 1=1"); 
        
        // Instead of string operations, please use bind variables regarding security concerns such as SQL injection.
        // For example: (:firstName is null or first_name = :firstName). In this way, you won't need these if conditions
        if (firstName != null) {
            query = query + " and first_name = '" + firstName + "'";
        }
        if (firstName != null) { // lastName?
            query = query + " and last_name = '" + firstName + "'"; // lastName?
        }
        if (firstName != null) { //address?
            query = query + " and address = '" + address + "'";
        }
        if (firstName != null) { //zipCode?
            query = query + " and zip_code = '" + zipCode + "'";
        }
        if (firstName != null) { //city?
            query = query + " and city = '" + city + "'";
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List customers = new ArrayList(); // Use generic type by adding <Customer> to List type
        while (rs.next()) {
            Object[] objects = new Object[] { rs.getString(1), rs.getString(2) }; // Check null for getString()
            if (debug) print(objects, 4); // I recommend you to use log4j or sl4j like logger.debug(...) 
            // Add null checks
            customers.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        return customers;
    }

    public void print(Object[] s, int indent) {
        for (int i=0; i<=indent; i++) System.out.print(' '); // That should be for each object?
        printUpper(s); 
    }

    public static void printUpper(Object [] words){
        int i = 0;
        try {
            while (true){ // Please use a for loop
                if (words[i].getClass() == String.class) { // My take is to use instanceof to check the class and check if the object is null
                    String so = (String)words[i];;
                    so = so.toUpperCase();
                    System.out.println(so);
                } // Won't you print non-string values?
                i++;
            }
        } catch (IndexOutOfBoundsException e) { // You shouldn't handle this Runtime error. Instead use a for loop.
            //iteration complete
        }
    }
}
```
