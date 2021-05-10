package sit.int221.kaofood.exeption;

public class productException extends RuntimeException{
    exceptionResponse.ERROR_CODE errorCode;
    public productException(exceptionResponse.ERROR_CODE errorCode, String s){
        super(s);
        this.errorCode = errorCode;
    }
    public exceptionResponse.ERROR_CODE getErrorCode() {
        return this.errorCode;
    }
    }

