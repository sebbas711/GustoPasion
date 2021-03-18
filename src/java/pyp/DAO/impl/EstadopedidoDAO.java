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

    public static final Integer ID_ESTADO_SOLICITADO = 1;
    public static final Integer ID_ESTADO_PREPARANDO = 2;
    public static final Integer ID_ESTADO_ANULADO = 3;
    public static final Integer ID_ESTADO_PREPARADO = 4;

    public EstadopedidoDAO() {
        super(Estadopedido.class);
    }

    @Override
    public Estadopedido findEstadoSolicitado() {
        return getEntityManager()
                .find(entityClass, ID_ESTADO_SOLICITADO);
    }

    @Override
    public Estadopedido findEstadoPreparando() {
        return getEntityManager()
                .find(entityClass, ID_ESTADO_PREPARANDO);
    }

    @Override
    public Estadopedido findEstadoAnulado() {
        return getEntityManager()
                .find(entityClass, ID_ESTADO_ANULADO);
    }

    @Override
    public Estadopedido findEstadoPreparado() {
        return getEntityManager()
                .find(entityClass, ID_ESTADO_PREPARADO);
    }

}
