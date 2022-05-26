package newver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println(getUserWithMaxMessages(LocalDate.of(2020, 6, 21), readMessagesFromCSV(),
                readUsersFromCSV()).getName());
    }

    public static User getUserWithMaxMessages(LocalDate date, List<Message> messages, List<User> users) {

        Map<LocalDate, Map<Integer, Integer>> res = new HashMap<LocalDate, Map<Integer, Integer>>();
        for (Message message : messages) {
            if (res.containsKey(message.getDate())) {
                Map<Integer, Integer> userIdsAndCount = res.get(message.getDate());
                Integer count = userIdsAndCount.get(message.getReceiverId());
                if (count != null) {
                    userIdsAndCount.put(message.getReceiverId(), ++count);
                } else {
                    userIdsAndCount.put(message.getReceiverId(), 1);
                }
                count = userIdsAndCount.get(message.getSenderId());
                if (count != null) {
                    userIdsAndCount.put(message.getSenderId(), ++count);
                } else {
                    userIdsAndCount.put(message.getSenderId(), 1);
                }

            } else {
                Map<Integer, Integer> userIdsAndCount = new HashMap<>();
                userIdsAndCount.put(message.getReceiverId(), 1);
                Integer count = userIdsAndCount.get(message.getSenderId());
                if (count == null) {
                    userIdsAndCount.put(message.getSenderId(), 1);
                } else {
                    userIdsAndCount.put(message.getSenderId(), ++count);
                }
                res.put(message.getDate(), userIdsAndCount);

            }
        }
        int id = res.get(date).entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        return users.stream().filter(user -> user.getId() == id).findFirst().get();

    }

    public static List<User> readUsersFromCSV() {
        List<User> users = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"));) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                line = line.replace("\"", "");
                String[] attributes = line.split(",");
                int id = Integer.parseInt(attributes[0]);
                String name = attributes[1];
                users.add(new User(id, name));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<Message> readMessagesFromCSV() {
        List<Message> messages = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("messages.csv"));) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                line = line.replace("\"", "");
                String[] attributes = line.split(",");
                LocalDate date = LocalDate.parse(attributes[0]);
                int senderId = Integer.parseInt(attributes[1]);
                int receiverId = Integer.parseInt(attributes[2]);

                messages.add(new Message(date, senderId, receiverId));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

}

