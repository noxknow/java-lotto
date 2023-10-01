package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

import static lotto.handler.ErrorHandler.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);

        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw INVALID_COUNT.getException();
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (numbers.size() != uniqueNumbers.size()) {
            throw DUPLICATE_NUMBER.getException();
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number -> number < 1 || number > 45)) {
            throw INVALID_RANGE.getException();
        }
    }

    public List<Integer> sortLottoNumbers() {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
