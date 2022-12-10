package application;


import database.*;
import entity.*;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        PersonSQL personSQL = new PersonSQL();
        StudentSQL studentSQL = new StudentSQL();
        AddressSQL addressSQL = new AddressSQL();
        FacultySQL facultySQL = new FacultySQL();
        CareerSQL careerSQL = new CareerSQL();
        SalarySQL salarySQL = new SalarySQL();
        EmployeeSQL employeeSQL = new EmployeeSQL();

        while (true) {
            System.out.println("Приветствую вас мой умный юзер! Выберите нужную для вас функцию: \n" +
                    "Создать новый факультет: 1\n" +
                    "Создать новую должность: 2\n" +
                    "Создать новую зарплату: 3\n" +
                    "Зарегистрировать нового студента: 4\n" +
                    "Зарегистрировать нового работника: 5");


            Scanner input = new Scanner(System.in);
            int decision = input.nextInt();

            switch (decision) {
                case 1 -> {
                    Faculty newFaculty = new Faculty();
                    Scanner getNewFaculty = new Scanner(System.in);
                    System.out.println("Введите название нового факультета: ");
                    String registerNewFaculty = getNewFaculty.nextLine();
                    newFaculty.setName(registerNewFaculty);
                    facultySQL.registerNewFaculty(newFaculty);
                }
                case 2 -> {
                    Career newCareer = new Career();
                    Scanner getNewCareer = new Scanner(System.in);
                    System.out.println("Введите название новой должности: ");
                    String registerNewCareer = getNewCareer.nextLine();
                    newCareer.setName(registerNewCareer);
                    careerSQL.registerNewCareer(newCareer);
                }
                case 3 -> {
                    Salary newSalary = new Salary();
                    Scanner getNewSalary = new Scanner(System.in);
                    System.out.println("Введите новую зарплату: ");
                    Double registerNewSalary = getNewSalary.nextDouble();
                    newSalary.setSalary(registerNewSalary);
                    salarySQL.registerNewSalary(newSalary);
                }
                case 4 -> {
                    Person newPerson = new Person();
                    Student newStudent = new Student();
                    Address newAddress = new Address();
                    Faculty faculty = new Faculty();
                    Scanner registerNewStudent = new Scanner(System.in);

                    System.out.println("Enter the firstname: ");
                    String inputCase41 = registerNewStudent.nextLine();
                    newPerson.setFirstName(inputCase41);
                    newStudent.setFirstName(newPerson.getFirstName());

                    System.out.println("Enter the lastname: ");
                    String inputCase42 = registerNewStudent.nextLine();
                    newPerson.setLastName(inputCase42);
                    newStudent.setLastName(newPerson.getLastName());

                    System.out.println("Enter his country: ");
                    String inputCase43 = registerNewStudent.nextLine();
                    newAddress.setCountry(inputCase43);

                    System.out.println("Enter his city: ");
                    String inputCase44 = registerNewStudent.nextLine();
                    newAddress.setCity(inputCase44);

                    System.out.println("Enter his street: ");
                    String inputCase45 = registerNewStudent.nextLine();
                    newAddress.setStreet(inputCase45);

                    System.out.println("Enter his house number: ");
                    String inputCase46 = registerNewStudent.nextLine();
                    newAddress.setHouseAddress(inputCase46);

                    System.out.println("Enter his faculty: ");
                    String getFacultyId = registerNewStudent.nextLine();

                    if (facultySQL.getIdOfFaculty(getFacultyId) == 0) {
                        System.out.println("Вы ввели не корректный факультет, попробуйте еще раз.");
                        getFacultyId = registerNewStudent.nextLine();
                        registerNewStudent.nextLine();
                    } else break;

                    faculty.setName(getFacultyId);
                    int addressId = addressSQL.create(newAddress);
                    int personId = personSQL.create(newPerson, addressId);
                    int facultyId = facultySQL.getIdOfFaculty(getFacultyId);
                    studentSQL.create(personId, facultyId);
                }
                case 5 -> {
                    Scanner isPersonExists = new Scanner(System.in);
                    System.out.println("Человек зарегистрирован в базе? \n" +
                            "Если зарегистрирован напишите: Да \n" +
                            "Если не зарегистрирован напишите: Нет");
                    String checkIfPersonExists = isPersonExists.nextLine();
                    switch (checkIfPersonExists) {
                        case "Да" -> {
                            System.out.println("Введите имя человека: ");
                            String personsName = isPersonExists.nextLine();
                            System.out.println("Введите фамилию человека: ");
                            String personsSurname = isPersonExists.nextLine();
                            System.out.println("Введите страну проживания человека: ");
                            String personsCountry = isPersonExists.nextLine();
                            System.out.println("Введите город проживания человека: ");
                            String personsCity = isPersonExists.nextLine();
                            System.out.println("Введите улицу на которой живет человек: ");
                            String personsStreet = isPersonExists.nextLine();
                            System.out.println("Введите номер дома в котором живет человек: ");
                            String personsHouseNumber = isPersonExists.nextLine();

                            int idOfAddress = addressSQL.getIdOfAddress(personsCountry, personsCity, personsStreet, personsHouseNumber);
                            int personId = personSQL.getPersonId(personsName, personsSurname, idOfAddress);

                            if (personSQL.isPersonExist(personsName, personsSurname, idOfAddress)) {
                                System.out.println("Введите должность человека: ");
                                String personsCareer = isPersonExists.nextLine();
                                int careerId = careerSQL.getCareerId(personsCareer);

                                System.out.println("Введите заработную плату из существующих: ");
                                Double personsSalary = isPersonExists.nextDouble();
                                double salaryId = salarySQL.getSalaryId(personsSalary);
                                employeeSQL.registerNewEmployee(0, personId, careerId, salaryId);

                            } else {
                                System.out.println("Упс... Что-то пошло не так. Попробуйте еще раз.");
                            }
                        }
                        case "Нет" -> {

                        }
                    }
                }
            }

        }
    }
}
