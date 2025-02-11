package com.desafio.picpaysimp.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.desafio.picpaysimp.domain.transaction.Transaction;
import com.desafio.picpaysimp.domain.user.User;
import com.desafio.picpaysimp.dtos.TransactionDTO;
import com.desafio.picpaysimp.repositories.TransactionRepository;

public class TransactionService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository tRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transactionDTO) throws Exception{
        User sender = this.userService.findUserById(transactionDTO.senderId());
        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.value());

        boolean isAuthorized = this.authorizationTransaction(sender, transactionDTO.value());
        if(isAuthorized){
            throw new Exception("Tranzação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());
    }

    public boolean authorizationTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String msg = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(msg);
        }else return false;
    }
}

