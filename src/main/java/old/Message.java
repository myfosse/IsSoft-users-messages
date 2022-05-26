package old;

public class Message {
    private String date;
    private String idFrom;
    private String idTo;

    public Message() {

    }

    public Message(String date, String idFrom, String idTo) {
        this.date = date;
        this.idFrom = idFrom;
        this.idTo = idTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getidFrom() {
        return idFrom;
    }

    public void setidFrom(String idFrom) {
        this.idFrom = idFrom;
    }

    public String getidTo() {
        return idTo;
    }

    public void setidTo(String idTo) {
        this.idTo = idTo;
    }

    @Override
    public String toString() {
        return "issoft.old.Message{" +
                "date=" + date +
                ", id From=" + idFrom +
                ", id To=" + idTo +
                '}';
    }
}