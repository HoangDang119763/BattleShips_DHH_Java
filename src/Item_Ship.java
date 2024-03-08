import java.util.Scanner;

public abstract class Item_Ship extends Item {
    private int sizeShip;

    public Item_Ship() {
    }

    public Item_Ship(String itemID, String itemName, int itemPrice) {
        super(itemID, itemName, itemPrice);
    }

    public Item_Ship(String itemID, String itemName, int itemPrice, int itemNum, int sizeShip) {
        super(itemID, itemName, itemPrice, itemNum);
        this.sizeShip = sizeShip;
    }

    public int getSizeShip() {
        return sizeShip;
    }

    public void setSizeShip(int sizeShip) {
        this.sizeShip = sizeShip;
    }

    @Override
    public void inputItemData() {
        Scanner sc = new Scanner(System.in);
        super.inputItemData();
        System.out.print("Nhập kích thước thuyền: ");
        setSizeShip(Integer.parseInt(sc.nextLine()));
    }
}
