import domain.entities.Alumne;
import domain.entities.Curs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


public class Application implements Runnable {
    @Override
    public void run() {

        List<Alumne> alumnes = getAlumnes();

        // NIVEL 2



        // EJERCICIO 1

        // Crea una llista de cadenes amb noms propis. Escriu un mètode que retorna una llista de totes les cadenes que comencen
        // amb la lletra 'A' (majúscula) i  tenen exactament 3 lletres. Imprimeix el resultat.

        Predicate<Alumne> startsWithCapitalA = alumne -> alumne.nom().startsWith("A");
        Predicate<Alumne> threeLettersLong = alumne -> alumne.nom().length() == 3;
        alumnes.add(new Alumne("Ali",21, Curs.JAVA, 5.5));
        List<String> nombresFiltrados = alumnes.stream()
                .filter(startsWithCapitalA
                        .and(threeLettersLong))
                .map(Alumne::nom)
                .toList();



        System.out.println("Alumnos que empiezan por A y cuyos nombres tienen exactamente tres letras:");
        for (String s: nombresFiltrados) System.out.println(s);
        System.out.println();






        // EJERCICIO 2

        // Programa un mètode que retorna una cadena separada per comes, basada en una llista d’Integers.
        // Cada element ha d’anar precedit per lletra “e” si el nombre és parell, o per la lletra “o” si el nombre és imparell.
        // Per exemple, si la llista d’entrada és (3, 55, 44), la sortida ha de ser “o3, o55, e44”. Imprimeix el resultat.

        System.out.println("Ejercicio 2 del Nivel 2");
        System.out.println(metodoDelEjercicioDos(3, 55, 44));
        System.out.println();






        // Crea una Functional Interface que contingui un mètode anomenat operacio(). Aquest mètode ha de retornar un float.
        // Injecta a la interfície creada mitjançant una lambda, el cos del mètode, de manera que es pugui transformar l’operació amb una suma,
        // una resta, una multiplicació i una divisió.

        System.out.println();
        ArithmeticOperation<Number> arithmeticOperation = new ArithmeticOperation<>((operand1, operand2) -> operand1.floatValue() + operand2.floatValue());
        System.out.println("Resultado de la suma: " + arithmeticOperation.operation(3, 8) );

        // FIN DEL NIVEL 2






        // NIVEL 3

        // Crea una classe Alumne amb els atributs: Nom, edat, curs i nota.
        //
        // Omple una llista amb 10 alumnes
        //
        //    Mostra per pantalla el nom i l’edat de cada alumne/a.
        //    Filtra la llista per tots els alumnes que el seu nom comença per ‘a’.
        //    Assigna a aquests alumnes a una altra  llista i  mostra per pantalla la nova llista (tot  amb lambdes).
        //    Filtra i mostra per pantalla els alumnes que tenen una nota de 5 o superior.
        //    Filtra i mostra per pantalla els alumnes que tenen un nota de 5 o més, i que no són de PHP.
        //    Mostra tots els alumnes que fan JAVA i són majors d’edat.

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




    private String metodoDelEjercicioDos(int i, int i1, int i2) {

        Predicate<Integer> isEven = integer -> (integer % 2) == 0;

        return List.of(i, i1, i2).stream()
                .map(integer -> isEven.test(integer) ? "e" + integer : "o" + integer)
                .reduce("", (acc, s) -> acc + s + ", ");
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
