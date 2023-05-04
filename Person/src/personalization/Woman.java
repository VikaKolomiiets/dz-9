package personalization;
import java.time.LocalDate;


public class Woman extends Person{
    private static final int RETIRED_AGE = 60;

    public Woman(String firstName, String lastName, LocalDate dateOfBirth) throws Exception {
        super(firstName, lastName, dateOfBirth);
    }

    public void createFamily(Man newHusband, boolean isChangeLastName, boolean isChangeLastNameNewHusband) throws Exception {
       createFamilyInner(newHusband, isChangeLastName, isChangeLastNameNewHusband);
    }
    public void giveBabyBirth(Person child) throws Exception {
        this.addChild(child);
        if (this.getStatus().equals(Status.IS_MARRIED)){
            this.getPartner().addChild(child);
            child.setLastName(this.getPartner().getLastName());
        } else {
            child.setLastName(this.getLastName());
        }
    }
    public void adoptChild(Person child) throws Exception {
        adoptChildInner(child);
        child.setLastName(this.getPartner().getLastName());
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
            case IS_MARRIED -> stringBuilder.append(" She is married with " + this.getPartner().getFirstName() + " " + this.getPartner().getLastName());
            case SINGLE -> stringBuilder.append(" She is single.");
            case IS_DIVORCED -> stringBuilder.append(" She is divorced.");
            case WIDOWED -> stringBuilder.append(" She is widowed.");
        }
        if(this.getChildren().size() > 0){
            stringBuilder.append(" and she has " + this.getChildren().size() + (this.getChildren().size() == 1 ? " child.": " children."));
        }
        if (isRetired()){
            int period = this.getFullAge() - RETIRED_AGE;
            stringBuilder.append(" She has been retired for " + period + (period == 1? " year": " years"));
        }
        return stringBuilder.toString();
    }
}
