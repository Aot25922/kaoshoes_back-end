package sit.int221.kaofood.exeption;

import java.time.LocalDateTime;

public class exceptionResponse {
    public static enum ERROR_CODE {
        PRODUCT_CANNOT_CREATE(1001),FILE_NOT_FOUND(2001),ERROR_TO_SAVE_FILE(3001),SAVE_OLD_EXTENSION(4001),ERROR_TO_DELETE_FILE(5001);
        private int value;

        ERROR_CODE(int value) {
            this.value = value;
        }
    }

    private ERROR_CODE errorCode;
    private String message;
    private LocalDateTime dateTime;


    public exceptionResponse(ERROR_CODE errorCode, String message, LocalDateTime dateTime) {
        this.errorCode = errorCode;
        this.message = message;
        this.dateTime = dateTime;
    }

    public ERROR_CODE getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
