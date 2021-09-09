package jdbctests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
            String dbUrl = "jdbc:oracle:thin:@34.229.106.124:1521:xe";
            String dbUsername = "hr";
            String dbPassword = "hr";

            //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement object
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        /*
        //move pointer to the first row
        resultSet.next();

        //getting information with column name
        System.out.println(resultSet.getString("region_name"));
        //getting info with column index(starts from 1)
        System.out.println(resultSet.getString(2));

        System.out.println(resultSet.getInt(1)+"-"+resultSet.getString("region_name"));

        //move pointer to second row
        resultSet.next();
        //getting information with column name
        System.out.println(resultSet.getString("region_name"));
        //getting info with column index(starts from 1)
        System.out.println(resultSet.getString(2));


       while (resultSet.next()) {
            System.out.println(resultSet.getInt("region_id")+"-"+resultSet.getString("region_name"));
        }
*/
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - " +resultSet.getString(2)+ " - "+resultSet.getString(3)+" - "+ resultSet.getString(4));
        }

        //how to find how many rows we have for the query
        //go to last row
        resultSet.last();
        //get the row count
         int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        //we need to move o first row to get full list since we are at the last row.
        resultSet.beforeFirst();
        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //you can execute another query without creating new object
        resultSet = statement.executeQuery("select * from regions");
        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User =" + dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}

