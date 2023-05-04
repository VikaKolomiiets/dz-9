package personalization;

import java.util.Date;
import java.util.UUID;

public abstract class Person {
    private final Date MIN_DATE = new Date(1900, 01, 01);
    private final Date MAX_DATE = new Date(2023, 05, 04);
    private UUID id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public Person(String firstName, String lastName, Date dateOfBirth) throws Exception {
        if (firstName == null || firstName.length() < 2) {
            throw new Exception("Name cannot be a null, or it length have to contain more than 1 letter.");
        }
        if (lastName == null || lastName.length() < 2) {
            throw new Exception("Name cannot be a null, or it length have to contain more than 1 letter.");
        }
        if (dateOfBirth == null){
            throw new Exception("Date of Birth can not be a null");
        }

        if (dateOfBirth.before(MIN_DATE) || dateOfBirth.after(MAX_DATE)){
             throw new Exception("Date of Birth is out of DateTime range");
        }
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;

    }

}
