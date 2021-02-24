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
public enum MessageException {
    
    BE_USUARIO_NO_EXISTE("0001", "Datos Incorrectos", "Verifique sus datos y vuelva a intentarlo", ExceptionType.WARNING),
    BE_USUARIO_INACTIVO("0002", "Usuario Inactivo", "Debe contactar al administrador para activar su usuario.", ExceptionType.WARNING),
    
    BE_TIPO_INVENTARIO_ERROR("0012", "Error buscando tipo de inventario", "Debe contactar al administrador para activar su usuario.", ExceptionType.ERROR),
    BE_ESTADO_PEDIDO_ERROR("0012", "Error buscando estado", "Contactese con el Administrador", ExceptionType.ERROR);
    
    String code;
    String message;
    String detail;
    ExceptionType type;

    private MessageException(String code, String message, String detail, ExceptionType type) {
        this.code = code;
        this.message = message;
        this.detail = detail;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public ExceptionType getType() {
        return type;
    }
    
}
