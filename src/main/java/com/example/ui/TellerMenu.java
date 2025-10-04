package com.example.ui;

import com.example.controller.AccountController;
import com.example.controller.ClientController;
import com.example.controller.CreditController;
import com.example.controller.TransactionController;
import com.example.dto.AccountDTO;
import com.example.dto.ClientDTO;
import com.example.dto.CreditDTO;
import com.example.dto.TransactionDTO;
import com.example.entity.Account;
import com.example.entity.Client;
import com.example.enums.AccountType;
import com.example.enums.CreditStatus;
import com.example.enums.CreditType;
import com.example.enums.TransactionType;
import com.example.mapper.AccountMapper;
import com.example.mapper.ClientMapper;
import com.example.service.AccountService;
import com.example.service.ClientService;
import com.example.service.CreditService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TellerMenu {

    public ClientController clientController;
    public Scanner scanner;
    public AccountService accountService;
    public AccountController accountController;
    public ClientService clientService;
    public ClientMapper clientMapper;
    public AccountMapper accountMapper;
    public TransactionController transactionController;
    public CreditController creditController;
    public CreditService creditService;

    public TellerMenu(ClientController clientController, Scanner scanner, AccountService accountService, AccountController accountController, ClientService clientService, ClientMapper clientMapper, AccountMapper accountMapper, TransactionController transactionController,CreditController creditController,CreditService creditService) {
        this.clientController = clientController;
        this.scanner = scanner;
        this.accountService = accountService;
        this.accountController = accountController;
        this.clientService = clientService;
        this.clientMapper = clientMapper;
        this.accountMapper = accountMapper;
        this.transactionController = transactionController;
        this.creditController = creditController;
        this.creditService = creditService;
    }


    public void TellerMenu(ClientController clientController, Scanner scanner) {
        this.clientController = clientController;
        this.scanner = scanner;
    }


    public void createClient() {
        System.out.println("Entrer Prénom Client :");
        String firstName = this.scanner.nextLine();
        System.out.println("Entrer nom Client :");
        String lastName = this.scanner.nextLine();
        System.out.println("Entrer email Client :");
        String email = this.scanner.nextLine();
        System.out.println("entrer cin Client :");
        String cin = this.scanner.nextLine();
        System.out.println("entrer address Client :");
        String address = this.scanner.nextLine();
        System.out.println("entrer sallaire Client");
        BigDecimal sallaire = this.scanner.nextBigDecimal();

        ClientDTO clientDTO = new ClientDTO(firstName, lastName, email, address, cin, sallaire);

        Client clientCreated = this.clientController.addClient(clientDTO);

        if (clientCreated != null) {
            System.out.println("client created with success");
        } else {
            System.out.println("client not created");
        }
        System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
        this.scanner.nextLine();
    }

    public void viewAllClients() {
        List<ClientDTO> clients = this.clientController.getAllClients();
        System.out.println("--------- voici liste des clients -----------");
        System.out.printf("%-5s %-15s %-15s %-20s %-15s %-20s %-10s%n",
                "ID", "Prénom", "Nom", "Email", "CIN", "Adresse", "Salaire");
        System.out.println("-------------------------------------------------------------------------------");

        for (ClientDTO client : clients) {
            System.out.printf("%-5s %-15s %-15s %-20s %-15s %-20s %-10.2f%n", client.getId(), client.getName(), client.getLastname(), client.getEmail(), client.getAddress(), client.getCin(), client.getSallaire());

        }
        System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
        this.scanner.nextLine();
    }

    public void createAccount() {
        String CIN;
        do {
            System.out.println("Entrer CIN Client :");
            CIN = this.scanner.nextLine();
            if (this.clientService.getClientByCin(CIN) == null) {
                System.out.println("CIN Incorrect!");
            }
        } while (this.clientService.getClientByCin(CIN) == null);
        Boolean check;
        int type;
        do {

            System.out.println("Choisi Type Du Compte Client");
            System.out.println("1 : COURANT");
            System.out.println("2 : EPARGNE");
            System.out.println("3 : CREDIT");
            type = this.scanner.nextInt();
            check = this.accountService.checkAccountTypeClient(type, CIN);
        } while (check == false);
        Client client = this.clientService.getClientByCin(CIN);
        System.out.println(client.toString());

        ClientDTO clientDTO = this.clientMapper.ToDTO(client);
        System.out.println(clientDTO.toString());

        String account_type = this.accountService.getIdAccountTypeByString(type);

        System.out.println(account_type);

        Account account = new Account(client, AccountType.valueOf(account_type), true, new BigDecimal(0), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

        System.out.println(account.getClient());

        AccountDTO accountDTO = this.accountMapper.TODTO(account);

        System.out.println(accountDTO.toString());

        this.accountController.createAccount(accountDTO);

        System.out.println("compte bien créer!");

        System.out.println("numero compte client : " + account.getId());
        System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
        this.scanner.nextLine();
    }

    public void closeClient() {
        String CIN;
        do {
            System.out.println("Entrer CIN Client :");
            CIN = this.scanner.nextLine();
            if (this.clientService.getClientByCin(CIN) == null) {
                System.out.println("CIN Incorrect!");
            }
        } while (this.clientService.getClientByCin(CIN) == null);

        String close = this.clientController.closeClient(CIN);

        if (close != null) {
            System.out.println("client closed success");
        }
        System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
        this.scanner.nextLine();

    }

    public void getAllAccounts() {
        List<AccountDTO> accountDTOS = this.accountController.getAllAccounts();
        System.out.println("--------- voici liste des accounts -----------");

        System.out.printf("%-5s %-10s %-10s %-25s %-25s %-15s %-15s %-15s %-10s%n",
                "ID", "Actif", "Solde", "Créé le", "Modifié le", "Prénom", "Nom", "CIN", "ClientID");
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        for (AccountDTO account : accountDTOS) {
            System.out.printf("%-5s %-10s %-10.2f %-25s %-25s %-15s %-15s %-15s %-10d%n",
                    account.getId(),
                    account.getActive(),
                    account.getSolde(),
                    account.getCreatedAt(),
                    account.getUpdatedAt(),
                    account.getClient().getName(),
                    account.getClient().getLastname(),
                    account.getClient().getCin(),
                    account.getClient().getId()
            );
        }
        System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
        this.scanner.nextLine();


    }

    public void closeAccount(){
        System.out.println("--------- La Fermeture Compte Client ---------");
        String accountNumber;
        do {
            System.out.println("Entrer Numero Compte Pour La Fermeture :");
            accountNumber = this.scanner.nextLine();
            this.accountService.checkAccountExists(accountNumber);
            if (this.accountService.checkAccountExists(accountNumber) == false) {
                System.out.println("Ce Compte N'existe Pas!");
            }
        } while (this.accountService.checkAccountExists(accountNumber) == false);
        this.accountController.closeAccount(accountNumber);
        System.out.println("compte bien fermer");
        System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
        this.scanner.nextLine();


    }


    public void Deposit() {
        System.out.println("------- Deposit Argent -------");
        String accountNumber;
        do {

            System.out.println("Entrer Numero Compte Client :");
            accountNumber = this.scanner.nextLine();
            this.accountService.checkAccountExists(accountNumber);
        } while (this.accountService.checkAccountExists(accountNumber) == null);
        System.out.println("Entrer Montant :");
        BigDecimal montant = this.scanner.nextBigDecimal();
        this.accountController.depositArgent(accountNumber, montant);
        Account account = this.accountService.getAccountByNumber(accountNumber);
        AccountDTO accountDTO = AccountMapper.TODTO(account);
        TransactionDTO transactionDTO = new TransactionDTO(montant, AccountMapper.TODTO(this.accountService.getAccountByNumber(accountNumber)), AccountMapper.TODTO(this.accountService.getAccountByNumber(accountNumber)), TransactionType.valueOf("DEPOSIT"), LocalDateTime.now(), LocalDateTime.now());
        this.transactionController.createTransaction(transactionDTO);
        System.out.println("montant bien deposé");
        System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
        this.scanner.nextLine();
    }

    public void Withdraw() {
        System.out.println("------- Withdraw Argent -------");
        String accountNumber;
        do {
            System.out.println("Entrer Numero Compte Client :");
            accountNumber = this.scanner.nextLine();
            this.accountService.checkAccountExists(accountNumber);
        } while (this.accountService.checkAccountExists(accountNumber) == null);
        Boolean check;
        BigDecimal montant;
        do {
            System.out.println("Entrer Montant :");
            montant = this.scanner.nextBigDecimal();
            check = this.accountService.checkBalanceAccount(montant, accountNumber);
            if (!check) {
                System.out.println("montant insuffisant avec votre solde!");
            }
        } while (!check);
        this.accountController.WithdrawArgent(accountNumber, montant);
        System.out.println("Withdraw a eté bien efféctuer");
        System.out.println("Vous Voulez Revenir Menu Principale ? Tapez Y");
        this.scanner.nextLine();
    }

    public void Transfer() {
        System.out.println("------- Transfer Argent -------");
        System.out.println("Choisi votre Type Transfer :");
        System.out.println("1 : TransferIn");
        System.out.println("2 : TransferOut");
        int inputChoix = scanner.nextInt();
        switch (inputChoix) {
            case 1:
                Boolean check1;
                String accountNumberDebit;
                String accountNumberDestinataire;
                do {
                    System.out.println("Entrer Numéro Compte Client Debité :");
                    accountNumberDebit = this.scanner.nextLine();
                    check1 = this.accountService.checkAccountExists(accountNumberDebit);
                } while (!check1);
                Boolean check2;
                do {
                    System.out.println("Entrer Numéro Compte Client Destinataire :");
                    accountNumberDestinataire = this.scanner.nextLine();
                    check2 = this.accountService.checkAccountExists(accountNumberDestinataire);
                } while (!check2);
                Boolean checkAccountBalance;
                BigDecimal montant;
                do {
                    System.out.println("Entrer Montant :");
                    montant = this.scanner.nextBigDecimal();
                    checkAccountBalance = this.accountService.checkBalanceAccount(montant, accountNumberDebit);
                }
                while (!checkAccountBalance);
                Account accountdebit = this.accountService.getAccountByNumber(accountNumberDebit);
                Account accountDestinataire = this.accountService.getAccountByNumber(accountNumberDestinataire);
                AccountDTO accountdebitDTO = AccountMapper.TODTO(accountdebit);
                AccountDTO accountDestinataireDTO = AccountMapper.TODTO(accountDestinataire);
                TransactionDTO transactionDTO = new TransactionDTO(montant, accountdebitDTO, accountDestinataireDTO, TransactionType.valueOf("TRANSFERIN"), LocalDateTime.now(), LocalDateTime.now());
                this.accountController.TransferArgent(accountNumberDebit, accountNumberDestinataire, montant, transactionDTO);
                break;
            case 2:

                break;

        }
    }

    public void creditApplication() {
        System.out.println("------- Credit Application -------");
        Boolean CheckAccountCredit;
        String accountNumber;
        do {
            System.out.println("Entrer Numero Compte Client :");
            accountNumber = this.scanner.nextLine();
            CheckAccountCredit = this.accountService.checkAccountTypeByNumber(accountNumber);
            if (!CheckAccountCredit) {
                System.out.println("Merci de Fournir un compte Credit");
            }
        } while (!CheckAccountCredit);

        System.out.println("Entrer Montant De Crédit :");
        BigDecimal montant = this.scanner.nextBigDecimal();
        this.scanner.nextLine();

        System.out.println("La Jusitification Du Crédit :");
        String justification = this.scanner.nextLine();

        BigDecimal divisor = new BigDecimal("12");
        BigDecimal resultMonthlyamount = montant.divide(divisor, 2, RoundingMode.HALF_UP);

        BigDecimal totalCreditSum = this.creditService.CalculateCreditClient(accountNumber);
        int id = this.accountService.getIdClientByAccountId(accountNumber);
        BigDecimal salaire = this.clientService.getSalaireById(id);

        BigDecimal sixtyPercent = salaire.multiply(BigDecimal.valueOf(0.6));
        BigDecimal resultSallaire = salaire.subtract(sixtyPercent);

        if(montant.compareTo(resultSallaire) > 0) {
            System.out.println("Votre salaire n'est pas suffisant pour ce crédit");
            return;
        }

        if(this.creditService.checkCreditLate(accountNumber)) {
            System.out.println("Demande Credit Refusé");
            return;
        }

        if(resultSallaire.compareTo(totalCreditSum) < 0) {
            System.out.println("Vous n'êtes pas eligible pour prendre ce crédit!");
            return;
        }
        AccountDTO accountDTO = AccountMapper.TODTO(this.accountService.getAccountByNumber(accountNumber));

        CreditDTO creditDTO = new CreditDTO();
        creditDTO.setAmount(montant);
        creditDTO.setMonthlyAmount(resultMonthlyamount);
        creditDTO.setJustification(justification);
        creditDTO.setCreditType(CreditType.SIMPLE);
        creditDTO.setCreditStatus(CreditStatus.ACTIVE);
        creditDTO.setDuree(12);
        creditDTO.setAccount(accountDTO);
        creditDTO.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        creditDTO.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));

        this.creditController.createCredit(creditDTO);

        System.out.println("Votre Demande De Crédit Avec succées");






    }
}

