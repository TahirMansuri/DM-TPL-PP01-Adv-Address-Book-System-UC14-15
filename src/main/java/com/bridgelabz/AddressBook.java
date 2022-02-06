package com.bridgelabz;

import java.util.*;

public class AddressBook {

    //Scanner object for User Input
    Scanner sc = new Scanner(System.in);
    int choice;

    ArrayList<Contacts> contactList = new ArrayList<>();

    //Method to Add New Contact to AddressBook
    public void addNewContact() {
        Contacts contacts = new Contacts();
        System.out.println("Enter the Contact Details -");
        System.out.println("Enter the First Name :");
        contacts.setFirstName(sc.next());
        if(equalsFirstNames(contacts)) {
            System.out.println("The Contact with given First Name is Already Available.");
            //contactOptions();
        }
        System.out.println("Enter the Last Name :");
        contacts.setLastname(sc.next());
        System.out.println("Enter the Address :");
        contacts.setAddress(sc.next());
        System.out.println("Enter the City :");
        contacts.setCity(sc.next());
        System.out.println("Enter the State :");
        contacts.setState(sc.next());
        System.out.println("Enter the Zip Code :");
        contacts.setZip(sc.next());
        System.out.println("Enter the Phone Number :");
        contacts.setPhoneNo(sc.next());
        System.out.println("Enter the EMail ID :");
        contacts.setEmailID(sc.next());
        contactList.add(contacts);
    }

    //Method to Show the Contact Details
    public void displayContact() {
        for(Contacts contact : contactList) {
            if (contact.getFirstName() == null) {
                System.out.println("Contact Details Not Available");
            } else {
                System.out.println("Contact Details -");
                System.out.println("First Name : " + contact.getFirstName());
                System.out.println("Last Name : " + contact.getLastname());
                System.out.println("Address : " + contact.getAddress());
                System.out.println("City : " + contact.getCity());
                System.out.println("State : " + contact.getState());
                System.out.println("Zip Code : " + contact.getZip());
                System.out.println("Phone Number : " + contact.getPhoneNo());
                System.out.println("EMail ID : " + contact.getEmailID());
            }
        }
    }

    //Method to Edit the Existing Contact
    public void editContact() {
        //Get First Name to Edit the Contact
        System.out.println("Enter the First Name : ");
        String firstName = sc.next();

        //check if the Given User with First Name
        boolean isAvailable = false;
        for(Contacts contact : contactList) {
            if (firstName.equalsIgnoreCase(contact.getFirstName())) {
                isAvailable = true;
                    System.out.println("Enter the Last Name :");
                    contact.setLastname(sc.next());
                    System.out.println("Enter the Address :");
                    contact.setAddress(sc.next());
                    System.out.println("Enter the City :");
                    contact.setCity(sc.next());
                    System.out.println("Enter the State :");
                    contact.setState(sc.next());
                    System.out.println("Enter the Zip Code :");
                    contact.setZip(sc.next());
                    System.out.println("Enter the Phone Number :");
                    contact.setPhoneNo(sc.next());
                    System.out.println("Enter the EMail ID :");
                    contact.setEmailID(sc.next());
                    break;
            }
        }
        if(!isAvailable) {
            System.out.println("Contact Number Not Found.");
        }
    }

    //Method to Delete the Existing Contact
    public void deleteContact() {
        //Get First Name to Edit the Contact
        System.out.println("Enter the First Name : ");
        String firstName = sc.next();

        //check if the Given User with First Name
        boolean isAvailable = false;
        for(Contacts contact : contactList) {
            if (firstName.equalsIgnoreCase(contact.getFirstName())) {
                isAvailable = true;
                contactList.remove(contact);
                System.out.println("Contact Deleted.");
                break;
            }
        }
        if(!isAvailable) {
            System.out.println("Contact Number Not Found.");
        }
    }

    //Method to Check Duplicate Contact in Address Book
    public boolean equalsFirstNames(Contacts contacts) {
        for(Contacts contact : contactList) {
            if(contacts.equals(contact)) {
                return true;
            }
        }
        return false;
    }

    //Method to Sort Contacts by City, State and Zipcode
    public void sortContact() {
        Collections.sort(contactList, Comparator.comparing((Contacts p) -> p.getCity()));
        System.out.println("SORTED By CITY :");
        for (Contacts p : contactList) {
            System.out.println(p.getCity() +"\t"+ p.getFirstName());
        }

        Collections.sort(contactList, Comparator.comparing((Contacts p) -> p.getState()));
        System.out.println("SORTED By STATE :");
        for (Contacts p : contactList) {
            System.out.println(p.getState() +"\t"+ p.getFirstName());
        }

        Collections.sort(contactList, Comparator.comparing((Contacts p) -> p.getZip()));
        System.out.println("SORTED By ZIPCODE :");
        for (Contacts p : contactList) {
            System.out.println(p.getZip() +"\t"+ p.getFirstName());
        }
    }

    //Method to View City and State wise Person Name
    public void viewCityPerson() {
        Map<String, ArrayList<String>> cityPersonMap = new HashMap<String,ArrayList<String>>();
        Map<String, ArrayList<String>> statePersonMap = new HashMap<String,ArrayList<String>>();

        ArrayList<String> cityPersonList;
        ArrayList<String> statePersonList;
        System.out.println("City Wise Person Data :");
        for(Contacts contacts : contactList) {
            if(!cityPersonMap.containsKey(contacts.getCity())) {
                cityPersonList = new ArrayList<>();
                cityPersonList.add(contacts.getFirstName());
                cityPersonMap.put(contacts.getCity(),cityPersonList);
            } else {
                ArrayList<String> arr = cityPersonMap.get(contacts.getCity());
                arr.add(contacts.getFirstName());
                cityPersonMap.put(contacts.getCity(),arr);
            }
        }
        for(String key : cityPersonMap.keySet()) {
            ArrayList<String> personData = cityPersonMap.get(key);
            for(String data : personData)
            System.out.println(key +" : "+data);
        }
        System.out.println("State Wise Person Data :");
        for(Contacts contacts : contactList) {
            if(!statePersonMap.containsKey(contacts.getState())) {
                statePersonList = new ArrayList<>();
                statePersonList.add(contacts.getFirstName());
                statePersonMap.put(contacts.getState(),statePersonList);
            } else {
                ArrayList<String> arr = statePersonMap.get(contacts.getState());
                arr.add(contacts.getFirstName());
                statePersonMap.put(contacts.getState(),arr);
            }
        }
        for(String key : statePersonMap.keySet()) {
            ArrayList<String> personData = statePersonMap.get(key);
            for(String data : personData)
                System.out.println(key +" : "+data);
        }

        for(String key : cityPersonMap.keySet()) {
            ArrayList arr = cityPersonMap.get(key);
            System.out.println("No of Person in City "+key+" = "+arr.size());
        }
        for(String key : statePersonMap.keySet()) {
            ArrayList arr = statePersonMap.get(key);
            System.out.println("No of Person in State "+key+" = "+arr.size());
        }
    }


    public void contactOptions(AddressBook addressBook) {
        //Show Menu for user to Select Operation on AddressBook
        do {
            System.out.println("***** ADDRESS BOOK MANAGEMENT *****");
            System.out.println("1. ADD NEW CONTACT\n2. EDIT CONTACT\n3. DELETE CONTACT" +
                    "\n4. DISPLAY CONTACT\n5. SORT CONTACT \n6. EXIT");
            System.out.println("Please Select the Operation Number : ");
            choice = addressBook.sc.nextInt();

            switch (choice) {
                case 1:
                    addressBook.addNewContact();    //Adding New Contact Details
                    break;
                case 2:
                    addressBook.editContact();  //Edit Contact Details
                    break;
                case 3:
                    addressBook.deleteContact();    //Delete the Contact Details
                    break;
                case 4:
                    addressBook.displayContact();   //Show Contact Details
                    break;
                case 5:
                    addressBook.sortContact();   //Show Contact Details
                    break;
                case 6:
                    System.out.println("Thank You for Using Address Book.");
                    break;
                default:
                    System.out.println("Please Select the Operation between 1 to 5 only.");
                    break;
            }
        }while( choice != 6 );
    }
}