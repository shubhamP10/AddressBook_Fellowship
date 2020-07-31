package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.models.Person;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileOperations {

    public void convertToFile(List<Person> addressBook, String filePath, int fileOperations) throws IOException {
        switch (fileOperations) {
            case 1:
                JSONArray personList = new JSONArray();
                for (Person person : addressBook) {
                    JSONObject personDetails = new JSONObject();
                    personDetails.put("First Name", person.getFirstName());
                    personDetails.put("Last Name", person.getLastName());
                    personDetails.put("Phone", person.getPhone());
                    personDetails.put("Address", person.getAddress());
                    personDetails.put("City", person.getCity());
                    personDetails.put("State", person.getState());
                    personDetails.put("Zip", person.getZip());
                    JSONObject personObject = new JSONObject();
                    personObject.put("person", personDetails);
                    personList.add(personObject);
                }
                try {
                    FileWriter fileWriter = new FileWriter(filePath);
                    fileWriter.append(personList.toJSONString());
                    fileWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
                    StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                            .build();
                    beanToCsv.write(addressBook);
                } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                String data = new Gson().toJson(addressBook);
                try (FileWriter writer = new FileWriter(filePath)){
                    writer.write(data);
                }
                break;
        }
    }

    public List<Person> getDataInList(String filePath, int fileOperations) throws IOException {

        List<Person> personDetails = new LinkedList<>();

        switch (fileOperations) {
            case 1:
                JSONParser jsonParser = new JSONParser();
                try {
                    FileReader fileReader = new FileReader(filePath);
                    Object obj = jsonParser.parse(fileReader);
                    JSONArray personList = (JSONArray) obj;
                    List<Person> finalPersonDetails = personDetails;
                    personList.forEach(person -> finalPersonDetails.add(parseJSONObject((JSONObject) person)));
                    personDetails = finalPersonDetails;
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
                    csvReader.readNext();
                    String[] data;
                    while ((data = csvReader.readNext()) != null) {
                        personDetails.add(new Person(data[2], data[3], data[0], data[1], data[5], data[6], data[4]));
                    }
                }
                break;
            case 3:
                Person[] personArray = new Gson().fromJson(new FileReader(filePath), Person[].class);
                personDetails = Arrays.asList(personArray);
                break;
        }
        return personDetails;
    }

    private Person parseJSONObject(JSONObject personJson) {
        JSONObject personObj = (JSONObject) personJson.get("person");
        return new Person((String) personObj.get("First Name"),
                (String) personObj.get("Last Name"),
                (String) personObj.get("Address"),
                (String) personObj.get("City"),
                (String) personObj.get("State"),
                (String) personObj.get("Zip"),
                (String) personObj.get("Phone"));
    }
}

