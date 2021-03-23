package mt.mas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Employee {
	private static List<Employee> extent = new ArrayList<>();

	private double salary;
	private String name;
	private Optional<Double> extraBonus = Optional.empty(); // initialization without a value

	// ...

	public Employee(String name, double salary) {
		this.salary = salary;
		this.name = name;

		// Add to the extent
		extent.add(this);
	}

	public Optional<Double> getExtraBonus() {
		return extraBonus;
	}

	public void setExtraBonus(Optional<Double> extraBonus) {
		this.extraBonus = extraBonus;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double newSalary) {
		this.salary = newSalary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Emp '%s', sal: %s, bonus: %s", getName(), getSalary(), getExtraBonus().isPresent() ? getExtraBonus().get() : "(no bonus)");
	}

	public double getIncome() {
		return getSalary() + getExtraBonus().orElse(0d);
	}

	/**
	 * Class method setting a new salary for all the employees.
	 * @param newSalary
	 */
	public static void changeSalary(double newSalary) {
		// For each employee in the extent
		for (Employee p : extent) {
			p.setSalary(newSalary);
		}
	}

	/**
	 * Class method returning all employees having a salary higher the the given minimum.
	 * @param minSalary
	 * @return
	 */
	public static List<Employee> findEmployeesWithMinSalary(float minSalary) {
		// Using Java 8 Functional Streams to find/process data
		return extent.stream().filter(emp -> emp.getSalary() > minSalary).collect(Collectors.toCollection(ArrayList::new));
	}
}