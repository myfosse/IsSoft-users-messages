package newver;

import java.time.LocalDate;
import java.util.Objects;

public class Message {
    private LocalDate date;
    private Integer senderId;
    private Integer receiverId;

    public Message(LocalDate date, Integer senderId, Integer receiverId) {
        this.date = date;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(date, message.date) &&
                Objects.equals(senderId, message.senderId) &&
                Objects.equals(receiverId, message.receiverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, senderId, receiverId);
    }
}
