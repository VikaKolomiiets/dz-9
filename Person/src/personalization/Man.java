package personalization;

import java.time.LocalDate;
import java.util.List;

public class Man extends Person{

    private static final int RETIRED_AGE = 65;
    private List<Person> partners;

    public Man(String firstName, String lastName, LocalDate dateOfBirth) throws Exception {
        super(firstName, lastName, dateOfBirth);
    }

    @Override
    public boolean isRetired() {
        if(getFullAge() >= RETIRED_AGE){
            return true;
        }
        return false;
    }

    public void createFamily(Woman newWife, boolean isChangeLastName, boolean isChangeLastNameNewWife) throws Exception {
        createFamilyInner(newWife, isChangeLastName, isChangeLastNameNewWife);
    }
}
