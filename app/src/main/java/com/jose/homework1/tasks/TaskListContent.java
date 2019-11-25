package com.jose.homework1.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TaskListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Task> ITEMS = new ArrayList<Task>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Task item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

    private static Task createDummyItem(int position) {
        return new Task(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Task {
        public final String name;
        public final String username;
        public final String surname;
        public final String sound;
        public final String bday;
        public final String telephone;
        public int n;

        public Task(String id, String name, String surname, String sound, String bday, String telephone) {
            this.name = id;
            this.username = name;
            this.surname = surname;
            this.sound = sound;
            this.bday = bday;
            this.telephone = telephone;
            Random rand = new Random();
            this.n = rand.nextInt(6);
            this.n+=1;
        }

        public Task(String id, String name, String surname, String sound) {
            this.name = id;
            this.username = name;
            this.surname = surname;
            this.sound = sound;
            this.bday = "Not specified";
            this.telephone = "Not specified";
            Random rand = new Random();
            this.n = rand.nextInt(6);
            this.n+=1;
        }

        public Task(String id, String name, String surname) {
            this.name = id;
            this.username = name;
            this.surname = surname;
            this.sound = "Not specified";
            this.bday = "Not specified";
            this.telephone = "Not specified";
            Random rand = new Random();
            this.n = rand.nextInt(6);
            this.n+=1;
        }

        public int getN(){
            return this.n;
        }


        @Override
        public String toString() {
            return username;
        }
    }
}
