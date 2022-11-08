package com.bridgelabz.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {


    public int bookId;
    public int quantity;
    double totalPrice;
}
