/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pyp.DAO.IClienteDAO;
import pyp.modelo.entidades.Cliente;
import pyp.util.MessageUtil;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class BuscarClienteControlador implements Serializable{

    @EJB
    private IClienteDAO clienteDao;
    private Cliente cliente;
    private Integer numeroDocumento;
    
    public void buscar(){
        cliente = clienteDao.find(numeroDocumento);
        if(Objects.isNull(cliente)){
            MessageUtil.sendErrorModal("Error validación cliente", "Cliente no encontrado, verifique sus datos o registro primero.");
        }
    }
    
    public boolean existeCliente(){
        return Objects.nonNull(cliente);
    }
    
    public void limpiar(){
        cliente= null;
        numeroDocumento = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    
    
}
