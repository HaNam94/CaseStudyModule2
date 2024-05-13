package services.impl;

import controllers.FuramaController;
import exception.CheckExceptionsUtils;
import exception.FacilityExceptionsUtils;
import models.*;

import java.io.*;
import java.util.*;

public class FacilityService implements IFacilityService{
    public static final String VILLA_FILE = "src\\data\\villa.csv";
    public static final String HOUSE_FILE = "src\\data\\house.csv";
    public static final String ROOM_FILE = "src\\data\\room.csv";
    private static Scanner scanner = new Scanner(System.in);
    private static List<Facility> maintenanceList = new ArrayList<>();
    private static Map<Facility, Integer> facilityList = new LinkedHashMap<>();
    private static LinkedHashMap<Villa, Integer> villaList = new LinkedHashMap<>();
    private static LinkedHashMap<House, Integer> houseList = new LinkedHashMap<>();
    private static LinkedHashMap<Room, Integer> roomList = new LinkedHashMap<>();
    private FacilityExceptionsUtils FacilityExceptionUtils;

    public static Map<Facility, Integer> setInfo() throws IOException {
        villaList = readVilla();
        houseList = readHouse();
        roomList = readRoom();
        for (Villa villa : villaList.keySet()) {
            facilityList.put(villa, 0);
        }
        for (House house : houseList.keySet()) {
            facilityList.put(house, houseList.get(house));
        }
        for (Room room : roomList.keySet()) {
            facilityList.put(room, 0);
        }
        return facilityList;

    }

    public static void setData() throws IOException {
        List<Booking> bookingList = BookingService.readBooking();
        for (int i = 0; i < bookingList.size(); i++) {
            for (Facility key : facilityList.keySet()) {
                if (bookingList.get(i).getServiceCode().equals(key.getCodeService())) {
                    facilityList.replace(key, facilityList.get(key) + 1);
                    if (facilityList.get(key) == 5) {
                        maintenanceList.add(key);
                        writeMaintenace(maintenanceList);
                    }
                }
            }
        }

    }


    @Override
    public void displayList() throws IOException {
        villaList = readVilla();
        houseList = readHouse();
        roomList = readRoom();
        for (Villa villa : villaList.keySet()) {
            System.out.println(villa);
        }
        for (House house : houseList.keySet()) {
            System.out.println(house);
        }
        for (Room room : roomList.keySet()) {
            System.out.println(room);
        }
    }

    @Override
    public void addNew() {
        int choice;
        while (true) {
            try {
                System.out.println("Mời bạn nhập lựa chọn: ");
                System.out.println("1. Thêm mới Villa");
                System.out.println("2. Thêm mới House");
                System.out.println("3. Thêm mới Room");
                System.out.println("4. Trở về menu");

                System.out.print("Lựa chọn của bạn là: ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        villaList = readVilla();
                        Villa villa = this.getInfoVilla();
                        villaList.put(villa, 0);
                        writeVillaFile(villaList);
                        break;
                    case 2:
                        houseList = readHouse();
                        House house = this.getInfoHouse();
                        houseList.put(house, 0);
                        writeHouseFile(houseList);
                        break;
                    case 3:
                        roomList = readRoom();
                        Room room = this.getInfoRoom();
                        roomList.put(room, 0);
                        writeRoomFile(roomList);
                        break;
                    case 4:
                        FuramaController.facilityManagement();
                        break;
                    default:
                        System.out.println("Số nhập vào không khả thi!");
                }

                CheckExceptionsUtils.addNewCheck(choice);
            } catch (CheckExceptionsUtils | NumberFormatException | IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void edit() {

    }

    @Override
    public void displayListFacilityMaintenance() throws IOException {
        setInfo();
        setData();
        maintenanceList = readMaintenanceList();
        for (Facility facility : maintenanceList) {
            System.out.println(facility.toString());
        }

    }

    private Villa getInfoVilla() {
        String codeService = addVillaCode();
        String nameService = addVillaName();
        Double areaUse = addareaUseVilla();
        Double rentalCost = addRentalCostVilla();
        int maxPerson = addMaxPersonVilla();
        String typeOfRental = addtypeOfRentalVilla();
        String roomStandard = addRoomStandardVilla();
        Double swimmingPoolAarea = addswimmingPoolAarea();
        int numberOfFloors = addNumberOfFloorsVilla();
        Villa villa = new Villa(codeService, nameService, areaUse, rentalCost, maxPerson, typeOfRental, roomStandard, swimmingPoolAarea, numberOfFloors);
        return villa;
    }

    private String addVillaCode() {
        String code;
        while (true) {
            try {
                System.out.print("Nhập mã dịch vụ của Villa: ");
                code = scanner.nextLine();
                if (FacilityExceptionUtils.codeVillaCheck(code)) {
                    return code;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addVillaName() {
        String name;
        while (true) {
            try {
                System.out.print("Nhập tên dịch vụ của Villa - Tên dịch vụ phải viết hoa ký tự đầu, các ký tự sau là ký tự bình thường: ");
                name = scanner.nextLine();
                if (FacilityExceptionUtils.nameServiceCheck(name)) {
                    return name;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Double addareaUseVilla() {
        Double araeUse;
        while (true) {
            try {
                System.out.print("Nhập diện tích sử dụng của Villa - Diện tích sử dụng phải là số thực lớn hơn 30m2: ");
                araeUse = Double.parseDouble(scanner.nextLine());
                if (FacilityExceptionUtils.areaUseCheck(araeUse)) {
                    return araeUse;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Double addRentalCostVilla() {
        Double rentalCost;
        while (true) {
            try {
                System.out.print("Nhập chi phí thuê của Villa - Chi phí thuê phải là số dương: ");
                rentalCost = Double.parseDouble(scanner.nextLine());
                if (FacilityExceptionUtils.rentalCoatCheck(rentalCost)) {
                    return rentalCost;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int addMaxPersonVilla() {
        int maxPerson;
        while (true) {
            try {
                System.out.print("Số lượng người tối đa của Villa - Số lượng người tối đa phải phải lớn hơn 0 và nhỏ hơn 20: ");
                maxPerson = Integer.parseInt(scanner.nextLine());
                if (FacilityExceptionUtils.maxPersonCheck(maxPerson)) {
                    return maxPerson;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addtypeOfRentalVilla() {
        String typeOfRental;
        while (true) {
            try {
                System.out.print("Nhập kiểu thuê của Villa: ");
                typeOfRental = scanner.nextLine();
                if (FacilityExceptionUtils.typeOfRentalCheck(typeOfRental)) {
                    return typeOfRental;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addRoomStandardVilla() {
        String roomStandard;
        while (true) {
            try {
                System.out.print("Nhập tiêu chuẩn phòng của Villa: ");
                roomStandard = scanner.nextLine();
                if (FacilityExceptionUtils.roomStandardCheck(roomStandard)) {
                    return roomStandard;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Double addswimmingPoolAarea() {
        Double swimmingPoolAarea;
        while (true) {
            try {
                System.out.print("Nhập diện tích hồ bơi của Villa - diện tích hồ bơi phải là số thực lớn hơn 30m2: ");
                swimmingPoolAarea = Double.parseDouble(scanner.nextLine());
                if (FacilityExceptionUtils.swimmingPoolAareaCheck(swimmingPoolAarea)) {
                    return swimmingPoolAarea;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int addNumberOfFloorsVilla() {
        int numberOfFloors;
        while (true) {
            try {
                System.out.print("Nhập số tầng của Villa: ");
                numberOfFloors = Integer.parseInt(scanner.nextLine());
                if (FacilityExceptionUtils.numberOfFloorsCheck(numberOfFloors)) {
                    return numberOfFloors;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

//    --------------------------------------

    private House getInfoHouse() {
        String codeService = addHouseCode();
        String nameService = addHouseName();
        Double areaUse = addareaUse();
        Double rentalCost = addRentalCost();
        int maxPerson = addMaxPerson();
        String typeOfRental = addtypeOfRental();
        String roomStandard = addRoomStandard();
        int numberOfFloors = addNumberOfFloors();

        House house = new House(codeService, nameService, areaUse, rentalCost, maxPerson, typeOfRental, roomStandard, numberOfFloors);
        return house;
    }

    private String addHouseCode() {
        String code;
        while (true) {
            try {
                System.out.print("Nhập mã dịch vụ của House: ");
                code = scanner.nextLine();
                if (FacilityExceptionUtils.codeHouseCheck(code)) {
                    return code;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addHouseName() {
        String name;
        while (true) {
            try {
                System.out.print("Nhập tên dịch vụ của House - Tên dịch vụ phải viết hoa ký tự đầu, các ký tự sau là ký tự bình thường: ");
                name = scanner.nextLine();
                if (FacilityExceptionUtils.nameServiceCheck(name)) {
                    return name;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Double addareaUse() {
        Double araeUse;
        while (true) {
            try {
                System.out.print("Nhập diện tích sử dụng của House - Diện tích sử dụng phải là số thực lớn hơn 30m2: ");
                araeUse = Double.parseDouble(scanner.nextLine());
                if (FacilityExceptionUtils.areaUseCheck(araeUse)) {
                    return araeUse;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Double addRentalCost() {
        Double rentalCost;
        while (true) {
            try {
                System.out.print("Nhập chi phí thuê của House - Chi phí thuê phải là số dương: ");
                rentalCost = Double.parseDouble(scanner.nextLine());
                if (FacilityExceptionUtils.rentalCoatCheck(rentalCost)) {
                    return rentalCost;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int addMaxPerson() {
        int maxPerson;
        while (true) {
            try {
                System.out.print("Số lượng người tối đa của House - Số lượng người tối đa phải phải lớn hơn 0 và nhỏ hơn 20: ");
                maxPerson = Integer.parseInt(scanner.nextLine());
                if (FacilityExceptionUtils.maxPersonCheck(maxPerson)) {
                    return maxPerson;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addtypeOfRental() {
        String typeOfRental;
        while (true) {
            try {
                System.out.print("Nhập kiểu thuê của House: ");
                typeOfRental = scanner.nextLine();
                if (FacilityExceptionUtils.typeOfRentalCheck(typeOfRental)) {
                    return typeOfRental;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addRoomStandard() {
        String roomStandard;
        while (true) {
            try {
                System.out.println("Nhập tiêu chuẩn phòng của House: ");
                roomStandard = scanner.nextLine();
                if (FacilityExceptionUtils.roomStandardCheck(roomStandard)) {
                    return roomStandard;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int addNumberOfFloors() {
        int numberOfFloors;
        while (true) {
            try {
                System.out.println("Nhập số tầng của House: ");
                numberOfFloors = Integer.parseInt(scanner.nextLine());
                if (FacilityExceptionUtils.numberOfFloorsCheck(numberOfFloors)) {
                    return numberOfFloors;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

//------------------------------------------

    private Room getInfoRoom() {
        String codeService = addRoomCode();
        String nameService = addRoomName();
        Double areaUse = addareaUseRoom();
        Double rentalCost = addRentalCostRoom();
        int maxPerson = addMaxPersonRoom();
        String typeOfRental = addTypeOfRentalRoom();
        String freeService = addFreeService();

        Room room = new Room(codeService, nameService, areaUse, rentalCost, maxPerson, typeOfRental, freeService);
        return room;

    }

    private String addRoomCode() {
        String code;
        while (true) {
            try {
                System.out.print("Nhập mã dịch vụ của Room: ");
                code = scanner.nextLine();
                if (FacilityExceptionUtils.codeRoomCheck(code)) {
                    return code;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addRoomName() {
        String name;
        while (true) {
            try {
                System.out.print("Nhập tên dịch vụ của Room - Tên dịch vụ phải viết hoa ký tự đầu, các ký tự sau là ký tự bình thường: ");
                name = scanner.nextLine();
                if (FacilityExceptionUtils.nameServiceCheck(name)) {
                    return name;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Double addareaUseRoom() {
        Double araeUse;
        while (true) {
            try {
                System.out.print("Nhập diện tích sử dụng của Room - Diện tích sử dụng phải là số thực lớn hơn 30m2: ");
                araeUse = Double.parseDouble(scanner.nextLine());
                if (FacilityExceptionUtils.areaUseCheck(araeUse)) {
                    return araeUse;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Double addRentalCostRoom() {
        Double rentalCost;
        while (true) {
            try {
                System.out.print("Nhập chi phí thuê của Room - Chi phí thuê phải là số dương: ");
                rentalCost = Double.parseDouble(scanner.nextLine());
                if (FacilityExceptionUtils.rentalCoatCheck(rentalCost)) {
                    return rentalCost;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int addMaxPersonRoom() {
        int maxPerson;
        while (true) {
            try {
                System.out.print("Số lượng người tối đa của Room - Số lượng người tối đa phải phải lớn hơn 0 và nhỏ hơn 20: ");
                maxPerson = Integer.parseInt(scanner.nextLine());
                if (FacilityExceptionUtils.maxPersonCheck(maxPerson)) {
                    return maxPerson;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addTypeOfRentalRoom() {
        String typeOfRental;
        while (true) {
            try {
                System.out.print("Nhập kiểu thuê của Room: ");
                typeOfRental = scanner.nextLine();
                if (FacilityExceptionUtils.typeOfRentalCheck(typeOfRental)) {
                    return typeOfRental;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String addFreeService() {
        String freeService;
        while (true) {
            try {
                System.out.print("Nhập dịch vụ miễn phí đi kèm của Room: ");
                freeService = scanner.nextLine();
                if (FacilityExceptionUtils.freeServiceCheck(freeService)) {
                    return freeService;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //    ------------------------------------------
    public static LinkedHashMap<Villa, Integer> readVilla() throws IOException {
        villaList = new LinkedHashMap<>();
        BufferedReader bufferedReader = null;
        try {
            File file = new File(VILLA_FILE);
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            String[] arr;
            Villa villa;
            while ((line = bufferedReader.readLine()) != null) {
                arr = line.split(",");
                villa = new Villa(arr[0], arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6], Double.parseDouble(arr[7]), Integer.parseInt(arr[8]));
                villaList.put(villa, 0);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Không đọc được file");
        }

        return villaList;
    }

    public static LinkedHashMap<House, Integer> readHouse() throws IOException {
        houseList = new LinkedHashMap<>();
        BufferedReader bufferedReader = null;
        try {
            File file = new File(HOUSE_FILE);
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            String[] arr;
            House house;
            while ((line = bufferedReader.readLine()) != null) {
                arr = line.split(",");
                house = new House(arr[0], arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6], Integer.parseInt(arr[7]));

                houseList.put(house, 0);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Không đọc được file");
        }

        return houseList;
    }

    public static LinkedHashMap<Room, Integer> readRoom() throws IOException {
        roomList = new LinkedHashMap<>();
        BufferedReader bufferedReader = null;
        try {
            File file = new File(ROOM_FILE);
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            String[] arr;
            Room room;
            while ((line = bufferedReader.readLine()) != null) {
                arr = line.split(",");
                room = new Room(arr[0], arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]);

                roomList.put(room, 0);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Không đọc được file");
        }

        return roomList;
    }


    public void writeVillaFile(LinkedHashMap<Villa, Integer> villaList) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(VILLA_FILE);
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Villa villa : villaList.keySet()) {
                bufferedWriter.write(villa.getInfo());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Không mở được file");
        }
    }

    public void writeHouseFile(LinkedHashMap<House, Integer> housrList) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(HOUSE_FILE);
            bufferedWriter = new BufferedWriter(new FileWriter(file));

            for (House house : housrList.keySet()) {
                bufferedWriter.write(house.getInfo());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Không mở được file");
        }
    }

    public void writeRoomFile(LinkedHashMap<Room, Integer> roomList) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(ROOM_FILE);
            bufferedWriter = new BufferedWriter(new FileWriter(file));

            for (Room room : roomList.keySet()) {
                bufferedWriter.write(room.getInfo());
                bufferedWriter.newLine();
                bufferedWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Không mở được file");
        }
    }

    public static List<Facility> readMaintenanceList() throws IOException {
        maintenanceList = new ArrayList<>();
        File file = new File("src\\data\\maintenanceFacility.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String[] arr;
        while ((line = bufferedReader.readLine()) != null) {
            arr = line.split(",");
            if (line.contains("SVVL-")) {
                maintenanceList.add(new Villa(arr[0], arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6], Double.parseDouble(arr[7]), Integer.parseInt(arr[8])));
            } else if (line.contains("VLHO-")) {
                maintenanceList.add(new House(arr[0], arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6], Integer.parseInt(arr[7])));
            } else {
                maintenanceList.add(new Room(arr[0], arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]));

            }
        }
        bufferedReader.close();
        return maintenanceList;
    }

    public static void writeMaintenace(List<Facility> maintenanceList) throws IOException {
        File file = new File("src\\data\\maintenanceFacility.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (Facility facility : maintenanceList) {
            bufferedWriter.write(facility.getInfo());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
