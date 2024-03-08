

public class Item_Normal extends Item {
    public Item_Normal() {
    }

    public Item_Normal(String itemID, String itemName, int itemPrice) {
        super(itemID, itemName, itemPrice);
    }

    public Item_Normal(String itemID, String itemName, int itemPrice, int itemNum) {
        super(itemID, itemName, itemPrice, itemNum);
    }

    @Override
    public void printPriceInformationItem() {
        System.out.println(getItemID() + ". " + getItemName() + "| (Giá: " + getItemPrice() + "/1)");
    }

    @Override
    public void printNumInformationItem() {
        System.out.println(getItemName() + "| Số lượng: " + getItemNum());
    }


}
