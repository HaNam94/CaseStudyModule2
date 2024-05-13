package services.impl;

import controllers.FuramaController;
import exception.CheckExceptionsUtils;
import models.Employee;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class EmployeeService implements IEmployeeService {
    public static final String EMPLOYEE_FILE = "src\\data\\employee.csv";
    private static List<Employee> employeeList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void displayList() {
        employeeList = readFileEmployee();

        if (employeeList.size() != 0) {
            System.out.println("Không có danh sách nhân viên để hiển thị!");
        }
        for (Employee employee : employeeList) {
            System.out.println(employee.toString());
        }
    }

    @Override
    public void addNew() {
        employeeList = readFileEmployee();

        Employee employee = this.infoEmployee();
        employeeList.add(employee);

        writeEmployeeFile(employeeList);

        System.out.println("Thêm mới thành công!");
    }

    @Override
    public void edit() {
        employeeList = readFileEmployee();


        boolean isCode = false;
        System.out.print("Nhập mã nhân viên cần chỉnh sửa: ");
        String codeEdit = scanner.nextLine();
        CheckExceptionsUtils.codeCheck(codeEdit);
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId().equals(codeEdit)) {
                isCode = true;
                break;
            }
        }
        if (isCode) {
            for (Employee employee : employeeList) {
                System.out.println("Bắt đầu chỉnh sửa");
                System.out.println("1. Chỉnh sửa toàn bộ");
                System.out.println("2. Chỉnh sửa một phần theo: ");
                System.out.println("3. Không chỉnh sửa nữa!");

                System.out.print("LỰa chọn của bạn là: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        for (int i = 0; i < employeeList.size(); i++) {
                            if (employeeList.get(i).getId().equals(codeEdit)) {
                                employee = this.infoEmployee();
                                employeeList.set(i, employee);
                                isCode = false;
                                System.out.println("Chỉnh sửa nhân viên thành công!");
                                break;
                            }
                        }
                        break;
                    case 2:
                        while (true) {
                            System.out.println("Bạn muốn chỉnh sửa theo: ");
                            System.out.println("1. Tên");
                            System.out.println("2. Ngày tháng năm sinh");
                            System.out.println("3. Giới tính");
                            System.out.println("4. CCCD");
                            System.out.println("5. Số điện thoại");
                            System.out.println("6. Email");
                            System.out.println("7. Trình độ");
                            System.out.println("8. Vị trí");
                            System.out.println("9. Lương");
                            System.out.println("0. Chỉnh sửa xong!");

                            System.out.print("Lựa chọn của bạn là: ");
                            int choicee = Integer.parseInt(scanner.nextLine());

                            switch (choicee) {
                                case 0:
                                    try {
                                        FuramaController.employeeManagement();
                                    } catch (IOException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 1:
                                    String nameNew;
                                    while (true) {
                                        try {
                                            System.out.print("Nhập vào tên nhân viên mới: ");
                                            nameNew = scanner.nextLine();
                                            CheckExceptionsUtils.nameCheck(nameNew);
                                            System.out.println("Chỉnh sửa thành công");
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    employee.setName(nameNew);
                                    break;
                                case 2:
                                    String newDay;
                                    while (true) {
                                        try {
                                            System.out.print("Nhập ngày tháng năm sinh mới: ");
                                            newDay = scanner.nextLine();
                                            CheckExceptionsUtils.isValidate(newDay);
                                            System.out.println("Chỉnh sửa thành công");
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    employee.setDayOfBirth(newDay);
                                    break;
                                case 3:
                                    String genderNew;
                                    while (true) {
                                        try {
                                            System.out.print("Nhập giới tính mới: ");
                                            genderNew = scanner.nextLine();
                                            CheckExceptionsUtils.genderCheck(genderNew);
                                            System.out.println("Chỉnh sửa thành công!");
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    employee.setGender(genderNew);
                                    break;

                                case 4:
                                    String idCardNew;
                                    while (true) {
                                        try {
                                            System.out.print("Nhập CCCD mới: ");
                                            idCardNew = scanner.nextLine();
                                            CheckExceptionsUtils.idCardCheck(idCardNew);
                                            System.out.println("Chỉnh sửa thành công!");
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    employee.setIdentityCard(idCardNew);
                                    break;

                                case 5:
                                    String phoneNumber;
                                    while (true) {
                                        try {
                                            System.out.print("Nhập số điện thoại mới: ");
                                            phoneNumber = scanner.nextLine();
                                            CheckExceptionsUtils.phoneNumberCheck(phoneNumber);
                                            System.out.println("Chỉnh sửa thành công!");
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    employee.setPhoneNumber(phoneNumber);
                                    break;
                                case 6:
                                    String email;
                                    while (true){
                                        try {
                                            System.out.print("Nhập email mới: ");
                                            email = scanner.nextLine();
                                            CheckExceptionsUtils.emailCheck(email);
                                            System.out.println("Chỉnh sửa thành công!");
                                            break;
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    employee.setEmail(email);
                                    break;
                                case 9:
                                    Double salary;
                                    while (true) {
                                        try {
                                            System.out.println("Nhập lương mới: ");
                                            salary = Double.parseDouble(scanner.nextLine());
                                            CheckExceptionsUtils.salaryCheck(salary);
                                            System.out.println("Chỉnh sửa thành công!");
                                            break;
                                        } catch (NumberFormatException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    employee.setSalary(salary);
                            }
                            writeEmployeeFile(employeeList);
                        }
                    case 3:
                        try {
                            FuramaController.employeeManagement();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                }
            }
        } else {
            System.out.println("Không có thông tin cần chỉnh sửa");
        }
    }

    private Employee infoEmployee() {
        String id = addCode();
        String name = addName();
        String dayOfBirth = addDayBirth();
        String gender = addGender();
        String identityCard = addidentityCard();
        String phoneNumber = addphoneNumber();
        String email = addEmail();
        String level = addLevel();
        String location = addLocation();
        Double salary = addSalary();

        Employee employee = new Employee(id, name, dayOfBirth, gender, identityCard, phoneNumber, email, level, location, salary);
        return employee;

    }

    private String addCode() {
        String code;
        while (true) {
            try {
                System.out.print("Nhập mã nhân viên: ");
                code = scanner.nextLine();
                if (CheckExceptionsUtils.codeEmployeeCheck(code)) {
                    return code;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String addDayBirth() {
        String day;
        boolean checkDate = false;
        do {
            checkDate = false;
            System.out.print("Nhập vào ngày tháng năm sinh của nhân viên: ");
            day = scanner.nextLine();
            if (!CheckExceptionsUtils.isValidate(day)) {
                System.out.println("Ngày tháng năm sinh của nhân viên không hợp lệ, mời nhập lại!");
                checkDate = true;
            } else {
                LocalDate dayOfBirth = LocalDate.parse(day, formatter);
                LocalDate nowSub18 = LocalDate.now().minusYears(18);
                LocalDate nowSub100 = LocalDate.now().minusYears(100);

                if (dayOfBirth.compareTo(nowSub18) > 0) {
                    System.out.println("Nhân viên nhập vào không hợp vì không đủ tuổi!");
                    checkDate = true;
                }

                if (dayOfBirth.compareTo((nowSub100)) < 0) {
                    System.out.println("Nhân viên nhập vào quá 100 tuổi");
                    checkDate = true;
                }
            }

        } while (checkDate);
        return day;
    }

    private String addName() {
        String name;
        while (true) {
            try {
                System.out.print("Nhập tên nhân viên: ");
                name = scanner.nextLine();
                if (CheckExceptionsUtils.nameCheck(name)) {
                    return name;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String addGender() {
        String gender;
        while (true) {
            try {
                System.out.print("Nhập giới tính nhân viên: ");
                gender = scanner.nextLine();
                if (CheckExceptionsUtils.genderCheck(gender)) {
                    return gender;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String addidentityCard() {
        String identityCard;
        while (true) {
            try {
                System.out.print("Nhập căn cước công dân của nhân viên: ");
                identityCard = scanner.nextLine();
                if (CheckExceptionsUtils.idCardCheck(identityCard)) {
                    return identityCard;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String addphoneNumber() {
        String phoneNumber;
        while (true) {
            try {
                System.out.print("Nhập số điện thoại của nhân viên: ");
                phoneNumber = scanner.nextLine();
                if (CheckExceptionsUtils.phoneNumberCheck(phoneNumber)) {
                    return phoneNumber;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String addEmail() {
        String email;
        while (true) {
            try {
                System.out.print("Nhập email của nhân viên: ");
                email = scanner.nextLine();
                if (CheckExceptionsUtils.emailCheck(email)) {
                    return email;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static String addLevel() {
        int choice;
        System.out.println("Nhập trình độ của nhân viên bao gồm: ");
        System.out.println("1.Trung cấp");
        System.out.println("2. Cao đẳng");
        System.out.println("3. Đại học");
        System.out.println("4.Sau đại học");

        while (true) {
            try {
                System.out.print("Nhập lựa chọn của bạn: ");
                choice = Integer.parseInt(scanner.nextLine());
                CheckExceptionsUtils.levelCheck(choice);
                switch (choice) {
                    case 1:
                        return "Trung cấp";
                    case 2:
                        return "Cao đẳng";
                    case 3:
                        return "Đại học";
                    case 4:
                        return "Sau đại học";
                }
            } catch (CheckExceptionsUtils | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String addLocation() {
        int choice;
        System.out.println("Nhập vị trí của nhân viên bao gồm: ");
        System.out.println("1. Lễ tân");
        System.out.println("2. Phục vụ");
        System.out.println("3. Chuyên viên");
        System.out.println("4. Giám sát");
        System.out.println("5. Quản lý");
        System.out.println("6. Giám đốc");

        while (true) {
            try {
                System.out.print("Nhập lựa chọn của bạn: ");
                choice = Integer.parseInt(scanner.nextLine());

                CheckExceptionsUtils.locationCheck(choice);
                switch (choice) {
                    case 1:
                        return "Lễ tân";
                    case 2:
                        return "Phục vụ";
                    case 3:
                        return "Chuyên viên";
                    case 4:
                        return "Giám sát";
                    case 5:
                        return "Quản lý";
                    case 6:
                        return "Giám đốc";
                }
            } catch (CheckExceptionsUtils | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Double addSalary() {
        Double salary;
        while (true) {
            try {
                System.out.print("Nhập lương của nhân viên: ");
                salary = Double.parseDouble(scanner.nextLine());
                if (CheckExceptionsUtils.salaryCheck(salary)) {
                    return salary;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Employee> readFileEmployee() {
        employeeList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            File file = new File("src\\data\\employee.csv");
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String[] arr;
            while ((line = bufferedReader.readLine()) != null) {
                arr = line.split(",");
                employeeList.add(new Employee(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], Double.parseDouble(arr[9])));
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại");
        } catch (IOException e) {
            System.out.println("Không đọc được file!");
        }
        return employeeList;
    }

    public void writeEmployeeFile(List<Employee> employeeList) {
        File file = new File(EMPLOYEE_FILE);
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Employee employee : employeeList) {
                bufferedWriter.write(employee.getEmployeeInfo());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Không mở được file");
        }


    }
}
