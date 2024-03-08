import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListGame implements Serializable {
    private ArrayList<BattleShips> listGame;

    public ListGame() {
        this.listGame = new ArrayList<>();
    }

    public ListGame(ArrayList<BattleShips> listGame) {
        this.listGame = listGame;
    }

    public boolean fileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    //Thêm game vào danh sách để lưu
    public void addGame(BattleShips bs) {
        this.listGame.add(bs);
    }

    //Xóa game ra khỏi danh sách
    public void removeGame(BattleShips bs) {
        this.listGame.remove(bs);
    }

    public void removeGame(ArrayList<BattleShips> listGame) {
        for (BattleShips bs : listGame) this.listGame.remove(bs);
    }

    //Kiểm tra rỗng
    public boolean checkNull() {
        return this.listGame.isEmpty();
    }

    //In ra số map game đã lưu
    public int numGame() {
        return this.listGame.size();
    }

    //Xóa hết game đã lưu
    public void removeAllGame() {
        this.listGame.removeAll(listGame);
    }

    //Xóa theo số thứ tự
    public void removeSTTGame(int STT) {
        this.listGame.remove(STT);
    }

    public BattleShips getGame(int STT) {
        return this.listGame.get(STT);
    }

    //=====================================================TO LIST================================================
    public void removeGameSavedSTTToList(int STT) {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (bs.getStatus()) {
                if (stt == STT) {
                    removeGame(bs);
                    break;
                } else {
                    stt++;
                }
            }
        }
    }

    public void removeGameSaved_TypeOneSTTToList(int STT) {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (bs.getStatus()) {
                if (bs instanceof BattleShips_TypeOne) {
                    if (stt == STT) {
                        removeGame(bs);
                        break;
                    } else stt++;
                }
            }
        }
    }

    public void removeGameSaved_TypeTwoSTTToList(int STT) {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (bs.getStatus()) {
                if (bs instanceof BattleShips_TypeTwo) {
                    if (stt == STT) {
                        removeGame(bs);
                        break;
                    } else stt++;
                }
            }
        }
    }

    public void removeGameSaved_TypeThreeSTTToList(int STT) {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (bs.getStatus()) {
                if (bs instanceof BattleShips_TypeThree) {
                    if (stt == STT) {
                        removeGame(bs);
                        break;
                    } else stt++;
                }
            }
        }
    }

    public void removeGameStillSTTToList(int STT) {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (!bs.getStatus()) {
                if (stt == STT) {
                    removeGame(bs);
                    break;
                } else {
                    stt++;
                }
            }
        }
    }

    public void removeGameStill_TypeOneSTTToList(int STT) {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (!bs.getStatus()) {
                if (bs instanceof BattleShips_TypeOne) {
                    if (stt == STT) {
                        removeGame(bs);
                        break;
                    } else stt++;
                }
            }
        }
    }

    public void removeGameStill_TypeTwoSTTToList(int STT) {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (!bs.getStatus()) {
                if (bs instanceof BattleShips_TypeTwo) {
                    if (stt == STT) {
                        removeGame(bs);
                        break;
                    } else stt++;
                }
            }
        }
    }

    public void removeGameStill_TypeThreeSTTToList(int STT) {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (!bs.getStatus()) {
                if (bs instanceof BattleShips_TypeThree) {
                    if (stt == STT) {
                        removeGame(bs);
                        break;
                    } else stt++;
                }
            }
        }
    }

    public void removeAllGameSavedToList() {
        ArrayList<BattleShips> temp = new ArrayList<>();
        for (BattleShips bs : listGame) if (bs.getStatus()) temp.add(bs);
        removeGame(temp);
    }

    public void removeAllGameSaved_TypeOneToList() {
        ArrayList<BattleShips> temp = new ArrayList<>();
        for (BattleShips bs : listGame) if (bs.getStatus() && bs instanceof BattleShips_TypeOne) temp.add(bs);
        removeGame(temp);
    }

    public void removeAllGameSaved_TypeTwoToList() {
        ArrayList<BattleShips> temp = new ArrayList<>();
        for (BattleShips bs : listGame) if (bs.getStatus() && bs instanceof BattleShips_TypeTwo) temp.add(bs);
        removeGame(temp);
    }

    public void removeAllGameSaved_TypeThreeToList() {
        ArrayList<BattleShips> temp = new ArrayList<>();
        for (BattleShips bs : listGame) if (bs.getStatus() && bs instanceof BattleShips_TypeThree) temp.add(bs);
        removeGame(temp);
    }

    public void removeAllGameStillToList() {
        ArrayList<BattleShips> temp = new ArrayList<>();
        for (BattleShips bs : listGame) if (!bs.getStatus()) temp.add(bs);
        removeGame(temp);
    }

    public void removeAllGameStill_TypeOneToList() {
        ArrayList<BattleShips> temp = new ArrayList<>();
        for (BattleShips bs : listGame) if (!bs.getStatus() && bs instanceof BattleShips_TypeOne) temp.add(bs);
        removeGame(temp);
    }

    public void removeAllGameStill_TypeTwoToList() {
        ArrayList<BattleShips> temp = new ArrayList<>();
        for (BattleShips bs : listGame) if (!bs.getStatus() && bs instanceof BattleShips_TypeTwo) temp.add(bs);
        removeGame(temp);
    }

    public void removeAllGameStill_TypeThreeToList() {
        ArrayList<BattleShips> temp = new ArrayList<>();
        for (BattleShips bs : listGame) if (!bs.getStatus() && bs instanceof BattleShips_TypeThree) temp.add(bs);
        removeGame(temp);
    }

    //=====================================================FROM LIST===============================================
    public void printAllGameFromList() {
        int stt = 1;
        for (BattleShips bs : listGame) {
            System.out.println("\nGame thứ " + stt + ":");
            stt++;
            bs.printOceanMap(bs.getGridPlayer());
        }
    }

    public void printAllGameStillByTypeFromList() {
        if (getNumGameStill_TypeOneFromList() != 0) {
            System.out.println("====> TYPE ONE <====");
            printAllGameStill_TypeOneFromList();
            System.out.println("----> **** <----");
        }
        if (getNumGameStill_TypeTwoFromList() != 0) {
            System.out.println("====> TYPE TWO <====");
            printAllGameStill_TypeTwoFromList();
            System.out.println("----> **** <----");
        }
        if (getNumGameStill_TypeThreeFromList() != 0) {
            System.out.println("====> TYPE THREE <====");
            printAllGameStill_TypeThreeFromList();
            System.out.println("----> **** <----");
        }
    }

    public void printAllGameSavedFromList() {
        int stt = 1;
        System.out.println("====> GAME SAVED <====");
        for (BattleShips bs : listGame) {
            if (bs.getStatus()) { //status = true => done
                System.out.println("\nGame thứ " + stt + ":");
                stt++;
                bs.printOceanMap(bs.getGridPlayer());
                if (bs instanceof BattleShips_TypeOne) System.out.println("====> TYPE ONE <====");
                else if (bs instanceof BattleShips_TypeTwo) System.out.println("====> TYPE TWO <====");
                else if (bs instanceof BattleShips_TypeThree) System.out.println("====> TYPE THREE <====");
                bs.statisticalGame();
            }
        }
    }

    public void printAllGameStillFromList() {
        int stt = 1;
        System.out.println("====> GAME STILL <====");
        for (BattleShips bs : listGame) {
            if (!bs.getStatus()) { //status = true => done
                System.out.println("\nGame thứ " + stt + ":");
                stt++;
                bs.printOceanMap(bs.getGridPlayer());
                if (bs instanceof BattleShips_TypeOne) System.out.println("====> TYPE ONE <====");
                else if (bs instanceof BattleShips_TypeTwo) System.out.println("====> TYPE TWO <====");
                else if (bs instanceof BattleShips_TypeThree) System.out.println("====> TYPE THREE <====");
                bs.statisticalGame();
            }
        }
    }
    public void printAllGameSaved_TypeOneFromList() {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (bs.getStatus() && bs instanceof BattleShips_TypeOne) { //status = true => done
                System.out.println("\nGame thứ " + stt + ":");
                stt++;
                bs.printOceanMap(bs.getGridPlayer());
            }
        }
    }

    public void printAllGameSaved_TypeTwoFromList() {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (bs.getStatus() && bs instanceof BattleShips_TypeTwo) { //status = true => done
                System.out.println("\nGame thứ " + stt + ":");
                stt++;
                bs.printOceanMap(bs.getGridPlayer());
            }
        }
    }

    public void printAllGameSaved_TypeThreeFromList() {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (bs.getStatus() && bs instanceof BattleShips_TypeThree) { //status = true => done
                System.out.println("\nGame thứ " + stt + ":");
                stt++;
                bs.printOceanMap(bs.getGridPlayer());
            }
        }
    }

    public void printAllGameStill_TypeOneFromList() {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if(!bs.getStatus() && bs instanceof BattleShips_TypeOne) {
                System.out.println("\nGame thứ " + stt + ":");
                stt++;
                bs.printOceanMap(bs.getGridPlayer());
                bs.statisticalGame();
            }
        }
    }

    public void printAllGameStill_TypeTwoFromList() {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if(!bs.getStatus() && bs instanceof BattleShips_TypeTwo) {
                System.out.println("\nGame thứ " + stt + ":");
                stt++;
                bs.printOceanMap(bs.getGridPlayer());
                bs.statisticalGame();
            }
        }
    }

    public void printAllGameStill_TypeThreeFromList() {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if(!bs.getStatus() && bs instanceof BattleShips_TypeThree) {
                System.out.println("\nGame thứ " + stt + ":");
                stt++;
                bs.printOceanMap(bs.getGridPlayer());
                bs.statisticalGame();
            }
        }
    }

    public int getNumGameSavedFromList() {
        int temp = 0;
        for (BattleShips bs : listGame) {
            if (bs.getStatus()) {
                temp++;
            }
        }
        return temp;
    }

    public int getNumGameSaved_TypeOneFromList() {
        int temp = 0;
        for (BattleShips bs : listGame) {
            if (bs.getStatus() && bs instanceof BattleShips_TypeOne) {
                temp++;
            }
        }
        return temp;
    }

    public int getNumGameSaved_TypeTwoFromList() {
        int temp = 0;
        for (BattleShips bs : listGame) {
            if (bs.getStatus() && bs instanceof BattleShips_TypeTwo) {
                temp++;
            }
        }
        return temp;
    }

    public int getNumGameSaved_TypeThreeFromList() {
        int temp = 0;
        for (BattleShips bs : listGame) {
            if (bs.getStatus() && bs instanceof BattleShips_TypeThree) {
                temp++;
            }
        }
        return temp;
    }

    public int getNumGameStillFromList() {
        int temp = 0;
        for (BattleShips bs : listGame) {
            if (!bs.getStatus()) {
                temp++;
            }
        }
        return temp;
    }

    public int getNumGameStill_TypeOneFromList() {
        int temp = 0;
        for (BattleShips bs : listGame) {
            if (!bs.getStatus() && bs instanceof BattleShips_TypeOne) {
                temp++;
            }
        }
        return temp;
    }

    public int getNumGameStill_TypeTwoFromList() {
        int temp = 0;
        for (BattleShips bs : listGame) {
            if (!bs.getStatus() && bs instanceof BattleShips_TypeTwo && !(bs instanceof BattleShips_TypeThree)) {
                temp++;
            }
        }
        return temp;
    }

    public int getNumGameStill_TypeThreeFromList() {
        int temp = 0;
        for (BattleShips bs : listGame) {
            if (!bs.getStatus() && bs instanceof BattleShips_TypeThree) {
                temp++;
            }
        }
        return temp;
    }

    public BattleShips_TypeOne getGameSaved_TypeOneSTTFromList(int STT) {
        int stt = 1;
        BattleShips_TypeOne temp = new BattleShips_TypeOne();
        for (BattleShips bs : listGame) {
            if (bs.getStatus()) {
                if (bs instanceof BattleShips_TypeOne) {
                    if (stt == STT) {
                        temp = (BattleShips_TypeOne) bs;
                        break;
                    } else stt++;
                }
            }
        }
        return temp;
    }

    public BattleShips_TypeTwo getGameSaved_TypeTwoSTTFromList(int STT) {
        int stt = 1;
        BattleShips_TypeTwo temp = new BattleShips_TypeTwo();
        for (BattleShips bs : listGame) {
            if (bs.getStatus()) {
                if (bs instanceof BattleShips_TypeTwo) {
                    if (stt == STT) {
                        temp = (BattleShips_TypeTwo) bs;
                        break;
                    } else stt++;
                }
            }
        }
        return temp;
    }

    public BattleShips_TypeThree getGameSaved_TypeThreeSTTFromList(int STT) {
        int stt = 1;
        BattleShips_TypeThree temp = new BattleShips_TypeThree();
        for (BattleShips bs : listGame) {
            if (bs.getStatus()) {
                if (bs instanceof BattleShips_TypeThree) {
                    if (stt == STT) {
                        temp = (BattleShips_TypeThree) bs;
                        break;
                    } else stt++;
                }
            }
        }
        return temp;
    }

    public BattleShips_TypeOne getGameStill_TypeOneSTTFromList(int STT) {
        int stt = 1;
        BattleShips_TypeOne temp = new BattleShips_TypeOne();
        temp.setStatus(false);
        for (BattleShips bs : listGame) {
            if (!bs.getStatus()) {
                if (bs instanceof BattleShips_TypeOne) {
                    if (stt == STT) {
                        temp = (BattleShips_TypeOne) bs;
                        break;
                    } else stt++;
                }
            }
        }
        return temp;
    }

    public BattleShips_TypeTwo getGameStill_TypeTwoSTTFromList(int STT) {
        int stt = 1;
        BattleShips_TypeTwo temp = new BattleShips_TypeTwo();
        temp.setStatus(false);
        for (BattleShips bs : listGame) {
            if (!bs.getStatus()) {
                if (bs instanceof BattleShips_TypeTwo) {
                    if (stt == STT) {
                        temp = (BattleShips_TypeTwo) bs;
                        break;
                    } else stt++;
                }
            }
        }
        return temp;
    }

    public BattleShips_TypeThree getGameStill_TypeThreeSTTFromList(int STT) {
        int stt = 1;
        BattleShips_TypeThree temp = new BattleShips_TypeThree();
        temp.setStatus(false);
        for (BattleShips bs : listGame) {
            if (!bs.getStatus()) {
                if (bs instanceof BattleShips_TypeThree) {
                    if (stt == STT) {
                        temp = (BattleShips_TypeThree) bs;
                        break;
                    } else stt++;
                }
            }
        }
        return temp;
    }
    //======================================================TO FILE==============================================
    //Lưu danh sách vào file
    public void updateListGameToFile(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (BattleShips bs : listGame) {
                objectOut.writeObject(bs);
            }
            objectOut.close();
            fileOut.close();
            //System.out.println("Đã lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Lưu thất bại!");
        }
    }

    //Thêm game vào list game của User ? vào file ?
    public void adddGameToFile(BattleShips bs, String fileName) {
        addGame(bs);
        updateListGameToFile(fileName);
    }

    //Xoa game trong list game cua User ? từ file ? theo STT
    public void removeGameSaved_TypeOneSTTToFile(int STT, String fileName) {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (bs.getStatus() && stt == STT && bs instanceof BattleShips_TypeOne) {
                removeGame(bs);
                break;
            }
            stt++;
        }
        updateListGameToFile(fileName);
    }

    public void removeGameStill_TypeOneByNameIDSTTToFile(int STT, String fileName) {
        int stt = 1;
        for (BattleShips bs : listGame) {
            if (!bs.getStatus() && stt == STT && bs instanceof BattleShips_TypeOne) {
                removeGame(bs);
                break;
            }
            stt++;
        }
        updateListGameToFile(fileName);
    }

    public void removeAllGameSaved_TypeOneToFile(String fileName) {
        for (BattleShips bs : listGame) {
            if (bs.getStatus() && bs instanceof BattleShips_TypeOne) {
                removeGame(bs);
                break;
            }
        }
        updateListGameToFile(fileName);
    }

    public void removeAllGameStill_TypeOneToFile(String fileName) {
        for (BattleShips bs : listGame) {
            if (!bs.getStatus() && bs instanceof BattleShips_TypeOne) {
                removeGame(bs);
                break;
            }
        }
        updateListGameToFile(fileName);
    }

    //=======================================================FROM FILE============================================
    //Cập nhật danh sách từ file
    public void updateListGameFromFile(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            try {
                while (true) {
                    Object obj = objectIn.readObject();
                    if (obj instanceof BattleShips) {
                        BattleShips bs = (BattleShips) obj;
                        this.listGame.add(bs);
                    }
                }
            } catch (EOFException e) {
                //
            }
            objectIn.close();
            fileIn.close();
            //System.out.println("Cập nhật thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Lỗi khi cập nhật ArrayList!");
        }
    }

    public boolean getStatusUpdateInformationListGameFromFile(String fileName) {
        boolean flag = true;
        if (checkNull()) {
            if (fileExist(fileName)) {
                updateListGameFromFile(fileName);
                if (checkNull()) flag = false;
            }
            else {
                flag = false;
            }
        }
        return flag;
    }


}
