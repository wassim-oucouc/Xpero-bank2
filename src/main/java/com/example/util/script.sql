CREATE TABLE Users (
                       id UUID PRIMARY KEY,
                       firstname VARCHAR(255) NOT NULL,
                       lastname VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       address TEXT,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE Clients (
                         id SERIAL PRIMARY KEY,
                         firstname VARCHAR(255) NOT NULL,
                         lastname VARCHAR(255) NOT NULL,
    email VARCHAR(80),
    cin varchar(50) NOT NULL,
    address varhar(50) NOT NULL,
                         salaire DECIMAL(15,2)
);

CREATE TABLE Managers(


)INHERITS (Users);


CREATE TABLE Tellers(

)INHERITS (Users);

CREATE TABLE Auditor(

)INHERITS (Users);

CREATE TABLE Admin(

)INHERITS (Users);

CREATE TABLE Role(
                     id SERIAL PRIMARY KEY,
                     role_name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO Role (role_name) VALUES ('ADMIN'),('TELLER'),('MANAGER'),('AUDITOR');


CREATE TABLE AccountTypes (
                              id SERIAL PRIMARY KEY,
                              type_name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO AccountTypes (type_name) VALUES
                                         ('COURANT'), ('EPARGNE'), ('CREDIT');

CREATE TABLE Accounts(
                         id VARCHAR PRIMARY KEY,
                         client_id INTEGER NOT NULL,
                         is_active BOOLEAN DEFAULT TRUE,
                         type_id INTEGER NOT NULL,
                         solde DECIMAL(15,2) DEFAULT 0.00,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (client_id) REFERENCES Clients(id) ON DELETE CASCADE,
                         FOREIGN KEY (type_id) REFERENCES AccountTypes(id)
);


CREATE TABLE CreditTypes(
                            id SERIAL PRIMARY KEY,
                            type_name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO CreditTypes(type_name) VALUES
                                       ('SIMPLE'), ('COMPOSEE');


CREATE TABLE CreditStatus(
                             id SERIAL PRIMARY KEY,
                             status_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE ModeFeeRules (
                              id SERIAL PRIMARY KEY,
                              mode_name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO ModeFeeRules (mode_name) VALUES
                                         ('FIX'), ('PERCENT');

INSERT INTO CreditStatus (status_name) VALUES
                                           ('ACTIVE'), ('LATE'), ('CLOSED');

CREATE TABLE Fee_rules (
                           id SERIAL PRIMARY KEY,
                           operation_type VARCHAR(100) NOT NULL,
                           mode_id INTEGER NOT NULL,
                           value DECIMAL(10,2) NOT NULL,
                           currency VARCHAR(3) DEFAULT 'MAD',
                           is_active BOOLEAN DEFAULT TRUE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (mode_id) REFERENCES ModeFeeRules(id)
);


CREATE TABLE Credits(
                        id SERIAL PRIMARY KEY,
                        amount DECIMAL(15,2) NOT NULL,
                        duree INTEGER NOT NULL,
                        taux FLOAT NOT NULL,
                        fee_rule_id INTEGER,
                        justification TEXT,
                        is_active BOOLEAN DEFAULT FALSE,
                        credit_type_id INTEGER NOT NULL,
                        account_id UUID NOT NULL,
                        status_id INTEGER NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (credit_type_id) REFERENCES CreditTypes(id),
                        FOREIGN KEY (account_id) REFERENCES Accounts(id) ON DELETE CASCADE,
                        FOREIGN KEY (status_id) REFERENCES CreditStatus(id),
                        FOREIGN KEY (fee_rule_id) REFERENCES Fee_rules(id)
);



CREATE TABLE VirementStatus (
                                id SERIAL PRIMARY KEY,
                                status_name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO VirementStatus (status_name) VALUES
                                             ('SEETLED'), ('PENDING'), ('FAILED');

CREATE TABLE TransactionType (
                                 id SERIAL PRIMARY KEY,
                                 type_name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO TransactionType (type_name) VALUES
                                            ('DEPOSIT'), ('WITHDRAW'), ('TRANSFEROUT'), ('TRANSFERIN'), ('ETRANGER');


CREATE TABLE Transactions (
                              id SERIAL PRIMARY KEY,
                              amount DECIMAL(15,2) NOT NULL,
                              transfer_in_account_id UUID,
                              transfer_out_account_id UUID,
                              type_id INTEGER NOT NULL,
                              status_id INTEGER NOT NULL,
                              status_virement_status INTEGER,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              fee_rule_id UUID,
                              FOREIGN KEY (transfer_in_account_id) REFERENCES Accounts(id),
                              FOREIGN KEY (transfer_out_account_id) REFERENCES Accounts(id),
                              FOREIGN KEY (type_id) REFERENCES TransactionType(id),
                              FOREIGN KEY (status_virement_status) REFERENCES VirementStatus(id),
                              FOREIGN KEY (fee_rule_id) REFERENCES Fee_rules(id)
);



