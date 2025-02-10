package com.desafio.picpaysimp.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.picpaysimp.domain.user.User;
import com.desafio.picpaysimp.dtos.TransactionDTO;
import com.desafio.picpaysimp.repositories.TransactionRepository;

public class TransactionService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository tRepository;

    public void createTransaction(TransactionDTO transactionDTO) throws Exception{
        User sender = this.userService.findUserById(transactionDTO.senderId());
        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.value());
    }
}
