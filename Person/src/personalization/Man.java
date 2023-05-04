package personalization;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Man extends Person{
    private static final int RETIRED_AGE = 65;
    public Man(String firstName, String lastName, LocalDate dateOfBirth) throws Exception {
        super(firstName, lastName, dateOfBirth);
    }

    public void createFamily(Woman newWife, boolean isChangeLastName, boolean isChangeLastNameNewWife) throws Exception {
        createFamilyInner(newWife, isChangeLastName, isChangeLastNameNewWife);
    }
    public void adoptChild(Person child) throws Exception {
        adoptChildInner(child);
        child.setLastName(this.getLastName());
    }
    @Override
    public boolean isRetired() {
        if(getFullAge() >= RETIRED_AGE){
            return true;
        }
        return false;
    }
    @Override
    public String getFullInformation() {
        StringBuilder stringBuilder = new StringBuilder(this.getFirstName() + " " + this.getLastName() + " is " + this.getFullAge() + " years old.");
        switch ( this.getStatus() ){
            case IS_MARRIED -> stringBuilder.append(" He is married with " + this.getPartner().getFirstName() + " " + this.getPartner().getLastName());
            case SINGLE -> stringBuilder.append(" He is single.");
            case IS_DIVORCED -> stringBuilder.append(" He is divorced.");
            case WIDOWED -> stringBuilder.append(" He is widowed.");
        }
        if(this.getChildren().size() > 0){
            stringBuilder.append(" and he has " + this.getChildren().size() + (this.getChildren().size() == 1 ? " child.": " children."));
        }
        if (isRetired()){
            int period = this.getFullAge() - RETIRED_AGE;
            stringBuilder.append(" He has been retired for " + period + (period == 1? "year": "years"));
        }
        return stringBuilder.toString();
    }
}
