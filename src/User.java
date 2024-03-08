import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable {
    private String nameID;//Tài khoản
    private String passwordID;//Mật khẩu
    private String namePlayer;//Tên người dùng
    private String secretQuestion; //Câu hỏi bảo mật
    private int numGameWin; //Số game win
    private ListGame listGame;
    private ListItemUser listItem;
    private int numGold;

    public User() {
        this.secretQuestion = "null";
        this.numGameWin = 0;
        this.numGold = 30;
        this.listGame = new ListGame();
        this.listItem = new ListItemUser();
    }

    public User(String nameID, String passwordID, String namePlayer) {
        this.nameID = nameID;
        this.passwordID = passwordID;
        this.namePlayer = namePlayer;
        this.numGameWin = 0;
        this.numGold = 500;
        this.listGame = new ListGame();
       this.listItem = new ListItemUser();
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public String getPasswordID() {
        return passwordID;
    }

    public void setPasswordID(String passwordID) {
        this.passwordID = passwordID;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public int getNumGameWin() {
        return numGameWin;
    }

    public void setNumGameWin(int numGameWin) {
        this.numGameWin = numGameWin;
    }

    public ListGame getListGame() {
        return listGame;
    }

    public void setListGame(ListGame listGame) {
        this.listGame = listGame;
    }

    public ListItemUser getListItem() {
        return listItem;
    }

    public void setListItem(ListItemUser listItem) {
        this.listItem = listItem;
    }

    public int getNumGold() {
        return numGold;
    }

    public void setNumGold(int numGold) {
        this.numGold = numGold;
    }

    public void addNumGold(int numGold) {
        setNumGold(getNumGold() + numGold);
    }

    public void subNumGold(int numGold) {
        setNumGold(getNumGold() - numGold);
    }

    //Đăng nhập: Chỉ nhập TK + MK
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tài khoản: ");
        setNameID(sc.nextLine().toLowerCase());
        System.out.print("Mật khẩu: ");
        setPasswordID(sc.nextLine());
    }

    //Nhập tài khoản
    public void inputNameID() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tài khoản: ");
        setNameID(sc.nextLine().toLowerCase());
    }

    //Nhâp mật khẩu
    public void inputPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mật khẩu: ");
        setPasswordID(sc.nextLine());
    }

    public void inputNamePlayer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tên đại diện: ");
        setNamePlayer(sc.nextLine());
    }

    public void inputSecretQuestion() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Câu trả lời: ");
        setSecretQuestion(sc.nextLine());
    }

    //Tạo tài khoản
    public void registerData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tài khoản: ");
        setNameID(sc.nextLine().toLowerCase());
        System.out.print("Mật khẩu: ");
        setPasswordID(sc.nextLine());
        System.out.print("Tên đại diện: ");
        setNamePlayer(sc.nextLine());
    }

    @Override
    public String toString() {
        return "User{" +
                "nameID='" + nameID + '\'' +
                ", passwordID='" + passwordID + '\'' +
                ", namePlayer='" + namePlayer + '\'' +
                ", secretQuestion='" + secretQuestion + '\'' +
                ", numGameWin=" + numGameWin +
                ", listGame=" + listGame +
                '}';
    }

    public void printInformationUser() {
        System.out.println("====> INFORMATION <====");
        System.out.println("Tên người dùng: " + this.getNamePlayer());
        System.out.println("TK: " + this.getNameID());
        System.out.println("MK: ********");
        System.out.println("Số GAME WIN: " + this.getNumGameWin());
        System.out.println("Số GAME đã lưu: " + this.getListGame().numGame() + " (SAVED: " + this.getListGame().getNumGameSavedFromList() + "| STILL: " + this.getListGame().getNumGameStillFromList() + ")");
        System.out.println("Số GOLD: " + this.getNumGold());
        System.out.println("Danh sách tài sản: ");
        this.getListItem().printAllItemFromList();
        System.out.println("----> **** <----");
        System.out.println("1.Đổi mật khẩu");
        System.out.println("2.Đặt câu hỏi bảo mật");
        System.out.println("0.Thoát");
    }

    public int numGame() {
        return getListGame().numGame();
    }

    public void addGame(BattleShips bs) {
        getListGame().addGame(bs);
    }

    public void removeGame(BattleShips bs) {
        getListGame().removeGame(bs);
    }

    public boolean checkNullListItem() {
        return getListItem().checkNull();
    }

    public int numItemNormalByID(String ID) {
        return  getListItem().getItemNormalByID(ID).getItemNum();
    }

    public int numItemShipNormalByID(String ID) {
        return getListItem().getItemShipNormalByID(ID).getItemNum();
    }

    public int numItemShipRocketByID(String ID) {
        return getListItem().getItemShipRocketByID(ID).getItemNum();
    }

    public void addNumItemNormalByID(String ID, int num) {
        getListItem().getItemNormalByID(ID).addItemNum(num);
    }

    public void addNumItemShipNormalByID(String ID, int num) {
        getListItem().getItemShipNormalByID(ID).addItemNum(num);
    }

    public void addNumItemShipRocketByID(String ID, int num) {
        getListItem().getItemShipRocketByID(ID).addItemNum(num);
    }

    public void removeGameStillSTT(int STT) {
        getListGame().removeGameStillSTTToList(STT);
    }

    public void removeAllGameStill() {
        getListGame().removeAllGameStillToList();
    }

    public void removeGameStillTypeOneSTT(int STT) {
        getListGame().removeGameStill_TypeOneSTTToList(STT);
    }

    public void removeGameStillTypeTwoSTT(int STT) {
        getListGame().removeGameStill_TypeTwoSTTToList(STT);
    }

    public void removeGameStillTypeThreeSTT(int STT) {
        getListGame().removeGameStill_TypeThreeSTTToList(STT);
    }

    public void removeGameSavedSTT(int STT) {
        getListGame().removeGameSavedSTTToList(STT);
    }

    public void removeAllGameSaved() {
        getListGame().removeAllGameSavedToList();
    }

    public void printAllGameStill() {
        if (numGameStill() == 0)
            System.out.println("Bạn chưa lưu game nào!");
        else getListGame().printAllGameStillFromList();
        System.out.println("----> **** <----");
    }

    public void printAllGameSaved() {
        if (getListGame().getNumGameSavedFromList() == 0)
            System.out.println("Bạn chưa lưu game nào!");
        else getListGame().printAllGameSavedFromList();
        System.out.println("----> **** <----");
    }

    public void printAllGameStillByType() {
        getListGame().printAllGameStillByTypeFromList();
    }

    public int numGameStill() {
        return getListGame().getNumGameStillFromList();
    }

    public int numGameStillTypeOne() {
        return getListGame().getNumGameStill_TypeOneFromList();
    }

    public int numGameStillTypeTwo() {
        return getListGame().getNumGameStill_TypeTwoFromList();
    }

    public int numGameStillTypeThree() {
        return getListGame().getNumGameStill_TypeThreeFromList();
    }

    public BattleShips_TypeOne getGameStillTypeOneSTT(int STT) {
        return getListGame().getGameStill_TypeOneSTTFromList(STT);
    }

    public BattleShips_TypeTwo getGameStillTypeTwoSTT(int STT) {
        return getListGame().getGameStill_TypeTwoSTTFromList(STT);
    }

    public BattleShips_TypeThree getGameStillTypeThreeSTT(int STT) {
        return getListGame().getGameStill_TypeThreeSTTFromList(STT);
    }

    public int numGameSaved() {
        return getListGame().getNumGameSavedFromList();
    }

    public int numAllItemShip() {
        return getListItem().numAllItem_Ship();
    }

    public boolean checkIDItem(String ID) {
        return getListItem().checkIDItemFromList(ID);
    }

    public void addItem(Item item) {
        getListItem().addItem(item);
    }

    public void choiceLevelGameTypeOne() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Mời bạn chon độ khó: ");
        System.out.println("1.Dễ\t2.Trung bình\t0.Thoát");
        BattleShips_TypeOne bs = new BattleShips_TypeOne();
        int choice;
        do {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    break;
                case 1:
                    bs.setLevelGame(1);
                    break;
                case 2:
                    bs.setLevelGame(2);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                    break;
            }
        } while (choice != 1 && choice != 2 && choice != 0);
        if (choice != 0) {
            bs.startBattle();
            askForSaveGameDone(bs);
        }
    }

    public void choiceGameStill() {
        Scanner sc = new Scanner(System.in);
        int stt, type;
        if (numGameStill() == 0)
            System.out.println("Bạn chưa lưu game nào!");
        else {
            do {
                printAllGameStillByType();
                //Tức là có dữ liệu
                System.out.println("Mời bạn chọn loại Game muốn chơi tiếp! (Bấm 0 để thoát!)");
                System.out.println("Loại: <<1>>   <<2>>   <<3>>");
                type = Integer.parseInt(sc.nextLine());
                if (type == 0) break;
                else if (type == 1) {
                    if (numGameStillTypeOne() == 0) {
                        System.out.println("Bạn chưa lưu Game Loại 1 nào!");
                    } else {
                        System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!)");
                        stt = Integer.parseInt(sc.nextLine());
                        if (stt == 0) break;
                        else if (stt >= 1 && stt <= numGameStillTypeOne()) {
                            BattleShips_TypeOne bs = getGameStillTypeOneSTT(stt);
                            removeGameStillTypeOneSTT(stt);
                            bs.startBattle();
                            askForSaveGameDone(bs);
                            break;
                        } else System.out.println("Số thứ tự không hợp lệ!");
                    }
                } else if (type == 2) {
                    if (numGameStillTypeTwo() == 0) {
                        System.out.println("Bạn chưa lưu Game Loại 2 nào!");
                    } else {
                        System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!)");
                        stt = Integer.parseInt(sc.nextLine());
                        if (stt == 0) break;
                        else if (stt >= 1 && stt <= numGameStillTypeTwo()) {
                            BattleShips_TypeTwo bs = getGameStillTypeTwoSTT(stt);
                            removeGameStillTypeTwoSTT(stt);
                            bs.startBattle();
                            askForSaveGameDone(bs);
                            break;
                        } else System.out.println("Số thứ tự không hợp lệ!");
                    }
                } else if (type == 3) {
                    if (numGameStillTypeThree() == 0) {
                        System.out.println("Bạn chưa lưu Game Loại 3 nào!");
                    } else {
                        System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!)");
                        stt = Integer.parseInt(sc.nextLine());
                        if (stt == 0) break;
                        else if (stt >= 1 && stt <= numGameStillTypeThree()) {
                            BattleShips_TypeThree bs = getGameStillTypeThreeSTT(stt);
                            removeGameStillTypeThreeSTT(stt);
                            bs.startBattle();
                            askForSaveGameDone(bs);
                            break;
                        } else System.out.println("Số thứ tự không hợp lệ!");
                    }
                } else System.out.println("Loại Game không hợp lệ!");
            } while (true);
        }
    }

    public void playGameTypeTwo() {
        if (numAllItemShip() == 0) {
            System.out.println("Bạn không có thuyền phù hợp!");
            System.out.println("Hãy vô Shop mua để chơi chế độ này!");
        } else {
            BattleShips_TypeTwo bs = new BattleShips_TypeTwo(getListItem());
            bs.startBattle();
            askForSaveGameDone(bs);
        }
    }

    public void playGameTypeThree() {
        if (numAllItemShip()== 0) {
            System.out.println("Bạn không có thuyền phù hợp!");
            System.out.println("Hãy vô Shop mua để chơi chế độ này!");
        } else {
            BattleShips_TypeThree bs = new BattleShips_TypeThree(getListItem());
            bs.startBattle();
            askForSaveGameDone(bs);
        }
    }

    public void removeGameDone() {
        if (numGameSaved() == 0)
            System.out.println("Bạn chưa lưu game nào!");
        else askForRemoveGameDone();
    }

    public void removeGameStill() {
        if (numGameStill() == 0)
            System.out.println("Bạn chưa lưu game nào!");
        else askForRemoveGameStill();
        System.out.println("----> **** <----");
    }

    public void askForSaveGameDone(BattleShips bs) {
        Scanner sc = new Scanner(System.in);
        addGame(bs);
        if (bs.getStatus()) {
            if (bs.getComputerShips() == 0) {
                setNumGameWin(getNumGameWin() + 1);
                if (bs instanceof BattleShips_TypeOne) addNumGold(10);
                else if (bs instanceof BattleShips_TypeTwo) addNumGold(50);
                else if (bs instanceof BattleShips_TypeThree) addNumGold(100);
            }
            int playerChoice;
            System.out.println("Bạn có muốn lưu không ?");
            System.out.println("1.Có\t0.Không");

            do {
                playerChoice = Integer.parseInt(sc.nextLine());
                switch (playerChoice) {
                    case 1:
                        if (checkNullListItem() || numItemNormalByID("001") == 0) {
                            System.out.println("Bạn chưa mua Giới hạn lưu Game!");
                            removeGame(bs);
                        } else if (numGameSaved() > numItemNormalByID("001")) {
                            System.out.println("Bạn đã đạt giới hạn lưu Game! " + (numGameSaved() - 1) + "/" + numItemNormalByID("001"));
                            removeGame(bs);
                        }
                        break;
                    case 0:
                        removeGame(bs);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                        System.out.println("Mời bạn nhập lại");
                        break;
                }
            } while (playerChoice != 1 && playerChoice != 0);
        }
        System.out.println("----> **** <----");
    }

    public void askForRemoveGameDone() {
        Scanner sc = new Scanner(System.in);
        int playerChoice;
        do {
            System.out.println("Mời bạn chọn kiểu xóa:");
            System.out.println("1.Xóa game theo STT\t2.Xóa hết!\t0.Không");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 1:
                    int stt;
                    do {
                        System.out.println("Mời bạn nhập STT của game cần xóa (Bấm 0 để thoát!)");
                        stt = Integer.parseInt(sc.nextLine());
                        if (stt == 0) break;
                        if (stt >= 1 && stt <= getListGame().getNumGameSavedFromList()) { //Không rỗng  thì kiểm trả STT có hợp lệ không
                            removeGameSavedSTT(stt);
                            break;
                        } else {
                            System.out.println("Số thứ tự không hợp lệ!");
                            System.out.println("Mời bạn nhập lại");
                        }
                    } while (true);
                    break;
                case 2:
                    removeAllGameSaved();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void askForRemoveGameStill() {
        Scanner sc = new Scanner(System.in);
        int playerChoice;
        do {
            System.out.println("Mời bạn chọn kiểu xóa:");
            System.out.println("1.Xóa game theo STT\t2.Xóa hết!\t0.Không");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 1:
                    int stt;
                    do {
                        System.out.println("Mời bạn nhập STT của game cần xóa (Bấm 0 để thoát!)");
                        stt = Integer.parseInt(sc.nextLine());
                        if (stt == 0) break;
                        if (stt >= 1 && stt <= getListGame().getNumGameStillFromList()) { //Không rỗng  thì kiểm trả STT có hợp lệ không
                            removeGameStillSTT(stt);
                            break;
                        } else {
                            System.out.println("Số thứ tự không hợp lệ!");
                            System.out.println("Mời bạn nhập lại");
                        }
                    } while (true);
                    break;
                case 2:
                    removeAllGameStill();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void buyItem(ListItemShop listItemShop) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Mời bạn nhập ID sản phẩm muốn mua (Bấm 0 để thoát) ");
            String tmp = sc.nextLine();
            if (tmp.equals("0")) break;
            else if (!listItemShop.checkIDItemFromList(tmp))
                System.out.println("ID sản phẩm không hợp lệ\nMời bạn nhập lại");
            else if (listItemShop.checkIDItemFromList(tmp)) {
                if (!getListItem().checkIDItemFromList(tmp))
                    getListItem().addItem(listItemShop.getItem(Integer.parseInt(tmp) - 1));
                System.out.print("Nhập số lượng cần mua: (Bấm 0 để thoát) ");
                int num = Integer.parseInt(sc.nextLine());
                if (num == 0) break;
                else if (getNumGold() >= (num * listItemShop.getItem(Integer.parseInt(tmp) - 1).getItemPrice())) {
                    subNumGold(listItemShop.getItem(Integer.parseInt(tmp) - 1).getItemPrice() * num);
                    if (listItemShop.getItem(Integer.parseInt(tmp) - 1) instanceof Item_Normal)
                        getListItem().getItemNormalByID(tmp).addItemNum(num);
                    else if (listItemShop.getItem(Integer.parseInt(tmp) - 1) instanceof Item_Ship_Normal)
                        getListItem().getItemShipNormalByID(tmp).addItemNum(num);
                    else if (listItemShop.getItem(Integer.parseInt(tmp) - 1) instanceof Item_Ship_Rocket)
                        getListItem().getItemShipRocketByID(tmp).addItemNum(num);
                    System.out.println("Mua thành công!");
                    break;
                } else {
                    System.out.println("Bạn không đủ GOLD!");
                    break;
                }
            }
        }
    }
}

