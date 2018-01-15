package back.records;

import back.kernel.AbstractRecord;

public class Section extends AbstractRecord {
    private int ID;
    private String name;

    public Section(){

    }

    public Section(String name){
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
