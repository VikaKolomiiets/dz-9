package personalization;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Man extends Person{

    private int age;
    private List<Person> partners;
    private List<Person> children;
    public Man(String firstName, String lastName, LocalDate dateOfBirth) throws Exception {
        super(firstName, lastName, dateOfBirth);
        this.partners = new ArrayList<>();
        this.children = new ArrayList<>();
    }



    @Override
    public void isRetired() {

    }
}
