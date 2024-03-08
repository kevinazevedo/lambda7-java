package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		List<Employee> list = new ArrayList<>();
		
		System.out.print("Enter full file path: ");
		String path = scan.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			
			while (line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				String email = fields[1];
				Double salary = Double.parseDouble(fields[2]);
				
				list.add(new Employee(name, email, salary));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			Double filterSalary = scan.nextDouble();
			
			List<String> emails = list.stream()
					.filter(x -> x.getSalary() > filterSalary)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("\nEmail of people whose salary is more than " + String.format("%.2f", filterSalary) + ":");
			emails.forEach(System.out::println);
			
			double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("\nSum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		scan.close();
	}
}
