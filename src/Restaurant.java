import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    //variables
    private String name;
    private double cashReg;
    List<Servers> serverList = new ArrayList<>();
    List<Table> tableList = new ArrayList<>();
    List<Party> waitingList = new ArrayList<>();

    //constructor
    public Restaurant(String name) {
        this.name = name;
    }

    //adders
    public boolean addServer(Servers server) {
            if (server != null) {
                return serverList.add(server);
            }
            return false;
    }

    public boolean addParty(Party party) {
        if (party != null) {
            Party p = new Party(party.getName(),party.getPartySize());
            return true;
        }
        return false;
    }

    public void addPartyToTable(Party party) {
        if(party != null) {
            if(getLargestTable() < party.getPartySize()) {

                for(int i = 0; i < tableList.size(); i++) {
                    if(getLargestTable() == tableList.get(i).getSeats() && tableList.get(i).getIsOccupied() == false) {
                        tableList.get(i).setIsOccupied(true);
                    }
                }
            }
        }
    }

    public boolean addWaiting(Party party) {
        if (party != null) {
            return waitingList.add(party);
        }
        return false;
    }

    public void addCashReg(double amount) {
        cashReg += amount;
    }

    //remove items
    public boolean removeServer(Servers server) {
        if (server != null) {
            return serverList.remove(server);
        }
        return false;
    }

    public boolean removeWaiting(Party party) {
        if (party != null) {
            return waitingList.remove(party);
        }
        return false;
    }

    //getters
    public String getName() {return name;}
    public double getCashReg() {return cashReg;}
    public int getServers() {
        Servers server = new Servers(1);
        return server.getNumOfServers();
    }

    public Table getTable(int tableNum) {
        for (int i = 0; i < tableList.size(); i++) {
            if (tableNum == tableList.get(i).getTableNum()) {
                Table t = new Table(tableList.get(i).getTableNum(), tableList.get(i).getSeats(), tableList.get(i).getIsOccupied(), tableList.get(i).getServer());
                return t;
            }
        }
        return null;
    }

    public Party getWaiting(String partyName) {
        for (int i = 0; i < waitingList.size(); i++) {
            if (partyName.equals(waitingList.get(i).getName())) {
                Party p = new Party(waitingList.get(i).getName(), waitingList.get(i).getPartySize());
                return p;
            }
        }
        return null;
    }

    public int getLargestTable() {
        int largestTable = 0;
        for(int i = 0; i < tableList.size(); i++) {
            if(largestTable < tableList.get(i).getSeats() && tableList.get(i).getIsOccupied() == false)
                largestTable = tableList.get(i).getSeats();
        }
        return largestTable;
    }

    public boolean isWaitingListEmpty() {
        if (waitingList.get(0) == null)
            return true;
        return false;
    }

    public boolean isServerListEmpty() {
        if (serverList.get(0) == null)
            return true;
        return false;
    }

    public void printWaitingList() {
        for (int i = 0; i < waitingList.size(); i++) {
            System.out.println(waitingList.get(i));
        }
    }

    public void displayAllServers() {
        for (int i = 0; i < serverList.size(); i++) {
            System.out.println(serverList.get(i));
        }
    }

    public void initiateTables(List<Table> t){
        this.tableList = t;
    }

}