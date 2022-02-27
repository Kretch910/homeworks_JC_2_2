package com.company;

import java.util.*;
import java.util.stream.Collectors;

import static com.company.Education.HIGHER;
import static com.company.Sex.MAN;
import static com.company.Sex.WOMAN;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        int count = (int) persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетних: " + count);

        List<String> conscriptsList = persons.stream()
                .filter(person -> person.getSex() == MAN)
                .filter(person -> person.getAge() > 17)
                .filter(person -> person.getAge() < 28)
                .map(person -> person.getName())
                .collect(Collectors.toList());

        List<Person> peopleList = persons.stream()
                .filter(person -> person.getEducation() == HIGHER)
                .filter(person -> person.getAge() > 17)
                .filter(person -> person.getAge() < 60 && person.getSex() == WOMAN || person.getAge() < 65 && person.getSex() == MAN)
                .sorted(Comparator.comparing(person -> person.getName()))
                .collect(Collectors.toList());
    }
}


