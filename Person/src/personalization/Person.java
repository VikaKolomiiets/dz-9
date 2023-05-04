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
    private LocalDate maxDate = LocalDate.now();
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
        return (int)ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
    }
    public void divorce(boolean isBackLastName, boolean isBackLastNamePartner) throws Exception {
        if (this.getStatus() != Status.IS_MARRIED){
            throw new Exception(this.firstName + " " + this.lastName + " doesn't hava the status 'is married'");
        }
        this.getPartner().setStatus(Status.IS_DIVORCED);
        this.setStatus(Status.IS_DIVORCED);
        backToBirthLastName(isBackLastName);
        backPartnerToBirthLastName(isBackLastNamePartner);
        this.getPartner().setPartner(null);
        this.setPartner(null);
    }
    public void passAwayPartner(boolean isBackLastName) throws Exception {
        if(this.getStatus() != Status.IS_MARRIED){
            throw new Exception(this.firstName + " " + this.lastName + " doesn't hava the status 'is married'");
        }
        if (this.getPartner().getDateOfDeath() == null){
            throw new Exception("The death of " + this.getPartner().getFirstName() + " " + this.getPartner().getLastName() + "is not recorded in DataBase" );
        }
        this.setStatus(Status.WIDOWED);
        this.setPartner(null);
        backToBirthLastName(isBackLastName);
    }
    private void backToBirthLastName(boolean isBack) throws Exception {
        if (isBack && (!this.getLastName().equals(this.getBirthLastName()))){
            this.setLastName(this.getBirthLastName());
        }
    }
    private void backPartnerToBirthLastName(boolean isBack) throws Exception {
        if(isBack && (!this.getPartner().getBirthLastName().equals(this.getPartner().getLastName()))){
            this.getPartner().setLastName(this.getPartner().getBirthLastName());
        }
    }
    protected void adoptChildInner(Person child) throws Exception {
        if (!this.getStatus().equals(Status.IS_MARRIED)) {
            throw new Exception("Status have to be 'Married' for adopting child");
        }
        this.addChild(child);
        this.getPartner().addChild(child);
    }
    protected void createFamilyInner(Person newPartner, boolean isChangeLastName, boolean isChangeLastNameNewPartner) throws Exception {
        if (newPartner == null){
            throw new Exception("Partner cannot be a null");
        }
        checkMarried(this);
        checkMarried(newPartner);
        if (isChangeLastName && isChangeLastNameNewPartner) {
            throw new Exception("The Last name can be changed for both partners at one time");
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
    protected void addChild(Person child) throws Exception {
        if (child == null){
            throw new Exception("Child can not be a null");
        }
        if((int)ChronoUnit.YEARS.between(this.dateOfBirth, child.dateOfBirth) <= MIN_YEARS_BETWEEN_PARENT_CHILD){
            throw new Exception("Parent have to be older than child on 12 years");
        }
        this.children.add(child);
    }
    protected void checkMarried(Person person) throws Exception {
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
        if (date.isBefore(MIN_DATE) || date.isAfter(maxDate)){
            throw new Exception("Date of Birth is out of the range of dates");
        }
    }

    //region Getter&Setter
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
    public void setDateOfBirth(LocalDate dateOfBirth) throws Exception {
        this.checkDate(dateOfBirth);
        this.dateOfBirth = dateOfBirth;
    }
    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }
// How to inform the Partner about Death and switch their status?
    public void setDateOfDeath(LocalDate dateOfDeath) throws Exception {
        this.checkDate(dateOfDeath);
        this.dateOfDeath = dateOfDeath;
    }
    public UUID getId() {
        return this.id;
    }
    public List<Person> getChildren() {
        return children;
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



//endregion

}
