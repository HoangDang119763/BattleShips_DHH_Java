

import java.io.Serializable;
import java.util.Scanner;

public abstract class Item implements Serializable {
    private String itemID;
    private String itemName;
    private int itemPrice;
    private int itemNum;

    public Item() {
    }

    public Item(String itemID, String itemName, int itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public Item(String itemID, String itemName, int itemPrice, int itemNum) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemNum = itemNum;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public abstract void printPriceInformationItem();

    public abstract void printNumInformationItem();

    public void addItemNum(int itemNum) {
        setItemNum(getItemNum() + itemNum);
    }

    public void subItemNum(int itemNum) {
        setItemNum(getItemNum() - itemNum);
    }

    public void inputItemData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập ID: ");
        setItemID(sc.nextLine());
        System.out.print("Nhập tên Item: ");
        setItemName(sc.nextLine());
        System.out.print("Nhập giá Item: ");
        setItemPrice(Integer.parseInt(sc.nextLine()));
    }
}
