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
import pyp.DAO.IInsumoDAO;
import pyp.DAO.ITipoInsumoDAO;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.TipoInsumo;
import pyp.servicios.IInventarioService;


@Stateless
public class InventarioService implements IInventarioService {

    @EJB
    private ITipoInsumoDAO tipoInsumoDao;
    @EJB
    private IInsumoDAO insumoDao;

    @Override
    public List<TipoInsumo> getTiposInsumosHabilitados() throws BusinessException {
        try {
            return tipoInsumoDao.findAll();
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_TIPO_INVENTARIO_ERROR, e);
        }
    }

    @Override
    public List<Insumo> findInsumosByFilter(TipoInsumo tipoInsumoFiltro) throws BusinessException {
        try {
            if (Objects.isNull(tipoInsumoFiltro)) {
                return insumoDao.findAll();
            }
            return insumoDao.findByTipoInsumo(tipoInsumoFiltro);
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_TIPO_INVENTARIO_ERROR, e);
        }
    }

}
