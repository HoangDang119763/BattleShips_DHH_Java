import java.io.Serializable;
import java.util.Scanner;

public class BattleShips_TypeOne extends BattleShips implements Serializable {
    private int levelGame;
    private String[][] gridMap;
    public BattleShips_TypeOne() {
        this.levelGame = 0;
    }

    public BattleShips_TypeOne(int levelGame) {
        this.levelGame = levelGame;
    }

    public BattleShips_TypeOne(int numRows, int numColums, int playerShips, int computerShips, int levelGame) {
        super(numRows, numColums, playerShips, computerShips);
        this.levelGame = levelGame;
        this.gridMap = new String[numRows][numColums];
    }

    public int getLevelGame() {
        return levelGame;
    }

    public void setLevelGame(int levelGame) {
        this.levelGame = levelGame;
    }

    public String[][] getGridMap() {
        return gridMap;
    }

    public void setGridMap(String[][] gridMap) {
        this.gridMap = gridMap;
    }

    public String getIndexGridMap(int x, int y) {
        return this.gridMap[x][y];
    }

    public void setIndexGridMap(int x, int y, String index) {
        this.gridMap[x][y] = index;
    }

    @Override
    public void createGrid() {
        super.createGrid();
        this.gridMap = new String[getNumRows()][getNumColums()];
    }

    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mời bạn nhập số hàng(Row): ");
        setNumRows(Integer.parseInt(sc.nextLine()));
        System.out.print("Mời bạn nhập số cột(Colum): ");
        setNumColums(Integer.parseInt(sc.nextLine()));
        System.out.print("Mời bạn nhập số thuyền của BẠN: ");
        setPlayerShips(Integer.parseInt(sc.nextLine()));
        System.out.print("Mời bạn nhập số thuyền của COMPUTER: ");
        setComputerShips(Integer.parseInt(sc.nextLine()));
        createGrid();
    }

    @Override
    public void createOceanMap() {
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColums(); j++) {
                setIndexGridPlayer(i, j, " ");
                setIndexGridComputer(i, j, " ");
                setIndexGridMap(i, j, " ");
            }
        }
        //In ra xem thử map vừa tạo
        printOceanMap(getGridPlayer());
        System.out.println();
    }

    @Override
    public void printOceanMap(String[][] grid) {
        System.out.println();
        //Phần đầu của map
        for (int j = 1; j <= numGrid(); j++) System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < getNumColums(); i++) {
            System.out.print(String.format("%" + (numGrid() + 1) + "s", i + "|"));
        }
        //System.out.print(i);
        System.out.println();
        //Phần giữa của map
        for (int i = 0; i < getNumRows(); i++) {
            System.out.print(String.format("%"+ numGrid() + "s", i) + "|");
            for (int j = 0; j < getNumColums(); j++) {
                System.out.print(String.format("%"+ numGrid() + "s", grid[i][j]) + "|");
                //System.out.print(grid[i][j]);
            }
            System.out.println(i);
        }
        //Phần cuối của map
        for (int j = 1; j <= numGrid(); j++) System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < getNumColums(); i++) {
            System.out.print(String.format("%" + (numGrid() + 1) + "s", i + "|"));
        }
        System.out.println();
    }

    @Override
    public void deployPlayerShips() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nTriển khai thuyền của bạn:");
        //Deploying five ships for player
        for (int i = 1; i <= getPlayerShips(); ) {
            System.out.print("Nhập tọa độ X cho thuyền thứ " + i + " : ");
            int x = sc.nextInt();
            System.out.print("Nhập tọa độ Y cho thuyền thứ " + i + " : ");
            int y = sc.nextInt();
            if (checkGrid(x, y) && (getIndexGridPlayer(x, y).equals(" "))) {
                setIndexGridPlayer(x, y, "P");
                setIndexGridMap(x, y, "P");
                i++;
                printOceanMap(getGridPlayer());
            } else if (checkGrid(x, y) && getIndexGridPlayer(x, y).equals("P"))
                System.out.println("Bạn không thể đặt nhiều hơn một thuyền tại cùng một vị trí!");
            else if (!checkGrid(x, y))
                System.out.println("Bạn không thể đặt ngoài kích thước map đã quy định: " + getNumRows() + " và " + getNumColums());
        }
        //In map của Player
        printOceanMap(getGridPlayer());
    }

    @Override
    public void deployComputerShips() {
        System.out.println("\nComputer đang đặt tọa độ!");
        //Đặt tọa độ cho Computer
        for (int i = 1; i <= getComputerShips(); ) {
            int x = (int) (Math.random() * getNumRows());
            int y = (int) (Math.random() * getNumColums());
            //Chế độ thường
            if (this.getLevelGame() == 1) {
                if ((getIndexGridComputer(x, y).equals(" ")) && !(getIndexGridPlayer(x, y).equals("P"))) //Không cho phép trùng
                {
                    setIndexGridComputer(x, y, "C");
                    setIndexGridMap(x, y, "C");
                    System.out.println("Thuyền thứ: " + i + " ĐÃ ĐƯỢC ĐẶT!!!");
                    i++;
                }
            } else if (this.getLevelGame() == 2) { //Chế độ trung bình
                if ((getIndexGridComputer(x, y).equals(" "))) //Cho phép trùng
                {
                    setIndexGridComputer(x, y, "C");
                    //Nếu vị trí đó Player cũng đã đặt thì lưu map chung là $ không thì chỉ là C
                    if (getIndexGridPlayer(x,y).equals("P")) setIndexGridMap(x, y, "$");
                    else setIndexGridMap(x, y, "C");
                    System.out.println("Thuyền thứ: " + i + " ĐÃ ĐƯỢC ĐẶT!!!");
                    i++;
                }
            }
        }
        //In cả Player và Computer => Test
        printOceanMap(getGridMap());
    }

    @Override
    public void playerTurn() {
        setStatus(true);
        System.out.println("\nLượt của BẠN!");
        int x = -1, y = -1;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("(Bấm -1 để lưu)");
            System.out.print("Nhập tọa độ X: ");
            x = Integer.parseInt(sc.nextLine());
            //Nếu x == -1 => Save
            if (x == -1) {
                setStatus(false);
                break;
            }
            System.out.print("Nhập tọa độ Y: ");
            y = Integer.parseInt(sc.nextLine());
            if (checkGrid(x, y)) //valid guess Integer.parseInt(x)
            {
                //Chế độ thường
                if (this.getLevelGame() == 1) {
                    if (getIndexGridMap(x, y).equals("C")) //Kiểm tra xem ỏ đó có thuyền Computer không
                    {
                        System.out.println("Boom! Bạn đã bắn trúng 1 thuyền ^^!");
                        //Cập nhật map
                        setIndexGridPlayer(x, y, "D");
                        setIndexGridComputer(x, y, "d");
                        setIndexGridMap(x, y, "D");
                        setComputerShips(getComputerShips() - 1);
                    } else if (getIndexGridMap(x,y).equals("P")) {
                        System.out.println("Ôi không, bạn đã tự bắn trúng thuyền của bản thân :((");
                        setIndexGridPlayer(x, y, "x");
                    } else if (getIndexGridMap(x,y).equals(" ")) {
                        System.out.println("Oops, hụt mất rồi :<");
                        setIndexGridPlayer(x, y, "-");
                    }
                }
                //Chế độ trung bình
                else if (this.getLevelGame() == 2) {
                    if (getIndexGridMap(x,y).equals("C") || getIndexGridMap(x,y).equals("$")) {
                        System.out.println("Boom! Bạn đã bắn trúng 1 thuyền ^^!");
                        //Nếu chọn vị trí đang trùng mà trúng và PLayer còn sống
                        if (getIndexGridPlayer(x,y).equals("P") && getIndexGridMap(x,y).equals("$") && getIndexGridComputer(x, y).equals("C")) {
                            setIndexGridPlayer(x, y, "L");
                            setIndexGridComputer(x, y, "d");
                            //Trừ thuyền Computer
                            setComputerShips(getComputerShips() - 1);
                        } else if (getIndexGridPlayer(x,y).equals("d") && getIndexGridMap(x,y).equals("$")) {
                            //Đã bị bắn và chọn vô tình Computer cũng ở đó
                            setIndexGridPlayer(x, y, "l");
                            setIndexGridComputer(x, y, "l");
                            //Set lại map để hiểu là vị trí này cả 2 đã chọn
                            setIndexGridMap(x, y, "S");
                            //Trừ thuyền Computer
                            setComputerShips(getComputerShips() - 1);
                        } else if (getIndexGridMap(x,y).equals("C")) {
                            setIndexGridPlayer(x, y, "D");
                            setIndexGridComputer(x, y, "d");
                            setIndexGridMap(x, y, "D");
                            //Trừ thuyền Computer
                            setComputerShips(getComputerShips() - 1);
                        }
                    } else if (getIndexGridPlayer(x,y).equals("P")) {
                        System.out.println("Ôi không, bạn đã tự bắn trúng thuyền của bản thân :((");
                        setIndexGridPlayer(x, y, "x");
                    } else if (getIndexGridPlayer(x,y).equals(" ")) {
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
                if (this.getLevelGame() == 1) {
                    if (getIndexGridMap(x,y).equals("P")) //if player ship is already there; player loses ship
                    {
                        System.out.println("Computer đã bắn trúng một những vị trí đặt thuyền của bạn!");
                        setIndexGridPlayer(x, y, "d");
                        setIndexGridComputer(x, y, "D");
                        setIndexGridMap(x, y, "d");
                        setPlayerShips(getPlayerShips() - 1);
                    } else if (getIndexGridMap(x,y).equals("C")) {
                        System.out.println("Computer đã tự bắn trúng thuyền của bản thân!");
                        setIndexGridComputer(x, y, "x");
                    } else if (getIndexGridMap(x,y).equals(" ")) {
                        System.out.println("Computer đẵ bắn hụt!");
                        setIndexGridComputer(x, y, "-");
                    }
                }
                else if (this.getLevelGame() == 2) {
                    if (getIndexGridMap(x,y).equals("P") || getIndexGridMap(x,y).equals("$")) {
                        System.out.println("Computer đã bắn trúng một những vị trí đặt thuyền của bạn!");
                        //Trùng và player còn sống thì được quyền chọn
                        if (getIndexGridComputer(x, y).equals("C") && getIndexGridMap(x,y).equals("$") && getIndexGridPlayer(x, y).equals("P")) {
                            setIndexGridPlayer(x, y, "d");
                            setIndexGridComputer(x, y, "L");
                            //Trừ thuyền Player
                            setPlayerShips(getPlayerShips() - 1);
                        } else if (getIndexGridComputer(x, y).equals("d") && getIndexGridMap(x,y).equals("$")) {
                            //Đã bị bắn và chọn vô tình Player cũng ở đó
                            setIndexGridPlayer(x, y, "l");
                            setIndexGridComputer(x, y, "l");
                            //Set lại map để hiểu là vị trí này cả 2 đã chọn
                            setIndexGridMap(x, y, "S");
                            //Trừ thuyền Player
                            setPlayerShips(getPlayerShips() - 1);
                        } else if (getIndexGridMap(x,y).equals("P")) {
                            setIndexGridPlayer(x, y, "d");
                            setIndexGridComputer(x, y, "D");
                            setIndexGridMap(x, y, "d");
                            //Trừ thuyền Player
                            setPlayerShips(getPlayerShips() - 1);
                        }
                    } else if (getIndexGridComputer(x, y).equals("C")) {
                        System.out.println("Computer đã tự bắn trúng thuyền của bản thân!");
                        setIndexGridComputer(x, y, "x");
                    } else if (getIndexGridComputer(x, y).equals(" ")) {
                        System.out.println("Computer đẵ bắn hụt!");
                        setIndexGridComputer(x, y, "-");
                    }
                }
            } else x = y = -1; //Không thỏa điều kiện thì lặp lại
        } while (!checkGrid(x, y)); //Example: x = -1 => checkGrid = false => ! flase = true
        //Test
        System.out.println("x: " + x + " y: " + y);
        printOceanMap(getGridComputer());
    }

    @Override
    public void gameOver() {
        System.out.println("====> Kết quả <====");
        System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
        if (getPlayerShips() > 0 && getComputerShips() <= 0)
            System.out.println("Yeah! Bạn đã THẮNG Game Battle Ships!!! :>");
        else
            System.out.println("Chia buồn! Bạn đã THUA Game Battle Ships!!! :<");
        //In map tổng quát
        printOceanMap(getGridPlayer());
    }

    @Override
    public void startBattle() {
        System.out.println("====> TYPE ONE <====");
        if (getStatus()) {
            inputData();
            createOceanMap();
            deployPlayerShips();
            deployComputerShips();
        } else {
            setStatus(true);
            printOceanMap(getGridPlayer());
        }

        do {
            playerTurn();
            if (!getStatus()) break;
            //Tránh trường hợp người chơi tiêu diệt hết sau đó máy cũng tiêu diệt hết player dẫn đến sai
            if (getComputerShips() > 0) {
                computerTurn();
                System.out.println();
                System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
                System.out.println();
            }
        } while (getPlayerShips() != 0 && getComputerShips() != 0 && getStatus());
        //B5: Kết thúc
        if (getStatus()) {
            gameOver();
        }
        statisticalGame();
    }

    @Override
    public void statisticalGame() {
        String temp1 = "", temp2 = "";
        System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
        if (getStatus()) {
            temp2 = "DONE";
            if (getComputerShips() == 0) temp1 = "WIN";
            else temp1 = "LOSE";
            System.out.println("Trạng thái: " + temp2 + "| " + temp1);
        } else {
            temp2 = "STILL";
            System.out.println("Trạng thái: " + temp2);
        }
    }


    @Override
    public boolean checkRepeatComputer(int x, int y) {
        boolean flag = false;
        if ((getIndexGridComputer(x, y).equals("-")) || (getIndexGridComputer(x, y).equals("x"))
                || (getIndexGridComputer(x, y).equals("D")) || (getIndexGridMap(x, y).equals("D"))
                || getIndexGridComputer(x, y).equals("l") || getIndexGridComputer(x, y).equals("L")) flag = true;
        return flag;
    }

}
