package com.example.demo.Service.Impl;

import com.example.demo.DAO.Message;
import com.example.demo.DAO.MessageRepository;
import com.example.demo.Dto.MessageDto;
import com.example.demo.Service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagesImpl implements MessagesService {


    private MessageRepository messageRepository;

    @Autowired
    public MessagesImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageDto createMessage(MessageDto message) {

        return null;
    }

    public List<MessageDto> getAllMessages() {
        return messageRepository.getAllMessages().stream().map(this::createMessageDto).collect(Collectors.toList());

    }

    public List<MessageDto> getMessagesByAuthor(String author) {
        return messageRepository.getMessagesByAuthor(author).stream().map(this::createMessageDto).collect(Collectors.toList());
    }

    public MessageDto editMessage(MessageDto message) {

    }

    private MessageDto createMessageDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setMessageId(message.getMessageId());
        dto.setMessageSubject(message.getMessageSubject());
        dto.setMessage(message.getMessage());
        dto.setMessageAuthor(message.getMessageAuthor());
        return null;
    }

}
