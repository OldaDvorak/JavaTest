package com.example.demo.Service.Impl;

import com.example.demo.DAO.Message;
import com.example.demo.DAO.MessageRepository;
import com.example.demo.Dto.MessageDto;
import com.example.demo.Service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagesImpl implements MessagesService {


    private MessageRepository messageRepository;

    @Autowired
    public MessagesImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageDto createMessages(MessageDto message) {
        return createMessageDto(messageRepository.saveMessage(createMessageDAO(message)));
    }

    @Override
    public List<MessageDto> getAllMessages() {
        return messageRepository.getAllMessages().stream().map(this::createMessageDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getMessagesByAuthor(String author) {
        return messageRepository.getMessagesByAuthor(author).stream().map(this::createMessageDto).collect(Collectors.toList());
    }

    @Override
    public MessageDto editMessage(MessageDto message) throws SQLException {
        return createMessageDto(messageRepository.editMessage(createMessageDAO(message)));
    }

    @Override
    public MessageDto getMessageDetail(int messageId) {
        return createMessageDto(messageRepository.getMessageDetail(messageId));
    }

    @Override
    public void deleteMessage(int messageId) {
        messageRepository.deleteMessage(messageId);
    }

    private MessageDto createMessageDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setMessageId(message.getMessageId());
        dto.setMessageSubject(message.getMessageSubject());
        dto.setMessage(message.getMessage());
        dto.setMessageAuthor(message.getMessageAuthor());
        return null;
    }

    private Message createMessageDAO(MessageDto dto) {
        Message message = new Message();
        message.setMessageId(dto.getMessageId());
        message.setMessage(dto.getMessage());
        message.setMessageSubject(dto.getMessageSubject());
        message.setMessageAuthor(dto.getMessageAuthor());
        return message;
    }

}
