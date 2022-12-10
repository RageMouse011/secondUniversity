package application;


import database.AddressSQL;
import database.PersonSQL;
import database.StudentSQL;
import database.util.FacultySQL;
import entity.Address;
import entity.Faculty;
import entity.Person;
import entity.Student;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        PersonSQL personSQL = new PersonSQL();
        StudentSQL studentSQL = new StudentSQL();
        AddressSQL addressSQL = new AddressSQL();
        FacultySQL facultySQL = new FacultySQL();

        while (true) {
            System.out.println("Enter the appropriate number for further action: \n" +
                    "1: Create student.");




            Scanner input = new Scanner(System.in);
            int decision = input.nextInt();

            switch (decision) {
                case 1:
                    Scanner getCase1 = new Scanner(System.in);
                    Person person = new Person();
                    Student student = new Student();
                    Address address = new Address();
                    Faculty faculty = new Faculty();

                    System.out.println("Enter the firstname: ");
                    String inputCase11 = getCase1.nextLine();
                    person.setFirstName(inputCase11);
                    student.setFirstName(person.getFirstName());

                    System.out.println("Enter the lastname: ");
                    String inputCase12 = getCase1.nextLine();
                    person.setLastName(inputCase12);
                    student.setLastName(person.getLastName());

                    System.out.println("Enter his country: ");
                    String inputCase13 = getCase1.nextLine();
                    address.setCountry(inputCase13);

                    System.out.println("Enter his city: ");
                    String inputCase14 = getCase1.nextLine();
                    address.setCity(inputCase14);

                    System.out.println("Enter his street: ");
                    String inputCase15 = getCase1.nextLine();
                    address.setStreet(inputCase15);

                    System.out.println("Enter his home address: ");
                    String inputCase16 = getCase1.nextLine();
                    address.setHomeAddress(inputCase16);


                    System.out.println("Enter his faculty: ");
                    String inputCase17 = getCase1.nextLine();
                    faculty.setName(inputCase17);

                    int addressId = addressSQL.create(address);
                    int personId = personSQL.create(person, addressId);
                    int facultyId = facultySQL.create(faculty);
                    studentSQL.create(personId, facultyId);
                    break;

            }

        }
    }
}
