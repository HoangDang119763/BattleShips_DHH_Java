import java.util.Scanner;

public abstract class BattleShips_Rocket extends BattleShips {
    private int[] numRocket;
    private int hpPlayer;
    private int hpComputer;
    private ListItemUser listShipUser;
    private int numTypeRocketUser = 0;
    private Rocket[] listRocketUser;

    public BattleShips_Rocket() {
        this.hpPlayer = 0;
        this.hpComputer = 0;
        this.listShipUser = new ListItemUser();
        this.numRocket = new int[2];
    }

    public BattleShips_Rocket(ListItemUser listShipUser) {
        this.hpPlayer = 0;
        this.hpComputer = 0;
        this.numRocket = new int[2];
        this.listShipUser = listShipUser;
        this.numTypeRocketUser = listShipUser.maxNumTypeRocket();
        this.listRocketUser = new Rocket[numTypeRocketUser];
    }

    public BattleShips_Rocket(int numRocket, int hpPlayer, int hpComputer, ListItemUser listShipUser) {
        this.numRocket = new int[2];
        this.hpPlayer = hpPlayer;
        this.hpComputer = hpComputer;
        this.listShipUser = listShipUser;
    }

    public int[] getNumRocket() {
        return numRocket;
    }

    public int getIndexNumRocket(int index) {
        return this.getNumRocket()[index];
    }

    public void setNumRocket(int[] numRocket) {
        this.numRocket = numRocket;
    }

    public void setIndexNumRocket(int index, int numRocket) {
        this.getNumRocket()[index] = numRocket;
    }

    public int getHpPlayer() {
        return hpPlayer;
    }

    public void setHpPlayer(int hpPlayer) {
        this.hpPlayer = hpPlayer;
    }

    public int getHpComputer() {
        return hpComputer;
    }

    public void setHpComputer(int hpComputer) {
        this.hpComputer = hpComputer;
    }

    public ListItemUser getListShipUser() {
        return listShipUser;
    }

    public void setListShipUser(ListItemUser listShipUser) {
        this.listShipUser = listShipUser;
    }

    public int getNumTypeRocketUser() {
        return numTypeRocketUser;
    }

    public void setNumTypeRocketUser(int numTypeRocketUser) {
        this.numTypeRocketUser = numTypeRocketUser;
    }

    public Rocket[] getListRocketUser() {
        return listRocketUser;
    }

    public void setListRocketUser(Rocket[] listRocketUser) {
        this.listRocketUser = listRocketUser;
    }

    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Mời bạn nhập số hàng(Row): ");
            setNumRows(Integer.parseInt(sc.nextLine()));
            System.out.print("Mời bạn nhập số cột(Colum): ");
            setNumColums(Integer.parseInt(sc.nextLine()));
            if (getNumRows() >= 6 && getNumColums() >= 6) break;
            else {
                System.out.println("Kích thước tối hiểu là 6x6!");
            }
        }
    }

    @Override
    public void createOceanMap() {
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColums(); j++) {
                setIndexGridPlayer(i, j, " ");
                setIndexGridComputer(i, j, " ");
            }
        }
        //In ra xem thử map vừa tạo
        printOceanMap(getGridPlayer());
        System.out.println();
    }

    public void addNumRocketByType(Rocket rocket) {
        for (int i = 0; i < numTypeRocketUser; i++) {
            if (listRocketUser[i] == null) {
                listRocketUser[i] = rocket;
                break;
            } else {
                if (listRocketUser[i].getTypeRocket() == rocket.getTypeRocket())
                    listRocketUser[i].setNumRocket(listRocketUser[i].getNumRocket() + rocket.getNumRocket());
            }
        }
    }

    public void addNumRocketByType(Rocket[] rockets) {
        for (int i = 0; i < rockets.length; i++) addNumRocketByType(rockets[i]);
    }


    @Override
    public void deployPlayerShips() {
        Scanner sc = new Scanner(System.in);
        int x, y, choice, direction;
        System.out.println("\nTriển khai thuyền của bạn:");
        //Deploying five ships for player
        for (int i = 1; i <= getPlayerShips(); ) {
            System.out.println("Danh sách các thuyền bạn đang có: ");
            getListShipUser().printAllShipFromList();
            while (true) {
                System.out.print("Chọn thuyền muốn đặt (STT): ");
                choice = sc.nextInt();
                if (choice == 0 || choice > getListShipUser().getListShip().size()) {
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
                }
                if (getListShipUser().getListShip().get(choice - 1).getItemNum() == 0)
                    System.out.println("Bạn không đủ thuyền này!");
                else {
                    int sizeShip = getListShipUser().getListShip().get(choice - 1).getSizeShip();
                    if (sizeShip == 1) {
                        System.out.print("Nhập tọa độ X cho thuyền thứ " + i + " : ");
                        x = sc.nextInt();
                        System.out.print("Nhập tọa độ Y cho thuyền thứ " + i + " : ");
                        y = sc.nextInt();
                        if (checkGrid(x, y) && (getIndexGridPlayer(x, y).equals(" "))) {
                            setIndexGridPlayer(x, y, "P");
                            getListShipUser().getListShip().get(choice - 1).subItemNum(1);
                            setHpPlayer(getHpPlayer() + sizeShip);
                            if (getListShipUser().getListShip().get(choice - 1) instanceof Item_Ship_Rocket) {
                                setIndexNumRocket(0, getIndexNumRocket(0) + ((Item_Ship_Rocket) getListShipUser().getListShip().get(choice - 1)).numAllRocket());
                                addNumRocketByType(((Item_Ship_Rocket) getListShipUser().getListShip().get(choice - 1)).getListRocket());
                            }
                            i++;
                            printOceanMap(getGridPlayer());
                        } else if (checkGrid(x, y) && getIndexGridPlayer(x, y).equals("P"))
                            System.out.println("Bạn không thể đặt nhiều hơn một thuyền tại cùng một vị trí!");
                        else if (!checkGrid(x, y))
                            System.out.println("Bạn không thể đặt ngoài kích thước map đã quy định: " + getNumRows() + " và " + getNumColums());
                    } else {
                        while (true) {
                            System.out.print("Nhập tọa độ X cho thuyền thứ " + i + " : ");
                            x = sc.nextInt();
                            System.out.print("Nhập tọa độ Y cho thuyền thứ " + i + " : ");
                            y = sc.nextInt();
                            boolean flag = true;
                            if (checkGrid(x, y)) {
                                while (flag) {
                                    System.out.println("0. Ngang trái|| 1. Ngang phải|| 2. Lên trên|| 3. Xuống dưới|| 4. Lên trên-Trái|| 5. Lên trên-Phải|| 6. Xuống dưới-Trái|| 7. Xuống dưới-Phải");
                                    System.out.print("Nhập chiều cho thuyền thứ " + i + " : ");
                                    direction = sc.nextInt();
                                    if (direction >= 0 && direction <= 7) {
                                        switch (direction) {
                                            case 0 -> { //Ngang trái   vd 2 2 => 2 1 . kích thước 2/ 3 thì 2 2 => 2 0
                                                if ((y - sizeShip + 1) >= 0) {
                                                    if (!checkSameShip(x, y, x, (y - sizeShip + 1), 1, "P")) {
                                                        deployShipAuto(x, y, x, (y - sizeShip + 1), 2, "P");
                                                        flag = false;
                                                    } else System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                } else
                                                    System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                            }
                                            case 1 -> { //Ngang phải vd 2 2 => 2 3 . kích thước 2/ 3 thì 2 2 => 2 4
                                                if ((y + sizeShip - 1) < getNumColums()) {
                                                    if (!checkSameShip(x, y, x, (y + sizeShip - 1), 1, "P")) {
                                                        deployShipAuto(x, y, x, (y + sizeShip - 1), 1, "P");
                                                        flag = false;
                                                    } else System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                } else
                                                    System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                            }
                                            case 2 -> { //Lên trên vd 2 2 => 1 2 . kích thước 2/ 3 thì 2 2 => 0 2
                                                if ((x - sizeShip + 1) >= 0) {
                                                    if (!checkSameShip(x, y, (x - sizeShip + 1), y, 1, "P")) {
                                                        deployShipAuto(x, y, (x - sizeShip + 1), y, 1, "P");
                                                        flag = false;
                                                    } else System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                } else
                                                    System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                            }
                                            case 3 -> { //Xuống dưới vd 2 2 => 3 2 . kích thước 2/ 3 thì 2 2 => 4 2
                                                if ((x + sizeShip - 1) < getNumRows()) {
                                                    if (!checkSameShip(x, y, (x + sizeShip - 1), y, 1, "P")) {
                                                        deployShipAuto(x, y, (x + sizeShip - 1), y, 1, "P");
                                                        flag = false;
                                                    } else System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                } else
                                                    System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                            }
                                            case 4 -> { //Lên trên bên trái vd 2 2 => 1 1 . kích thước 2/ 3 thì 2 2 => 0 0
                                                if (((x - sizeShip + 1) >= 0) && ((y - sizeShip + 1) >= 0)) {
                                                    if (!checkSameShip(x, y, (x - sizeShip + 1), (y - sizeShip + 1), 1, "P")) {
                                                        deployShipAuto(x, y, (x - sizeShip + 1), (y - sizeShip + 1), 1, "P");
                                                        flag = false;
                                                    } else System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                } else
                                                    System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                            }
                                            case 5 -> { //Lên trên bên phải vd 2 2 => 1 3 . kích thước 2/ 3 thì 2 2 => 0 4
                                                if (((x - sizeShip + 1) >= 0) && ((y + sizeShip - 1) < getNumColums())) {
                                                    if (!checkSameShip(x, y, (x - sizeShip + 1), (y + sizeShip - 1), 1, "P")) {
                                                        deployShipAuto(x, y, (x - sizeShip + 1), (y + sizeShip - 1), 1, "P");
                                                        flag = false;
                                                    } else System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                } else
                                                    System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                            }
                                            case 6 -> { //Xuống dưới bên trái vd 2 2 => 3 1 . kích thước 2/ 3 thì 2 2 => 4 0     1 4 => 3 2. kích thước 3
                                                if (((x + sizeShip - 1) < getNumRows()) && ((y - sizeShip + 1) >= 0)) {
                                                    if (!checkSameShip(x, y, (x + sizeShip - 1), (y - sizeShip + 1), 1, "P")) {
                                                        deployShipAuto(x, y, (x + sizeShip - 1), (y - sizeShip + 1), 1, "P");
                                                        flag = false;
                                                    } else System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                } else
                                                    System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                            }
                                            case 7 -> { //Xuống dưới bên phải vd 2 2 => 3 3 . kích thước 2/ 3 thì 2 2 => 4 4
                                                if (((x + sizeShip - 1) < getNumRows()) && ((y + sizeShip - 1) < getNumColums())) {
                                                    if (!checkSameShip(x, y, (x + sizeShip - 1), (y + sizeShip - 1), 1, "P")) {
                                                        deployShipAuto(x, y, (x + sizeShip - 1), (y + sizeShip - 1), 1, "P");
                                                        flag = false;
                                                    } else System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                } else
                                                    System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                            }
                                        }
                                    } else System.out.println("Chiều không hợp lệ, mời bạn nhập lại!");
                                }
                            } else if (!checkGrid(x, y)) {
                                System.out.println("Bạn không thể đặt ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                            }
                            if (!flag) break;
                        }
                        getListShipUser().getListShip().get(choice - 1).subItemNum(1);
                        setHpPlayer(getHpPlayer() + sizeShip);
                        if (getListShipUser().getListShip().get(choice - 1) instanceof Item_Ship_Rocket) {
                            setIndexNumRocket(0, getIndexNumRocket(0) + ((Item_Ship_Rocket) getListShipUser().getListShip().get(choice - 1)).numAllRocket());
                            addNumRocketByType(((Item_Ship_Rocket) getListShipUser().getListShip().get(choice - 1)).getListRocket());
                        }
                        i++;
                        printOceanMap(getGridPlayer());
                    }
                    break;
                }
            }
        }
        sortListRocket();
        getListShipUser().updateInformationItem_ShipToList(getListShipUser().getListShip());
        setIndexNumRocket(1, getIndexNumRocket(0));
    }

    public void sortListRocket() {
        if (getNumTypeRocketUser() > 1) {
            for (int i = 0; i < getNumTypeRocketUser() - 1; i++)
                if (listRocketUser[i] == null) break;
                else {
                    for (int j = i + 1; j < getNumTypeRocketUser(); j++)
                        if (listRocketUser[j] == null) break;
                        else {
                            if (listRocketUser[i].getTypeRocket() > listRocketUser[j].getTypeRocket()) {
                                Rocket temp = listRocketUser[i];
                                listRocketUser[i] = listRocketUser[j];
                                listRocketUser[j] = temp;
                            }
                        }
                }
        }
    }

    public void showListRocketUser() {
        if (numTypeRocketUser == 1)
            System.out.println("Số Rocket (Loại " + listRocketUser[0].getTypeRocket() + "): " + listRocketUser[0].getNumRocket());
        else {
            for (int i = 0; i < getNumTypeRocketUser(); i++) {
                if (listRocketUser[i] != null) System.out.print("| Số Rocket (Loại " + listRocketUser[i].getTypeRocket() + "): " + listRocketUser[i].getNumRocket());
                else break;
            }
            System.out.println("");
        }
    }

    @Override
    public void gameOver() {
        System.out.println("====> Kết quả <====");
        System.out.println("Số HP của bạn: " + getHpPlayer() + " | Số HP của Computer: " + getHpComputer());
        if (getHpPlayer() > 0 && getHpComputer() <= 0) {
            super.setComputerShips(0);
            System.out.println("Yeah! Bạn đã THẮNG Game Battle Ships!!! :>");
        } else {
            super.setPlayerShips(0);
            System.out.println("Chia buồn! Bạn đã THUA Game Battle Ships!!! :<");
        }
        //In map tổng quát
        printOceanMap(getGridPlayer());
    }

    @Override
    public void startBattle() {
        if (getStatus()) {
            inputData();
            createOceanMap();
            deployPlayerShips();
            deployComputerShips();
            //test();
        } else {
            setStatus(true);
            printOceanMap(getGridPlayer());
            statisticalGame();
        }

        do {
            playerTurn();
            if (!getStatus()) break;
            //Tránh trường hợp người chơi tiêu diệt hết sau đó máy cũng tiêu diệt hết player dẫn đến sai
            if (getHpComputer() > 0) {
                computerTurn();
                System.out.println();
                System.out.println("Số HP của bạn: " + getHpPlayer() + " | Số HP của Computer: " + getHpComputer());
                System.out.println();
            }
        } while (getHpPlayer() != 0 && getHpComputer() != 0 && getStatus());
        //B5: Kết thúc
        if (getStatus()) {
            gameOver();
        }
    }

    @Override
    public void statisticalGame() {
        String temp1 = "", temp2 = "";
        System.out.println("Số HP của bạn: " + getHpPlayer() + " | Số HP của Computer: " + getHpComputer());
        if (getStatus()) {
            temp2 = "DONE";
            if (getComputerShips() == 0 || getHpComputer() == 0) temp1 = "WIN";
            else temp1 = "LOSE";
            System.out.println("Trạng thái: " + temp2 + "| " + temp1);
        } else {
            temp2 = "STILL";
            System.out.println("Trạng thái: " + temp2);
        }
        System.out.println("Số rocket: " + getIndexNumRocket(0));
    }

    public boolean checkSizeShip(int x1, int y1, int x2, int y2, int sizeShip) {
        boolean flag = false;
        if ((x1 != x2) && (y1 != y2)) {
            if ((Math.abs(x2 - x1) == Math.abs(y2 - y1)) && (sizeShip == (Math.abs(x2 - x1) + 1))) flag = true;
        } else if (x1 == x2 && (sizeShip == (Math.abs(y2 - y1) + 1))) {
            flag = true;
        } else if (y1 == y2 && (sizeShip == (Math.abs(x2 - x1) + 1))) flag = true;
        return flag;
    }

    public boolean checkShipSunkByRocketPlayer(int x, int y) {
        boolean flag = false;
        if (checkGrid(x, y)) {
            if (getIndexGridComputer(x, y).equals("C")) {
                setIndexGridPlayer(x, y, "D");
                setIndexGridComputer(x, y, "d");
                flag = true;
            }
        }
        return flag;
    }

    public boolean checkShipSunkByRocketComputer(int x, int y) {
        boolean flag = false;
        if (checkGrid(x, y)) {
            if (getIndexGridPlayer(x, y).equals("P")) {
                setIndexGridPlayer(x, y, "d");
                setIndexGridComputer(x, y, "D");
                flag = true;
            }
        }
        return flag;
    }

    public int numShipSunkByRocket(int x, int y, int type, int typeRocket) {
        int tmp = 0;
        if (type == 1) {
            if (typeRocket == 0 || typeRocket == 2) {
                if (checkShipSunkByRocketPlayer(x - 1, y)) tmp++;
                if (checkShipSunkByRocketPlayer(x + 1, y)) tmp++;
                if (checkShipSunkByRocketPlayer(x, y - 1)) tmp++;
                if (checkShipSunkByRocketPlayer(x, y + 1)) tmp++;
            }
            if (typeRocket == 1 || typeRocket == 2) {
                if (checkShipSunkByRocketPlayer(x - 1, y - 1)) tmp++;
                if (checkShipSunkByRocketPlayer(x - 1, y + 1)) tmp++;
                if (checkShipSunkByRocketPlayer(x + 1, y - 1)) tmp++;
                if (checkShipSunkByRocketPlayer(x + 1, y + 1)) tmp++;
            }
            if (typeRocket == 3 || typeRocket == 5) {
                if (checkShipSunkByRocketPlayer(x - 2, y)) tmp++;
                if (checkShipSunkByRocketPlayer(x + 2, y)) tmp++;
                if (checkShipSunkByRocketPlayer(x, y - 2)) tmp++;
                if (checkShipSunkByRocketPlayer(x, y + 2)) tmp++;
            }
            if (typeRocket == 4 || typeRocket == 5) {
                if (checkShipSunkByRocketPlayer(x - 2, y - 2)) tmp++;
                if (checkShipSunkByRocketPlayer(x - 2, y + 2)) tmp++;
                if (checkShipSunkByRocketPlayer(x + 2, y - 2)) tmp++;
                if (checkShipSunkByRocketPlayer(x + 2, y + 2)) tmp++;
            }
        } else if (type == 2) {
            if (typeRocket == 0 || typeRocket == 2) {
                if (checkShipSunkByRocketComputer(x - 1, y)) tmp++;
                if (checkShipSunkByRocketComputer(x + 1, y)) tmp++;
                if (checkShipSunkByRocketComputer(x, y - 1)) tmp++;
                if (checkShipSunkByRocketComputer(x, y + 1)) tmp++;
            }
            if (typeRocket == 1 || typeRocket == 2) {
                if (checkShipSunkByRocketComputer(x - 1, y - 1)) tmp++;
                if (checkShipSunkByRocketComputer(x - 1, y + 1)) tmp++;
                if (checkShipSunkByRocketComputer(x + 1, y - 1)) tmp++;
                if (checkShipSunkByRocketComputer(x + 1, y + 1)) tmp++;
            }
            if (typeRocket == 3 || typeRocket == 5) {
                if (checkShipSunkByRocketComputer(x - 2, y)) tmp++;
                if (checkShipSunkByRocketComputer(x + 2, y)) tmp++;
                if (checkShipSunkByRocketComputer(x, y - 2)) tmp++;
                if (checkShipSunkByRocketComputer(x, y + 2)) tmp++;
            }
            if (typeRocket == 4 || typeRocket == 5) {
                if (checkShipSunkByRocketComputer(x - 2, y - 2)) tmp++;
                if (checkShipSunkByRocketComputer(x - 2, y + 2)) tmp++;
                if (checkShipSunkByRocketComputer(x + 2, y - 2)) tmp++;
                if (checkShipSunkByRocketComputer(x + 2, y + 2)) tmp++;
            }
        }
        return tmp;
    }

    /*public int numShipSunkByRocket(int x, int y, int type, String temp, int typeRocket) {
        int tmp = 0;
        if (type == 1) {
            if (checkGrid(x - 1, y)) {
                if (getIndexGridComputer(x - 1, y).equals(temp)) {
                    setIndexGridPlayer(x - 1, y, "D");
                    setIndexGridComputer(x - 1, y, "d");
                    tmp++;
                }
            }
            if (checkGrid(x + 1, y)) {
                if (getIndexGridComputer(x + 1, y).equals(temp)) {
                    setIndexGridPlayer(x + 1, y, "D");
                    setIndexGridComputer(x + 1, y, "d");
                    tmp++;
                }
            }
            if (checkGrid(x, y - 1)) {
                if (getIndexGridComputer(x, y - 1).equals(temp)) {
                    setIndexGridPlayer(x, y - 1, "D");
                    setIndexGridComputer(x, y - 1, "d");
                    tmp++;
                }
            }
            if (checkGrid(x, y + 1)) {
                if (getIndexGridComputer(x, y + 1).equals(temp)) {
                    setIndexGridPlayer(x, y + 1, "D");
                    setIndexGridComputer(x, y + 1, "d");
                    tmp++;
                }
            }
            if (typeRocket == 1) {
                if (checkGrid(x - 1, y - 1)) {
                    if (getIndexGridComputer(x - 1, y - 1).equals(temp)) {
                        setIndexGridPlayer(x - 1, y - 1, "D");
                        setIndexGridComputer(x - 1, y - 1, "d");
                        tmp++;
                    }
                }
                if (checkGrid(x - 1, y + 1)) {
                    if (getIndexGridComputer(x - 1, y + 1).equals(temp)) {
                        setIndexGridPlayer(x - 1, y + 1, "D");
                        setIndexGridComputer(x - 1, y + 1, "d");
                        tmp++;
                    }
                }
                if (checkGrid(x + 1, y - 1)) {
                    if (getIndexGridComputer(x + 1, y - 1).equals(temp)) {
                        setIndexGridPlayer(x + 1, y - 1, "D");
                        setIndexGridComputer(x + 1, y - 1, "d");
                        tmp++;
                    }
                }
                if (checkGrid(x + 1, y + 1)) {
                    if (getIndexGridComputer(x + 1, y + 1).equals(temp)) {
                        setIndexGridPlayer(x + 1, y + 1, "D");
                        setIndexGridComputer(x + 1, y + 1, "d");
                        tmp++;
                    }
                }
            }
        } else if (type == 2) {
            if (checkGrid(x - 1, y)) {
                if (getIndexGridPlayer(x - 1, y).equals(temp)) {
                    setIndexGridPlayer(x - 1, y, "d");
                    setIndexGridComputer(x - 1, y, "D");
                    tmp++;
                }
            }
            if (checkGrid(x + 1, y)) {
                if (getIndexGridPlayer(x + 1, y).equals(temp)) {
                    setIndexGridPlayer(x + 1, y, "d");
                    setIndexGridComputer(x + 1, y, "D");
                    tmp++;
                }
            }
            if (checkGrid(x, y - 1)) {
                if (getIndexGridPlayer(x, y - 1).equals(temp)) {
                    setIndexGridPlayer(x, y - 1, "d");
                    setIndexGridComputer(x, y - 1, "D");
                    tmp++;
                }
            }
            if (checkGrid(x, y + 1)) {
                if (getIndexGridPlayer(x, y + 1).equals(temp)) {
                    setIndexGridPlayer(x, y + 1, "d");
                    setIndexGridComputer(x, y + 1, "D");
                    tmp++;
                }
            }
            if (typeRocket == 1) {
                if (checkGrid(x - 1, y - 1)) {
                    if (getIndexGridPlayer(x - 1, y - 1).equals(temp)) {
                        setIndexGridPlayer(x - 1, y - 1, "d");
                        setIndexGridComputer(x - 1, y - 1, "D");
                        tmp++;
                    }
                }
                if (checkGrid(x - 1, y + 1)) {
                    if (getIndexGridPlayer(x - 1, y + 1).equals(temp)) {
                        setIndexGridPlayer(x - 1, y + 1, "d");
                        setIndexGridComputer(x - 1, y + 1, "D");
                        tmp++;
                    }
                }
                if (checkGrid(x + 1, y - 1)) {
                    if (getIndexGridPlayer(x + 1, y - 1).equals(temp)) {
                        setIndexGridPlayer(x + 1, y - 1, "d");
                        setIndexGridComputer(x + 1, y - 1, "D");
                        tmp++;
                    }
                }
                if (checkGrid(x + 1, y + 1)) {
                    if (getIndexGridPlayer(x + 1, y + 1).equals(temp)) {
                        setIndexGridPlayer(x + 1, y + 1, "d");
                        setIndexGridComputer(x + 1, y + 1, "D");
                        tmp++;
                    }
                }
            }
        }
        return tmp;
    }*/

    public boolean checkShipSelfSunkByRocketPlayer(int x, int y) {
        boolean flag = false;
        if (checkGrid(x, y)) {
            if (getIndexGridPlayer(x, y).equals("P")) {
                setIndexGridPlayer(x, y, "x");
                flag = true;
            }
        }
        return flag;
    }

    public boolean checkShipSelfSunkByRocketComputer(int x, int y) {
        boolean flag = false;
        if (checkGrid(x, y)) {
            if (getIndexGridComputer(x, y).equals("C")) {
                setIndexGridComputer(x, y, "x");
                flag = true;
            }
        }
        return flag;
    }

    public int numShipSelfSunkByRocket(int x, int y, int type, int typeRocket) {
        int tmp = 0;
        if (type == 1) {
            if (typeRocket == 0 || typeRocket == 2) {
                if (checkShipSelfSunkByRocketPlayer(x - 1, y)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x + 1, y)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x, y - 1)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x, y + 1)) tmp++;
            }
            if (typeRocket == 1 || typeRocket == 2) {
                if (checkShipSelfSunkByRocketPlayer(x - 1, y - 1)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x - 1, y + 1)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x + 1, y - 1)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x + 1, y + 1)) tmp++;
            }
            if (typeRocket == 3 || typeRocket == 5) {
                if (checkShipSelfSunkByRocketPlayer(x - 2, y)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x + 2, y)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x, y - 2)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x, y + 2)) tmp++;
            }
            if (typeRocket == 4 || typeRocket == 5) {
                if (checkShipSelfSunkByRocketPlayer(x - 2, y - 2)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x - 2, y + 2)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x + 2, y - 2)) tmp++;
                if (checkShipSelfSunkByRocketPlayer(x + 2, y + 2)) tmp++;
            }
        } else if (type == 2) {
            if (typeRocket == 0 || typeRocket == 2) {
                if (checkShipSelfSunkByRocketComputer(x - 1, y)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x + 1, y)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x, y - 1)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x, y + 1)) tmp++;
            }
            if (typeRocket == 1 || typeRocket == 2) {
                if (checkShipSelfSunkByRocketComputer(x - 1, y - 1)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x - 1, y + 1)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x + 1, y - 1)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x + 1, y + 1)) tmp++;
            }
            if (typeRocket == 3 || typeRocket == 5) {
                if (checkShipSelfSunkByRocketComputer(x - 2, y)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x + 2, y)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x, y - 2)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x, y + 2)) tmp++;
            }
            if (typeRocket == 4 || typeRocket == 5) {
                if (checkShipSelfSunkByRocketComputer(x - 2, y - 2)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x - 2, y + 2)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x + 2, y - 2)) tmp++;
                if (checkShipSelfSunkByRocketComputer(x + 2, y + 2)) tmp++;
            }
        }
        return tmp;
    }

    /*public int numShipSelfSunkByRocket(int x, int y, int type, String temp, int typeRocket) {
        int tmp = 0;
        if (type == 1) {
            if (checkGrid(x - 1, y)) {
                if (getIndexGridPlayer(x - 1, y).equals(temp)) {
                    setIndexGridPlayer(x - 1, y, "x");
                    tmp++;
                }
            }
            if (checkGrid(x + 1, y)) {
                if (getIndexGridPlayer(x + 1, y).equals(temp)) {
                    setIndexGridPlayer(x + 1, y, "x");
                    tmp++;
                }
            }
            if (checkGrid(x, y - 1)) {
                if (getIndexGridPlayer(x, y - 1).equals(temp)) {
                    setIndexGridPlayer(x, y - 1, "x");
                    tmp++;
                }
            }
            if (checkGrid(x, y + 1)) {
                if (getIndexGridPlayer(x, y + 1).equals(temp)) {
                    setIndexGridPlayer(x, y + 1, "x");
                    tmp++;
                }
            }
            if (typeRocket == 1) {
                if (checkGrid(x - 1, y - 1)) {
                    if (getIndexGridPlayer(x - 1, y - 1).equals(temp)) {
                        setIndexGridPlayer(x - 1, y - 1, "x");
                        tmp++;
                    }
                }
                if (checkGrid(x - 1, y + 1)) {
                    if (getIndexGridPlayer(x - 1, y + 1).equals(temp)) {
                        setIndexGridPlayer(x - 1, y + 1, "x");
                        tmp++;
                    }
                }
                if (checkGrid(x + 1, y - 1)) {
                    if (getIndexGridPlayer(x + 1, y - 1).equals(temp)) {
                        setIndexGridPlayer(x + 1, y - 1, "x");
                        tmp++;
                    }
                }
                if (checkGrid(x + 1, y + 1)) {
                    if (getIndexGridPlayer(x + 1, y + 1).equals(temp)) {
                        setIndexGridPlayer(x + 1, y + 1, "x");
                        tmp++;
                    }
                }
            }
        } else if (type == 2) {
            if (checkGrid(x - 1, y)) {
                if (getIndexGridComputer(x - 1, y).equals(temp)) {
                    setIndexGridComputer(x - 1, y, "x");
                    tmp++;
                }
            }
            if (checkGrid(x + 1, y)) {
                if (getIndexGridComputer(x + 1, y).equals(temp)) {
                    setIndexGridComputer(x + 1, y, "x");
                    tmp++;
                }
            }
            if (checkGrid(x, y - 1)) {
                if (getIndexGridComputer(x, y - 1).equals(temp)) {
                    setIndexGridComputer(x, y - 1, "x");
                    tmp++;
                }
            }
            if (checkGrid(x, y + 1)) {
                if (getIndexGridComputer(x, y + 1).equals(temp)) {
                    setIndexGridComputer(x, y + 1, "x");
                    tmp++;
                }
            }
            if (typeRocket == 1) {
                if (checkGrid(x - 1, y - 1)) {
                    if (getIndexGridComputer(x - 1, y - 1).equals(temp)) {
                        setIndexGridComputer(x - 1, y - 1, "x");
                        tmp++;
                    }
                }
                if (checkGrid(x - 1, y + 1)) {
                    if (getIndexGridComputer(x - 1, y + 1).equals(temp)) {
                        setIndexGridComputer(x - 1, y + 1, "x");
                        tmp++;
                    }
                }
                if (checkGrid(x + 1, y - 1)) {
                    if (getIndexGridComputer(x + 1, y - 1).equals(temp)) {
                        setIndexGridComputer(x + 1, y - 1, "x");
                        tmp++;
                    }
                }
                if (checkGrid(x + 1, y + 1)) {
                    if (getIndexGridComputer(x + 1, y + 1).equals(temp)) {
                        setIndexGridComputer(x + 1, y + 1, "x");
                        tmp++;
                    }
                }
            }
        }
        return tmp;
    }*/

    public boolean checkShipMissSunkByRocketPlayer(int x, int y) {
        boolean flag = false;
        if (checkGrid(x, y)) {
            if (getIndexGridComputer(x, y).equals(" ")) {
                setIndexGridPlayer(x, y, "-");
                flag = true;
            }
        }
        return flag;
    }

    public boolean checkShipMissSunkByRocketComputer(int x, int y) {
        boolean flag = false;
        if (checkGrid(x, y)) {
            if (getIndexGridPlayer(x, y).equals(" ")) {
                setIndexGridComputer(x, y, "-");
                flag = true;
            }
        }
        return flag;
    }

    public int numShipMissSunkByRocket(int x, int y, int type, int typeRocket) {
        int tmp = 0;
        if (type == 1) {
            if (typeRocket == 0 || typeRocket == 2) {
                if (checkShipMissSunkByRocketPlayer(x - 1, y)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x + 1, y)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x, y - 1)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x, y + 1)) tmp++;
            }
            if (typeRocket == 1 || typeRocket == 2) {
                if (checkShipMissSunkByRocketPlayer(x - 1, y - 1)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x - 1, y + 1)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x + 1, y - 1)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x + 1, y + 1)) tmp++;
            }
            if (typeRocket == 3 || typeRocket == 5) {
                if (checkShipMissSunkByRocketPlayer(x - 2, y)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x + 2, y)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x, y - 2)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x, y + 2)) tmp++;
            }
            if (typeRocket == 4 || typeRocket == 5) {
                if (checkShipMissSunkByRocketPlayer(x - 2, y - 2)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x - 2, y + 2)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x + 2, y - 2)) tmp++;
                if (checkShipMissSunkByRocketPlayer(x + 2, y + 2)) tmp++;
            }
        } else if (type == 2) {
            if (typeRocket == 0 || typeRocket == 2) {
                if (checkShipMissSunkByRocketComputer(x - 1, y)) tmp++;
                if (checkShipMissSunkByRocketComputer(x + 1, y)) tmp++;
                if (checkShipMissSunkByRocketComputer(x, y - 1)) tmp++;
                if (checkShipMissSunkByRocketComputer(x, y + 1)) tmp++;
            }
            if (typeRocket == 1 || typeRocket == 2) {
                if (checkShipMissSunkByRocketComputer(x - 1, y - 1)) tmp++;
                if (checkShipMissSunkByRocketComputer(x - 1, y + 1)) tmp++;
                if (checkShipMissSunkByRocketComputer(x + 1, y - 1)) tmp++;
                if (checkShipMissSunkByRocketComputer(x + 1, y + 1)) tmp++;
            }
            if (typeRocket == 3 || typeRocket == 5) {
                if (checkShipMissSunkByRocketComputer(x - 2, y)) tmp++;
                if (checkShipMissSunkByRocketComputer(x + 2, y)) tmp++;
                if (checkShipMissSunkByRocketComputer(x, y - 2)) tmp++;
                if (checkShipMissSunkByRocketComputer(x, y + 2)) tmp++;
            }
            if (typeRocket == 4 || typeRocket == 5) {
                if (checkShipMissSunkByRocketComputer(x - 2, y - 2)) tmp++;
                if (checkShipMissSunkByRocketComputer(x - 2, y + 2)) tmp++;
                if (checkShipMissSunkByRocketComputer(x + 2, y - 2)) tmp++;
                if (checkShipMissSunkByRocketComputer(x + 2, y + 2)) tmp++;
            }
        }
        return tmp;
    }

    public boolean checkSameShip(int x, int y, int x1, int y1, int type, String temp) {
        boolean flag = false;
        if (type == 1) {
            if (getIndexGridPlayer(x, y).equals(temp) || getIndexGridPlayer(x1, y1).equals(temp)) flag = true;
            else {
                if ((x != x1) && (y != y1)) {
                    int temp1 = x, temp2 = y;
                    if (temp1 < x1) {
                        if (temp2 < y1) {
                            while (temp1 != x1 && temp2 != y1) {
                                if (getIndexGridPlayer(temp1, temp2).equals(temp)) {
                                    flag = true;
                                    break;
                                }
                                temp1++;
                                temp2++;
                            }
                        } else {
                            while (temp1 != x1 && temp2 != y1) {
                                if (getIndexGridPlayer(temp1, temp2).equals(temp)) {
                                    flag = true;
                                    break;
                                }
                                temp1++;
                                temp2--;
                            }
                        }
                    } else {
                        if (temp2 < y1) {
                            while (temp1 != x1 && temp2 != y1) {
                                if (getIndexGridPlayer(temp1, temp2).equals(temp)) {
                                    flag = true;
                                    break;
                                }
                                temp1--;
                                temp2++;
                            }
                        } else {
                            while (temp1 != x1 && temp2 != y1) {
                                if (getIndexGridPlayer(temp1, temp2).equals(temp)) {
                                    flag = true;
                                    break;
                                }
                                temp1--;
                                temp2--;
                            }
                        }
                    }
                } else if (x == x1) {
                    if (y < y1) {
                        for (int index = y; index <= y1; index++) {
                            if (getIndexGridPlayer(x, index).equals(temp)) {
                                flag = true;
                                break;
                            }
                        }
                    } else {
                        for (int index = y1; index <= y; index++) {
                            if (getIndexGridPlayer(x, index).equals(temp)) {
                                flag = true;
                                break;
                            }
                        }
                    }
                } else {
                    if (x < x1) {
                        for (int index = x; index <= x1; index++) {
                            if (getIndexGridPlayer(index, y).equals(temp)) {
                                flag = true;
                                break;
                            }
                        }
                    } else {
                        for (int index = x1; index <= x; index++) {
                            if (getIndexGridPlayer(index, y).equals(temp)) {
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
        } else if (type == 2) {
            if (getIndexGridComputer(x, y).equals(temp) || getIndexGridComputer(x1, y1).equals(temp)) flag = true;
            else {
                if ((x != x1) && (y != y1)) {
                    int temp1 = x, temp2 = y;
                    if (temp1 < x1) {
                        if (temp2 < y1) {
                            while (temp1 != x1 && temp2 != y1) {
                                if (getIndexGridComputer(temp1, temp2).equals(temp)) {
                                    flag = true;
                                    break;
                                }
                                temp1++;
                                temp2++;
                            }
                        } else {
                            while (temp1 != x1 && temp2 != y1) {
                                if (getIndexGridComputer(temp1, temp2).equals(temp)) {
                                    flag = true;
                                    break;
                                }
                                temp1++;
                                temp2--;
                            }
                        }
                    } else {
                        if (temp2 < y1) {
                            while (temp1 != x1 && temp2 != y1) {
                                if (getIndexGridComputer(temp1, temp2).equals(temp)) {
                                    flag = true;
                                    break;
                                }
                                temp1--;
                                temp2++;
                            }
                        } else {
                            while (temp1 != x1 && temp2 != y1) {
                                if (getIndexGridComputer(temp1, temp2).equals(temp)) {
                                    flag = true;
                                    break;
                                }
                                temp1--;
                                temp2--;
                            }
                        }
                    }
                } else if (x == x1) {
                    if (y < y1) {
                        for (int index = y; index <= y1; index++) {
                            if (getIndexGridComputer(x, index).equals(temp)) {
                                flag = true;
                                break;
                            }
                        }
                    } else {
                        for (int index = y1; index <= y; index++) {
                            if (getIndexGridComputer(x, index).equals(temp)) {
                                flag = true;
                                break;
                            }
                        }
                    }
                } else {
                    if (x < x1) {
                        for (int index = x; index <= x1; index++) {
                            if (getIndexGridComputer(index, y).equals(temp)) {
                                flag = true;
                                break;
                            }
                        }
                    } else {
                        for (int index = x1; index <= x; index++) {
                            if (getIndexGridComputer(index, y).equals(temp)) {
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    public void deployShipAuto(int x, int y, int x1, int y1, int type, String temp) {
        if (type == 1) {
            if ((x != x1) && (y != y1)) {
                int temp1 = x, temp2 = y;
                if (temp1 < x1) {
                    if (temp2 < y1) {
                        while (temp1 != x1 && temp2 != y1) {
                            setIndexGridPlayer(temp1, temp2, temp);
                            temp1++;
                            temp2++;
                        }
                    } else {
                        while (temp1 != x1 && temp2 != y1) {
                            setIndexGridPlayer(temp1, temp2, temp);
                            temp1++;
                            temp2--;
                        }
                    }
                } else {
                    if (temp2 < y1) {
                        while (temp1 != x1 && temp2 != y1) {
                            setIndexGridPlayer(temp1, temp2, temp);
                            temp1--;
                            temp2++;
                        }
                    } else {
                        while (temp1 != x1 && temp2 != y1) {
                            setIndexGridPlayer(temp1, temp2, temp);
                            temp1--;
                            temp2--;
                        }
                    }
                }
                setIndexGridPlayer(x1, y1, temp);
            } else if (x == x1 && y != y1) {
                if (y < y1) for (int index = y; index <= y1; index++) setIndexGridPlayer(x, index, temp);
                else for (int index = y1; index <= y; index++) setIndexGridPlayer(x, index, temp);
            } else if (x != x1) {
                if (x < x1) for (int index = x; index <= x1; index++) setIndexGridPlayer(index, y, temp);
                else for (int index = x1; index <= x; index++) setIndexGridPlayer(index, y, temp);
            }
        } else if (type == 2) {
            if ((x != x1) && (y != y1)) {
                int temp1 = x, temp2 = y;
                if (temp1 < x1) {
                    if (temp2 < y1) { //Xuống dưới bên phải
                        while (temp1 != x1 && temp2 != y1) {
                            setIndexGridComputer(temp1, temp2, temp);
                            temp1++;
                            temp2++;
                        }
                    } else { //Xuống dưới bên trái  4 2 6 0 => 4 2|5 1|6 0
                        while (temp1 != x1 && temp2 != y1) {
                            setIndexGridComputer(temp1, temp2, temp);
                            temp1++;
                            temp2--;
                        }
                    }
                } else {
                    if (temp2 < y1) { //Lên trên bên phải 2 1|1 2
                        while (temp1 != x1 && temp2 != y1) {
                            setIndexGridComputer(temp1, temp2, temp);
                            temp1--;
                            temp2++;
                        }
                    } else { //Lên trên bên trái
                        while (temp1 != x1 && temp2 != y1) {
                            setIndexGridComputer(temp1, temp2, temp);
                            temp1--;
                            temp2--;
                        }
                    }
                }
                setIndexGridComputer(x1, y1, temp);
            } else if (x == x1 && y != y1) {
                if (y < y1) for (int index = y; index <= y1; index++) setIndexGridComputer(x, index, temp);
                else for (int index = y1; index <= y; index++) setIndexGridComputer(x, index, temp);
            } else if (x != x1) {
                if (x < x1) for (int index = x; index <= x1; index++) setIndexGridComputer(index, y, temp);
                else for (int index = x1; index <= x; index++) setIndexGridComputer(index, y, temp);
            }
        }
    }

    public int numTypeRocketNumRocketZero() {
        int temp = 0;
        for (int i = 0; i < getNumTypeRocketUser(); i++) {
            if (listRocketUser[i].getNumRocket() == 0 && listRocketUser[i] != null) temp++;
            else break;
        }
        return temp;
    }

    public int getNumRocketByType(int type) {
        int temp = 0;
        for (int i = 0; i < numTypeRocketUser; i++) {
            if (listRocketUser[i].getTypeRocket() == type) {
                temp = listRocketUser[i].getNumRocket();
                break;
            }
        }
        return temp;
    }

    public Rocket getRocketByType(int type) {
        Rocket temp = new Rocket();
        for (int i = 0; i < getNumTypeRocketUser(); i++) {
            if (listRocketUser[i].getTypeRocket() == type) {
                temp = listRocketUser[i];
                break;
            }
        }
        return temp;
    }

    public void addNumRocketByType(int type, int num) {
        for (int i = 0; i < numTypeRocketUser; i++) {
            if (listRocketUser[i].getTypeRocket() == type) {
                listRocketUser[i].setNumRocket(listRocketUser[i].getNumRocket() + num);
                break;
            }
        }
    }

    public void subNumRocketByType(int type, int num) {
        for (int i = 0; i < numTypeRocketUser; i++) {
            if (listRocketUser[i].getTypeRocket() == type) {
                listRocketUser[i].setNumRocket(listRocketUser[i].getNumRocket() - num);
                break;
            }
        }
    }

    @Override
    public boolean checkRepeatComputer(int x, int y) {
        return (getIndexGridComputer(x, y).equals("-")) || (getIndexGridComputer(x, y).equals("x"))
                || (getIndexGridComputer(x, y).equals("D"));
    }
}
