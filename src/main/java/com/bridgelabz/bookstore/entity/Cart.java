package com.bridgelabz.bookstore.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int cartId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    public UserData userData;

    @ManyToOne
    @JoinColumn(name = "bookId")
    public Book book;

    public int quantity;
    public double totalPrice;

    public Cart(UserData userData, Book book, int quantity, double totalPrice)  {
        this.userData = userData;
        this.book = book;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }


}
