import personalization.Man;
import personalization.Woman;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) throws Exception {

        Woman ann = new Woman("Ann", "Kingtom", LocalDate.of(1955, 12, 01));
        Woman kate = new Woman("Kate", "Bange", LocalDate.of(1972, 01, 28));
        Man miki = new Man("Nikola", "Bange", LocalDate.of(1949, 07, 01));
        Man georg = new Man("George", "Bange", LocalDate.of(1982, 01, 01));
        Woman zita = new Woman("Zita", "Osnovatkay", LocalDate.of(1998, 12, 05));
        System.out.println(zita.getFullInformation());
        zita.createFamily(georg, true, false);
        System.out.println(zita.getFullInformation());
        miki.createFamily( ann, false, true);
        ann.giveBabyBirth(kate);
        miki.adoptChild(georg);
        georg.divorce(false, true);
        System.out.println(zita.getFullInformation());
        miki.passAway(LocalDate.of(2020, 10, 15));
        System.out.println(ann.getFullInformation());
        System.out.println(miki.getFullInformation());
        System.out.println(kate.getFullInformation());
        System.out.println(georg.getFullInformation());

    }
}