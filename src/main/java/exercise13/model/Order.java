package exercise13.model;

//[KSTREAM-SOURCE-0000000000]: 1013, {"ordertime":1509049597997,"orderid":1013,"itemid":"Item_273","orderunits":5.076322735052166,"address":{"city":"City_14","state":"State_31","zipcode":80962}}
public class Order {

    private long ordertime;
    private  long orderid;
    private String itemid;
    private Double orderunits;
    private Address address;

    public Order(long ordertime, long orderid, String itemid, Double orderunits, Address address) {
        this.ordertime = ordertime;
        this.orderid = orderid;
        this.itemid = itemid;
        this.orderunits = orderunits;
        this.address = address;
    }

    public long getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(long ordertime) {
        this.ordertime = ordertime;
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public Double getOrderunits() {
        return orderunits;
    }

    public void setOrderunits(Double orderunits) {
        this.orderunits = orderunits;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
