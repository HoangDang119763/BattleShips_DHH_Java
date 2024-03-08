

import java.io.*;
import java.util.ArrayList;

public class ListItemShop extends ListItem {
    public ListItemShop() {
    }

    public ListItemShop(ArrayList<Item> listItem) {
        super(listItem);
    }

    @Override
    public void printAllItemFromList() {
        for (Item item: getListItem()) {
            item.printPriceInformationItem();
        }
    }


    public void updateInformationListFromFile(String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListItemFromFile(fileName);
        }
    }

    public void updateListItemToFile(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Item item : getListItem()) {
                objectOut.writeObject(item);
            }
            objectOut.close();
            fileOut.close();
            //System.out.println("Đã lưu thành công!");
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("Lưu thất bại!");
        }
    }

    public void updateListItemFromFile(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            try {
                while (true) {
                    Object obj = objectIn.readObject();
                    if (obj instanceof Item) {
                        Item item = (Item) obj;
                        getListItem().add(item);
                    }
                }
            } catch (EOFException e) {
                //
            }
            objectIn.close();
            fileIn.close();
            //System.out.println("Cập nhật thành công!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            //System.out.println("Lỗi khi cập nhật ArrayList!");
        }
    }
}
