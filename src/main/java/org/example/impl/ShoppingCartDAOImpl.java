package org.example.impl;

import org.example.dao.ShoppingCartDAO;
import org.example.db.MySQLConnection;
import org.example.model.ShoppingCart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartDAOImpl implements ShoppingCartDAO {

    private static final String SAVE = " INSERT INTO shopping_cart (id, last_update, order_status, delivery_address, customer_reference) VALUES (?,?,?,?,?)";
    private static final String FINDBYID = " SELECT * FROM shopping_cart WHERE id = ? ";
    private static final String  FINDALL = " SELECT * FROM shopping_cart";
    private static final String FINDBYORDERSTATUS = "SELECT * FROM shopping_cart WHERE order_status LIKE ?";
    private static final String FINDBYREFERENCE = " SELECT * FROM shopping_cart WHERE customer_reference LIKE ?";
    private static final String DELETEBYID = " DELETE FROM shopping_cart WHERE id = ? ";


    @Override
    public ShoppingCart save(ShoppingCart cart) {

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            preparedStatement.setInt(1, cart.getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(cart.getLastUpdate()));
            preparedStatement.setString(3, cart.getOrderStatus());
            preparedStatement.setString(4, cart.getDeliveryAddress());
            preparedStatement.setString(5, cart.getCustomerReference());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    @Override
    public Optional<ShoppingCart> findById(int id) {
        Optional<ShoppingCart> found = Optional.empty();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYID);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                found = Optional.of( new ShoppingCart(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("last_update").toLocalDateTime(),
                        resultSet.getString("order_status"),
                        resultSet.getString("delivery_address"),
                        resultSet.getString("customer_reference")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return found;
    }

    @Override
    public List<ShoppingCart> findAll() {

        List<ShoppingCart> cartList = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDALL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cartList.add(new ShoppingCart(
                            resultSet.getInt("id"),
                            resultSet.getTimestamp("last_update").toLocalDateTime(),
                            resultSet.getString("order_status"),
                            resultSet.getString("delivery_address"),
                            resultSet.getString("customer_reference")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartList;
    }

    @Override
    public List<ShoppingCart> findByOrderStatus(ShoppingCart status) {

        List<ShoppingCart> cartList = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYORDERSTATUS);
            preparedStatement.setString(1, status.getOrderStatus());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cartList.add(new ShoppingCart(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("last_update").toLocalDateTime(),
                        resultSet.getString("order_status"),
                        resultSet.getString("delivery_address"),
                        resultSet.getString("customer_reference")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartList;
    }

    @Override
    public List<ShoppingCart> findByReference(ShoppingCart customer) {
        List<ShoppingCart> cartList = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYREFERENCE);
            preparedStatement.setString(1, customer.getCustomerReference());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cartList.add(new ShoppingCart(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("last_update").toLocalDateTime(),
                        resultSet.getString("order_status"),
                        resultSet.getString("delivery_address"),
                        resultSet.getString("customer_reference")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartList;
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
