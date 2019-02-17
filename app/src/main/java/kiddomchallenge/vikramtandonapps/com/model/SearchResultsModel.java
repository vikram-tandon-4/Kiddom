package kiddomchallenge.vikramtandonapps.com.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchResultsModel implements Serializable {


    ArrayList<ItemListElementModel> itemListElement;

    public ArrayList<ItemListElementModel> getItemListElement() {
        return itemListElement;
    }

    public void setItemListElement(ArrayList<ItemListElementModel> itemListElement) {
        this.itemListElement = itemListElement;
    }
}
