package org.example;

import org.example.db.MySQLConnection;
import org.example.impl.ProductDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main( String[] args ) {

        ProductDAOImpl productDAO = new ProductDAOImpl();
        productDAO.findAll().forEach(System.out ::println);

        System.out.println("/-------------");
        System.out.println(productDAO.findById(6).isPresent());

        System.out.println(productDAO.findByName("powder"));

        System.out.println(productDAO.findByPriceBetween(1,400));
//
//        productDAO.delete(2);

//        try{
//            Connection connection = MySQLConnection.myConnection();
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
//
//            while(resultSet.next()){
//                System.out.print(" | id = " + resultSet.getInt("id"));
//                System.out.print(" | name = " + resultSet.getString(2));
//                System.out.print(" | price = " + resultSet.getBigDecimal(3));
//                System.out.println();
//            }
//        } catch (SQLException ex){
//            ex.printStackTrace();
//        }

    }
}
