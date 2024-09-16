//без використанням Stream API

package com.labs;
import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String firstName;
    private String lastName;
    private String dormitory;
    private int roomNumber;
    private double fee;
    private int age;
    private boolean isBeneficiary;

    public Student(String firstName, String lastName, String dormitory, int roomNumber, double fee, int age, boolean isBeneficiary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dormitory = dormitory;
        this.roomNumber = roomNumber;
        this.fee = fee;
        this.age = age;
        this.isBeneficiary = isBeneficiary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDormitory() {
        return dormitory;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getFee() {
        return fee;
    }

    public int getAge() {
        return age;
    }

    public boolean isBeneficiary() {
        return isBeneficiary;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
        //return firstName + " " + lastName + ", Гуртожиток: " + dormitory + ", Кімната: " + roomNumber + ", Плата: " + fee + ", Вік: " + age + ", Пільговик: " + isBeneficiary;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = getStudents();

        printBeneficiaries(students);
        groupByDormitory(students);
        printRoomCounts(students);
        printSortedByAgeAndBeneficiary(students);
        printUniqueRoomNumbers(students);
        printMaxFeeStudent(students);
    }

    private static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ivan", "Sydorenko", "Dormitory 1", 101, 3200.50, 19, true));
        students.add(new Student("Petro", "Huk", "Dormitory 2", 202, 3400.75, 21, false));
        students.add(new Student("Svitlana", "Black", "Dormitory 1", 101, 3000.00, 18, true));
        students.add(new Student("Olia", "Miller", "Dormitory 3", 303, 3600.00, 22, false));
        students.add(new Student("Victoria", "Mlyck", "Dormitory 4", 111, 3000.00, 18, true));
        students.add(new Student("Stanislav", "Melnyk", "Dormitory 2", 202, 3100.25, 20, true));
        return students;
    }

    public static void printBeneficiaries(List<Student> students) {
        List<Student> beneficiaries = new ArrayList<>();
        List<Student> nonBeneficiaries = new ArrayList<>();

        for(Student student:students){
            if(student.isBeneficiary())
                beneficiaries.add(student);
            else
                nonBeneficiaries.add(student);
        }
        System.out.println("\nBeneficiaries: \n" + beneficiaries);
        System.out.println("Non-Beneficiaries: \n" + nonBeneficiaries);
    }

    public static void groupByDormitory(List<Student> students) {
        Map<String, List<Student>> groupedByDormitory = new HashMap<>();

        for (Student student : students) {
            if (!groupedByDormitory.containsKey(student.getDormitory())) {
                groupedByDormitory.put(student.getDormitory(), new ArrayList<>());
            }
            groupedByDormitory.get(student.getDormitory()).add(student);
        }

        System.out.println("\nGrouped by dormitory: \n" + groupedByDormitory);
    }

    public static void printRoomCounts(List<Student> students) {
        Map<Integer, Integer> roomCount = new HashMap<>();
        for (Student student : students) {
            roomCount.put(student.getRoomNumber(),
                    roomCount.getOrDefault(student.getRoomNumber(), 0) + 1);
        }

        System.out.println("\nRoom counts: \n" + roomCount);
    }

    public static void printSortedByAgeAndBeneficiary(List<Student> students) {
        students.sort(Comparator.comparingInt(Student::getAge)
                .thenComparing(Student::isBeneficiary));

        System.out.println("\nSorted by age and beneficiary: \n" + students);
    }

    public static void printUniqueRoomNumbers(List<Student> students) {
        Set<Integer> uniqueRoomNumbers = new HashSet<>();
        for (Student student : students) {
            uniqueRoomNumbers.add(student.getRoomNumber());
        }

        System.out.println("\nUnique room numbers: \n" + uniqueRoomNumbers);
    }

    public static void printMaxFeeStudent(List<Student> students) {
        Student maxFeeStudent = null;
        for (Student student : students) {
            if (maxFeeStudent == null || student.getFee() > maxFeeStudent.getFee()) {
                maxFeeStudent = student;
            }
        }

        Optional<Student> maxFeeStudentOptional = Optional.ofNullable(maxFeeStudent);
        maxFeeStudentOptional.ifPresentOrElse(
                student -> System.out.println("\nStudent with max fee: " + student),
                () -> System.out.println("\nNo student found with max fee")
        );
    }
}