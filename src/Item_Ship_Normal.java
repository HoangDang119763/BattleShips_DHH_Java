import java.util.Scanner;

public class Item_Ship_Normal extends Item_Ship {
    public Item_Ship_Normal() {
        setItemNum(0);
    }

    public Item_Ship_Normal(String itemID, String itemName, int itemPrice) {
        super(itemID, itemName, itemPrice);
    }

    public Item_Ship_Normal(String itemID, String itemName, int itemPrice, int itemNum, int sizeShip) {
        super(itemID, itemName, itemPrice, itemNum, sizeShip);
    }

    @Override
    public void printPriceInformationItem() {
        System.out.println(getItemID() + ". " + getItemName() + "| Kích thước thuyền: "+ getSizeShip() +"| (Giá: " + getItemPrice() + "/1)");
    }

    @Override
    public void printNumInformationItem() {
        System.out.println(getItemName() + "| Kích thước thuyền: "+ getSizeShip() + "| Số lượng: " + getItemNum());
    }

}
