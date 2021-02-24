/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.DAO.IEstadopedidoDAO;
import pyp.modelo.entidades.Estadopedido;

/**
 *
 * @author PC
 */
@Stateless
public class EstadopedidoDAO extends AbstractDAO<Estadopedido> implements IEstadopedidoDAO {

    public EstadopedidoDAO() {
        super(Estadopedido.class);
    }

}
