package org.example.impl;

import org.example.dao.ShoppingCartDAO;
import org.example.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public class ShoppingCartDAOImpl implements ShoppingCartDAO {



    @Override
    public ShoppingCart save(ShoppingCart cart) {
        return null;
    }

    @Override
    public Optional<ShoppingCart> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<ShoppingCart> findAll() {
        return null;
    }

    @Override
    public List<ShoppingCart> findByOrderStatus(ShoppingCart status) {
        return null;
    }

    @Override
    public List<ShoppingCart> findByReference(ShoppingCart customer) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
