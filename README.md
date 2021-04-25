# gestion-compte-bancaire
sample bank account management

# Bank account kata
Think of your personal bank account experience When in doubt, go for the simplest solution

# Requirements
- Deposit and Withdrawal
- Account statement (date, amount, balance)
- Statement printing
 
# User Stories
##### US 1:
**In order to** save money  
**As a** bank client  
**I want to** make a deposit in my account  
 
##### US 2: 
**In order to** retrieve some or all of my savings  
**As a** bank client  
**I want to** make a withdrawal from my account  
 
##### US 3: 
**In order to** check my operations  
**As a** bank client  
**I want to** see the history (operation, date, amount, balance)  of my operations  


----------------------------
## Print account history

This is a sample to print all operations History of my account :(Mouvement is a Class that represent one Operation in the account, account must has one or more mouvements"operations" )

```
HISTORIQUE DU COMPTE numero XXXXXX
History [Operation=DEPOSIT| date=25/04/2021 17:46:15| amount=1000.00| balance=1000.00]
History [Operation=DEPOSIT| date=25/04/2021 17:46:15| amount=500.00| balance=1500.00]
History [Operation=WITHDRAWAL| date=25/04/2021 17:46:15| amount=-200.00| balance=1300.00]
```

----------------------------
## Print Statement

This is a sample to print the statement of account

```
Account [accountNumber=11111111111111 Date : 25/04/2021 18:09:52 , Solde =1300.00]
```


**NOTE:** : There is no main class.
- AcountService is a business class. All operations are done at this level (deposit, withdrawal and history)




# @Author
- Fayçal BACHER
- http://cv.fbacher.free.fr