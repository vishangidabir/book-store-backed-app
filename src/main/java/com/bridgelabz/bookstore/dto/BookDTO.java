package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class BookDTO {

    public String bookName;
    public String authorName;
    public Integer price;
    public Integer quantity;
    public String bookDescription;
    public String bookImg;
}
