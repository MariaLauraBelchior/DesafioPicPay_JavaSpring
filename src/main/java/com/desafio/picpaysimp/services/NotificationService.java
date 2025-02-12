package com.desafio.picpaysimp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.desafio.picpaysimp.domain.user.User;
import com.desafio.picpaysimp.dtos.NotificationDTO;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message ) throws Exception{
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v2/authorize", notificationRequest, String.class);

        if(notificationResponse.getStatusCode() == HttpStatus.OK){
            System.out.println("Erro ao enviar notificação"); 
            throw new Exception("Serviço fora do ar");
        }
    }
} 