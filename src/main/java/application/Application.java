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
                    Address personsAddress = new Address();
                    Person person = new Person();
                    Scanner getNewStudent = new Scanner(System.in);

                    System.out.println("Введите страну, в которой проживает человек: ");
                    String personsCountry = getNewStudent.next();
                    personsAddress.setCountry(personsCountry);

                    System.out.println("Введите город, в котором проживает человек: ");
                    String personsCity = getNewStudent.next();
                    personsAddress.setCity(personsCity);

                    System.out.println("Введите улицу, на которой проживает человек: ");
                    String personsStreet = getNewStudent.next();
                    personsAddress.setStreet(personsStreet);

                    System.out.println("Введите номер дома, в котором проживает человек: ");
                    String personsHouseNumber = getNewStudent.next();
                    personsAddress.setHouseAddress(personsHouseNumber);

                    System.out.println("Введите имя человека: ");
                    String personsName = getNewStudent.next();
                    person.setFirstName(personsName);

                    System.out.println("Введите фамилию человека: ");
                    String personsLastName = getNewStudent.next();
                    person.setLastName(personsLastName);

                    int addressId = addressSQL.getIdOfAddress(personsAddress);
                    if (personSQL.getPersonId(person, addressId) != 0) {
                        System.out.println("Человек был в базе, теперь введите название факультета " +
                                "для продолжения регистрации.");
                    } else {
                        personSQL.registerNewPerson(person, addressId, personsAddress);
                        System.out.println("Человека успешно добавлен в базу, теперь введите название факультета " +
                                "для продолжения регистрации.");
                    }
                        String personsFaculty = getNewStudent.next();
                        int facultyId = facultySQL.getIdOfFaculty(personsFaculty);
                        while (facultyId == 0) {
                            System.out.println("Такого факультета не существует, введите название факультета " +
                                    "еще раз: ");
                            personsFaculty = getNewStudent.next();
                            facultyId = facultySQL.getIdOfFaculty(personsFaculty);
                        }
                        addressId = addressSQL.getIdOfAddress(personsAddress);
                        int personId = personSQL.getPersonId(person, addressId);
                        if (personId != 0) {
                            if (studentSQL.isStudentExists(personId, facultyId) == 0) {
                                studentSQL.registerNewStudent(personId, facultyId);
                                System.out.println("Студент успешно зарегистрирован!");
                            } else {
                                System.out.println("Студент уже находится в базе.");
                            }
                        } else {
                            System.out.println("Не удалось найди человека в системе, технические неполадки... " +
                                    "Пожалуйста попробуйте повторить операцию попозже.");
                        }



                }

            }
        }
    }
}



