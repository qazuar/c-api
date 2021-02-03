package steam;

public class MarketItemObj {

    private int listId;
    private String inspectLink;
    private String price;

    public MarketItemObj(int listId, String inspectLink, String price) {
        this.listId = listId;
        this.inspectLink = inspectLink;
        this.price = price;
    }

    public int getListId() {
        return listId;
    }

    public String getInspectLink() {
        return inspectLink;
    }

    public String getPrice() {
        return price;
    }
}
