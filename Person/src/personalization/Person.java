package personalization;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Person {
    private static final LocalDate MIN_DATE = LocalDate.of(1900, 01, 01);
    private final String BIRTH_LAST_NAME;
    private final int MIN_YEARS_BETWEEN_PARENT_CHILD = 12;
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private Person partner;
    private Status status;
    private List<Person> children;

    public Person(String firstName, String lastName, LocalDate dateOfBirth) throws Exception {
        this.checkName(firstName);
        this.checkName(lastName);
        this.checkDate(dateOfBirth);
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.BIRTH_LAST_NAME = lastName;
        this.dateOfBirth = dateOfBirth;
        this.partner = null;
        this.status = Status.SINGLE;
        this.dateOfDeath = null;
        children = new ArrayList<>();
    }
    public abstract boolean isRetired();
    public abstract String getFullInformation();
    public int getFullAge(){
        if (this.getDateOfDeath() == null){
            return (int)ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
        } else {
            return (int)ChronoUnit.YEARS.between(dateOfBirth, dateOfDeath);
        }
    }

    //todo create function revert
    public void divorce(boolean isBackLastName, boolean isBackLastNamePartner) throws Exception {
        if (this.getStatus() != Status.IS_MARRIED){
            throw new Exception(this.firstName + " " + this.lastName + " doesn't hava the status 'is married'");
        }
        this.getPartner().setStatus(Status.IS_DIVORCED);
        this.setStatus(Status.IS_DIVORCED);
        if(isBackLastNamePartner) {
            backToBirthLastName(this.getPartner());
        }
        if(isBackLastName) {
            backToBirthLastName(this);
        }

        this.getPartner().setPartner(null);
        this.setPartner(null);
    }
    public void passAway(LocalDate dateOfDeath) throws Exception {
        this.checkDate(dateOfDeath);
        if(getDateOfBirth().isBefore(dateOfDeath)){
            throw new Exception("Date of Death out of range");
        }
        this.setDateOfDeath(dateOfDeath);
        if(this.getStatus() != Status.IS_MARRIED){
            return;
        }
        this.partner.setStatus(Status.WIDOWED);
        this.setPartner(null);
        this.partner.setPartner(null);
    }

    protected void createFamilyInner(Person newPartner, boolean isChangeLastName, boolean isChangeLastNameNewPartner) throws Exception {
        if (newPartner == null){
            throw new Exception("Partner cannot be a null");
        }
        checkMarried(this);
        checkMarried(newPartner);
        if (isChangeLastName && isChangeLastNameNewPartner) {
            throw new Exception("The Last name cannot be changed for both partners at one time");
        }
        this.setStatus(Status.IS_MARRIED);
        newPartner.setStatus(Status.IS_MARRIED);
        this.setPartner(newPartner);
        newPartner.setPartner(this);
        if (isChangeLastName) {
            this.setLastName(newPartner.getLastName());
        }
        if (isChangeLastNameNewPartner){
            newPartner.setLastName(this.getLastName());
        }
    }
    protected void adoptChildInner(Person child) throws Exception {
        if (!this.getStatus().equals(Status.IS_MARRIED)) {
            throw new Exception("Status have to be 'Married' for adopting child");
        }
        //todo make check child to age for both parents
        this.addChild(child);
        this.getPartner().addChild(child);
    }
    protected void addChild(Person child) throws Exception {
        if (child == null){
            throw new Exception("Child can not be a null");
        }
        if((int)ChronoUnit.YEARS.between(this.dateOfBirth, child.dateOfBirth) <= MIN_YEARS_BETWEEN_PARENT_CHILD){
            throw new Exception("Parent have to be older than child on 12 years");
        }
        this.children.add(child);
    }

    private void checkMarried(Person person) throws Exception {
        if (person.getStatus() == Status.IS_MARRIED){
            throw new Exception(person.getFirstName() + " " + person.getLastName() +" can not married twice!");
        }
    }
    private void checkName(String name) throws Exception {
        if (name == null) {
            throw new Exception("Name cannot be a null.");
        }
        if (name.length() < 2) {
            throw new Exception("Name length have to contain more than one letter.");
        }
    }
    private void checkDate(LocalDate date) throws Exception {
        if (date == null){
            throw new Exception("Date of Birth can not be a null");
        }
        if (date.isBefore(MIN_DATE) || date.isAfter(LocalDate.now())){
            throw new Exception("Date of Birth is out of the range of dates");
        }
    }
    private void backToBirthLastName(Person person) throws Exception {
        if ((!person.getLastName().equals(person.getBirthLastName()))){
            person.setLastName(person.getBirthLastName());
        }
    }

    //region Getter&Setter
    public UUID getId() {
        return this.id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) throws Exception {
        this.checkName(firstName);
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) throws Exception {
        this.checkName(lastName);
        this.lastName = lastName;
    }
    public String getBirthLastName() {
        return BIRTH_LAST_NAME;
    }
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }
    public Person getPartner() {
        return this.partner;
    }
    public Status getStatus() {
        return status;
    }
    protected void setPartner(Person partner) {
        this.partner = partner;
    }
    protected void setStatus(Status status) {
        this.status = status;
    }
    protected void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
    protected List<Person> getChildren() {
        return children;
    }
    //endregion
}
