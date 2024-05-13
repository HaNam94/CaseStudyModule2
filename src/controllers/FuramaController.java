package controllers;

import services.impl.*;

import java.io.IOException;
import java.util.Scanner;

public class FuramaController {
    private static Scanner scanner = new Scanner(System.in);
    private static final IEmployeeService iEmployeeService = new EmployeeService();
    private static final ICustomerService iCustomerService = new CustomerService();
    private static final IFacilityService iFacilityService = new FacilityService();
    private static final IBookingService iBookingService = new BookingService();
    public static void displayMainMenu() throws IOException {
        while (true) {
            System.out.println("-----------CHƯƠNG TRÌNH QUẢN LÝ FURAMA RESORT -----------");
            System.out.println("Nhập số tương ứng với menu để tiếp tục");
            System.out.println("1. Quản lý nhân viên");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý cơ sở vật chất");
            System.out.println("4. Quản lý đặt phòng");
            System.out.println("5. Quản lý khuyến mãi");
            System.out.println("6. Thoát");

            System.out.print("Lựa chọn của bạn là: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    FuramaController.employeeManagement();
                    break;
                case 2:
                    FuramaController.customerManagement();
                    break;
                case 3:
                    FuramaController.facilityManagement();
                    break;
                case 4:
                    FuramaController.bookingManagement();
                    break;
                case 5:
                    FuramaController.promotionManagement();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui lòng nhập đúng số ứng với chức năng");
            }
        }
    }


    public static void employeeManagement() throws IOException {
        while (true) {
            System.out.println("----------Quản lý nhân viên----------");
            System.out.println("1. Hiển thị danh sách nhân viên");
            System.out.println("2. Thêm mới nhân viên");
            System.out.println("3. Chỉnh sửa nhân viên ");
            System.out.println("4. Trở về menu chính");

            System.out.print("Lựa chọn của bạn là: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    iEmployeeService.displayList();
                    break;
                case 2:
                    iEmployeeService.addNew();
                    break;
                case 3:
                    iEmployeeService.edit();
                    break;
                case 4:
                    FuramaController.displayMainMenu();
                    break;
                default:
                    System.out.println("Vui lòng nhập đúng số ứng với chức năng");
            }
        }
    }

    public static void customerManagement() throws IOException {
        while (true) {
            System.out.println("----------QUẢN LÝ KHÁCH HÀNG----------");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm mới khách hàng");
            System.out.println("3. Chỉnh sửa khách hàng");
            System.out.println("4. Trở về menu chính");

            System.out.print("Lựa chọn của bạn là: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    iCustomerService.displayList();
                    break;
                case 2:
                    iCustomerService.addNew();
                    break;
                case 3:
                    iCustomerService.edit();
                    break;
                case 4:
                    FuramaController.displayMainMenu();
                    break;
                default:
                    System.out.println("Vui lòng nhập đúng số ứng với chức năng");
            }
        }
    }

    public static void facilityManagement() throws IOException {
        while (true) {
            System.out.println("----------QUẢN LÝ CƠ SỞ VẬT CHẤT----------");
            System.out.println("1. Hiển thị danh sách cơ sở vật chất");
            System.out.println("2. Thêm mới cơ sở vật chất");
            System.out.println("3. Chỉnh sửa cơ sở vật chất");
            System.out.println("4. Trở về menu chính");

            System.out.print("Lựa chọn của bạn là: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    iFacilityService.displayList();
                    break;
                case 2:
                    iFacilityService.addNew();
                    break;
                case 3:
                    iFacilityService.displayListFacilityMaintenance();
                    break;
                case 4:
                    FuramaController.displayMainMenu();
                    break;
                default:
                    System.out.println("Vui lòng nhập đúng số ứng với chức năng");
            }
        }
    }

    public static void bookingManagement() throws IOException {
        while (true) {
            System.out.println("----------QUẢN LÝ ĐẶT PHÒNG----------");
            System.out.println("1. Thêm mới đặt phòng");
            System.out.println("2. Hiển thị danh sách đặt phòng");
            System.out.println("3. Tạo hợp đồng mới");
            System.out.println("4. Chỉnh sửa hợp đồng");
            System.out.println("5. Trở về menu chính");

            System.out.print("Lựa chọn của bạn là: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    iBookingService.addNew();
                    break;
                case 2:
                    iBookingService.displayList();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    FuramaController.displayMainMenu();
                    break;
                default:
                    System.out.println("Vui lòng nhập đúng số ứng với chức năng");
            }
        }
    }

    public static void promotionManagement() throws IOException {
        while (true) {
            System.out.println("----------QUẢN LÝ KHUYẾN MÃI----------");
            System.out.println("1. Hiển thị danh sách khách hàng sử dụng dịch vụ");
            System.out.println("2.Hiển thị danh sách khách hàng nhận được voucher");
            System.out.println("3.Trở về menu chính");

            System.out.print("Lựa chọn của bạn là: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    FuramaController.displayMainMenu();
                    break;
                default:
                    System.out.println("Vui lòng nhập đúng số ứng với chức năng");
            }
        }
    }
}
