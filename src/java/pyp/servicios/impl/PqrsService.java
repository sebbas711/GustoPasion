/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.impl;

import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.IEstadopqrsDAO;
import pyp.DAO.IPqrsDAO;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;
import pyp.modelo.entidades.Estadopqrs;
import pyp.modelo.entidades.Pqrs;
import pyp.servicios.IPqrsService;

@Stateless
public class PqrsService implements IPqrsService {

    @EJB
    private IEstadopqrsDAO estadoPqrsDao;
    @EJB
    private IPqrsDAO pqrsDao;

    @Override
    public List<Estadopqrs> getEstadoPqrsHabilitados() throws BusinessException {
        try {
            return estadoPqrsDao.findAll();
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_ESTADO_PEDIDO_ERROR, e);
        }
    }

    @Override
    public List<Pqrs> findPqrsByFilter(Estadopqrs estadoPqrsFiltro) throws BusinessException {
        try {
            if (Objects.isNull(estadoPqrsFiltro)) {
                return pqrsDao.findAll();
            }
            return pqrsDao.findByEstadoPqrs(estadoPqrsFiltro);
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_ESTADO_PEDIDO_ERROR, e);
        }
    }

    @Override
    public List<Pqrs> findPqrsByAdmin(Integer idAdministrador, Estadopqrs estadoPqrs) throws BusinessException {
        try {
            if (Objects.isNull(estadoPqrs)) {
                return pqrsDao.findByAdmin(idAdministrador);
            }
            return pqrsDao.findByAdminAndEstado(idAdministrador, estadoPqrs);
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_ESTADO_PEDIDO_ERROR, e);
        }
    }

}
