package personalization;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Woman extends Person{

    private static final int RETIRED_AGE = 60;
    private List<Person> children;

    public Woman(String firstName, String lastName, LocalDate dateOfBirth) throws Exception {
        super(firstName, lastName, dateOfBirth);
        children = new ArrayList<Person>();
    }

    @Override
    public boolean isRetired() {
        if(getFullAge() >= RETIRED_AGE){
            return true;
        }
        return false;
    }

    public void createFamily(Man newHusband, boolean isChangeLastName, boolean isChangeLastNameNewHusband) throws Exception {
       createFamilyInner(newHusband, isChangeLastName, isChangeLastNameNewHusband);
    }
    public void giveBirthBoy(Man child){
        children.add(child);
    }
    public void giveBirthGirl(Woman child){
        children.add(child);
    }

    public List<Person> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        
        Integer numberOfChilgren = this.children.size();
        if(this.getStatus().equals(Status.IS_MARRIED)){
            return this.getFirstName() + " " + this.getLastName() + " is married with " + this.getPartner().getFirstName() + " " + this.getPartner().getLastName();
        }
        return this.getFirstName() + " " + this.getLastName() + " is " + this.getStatus();
    }
}
