package roboticvacuum5;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RoboticVacuum5 {

    private Map<String, String> rooms;
    private String[] roomNames = {"room1", "room2"};
    private Random random = new Random();
    private int cleaningCycles = 1;
    private int maxCleaningCycles = 3;

    public RoboticVacuum5() {
        rooms = new HashMap<>();
        for (String roomName : roomNames) {
            rooms.put(roomName, (random.nextBoolean() ? "clean" : "dirty"));
        }
    }

    public void cleanRoom(String room) {
        System.out.println("Cleaning " + room + "...");
        rooms.put(room, "clean");
        System.out.println(room + " is now clean.");
    }

    public void runVacuum() {
        while (cleaningCycles < maxCleaningCycles) {
            String currentRoom = roomNames[random.nextInt(roomNames.length)];

            while (true) {
                String currentRoomStatus = rooms.get(currentRoom);

                // Clean the current room
                System.out.println("Vacuum starting in " + currentRoom);
                if (currentRoomStatus.equals("dirty")) {
                    cleanRoom(currentRoom);
                } else {
                    System.out.println(currentRoom + " is already clean.");
                }

                currentRoom = (currentRoom.equals("room1") ? "room2" : "room1");
                currentRoomStatus = rooms.get(currentRoom);

                System.out.println("Vacuum starting in " + currentRoom);
                if (currentRoomStatus.equals("dirty")) {
                    cleanRoom(currentRoom);
                } else {
                    System.out.println(currentRoom + " is already clean.");
                }

                System.out.println("--------------------------");

                
                if (cleaningCycles == maxCleaningCycles) {
                    System.out.println("Vacuum has completed 3 cleaning cycles. Shutting down.");
                    return;
                }

                cleaningCycles++;

                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        RoboticVacuum5 vacuum = new RoboticVacuum5();
        vacuum.runVacuum();
    }
}