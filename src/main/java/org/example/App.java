package org.example;

import org.example.dao.ShoppingCartItemDAO;
import org.example.impl.ProductDAOImpl;
import org.example.impl.ShoppingCartDAOImpl;
import org.example.impl.ShoppingCartItemDAOImpl;
import org.example.model.ShoppingCart;

public class App {
    public static void main( String[] args ) {

//-----------------product implementation----------------

        ProductDAOImpl productDAO = new ProductDAOImpl();

//        System.out.println(productDAO.save(new Product(12,"Brush B",90)));
        System.out.println("/-------------");
        productDAO.findAll().forEach(System.out ::println);

        System.out.println("/-------------");
        System.out.println(productDAO.findById(6));

        System.out.println("/-------------");
        System.out.println(productDAO.findByName("concealer"));

        System.out.println("/-------------");
        productDAO.findByPriceBetween(150,300).forEach(System.out ::println);

        System.out.println("/-------------");
//        productDAO.delete(12);

//-----------------shoppingCart implementation----------------

        ShoppingCartDAOImpl shoppingCartDAO = new ShoppingCartDAOImpl();

//        System.out.println(shoppingCartDAO.save(new ShoppingCart(6, LocalDateTime.now(),"delivered", "Germany","Arash Ventar")));

        System.out.println("/-------------");
        shoppingCartDAO.findAll().forEach(System.out :: println);

        System.out.println("/-------------");
        shoppingCartDAO.findByOrderStatus("complete").forEach(System.out::println);

        System.out.println("/-------------");
        shoppingCartDAO.findByReference("Adam Erikson").forEach(System.out::println);

        System.out.println("/-------------");
//        shoppingCartDAO.delete(6);


//-----------------shoppingCartItem implementation----------------

        ShoppingCartItemDAO shoppingCartItemDAO = new ShoppingCartItemDAOImpl();

        shoppingCartItemDAO.findAll().forEach(System.out::println);

        System.out.println("/-------------");
        System.out.println(shoppingCartItemDAO.findById(4));

        System.out.println("/-------------");
        shoppingCartItemDAO.findByCartId(5).forEach(System.out::println);

        System.out.println("/-------------");
        shoppingCartItemDAO.findByProductId(3).forEach(System.out::println);

    }
}
