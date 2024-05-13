package models;

public class Booking {
    private String idBooking;
    private String dayStart;
    private String dayEnd;
    private String idCustomer;
    private String serviceCode;
    private String typeOfService;

    public Booking(){
    }

    public Booking(String idBooking, String dayStart, String dayEnd, String idCustomer, String serviceCode, String typeOfService) {
        this.idBooking = idBooking;
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.idCustomer = idCustomer;
        this.serviceCode = serviceCode;
        this.typeOfService = typeOfService;
    }

    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }

    public String getDayStart() {
        return dayStart;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "idBooking='" + idBooking + '\'' +
                ", dayStart='" + dayStart + '\'' +
                ", dayEnd='" + dayEnd + '\'' +
                ", idCustomer='" + idCustomer + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", typeOfService='" + typeOfService + '\'' +
                '}';
    }

    public String getInfo() {
        return String.format("%s,%s,%s,%s,%s,%s",this.idBooking,dayStart,dayEnd,idCustomer,serviceCode,typeOfService);
    }
}
