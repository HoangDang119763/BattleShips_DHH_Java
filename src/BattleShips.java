import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

public abstract class BattleShips implements Serializable {
    private int numRows;
    private int numColums;
    private int playerShips;
    private int computerShips;
    private boolean status;

    private String[][] gridPlayer;
    private String[][] gridComputer;

    //Constructor
    public BattleShips() {
        this.playerShips = 0;
        this.computerShips = 0;
        this.status = true;
    }

    public BattleShips(int numRows, int numColums, int playerShips, int computerShips) {
        this.numRows = numRows;
        this.numColums = numColums;
        this.playerShips = playerShips;
        this.computerShips = computerShips;
        this.status = true;
        this.gridPlayer = new String[numRows][numColums];
        this.gridComputer = new String[numRows][numColums];
    }

    //Getter và Setter
    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumColums() {
        return numColums;
    }

    public void setNumColums(int numColums) {
        this.numColums = numColums;
    }

    public int getPlayerShips() {
        return playerShips;
    }

    public void setPlayerShips(int playerShips) {
        this.playerShips = playerShips;
    }

    public int getComputerShips() {
        return computerShips;
    }

    public void setComputerShips(int computerShips) {
        this.computerShips = computerShips;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String[][] getGridPlayer() {
        return gridPlayer;
    }

    public void setGridPlayer(String[][] gridPlayer) {
        this.gridPlayer = gridPlayer;
    }

    public String[][] getGridComputer() {
        return gridComputer;
    }

    public void setGridComputer(String[][] gridComputer) {
        this.gridComputer = gridComputer;
    }

    public String getIndexGridPlayer(int x, int y) {
        return this.gridPlayer[x][y];
    }

    public void setIndexGridPlayer(int x, int y, String index) {
        this.gridPlayer[x][y] = index;
    }

    public String getIndexGridComputer(int x, int y) {
        return this.gridComputer[x][y];
    }

    public void setIndexGridComputer(int x, int y, String index) {
        this.gridComputer[x][y] = index;
    }

    public void createGrid() {
        this.gridPlayer = new String[getNumRows()][getNumColums()];
        this.gridComputer = new String[getNumRows()][getNumColums()];
    }

    //Cấu trúc của game BattleShips
    public abstract void inputData();

    public abstract void createOceanMap();

    public abstract void printOceanMap(String[][] grid);
    public int numGrid() {
        String n1 = Integer.toString(getNumRows()), n2 = Integer.toString(getNumColums());
        return n1.length() > n2.length() ? n1.length() : n2.length();
    }

    public abstract void deployPlayerShips();

    public abstract void deployComputerShips();

    public abstract void playerTurn();

    public abstract void computerTurn();

    public abstract void gameOver();

    public abstract void startBattle();

    public abstract void statisticalGame();

    //Kiểm tra có thuộc bản đồ không
    public boolean checkGrid(int x, int y) {
        boolean flag = true;
        if ((x < 0 || x >= getNumRows()) || (y < 0 || y >= getNumColums())) flag = false;
        return flag;
    }

    //Kiểm tra máy có lặp lại lựa chọn không
    public abstract boolean checkRepeatComputer(int x, int y);
}
