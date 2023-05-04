import personalization.Man;
import personalization.Person;
import personalization.Woman;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws Exception {

        Woman ann = new Woman("Ann", "Kingtom", LocalDate.of(1965, 12, 01));
        Woman kate = new Woman("Kate", "LastName", LocalDate.of(1982, 01, 28));
        Man nik = new Man("Nikola", "Bange", LocalDate.of(1949, 07, 01));
        Man georg = new Man("George", "LastName", LocalDate.of(1992, 01, 01));
        nik.createFamily( ann, false, true);
        ann.giveBabyBirth(kate);
        nik.adoptChild(georg);
        //ann.passAwayPartner(true);
        System.out.println(ann.getFullInformation());
        System.out.println(nik.getFullInformation());
        System.out.println(kate.getFullInformation());
        System.out.println(georg.getFullInformation());
        nik.passAway(LocalDate.of(2022, 12,01));
        System.out.println(ann.getFullInformation());
        System.out.println(nik.getFullInformation());
    }
}