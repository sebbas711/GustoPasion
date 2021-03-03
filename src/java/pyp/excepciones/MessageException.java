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
    BE_USUARIO_NOT_HAS_ROLES("0003", "Usuario sin roles", "Debe esperar que se le asigne un rol.", ExceptionType.WARNING),
    
    BE_TIPO_INVENTARIO_ERROR("0012", "Error buscando tipo de inventario", "Debe contactar al administrador para activar su usuario.", ExceptionType.ERROR),
    BE_ESTADO_PEDIDO_ERROR("0012", "Error buscando estado", "Contactese con el Administrador", ExceptionType.ERROR),
    BE_ESTADO_PQRS_ERROR("0012", "Error buscando estado", "Contactese con el Administrador", ExceptionType.ERROR),
    
    BE_PEDIDO_VACIO("0021", "Error validación", "El pedido no puede estar vacio", ExceptionType.WARNING),
    BE_PEDIDO_SIN_PRODUCTOS("0022", "Error validación", "El pedido debe contener por lo menos un producto", ExceptionType.WARNING),
    BE_PEDIDO_SIN_CANTIDAD("0023", "Error validación", "El producto %s debe tener una cantidad mayor a 0", ExceptionType.WARNING),
    BE_PEDIDO_SIN_VALOR_UNITARIO("0024", "Error validación", "El producto %s debe tener relacionado el valor unitario", ExceptionType.WARNING),
    BE_ERROR_REGISTRAR_PEDIDO("0025", "Error registrar pedido", "Se ha presentado un error al registar el pedio", ExceptionType.ERROR),
    BE_PEDIDO_INSUMO_BAJO("0026", "Error validación", "El pedido no puede realizar porque no hay suficientes insumos", ExceptionType.WARNING);

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
