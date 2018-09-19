package com.finallab.account.model;

import com.finallab.account.summary.OrderSummary;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Account")
@SqlResultSetMapping(
        name = "AccountOrdersMapping",
        classes = @ConstructorResult(
                targetClass = OrderSummary.class,
                columns = {
                        //@ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "order_date", type = Date.class),
                        @ColumnResult(name = "order_number", type = Long.class),
                        @ColumnResult(name = "total_price", type = Double.class),
                        @ColumnResult(name = "account_id", type = Long.class),
                        @ColumnResult(name = "address_address_id", type = Long.class)
                }))

@NamedNativeQueries(value = {
        @NamedNativeQuery(
                resultSetMapping = "AccountOrdersMapping",
                name = "Account.findAllOrders",
                query = "select DATE(order_date) as order_date, orders.order_number, SUM(order_line_items.quantity*order_line_items.price) as total_price, orders.account_id, orders.address_address_id from orders, order_line_items where account_id = :id group by order_number, account_id, address_address_id, order_date  order by order_date;"
        )
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    //@JsonManagedReference
    @JoinColumn(name = "accountId")
    //@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Long addressId;

    public Account() {
    }

    public Account(String firstName, String lastName, String email, Long addressId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressId = addressId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long address) {
        this.addressId = addressId;
    }
}
