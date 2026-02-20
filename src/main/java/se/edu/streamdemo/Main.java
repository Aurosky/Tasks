package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        printWelcom();
        Datamanager dataManager = new Datamanager("./data/data.txt"); // relative path
        // save file, path ie. C:\\Users\\Deskpot...
        ArrayList<Task> tasksData = dataManager.loadData();

//        System.out.println("Printing all data ...");
//        printAllData(tasksData);
//        printDataUsingStreams(tasksData);

//        System.out.println("Printing deadlines ...");
//        printDeadlines(tasksData);
//        printDeadlinesUsingStreams(tasksData);

        System.out.println("Total number of deadlines(iteration): " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines(streams): " + countDeadlinesUsingStreams(tasksData));
        
    }

    private static void printWelcom(){
        System.out.println("Welcome to Task manager (using streams)");
    }
    
    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }
    
    private static int countDeadlinesUsingStreams(ArrayList<Task> tasks) {
        int counts = (int)tasks.stream()
                .filter((Task t) -> t instanceof Deadline)
                .count();
        return counts;
    }
    
    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using iteration");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }
    
    public static void printDataUsingStreams(ArrayList<Task> tasks){
        System.out.println("Printing data using streams");
        tasks.stream()
                .forEach(System.out::println);
    }
    
    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadline using iteration");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
    
    public static void printDeadlinesUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Printing data using streams");
        tasks.parallelStream()
                .filter((Task t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }
}
