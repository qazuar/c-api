package rest.steam;

import com.fasterxml.jackson.annotation.JsonInclude;
import steam.Sticker;

import java.io.Serializable;
import java.util.List;

public class MarketItem implements Serializable {

    private String listId;
    private String name;
    private String floatValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Sticker> stickers;

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

    public List<Sticker> getStickers() {
        return stickers;
    }

    public void setStickers(List<Sticker> stickers) {
        this.stickers = stickers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
