package lotto.handler;

public enum ErrorHandler {

    INCONVERTIBLE_TYPE("유효하지 않은 입력입니다."),
    DUPLICATE_NUMBER("로또번호는 중복될 수 없습니다."),
    INVALID_NEGATIVE_INPUT("입력은 음수가 될 수 없습니다."),
    INVALID_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_AMOUNT("입력한 금액이 1,000원으로 나누어 떨어지지 않습니다."),
    INVALID_COUNT("6개의 숫자만 입력해야 합니다.");

    private final String errorMessage;

    ErrorHandler(String message) {
        errorMessage = "[ERROR] " + message;
    }

    public RuntimeException getException() {
        return new IllegalArgumentException(errorMessage);
    }
}
