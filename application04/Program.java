package application04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities04.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		try(BufferedReader bf = new BufferedReader(new FileReader(path))){
			
			System.out.print("Enter salary: ");
			Double salary = sc.nextDouble();
			
			List<Employee> list = new ArrayList<>();
			String line = bf.readLine();
			
			while(line != null) {
				
				String[] fields = line.split(",");
				
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				
				line = bf.readLine();
				
			}
			
			List<String> emails = list.stream()
					.filter(p -> p.getSalary() > salary)
					.map(p -> p.getEmail())
					.sorted()
					.collect(Collectors.toList());
			System.out.println("Email of people whose salary is more than 2000.00:");
			emails.forEach(System.out::println);
			
			Double sum = list.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}

}
