/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.excepciones;

/**
 *
 * @author Ismael
 */
public class BusinessException extends PypException {
    
    private static final String BASE_CODE = "BE-";
    private MessageException messageException;
    
    
    public BusinessException(MessageException messageException) {
        super(messageException.message, messageException.detail, BASE_CODE.concat(messageException.code), messageException.type);
        this.messageException = messageException;
    }
    
    public BusinessException(MessageException messageException, Throwable throwable) {
        super(messageException.message, messageException.detail, BASE_CODE.concat(messageException.code), messageException.type, throwable);
        this.messageException = messageException;
    }

    public MessageException getMessageException() {
        return messageException;
    }
    
}
