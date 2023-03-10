import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

interface IRobot {
    void moveToRoom(String room);
    void cleanRoom(String room);
    boolean isTimeToClean(String room);
}

interface IHouse {
    void addRoom(String room);
    List<String> getRooms();
    void updateLastCleaned(String room);
    long getLastCleaned(String room);
}

interface IUserInput {
    String getInput(String prompt);
    String getRandomRoom(List<String> rooms);
}

class Robot implements IRobot {
    private String currentRoom;
    private IHouse house;

    public Robot(IHouse house) {
        this.house = house;
    }

    @Override
    public void moveToRoom(String room) {
        System.out.println("Moving to room " + room);
        currentRoom = room;
    }

    @Override
    public void cleanRoom(String room) {
        System.out.println("Cleaning room " + room);
        house.updateLastCleaned(room);
    }

    @Override
    public boolean isTimeToClean(String room) {
        long lastCleaned = house.getLastCleaned(room);
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastCleaned;
        return elapsedTime >= 24 * 60 * 60 * 1000; // clean once a day
    }
}

class House implements IHouse {
    private List<String> rooms;
    private List<Long> lastCleanedTimes;

    public House() {
        rooms = new ArrayList<>();
        lastCleanedTimes = new ArrayList<>();
    }

    @Override
    public void addRoom(String room) {
        rooms.add(room);
        lastCleanedTimes.add(0L);
    }

    @Override
    public List<String> getRooms() {
        return rooms;
    }

    @Override
    public void updateLastCleaned(String room) {
        int roomIndex = rooms.indexOf(room);
        lastCleanedTimes.set(roomIndex, System.currentTimeMillis());
    }

    @Override
    public long getLastCleaned(String room) {
        int roomIndex = rooms.indexOf(room);
        return lastCleanedTimes.get(roomIndex);
    }
}

class UserInput implements IUserInput {
    private Scanner scanner;

    public UserInput() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public String getRandomRoom(List<String> rooms) {
        Random random = new Random();
        int index = random.nextInt(rooms.size());
        return rooms.get(index);
    }
}