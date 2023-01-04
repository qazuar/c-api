package rest.steam;

import com.fasterxml.jackson.annotation.JsonInclude;
import steam.Sticker;

import java.io.Serializable;
import java.util.List;

public class MarketItem implements Serializable {

    private String listId;
    private String name;
    private String floatValue;
    private int paintIndex;
    private int paintSeed;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String paintSeedName;

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

    public int getPaintIndex() {
        return paintIndex;
    }

    public void setPaintIndex(int paintIndex) {
        this.paintIndex = paintIndex;
    }

    public int getPaintSeed() {
        return paintSeed;
    }

    public void setPaintSeed(int paintSeed) {
        this.paintSeed = paintSeed;
    }

    public String getPaintSeedName() {
        return paintSeedName;
    }

    public void setPaintSeedName(String paintSeedName) {
        this.paintSeedName = paintSeedName;
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
