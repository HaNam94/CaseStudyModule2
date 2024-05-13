package models;

public class Customer extends Person{
    private String typeOfGuest;
    private String address;

    public Customer(String typeOfGuest, String address) {
        this.typeOfGuest = typeOfGuest;
        this.address = address;
    }

    public Customer(String id, String name, String dayOfBirth, String gender, String identityCard, String phoneNumber, String email, String typeOfGuest, String address) {
        super(id, name, dayOfBirth, gender, identityCard, phoneNumber, email);
        this.typeOfGuest = typeOfGuest;
        this.address = address;
    }

    public String getTypeOfGuest() {
        return typeOfGuest;
    }

    public void setTypeOfGuest(String typeOfGuest) {
        this.typeOfGuest = typeOfGuest;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + super.toString()+
                "typeOfGuest='" + typeOfGuest + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getCustomerInfo() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",getId(),getName(),getDayOfBirth(),getGender(),getIdentityCard(),getPhoneNumber(),getEmail(),getTypeOfGuest(),getAddress());
    }
}
