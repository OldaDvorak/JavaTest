package com.example.demo;

import com.example.demo.Dto.MessageDto;
import com.example.demo.Service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class MessageController {

    private MessagesService messagesService;

    @Autowired
    public MessageController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<MessageDto>> getAllMessages() {
        return ResponseEntity.ok(messagesService.getAllMessages());
    }

    @RequestMapping(value = "/messages/{authorName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<MessageDto>> getMessagesByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(messagesService.getMessagesByAuthor(author));
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MessageDto> createMessage(MessageDto message) {
        return ResponseEntity.ok(messagesService.createMessages(message));
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<MessageDto> editMessage(@PathVariable int messageId, @RequestBody MessageDto message) throws SQLException {
        return ResponseEntity.ok(messagesService.editMessage(message));
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MessageDto> getMessageDetail(@PathVariable int messageId) {
        return ResponseEntity.ok(messagesService.getMessageDetail(messageId));
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteMessage(@PathVariable int messageId) {
        messagesService.deleteMessage(messageId);
    }
}
