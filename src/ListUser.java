import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ListUser implements FileName, Serializable {
    private ArrayList<User> listUser;

    public ListUser() {
        this.listUser = new ArrayList<>();
    }

    public boolean fileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public void addUser(User user) {
        this.listUser.add(user);
    }

    public void removeUser(User user) {
        this.listUser.remove(user);
    }

    //Kiểm tra rỗng
    public boolean checkNull() {
        return this.listUser.isEmpty();
    }

    //Số user
    public int numUser() {
        return this.listUser.size();
    }

    //Xóa hết User đã lưu
    public void removeAllUser() {
        this.listUser.removeAll(listUser);
    }

    //Xóa theo số thứ tự
    public void removeSTT(int STT) {
        this.listUser.remove(STT);
    }

    public User getUser(int STT) {
        return listUser.get(STT);
    }

    //================================================TO LIST=====================================================
    //Xóa User theo nameID
    public void removeUserByNameIDToList(User user) {
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                removeUser(user);
                break;
            }
        }
    }

    public void setNamePlayerByNameIDToList(User user, String namePlayer) {
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) user1.setNamePlayer(namePlayer);
        }
    }

    public void setPasswordIDByNameIDToList(User user, String passwordID) {
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) user1.setPasswordID(passwordID);
        }
    }

    public void setSecretQuestionByNameIDToList(User user, String secretQuestion) {
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) user1.setSecretQuestion(secretQuestion);
        }
    }

    public void setNumGameWinByNameIDToList(User user, int numGameWin) {
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) user1.setNumGameWin(numGameWin);
        }
    }

    //=====================================================FROM LIST===============================================
    //Kiểm tra tài khoản người dùng nhập có đúng không (nếu đã đăng kí) và chưa đăng kí
    public boolean checkInputUserFromList(User user) {
        boolean flag = false;
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID()) && user1.getPasswordID().equals(user.getPasswordID())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //Kiểm tra tài khoản
    public boolean checknameIDUserFromList(User user) {
        boolean flag = false;
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //Kiểm tra mật khẩu
    public boolean checkPasswordIDByNameIDFromList(User user) {
        boolean flag = false;
        for (User user1 : listUser) {
            if (user1.getPasswordID().equals(user.getPasswordID())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //Kiểm tra câu hỏi bảo mật
    public boolean checkSecretQuestionByNameIDFromList(User user) {
        boolean flag = false;
        for (User user1 : listUser) {
            if (user1.getSecretQuestion().equals(user.getSecretQuestion())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean checkNumGameWinByNameIDFromList(User user) {
        boolean flag = false;
        for (User user1 : listUser) {
            if (user1.getNumGameWin() == user.getNumGameWin()) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public String getPasswordIDByNameIDFromList(User user) {
        String temp = "";
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                temp = user1.getPasswordID();
                break;
            }
        }
        return temp;
    }

    public String getNamePlayerByNameIDFromList(User user) {
        String temp = "";
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                temp = user1.getNamePlayer();
                break;
            }
        }
        return temp;
    }

    public String getSecretQuestionByNameIDFromList(User user) {
        String temp = "";
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                temp = user.getSecretQuestion();
                break;
            }
        }
        return temp;
    }

    public int getNumGameWinByNameIDFromList(User user) {
        int temp = 0;
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                temp = user1.getNumGameWin();
                break;
            }
        }
        return temp;
    }

    public ListGame getListGameByNameIDFromList(User user) {
        ListGame listGame = new ListGame();
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                for (int i = 0; i < user1.getListGame().numGame(); i++) {
                    listGame.addGame(user1.getListGame().getGame(i));
                }
                break;
            }
        }
        return listGame;
    }

    public ListItemUser getListItemByNameIDFromList(User user) {
        ListItemUser listItem = new ListItemUser();
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                for (int i = 0; i < user1.getListItem().numItem(); i++) {
                    listItem.addItem(user1.getListItem().getItem(i));
                }
                break;
            }
        }
        return listItem;
    }

    public int getNumGoldByNameIDFromList(User user) {
        int temp = 0;
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                temp = user1.getNumGold();
                break;
            }
        }
        return temp;
    }

    //In danh sách User đã lưu ra màn hình
    public void printAllUserFromList() {
        int stt = 1;
        for (User user : listUser) {
            System.out.println("\nNgười dùng thứ " + stt + ":");
            stt++;
            System.out.println(user);
        }
    }

    //======================================================TO FILE==============================================
    //Lưu danh sách vào file
    public void updateListUserToFile(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (User user : listUser) {
                objectOut.writeObject(user);
            }
            objectOut.close();
            fileOut.close();
            //System.out.println("Đã lưu thành công!");
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("Lưu thất bại!");
        }
    }

    public void updateInformationUserToFile(User user, String fileName) {
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                //user1.setNameID(user.getNameID());
                user1.setPasswordID(user.getPasswordID());
                user1.setNamePlayer(user.getNamePlayer());
                user1.setSecretQuestion(user.getSecretQuestion());
                user1.setNumGameWin(user.getNumGameWin());
                user1.setListGame(user.getListGame());
                user1.setListItem(user.getListItem());
                user1.setNumGold(user.getNumGold());
                updateListUserToFile(fileName);
                break;
            }
        }
    }

    public void updateNumGoldUserToFile(User user, String fileName) {
        for (User user1 : listUser) {
            if (user1.getNameID().equals(user.getNameID())) {
                user1.setNumGold(user.getNumGold());
                updateListUserToFile(fileName);
                break;
            }
        }
    }

    public void addUserToFile(User user, String fileName) {
        listUser.add(user);
        updateListUserToFile(fileName);
    }

    //Xóa User thep STT
    public void removeUserSavedSTTToFile(int STT, String fileName) {
        removeSTT(STT - 1);
        updateListUserToFile(fileName);
    }

    public void removeAllUserSaved(String fileName) {
        removeAllUser();
        updateListUserToFile(fileName);
    }

    //=======================================================FROM FILE============================================
    //Cập nhật danh sách từ file
    public void updateListUserFromFile(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            try {
                while (true) {
                    Object obj = objectIn.readObject();
                    if (obj instanceof User) {
                        User user = (User) obj;
                        this.listUser.add(user);
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

    public void updateInformationUserFromFile(User user) {
        user.setPasswordID(getPasswordIDByNameIDFromList(user));
        user.setNamePlayer(getNamePlayerByNameIDFromList(user));
        user.setSecretQuestion(getSecretQuestionByNameIDFromList(user));
        user.setNumGameWin(getNumGameWinByNameIDFromList(user));
        user.setListGame(getListGameByNameIDFromList(user));
        user.setListItem(getListItemByNameIDFromList(user));
        user.setNumGold(getNumGoldByNameIDFromList(user));
    }

    public void updateInformationListFromFile(String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListUserFromFile(fileName);
        }
    }

    public boolean getStatusUpdateInformationListUserFromFile(String fileName) {
        boolean flag = true;
        if (checkNull()) {
            if (fileExist(fileName)) {
                updateListUserFromFile(fileName);
                if (checkNull()) flag = false;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public ArrayList<User> sortListUserByNumGameWinToLower() {
        ArrayList<User> temp = (ArrayList<User>) this.listUser.clone();
        for (int i = 0; i < numUser() - 1; i++) {
            for (int j = i + 1; j < numUser(); j++) {
                if (getUser(i).getNumGameWin() < getUser(j).getNumGameWin()) {
                    Collections.swap(temp, i, j);
                }
            }
        }
        return temp;
    }

    public ArrayList<User> sortListUserByNumGameWinToUpper() {
        ArrayList<User> temp = (ArrayList<User>) this.listUser.clone();
        for (int i = 0; i < numUser() - 1; i++) {
            for (int j = i + 1; j < numUser(); j++) {
                if (getUser(i).getNumGameWin() > getUser(j).getNumGameWin()) {
                    Collections.swap(temp, i, j);
                }
            }
        }
        return temp;
    }

    public boolean login(User user) {
        boolean flag = false;
        int count = 0;
        while (count < 3) {
            System.out.println("====> LOGIN <====");
            user.inputNameID();
            if (user.getNameID().equals("0")) break;
            user.inputPassword();
            if (checkInputUserFromList(user)) break;
            else {
                System.out.println("Bạn đã nhập sai tài khoản (mật khẩu)!");
                System.out.println("Mời bạn nhập lại (Bấm 0 để thoát)");
            }
            count++;
            if (count == 3) {
                System.out.println("Bạn đã nhập sai quá 3 lần!");
                askForRegisterUser();
                System.out.println("THÔNG BÁO THOÁT RA CLIENT!");
                break;
            }
        }
        if (checkInputUserFromList(user)) {
            updateInformationUserFromFile(user);
            flag = true;
        }
        return flag;
    }

    public void registerUser() {
        User user = new User();
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
                if (!checknameIDUserFromList(user)) break;
                System.out.println("Tài khoản đã có người sử dụng!\nMời bạn nhập lại tài khoản khác (Bấm 0 để thoát)");
            }
        } while (true);
        if (!checknameIDUserFromList(user)) {
            addUser(user);
            updateListUserToFile(FileName.fileUser);
        }
        System.out.println("----> **** <----");
    }

    public void askForRegisterUser() {
        int playerChoice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bạn có muốn tạo tài khoản mới?");

        do {
            System.out.println("1.Có\n2.Không");
            playerChoice = Integer.parseInt(sc.nextLine());
            if (playerChoice == 1) {
                registerUser();
                break;
            } else if (playerChoice == 2) break;
            else {
                System.out.println("Lựa chọn không hợp lệ!");
                System.out.println("Mời bạn nhập lại");
            }
        } while (true); //Vì chỉ muốn trả lời 1 trong 2
        System.out.println();
    }

    public void forgetPasswordUser() {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        while (true) {
            System.out.println("====> FORGET PASSWORD <====");
            System.out.println("(Bấm 0 để thoát)");
            user.inputNameID();
            if (user.getNameID().equals("0")) break;
            //Kiểm tra tài khoản vừa nhập có trong danh sách tài khoản đăng kí
            if (checknameIDUserFromList(user)) {
                System.out.println(questionOne + " (Bấm 0 để thoát)");
                user.inputSecretQuestion();
                if (user.getSecretQuestion().equals("0")) break;
                //Nếu nhập đúng câu trả lời
                if (checkSecretQuestionByNameIDFromList(user)) {
                    do {
                        updateInformationUserFromFile(user);
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
                            updateInformationUserToFile(user, FileName.fileUser);
                            break;
                        }
                    } while (true);
                    break;
                } else {
                    System.out.println("Câu trả lời cho câu hỏi bảo mật sai !!!");
                }
            } else System.out.println("Tài khoản không tồn tại hoặc nhập sai\nMời bạn nhập lại ! (Bấm 0 để thoát)");
        }
        System.out.println("----> **** <----");
    }

    public void changePasswordUser(User user) {
        Scanner sc = new Scanner(System.in);
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
                        updateInformationUserToFile(user, FileName.fileUser);
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

    public void setSecretQuestionUser(User user) {
        if (!user.getSecretQuestion().equals("null")) System.out.println("Bạn đã cài câu hỏi bảo mật!");
        else {
            System.out.println("====> SECRET QUESTION <====");
            System.out.println(questionOne + " (Bấm 0 để thoát)");
            user.inputSecretQuestion();
            if (!user.getSecretQuestion().equals("0")) updateInformationUserToFile(user, FileName.fileUser);
        }
        System.out.println("----> **** <----");
    }

    public void showListRankUser() {
        System.out.println("====> LIST RANK (TOP 10) <====");
        ArrayList<User> temp = this.sortListUserByNumGameWinToLower();
        for (int i = 1; i <= temp.size(); i++) {
            System.out.println(i + ". " + temp.get(i - 1).getNamePlayer() + "(" + temp.get(i - 1).getNameID() + ") | Số Game Win: " + temp.get(i - 1).getNumGameWin());
        }
        System.out.println("----> **** <----");
    }
}
