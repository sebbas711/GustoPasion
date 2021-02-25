/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.TipoInsumo;
import pyp.servicios.IInventarioService;
import pyp.util.MessageUtil;

@Named
@ViewScoped
public class ControlInventarioView implements Serializable {
    
    @EJB
    private IInventarioService inventarioService;
    
    private List<TipoInsumo> tiposInsumosHabilitados;
    private List<Insumo> insumos;
    private TipoInsumo tipoInsumoFiltro;
    
    public ControlInventarioView(){
    }
    
    @PostConstruct
    public void init(){
        try {
            findInsumos();
            tiposInsumosHabilitados = inventarioService.getTiposInsumosHabilitados();
        } catch (BusinessException ex) {
            MessageUtil.sendBusinessException(null, ex);
        }
    }
    
    public String getColorByTipoInsumo(TipoInsumo tipoInsumo){
        return "#" + (tipoInsumo.getId()%2 == 0 ? "951010": "727272");
    }

    public void filtrarPorTipoInsumo(TipoInsumo tipoInsumoFiltro) {
        this.tipoInsumoFiltro = tipoInsumoFiltro;
        findInsumos();
    }
    public void limpiarFiltroTipoInsumo() {
        this.tipoInsumoFiltro = null;
        findInsumos();
    }
    
    private void findInsumos(){
        try {
            insumos = inventarioService.findInsumosByFilter(tipoInsumoFiltro);
        } catch (BusinessException ex) {
            MessageUtil.sendBusinessException(null, ex);
        }
    }

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public TipoInsumo getTipoInsumoFiltro() {
        return tipoInsumoFiltro;
    }

    public List<TipoInsumo> getTiposInsumosHabilitados() {
        return tiposInsumosHabilitados;
    }
}
