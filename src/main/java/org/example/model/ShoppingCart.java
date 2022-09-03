package org.example.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class ShoppingCart {

    private int id;
    private LocalDateTime lastUpdate;
    private String orderStatus;
    private String deliveryAddress;
    private String customerReference;
    private boolean paymentApproved;

    public ShoppingCart() {
    }

    public ShoppingCart(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ShoppingCart(LocalDateTime lastUpdate, String orderStatus, String deliveryAddress, String customerReference) {
        setLastUpdate(lastUpdate);
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.customerReference = customerReference;
    }

    //    public ShoppingCart(String customerReference){
//        this.customerReference = customerReference;
//    }

    public ShoppingCart(int id, LocalDateTime lastUpdate, String orderStatus, String deliveryAddress, String customerReference) {
        this.id = id;
        setLastUpdate(lastUpdate);
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.customerReference = customerReference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = LocalDateTime.now();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public boolean isPaymentApproved() {
        return paymentApproved;
    }

    public void setPaymentApproved(boolean paymentApproved) {
        this.paymentApproved = paymentApproved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return id == that.id && paymentApproved == that.paymentApproved && Objects.equals(lastUpdate, that.lastUpdate) && Objects.equals(orderStatus, that.orderStatus) && Objects.equals(deliveryAddress, that.deliveryAddress) && Objects.equals(customerReference, that.customerReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastUpdate, orderStatus, deliveryAddress, customerReference, paymentApproved);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", lastUpdate=" + lastUpdate +
                ", orderStatus='" + orderStatus + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", customerReference='" + customerReference + '\'' +
                ", paymentApproved=" + paymentApproved +
                '}';
    }
}
