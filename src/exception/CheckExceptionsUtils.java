package exception;

import models.Customer;
import models.Employee;
import services.impl.CustomerService;
import services.impl.EmployeeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CheckExceptionsUtils extends Exception{
    CheckExceptionsUtils(String messager){super(messager);}

    public static boolean codeEmployeeCheck(String code){
        List<Employee> employeeList = EmployeeService.readFileEmployee();
        try {
            String regex = "^NV[0-9]{3,6}";
            if (!code.matches(regex)){
                throw new CheckExceptionsUtils("Không đúng địng dạng, mời nhập lại");
            }
            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i).getId().equals(code)){
                    throw new CheckExceptionsUtils("Mã nhân viên trùng lặp, vùi lòng nhập lại");
                }
            }
            return true;
        } catch (CheckExceptionsUtils e){
            System.out.println(e.getMessage());
            return false;
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static boolean codeCheck(String code) {
        List<Employee> employeeList = EmployeeService.readFileEmployee();
        try {
            String regex = "^NV[0-9]{3,6}$";
            if (!code.matches(regex)) {
                throw new CheckExceptionsUtils("Không đúng định dạng, mời nhập lại");
            }
            return true;
        } catch (CheckExceptionsUtils e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean codeCustomerCheck(String code) {
        try {
            List<Customer> customerList = CustomerService.readCustomerFile();
            String regex = "^KH[0-9]{3,6}$";
            if (!code.matches(regex)) {
                throw new CheckExceptionsUtils("Không đúng định dạng, mời nhập lại");
            }
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getId().equals(code)) {
                    throw new CheckExceptionsUtils("Mã khách hàng trùng lặp, vui lòng nhập lại!");
                }
            }
            return true;
        } catch (CheckExceptionsUtils e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean nameCheck(String name) {
        try {
            String regex = "^([A-ZĐ][a-záàảãạăâắằấầặẵẫêậẩéèẻẽẹếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịđùúủũụưứửữựỷỹ]+[ ])+[A-ZĐ][a-záàảãạăâắằấầặẵẫậéèẻẽẹếềểễệóòêỏõọôốồổỗộơớờởỡợíìỉĩịđùúủũụưứửữựỷỹ]+$";

            if (!name.matches(regex)) {
                throw new CheckExceptionsUtils("Không đúng định dạng, mời nhập lại");
            }
            return true;
        } catch (CheckExceptionsUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean genderCheck(String gender) {
        try {
            String regex = "^(Nam|Nữ|Giới tính khác|nam|nữ|giới tính khác)+$";
            if (!gender.matches(regex)) {
                throw new CheckExceptionsUtils("Không đúng định dạng, mời nhập lại");
            }
            return true;
        } catch (CheckExceptionsUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean idCardCheck(String addidentityCard) {

        try {
            List<Customer> customerList = CustomerService.readCustomerFile();
            List<Employee> employeeList = EmployeeService.readFileEmployee();
            String regex = "^[0-9]{9}$";
            if (!addidentityCard.matches(regex)) {
                throw new CheckExceptionsUtils("Không đúng định dạng, mời nhập lại");
            }
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getIdentityCard().equals(addidentityCard)) {
                    throw new CheckExceptionsUtils("CCCD khách hàng trùng lặp, mời nhập lại!");
                }
            }
            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i).getIdentityCard().equals(addidentityCard)) {
                    throw new CheckExceptionsUtils("CCCD nhân viên trùng lặp, mời nhập lại!");
                }
            }
            return true;
        } catch (CheckExceptionsUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean phoneNumberCheck(String phoneNumber) {
        try {
            String regex = "^[0][0-9]{9}$";
            if (!phoneNumber.matches(regex)) {
                throw new CheckExceptionsUtils("Không đúng định dạng, mời nhập lại");
            }
            return true;
        } catch (CheckExceptionsUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean emailCheck(String email) {
        try {
            String regex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
            if (!email.matches(regex)) {
                throw new CheckExceptionsUtils("Không đúng định dạng, mời nhập lại");
            }
            return true;
        } catch (CheckExceptionsUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean salaryCheck(Double salary) {
        try {
            if (salary <= 0) {
                throw new CheckExceptionsUtils("Không đúng định dạng, mời nhập lại");
            }
            return true;
        } catch (CheckExceptionsUtils e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void levelCheck(int choice) throws CheckExceptionsUtils {
        int[] arr = new int[]{1, 2, 3, 4};
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == choice) {
                flag = false;
                break;
            }
        }
        if (false) {
            throw new CheckExceptionsUtils("Ngoài phạm vi lựa chọn 1 -4 || " + choice);
        }
    }

    public static void locationCheck(int choice) throws CheckExceptionsUtils {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == choice) {
                flag = false;
                break;
            }
        }
        if (false) {
            throw new CheckExceptionsUtils("Ngoài phạm vi lựa chọn 1 -6 || " + choice);
        }
    }

    public static void typeOfGuestCheck(int choice) throws CheckExceptionsUtils {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        boolean check = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == choice) {
                check = false;
                break;
            }
            if (false) {
                throw new CheckExceptionsUtils("Ngoài phạm vi lựa chọn 1 - 5 || " + choice);
            }
        }
    }

    public static void addNewCheck(int choice) throws CheckExceptionsUtils {
        int[] arr = new int[]{1, 2, 3, 4};
        boolean check = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == choice) {
                check = false;
                break;
            }
            if (false) {
                throw new CheckExceptionsUtils("Ngoài phạm vi lựa chọn 1 - 5 || " + choice);
            }
        }
    }

    public static boolean isValidate(String dayOfBirth) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false); // để kiểm tra năm nhuận hay không
        try {
            simpleDateFormat.parse(dayOfBirth);
            return true;
        } catch (ParseException e) {
            return false;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
