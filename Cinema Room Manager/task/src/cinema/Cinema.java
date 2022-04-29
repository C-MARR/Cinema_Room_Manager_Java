package cinema;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    static int income = 0;
    static int ticketsSold = 0;
    static int totalIncome;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        if (rows % 2 == 0 && rows * seats > 60) {
            totalIncome = rows * seats * 9;
        } else if (rows * seats > 60) {
            totalIncome = (rows - 1) * seats * 9 + (seats * 8);
        } else {
            totalIncome = rows * seats * 10;
        }
        char[][] seatingChart = new char[rows][seats];
        for (char[] row : seatingChart) {
            Arrays.fill(row, 'S');
        }
        System.out.println();
        menu(rows, seats, seatingChart);
    }

    public static void printCinema(char[][] seatingChart) {
        System.out.print("\nCinema:\n ");
        for (int i = 1; i <= seatingChart[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int j = 0; j < seatingChart.length; j++) {
            System.out.print(j + 1);
            for (int k = 0; k < seatingChart[0].length; k++) {
                System.out.print(" " + seatingChart[j][k]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void purchaseTicket(int rows, int seats, char[][] seatingChart) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a row number:");
            int ticketRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int ticketSeat = scanner.nextInt();
            int ticketPrice;
            if (ticketRow > rows || ticketSeat > seats || ticketRow < 1 || ticketSeat < 1) {
                System.out.println("Wrong Input!");
                continue;
            } else if (seatingChart[ticketRow - 1][ticketSeat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            if (rows * seats <= 60 || ticketRow <= rows / 2) {
                Cinema.income += 10;
                Cinema.ticketsSold += 1;
                ticketPrice = 10;
            } else {
                Cinema.income += 8;
                Cinema.ticketsSold += 1;
                ticketPrice = 8;
            }
            System.out.println("\nTicket Price: $" + ticketPrice + "\n");
            seatingChart[ticketRow - 1][ticketSeat - 1] = 'B';
            break;
        }
    }

    public static void statistics(int rows, int seats) {
        double percent = (double) ticketsSold / (double) (rows * seats) * 100;
        String rounded = String.format("%.2f", percent);
        System.out.println("\nNumber of purchased tickets: "+ ticketsSold +
                "\nPercentage: " + rounded +
                "%\nCurrent income: $" + income +
                "\nTotal income: $" + totalIncome + "\n");
    }

    public static void menu(int rows, int seats, char[][] seatingChart) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            String option = scanner.nextLine();
            if (Objects.equals("1",option)) {
                printCinema(seatingChart);
            } else if (Objects.equals("2",option)) {
                purchaseTicket(rows, seats, seatingChart);
            } else if (Objects.equals("3",option)) {
                statistics(rows, seats);
            } else if (Objects.equals("0",option)) {
                break;
            }
        }
    }
}