package models;

public class Contract {
    private int numberContract;
    private String idBooking;
    private Double depositAmount;
    private Double totalPayment;
    public Contract(){
    }

    public Contract(int numberContract, String idBooking, Double depositAmount, Double totalPayment) {
        this.numberContract = numberContract;
        this.idBooking = idBooking;
        this.depositAmount = depositAmount;
        this.totalPayment = totalPayment;
    }

    public int getNumberContract() {
        return numberContract;
    }

    public void setNumberContract(int numberContract) {
        this.numberContract = numberContract;
    }

    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "numberContract=" + numberContract +
                ", idBooking='" + idBooking + '\'' +
                ", depositAmount=" + depositAmount +
                ", totalPayment=" + totalPayment +
                '}';
    }
}
