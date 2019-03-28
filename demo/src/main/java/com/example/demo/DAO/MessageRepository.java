package com.example.demo.DAO;


import java.sql.SQLException;
import java.util.List;

public interface MessageRepository {

    Message saveMessage(Message message);

    List<Message> getAllMessages();

    List<Message> getMessagesByAuthor(String author);

    Message getMessageDetail(int messageId);

    Message editMessage(Message message) throws SQLException;
}
