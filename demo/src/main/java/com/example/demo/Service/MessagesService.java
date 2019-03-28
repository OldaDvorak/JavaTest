package com.example.demo.Service;

import com.example.demo.Dto.MessageDto;

import java.sql.SQLException;
import java.util.List;

public interface MessagesService {

    /**Create and save new message
     * @param message
     * @return
     */
    MessageDto createMessages(MessageDto message);

    /**Get all saved messages
     * @return
     */
    List<MessageDto> getAllMessages();

    /**Filter messages by author
     * @param author
     * @return
     */
    List<MessageDto> getMessagesByAuthor(String author);

    /**Edit message
     * @param message
     * @return
     * @throws SQLException
     */
    MessageDto editMessage(MessageDto message) throws SQLException;

    /**Get detail of message
     * @param messageId
     * @return
     */
    MessageDto getMessageDetail(int messageId);

    /**Delete Message
     * @param messageId
     */
    void deleteMessage(int messageId);
}
