/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO;

import java.util.List;
import javax.ejb.Local;
import pyp.modelo.entidades.Estadopedido;

/**
 *
 * @author PC
 */
@Local
public interface IEstadopedidoDAO extends DAO<Estadopedido> {

    public Estadopedido findEstadoSolicitado();

    public Estadopedido findEstadoPreparando();
    
    public Estadopedido findEstadoAnulado();

    public Estadopedido findEstadoPreparado();

}
