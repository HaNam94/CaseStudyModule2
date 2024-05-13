package services.impl;

import models.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingService  implements IBookingService{
    private static Scanner scanner = new Scanner(System.in);
    private static List<Booking> bookingList = new ArrayList<>();


    @Override
    public void createNewContracts() {


    }

    @Override
    public void displayListContract() {

    }

    @Override
    public void displayList() throws IOException {

        bookingList = readBooking();
        for(Booking booking: bookingList){
            System.out.println(booking.toString());
        }
    }

    @Override
    public void addNew() throws IOException {
        bookingList = readBooking();
        Booking booking = this.getInfo();
        bookingList.add(booking);
        writeBooking(bookingList);
        System.out.println("Thêm mới thành công!");
        FacilityService.setInfo();
        FacilityService.setData();
    }

    @Override
    public void edit() {

    }
    public static List<Booking> readBooking() throws IOException {
        bookingList = new ArrayList<>();
        File file = new File("src\\data\\booking.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String[] arrayBooking;
        while ((line = bufferedReader.readLine()) != null){
            arrayBooking = line.split(",");
            bookingList.add(new Booking(arrayBooking[0],arrayBooking[1],arrayBooking[2],arrayBooking[3],arrayBooking[4],arrayBooking[5]));
        }
        bufferedReader.close();
        return bookingList;
    }
    public static void writeBooking(List<Booking> bookingList) throws IOException {
        File file = new File("src\\data\\booking.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for(Booking booking:bookingList){
            bufferedWriter.write(booking.getInfo());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
    private Booking getInfo(){
        System.out.println("Nhập mã booking code");
        String bookingCode = scanner.nextLine();
        System.out.println("Nhập ngày bắt đầu: ");
        String startDay = scanner.nextLine();
        System.out.println("Nhập ngày kết thúc: ");
        String endDay = scanner.nextLine();
        System.out.println("Nhập mã khách hàng: ");
        String customerCode = scanner.nextLine();
        System.out.println("Nhập mã dịch vụ:");
        String serviceCode = scanner.nextLine();
        System.out.println("Nhập loại dịch vụ:");
        String typeOfService = scanner.nextLine();

        Booking booking = new Booking(bookingCode,startDay,endDay,customerCode,serviceCode,typeOfService);
        return  booking;
    }
}
