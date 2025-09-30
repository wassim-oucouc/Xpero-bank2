package com.example;
import com.example.controller.AccountController;
import com.example.controller.ClientController;
import com.example.controller.TransactionController;
import com.example.mapper.AccountMapper;
import com.example.mapper.ClientMapper;
import com.example.mapper.TransactionMapper;
import com.example.repository.AccountRepository;
import com.example.repository.ClientRepository;
import com.example.repository.TransactionRepository;
import com.example.repository.UserRepository;
import com.example.repository.implementation.InMemoryAccountRepository;
import com.example.repository.implementation.InMemoryClientRepository;
import com.example.repository.implementation.InMemoryTransactionRepository;
import com.example.repository.implementation.InMemoryUserRepository;
import com.example.service.AccountService;
import com.example.service.ClientService;
import com.example.service.TransactionService;
import com.example.service.UserService;
import com.example.ui.HomeMenu;
import com.example.ui.TellerMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ClientRepository clientRepository = new InMemoryClientRepository();
        UserRepository userRepository = new InMemoryUserRepository();
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(userRepository);
        ClientService clientService = new ClientService(clientRepository);
        ClientMapper clientMapper = new ClientMapper();
        AccountMapper accountMapper = new AccountMapper(clientMapper);
        AccountRepository accountRepository = new InMemoryAccountRepository(clientRepository,clientService);
        AccountService accountService = new AccountService(accountRepository);

        ClientController clientController = new ClientController(clientService,clientMapper);
        AccountController accountController = new AccountController(accountService,accountMapper);
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);
        TransactionMapper transactionMapper = new TransactionMapper(accountMapper);
        TransactionController transactionController = new TransactionController(transactionService,transactionMapper);
        TellerMenu tellerMenu = new TellerMenu(clientController,scanner,accountService,accountController,clientService,clientMapper,accountMapper,transactionController);


        HomeMenu homeMenu =  new HomeMenu(scanner,userService,tellerMenu);
        homeMenu.run();




    }
}
