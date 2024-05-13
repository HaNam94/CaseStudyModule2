package models;

public class Employee extends Person {
    private String level;
    private String location;
    private Double salary;

    public Employee(){
    }

    public Employee(String level, String location, Double salary) {
        this.level = level;
        this.location = location;
        this.salary = salary;
    }

    public Employee(String id, String name, String dayOfBirth, String gender, String identityCard, String phoneNumber, String email, String level, String location, Double salary) {
        super(id, name, dayOfBirth, gender, identityCard, phoneNumber, email);
        this.level = level;
        this.location = location;
        this.salary = salary;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString() +
                "level='" + level + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getEmployeeInfo() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%.3f",getId(),getName(),getDayOfBirth(),getGender(),getIdentityCard(),getPhoneNumber(),getEmail(),getLevel(),getLocation(), getSalary());
    }
}
