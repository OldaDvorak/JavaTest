package com.example.demo.Service;

import com.example.demo.Dto.MessageDto;

import java.util.List;

public interface MessagesService {

    MessageDto createMessage(MessageDto message);

    /**Get all saved messages
     * @return
     */
    List<MessageDto> getAllMessages();

    List<MessageDto> getMessagesByAuthor(String author);

    MessageDto editMessage(MessageDto message);
}
