package org.example;

import org.example.db.MySQLConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main( String[] args ) {

        try{
            MySQLConnection.myConnection();
            Statement statement = MySQLConnection.myConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

            while(resultSet.next()){
                System.out.print(" | id = " + resultSet.getInt("id"));
                System.out.print(" | name = " + resultSet.getString(2));
                System.out.print(" | price = " + resultSet.getBigDecimal(3));
                System.out.println();
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }
}
