import java.util.ArrayList;
import java.util.List;

public class Party {
    //variables
    private String name;
    private int partySize;

    //constructor
    public Party(String name, int partySize) {
        this.name = name;
        this.partySize = partySize;
    }

    //getters
    public String getName() {return name;}
    public int getPartySize() {return partySize;}

    //setters
    public void setName(String name) {this.name = name;}
    public void setPartySize(int partySize) {this.partySize = partySize;}

    //checks if all names are unique
    public boolean uniqueName(List<Party> p) {
        for (int i = 0; i < p.size(); i++) {
            if(p.get(i).getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Party name: " + getName() + "\n" + "Party Size: " + getPartySize();
    }
}
