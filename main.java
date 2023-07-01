package ree;

import java.util.*;

public class main {
    public static void main(String[] args) {
        // Lees gegevens uit het bestand "studentData.json" en maak een Administratie-object
        Administratie<Student> administratie = Administratie.leesGegevensUitBestand("studentData_v1.json");

        // Haal de studenten op die in de klas "TI2.1" zitten en toon hun namen
        List<Student> studentenInKlas = administratie.getStudentenInKlas("TI3.1");
        System.out.println("Studenten in klas IT1.1:");
        for (Student student : studentenInKlas) {
            System.out.println(student.getNaam());
        }

        // Haal de studenten op die het vak "INF-DS" volgen en toon hun namen
        List<Student> studentenMetVak = administratie.getStudentenMetVak("INF-PROG");
        System.out.println("Studenten met vak INF-PROG:");
        for (Student student : studentenMetVak) {
            System.out.println(student.getNaam());
        }

        // Haal het vakkenpakket op van de student met studentnummer "11134567" en toon de vaknamen
        List<Vak> vakkenpakket = administratie.getVakkenpakketVanStudent("2333378");
        System.out.println("Vakkenpakket van student 11134567:");
        for (Vak vak : vakkenpakket) {
            System.out.println(vak.getNaam());
        }

        // Haal de vakken op die de student met studentnummer "125545" nog moet halen en toon de vaknamen
        List<Vak> nogTeHalenvakken = administratie.getNogTeHalenvanStudent("2333378");
        System.out.println("Nog te halen vakken van student 11134567:");
        for (Vak vak : nogTeHalenvakken) {
            System.out.println(vak.getNaam());
        }

        // Haal de studenten op die het vak "INF-PROG" hebben gehaald en toon hun namen
        List<Student> studentenDieVakHebbenGehaald = administratie.getStudentenMetVak("INF-PROG");
        System.out.println("Studenten die vak INF-PROG hebben gehaald:");
        for (Student student : studentenDieVakHebbenGehaald) {
            System.out.println(student.getNaam());
        }

        // Bereken het gemiddelde cijfer voor het vak "INF-PROG" en toon het resultaat
        double gemiddeldeCijferVoorVak = administratie.getGemiddeldeVak("INF-PROG");
        System.out.println("Gemiddeld cijfer voor vak INF-PROG: " + gemiddeldeCijferVoorVak);

        // Bereken het gewogen gemiddelde voor de student met studentnummer "11134567" en toon het resultaat
        double gewogenGemiddeldeVoorStudent = administratie.getGemiddelde("2333378");
        System.out.println("Gemiddelde voor student 2333378: " + gewogenGemiddeldeVoorStudent);

        // Bereken het percentage studenten dat het vak "INF-PROG" heeft gehaald en toon het resultaat
        double percentageStudentenDieVakHebbenGehaald = administratie.getPercentageStudentenMetVak("INF-PROG");
        System.out.println("Percentage studenten die vak INF-PROG hebben gehaald: " + percentageStudentenDieVakHebbenGehaald);

        // Bereken de voortgang van de studie van de student met studentnummer "125545" en toon het resultaat
        double voortgangStudieVanStudent = administratie.getVoortgangStudie("2333378");
        System.out.println("Voortgang studie van student 2333378: " + voortgangStudieVanStudent);
    }
}
