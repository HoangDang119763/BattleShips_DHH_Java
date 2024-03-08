import java.util.Scanner;

public class BattleShips_TypeThree extends BattleShips_Rocket {
    private int maxSizeShipComputer;
    private int[] delayRocket;

    public BattleShips_TypeThree() {
        this.maxSizeShipComputer = 1;
        this.delayRocket = new int[3];
    }

    public BattleShips_TypeThree(ListItemUser listShipUser) {
        super(listShipUser);
        this.delayRocket = new int[3];
    }

    public BattleShips_TypeThree(ListItemUser listShipUser, int maxSizeShipComputer, int[] delayRocket) {
        super(listShipUser);
        this.maxSizeShipComputer = maxSizeShipComputer;
        this.delayRocket = delayRocket;
    }

    public int getMaxSizeShipComputer() {
        return maxSizeShipComputer;
    }

    public void setMaxSizeShipComputer(int maxSizeShipComputer) {
        this.maxSizeShipComputer = maxSizeShipComputer;
    }

    public int[] getDelayRocket() {
        return delayRocket;
    }

    public int getIndexDelayRocket(int index) {
        return this.getDelayRocket()[index];
    }

    public void setDelayRocket(int[] delayRocket) {
        this.delayRocket = delayRocket;
    }

    public void setIndexDelayRocket(int index, int delayRocket) {
        this.getDelayRocket()[index] = delayRocket;
    }

    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        super.inputData();
        setNumRows(getNumRows() * 2);
        System.out.println("Tổng số thuyền bạn đang có: " + getListShipUser().numAllItem_Ship());
        while (true) {
            System.out.print("Mời bạn nhập số thuyền của BẠN: ");
            int numShipPlayer = Integer.parseInt(sc.nextLine());
            if (getListShipUser().numAllItem_Ship() < numShipPlayer) System.out.println("Bạn không đủ thuyền!");
            else {
                setPlayerShips(numShipPlayer);
                System.out.print("Mời bạn nhập số thuyền của COMPUTER: ");
                setComputerShips(Integer.parseInt(sc.nextLine()));
                createGrid();
                break;
            }
        }
        while (true) {
            System.out.print("Mời bạn nhập kích thước thuyền tối đa của Computer: ");
            setMaxSizeShipComputer(Integer.parseInt(sc.nextLine()));
            System.out.print("Mời bạn nhập lượt chờ sử dùng Rocket: ");
            setIndexDelayRocket(0, Integer.parseInt(sc.nextLine()));
            setIndexDelayRocket(1, 0);
            setIndexDelayRocket(2, 0);
            if ((getMaxSizeShipComputer() >= 1) && (getIndexDelayRocket(0) >= 2)) break;
            else if ((getMaxSizeShipComputer() < 1) && (getIndexDelayRocket(0) >= 2))
                System.out.println("Kích thước thuyền tối đa của Computer phải lớn hơn 1");
            else if ((getMaxSizeShipComputer() >= 1) && (getIndexDelayRocket(0) < 2))
                System.out.println("Lượt chờ sử dùng Rocket phải lớn hơn 2");
            else {
                System.out.println("Kích thước thuyền tối đa của Computer phải lớn hơn 1 và Lượt chờ sử dùng Rocket phải lớn hơn 2");
            }
        }
    }

    @Override
    public void printOceanMap(String[][] grid) {
        System.out.println();
        for (int j = 1; j <= numGrid(); j++) System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < getNumColums(); i++) {
            System.out.print(String.format("%" + numGrid() + "s", i) + "|");
        }
        System.out.println();
        for (int i = 0; i < getNumRows(); i++) {
            if (i == getNumRows()/2) {
                for (int k = 0; k <= numGrid(); k++) System.out.print("=");
                for (int j = 0; j < getNumColums(); j++)
                    for (int m = 0; m <= numGrid(); m++) System.out.print("=");
                for (int k = 0; k < numGrid(); k++) System.out.print("=");
                System.out.println();
            }
            System.out.print(String.format("%" + numGrid() + "s", (i % (getNumRows()/2))) + "|");
            for (int j = 0; j < getNumColums(); j++) {
                System.out.print(String.format("%" + numGrid() + "s", grid[i][j]) + "|");
            }
            System.out.println(i % (getNumRows()/2));
        }
        for (int j = 1; j <= numGrid(); j++) System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < getNumColums(); i++) {
            System.out.print(String.format("%" + numGrid() + "s", i) + "|");
        }
        System.out.println();
    }

    private boolean checkGridPlayer(int x, int y) {
        boolean flag = true;
        if ((x < 0 || x >= getNumRows()/2) || (y < 0 || y >= getNumColums())) flag = false;
        return flag;
    }

    @Override
    public void deployPlayerShips() {
        Scanner sc = new Scanner(System.in);
        int x, y, x1, y1, choice, direction;
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
                        if (checkGridPlayer(x, y) && (getIndexGridPlayer(x, y).equals(" "))) {
                            setIndexGridPlayer(x, y, "P");
                            getListShipUser().getListShip().get(choice - 1).subItemNum(1);
                            setHpPlayer(getHpPlayer() + sizeShip);
                            if (getListShipUser().getListShip().get(choice - 1) instanceof Item_Ship_Rocket) {
                                setIndexNumRocket(0, getIndexNumRocket(0) + ((Item_Ship_Rocket) getListShipUser().getListShip().get(choice - 1)).numAllRocket());
                                addNumRocketByType(((Item_Ship_Rocket) getListShipUser().getListShip().get(choice - 1)).getListRocket());
                            }
                            i++;
                            printOceanMap(getGridPlayer());
                        } else if (checkGridPlayer(x, y) && getIndexGridPlayer(x, y).equals("P"))
                            System.out.println("Bạn không thể đặt nhiều hơn một thuyền tại cùng một vị trí!");
                        else if (!checkGridPlayer(x, y))
                            System.out.println("Bạn không thể đặt ngoài kích thước map đã quy định: " + (getNumRows()/2) + " và " + getNumColums());
                    } else {
                        while (true) {
                            System.out.print("Nhập tọa độ X cho thuyền thứ " + i + " : ");
                            x = sc.nextInt();
                            System.out.print("Nhập tọa độ Y cho thuyền thứ " + i + " : ");
                            y = sc.nextInt();
                            boolean flag = true;
                            int count = 0;
                            if (checkGridPlayer(x, y)) {
                                while (flag) {
                                    System.out.println("0. Ngang trái|| 1. Ngang phải|| 2. Lên trên|| 3. Xuống dưới|| 4. Lên trên-Trái|| 5. Lên trên-Phải|| 6. Xuống dưới-Trái|| 7. Xuống dưới-Phải");
                                    System.out.print("Nhập chiều cho thuyền thứ " + i + " : ");
                                    direction = sc.nextInt();
                                    if (direction >= 0 && direction <= 7) {
                                        count++;
                                        if (count < 8) {
                                            switch (direction) {
                                                case 0 -> { //Ngang trái   vd 2 2 => 2 1 . kích thước 2/ 3 thì 2 2 => 2 0
                                                    if ((y - sizeShip + 1) >= 0) {
                                                        if (!checkSameShip(x, y, x, (y - sizeShip + 1), 1, "P")) {
                                                            deployShipAuto(x, y, x, (y - sizeShip + 1), 1, "P");
                                                            flag = false;
                                                        } else
                                                            System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                    } else
                                                        System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                                }
                                                case 1 -> { //Ngang phải vd 2 2 => 2 3 . kích thước 2/ 3 thì 2 2 => 2 4
                                                    if ((y + sizeShip - 1) < getNumColums()) {
                                                        if (!checkSameShip(x, y, x, (y + sizeShip - 1), 1, "P")) {
                                                            deployShipAuto(x, y, x, (y + sizeShip - 1), 1, "P");
                                                            flag = false;
                                                        } else
                                                            System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                    } else
                                                        System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                                }
                                                case 2 -> { //Lên trên vd 2 2 => 1 2 . kích thước 2/ 3 thì 2 2 => 0 2
                                                    if ((x - sizeShip + 1) >= 0) {
                                                        if (!checkSameShip(x, y, (x - sizeShip + 1), y, 1, "P")) {
                                                            deployShipAuto(x, y, (x - sizeShip + 1), y, 1, "P");
                                                            flag = false;
                                                        } else
                                                            System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                    } else
                                                        System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                                }
                                                case 3 -> { //Xuống dưới vd 2 2 => 3 2 . kích thước 2/ 3 thì 2 2 => 4 2
                                                    if ((x + sizeShip - 1) < getNumRows() / 2) {
                                                        if (!checkSameShip(x, y, (x + sizeShip - 1), y, 1, "P")) {
                                                            deployShipAuto(x, y, (x + sizeShip - 1), y, 1, "P");
                                                            flag = false;
                                                        } else
                                                            System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                    } else
                                                        System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                                }
                                                case 4 -> { //Lên trên bên trái vd 2 2 => 1 1 . kích thước 2/ 3 thì 2 2 => 0 0
                                                    if (((x - sizeShip + 1) >= 0) && ((y - sizeShip + 1) >= 0)) {
                                                        if (!checkSameShip(x, y, (x - sizeShip + 1), (y - sizeShip + 1), 1, "P")) {
                                                            deployShipAuto(x, y, (x - sizeShip + 1), (y - sizeShip + 1), 1, "P");
                                                            flag = false;
                                                        } else
                                                            System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                    } else
                                                        System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                                }
                                                case 5 -> { //Lên trên bên phải vd 2 2 => 1 3 . kích thước 2/ 3 thì 2 2 => 0 4
                                                    if (((x - sizeShip + 1) >= 0) && ((y + sizeShip - 1) < getNumColums())) {
                                                        if (!checkSameShip(x, y, (x - sizeShip + 1), (y + sizeShip - 1), 1, "P")) {
                                                            deployShipAuto(x, y, (x - sizeShip + 1), (y + sizeShip - 1), 1, "P");
                                                            flag = false;
                                                        } else
                                                            System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                    } else
                                                        System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                                }
                                                case 6 -> { //Xuống dưới bên trái vd 2 2 => 3 1 . kích thước 2/ 3 thì 2 2 => 4 0     1 4 => 3 2. kích thước 3
                                                    if (((x + sizeShip - 1) < getNumRows() / 2) && ((y - sizeShip + 1) >= 0)) {
                                                        if (!checkSameShip(x, y, (x + sizeShip - 1), (y - sizeShip + 1), 1, "P")) {
                                                            deployShipAuto(x, y, (x + sizeShip - 1), (y - sizeShip + 1), 1, "P");
                                                            flag = false;
                                                        } else
                                                            System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                    } else
                                                        System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                                }
                                                case 7 -> { //Xuống dưới bên phải vd 2 2 => 3 3 . kích thước 2/ 3 thì 2 2 => 4 4
                                                    if (((x + sizeShip - 1) < getNumRows() / 2) && ((y + sizeShip - 1) < getNumColums())) {
                                                        if (!checkSameShip(x, y, (x + sizeShip - 1), (y + sizeShip - 1), 1, "P")) {
                                                            deployShipAuto(x, y, (x + sizeShip - 1), (y + sizeShip - 1), 1, "P");
                                                            flag = false;
                                                        } else
                                                            System.out.println("Thuyền bị trùng với một thuyền khác!");
                                                    } else
                                                        System.out.println("Chiều này ngoài kích thước map đã quy định: " + (getNumRows() / 2) + " và " + getNumColums());
                                                }
                                            }
                                        } else {
                                            System.out.println("Có vẻ vị trí này không phù hợp để đặt thuyền!");
                                            break;
                                        }
                                    } else System.out.println("Chiều không hợp lệ, mời bạn nhập lại!");
                                }
                            } else if (!checkGridPlayer(x, y)) {
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
        //In map của Player
        printOceanMap(getGridPlayer());
    }

    @Override
    public void deployComputerShips() {
        System.out.println("\nComputer đang đặt tọa độ!");
        //Đặt tọa độ cho Computer
        for (int i = 1; i <= getComputerShips(); ) {
            int x = (int) (Math.random() * (getNumRows()/2) + getNumRows()/2);
            int y = (int) (Math.random() * getNumColums());
            int sizeShip = (int) (Math.random() * getMaxSizeShipComputer()) + 1;
            if (sizeShip == 1) {
                if ((getIndexGridComputer(x, y).equals(" ")) && !(getIndexGridPlayer(x, y).equals("P"))) //Không cho phép trùng
                {
                    setIndexGridComputer(x, y, "C");
                    System.out.println("Thuyền thứ: " + i + " ĐÃ ĐƯỢC ĐẶT!!!");
                    System.out.println((x % (getNumRows()/2)) + "   " + y);
                    //=================================
                    setHpComputer(getHpComputer() + 1);
                    i++;
                }
            } else {
                boolean flag = true;
                int temp1 = 0 , temp2 = 0, count = 0;
                if (getIndexGridComputer(x, y).equals(" ")) {
                    while (flag && count != 9) {
                        int direction = (int) (Math.random() * 50) % 8;
                        switch (direction) {
                            case 0 -> { //Ngang trái   vd 2 2 => 2 1 . kích thước 2/ 3 thì 2 2 => 2 0
                                if ((y - sizeShip + 1) >= 0) {
                                    if (!checkSameShip(x, y, x, (y - sizeShip + 1), 2, "C")) {
                                        deployShipAuto(x, y, x, (y - sizeShip + 1), 2, "C");
                                        flag = false;
                                        temp1 = x;
                                        temp2 = (y - sizeShip + 1);
                                    }
                                }
                            }
                            case 1 -> { //Ngang phải vd 2 2 => 2 3 . kích thước 2/ 3 thì 2 2 => 2 4
                                if ((y + sizeShip - 1) < getNumColums()) {
                                    if (!checkSameShip(x, y, x, (y + sizeShip - 1), 2, "C")) {
                                        deployShipAuto(x, y, x, (y + sizeShip - 1), 2, "C");
                                        flag = false;
                                        temp1 = x;
                                        temp2 = (y + sizeShip - 1);
                                    }
                                }
                            }
                            case 2 -> { //Lên trên vd 2 2 => 1 2 . kích thước 2/ 3 thì 2 2 => 0 2
                                if ((x - sizeShip + 1) >= getNumRows()/2) {
                                    if (!checkSameShip(x, y, (x - sizeShip + 1), y, 2, "C")) {
                                        deployShipAuto(x, y, (x - sizeShip + 1), y, 2, "C");
                                        flag = false;
                                        temp1 = (x - sizeShip + 1);
                                        temp2 = y;
                                    }
                                }
                            }
                            case 3 -> { //Xuống dưới vd 2 2 => 3 2 . kích thước 2/ 3 thì 2 2 => 4 2
                                if ((x + sizeShip - 1) < getNumRows()) {
                                    if (!checkSameShip(x, y, (x + sizeShip - 1), y, 2, "C")) {
                                        deployShipAuto(x, y, (x + sizeShip - 1), y, 2, "C");
                                        flag = false;
                                        temp1 = (x + sizeShip - 1);
                                        temp2 = y;
                                    }
                                }
                            }
                            case 4 -> { //Lên trên bên trái vd 2 2 => 1 1 . kích thước 2/ 3 thì 2 2 => 0 0
                                if (((x - sizeShip + 1) >= getNumRows()/2) && ((y - sizeShip + 1) >= 0)) {
                                    if (!checkSameShip(x, y, (x - sizeShip + 1), (y - sizeShip + 1), 2, "C")) {
                                        deployShipAuto(x, y, (x - sizeShip + 1), (y - sizeShip + 1), 2, "C");
                                        flag = false;
                                        temp1 = (x - sizeShip + 1);
                                        temp2 = (y - sizeShip + 1);
                                    }
                                }
                            }
                            case 5 -> { //Lên trên bên phải vd 2 2 => 1 3 . kích thước 2/ 3 thì 2 2 => 0 4
                                if (((x - sizeShip + 1) >= getNumRows()/2) && ((y + sizeShip - 1) < getNumColums())) {
                                    if (!checkSameShip(x, y, (x - sizeShip + 1), (y + sizeShip - 1), 2, "C")) {
                                        deployShipAuto(x, y, (x - sizeShip + 1), (y + sizeShip - 1), 2, "C");
                                        flag = false;
                                        temp1 = (x - sizeShip + 1);
                                        temp2 = (y + sizeShip - 1);
                                    }
                                }
                            }
                            case 6 -> { //Xuống dưới bên trái vd 2 2 => 3 1 . kích thước 2/ 3 thì 2 2 => 4 0     1 4 => 3 2. kích thước 3
                                if (((x + sizeShip - 1) < getNumRows()) && ((y - sizeShip + 1) >= 0)) {
                                    if (!checkSameShip(x, y, (x + sizeShip - 1), (y - sizeShip + 1), 2, "C")) {
                                        deployShipAuto(x, y, (x + sizeShip - 1), (y - sizeShip + 1), 2, "C");
                                        flag = false;
                                        temp1 = (x + sizeShip - 1);
                                        temp2 = (y - sizeShip + 1);
                                    }
                                }
                            }
                            case 7 -> { //Xuống dưới bên phải vd 2 2 => 3 3 . kích thước 2/ 3 thì 2 2 => 4 4
                                if (((x + sizeShip - 1) < getNumRows()) && ((y + sizeShip - 1) < getNumColums())) {
                                    if (!checkSameShip(x, y, (x + sizeShip - 1), (y + sizeShip - 1), 2, "C")) {
                                        deployShipAuto(x, y, (x + sizeShip - 1), (y + sizeShip - 1), 2, "C");
                                        flag = false;
                                        temp1 = (x + sizeShip - 1);
                                        temp2 = (y + sizeShip - 1);
                                    }
                                }
                            }
                        }
                        count++;
                    }
                    if (count != 9) {
                        System.out.println("Thuyền thứ: " + i + " ĐÃ ĐƯỢC ĐẶT!!!");
                        System.out.println((x % (getNumRows()/2)) + "   " + y);
                        System.out.println((temp1 % (getNumRows()/2)) + "   " + temp2);
                        //=================================
                        setHpComputer(getHpComputer() + sizeShip);
                        i++;
                    }
                }
            }
        }
        //In cả Player và Computer => Test
        printOceanMap(getGridComputer());
    }

    @Override
    public void playerTurn() {
        System.out.println("\nLượt của BẠN!");
        int x, y;
        do {
            Scanner sc = new Scanner(System.in);
            boolean flag = false;
            int choiceTypeRocket = 0;
            System.out.print("(Bấm -1 để lưu)     (Bấm -2 để sử dụng tên lửa) - Hiện có : " + getIndexNumRocket(0) + "| ");
            showListRocketUser();
            System.out.print("Nhập tọa độ X: ");
            x = Integer.parseInt(sc.nextLine());
            //Nếu x == -1 => Save
            if (x == -1) {
                setStatus(false);
                break;
            } else if (x == -2) {
                if (getIndexNumRocket(0) != 0) {
                    if (getIndexDelayRocket(1) != 0) { // Ví dụ delay là 3. hiện tại nếu là 0 => dc bắn . nếu là 2 => cần đợi 1 lượt nữa
                        System.out.println("Bạn phải đợi: " + (getIndexDelayRocket(1)) + " turn để dùng Rocket!");
                        while (true) {
                            System.out.print("Nhập tọa độ X: ");
                            x = Integer.parseInt(sc.nextLine());
                            if (x != -2) break;
                            else
                                System.out.println("Bạn phải đợi: " + (getIndexDelayRocket(1)) + " turn để dùng Rocket!");
                        }
                    } else if (getIndexDelayRocket(1) == 0) {
                        flag = true;
                        while (true) {
                            System.out.println("====> ROCKET <====");
                            while (true) {
                                if (getNumTypeRocketUser() == 1 || (getNumTypeRocketUser() - numTypeRocketNumRocketZero()) == 1) break;
                                System.out.print("Chọn loại Rocket: ");
                                for (int i = 0; i < getNumTypeRocketUser(); i++) {
                                    if (getListRocketUser()[i] == null) break;
                                    if (getListRocketUser()[i].getNumRocket() != 0) System.out.print("<<" + getListRocketUser()[i].getTypeRocket() + ">>  ");
                                }
                                System.out.println("");
                                choiceTypeRocket = Integer.parseInt(sc.nextLine());
                                if (getNumRocketByType(choiceTypeRocket) != 0) break;
                                else System.out.println("Lựa chọn không hợp lệ!");
                            }

                            System.out.print("Nhập tọa độ X: ");
                            x = Integer.parseInt(sc.nextLine());
                            if (x != -2) break;
                            else System.out.println("Bạn đã đang sử dụng Rocket rồi!");
                        }
                    }
                } else {
                    System.out.println("Bạn không đủ rocket!");
                    while(true) {
                        System.out.print("Nhập tọa độ X: ");
                        x = Integer.parseInt(sc.nextLine());
                        if (x != -2) break;
                        else System.out.println("Bạn không đủ rocket!");
                    }
                }
                if (x == -1) {
                    setStatus(false);
                    break;
                }
            }
            System.out.print("Nhập tọa độ Y: ");
            y = Integer.parseInt(sc.nextLine());
            if (checkGridPlayer(x, y)) //valid guess Integer.parseInt(x)
            {
                x = x + getNumRows()/2;
                ////flag true => dùng tên lửa
                if (flag && getIndexNumRocket(0) != 0 && getIndexDelayRocket(1) == 0) {
                    int numShipSunked = numShipSunkByRocket(x, y, 1, getRocketByType(choiceTypeRocket).getTypeRocket());
                    int numShipMisSunked = numShipMissSunkByRocket(x, y, 1, getRocketByType(choiceTypeRocket).getTypeRocket());
                    if (getIndexGridComputer(x, y).equals("C")) //Kiểm tra xem ỏ đó có thuyền Computer không
                    {
                        int s = 1 + numShipSunked;
                        System.out.println("Boom! Bạn đã bắn trúng " + s + " thuyền ^^!");
                        //Cập nhật map
                        setIndexGridPlayer(x, y, "D");
                        setIndexGridComputer(x, y, "d");
                        setHpComputer(getHpComputer() - s);
                    } else if (getIndexGridComputer(x, y).equals(" ")) {
                        if (numShipSunked == 0) System.out.println("Oops, hụt mất rồi :<");
                        else {
                            System.out.println("Bạn đã bắn hụt nhưng cũng đã tiêu diệt được " + numShipSunked + " thuyền bằng rocket");
                            setHpComputer(getHpComputer() - numShipSunked);
                        }
                        setIndexGridPlayer(x, y, "-");
                    } else if (getIndexGridPlayer(x, y).equals("-") || getIndexGridPlayer(x, y).equals("x")) {
                        if (numShipSunked == 0) System.out.println("Bạn đã từng bắn vị trí này rồi :<!");
                        else {
                            System.out.println("Bạn đã từng bắn vị trí này rồi nhưng cũng đã tiêu diệt được " + numShipSunked + " thuyền bằng rocket");
                            setHpComputer(getHpComputer() - numShipSunked);
                        }
                    }
                    setIndexDelayRocket(1, getIndexDelayRocket(0)); //Bắn xong thì đặt lại delay . ví dụ là 2
                    setIndexNumRocket(0,getIndexNumRocket(0) - 1); //Giảm tên lửa xuống
                    subNumRocketByType(choiceTypeRocket, 1);
                } else {
                    if (getIndexGridComputer(x, y).equals("C")) //Kiểm tra xem ỏ đó có thuyền Computer không
                    {
                        System.out.println("Boom! Bạn đã bắn trúng 1 thuyền ^^!");
                        //Cập nhật map
                        setIndexGridPlayer(x, y, "D");
                        setIndexGridComputer(x, y, "d");
                        setHpComputer(getHpComputer() - 1);
                    } else if (getIndexGridPlayer(x, y).equals(" ")) {
                        System.out.println("Oops, hụt mất rồi :<");
                        setIndexGridPlayer(x, y, "-");
                    } else if (getIndexGridPlayer(x, y).equals("-") || getIndexGridPlayer(x, y).equals("x")) {
                        System.out.println("Bạn đã từng bắn vị trí này rồi :<!");
                    }
                    //TH2 delay != 0 và k bắn rocket/ đang là 2 mà max delay là 3 => giảm delay hiện tại xuống/ 1 3
                    if (getIndexDelayRocket(1) != 0) setIndexDelayRocket(1, getIndexDelayRocket(1) - 1);
                }
                x = x - getNumRows()/2;
            } else if (!checkGridPlayer(x, y)) //Tọa độ đoán không hợp lệ
                System.out.println("Bạn không thể đặt ngoài kích thước map đã quy định: " + (getNumRows()/2) + " và " + getNumColums());
        } while (!checkGridPlayer(x, y));
        //Test
        printOceanMap(getGridPlayer());
    }

    @Override
    public void computerTurn() {
        System.out.println("\nLượt của Computer!");
        //Đoán tọa độ
        int x, y, flag = 0;
        do {
            x = (int) (Math.random() * getNumRows()/2);
            y = (int) (Math.random() * getNumColums());
            //Nếu delay hiện tại là 0 thì random coi có dùng rocket không
            if (getIndexDelayRocket(2) == 0) flag = (int) (Math.random() * 50) % 2;
            if (!checkRepeatComputer(x, y)) //valid guess
            {
                if (flag == 1 && getIndexNumRocket(1) != 0 && getIndexDelayRocket(2) == 0) { //Sử dụng rocket
                    int typeRocket = (int) (Math.random() * 50) % (getListRocketUser()[getListRocketUser().length - 1].getTypeRocket() + 1);
                    int numShipSunked = numShipSunkByRocket(x, y, 2, typeRocket);
                    int numShipMisSunked = numShipMissSunkByRocket(x, y, 2, typeRocket);
                    if (getIndexGridPlayer(x, y).equals("P"))
                    {
                        int s = 1 + numShipSunked;
                        System.out.println("Computer đã bắn trúng " + s + " vị trí đặt thuyền của bạn!");
                        setIndexGridPlayer(x, y, "d");
                        setIndexGridComputer(x, y, "D");
                        setHpPlayer(getHpPlayer() - s);
                    } else if (getIndexGridPlayer(x, y).equals(" ")) {
                        if (numShipSunked == 0) System.out.println("Computer đẵ bắn hụt!");
                        else {
                            System.out.println("Computer đẵ bắn hụt nhưng cũng đã tiêu diệt được " + numShipSunked + " thuyền của bạn bằng rocket");
                            setHpPlayer(getHpPlayer() - numShipSunked);
                        }
                        setIndexGridComputer(x, y, "-");
                    }
                    setIndexDelayRocket(2, getIndexDelayRocket(0)); //Bắn xong thì đặt lại delay . ví dụ là 2
                    setIndexNumRocket(1,getIndexNumRocket(1) - 1); //Giảm tên lửa xuống
                } else if (flag == 0) {
                    if (getIndexGridPlayer(x, y).equals("P")) //if player ship is already there; player loses ship
                    {
                        System.out.println("Computer đã bắn trúng một những vị trí đặt thuyền của bạn!");
                        setIndexGridPlayer(x, y, "d");
                        setIndexGridComputer(x, y, "D");
                        setHpPlayer(getHpPlayer() - 1);
                    } else if (getIndexGridComputer(x, y).equals(" ")) {
                        System.out.println("Computer đẵ bắn hụt!");
                        setIndexGridComputer(x, y, "-");
                    }
                    if (getIndexDelayRocket(2) != 0) setIndexDelayRocket(2, getIndexDelayRocket(2) - 1);
                }
            } else x = y = -1; //Không thỏa điều kiện thì lặp lại
        } while (!checkGrid(x, y)); //Example: x = -1 => checkGrid = false => ! flase = true
        //Test
        System.out.println("x: " + x + " y: " + y);
        printOceanMap(getGridComputer());
    }

    @Override
    public void startBattle() {
        System.out.println("====> TYPE THREE <====");
        super.startBattle();
        statisticalGame();
    }

    public void statisticalGame() {
        super.statisticalGame();
        System.out.println("Kích thước thuyền tối đa của Computer: " + getMaxSizeShipComputer());
        System.out.println("Lượt chờ sử dùng Rocket: " + (getIndexDelayRocket(1)));
    }
}
