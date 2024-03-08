import java.util.Scanner;

public class BattleShips_TypeTwo extends BattleShips_Rocket {
    public BattleShips_TypeTwo() {
        super();
    }

    public BattleShips_TypeTwo(ListItemUser listShipUser) {
        super(listShipUser);
    }

    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        super.inputData();
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
    }

    @Override
    public void printOceanMap(String[][] grid) {
        System.out.println();
        for (int j = 1; j <= numGrid(); j++) System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < getNumColums(); i++) {
            System.out.print(String.format("%" + (numGrid() + 1) + "s", i + "|"));
        }
        System.out.println();
        //Phần giữa của map
        for (int i = 0; i < getNumRows(); i++) {
            System.out.print(String.format("%" + numGrid() + "s", i) + "|");
            for (int j = 0; j < getNumColums(); j++) {
                System.out.print(String.format("%" + numGrid() + "s", grid[i][j]) + "|");
            }
            System.out.println(i);
        }
        for (int j = 1; j <= numGrid(); j++) System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < getNumColums(); i++) {
            System.out.print(String.format("%" + (numGrid() + 1) + "s", i + "|"));
        }
        System.out.println();
    }

    @Override
    public void deployComputerShips() {
        System.out.println("\nComputer đang đặt tọa độ!");
        //Đặt tọa độ cho Computer
        for (int i = 1; i <= getComputerShips(); ) {
            int x = (int) (Math.random() * getNumRows());
            int y = (int) (Math.random() * getNumColums());
            if ((getIndexGridComputer(x, y).equals(" ")) && !(getIndexGridPlayer(x, y).equals("P"))) //Không cho phép trùng
            {
                setIndexGridComputer(x, y, "C");
                System.out.println("Thuyền thứ: " + i + " ĐÃ ĐƯỢC ĐẶT!!!");
                //=================================
                setHpComputer(getHpComputer() + 1);
                i++;
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
                } else {
                    System.out.println("Bạn không đủ rocket!");
                    while (true) {
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
            if (checkGrid(x, y)) //valid guess Integer.parseInt(x)
            {
                //Chế độ thường
                ////flag true => dùng tên lửa
                if (flag && getIndexNumRocket(0) != 0) {
                    int numShipSunked = numShipSunkByRocket(x, y, 1, getRocketByType(choiceTypeRocket).getTypeRocket());
                    int numShipSelfSunked = numShipSelfSunkByRocket(x, y, 1, getRocketByType(choiceTypeRocket).getTypeRocket());
                    int numShipMisSunked = numShipMissSunkByRocket(x, y, 1, getRocketByType(choiceTypeRocket).getTypeRocket());
                    if (getIndexGridComputer(x, y).equals("C")) //Kiểm tra xem ỏ đó có thuyền Computer không
                    {
                        int s = 1 + numShipSunked;
                        System.out.println("Boom! Bạn đã bắn trúng " + s + " thuyền ^^!");
                        //Cập nhật map
                        setIndexGridPlayer(x, y, "D");
                        setIndexGridComputer(x, y, "d");
                        setHpComputer(getHpComputer() - s);
                        if (numShipSelfSunked != 0)
                            System.out.println("Rocket cũng đã vô tình trúng " + numShipSelfSunked + "thuyền của bản thân :((");
                        //setComputerShips(getComputerShips() - 1);
                    } else if (getIndexGridPlayer(x, y).equals("P")) {
                        if (numShipSunked == 0)
                            System.out.println("Ôi không, bạn đã tự bắn trúng thuyền của bản thân :((");
                        else {
                            System.out.println("Bạn đã bắn chính bản thân nhưng cũng đã tiêu diệt được " + numShipSunked + " thuyền bằng rocket");
                            setHpComputer(getHpComputer() - numShipSunked);
                        }
                        setIndexGridPlayer(x, y, "x");
                    } else if (getIndexGridComputer(x, y).equals(" ")) {
                        if (numShipSunked == 0) System.out.println("Oops, hụt mất rồi :<");
                        else {
                            System.out.println("Bạn đã bắn hụt nhưng cũng đã tiêu diệt được " + numShipSunked + " thuyền bằng rocket");
                            setHpComputer(getHpComputer() - numShipSunked);
                        }
                        setIndexGridPlayer(x, y, "-");
                    }
                    setIndexNumRocket(0, getIndexNumRocket(0) - 1); //Giảm tên lửa xuống
                    subNumRocketByType(choiceTypeRocket, 1);
                } else {
                    if (getIndexGridComputer(x, y).equals("C")) //Kiểm tra xem ỏ đó có thuyền Computer không
                    {
                        System.out.println("Boom! Bạn đã bắn trúng 1 thuyền ^^!");
                        //Cập nhật map
                        setIndexGridPlayer(x, y, "D");
                        setIndexGridComputer(x, y, "d");
                        setHpComputer(getHpComputer() - 1);
                    } else if (getIndexGridPlayer(x, y).equals("P")) {
                        System.out.println("Ôi không, bạn đã tự bắn trúng thuyền của bản thân :((");
                        setIndexGridPlayer(x, y, "x");
                    } else if (getIndexGridComputer(x, y).equals(" ")) {
                        System.out.println("Oops, hụt mất rồi :<");
                        setIndexGridPlayer(x, y, "-");
                    }
                }
            } else if (!checkGrid(x, y)) //Tọa độ đoán không hợp lệ
                System.out.println("Bạn không thể đặt ngoài kích thước map đã quy định: " + getNumRows() + " và " + getNumColums());
        } while (!checkGrid(x, y));
        //Test
        printOceanMap(getGridPlayer());
    }

    @Override
    public void computerTurn() {
        System.out.println("\nLượt của Computer!");
        //Đoán tọa độ
        int x = -1, y = -1;
        do {
            x = (int) (Math.random() * getNumRows());
            y = (int) (Math.random() * getNumColums());
            if (checkGrid(x, y) && !checkRepeatComputer(x, y)) //valid guess
            {
                if (getIndexGridPlayer(x, y).equals("P")) //if player ship is already there; player loses ship
                {
                    System.out.println("Computer đã bắn trúng một những vị trí đặt thuyền của bạn!");
                    setIndexGridPlayer(x, y, "d");
                    setIndexGridComputer(x, y, "D");
                    setHpPlayer(getHpPlayer() - 1);
                } else if (getIndexGridComputer(x, y).equals("C")) {
                    System.out.println("Computer đã tự bắn trúng thuyền của bản thân!");
                    setIndexGridComputer(x, y, "x");
                } else if (getIndexGridComputer(x, y).equals(" ")) {
                    System.out.println("Computer đẵ bắn hụt!");
                    setIndexGridComputer(x, y, "-");
                }
            } else x = y = -1; //Không thỏa điều kiện thì lặp lại
        } while (!checkGrid(x, y)); //Example: x = -1 => checkGrid = false => ! flase = true
        //Test
        System.out.println("x: " + x + " y: " + y);
        printOceanMap(getGridComputer());
    }

    @Override
    public void startBattle() {
        System.out.println("====> TYPE TWO <====");
        super.startBattle();
        statisticalGame();
    }
}
