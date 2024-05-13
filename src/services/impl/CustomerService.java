package services.impl;

import exception.CheckExceptionsUtils;
import models.Customer;

import java.io.*;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService implements ICustomerService{
    public static final String CUSTOMER_FILE = "src\\data\\customer.csv";
    private static List<Customer> customerList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    @Override
    public void displayList() throws IOException {
        customerList = readCustomerFile();
        if (customerList.size() == 0) {
            System.out.println("Không có danh sách khách hàng để hiển thị!");
        } else {
            for (Customer customer : customerList) {
                System.out.println(customer.toString());
            }
        }
    }

    @Override
    public void addNew() throws IOException {
        customerList = readCustomerFile();
        Customer customer = this.info();
        customerList.add(customer);
        System.out.println("Thêm mới thành công!");
        writeCustomerFile(customerList);
    }

    @Override
    public void edit() {
        customerList = readCustomerFile();
        System.out.print("Nhập mã khách hàng cần chỉnh sửa: ");
        String customerId = scanner.nextLine();
        Customer customerToEdit = findCustomerById(customerId);
        if (customerToEdit == null) {
            System.out.println("Không tìm thấy khách hàng với mã " + customerId);
            return;
        }
        System.out.println("Thông tin khách hàng cần chỉnh sửa: ");
        System.out.println(customerToEdit);

        Customer editedCustomer = info();
        customerList.remove(customerToEdit);
        customerList.add(editedCustomer);
        System.out.println("Chỉnh sửa thành công!");
        writeCustomerFile(customerList);

    }

    private Customer findCustomerById(String id) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    private Customer info() {
        String id = addCode();
        String name = addName();
        String dayOfBirth = addDayBirth();
        String gender = addGender();
        String identityCard = addidentityCard();
        String phoneNumber = addphoneNumber();
        String email = addEmail();
        String typeOfGuest = addTypeOfGuest();
        String address = addaddress();

        Customer customer = new Customer(id, name, dayOfBirth, gender, identityCard, phoneNumber, email, typeOfGuest, address);
        return customer;
    }

    private String addCode() {
        String code;
        while (true) {
            try {
                System.out.print("Nhập mã khách hàng: ");
                code = scanner.nextLine();
                if (CheckExceptionsUtils.codeCustomerCheck(code)) {
                    return code;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addDayBirth() {
        String dayOfBirth;
        while (true) {
            try {
                System.out.print("Nhập ngày sinh của khách hàng: ");
                dayOfBirth = scanner.nextLine();
                break;
            } catch (DateTimeException e) {
                System.out.println("Không đúng định dạng, mời nhập lại");
            }
        }
        return dayOfBirth;
    }

    private String addName() {
        String name;
        while (true) {
            try {
                System.out.print("Nhập tên khách hàng: ");
                name = scanner.nextLine();
                if (CheckExceptionsUtils.nameCheck(name)) {
                    return name;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addGender() {
        String gender;
        while (true) {
            try {
                System.out.print("Nhập giới tính khách hàng: ");
                gender = scanner.nextLine();
                if (CheckExceptionsUtils.genderCheck(gender)) {
                    return gender;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addidentityCard() {
        String identityCard;
        while (true) {
            try {
                System.out.print("Nhập căn cước công dân của khách hàng: ");
                identityCard = scanner.nextLine();
                if (CheckExceptionsUtils.idCardCheck(identityCard)) {
                    return identityCard;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addphoneNumber() {
        String phoneNumber;
        while (true) {
            try {
                System.out.print("Nhập số điện thoại của khách hàngx: ");
                phoneNumber = scanner.nextLine();
                if (CheckExceptionsUtils.phoneNumberCheck(phoneNumber)) {
                    return phoneNumber;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addEmail() {
        String email;
        while (true) {
            try {
                System.out.print("Nhập email của khách hàng: ");
                email = scanner.nextLine();
                if (CheckExceptionsUtils.emailCheck(email)) {
                    return email;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addTypeOfGuest() {
        System.out.println("Nhập số tương ứng với loại khách:");
        System.out.println("1. Diamond");
        System.out.println("2. Platinium");
        System.out.println("3. Gold");
        System.out.println("4. Silver");
        System.out.println("5. Member");

        while (true) {
            try {
                System.out.print("Lựa chọn của bạn là: ");
                int choice;
                choice = Integer.parseInt(scanner.nextLine());

                CheckExceptionsUtils.typeOfGuestCheck(choice);
                switch (choice) {
                    case 1:
                        return "Diamond";
                    case 2:
                        return "Platinium";
                    case 3:
                        return "Gold";
                    case 4:
                        return "Silver";
                    case 5:
                        return "Member";
                }
            } catch (CheckExceptionsUtils | NumberFormatException exceptions) {
                System.out.println(exceptions.getMessage());
            }
        }
    }

    private String addaddress() {
        String address;
        System.out.print("Nhập địa chỉ của khách hàng: ");
        address = scanner.nextLine();

        return address;
    }

    public static List<Customer> readCustomerFile() {
        List<Customer> customerList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {

            File file = new File(CUSTOMER_FILE);
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String[] arr;
            while ((line = bufferedReader.readLine()) != null ) {
                if (line.length() == 1) {
                    break;
                }
                arr = line.split(",");
                customerList.add(new Customer(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8]));
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại");
        } catch (IOException e) {
            System.out.println("Không đọc được file");
        }

        return customerList;
    }

    public void writeCustomerFile(List<Customer> customerList) {
        File file = new File(CUSTOMER_FILE);
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Customer customer : customerList) {
                bufferedWriter.write(customer.getCustomerInfo());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Không mở được file");
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
