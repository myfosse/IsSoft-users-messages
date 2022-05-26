package old;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class Main {
    public static void main(String[] args) throws IOException {

        List<User> userArrayList = new ArrayList<>();
        ICsvBeanReader iCsvBeanReaderUser = new CsvBeanReader(new FileReader("users.csv"), CsvPreference.STANDARD_PREFERENCE);
        String[] mapping = new String[]{"id", "name"};
        CellProcessor[] procsUser = getProcessorsUsers();
        User user;
        while ((user = iCsvBeanReaderUser.read(User.class, mapping, procsUser)) != null) {
            userArrayList.add(user);
        }
        iCsvBeanReaderUser.close();

        List<Message> messageArrayList = new ArrayList<>();
        ICsvBeanReader csvBeanReaderMessage = new CsvBeanReader(new FileReader("messages.csv"), CsvPreference.STANDARD_PREFERENCE);
        String[] mappingMessage = new String[]{"date", "idFrom", "idTo"};
        CellProcessor[] procsMessage = getProcessorsMessages();
        Message message;
        while ((message = csvBeanReaderMessage.read(Message.class, mappingMessage, procsMessage)) != null) {
            messageArrayList.add(message);
        }
        csvBeanReaderMessage.close();

        ArrayList<Message> answer = new ArrayList<>();
        for (int i = 0; i < messageArrayList.size(); i++) {
            if (messageArrayList.get(i).getDate().equals("2020-06-21")) {
                answer.add(messageArrayList.get(i));
            }
        }

        Integer temp;
        Map<String, Integer> map = new HashMap<>();
        for (Message value : answer) {
            if (map.containsKey(value.getidFrom())) {
                temp = map.get(value.getidFrom());
                temp++;
                map.put(value.getidFrom(), temp);
            } else {
                map.put(value.getidFrom(), 1);
            }
            if (map.containsKey(value.getidTo())) {
                temp = map.get(value.getidTo());
                temp++;
                map.put(value.getidTo(), temp);
            } else {
                map.put(value.getidTo(), 1);
            }
        }

        int max = 0;
        String id = " ";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (max <= entry.getValue()) {
                max = entry.getValue();
                id = entry.getKey();
            }
        }

        for (User user1 : userArrayList) {
            if (user1.getId().equals(id)) {
                System.out.println(user1);
            }
        }
    }

    private static CellProcessor[] getProcessorsUsers() {
        return new CellProcessor[]{
                new UniqueHashCode(),
                new NotNull()
        };
    }

    private static CellProcessor[] getProcessorsMessages() {
        return new CellProcessor[]{
                new NotNull(),
                new NotNull(),
                new NotNull()
        };
    }
}