package com.company;

/**
 * Created by Ramzan Dieze on 05/04/2017.
 */

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Rooms {
    //declaring and initializing array as empty rooms.
    String[] rooms = {"Available", "Available", "Available", "Available",
            "Available", "Available", "Available",
            "Available", "Available", "Available"};
    //declaring and initializing variables
    Scanner sc = new Scanner(System.in);
    File file = new File("src//rooms.txt");
    PrintWriter pw = null;
    FileWriter fw = null;

    //Display all customers data and their room number
    public void displayRooms() {
        for (int i = 0; i < rooms.length; i++) {
            System.out.print("Room " + (i + 1) + " : ");
            if (rooms[i].equals("Available")) {
                System.out.println("Available");
            } else
                System.out.println(rooms[i]);
        }
    }

    //book the room with necessary information
    public void addCustomers() {
        System.out.println("Enter room number: ");
        String sNmb;
//to handle the exceptions that might cause by invalid inputs
        try {
            sNmb = sc.next();
//validate the room number
            int nmb = Integer.parseInt(sNmb) - 1;
            if (!(nmb < 10 && nmb >= 0)) {
                System.out.println("Enter valid room number");
                addCustomers();
            } else {
//checking whether room is empty
                if (rooms[nmb].equals("Available")) {
                    System.out.println("Enter customer sur name: ");
                    String sirName = sc.next();
                    System.out.println("Enter customer name: ");
                    String name = sc.next();
                    System.out.println("Enter customer id: ");
                    String id = sc.next();
                    rooms[nmb] = sirName + " " + name + " " + id;
                } else {
                    System.out.println("That room is already occupied");
                    System.out.println("Do you want continue?");
                    String ans = sc.next();
                    if (ans.equalsIgnoreCase("yes"))
                        addCustomers();
                }
            }
        } catch (Exception e) {
            System.out.println("Enter valid room number");
            addCustomers();
        }
    }

    //display all empty rooms
    public void displayEmptyRooms() {
        boolean flag = true;
        System.out.println(" Empty rooms");
        System.out.println("<--------------->");
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].equals("Available")) {
                System.out.println("Room " + (i + 1));
                flag = false;
            }
        }
        if (flag) {
            System.out.println("*NO Empty rooms available");
        }
    }

    //delete customer details from the room
    public void deleteCustomer() {
        System.out.println("Enter the room number to delete customer ");
        String room = sc.next();
        try {
//validating the user input
            int rm = Integer.parseInt(room) - 1;
            if (rm >= 0 && rm < 10) {
                if (!(rooms[rm].equals("Available"))) {
                    rooms[(rm)] = "Available";
                    System.out.println("Deleted successfully");
                } else
                    System.out.println("This room is empty");
            } else {
                System.out.println("The number you enter is invlid please try again.");
                deleteCustomer();
            }
        } catch (Exception e) {
            System.out.println("You have enterned invalid input. Please try again.");
            deleteCustomer();
        }
    }

    //to find the customer details and room number by their name
    public void findCustomer() {
        boolean flag = false;
        System.out.println("Enter the customer name: ");
        String sName = sc.next();
        int i = 0;
        try {
            for (int j = 0; j < rooms.length; j++) {
                String name = rooms[i];
                i++;
                String[] data = name.split(" ", -1);
                String surName = data[0];
                String firstName = "";
                if (!(surName.equals("Available"))) {
                    firstName = data[1];
                }
                String name1 = surName + " " + firstName;
                if
                        ((sName.equalsIgnoreCase(name1)) || sName.equalsIgnoreCase(surName) ||
                        sName.equalsIgnoreCase(firstName)) {
                    System.out.println(name + " is in room " + (j + 1));
                    flag = true;
                }
            }
            if ((!(flag))) {
                System.out.println(sName + " is not found");
            }
        } catch (Exception e) {
            System.out.println("Error.Please Try again.");
        }
    }

    //to display customers name alphabetical order
    public void displayCustomersOrderly() {
        String[] dupRooms = new String[10];
        int[] roomNmb = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        try {
            for (int j = 0; j < rooms.length; j++) {
                String name = rooms[j];
                String[] data = name.split(" ", -1);
                String surName = data[0];
                dupRooms[j] = surName;
                String firstName = " ";
                if (!(surName.equals("Available"))) {
                    firstName = data[1];
                    dupRooms[j] = surName + " " + firstName;
                }
            }
        } catch (Exception e) {
        }
        for (int i = 0; i < (rooms.length - 1); i++) {
            for (int j = 0; j < ((rooms.length - 1)); j++) {
                if (dupRooms[j].compareTo(dupRooms[j + 1]) > 0) {
                    String temp = dupRooms[j];
                    int temp1 = roomNmb[j];
                    dupRooms[j] = dupRooms[j + 1];
                    roomNmb[j] = roomNmb[j + 1];
                    dupRooms[j + 1] = temp;
                    roomNmb[j + 1] = temp1;
                }
            }
        }
        for (int i = 0; i < rooms.length; i++) {
            if (!(dupRooms[i].equals("Available")))
                System.out.println("Room 0" + roomNmb[i] + " : " + dupRooms[i]);
        }
    }

    //save current customer details to a plain text file
    public void saveRooms() {
//to handle the exception that might cause
        try {
            fw = new FileWriter(file);                 // to open the file in append mode
            pw = new PrintWriter(fw, true); // second parameter is used to auto flush
            for (int i = 0; i < rooms.length; i++) {
                pw.println(rooms[i]);
            }
            System.out.println("Successfully written to the file.");
            fw.close();
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found,Please check the file" + file.getName());
        }
    }

    //retrieve back customer details from the text file
    public void loadRoams() {
        Scanner scx;
//to handle exception that might cuause by files
        try {
            scx = new Scanner(file);
            int i = 0;
            try {
                while (true) {
                    String name = scx.nextLine();
                    rooms[i] = name;
                    i++;
                }
            } catch (Exception e) {
            }
            System.out.println("File loaded to the array successfully");
        } catch (FileNotFoundException e1) {
            System.out.println("File not found ");
        }
    }
}