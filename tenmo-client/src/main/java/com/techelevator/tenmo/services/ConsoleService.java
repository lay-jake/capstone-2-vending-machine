package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import io.cucumber.core.backend.Pending;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("---------------------------------------------------");
        System.out.println("                Welcome to TEnmo!                  ");
        System.out.println("---------------------------------------------------");
    }

    public void printLoginMenu() {
        System.out.println("---------------------------------------------------");
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println("---------------------------------------------------");
    }

    public void printMainMenu() {
        System.out.println("---------------------------------------------------");
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println("---------------------------------------------------");
    }

    public void printAvailableAccounts(List<Account> accounts) {
        if (accounts.size() > 1) {
            for (Account account : accounts) {
                int count = 1;
                System.out.println("The following accounts are available:");
                System.out.println(count + ". " + account.getAccountId());
            }
        }
    }

    public void displayBalanceForAccount(Account account) {
        String accountPrint = "Account Number: " + account.getAccountId();
        String balancePrint = "Total Balance: " + account.getBalance();
        System.out.println("---------------------------------------------------");
        System.out.println(accountPrint);
        System.out.println(balancePrint);
        System.out.println("---------------------------------------------------");
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    public void getUserList(List<User> users, User currentUser)
    {
        List<Integer> userIds = new ArrayList<>();
        System.out.println("---------------------------------------------------");
        System.out.println(" Users ID     User Name ");
        System.out.println("---------------------------------------------------");
        for (User user : users)
        {
            if (!user.getId().equals(currentUser.getId()))
            {
                System.out.println(user.getId() + "          " + user.getUsername());
                userIds.add(Math.toIntExact(user.getId()));
            }
        }
    }
    public void printTransHistory(int id, String name, BigDecimal amount,String status, Boolean isFrom){
        //Formatted print using isFrom logic to determine which line to print.
        if (isFrom) {
            System.out.printf("%4d         To:%-9s      $%.2f   %10s\n",id,name,amount,status);
        } else{
            System.out.printf("%4d       From:%-9s      $%.2f   %10s\n",id,name,amount,status);
        }
    }


    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }
    public void printHistoryHeader(){
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("Transfer");
        System.out.println("ID            From/To          Amount      Status");
        System.out.println("--------------------------------------------------");
    }
    public void printEmptyHistory(){
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("       No current transfers in the system         ");
        System.out.println("--------------------------------------------------");
        System.out.println();
    }
    public void printTransferSelection(){
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("---- Enter a transfer ID to view more details ----");
        System.out.println("--------- Press 0 to exit to the main menu -------");
        System.out.println("--------------------------------------------------");
        System.out.println();
    }
    public void exitMenuPrompt(){
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("--------- Press 0 to exit to the main menu -------");
        System.out.println("--------------------------------------------------");
        System.out.println();

    }

    public void printTransferDetails(Transfer transfer,String userNameFrom, String userNameTo){
            //Transfer Header
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println("--------------- Transfer Details -----------------");
            System.out.println("--------------------------------------------------");

            //Printing based on parameters received from method.
            System.out.println("ID: " + transfer.getId());
            System.out.println("From: " + userNameFrom);
            System.out.println("To: " + userNameTo);
            System.out.println("Transfer type: " + (transfer.getTypeId() == 1 ? "Request" : "Send"));

            //Check the transfer status, and print correct information.
            if (transfer.getStatusId() == 3){
                System.out.println("Status: Rejected");
            }else{
                System.out.println("Status: " + (transfer.getStatusId() == 1 ? "Pending" : "Approved"));
            }

            System.out.println("Amount: " + transfer.getAmount());
            System.out.println("--------------------------------------------------");
        }
        public void printPendingMenu(){
        //Pending menu approval/decline options
            System.out.println("--------------------------------------------------");
            System.out.println("1. Approve                                        ");
            System.out.println("2. Decline                                        ");
            System.out.println("0. Exit                                           ");
            System.out.println("--------------------------------------------------");
        }

    public void printTransferCreation(int transferId,int statusId){
        //Menu printed on creation of transfer
        System.out.println("--------------------------------------------------");
        System.out.println((statusId == 1) ? "Transfer successfully created" : "Transfer successful, funds have been sent");
        System.out.println("Confirmation number: " + transferId);
        System.out.println("Current Status: " +  (statusId == 1 ? "Pending" : "Approved"));
        System.out.println("--------------------------------------------------");
    }

}