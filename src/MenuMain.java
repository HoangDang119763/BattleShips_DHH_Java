import java.util.ArrayList;
import java.util.Scanner;

public class MenuMain implements FileName {
    Scanner sc = new Scanner(System.in);
    //Phần người dùng
    User user = new User();
    ListUser listUser = new ListUser();
    ListItemShop listItemShop = new ListItemShop();

    //Thông báo không hợp lệ
    private void notificationMess() {
        System.out.println("Lựa chọn không hợp lệ!");
        System.out.println("Mời bạn nhập lại");
    }

    public void menuLogin() {
        int playerChoice;
        do {
            System.out.println("====> CLIENT <====");
            System.out.println("1.Đăng nhập\n2.Đăng kí\n3.Quên mật khẩu\n0.Thoát");

            //Cập nhật list User
            listUser.updateInformationListFromFile(fileUser);
            listItemShop.updateInformationListFromFile(fileItem);

            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1: //Đăng nhập
                    login();
                    break;
                case 2: //Đăng kí
                    register();
                    break;
                case 3: //Quên mật khẩu
                    forgetPassword();
                    break;
                case 110604://Chức năng admin
                    Console console = new Console();
                    console.menuMain();
                    listUser.removeAllUser();
                    listUser.updateListUserFromFile(fileUser);
                    listItemShop.removeAllItem();
                    listItemShop.updateListItemFromFile(fileItem);
                    break;
                default:
                    notificationMess();
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void login() {
        int count = 0;
        while (count < 3) {
            System.out.println("====> LOGIN <====");
            user.inputNameID();
            //Nhập 0 để thoát
            if (user.getNameID().equals("0")) break;
            user.inputPassword();
            if (listUser.checkInputUserFromList(user)) break;
            else {
                System.out.println("Bạn đã nhập sai tài khoản (mật khẩu)!");
                System.out.println("Mời bạn nhập lại (Bấm 0 để thoát)");
            }
            count++;
            if (count == 3) {
                System.out.println("Bạn đã nhập sai quá 3 lần!");
                askForRegister();
                System.out.println("THÔNG BÁO THOÁT RA CLIENT!");
                break;
            }
        }
        if (listUser.checkInputUserFromList(user)) {
            listUser.updateInformationUserFromFile(user);
            menu();
            user = new User();
        }
    }

    public void register() {
        do {
            System.out.println("====> REGISTER <====");
            user.inputNameID();
            //Nhập 0 để thoát
            if (user.getNameID().equals("0")) break;
            if (user.getNameID().contains(" ")) {
                System.out.println("Tài khoản không được chứa khoảng cách!\nMời bạn nhập lại tài khoản khác (Bấm 0 để thoát)");
            } else if (user.getNameID().length() <= 5) {
                System.out.println("Tài khoản phải có ít nhất 6 kí tự!\nMời bạn nhập lại tài khoản khác (Bấm 0 để thoát)");
            } else {
                user.inputPassword();
                user.inputNamePlayer();
                //Nếu không có trong danh sách thì dừng
                if (!listUser.checknameIDUserFromList(user)) break;
                System.out.println("Tài khoản đã có người sử dụng!\nMời bạn nhập lại tài khoản khác (Bấm 0 để thoát)");
            }
        } while (true);
        if (!listUser.checknameIDUserFromList(user)) {
            listUser.addUser(user);
            listUser.updateListUserToFile(FileName.fileUser);
            user = new User();
        }
        System.out.println("----> **** <----");
    }

    public void askForRegister() {
        int playerChoice;
        System.out.println("Bạn có muốn tạo tài khoản mới?");

        do {
            System.out.println("1.Có\n2.Không");
            playerChoice = Integer.parseInt(sc.nextLine());
            if (playerChoice == 1) {
                register();
                break;
            } else if (playerChoice == 2) break;
            else {
                System.out.println("Lựa chọn không hợp lệ!");
                System.out.println("Mời bạn nhập lại");
            }
        } while (true); //Vì chỉ muốn trả lời 1 trong 2
        System.out.println();
    }

    public void changePassword() {
        while (true) {
            System.out.println("====> CHANGE PASSWORD <====");
            System.out.println("Nhập mật khẩu hiện tại (Bấm 0 để thoát)");
            String temp1 = sc.nextLine();
            if (temp1.equals("0")) break;
            //Nếu mật khẩu đúng
            if (user.getPasswordID().equals(temp1)) {
                do {
                    System.out.println("Nhập mật khẩu mới (Bấm 0 để thoát)");
                    String temp2 = sc.nextLine();
                    if (temp2.equals("0")) break;
                    System.out.println("Nhập lại mật khẩu mới");
                    String temp3 = sc.nextLine();

                    //Nếu 2 cái mật khẩu vừa nhập khác nhau => bắt nhập lại
                    if (!temp2.equals(temp3)) {
                        System.out.println("Hai mật khẩu không trùng khớp");
                        System.out.println("Mời bạn nhập lại ! (Bấm 0 để thoát)");
                    } else {
                        user.setPasswordID(temp3);
                        listUser.updateInformationUserToFile(user, FileName.fileUser);
                        break;
                    }
                } while (true);
                break;
            } else {
                System.out.println("Mật khẩu hiện tại sai!");
                System.out.println("Mời bạn nhập lại ! (Bấm 0 để thoát)");
            }
        }
        System.out.println("----> **** <----");
    }

    public void forgetPassword() {
        while (true) {
            System.out.println("====> FORGET PASSWORD <====");
            System.out.println("(Bấm 0 để thoát)");
            user.inputNameID();
            if (user.getNameID().equals("0")) break;
            //Kiểm tra tài khoản vừa nhập có trong danh sách tài khoản đăng kí
            if (listUser.checknameIDUserFromList(user)) {
                System.out.println(questionOne + " (Bấm 0 để thoát)");
                user.inputSecretQuestion();
                if (user.getSecretQuestion().equals("0")) break;
                //Nếu nhập đúng câu trả lời
                if (listUser.checkSecretQuestionByNameIDFromList(user)) {
                    do {
                        listUser.updateInformationUserFromFile(user);
                        System.out.println("Nhập mật khẩu mới");
                        String temp2 = sc.nextLine();
                        if (temp2.equals("0")) break;
                        System.out.println("Nhập lại mật khẩu mới");
                        String temp3 = sc.nextLine();

                        //Nếu 2 cái mật khẩu vừa nhập khác nhau => bắt nhập lại
                        if (!temp2.equals(temp3)) {
                            System.out.println("Hai mật khẩu không trùng khớp");
                            System.out.println("Mời bạn nhập lại ! (Bấm 0 để thoát)");
                        } else {
                            user.setPasswordID(temp3);
                            listUser.updateInformationUserToFile(user, FileName.fileUser);
                            break;
                        }
                    } while (true);
                    break;
                } else {
                    System.out.println("Câu trả lời cho câu hỏi bảo mật sai !!!");
                }
                user = new User();
            } else System.out.println("Tài khoản không tồn tại hoặc nhập sai\nMời bạn nhập lại ! (Bấm 0 để thoát)");
        }
        System.out.println("----> **** <----");
    }

    public void menu() {
        int playerChoice;
        System.out.println("**** Chào mừng đến với Battle Ships Game ****");

        do {
            System.out.println("====> MENU <====");
            System.out.println("1.Thông tin tài khoản");
            System.out.println("2.Chơi mới");
            System.out.println("3.Chơi tiếp");
            System.out.println("4.Thông tin chi tiết");
            System.out.println("5.Bảng xếp hạng");
            System.out.println("6.Cửa hàng");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0: //Thoát
                    break;
                case 1: //Thông tin tài khoản
                    menuInformationNameID();
                    break;
                case 2: //Chơi
                    menuGame();
                    break;
                case 3: //Chơi tiếp
                    menuGameStill();
                    break;
                case 4: //Thông tin chi tiết
                    detailInformationSymbols();
                    System.out.println("----> **** <----");
                    break;
                case 5: //Bảng xếp hạng
                    showListRankUser();
                    break;
                case 6: //Cửa hàng
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

    public void choiceLevelGameTypeOne() {
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
            askForSaveGameDone(user, bs);
        }
    }

    public void choiceTypeGame() {
        int playerChoice;

        do {
            System.out.println("====> TYPE GAME <====");
            System.out.println("1.Loại 1");
            System.out.println("2.Loại 2");
            System.out.println("3.Loại 3");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0: //Thoát
                    break;
                case 1:
                    choiceLevelGameTypeOne();
                    break;
                case 2:
                    if (user.numAllItemShip() == 0) {
                        System.out.println("Bạn không có thuyền phù hợp!");
                        System.out.println("Hãy vô Shop mua để chơi chế độ này!");
                    } else {
                        BattleShips_TypeTwo bs = new BattleShips_TypeTwo(user.getListItem());
                        bs.startBattle();
                        askForSaveGameDone(user, bs);
                    }
                    break;
                case 3:
                    if (user.numAllItemShip() == 0) {
                        System.out.println("Bạn không có thuyền phù hợp!");
                        System.out.println("Hãy vô Shop mua để chơi chế độ này!");
                    } else {
                        BattleShips_TypeThree bs = new BattleShips_TypeThree(user.getListItem());
                        bs.startBattle();
                        askForSaveGameDone(user, bs);
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

    public void menuGame() {
        int playerChoice;

        do {
            System.out.println("====> GAME <====");
            System.out.println("1.Chơi");
            System.out.println("2.In các game đã lưu");
            System.out.println("3.Xóa game đã lưu");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1:
                    choiceTypeGame();
                    break;
                case 2: //In các game đã lưu
                    if (user.numGameSaved() == 0)
                        System.out.println("Bạn chưa lưu game nào!");
                    else user.printAllGameSaved();
                    break;
                case 3: //Xóa game
                    if (user.numGameSaved() == 0)
                        System.out.println("Bạn chưa lưu game nào!");
                    else askForRemoveGameDone();
                    break;
                default:
                    notificationMess();//Done!
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void menuGameStill() {
        int playerChoice;

        do {
            System.out.println("====> GAME STILL PLAYING <====");
            System.out.println("1.In các game đang dang dở đã lưu");
            System.out.println("2.Xóa game đã lưu");
            System.out.println("3.Chọn game đã lưu để chơi tiếp theo STT trong danh sách!");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1:
                    if (user.numGameStill() == 0)
                        System.out.println("Bạn chưa lưu game nào!");
                    else user.printAllGameStill();
                    break;
                case 2:
                    if (user.numGameStill() == 0)
                        System.out.println("Bạn chưa lưu game nào!");
                    else askForRemoveGameStill();
                    System.out.println("----> **** <----");
                    break;
                case 3:
                    int stt, type;
                    if (user.numGameStill() == 0)
                        System.out.println("Bạn chưa lưu game nào!");
                    else {
                        do {
                            user.printAllGameStillByType();
                            //Tức là có dữ liệu
                            System.out.println("Mời bạn chọn loại Game muốn chơi tiếp! (Bấm 0 để thoát!)");
                            System.out.println("Loại: <<1>>   <<2>>   <<3>>");
                            type = Integer.parseInt(sc.nextLine());
                            if (type == 0) break;
                            else if (type == 1) {
                                if (user.numGameStillTypeOne() == 0) {
                                    System.out.println("Bạn chưa lưu Game Loại 1 nào!");
                                } else {
                                    System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!)");
                                    stt = Integer.parseInt(sc.nextLine());
                                    if (stt == 0) break;
                                    else if (stt >= 1 && stt <= user.numGameStillTypeOne()) {
                                        BattleShips_TypeOne bs = user.getGameStillTypeOneSTT(stt);
                                        user.removeGameStillTypeOneSTT(stt);
                                        bs.startBattle();
                                        askForSaveGameDone(user, bs);
                                        break;
                                    } else System.out.println("Số thứ tự không hợp lệ!");
                                }
                            } else if (type == 2) {
                                if (user.numGameStillTypeTwo() == 0) {
                                    System.out.println("Bạn chưa lưu Game Loại 2 nào!");
                                } else {
                                    System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!)");
                                    stt = Integer.parseInt(sc.nextLine());
                                    if (stt == 0) break;
                                    else if (stt >= 1 && stt <= user.numGameStillTypeTwo()) {
                                        BattleShips_TypeTwo bs = user.getGameStillTypeTwoSTT(stt);
                                        user.removeGameStillTypeTwoSTT(stt);
                                        bs.startBattle();
                                        askForSaveGameDone(user, bs);
                                        break;
                                    } else System.out.println("Số thứ tự không hợp lệ!");
                                }
                            } else if (type == 3) {
                                if (user.numGameStillTypeThree() == 0) {
                                    System.out.println("Bạn chưa lưu Game Loại 3 nào!");
                                } else {
                                    System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!)");
                                    stt = Integer.parseInt(sc.nextLine());
                                    if (stt == 0) break;
                                    else if (stt >= 1 && stt <= user.numGameStillTypeThree()) {
                                        BattleShips_TypeThree bs = user.getGameStillTypeThreeSTT(stt);
                                        user.removeGameStillTypeThreeSTT(stt);
                                        bs.startBattle();
                                        askForSaveGameDone(user, bs);
                                        break;
                                    } else System.out.println("Số thứ tự không hợp lệ!");
                                }
                            } else System.out.println("Loại Game không hợp lệ!");
                        } while (true);
                    }
                    break;
                default:
                    notificationMess();//Done!
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void menuInformationNameID() {
        int choice1;
        do {
            user.printInformationUser();
            choice1 = Integer.parseInt(sc.nextLine());
            switch (choice1) {
                case 0:
                    break;
                case 1: //Đổi mật khẩu
                    changePassword();
                    break;
                case 2: //Đặt câu hỏi bảo mật
                    if (!user.getSecretQuestion().equals("null")) System.out.println("Bạn đã cài câu hỏi bảo mật!");
                    else {
                        System.out.println("====> SECRET QUESTION <====");
                        System.out.println(questionOne + " (Bấm 0 để thoát)");
                        user.inputSecretQuestion();
                        if (user.getSecretQuestion().equals("0")) break;
                        listUser.updateInformationUserToFile(user, FileName.fileUser);
                    }
                    System.out.println("----> **** <----");
                    break;
                default:
                    notificationMess();//Done!
                    System.out.println("----> **** <----");
                    break;
            }
        } while (choice1 != 0);
        System.out.println("----> **** <----");
    }

    //===========================================================================================================
    //Hỏi việc lưu game hoàn thành của User ?
    public void askForSaveGameDone(User user, BattleShips bs) {
        user.addGame(bs);
        if (bs.getStatus()) {
            if (bs.getComputerShips() == 0) {
                user.setNumGameWin(user.getNumGameWin() + 1);
                if (bs instanceof BattleShips_TypeOne) user.addNumGold(10);
                else if (bs instanceof BattleShips_TypeTwo) user.addNumGold(50);
                else if (bs instanceof BattleShips_TypeThree) user.addNumGold(100);
            }
            int playerChoice;
            System.out.println("Bạn có muốn lưu không ?");
            System.out.println("1.Có\t0.Không");

            do {
                playerChoice = Integer.parseInt(sc.nextLine());
                switch (playerChoice) {
                    case 1: //
                        if (user.checkNullListItem() || user.getListItem().getItemNormalByID("001").getItemNum() == 0) {
                            System.out.println("Bạn chưa mua Giới hạn lưu Game!");
                            user.removeGame(bs);
                        } else if (user.getListGame().numGame() > user.getListItem().getItemNormalByID("001").getItemNum()) {
                            System.out.println("Bạn đã đạt giới hạn lưu Game! " + (user.getListGame().numGame() - 1) + "/" + user.getListItem().getItemNormalByID("001").getItemNum());
                            user.removeGame(bs);
                        }
                        break;
                    case 0:
                        user.removeGame(bs);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                        System.out.println("Mời bạn nhập lại");
                        break;
                }
            } while (playerChoice != 1 && playerChoice != 0);
        }

        listUser.updateInformationUserToFile(user, FileName.fileUser);
        System.out.println("----> **** <----");
    }

    //Hỏi việc xóa game của User ?
    public void askForRemoveGameDone() {
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
                        if (stt >= 1 && stt <= user.getListGame().getNumGameSavedFromList()) { //Không rỗng  thì kiểm trả STT có hợp lệ không
                            user.removeGameSavedSTT(stt);
                            listUser.updateInformationUserToFile(user, FileName.fileUser);
                            break;
                        } else {
                            System.out.println("Số thứ tự không hợp lệ!");
                            System.out.println("Mời bạn nhập lại");
                        }
                    } while (true);
                    break;
                case 2:
                    user.removeAllGameSaved();
                    listUser.updateInformationUserToFile(user, FileName.fileUser);
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

    //Hỏi việc xóa game dang dở của User ?
    public void askForRemoveGameStill() {
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
                        if (stt >= 1 && stt <= user.getListGame().getNumGameStillFromList()) { //Không rỗng  thì kiểm trả STT có hợp lệ không
                            user.removeGameStillSTT(stt);
                            listUser.updateInformationUserToFile(user, FileName.fileUser);
                            break;
                        } else {
                            System.out.println("Số thứ tự không hợp lệ!");
                            System.out.println("Mời bạn nhập lại");
                        }
                    } while (true);
                    break;
                case 2:
                    user.removeAllGameStill();
                    listUser.updateInformationUserToFile(user, FileName.fileUser);
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

    public void detailInformationSymbols() {
        System.out.println("Các kí hiệu quy ước");
        System.out.println("Kí hiệu tượng trưng cho Player: P");
        System.out.println("Kí hiệu tượng trưng cho Computer: C");
        System.out.println("Kí hiệu PLayer tiêu diệt được Computer: D");
        System.out.println("Kí hiệu PLayer bị tiêu diệt bởi Computer: d");
        System.out.println("Kí hiệu PLayer đánh hụt: -");
        System.out.println("Kí hiệu PLayer đánh nhầm: x");
        System.out.println("Kí hiệu PLayer tiêu diệt được Computer tại vị trí trùng nhau và chưa bị tiêu diệt: L");
        System.out.println("Kí hiệu PLayer tiêu diệt được Computer tại vị trí trùng nhau và đã bị tiêu diệt: l");
        System.out.println("Kí hiệu cả Player và Computer đều bắn được điểm trùng: S");
    }

    public void showListRankUser() {
        System.out.println("====> LIST RANK (TOP 10) <====");
        ArrayList<User> temp = listUser.sortListUserByNumGameWinToLower();
        for (int i = 1; i <= temp.size(); i++) {
            System.out.println(i + ". " + temp.get(i - 1).getNamePlayer() + "(" + temp.get(i - 1).getNameID() + ") | Số Game Win: " + temp.get(i - 1).getNumGameWin());
        }
        System.out.println("----> **** <----");
    }

    public void menuShop() {
        while (true) {
            System.out.println("====> SHOP <====");
            listItemShop.printAllItemFromList();
            System.out.println("====> <====");
            System.out.println("1.Mua sản phẩm");
            System.out.println("0.Thoát");
            String tmp = sc.nextLine();
            if (tmp.equals("0")) break;
            else if (tmp.equals("1")) {
                menuBuyItem();
            } else {
                System.out.println("Lựa chọn không hợp lệ\nMời bạn nhập lại");
            }
        }
        System.out.println("----> **** <----");
    }

    public void menuBuyItem() {
        while (true) {
            System.out.print("Mời bạn nhập ID sản phẩm muốn mua (Bấm 0 để thoát) ");
            String tmp = sc.nextLine();
            if (tmp.equals("0")) break;
            else if (!listItemShop.checkIDItemFromList(tmp))
                System.out.println("ID sản phẩm không hợp lệ\nMời bạn nhập lại");
            else if (listItemShop.checkIDItemFromList(tmp)) {
                if (!user.getListItem().checkIDItemFromList(tmp)) {
                    user.getListItem().addItem(listItemShop.getItemByID(tmp));
                    user.getListItem().sortListItemByID();
                }
                System.out.print("Nhập số lượng cần mua: (Bấm 0 để thoát) ");
                int num = Integer.parseInt(sc.nextLine());
                if (num == 0) break;
                else if (user.getNumGold() >= (num * listItemShop.getItemByID(tmp).getItemPrice())) {
                    Item tmp2 = listItemShop.getItemByID(tmp);
                    user.subNumGold(tmp2.getItemPrice() * num);
                    if (tmp2 instanceof Item_Normal)
                        user.getListItem().getItemNormalByID(tmp).addItemNum(num);
                    else if (tmp2 instanceof Item_Ship_Normal)
                        user.getListItem().getItemShipNormalByID(tmp).addItemNum(num);
                    else if (tmp2 instanceof Item_Ship_Rocket)
                        user.getListItem().getItemShipRocketByID(tmp).addItemNum(num);
                    System.out.println("Mua thành công!");
                    break;
                } else {
                    System.out.println("Bạn không đủ GOLD!");
                    break;
                }
            }
        }
        listUser.updateInformationUserToFile(user, FileName.fileUser);
    }

    public static void main(String[] args) {

        MenuMain menuMain = new MenuMain();
//        //menuMain.initTest();
        menuMain.menuLogin();
        String a = "";
        a.concat("");
    }
}
