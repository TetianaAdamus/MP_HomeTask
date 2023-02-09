package dto;

public class DeliveryAddress {

    private String fullName;
    private String deliveryCountry;
    private String addressOne;
    private String addressTwo;
    private String city;
    private String country;
    private String postcode;

    public DeliveryAddress(String fullName, String deliveryCountry, String addressOne, String addressTwo, String city,
            String country, String postcode) {
        this.fullName = fullName;
        this.deliveryCountry = deliveryCountry;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostcode() {
        return postcode;
    }
}
