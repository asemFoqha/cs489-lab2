package org.example;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("App started");

        Employee[] employees = {
                new Employee("Daniel", "Agar", 105945.50, LocalDate.parse("2018-01-17"), new PensionPlan("EX1089", LocalDate.parse("2023-01-17"), 100.00)),
                new Employee("Benard", "Shaw", 197750.00, LocalDate.parse("2019-04-03")),
                new Employee("Carly", "Agar", 842000.75, LocalDate.parse("2014-05-16"), new PensionPlan("SM2307", LocalDate.parse("2019-11-04"), 1555.50)),
                new Employee("Wesley", "Schneider", 74500.00, LocalDate.parse("2019-10-02"))
        };

        printAllEmployeesInJsonFormat(employees);
        printUpcomingEnrolleesReport(employees);
    }

    public static void printAllEmployeesInJsonFormat(Employee[] employees) {
        System.out.println("Printing all employees in JSON format");
        System.out.println("[");

        String jsonOutput = Arrays.stream(employees)
                .map(employee -> String.format(
                        "  { \"planReferenceNumber\": \"%s\",\"firstName\": \"%s\", \"lastName\": \"%s\", \"yearlySalary\": %.2f, \"employmentDate\": \"%s\", \"enrollmentDate\": \"%s\",  \"monthlyContribution\": \"%.2f\" }",
                        (employee.pensionPlan != null ? employee.pensionPlan.getPlanReferenceNumber() : "null"), employee.firstName, employee.lastName, employee.yearlySalary, employee.employmentDate, (employee.pensionPlan != null ? employee.pensionPlan.getEnrollmentDate() : "null"), (employee.pensionPlan != null ? employee.pensionPlan.getMonthlyContribution() : 0.0)
                ))
                .collect(Collectors.joining(",\n"));

        System.out.println(jsonOutput);
        System.out.println("]");

    }

    public static void printUpcomingEnrolleesReport(Employee[] employees) {
        System.out.println("Printing Monthly Upcoming Enrollees report in JSON format");
        System.out.println("[");

        LocalDate firstOfNextMonth = LocalDate.now().plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastOfNextMonth = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        String jsonOutput = Arrays.stream(employees)

                .filter(employee -> employee.pensionPlan == null
                        && employee.employmentDate.isBefore(firstOfNextMonth.minusYears(5))
                        && employee.employmentDate.isAfter(firstOfNextMonth.minusDays(1)))

                .sorted(Comparator.comparing(Employee::getEmploymentDate))

                .map(employee -> String.format(
                        "  { \"firstName\": \"%s\", \"lastName\": \"%s\", \"yearlySalary\": %.2f, \"employmentDate\": \"%s\" }",
                        employee.firstName,
                        employee.lastName,
                        employee.yearlySalary,
                        employee.employmentDate
                ))
                .collect(Collectors.joining(",\n"));

        System.out.println(jsonOutput);
        System.out.println("]");
    }

}