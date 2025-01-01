package com.example.chatApp.repository;

import com.example.chatApp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndReceiver(String sender, String receiver);
    List<Message> findByReceiver(String receiver);
}
