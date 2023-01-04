package mappers;

import rest.steam.MarketItem;
import rest.steam.MarketItemList;
import steam.ItemObj;

import java.util.List;

public class RestMapper {

    public static MarketItemList mapItemObjList2MarketItemList(List<ItemObj> items) {
        MarketItemList list = new MarketItemList();

        for (ItemObj item : items) {
            MarketItem mItem = new MarketItem();

            mItem.setListId(item.getListId());
            mItem.setName(item.getFullItemName());
            mItem.setFloatValue(item.getFloatValue());
            mItem.setPaintIndex(item.getPaintIndex());
            mItem.setPaintSeed(item.getPaintSeed());
            mItem.setPaintSeedName(item.getPaintSeedName());
            mItem.setStickers(item.getStickers().isEmpty() ? null : item.getStickers());
            mItem.setPrice(item.getPrice());

            list.getItems().add(mItem);
        }

        return list;
    }

}
