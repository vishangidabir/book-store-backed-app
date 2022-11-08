package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResposeDTO;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/cartdetails")
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/add")
    public ResponseEntity<ResposeDTO> addToCart(@RequestBody CartDTO cartDTO, @RequestParam String token){
        ResposeDTO responseDTO = new ResposeDTO("Add record  Success", cartService.addToCart(cartDTO,token));
        return new ResponseEntity<ResposeDTO>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResposeDTO> getAll(){
        ResposeDTO respnseDTO = new ResposeDTO("Here are all the Cart Items..." , cartService.getAll());
        return new ResponseEntity<ResposeDTO>(respnseDTO,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResposeDTO> getById(@PathVariable int id){
        ResposeDTO respnseDTO = new ResposeDTO("Here are all the Cart Items..." , cartService.getById(id));
        return new ResponseEntity<ResposeDTO>(respnseDTO,HttpStatus.OK);
    }


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ResposeDTO> removeFromCart(@RequestParam String token, @PathVariable int id){
        ResposeDTO respnseDTO = new ResposeDTO("Here are all the Cart Items..." , cartService.removeById(id,token));
        return new ResponseEntity<ResposeDTO>(respnseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/empty")
    public ResponseEntity<ResposeDTO> emptyCart(){
        ResposeDTO responseDTO = new ResposeDTO("Deleting all the cart Itmens",cartService.emptyCart());
        return new ResponseEntity<ResposeDTO>(responseDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/emptybyid/{token}")
    public ResponseEntity<ResposeDTO> emptyCartById(@RequestParam String token){
        ResposeDTO responseDTO = new ResposeDTO("Deleting all the cart Itmens",cartService.deleteByUserId(token));
        return new ResponseEntity<ResposeDTO>(responseDTO,HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResposeDTO> updateQuantity(@RequestParam String token,@RequestBody CartDTO cartDTO,@PathVariable int id){
        ResposeDTO responseDTO = new ResposeDTO("Deleting all the cart Itmens",cartService.updateQuantity(token,cartDTO,id));
        return new ResponseEntity<ResposeDTO>(responseDTO,HttpStatus.ACCEPTED);
    }

}
