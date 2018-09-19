package com.finallab.account.summary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class OrderSummary {

    //@JsonProperty("id")
    //private BigInteger id;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("order_date")
    private Date orderDate;
    @JsonProperty("order_number")
    private Long orderNumber;
    @JsonProperty("total_price")
    private Double totalPrice;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("address_address_id")
    private Long addressId;

    public OrderSummary() {
    }

    public OrderSummary(Date orderDate, Long orderNumber, Double totalPrice, Long accountId, Long addressId) {
        this.orderDate = orderDate;
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.accountId = accountId;
        this.addressId = addressId;
    }

    /*public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }*/

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
