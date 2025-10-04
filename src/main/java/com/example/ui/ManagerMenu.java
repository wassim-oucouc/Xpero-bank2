package com.example.ui;

import com.example.controller.CreditController;
import com.example.service.AccountService;

import java.util.Scanner;

public class ManagerMenu {

    public Scanner scanner;
    public AccountService accountService;
    public CreditController creditController;

    public ManagerMenu(Scanner scanner,AccountService accountService,CreditController creditController)
    {
        this.scanner = scanner;
        this.accountService = accountService;
        this.creditController = creditController;
    }


    public void ApproveCredit() {
        Boolean CheckAccountCredit;
        String accountCredit;
        do {
            System.out.println("Compte Credit Client :");
            accountCredit = this.scanner.nextLine();
            CheckAccountCredit = this.accountService.checkAccountTypeByNumber(accountCredit);
            if (!CheckAccountCredit) {
                System.out.println("ce compte credit n'existe pas!");
            }
        } while (!CheckAccountCredit);
        this.creditController.acceptationCredit(accountCredit);
        System.out.println("Credit Du Client a éte bien accepter");


    }
}
