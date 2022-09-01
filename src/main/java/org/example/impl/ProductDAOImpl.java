package org.example.impl;

import org.example.dao.ProductDAO;
import org.example.db.MySQLConnection;
import org.example.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO {


    private static final String SAVE = "INSERT INTO product (id, name, price) VALUES (?,?,?)";
    private static final String FINDBYID = " SELECT * FROM product WHERE id = ? ";
    private static final String FINDALL = " SELECT * FROM product ";
    private static final String FINDBYNAME = " SELECT * FROM product WHERE name LIKE ? ";
    private static final String FINDBYPRICE = " SELECT * FROM product WHERE price BETWEEN ? AND ? ";
    private static final String DELETEBYID = " DELETE FROM product WHERE id = ? ";


    @Override
    public Product save(Product product) {

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    @Override
    public Optional<Product> findById(int id) {
        Optional<Product> found = Optional.empty();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYID);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                found = Optional.of( new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return found;
    }

    @Override
    public List<Product> findAll() {

        List<Product> allInfo = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.myConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FINDALL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                allInfo.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("price")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allInfo;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> find = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYNAME);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                find.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return find;
    }

    @Override
    public List<Product> findByPriceBetween(int low, int high) {
        List<Product> findPrice = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.myConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYPRICE);
            preparedStatement.setInt(1, low);
            preparedStatement.setInt(2, high);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                findPrice.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findPrice;
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
