//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class MenuMainTest implements FileName {
//    Scanner sc = new Scanner(System.in);
//    //Phần người dùng
//    User user = new User();
//    ListUser listUser = new ListUser();
//    ListItemShop listItemShop = new ListItemShop();
//
//    //Thông báo không hợp lệ
//    private void notificationMess() {
//        System.out.println("Lựa chọn không hợp lệ!");
//        System.out.println("Mời bạn nhập lại");
//    }
//
//    public void menuLogin() {
//        int playerChoice;
//        do {
//            System.out.println("====> CLIENT <====");
//            System.out.println("1.Đăng nhập\n2.Đăng kí\n3.Quên mật khẩu\n0.Thoát");
//
//            //Cập nhật list User
//            listUser.updateInformationListFromFile(fileUser);
//            listItemShop.updateInformationListFromFile(fileItem);
//
//            playerChoice = Integer.parseInt(sc.nextLine());
//            switch (playerChoice) {
//                case 0:
//                    break;
//                case 1: //Đăng nhập
//                    if (listUser.login(user)) menu();
//                    break;
//                case 2: //Đăng kí
//                    listUser.registerUser();
//                    break;
//                case 3: //Quên mật khẩu
//                    listUser.forgetPasswordUser();
//                    break;
//                case 110604://Chức năng admin
//                    Console console = new Console();
//                    console.menuMain();
//                    listUser.removeAllUser();
//                    listUser.updateListUserFromFile(fileUser);
//                    listItemShop.removeAllItem();
//                    listItemShop.updateListItemFromFile(fileItem);
//                    break;
//                default:
//                    notificationMess();
//                    System.out.println("----> **** <----");
//                    break;
//            }
//        } while (playerChoice != 0);
//        System.out.println("----> **** <----");
//    }
//
//    public void menu() {
//        int playerChoice;
//        System.out.println("**** Chào mừng đến với Battle Ships Game ****");
//
//        do {
//            System.out.println("====> MENU <====");
//            System.out.println("1.Thông tin tài khoản");
//            System.out.println("2.Chơi mới");
//            System.out.println("3.Chơi tiếp");
//            System.out.println("4.Thông tin chi tiết");
//            System.out.println("5.Bảng xếp hạng");
//            System.out.println("6.Cửa hàng");
//            System.out.println("0.Thoát");
//            playerChoice = Integer.parseInt(sc.nextLine());
//            switch (playerChoice) {
//                case 0: //Thoát
//                    break;
//                case 1: //Thông tin tài khoản
//                    menuInformationNameID();
//                    break;
//                case 2: //Chơi
//                    menuGame();
//                    break;
//                case 3: //Chơi tiếp
//                    menuGameStill();
//                    break;
//                case 4: //Thông tin chi tiết
//                    detailInformationSymbols();
//                    System.out.println("----> **** <----");
//                    break;
//                case 5: //Bảng xếp hạng
//                    listUser.showListRankUser();
//                    break;
//                case 6: //Cửa hàng
//                    menuShop();
//                    break;
//                default:
//                    notificationMess();
//                    System.out.println("----> **** <----");
//                    break;
//            }
//        } while (playerChoice != 0);
//        System.out.println("----> **** <----");
//    }
//
//    public void choiceTypeGame() {
//        int playerChoice;
//
//        do {
//            System.out.println("====> TYPE GAME <====");
//            System.out.println("1.Loại 1");
//            System.out.println("2.Loại 2");
//            System.out.println("3.Loại 3");
//            System.out.println("0.Thoát");
//            playerChoice = Integer.parseInt(sc.nextLine());
//            switch (playerChoice) {
//                case 0: //Thoát
//                    break;
//                case 1:
//                    user.choiceLevelGameTypeOne();
//                    listUser.updateInformationUserToFile(user, FileName.fileUser);
//                    break;
//                case 2:
//                    user.playGameTypeTwo();
//                    listUser.updateInformationUserToFile(user, FileName.fileUser);
//                    break;
//                case 3:
//                    user.playGameTypeThree();
//                    listUser.updateInformationUserToFile(user, FileName.fileUser);
//                    break;
//                default:
//                    notificationMess();
//                    System.out.println("----> **** <----");
//                    break;
//            }
//        } while (playerChoice != 0);
//        System.out.println("----> **** <----");
//    }
//
//    public void menuGame() {
//        int playerChoice;
//
//        do {
//            System.out.println("====> GAME <====");
//            System.out.println("1.Chơi");
//            System.out.println("2.In các game đã lưu");
//            System.out.println("3.Xóa game đã lưu");
//            System.out.println("0.Thoát");
//            playerChoice = Integer.parseInt(sc.nextLine());
//            switch (playerChoice) {
//                case 0:
//                    break;
//                case 1:
//                    choiceTypeGame();
//                    break;
//                case 2: //In các game đã lưu
//                    user.printAllGameSaved();
//                    break;
//                case 3: //Xóa game
//                    user.removeGameDone();
//                    break;
//                default:
//                    notificationMess();//Done!
//                    System.out.println("----> **** <----");
//                    break;
//            }
//        } while (playerChoice != 0);
//        System.out.println("----> **** <----");
//    }
//
//    public void menuGameStill() {
//        int playerChoice;
//
//        do {
//            System.out.println("====> GAME STILL PLAYING <====");
//            System.out.println("1.In các game đang dang dở đã lưu");
//            System.out.println("2.Xóa game đã lưu");
//            System.out.println("3.Chọn game đã lưu để chơi tiếp theo STT trong danh sách!");
//            System.out.println("0.Thoát");
//            playerChoice = Integer.parseInt(sc.nextLine());
//            switch (playerChoice) {
//                case 0:
//                    break;
//                case 1:
//                    user.printAllGameStill();
//                    break;
//                case 2:
//                    user.removeGameStill();
//                    break;
//                case 3:
//                    user.choiceGameStill();
//                    listUser.updateInformationUserToFile(user, FileName.fileUser);
//                    break;
//                default:
//                    notificationMess();//Done!
//                    System.out.println("----> **** <----");
//                    break;
//            }
//        } while (playerChoice != 0);
//        System.out.println("----> **** <----");
//    }
//
//    public void menuInformationNameID() {
//        int choice1;
//        do {
//            user.printInformationUser();
//            choice1 = Integer.parseInt(sc.nextLine());
//            switch (choice1) {
//                case 0:
//                    break;
//                case 1: //Đổi mật khẩu
//                    listUser.changePasswordUser(user);
//                    break;
//                case 2: //Đặt câu hỏi bảo mật
//                    listUser.setSecretQuestionUser(user);
//                    break;
//                default:
//                    notificationMess();//Done!
//                    System.out.println("----> **** <----");
//                    break;
//            }
//        } while (choice1 != 0);
//        System.out.println("----> **** <----");
//    }
//    //===========================================================================================================
//    public void detailInformationSymbols() {
//        System.out.println("Các kí hiệu quy ước");
//        System.out.println("Kí hiệu tượng trưng cho Player: P");
//        System.out.println("Kí hiệu tượng trưng cho Computer: C");
//        System.out.println("Kí hiệu PLayer tiêu diệt được Computer: D");
//        System.out.println("Kí hiệu PLayer bị tiêu diệt bởi Computer: d");
//        System.out.println("Kí hiệu PLayer đánh hụt: -");
//        System.out.println("Kí hiệu PLayer đánh nhầm: x");
//        System.out.println("Kí hiệu PLayer tiêu diệt được Computer tại vị trí trùng nhau và chưa bị tiêu diệt: L");
//        System.out.println("Kí hiệu PLayer tiêu diệt được Computer tại vị trí trùng nhau và đã bị tiêu diệt: l");
//        System.out.println("Kí hiệu cả Player và Computer đều bắn được điểm trùng: S");
//    }
//
//    public void menuShop() {
//        while (true) {
//            System.out.println("====> SHOP <====");
//            listItemShop.printAllItemFromList();
//            System.out.println("1.Mua sản phẩm");
//            System.out.println("0.Thoát");
//            String tmp = sc.nextLine();
//            if (tmp.equals("0")) break;
//            else if (tmp.equals("1")) {
//                user.buyItem(listItemShop);
//                listUser.updateInformationUserToFile(user, FileName.fileUser);
//            } else {
//                System.out.println("Lựa chọn không hợp lệ\nMời bạn nhập lại");
//            }
//        }
//        System.out.println("----> **** <----");
//    }
//
//    public void initTest() {
////        Item item1 = new Item_Normal("001", "Giới hạn lưu game", 10, 0);
////        Item item2 = new Item_Ship_Normal("002", "Thuyền thường-Cở nhỏ", 10, 0, 2);
////        Item item3 = new Item_Ship_Rocket("003", "Thuyền chiến-Cở nhỏ", 20, 0, 2, 2);
////        Item item4 = new Item_Ship_Rocket("004", "Thuyền chiến-Cở to", 25, 0, 3, 2);
////        Item item5 = new Item_Ship_Rocket("005", "Thuyền chiến-Cở khủng", 40, 0, 4, 3);
////        Item item6 = new Item_Ship_Rocket("006", "Tàu chiến FX-Cở nhỏ", 50, 0, 5, 5);
////        Item item7 = new Item_Ship_Rocket("007", "Tàu chiến FX-Cở to", 55, 0, 6, 6);
////
////        User user = new User("danghuyhoang", "danghuyhoang", "Hoang119763");
////        user.setNumGold(5000);
////
////        ListItemShop list1 = new ListItemShop();
////        list1.addItem(item1);
////        list1.addItem(item2);
////        list1.addItem(item3);
////        list1.addItem(item4);
////        list1.addItem(item5);
////        list1.addItem(item6);
////        list1.addItem(item7);
////        list1.printAllItemFromList();
////        list1.updateListItemToFile("ListItem.txt");
////
////        ListUser listUser = new ListUser();
////        listUser.addUser(user);
////        listUser.printAllUserFromList();
////        listUser.updateListUserToFile("ListUser.dat");
//    }
//
//    public static void main(String[] args) {
//
//        MenuMainTest menuMain = new MenuMainTest();
////        //menuMain.initTest();
//        menuMain.menuLogin();
//
//
//    }
//}
