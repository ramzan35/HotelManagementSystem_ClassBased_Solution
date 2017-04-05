package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Rooms rooms = new Rooms();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println(" Hotel Miami");
            System.out.println(" <----------->");
//Display the options for the user to choose
            System.out.println("=============================================");
            System.out.println("A : Add a customer ");
            System.out.println("V : View all the rooms ");
            System.out.println("E : Display empty rooms ");
            System.out.println("D : Delete customer ");
            System.out.println("F : Find room from customer ");
            System.out.println("O : View rooms Ordered alphabetically ");
            System.out.println("S : Store program details to text file ");
            System.out.println("L : Load program data back from the file");
            System.out.println("=============================================");
            String command = sc.next();
//converting all inputs to lowercase to use as a switch case
            String newCommand = command.toLowerCase();
//choosing the case according to user’s input
            switch (newCommand) {
                case "a":
                    rooms.addCustomers();
                    break;
                case "v":
                    rooms.displayRooms();
                    break;
                case "e":
                    rooms.displayEmptyRooms();
                    break;
                case "d":
                    rooms.deleteCustomer();
                    break;
                case "f":
                    rooms.findCustomer();
                    break;
                case "o":
                    rooms.displayCustomersOrderly();
                    break;
                case "s":
                    rooms.saveRooms();
                    break;
                case "l":
                    rooms.loadRoams();
                    break;
                default:
                    System.out.println("Invalid selection.Please try again.");
            }
        }
    }
}
