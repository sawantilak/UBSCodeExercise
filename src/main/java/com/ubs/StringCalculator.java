package com.ubs;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides an add method to calculate the sum of numbers provides as a delimited string.
 * This class focuses only on the logic and ignores error checking code as mentioned in the exercise requirement.
 * This class also does not try to implement a parallel add computation
 */
public class StringCalculator {

    /**
     *
     * @param numbers Delimited String containing numbers. Supports custom and multiple delimiters.
     * @return Sum of the numbers in the input String
     * @throws NumberFormatException Will be thrown in case of bad input string format (for non-numbers).
     */
    public int add(String numbers) throws NumberFormatException, NegativeNumberException {
        if(numbers == null || numbers.length() == 0)
            return 0;

        if (delimiterExists(numbers))
            return withDelimiters(numbers);
        else {
            numbers = numbers.replaceAll("\n",",");
            return computeAdd(Arrays.asList(numbers.split(",")));
        }

    }

    private boolean delimiterExists(String numbers) {
        if(numbers.startsWith("//"))
            return true;
        return false;
    }

    private int withDelimiters(String input) throws NumberFormatException, NegativeNumberException {
        Pair<List<String>, String> output = extractDelimitersAndNumbersString(input);
        List<String> delimiters = output.getValue0();
        String onlyNumbers = output.getValue1();

        /*
            The below computation can be done using reductions or collectors, but feels like an overkill.
            The nature of this computation is more sequential and the requirement/data_size also does not hint at
            a parallel version. Assuming that we only work with sequential version to apply delimiters one after the other,
            and the fact that the container (arrayList) has to be mutated over every iteration of delimiter, using reduction didn't make sense.
            So, I took a more simpler/readable and imperative style approach here.
         */
        List<String> result = Arrays.asList(onlyNumbers);
        for(String del : delimiters) {
            List<String> tempResult = new ArrayList<>();
            for(String numbersplit: result) {
                tempResult.addAll(Arrays.asList(numbersplit.split(del)));
            }
            result = tempResult;
        }

        return computeAdd(result);
    }

    private Pair<List<String>, String> extractDelimitersAndNumbersString(String input) {
        String[] splits = input.split("\n");
        String delim = splits[0].substring(2, splits[0].length());
        String[] delims =  delim.split("\\|");

        List<String> delimsEscaped = Stream.of(delims)
                .map(e -> e.chars()
                        .mapToObj(e1 -> (char) e1)
                        .map(String::valueOf)
                        .collect(Collectors.joining("\\", "\\", "")))
                .collect(Collectors.toList());

        return Pair.with(delimsEscaped, splits[1]);
    }


    private int computeAdd(List<String> numbers) throws NegativeNumberException, NumberFormatException {
        int[] negatives = numbers.stream()
                .mapToInt(e -> Integer.parseInt(e))     // Can throw NumberFormatException
                .filter(e -> e < 0).toArray();

        if(negatives.length > 0)
            throw new NegativeNumberException("Negatives not allowed: "+Arrays.stream(negatives).mapToObj(Integer::toString)
                    .collect(Collectors.joining(",")));

        return numbers.stream()
                .mapToInt(e -> Integer.parseInt(e))
                .filter(e -> e<= 1000 && e>= 0)
                .sum();
    }

}
