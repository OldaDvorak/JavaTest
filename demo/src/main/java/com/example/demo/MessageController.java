package com.example.demo;

import com.example.demo.Dto.MessageDto;
import com.example.demo.Service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
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

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<MessageDto> editMessage(@PathVariable int messageId, @RequestBody MessageDto message) {

    }
}
