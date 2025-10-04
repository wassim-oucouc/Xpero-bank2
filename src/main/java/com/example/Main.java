package com.example;
import com.example.controller.AccountController;
import com.example.controller.ClientController;
import com.example.controller.CreditController;
import com.example.controller.TransactionController;
import com.example.mapper.*;
import com.example.repository.*;
import com.example.repository.implementation.*;
import com.example.service.*;
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
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        AccountRepository accountRepository = new InMemoryAccountRepository(clientRepository,clientService);
        AccountService accountService = new AccountService(accountRepository);
        ClientController clientController = new ClientController(clientService,clientMapper);
        TransactionService transactionService = new TransactionService(transactionRepository);
        Fee_ruleMapper feeRuleMapper = new Fee_ruleMapper();
        TransactionMapper transactionMapper = new TransactionMapper(accountMapper,feeRuleMapper);
        AccountController accountController = new AccountController(accountService,accountMapper,transactionService,transactionMapper);
        TransactionController transactionController = new TransactionController(transactionService,transactionMapper);
        CreditRepository creditRepository = new InMemoryCreditRepository();
        CreditService creditService = new CreditService(creditRepository);
        CreditMapper creditMapper = new CreditMapper(accountMapper);
        CreditController creditController = new CreditController(creditService,creditMapper);
        TellerMenu tellerMenu = new TellerMenu(clientController,scanner,accountService,accountController,clientService,clientMapper,accountMapper,transactionController,creditController,creditService);


        HomeMenu homeMenu =  new HomeMenu(scanner,userService,tellerMenu);
        homeMenu.run();




    }
}
