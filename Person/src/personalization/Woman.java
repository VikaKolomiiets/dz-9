package personalization;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Woman extends Person{

    private static final int RETIRED_AGE = 60;


    public Woman(String firstName, String lastName, LocalDate dateOfBirth) throws Exception {
        super(firstName, lastName, dateOfBirth);
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
    public void giveBabyBirth(Man child){
        this.getChildren().add(child);
        if (this.getStatus().equals(Status.IS_MARRIED)){
            this.getPartner().getChildren().add(child);
        }
    }
    public void giveBabyBirth(Woman child){
        this.getChildren().add(child);
        if (this.getStatus().equals(Status.IS_MARRIED)){
            this.getPartner().getChildren().add(child);
        }
    }


    @Override
    public String getFullInformation() {
        StringBuilder stringBuilder = new StringBuilder(this.getFirstName() + " " + this.getLastName() + " is " + this.getFullAge() + " years old.");
        switch ( this.getStatus() ){
            case IS_MARRIED -> stringBuilder.append(" She is married with " + this.getPartner().getFirstName() + " " + this.getPartner().getLastName());
            case SINGLE -> stringBuilder.append(" She is single.");
            case IS_DIVORCED -> stringBuilder.append(" She is divorced.");
            case WIDOWED -> stringBuilder.append(" She is widowed.");
        }
        if(this.getChildren().size() > 0){
            stringBuilder.append(" And she has " + this.getChildren().size() + ".");
        }
        if (isRetired()){
            stringBuilder.append(" She has been retired for " + (this.getFullAge() - RETIRED_AGE) + ".");
        }
        return stringBuilder.toString();
    }


}
