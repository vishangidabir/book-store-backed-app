package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.exception.CustomException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    IUserService iUserService;
    @Autowired
    IBookService iBookService;

    public Object addToCart(CartDTO cartDTO, String token) {
        int id = (int) tokenUtility.decodeToken(token);
        Optional<UserData> userData = userRepository.findById(id);
        Optional<Book> bookData = iBookService.getBookById(cartDTO.getBookId());
        if (userData.isPresent() && bookData.isPresent()) {
            if (bookData.get().quantity >= cartDTO.getQuantity() && cartDTO.getQuantity() > 0) {
                Cart cart = cartRepository.findCartsByUserIdAndBookId(cartDTO.getBookId(), userData.get().userID);
                if (cart != null) {
                    Cart cartDetails = updateQuantity(token, cartDTO, cart.getCartId());
                    return cartDetails;
                } else {
                    double totalPrice = cartDTO.getQuantity() * bookData.get().getPrice();
                    Cart cartDetails = new Cart(userData.get(), bookData.get(), cartDTO.getQuantity(), totalPrice);
                    return cartRepository.save(cartDetails);
                }
            }throw (new CustomException("Book Out Of Stock"));
        }throw (new CustomException("Record not Found"));
    }


    public List<Cart> getAll() {
        List<Cart> cartDetailsModelList = cartRepository.findAll();
        return cartDetailsModelList;
    }

    public Cart getById(int id) {
        return cartRepository.findById(id).orElseThrow(() -> new CustomException("Book  with id " + id + " does not exist in database..!"));
    }

    public Object removeById(Integer id, String token) {
        UserData userRegistrationModule = iUserService.getUserById(token);
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent() && userRegistrationModule != null){
            cartRepository.delete(cart.get());
            return "Record is deleted with id ";
        }
        throw (new CustomException("Record not Found"));
    }

    public String emptyCart() {
        cartRepository.deleteAll();
        return "All Cart Item Deleted";
    }

    public String deleteByUserId(String token) {
        int id = (int) tokenUtility.decodeToken(token);
        cartRepository.deleteByUserId(id);
        return "Record Delete Successful";
    }

    public Cart updateQuantity(String token, CartDTO cartDTO, int id) {
        Optional<UserData> userData = userRepository.getUserById((token));
        if (cartRepository.findById(id).isPresent() && userData != null) {
            Cart cart = cartRepository.findById(id).get();
            cart.setQuantity(cartDTO.quantity);
            cart.setTotalPrice(cart.getQuantity() * cart.getBook().getPrice());
            return cartRepository.save(cart);
        } else throw new CustomException("No book found with the given id or you are not an admin user.");
    }

}
