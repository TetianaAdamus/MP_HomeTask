package dto;

public class BasketSummary {

    private String delivery;
    private String  total;

    public BasketSummary(String delivery, String total) {
        this.delivery = delivery;
        this.total = total;
    }

    public String getDeliveryCost() {
        return delivery;
    }

    public String  getTotal() {
        return total;
    }
}
