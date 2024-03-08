
import java.util.ArrayList;

public class ListItemUser extends ListItem {
    public ListItemUser() {
    }

    public ListItemUser(ArrayList<Item> listItem) {
        super(listItem);
    }

    public void printAllItemFromList() {
        int stt = 1;
        for (Item item: getListItem()) {
            System.out.print(stt + ". ");
            item.printNumInformationItem();
            stt++;
        }
    }

    public void printAllShipFromList() {
        int stt = 1;
        for (Item item: getListItem()) {
            if (item instanceof Item_Ship) {
                System.out.print(stt + ". ");
                item.printNumInformationItem();
                stt++;
            }
        }
    }

}
