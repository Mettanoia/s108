import domain.entities.Alumne;
import domain.entities.Curs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

/**
 * <p>Crea una classe Alumne amb els atributs: Nom, edat, curs i nota.</p>
 *
 * <p>Omple una llista amb 10 alumnes.</p>
 *
 * <p>Mostra per pantalla el nom i l’edat de cada alumne/a.
 * Filtra la llista per tots els alumnes que el seu nom comença per ‘a’. Assigna a aquests alumnes a una altra  llista i  mostra per pantalla la nova llista (tot  amb lambdes).
 * Filtra i mostra per pantalla els alumnes que tenen una nota de 5 o superior.
 * Filtra i mostra per pantalla els alumnes que tenen un nota de 5 o més, i que no són de PHP.
 * Mostra tots els alumnes que fan JAVA i són majors d’edat.</p>
 */
public class Application implements Runnable {
    @Override
    public void run() {

        List<Alumne> alumnes = getAlumnes();

        System.out.println("All alumnes whose name start with \"a\"");
        System.out.println();
        // Filter all those alumnes that start with "a" and print them in the standard output. Then convert to List.
        List<Alumne> alumnesThatStartWithA = alumnes.stream()
                .filter(e -> e.nom().startsWith("A"))
                .peek(System.out::println)
                .toList();

        System.out.println();System.out.println();

        System.out.println("All alumnes whose score is greater than 5");
        System.out.println();
        // Print all those alumnes whose score is greater than 5.
        alumnes.stream()
                .filter(e -> e.nota() > 5.0)
                .forEach(System.out::println);

        // The logical conjunction: The score of the alumne is greather than 5 AND The alumne is not studying Java
        Predicate<Alumne> greaterThan5AndNotPHP = (Alumne e) -> e.curs() != Curs.PHP && e.nota() > 5;

        System.out.println();
        System.out.println();

        System.out.println("All alumnes whose score is greater than 5 and are NOT studying PHP");
        System.out.println();
        // Print all alumnes that satisfy the previous predicate
        alumnes.stream()
                .filter(greaterThan5AndNotPHP)
                .forEach(System.out::println);

        // The logical conjunction: The alumne's age is greater than 18 AND The alumne is studying Java.
        Predicate<Alumne> olderThan18AndJava = e -> e.edat() > 18 && e.curs() == Curs.JAVA;

        System.out.println();
        System.out.println();

        System.out.println("All alumnes whose age is greater than 18 and are studying JAVA");
        System.out.println();

        //Print all alumnes that satisfy the previous predicate.
        alumnes.stream()
                .filter(olderThan18AndJava)
                .forEach(System.out::println);

    }

    private static List<Alumne> getAlumnes() {

        Random random = new Random();
        String[] possibleNames = {"Alice", "Bob", "Charlie", "David", "Emma", "Anthony", "Grace", "Henry", "Isabella", "Jack"};

        List<Alumne> alumnes = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            alumnes.add(new Alumne(possibleNames[i],
                            random.nextInt(40) + 18, random.nextBoolean() ? Curs.PHP : Curs.JAVA,
                            random.nextDouble(3) + random.nextDouble(8)
                    )
            );

        return alumnes;
    }
}
