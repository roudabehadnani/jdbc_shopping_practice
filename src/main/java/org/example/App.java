package org.example;

import org.example.impl.ProductDAOImpl;
import org.example.impl.ShoppingCartDAOImpl;
import org.example.model.Product;
import org.example.model.ShoppingCart;

public class App {
    public static void main( String[] args ) {

//-----------------product implementation----------------

        ProductDAOImpl productDAO = new ProductDAOImpl();

//        System.out.println(productDAO.save(new Product(12,"Brush B",90)));

        productDAO.findAll().forEach(System.out ::println);
        System.out.println("/-------------");
        System.out.println(productDAO.findById(6));
        System.out.println("/-------------");
        System.out.println(productDAO.findByName("concealer"));
        System.out.println("/-------------");
        productDAO.findByPriceBetween(150,300).forEach(System.out ::println);
//        productDAO.delete(12);

//-----------------shopping cart implementation----------------

        ShoppingCartDAOImpl shoppingCartDAO = new ShoppingCartDAOImpl();

//        System.out.println(shoppingCartDAO.save(new ShoppingCart(6, LocalDateTime.now(),"delivered", "Germany","Arash Ventar")));

        shoppingCartDAO.findAll().forEach(System.out :: println);

        System.out.println("/-------------");
        System.out.println(shoppingCartDAO.findByOrderStatus(new ShoppingCart("pending")));

        System.out.println("/-------------");
        // TODO: 1/09/2022
//        shoppingCartDAO.findByReference();

//        shoppingCartDAO.delete(6);




    }
}
