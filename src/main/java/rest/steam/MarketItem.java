package rest.steam;

public class MarketItem {

    private String listId;
    private String name;
    private String floatValue;
    private String[] stickers;
    private String price;

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(String floatValue) {
        this.floatValue = floatValue;
    }

    public String[] getStickers() {
        return stickers;
    }

    public void setStickers(String[] stickers) {
        this.stickers = stickers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
