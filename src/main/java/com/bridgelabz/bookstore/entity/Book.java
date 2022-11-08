package com.bridgelabz.bookstore.entity;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.boot.jackson.JsonComponent;

import javax.persistence.*;

@Entity
@Data
@ToString
@RequiredArgsConstructor
@Table(name = "book_Details")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    public int bookID;
    private String bookName;
    private String authorName;
    public float price;
    public int quantity;
    private String bookDescription;
    private String bookImg;

    public Book(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookImg = bookDTO.getBookImg();
    }

    public Book(int id, BookDTO bookDTO) {
        this.bookID = id;
        this.bookName = bookDTO.bookName;
        this.price = bookDTO.price;
        this.authorName = bookDTO.authorName;
        this.quantity = bookDTO.quantity;
        this.bookImg = bookDTO.bookImg;
    }

    public Book(Book book) {
        this.bookID = book.bookID;
        this.bookName = book.bookName;
        this.price = book.price;
        this.authorName = book.authorName;
        this.quantity = book.quantity;
        this.bookImg = book.bookImg;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }
}
