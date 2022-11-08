package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserData, Integer>{

    @Query(value = "SELECT * from book_store_app.users_details u where u.email = :bemail_id",nativeQuery = true)
    List<UserData> findByEmail(@Param("bemail_id") String bemail_id);

    @Query(value = "select * from book_store_app.users_details u where u.email= :bemail_id", nativeQuery = true)
    Optional<UserData> findByLoginId(String bemail_id);

    @Query(value = "select * from book_store_app.users_details where userid= :userID", nativeQuery = true)
    Optional<UserData> getUserById(String userID);

}
