import java.util.ArrayList;
import java.util.Scanner;

public class Item_Ship_Rocket extends Item_Ship {
    private int numTypeRocket;
    private Rocket[] listRocket;

    public Item_Ship_Rocket() {
    }

    public Item_Ship_Rocket(String itemID, String itemName, int itemPrice) {
        super(itemID, itemName, itemPrice);
    }


    public int getNumTypeRocket() {
        return numTypeRocket;
    }

    public void setNumTypeRocket(int numTypeRocket) {
        this.numTypeRocket = numTypeRocket;
    }

    public Rocket[] getListRocket() {
        return listRocket;
    }

    public void setListRocket(Rocket[] listRocket) {
        this.listRocket = listRocket;
    }

    public int getNumRocketByType(int type) {
        int temp = 0;
        for (int i = 0; i < getNumTypeRocket(); i++) {
            if (listRocket[i].getTypeRocket() == type) {
                temp = listRocket[i].getNumRocket();
                break;
            }
        }
        return temp;
    }

    @Override
    public void printPriceInformationItem() {
        if (numTypeRocket == 1) System.out.println(getItemID() + ". " + getItemName() + "| Kích thước thuyền: " + getSizeShip() + "| Số Rocket (Loại " + listRocket[0].getTypeRocket() + "): " + listRocket[0].getNumRocket() + "| (Giá: " + getItemPrice() + "/1)");
        else {
            sortListRocket();
            System.out.print(getItemID() + ". " + getItemName() + "| Kích thước thuyền: " + getSizeShip());
            for (int i = 0 ; i < getNumTypeRocket(); i++) {
                System.out.print("| Số Rocket (Loại " + listRocket[i].getTypeRocket() + "): " + listRocket[i].getNumRocket());
            }
            System.out.println("| (Giá: " + getItemPrice() + "/1)");
        }
    }

    @Override
    public void printNumInformationItem() {
        if (numTypeRocket == 1) System.out.println(getItemName() + "| Kích thước thuyền: " + getSizeShip() + "| Số Rocket (Loại " + listRocket[0].getTypeRocket() + "): " + listRocket[0].getNumRocket() + "| Số lượng: " + getItemNum());
        else {
            sortListRocket();
            System.out.print(getItemName() + "| Kích thước thuyền: " + getSizeShip());
            for (int i = 0 ; i < getNumTypeRocket(); i++) {
                System.out.print("| Số Rocket (Loại " + listRocket[i].getTypeRocket() + "): " + listRocket[i].getNumRocket());
            }
            System.out.println("| Số lượng: " + getItemNum());
        }
    }

    public boolean checkListTypeRocket(Rocket rocket) {
        boolean flag = false;
        for (int i = 0; i < getNumTypeRocket(); i++) {
            if (listRocket[i] == null) break;
            if (listRocket[i].getTypeRocket() == rocket.getTypeRocket()) flag = true;
        }
        return flag;
    }

    public int numAllRocket() {
        int temp = 0;
        for (int i = 0; i < getNumTypeRocket(); i++) {
            temp += listRocket[i].getNumRocket();
        }
        return temp;
    }

    public void sortListRocket() {
        for (int i = 0; i < getNumTypeRocket() - 1; i++)
            for (int j = i + 1; j < getNumTypeRocket(); j++)
                if (listRocket[i].getTypeRocket() > listRocket[j].getTypeRocket()) {
                    Rocket temp = listRocket[i];
                    listRocket[i] = listRocket[j];
                    listRocket[j] = temp;
                }
    }

    @Override
    public void inputItemData() {
        Scanner sc = new Scanner(System.in);
        super.inputItemData();
        System.out.print("Nhập số loại Rocket: ");
        setNumTypeRocket(Integer.parseInt(sc.nextLine()));
        this.listRocket = new Rocket[numTypeRocket];
        for (int i = 0 ; i < getNumTypeRocket();) {
            if (numTypeRocket == 1) System.out.print("Nhập loại Rocket: ");
            else System.out.print("Nhập loại Rocket thứ " + (i + 1) + ": ");
            Rocket rocket = new Rocket();
            rocket.setTypeRocket(Integer.parseInt(sc.nextLine()));
            if (numTypeRocket == 1) System.out.print("Nhập số lượng Rocket: ");
            else System.out.print("Nhập số lượng Rocket thứ " + (i + 1) + ": ");
            int temp = Integer.parseInt(sc.nextLine());
            rocket.setNumRocket(temp);
            if (checkListTypeRocket(rocket)) System.out.println("Loại Rocket này đã có trước đó!\nHãy nhập lại!");
            else {
                listRocket[i] = rocket;
                i++;
            }
        }
    }
}
