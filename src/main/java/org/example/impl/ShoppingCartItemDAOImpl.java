package org.example.impl;

import org.example.dao.ShoppingCartItemDAO;
import org.example.db.MySQLConnection;
import org.example.model.ShoppingCartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartItemDAOImpl implements ShoppingCartItemDAO {

    private static final String SAVE = "INSERT INTO shopping_cart_item VALUES (?,?,?,?,?)";
    private static final String FINDBYID = "SELECT * FROM shopping_cart_item WHERE id = ?";
    private static final String FINDALL = "SELECT * FROM shopping_cart_item";
    private static final String FINDBYCARTID = "SELECT * FROM shopping_cart_item WHERE shopping_cart_id = ? ";
    private static final String FINDBYPRODUCTID = "SELECT * FROM shopping_cart_item WHERE product_id = ? ";
    private static final String DELETEBYID = "SELECT FROM shopping_cart_item WHERE id = ?";

    ProductDAOImpl productDAO = new ProductDAOImpl();
    ShoppingCartDAOImpl shoppingCartDAO = new ShoppingCartDAOImpl();


    @Override
    public ShoppingCartItem save(ShoppingCartItem cartItem) {

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            preparedStatement.setInt(1, cartItem.getId());
            preparedStatement.setInt(2, cartItem.getAmount());
            preparedStatement.setDouble(3, cartItem.getTotalPrice());
            preparedStatement.setInt(4, cartItem.getItem().getId());
            preparedStatement.setInt(5, cartItem.getCart().getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItem;
    }

    @Override
    public Optional<ShoppingCartItem> findById(int id) {
        Optional<ShoppingCartItem> found = Optional.empty();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYID);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                found = Optional.of(new ShoppingCartItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("amount"),
                        resultSet.getInt("total_price"),
                        productDAO.findById(resultSet.getInt("product_id")).get(),
                        shoppingCartDAO.findById(resultSet.getInt("shopping_cart_id")).get()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return found;
    }

    @Override
    public List<ShoppingCartItem> findAll() {

        List<ShoppingCartItem> cartItems = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDALL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cartItems.add(new ShoppingCartItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("amount"),
                        resultSet.getInt("total_price"),
                        productDAO.findById(resultSet.getInt("product_id")).get(),
                        shoppingCartDAO.findById(resultSet.getInt("shopping_cart_id")).get()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItems;
    }

    @Override
    public List<ShoppingCartItem> findByCartId(int cartId) {
        List<ShoppingCartItem> cartItems = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYCARTID);
            preparedStatement.setInt(1,cartId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cartItems.add(new ShoppingCartItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("amount"),
                        resultSet.getInt("total_price"),
                        productDAO.findById(resultSet.getInt("product_id")).get(),
                        shoppingCartDAO.findById(resultSet.getInt("shopping_cart_id")).get()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItems;
    }

    @Override
    public List<ShoppingCartItem> findByProductId(int productId) {
        List<ShoppingCartItem> cartItem = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYPRODUCTID);
            preparedStatement.setInt(1,productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cartItem.add(new ShoppingCartItem(
                        resultSet.getInt("id"),
                        resultSet.getInt("amount"),
                        resultSet.getInt("total_price"),
                        productDAO.findById(resultSet.getInt("product_id")).get(),
                        shoppingCartDAO.findById(resultSet.getInt("shopping_cart_id")).get()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItem;
    }

    @Override
    public void delete(int id) {

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(DELETEBYID);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
