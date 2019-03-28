package com.example.demo.DAO;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    public Message saveMessage(Message message) {
        return null;
    }

    public List<Message> getAllMessages() {
        Connection con = null;
        Statement stmt = null;
        List<Message> messageList = new ArrayList<>();
        try {
            con = DBConnection.getConnection();
            ResultSet rs = stmt.executeQuery("SELECT ID, MESSAGE_SUBJECT, MESSAGE, MESSAGE_AUTHOR FROM MESSEGES_TABLE");
            while (rs.next()) {
                messageList.add(createMessageFromRow(rs));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return messageList;
    }

    public List<Message> getMessagesByAuthor(String author) {
        Connection con = null;
        PreparedStatement prstmt = null;
        List<Message> messageList = new ArrayList<>();
        try {
            con = DBConnection.getConnection();
            prstmt = con.prepareCall("SELECT ID, MESSAGE_SUBJECT, MESSAGE, MESSAGE_AUTHOR FROM MESSEGES_TABLE WHERE MESSAGE_AUTHOR = ?");
            prstmt.setString(1, author);
            ResultSet rs = prstmt.executeQuery();
            while (rs.next()) {
                messageList.add(createMessageFromRow(rs));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prstmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return messageList;
    }

    public Message editMessage(Message message) throws SQLException {
        Connection con = null;
        CallableStatement clstmt = null;
        try {
            con = DBConnection.getConnection();
            clstmt = con.prepareCall("{call message_pckg.edit_message( ? , ? )}");
            clstmt.setInt(1, message.getMessageId());
            clstmt.setString(2, message.getMessage());
            clstmt.registerOutParameter(3, Types.VARCHAR);
            clstmt.execute();
            message.setMessage(clstmt.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clstmt.close();
            con.close();
        }
        return message;
    }

    public Message getMessageDetail(int messageId) {
        Connection con = null;
        PreparedStatement prstmt = null;
        Message message = new Message();
        try {
            con = DBConnection.getConnection();
            prstmt = con.prepareCall("SELECT ID, MESSAGE_SUBJECT, MESSAGE, MESSAGE_AUTHOR FROM MESSEGES_TABLE WHERE ID = ?");
            prstmt.setInt(1, messageId);
            ResultSet rs = prstmt.executeQuery();
            rs.next();
            message = createMessageFromRow(rs);
            return message;
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prstmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    private Message createMessageFromRow(ResultSet rs) throws SQLException {
        Message message = new Message();
        message.setMessageId(rs.getInt(1));
        message.setMessageSubject(rs.getString(2));
        message.setMessage(rs.getString(3));
        message.setMessageAuthor(rs.getString(4));
        return message;
    }
}
