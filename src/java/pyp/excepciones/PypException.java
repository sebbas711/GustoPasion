/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.excepciones;

/**
 *
 * @GAES 1
 */
public class PypException extends Exception{
    
    private String details;
    private String code;
    private ExceptionType type;

    public PypException(String message, String details, String code, ExceptionType type) {
        super(message);
        this.details = details;
        this.code = code;
        this.type = type;
    }

    public PypException(String message, String details, String code, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.details = details;
        this.code = code;
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ExceptionType getType() {
        return type;
    }

    public void setType(ExceptionType type) {
        this.type = type;
    }
    
}
