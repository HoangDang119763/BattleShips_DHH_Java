

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class ListItem implements Serializable {
    private ArrayList<Item> listItem;

    public ListItem() {
        this.listItem = new ArrayList<>();
    }

    public ListItem(ArrayList<Item> listItem) {
        this.listItem = listItem;
    }

    public ArrayList<Item> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<Item> listItem) {
        this.listItem = listItem;
    }

    public boolean fileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public void addItem(Item item) {
        this.listItem.add(item);
    }

    public void removeItem(Item item) {
        this.listItem.remove(item);
    }

    //Kiểm tra rỗng
    public boolean checkNull() {
        return this.listItem.isEmpty();
    }

    //Số user
    public int numItem() {
        return this.listItem.size();
    }

    //Xóa hết User đã lưu
    public void removeAllItem() {
        this.listItem.removeAll(listItem);
    }

    //Xóa theo số thứ tự
    public void removeSTT(int STT) {
        this.listItem.remove(STT);
    }

    public Item getItem(int STT) {
        return this.listItem.get(STT);
    }

    public abstract void printAllItemFromList();

    public boolean checkIDItemFromList(String ID) {
        boolean flag = false;
        for (Item item : getListItem()) {
            if (item.getItemID().equals(ID)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public ArrayList<Item_Ship> getListShip() {
        ArrayList<Item_Ship> temp = new ArrayList<>();
        for (Item item: getListItem()) {
            if (item instanceof Item_Ship) temp.add((Item_Ship) item);
        }
        return temp;
    }

    public int maxNumTypeRocket() {
        int temp = 0;
        ArrayList<Item_Ship> temp1 = (ArrayList<Item_Ship>) getListShip().clone();
        for (Item_Ship itemShip : temp1) {
            if (itemShip instanceof Item_Ship_Rocket) {
                if (((Item_Ship_Rocket) itemShip).getNumTypeRocket() > temp) temp = ((Item_Ship_Rocket) itemShip).getNumTypeRocket();
            }
        }
        return temp;
    }

    public void updateInformationItem_ShipToList(Item_Ship item) {
        for (Item item1: listItem) {
            if (item1.getItemID().equals(item.getItemID()) && item1 instanceof Item_Ship) {
                item1.setItemNum(item.getItemNum());
                break;
            }
        }
    }

    public void updateInformationItem_ShipToList(ArrayList<Item_Ship> list) {
        for (Item_Ship itemShip : list) {
            updateInformationItem_ShipToList(itemShip);
        }
    }

    public void updateInformationItem_NormalToList(Item_Normal item) {
        for (Item item1: listItem) {
            if (item1.getItemID().equals(item.getItemID()) && item1 instanceof Item_Normal) {
                item1.setItemNum(item.getItemNum());
                break;
            }
        }
    }

    public void updateInformationItem_NormalToList(ArrayList<Item_Normal> list) {
        for (Item_Normal itemNormal : list) {
            updateInformationItem_NormalToList(itemNormal);
        }
    }

    public int numAllItem_Ship() {
        int num = 0;
        for (Item item: listItem) {
            if (item instanceof Item_Ship) num += item.getItemNum();
        }
        return num;
    }

    public Item_Normal getItemNormalByID(String ID) {
        Item_Normal tmp = new Item_Normal();
        for (Item item : getListItem()) {
            if (item.getItemID().equals(ID) && item instanceof Item_Normal) tmp = (Item_Normal) item;
        }
        return tmp;
    }

    public Item_Ship_Normal getItemShipNormalByID(String ID) {
        Item_Ship_Normal tmp = new Item_Ship_Normal();
        for (Item item : getListItem()) {
            if (item.getItemID().equals(ID) && item instanceof Item_Ship_Normal) tmp = (Item_Ship_Normal) item;
        }
        return tmp;
    }

    public Item_Ship_Rocket getItemShipRocketByID(String ID) {
        Item_Ship_Rocket tmp = new Item_Ship_Rocket();
        for (Item item : getListItem()) {
            if (item.getItemID().equals(ID) && item instanceof Item_Ship_Rocket) tmp = (Item_Ship_Rocket) item;
        }
        return tmp;
    }

    public Item getItemByID(String ID) {
        Item tmp = null;
        for (Item item : getListItem()) {
            if (item.getItemID().equals(ID) && item instanceof Item_Ship_Rocket) {
                tmp = (Item_Ship_Rocket) item;
                break;
            } else if (item.getItemID().equals(ID) && item instanceof Item_Ship_Normal) {
                tmp = (Item_Ship_Normal) item;
                break;
            } else if (item.getItemID().equals(ID) && item instanceof Item_Normal) {
                tmp = (Item_Normal) item;
                break;
            }
        }
        return tmp;
    }

    public void sortListItemByID() {
        for (int i = 0; i < numItem() - 1; i++) {
            for (int j = i + 1; j < numItem(); j++) {
                int tmp1 = Integer.parseInt(listItem.get(i).getItemID());
                int tmp2 = Integer.parseInt(listItem.get(j).getItemID());
                if (tmp1 > tmp2) {
                    Collections.swap(this.listItem, i, j);
                }
            }
        }
    }
}
