import java.util.Scanner;

public class Console implements FileName{
    Scanner sc = new Scanner(System.in);
    ListUser listUser = new ListUser();
    ListItemShop listItemShop = new ListItemShop();
    User user = new User();

    public Console() {

    }

    private void notificationMess() {
        System.out.println("Lựa chọn không hợp lệ!");
        System.out.println("Mời bạn nhập lại");
    }

    public void menuMain() {
        int playerChoice;
        do {
            System.out.println("====> CONSOLE ADMIN ROLE <====");
            System.out.println("1. Chức năng phần User");
            System.out.println("2. Chức năng phần Shop");
            System.out.println("0. Thoát");

            //Cập nhật list User
            listUser.updateInformationListFromFile(fileUser);
            listItemShop.updateInformationListFromFile(fileItem);

            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1: //User
                    menuUser();
                    break;
                case 2: //Shop
                    menuShop();
                    break;
                default:
                    notificationMess();
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void menuUser() {
        int playerChoice;
        do {
            System.out.println("====> OPTION USER <====");
            System.out.println("1. Thêm User");
            System.out.println("2. Xóa User");
            System.out.println("3. Cộng Gold của User");
            System.out.println("4. Trừ Gold của User");
            System.out.println("0. Thoát");

            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1:
                    listUser.registerUser();
                    break;
                case 2:
                    System.out.println("Nhập ID của User cần xóa: ");
                    user.inputNameID();
                    listUser.updateInformationUserFromFile(user);
                    if (listUser.checknameIDUserFromList(user)) {
                        listUser.removeUser(user);
                        listUser.updateInformationUserToFile(user, FileName.fileUser);
                    }
                    break;
                case 3:
                    System.out.println("Nhập ID của User cần Cộng Gold: ");
                    user.inputNameID();
                    listUser.updateInformationUserFromFile(user);
                    if (listUser.checknameIDUserFromList(user)) {
                        System.out.print("Nhập số Gold: ");
                        user.addNumGold(Integer.parseInt(sc.nextLine()));
                        listUser.updateInformationUserToFile(user, FileName.fileUser);
                    }
                    break;
                case 4:
                    System.out.println("Nhập ID của User cần Trừ Gold: ");
                    user.inputNameID();
                    listUser.updateInformationUserFromFile(user);
                    if (listUser.checknameIDUserFromList(user)) {
                        System.out.print("Nhập số Gold: ");
                        user.subNumGold(Integer.parseInt(sc.nextLine()));
                        listUser.updateInformationUserToFile(user, FileName.fileUser);
                    }
                    break;
                default:
                    notificationMess();
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void menuShop() {
        int playerChoice;
        do {
            System.out.println("====> OPTION SHOP <====");
            System.out.println("1. In các sản phẩm hiện có");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("0. Thoát");

            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1:
                    listItemShop.printAllItemFromList();
                    break;
                case 2:
                    menuAddItemShop();
                    break;
                case 3:
                    System.out.println("====> REMOVE ITEM SHOP <====");
                    System.out.print("Nhập ID: ");
                    listItemShop.removeSTT(Integer.parseInt(sc.nextLine()) - 1);
                    System.out.println("----> **** <----");
                    break;
                default:
                    notificationMess();
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void menuAddItemShop() {
        int playerChoice;
        do {
            System.out.println("====> ADD ITEM SHOP <====");
            System.out.println("1. Loại Item Normal");
            System.out.println("2. Loại Item Ship Normal");
            System.out.println("3. Loại Item Ship Rocket");
            System.out.println("0. Thoát");

            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1:
                    Item item = new Item_Normal();
                    item.inputItemData();
                    listItemShop.addItem(item);
                    listItemShop.sortListItemByID();
                    listItemShop.updateListItemToFile(fileItem);
                    break;
                case 2:
                    Item item1 = new Item_Ship_Normal();
                    item1.inputItemData();
                    listItemShop.addItem(item1);
                    listItemShop.sortListItemByID();
                    listItemShop.updateListItemToFile(fileItem);
                    break;
                case 3:
                    Item item2 = new Item_Ship_Rocket();
                    item2.inputItemData();
                    listItemShop.addItem(item2);
                    listItemShop.sortListItemByID();
                    listItemShop.updateListItemToFile(fileItem);
                    break;
                default:
                    notificationMess();
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }



}
