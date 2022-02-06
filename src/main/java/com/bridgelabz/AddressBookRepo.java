package com.bridgelabz;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.*;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AddressBookRepo {
    static Map<String, AddressBook> addressBookMap = new HashMap<>();

    static Scanner sc = new Scanner(System.in);

    public static void addNewAddressBook() {
        System.out.println("Enter the Address Book Name :");
        String addressbookName = sc.next();
        if(addressBookMap.containsKey(addressbookName)) {
            System.out.println("Entered AddressBook is Already Available");
        } else {
            AddressBook addressBook = new AddressBook();
            addressBookMap.put(addressbookName,addressBook);
        }
    }

    public static void displayAddressBooks() {
        for(String key: addressBookMap.keySet()){
            System.out.println(key);
        }
    }

    public static void selectAddressBook() {
        displayAddressBooks();
        System.out.println("Enter the Address Book Name :");
        String addressbookName = sc.next();
        if(addressBookMap.containsKey(addressbookName)) {
            addressBookMap.get(addressbookName).contactOptions(addressBookMap.get(addressbookName));
        } else {
            System.out.println("Entered Address Book Name is Invalid");
        }
    }

    public static void editAddressBook() {
        displayAddressBooks();
        System.out.println("Enter the Address Book Name :");
        String addressbookName = sc.next();
        if(addressBookMap.containsKey(addressbookName)) {
//            System.out.println("Enter the New Name for Addressbook :");
//            String newName = sc.next();
//            addressBookMap.;
        } else {
            System.out.println("Entered Address Book Name is Invalid");
        }
    }

    public static void deleteAddressBook() {
        displayAddressBooks();
        System.out.println("Enter the Address Book Name :");
        String addressbookName = sc.next();
        if(addressBookMap.containsKey(addressbookName)) {
            addressBookMap.remove(addressbookName);
            System.out.println("Address Book is Deleted");
        } else {
            System.out.println("Entered Address Book Name is Invalid");
        }
    }

    public static final int userInput() {
        return sc.nextInt();
    }

    //Method to Search for Contact Name using City or State
    public static void searchPerson() {

        System.out.println("Enter the City or State to Search Contact : ");
        String cityState = sc.next();
        for(String addressBook : addressBookMap.keySet()) {
            for(Contacts contacts : addressBookMap.get(addressBook).contactList) {
                if(cityState.equalsIgnoreCase(contacts.getCity()) || cityState.equalsIgnoreCase(contacts.getState())) {
                    System.out.println(contacts.getFirstName() +" "+contacts.getLastname());
                }
            }
        }
    }

    public static void viewPerson() {
        displayAddressBooks();
        System.out.println("Enter the Address Book Name to View City wise Person List :");
        String addressbookName = sc.next();
        if(addressBookMap.containsKey(addressbookName)) {
            addressBookMap.get(addressbookName).viewCityPerson();
        } else {
            System.out.println("Entered Address Book Name is Invalid");
        }

    }

    public static void saveAddressBooktoFile() {
        for(String addressbookName : addressBookMap.keySet()) {
            Path path = Paths.get(addressbookName+".txt");
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<Contacts> contactsArrayList = addressBookMap.get(addressbookName).contactList;
            for(Contacts contacts : contactsArrayList) {
                stringBuilder.append(contacts.getFirstName() +","+ contacts.getLastname() +","+ contacts.getEmailID()
                        +","+ contacts.getPhoneNo() +","+ contacts.getAddress() +","+ contacts.getCity() +","+ contacts.getState()
                        +","+ contacts.getZip()+"\n");
            }
            //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File()));
            byte[] strByte = stringBuilder.toString().getBytes();
            try {
                Files.write(path,strByte);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void accessAddressBookfromFile() {
        //displayAddressBooks();
        System.out.println("Enter the Address Book Name to Access Data :");
        String addressbookName = sc.next();
        //Path filePath = Paths.get(addressbookName+".txt");
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(addressbookName+".txt"));
            String contactData;
            while((contactData = bufferedReader.readLine()) != null) {
                Contacts contacts = new Contacts();
                String[] separetData = contactData.split(",");
                contacts.setFirstName(separetData[0]);
                contacts.setLastname(separetData[1]);
                contacts.setEmailID(separetData[2]);
                contacts.setPhoneNo(separetData[3]);
                contacts.setAddress(separetData[4]);
                contacts.setCity(separetData[5]);
                contacts.setState(separetData[6]);
                contacts.setZip(separetData[7]);
                addressBookMap.get(addressbookName).contactList.add(contacts);
                //addressBookMap.put(addressbookName,addressBookMap.get(addressbookName).contactList.add(contacts));
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void saveAddressBooktoCSVFile() {
        for(String addressbookNames : addressBookMap.keySet()) {
//                File file = new File(addressbookNames+".csv");
//                try {
//                    // create FileWriter object with file as parameter
//                    FileWriter outputfile = new FileWriter(file);
//                    // create CSVWriter object filewriter object as parameter
//                    CSVWriter writer = new CSVWriter(outputfile);
//                    // adding header to csv
//                    String[] header = { "FName", "LName", "EMailID", "PhoneNo", "Address", "City", "State", "Zip" };
//                    writer.writeNext(header);
//                    // add data to csv
//                    for(Contacts contacts : addressBookMap.get(addressbookNames).contactList) {
//                        String[] data1 = { contacts.getFirstName(), contacts.getLastname(), contacts.getEmailID(), contacts.getPhoneNo()
//                        , contacts.getAddress(), contacts.getCity(), contacts.getState(), contacts.getZip()};
//                        writer.writeNext(data1);
//                    }
//                    // closing writer connection
//                    writer.close();
            try{
                File file = new File(addressbookNames+".csv");
                FileWriter fileWriter = new FileWriter(file);

                ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
                mappingStrategy.setType(Contacts.class);

                String[] Columns = {"firstName","lastname","address","city","state","zip","phoneNo","emailID"};
                mappingStrategy.setColumnMapping(Columns);

                StatefulBeanToCsvBuilder<Contacts> beanToCsvBuilder = new StatefulBeanToCsvBuilder(fileWriter);
                StatefulBeanToCsv beanToCsv = beanToCsvBuilder.withMappingStrategy(mappingStrategy).build();

                beanToCsv.write(addressBookMap.get(addressbookNames).contactList);

                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvRequiredFieldEmptyException e) {
                e.printStackTrace();
            } catch (CsvDataTypeMismatchException e) {
                e.printStackTrace();
            }
        }
    }

    public static void accessAddressBookfromCSVFile() {
        System.out.println("Enter the Address Book Name to Access Data :");
        String addressbookName = sc.next();
        try
        {
            Reader reader = Files.newBufferedReader(Paths.get(addressbookName+".csv"));

            // create csv bean reader
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Contacts.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            // iterate through users
            for (Contacts contacts : (Iterable<Contacts>) csvToBean) {
                addressBookMap.get(addressbookName).contactList.add(contacts);
                System.out.println("Contact : " + contacts.toString());
            }
            // close the reader
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAddressBooktoJSONFile() {
// create a writer
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("AddressBook.json"));
            for (Map.Entry<String, AddressBook> addressBookEntry : addressBookMap.entrySet()) {

                List<Contacts> contactlist = addressBookEntry.getValue().contactList;
                Gson gson = new Gson();
                Map<String, List<Contacts>> addressMap = new HashMap<>();
                addressMap.put(addressBookEntry.getKey(), new ArrayList<>());
                contactlist.stream().forEach(contact -> {
                    addressMap.get(addressBookEntry.getKey()).add(contact);
                });
                writer.write(gson.toJson(addressMap));

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void accessAddressBookfromJSONFile() {

        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("AddressBook.json"));
            //create JsonObject instance
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element: jsonArray) {

                if (element.isJsonObject()) {

                    JsonObject car = element.getAsJsonObject();

                    System.out.println("********************");
                    System.out.println(car.get("firstName").getAsString());
                   // System.out.println(car.get("studentName").getAsString());
                }
            }
            //System.out.println(parser.get("PersonalBook").getAsJsonArray());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
