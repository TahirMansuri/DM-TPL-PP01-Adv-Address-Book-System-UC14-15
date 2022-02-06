package com.bridgelabz;

import java.util.*;

public class AddressBookMain {
    public static void main(String[] args) {

        //Welcome message for Users
        System.out.println("Welcome to Address Book System Problem Developed by Tahir Mansuri.");

        int choice; //Variable for user Choice

        //Show Menu for user to Select Operation on AddressBook
        do {
            System.out.println("***** ADDRESS BOOK MANAGEMENT *****");
            System.out.println("1. ADD NEW ADDRESSBOOK\n2. EDIT ADDRESSBOOK\n3. DELETE ADDRESSBOOK" +
                    "\n4. DISPLAY ADDRESSBOOKS\n5. SELECT ADDRESSBOOK\n6. SEARCH CONTACT \n7. VIEW CITY & STATE WISE PERSON " +
                    "\n8. SAVE ADDRESSBOOK TO FILE \n9. ACCESS ADDRESSBOOK FROM FILE \n10. SAVE ADDRESSBOOK TO CSV FILE \n11. ACCESS ADDRESSBOOK FROM CSV FILE " +
                    "\n12. SAVE ADDRESSBOOK TO GSON FILE \n13. ACCESS ADDRESSBOOK FROM GSON FILE " +
                    "\n14. EXIT");
            System.out.println("Please Select the Operation Number : ");
            choice = AddressBookRepo.userInput();

            switch (choice) {
                case 1:
                    AddressBookRepo.addNewAddressBook();    //Adding New Address Book to System
                    break;
                case 2:
                    //Edit Address Book Details
                    //addressBook.editContact();
                    break;
                case 3:
                    AddressBookRepo.deleteAddressBook();    //Delete the Address Book Details
                    break;
                case 4:
                    AddressBookRepo.displayAddressBooks();  //Show Contact Details
                    break;
                case 5:
                    AddressBookRepo.selectAddressBook();
                    break;
                case 6:
                    AddressBookRepo.searchPerson();
                    break;
                case 7:
                    AddressBookRepo.viewPerson();
                    break;
                case 8:
                    //System.out.println("Thank You for using Address Book System.");
                    AddressBookRepo.saveAddressBooktoFile();
                    break;
                case 9:
                    //System.out.println("Thank You for using Address Book System.");
                    AddressBookRepo.accessAddressBookfromFile();
                    break;
                case 10:
                    //System.out.println("Thank You for using Address Book System.");
                    AddressBookRepo.saveAddressBooktoCSVFile();
                    break;
                case 11:
                    //System.out.println("Thank You for using Address Book System.");
                    AddressBookRepo.accessAddressBookfromCSVFile();
                    break;
                case 12:
                    //System.out.println("Thank You for using Address Book System.");
                    AddressBookRepo.saveAddressBooktoJSONFile();
                    break;
                case 13:
                    //System.out.println("Thank You for using Address Book System.");
                    AddressBookRepo.accessAddressBookfromJSONFile();
                    break;
                case 14:
                    System.out.println("Thank You for using Address Book System.");
                    break;
                default:
                    System.out.println("Please Select the Operation between 1 to 6 only.");
                    break;
            }
        }while( choice != 14 );
    }
}