package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query(value = "select * from book_store_app.cart where user_Id = :userID and book_Id = :bookID",nativeQuery = true)
    Cart findCartsByUserIdAndBookId(int bookID, int userID);

    @Query(value = "delete from  book_store_app.cart where user_Id = :userID ",nativeQuery = true)
    Cart deleteByUserId( int userID);

}
