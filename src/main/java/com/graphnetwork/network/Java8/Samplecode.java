package com.graphnetwork.network.Java8;

import java.util.*;
import java.util.stream.Collectors;

public class Samplecode {
    public static void main(String[] args) {
        List<String> employeeData = Arrays.asList("alice", "bob", "anna", "andy", "carl");

        // Filter names starting with 'a' and convert them to uppercase
        ArrayList<String> startsWithA = employeeData.stream()
                .filter(name -> name.startsWith("a"))
                .map(String::toUpperCase) // Transform the data
                .collect(Collectors.toCollection(ArrayList::new));
        List<Integer> distince = Arrays.asList(1, 2, 3, 4, 4, 2, 2);
        ArrayList<Integer> distinct = distince.stream().distinct().
                collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> sorted = distinct.stream()
                .distinct() // Remove duplicates
                .sorted(Comparator.comparingInt(a -> a)) // Sort using a custom comparator (ascending order)
                .collect(Collectors.toCollection(ArrayList::new));
        Optional<Integer> usinnglimi = distinct.stream()
                .distinct() // Remove duplicates
                .sorted(Comparator.reverseOrder()) // Sort in descending order
                .skip(1) // Skip the first 2 elements
                .limit(1) // Limit to 1 element
                .findFirst(); // Get the first element from the stream

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Compute the sum using reduce
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);


        List<Integer> squares = numbers.stream()
                .map(n -> n * n) // Apply transformation
                .collect(Collectors.toList());
    }
}
