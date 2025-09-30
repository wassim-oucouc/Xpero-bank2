package com.example.ui;

import com.example.controller.ClientController;
import com.example.dto.ClientDTO;
import com.example.entity.Client;
import com.example.entity.Session;
import com.example.entity.User;
import com.example.service.UserService;

import java.math.BigDecimal;
import java.util.Scanner;

import static com.example.config.DatabaseConnection.password;

public class HomeMenu {


    public final Scanner scanner;
    public UserService userService;
    public TellerMenu tellerMenu;


    public HomeMenu(Scanner scanner, UserService userService,TellerMenu tellerMenu) {
        this.scanner = scanner;
        this.userService = userService;
        this.tellerMenu = tellerMenu;
    }


    public void run() {
        System.out.println("Welcome To XperoBank");
        System.out.println("-----------------------");
        int valueIntFirst = 0;
        System.out.println("==========================================");
        System.out.println("               MENU PRINCIPAL           ");
        System.out.println("==========================================");
        System.out.println(" 1 :  Login");
        System.out.println(" 2 :  Exit");
        System.out.println("==========================================");
        System.out.print("➡ Veuillez choisir une option : ");
        Scanner inputValue = new Scanner(System.in);
        valueIntFirst = inputValue.nextInt();

        switch (valueIntFirst) {
            case 1:
                this.login();
                break;
            case 2:
                System.out.println("Good Bye!");
        }

    }

    public void tellerMenu() {
        User user = Session.getUser();
        int InputChoix;
        do {
            System.out.println(" Connecté en tant que : " + user.getName() + " " + user.getLastname());
            System.out.println("============menu============");
            System.out.println("1. add new client");
            System.out.println("2. update info client");
            System.out.println("3. view all clients");
            System.out.println("4. close client");
            System.out.println("5. Create account");
            System.out.println("6. List all accounts");
            System.out.println("7. Close account");
            System.out.println("8. Deposit");
            System.out.println("9. Withdraw");
            System.out.println("10. Transfer");
            System.out.println("11. credit applications");
            System.out.println("12. Logout");
            System.out.println("13. Exit");
            InputChoix = this.scanner.nextInt();
            String forskip = this.scanner.nextLine();

        switch (InputChoix) {
            case 1:
                this.tellerMenu.createClient();
                System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
                this.scanner.nextLine();
                break;
            case 3:
                this.tellerMenu.viewAllClients();
                System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
                this.scanner.nextLine();
                break;
            case 4:
                this.tellerMenu.closeClient();
                System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
                this.scanner.nextLine();
                break;
            case 5:
                this.tellerMenu.createAccount();
                System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
                this.scanner.nextLine();
                break;
            case 6:
                this.tellerMenu.getAllAccounts();
                System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
                this.scanner.nextLine();
                break;
            case 7:
                this.tellerMenu.closeAccount();
                System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
                this.scanner.nextLine();
                break;
            case 8 :
                this.tellerMenu.Deposit();
                System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
                this.scanner.nextLine();
                break;

        }
        } while (InputChoix != 13);
    }


    public void managerMenu() {

    }

    public void adminMenu() {

    }

    public void auditorMenu() {

    }


    public void login() {
        User user;
        do {
            System.out.println("===== LOGIN =====");
            System.out.print("➡ email : ");
            String email = scanner.nextLine();

            System.out.print("➡ Mot de passe : ");
            String password = scanner.nextLine();
            user = this.userService.login(email, password);


            if (user != null) {

                switch (user.getRole().name()) {
                    case "ADMIN":
                        this.adminMenu();
                        break;
                    case "TELLER":
                        this.tellerMenu();
                        break;
                    case "MANAGER":
                        this.managerMenu();
                        break;
                    case "AUDITOR":
                        this.auditorMenu();
                        break;
                }
            }
            if(user == null)
            {
                System.out.println("Email ou Mot De Passe Incorrect!");
            }
        }
            while (user == null) ;

    }
}
