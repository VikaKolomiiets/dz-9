package personalization;
import java.time.LocalDate;


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
        StringBuilder stringBuilder = new StringBuilder(this.getFirstName() + " " + this.getLastName());
        if (this.getDateOfDeath() == null ){
            stringBuilder.append(this.getFullInformationForAliveMan());
        } else {
            stringBuilder.append(getFullInformationForDiedMan());
        }
        return stringBuilder.toString();
    }

    private String getFullInformationForAliveMan(){
        StringBuilder stringBuilder = new StringBuilder(" is " + this.getFullAge() + " years old.");
        switch ( this.getStatus() ){
            case IS_MARRIED -> stringBuilder.append(" He is married with " + this.getPartner().getFirstName() + " " + this.getPartner().getLastName());
            case SINGLE -> stringBuilder.append(" He is single.");
            case IS_DIVORCED -> stringBuilder.append(" He is divorced.");
            case WIDOWED -> stringBuilder.append(" He is widowed.");
        }
        if(this.getChildren().size() > 0){
            stringBuilder.append(" He has " + this.getChildren().size() + (this.getChildren().size() == 1 ? " child.": " children."));
        }
        if (isRetired()){
            int period = this.getFullAge() - RETIRED_AGE;
            stringBuilder.append(" He has been retired for " + period + (period == 1? " year": " years"));
        }
        return stringBuilder.toString();
    }
    private String getFullInformationForDiedMan(){
        StringBuilder stringBuilder = new StringBuilder(
                " died in  "
                + this.getDateOfDeath().getYear()
                + " at the age of "
                + this.getFullAge() + " years old.");
        switch ( this.getStatus() ){
            case IS_MARRIED -> stringBuilder.append(" He was married with " + this.getPartner().getFirstName() + " " + this.getPartner().getLastName() + ".");
            case SINGLE -> stringBuilder.append(" He was single.");
            case IS_DIVORCED -> stringBuilder.append(" He was divorced.");
            case WIDOWED -> stringBuilder.append(" He was widowed.");
        }
        if(this.getChildren().size() > 0){
            stringBuilder.append(" He had " + this.getChildren().size() + (this.getChildren().size() == 1 ? " child.": " children."));
        }
        return stringBuilder.toString();
    }
}
