package personalization;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Person {
    private final LocalDate MIN_DATE = LocalDate.of(1900, 01, 01);
    private LocalDate maxDate = LocalDate.now();
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Person(String firstName, String lastName, LocalDate dateOfBirth) throws Exception {
        if (firstName == null || firstName.length() < 2) {
            throw new Exception("Name cannot be a null, or it length have to contain more than 1 letter.");
        }
        if (lastName == null || lastName.length() < 2) {
            throw new Exception("Name cannot be a null, or it length have to contain more than 1 letter.");
        }
        if (dateOfBirth == null){
            throw new Exception("Date of Birth can not be a null");
        }
        if (dateOfBirth.isBefore(MIN_DATE) || dateOfBirth.isAfter(maxDate)){
             throw new Exception("Date of Birth is out of the date range");
        }
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    public abstract int getFullAge();
    public abstract void isRetired();
}
