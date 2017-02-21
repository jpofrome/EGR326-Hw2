import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {
    //variables
    private int tableNum;
    private int seats;
    private boolean occupied = false;
    private Servers server;
    private String line = null;
    private List<String> holder = new ArrayList<String>();

    //constructor
    public Table(int tableNum, int seats, boolean occupied, Servers server) {
        this.tableNum = tableNum;
        this.seats = seats;
        this.occupied = occupied;
        this.server = server;
    }

    //gets data about tables from file
    public List<Table> producer() {
        List<Table> tables = new ArrayList<Table>();
        try {
            FileReader fileReader = new FileReader("tables.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lines = "";

            while ((line = bufferedReader.readLine()) != null) {
                lines += " " + line;
            }

            holder = Arrays.asList(lines.split("\\s* \\s*"));

            try {
                for (int i = 0; i < holder.size(); i++) {
                    if (i > 2) {
                        Table temp = new Table(i - 2, Integer.parseInt(holder.get(i)), false, null);
                        tables.add(i - 3, temp);
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("ERROR FUCK ME");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return tables;
    }

    public void printTables() {
        List<Table> tables = new ArrayList<Table>();
        tables = producer();
        for (int i = 0; i < tables.size(); i++) {
            System.out.println(tables.get(i));
        }
    }

    //checks if certain table is occupied
    public boolean getIsOccupied() {return occupied;}
    public int getTableNum() {return tableNum;}
    public int getSeats() {return seats;}
    public Servers getServer() {return server;}

    //setter
    public void setIsOccupied(boolean occupied) {this.occupied = occupied;}
    public void setServer(Servers server) {this.server = server;}

    @Override
    public String toString() {
        return ("Table number: " + getTableNum() + "\n" + "Seats: " + getSeats() + "\n" + "Occupied: " + getIsOccupied() + "\n");
    }

}