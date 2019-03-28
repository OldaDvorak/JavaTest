package com.example.demo.Service;

import com.example.demo.Dto.MessageDto;

import java.util.List;

public interface MessagesService {

    MessageDto createMessage(MessageDto message);

    List<MessageDto> getAllMessages();

    List<MessageDto> getMessagesByAuthor(String author);

    MessageDto editMessage(MessageDto message);
}
