package api;

import com.google.gson.annotations.SerializedName;

public class Currency {

    private String id;
    private Price price;

    public Currency() {
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    class Price {
        public String price;
    }
}
