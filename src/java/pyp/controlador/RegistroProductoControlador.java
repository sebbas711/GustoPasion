/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import pyp.DAO.ICategoriaProductoDAO;
import pyp.DAO.IInsumoDAO;
import pyp.DAO.IProductoDAO;
import pyp.DAO.IUsuarioDAO;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.InsumosDelProducto;
import pyp.modelo.entidades.Producto;
import pyp.modelo.entidades.enums.EstadoPedidoEnum;
import pyp.servicios.file.IFileSaveService;
import pyp.util.MessageUtil;

/**
 *
 * @GAES 1
 */
@Named
@ViewScoped
public class RegistroProductoControlador implements Serializable {

    @EJB
    private ICategoriaProductoDAO categoriaProductoDAO;
    @EJB
    private IInsumoDAO insumoDAO;
    @EJB
    private IProductoDAO productoDAO;

    private List<CategoriaProducto> categorias;
    private List<Insumo> insumos;
    private Producto producto;
    private InsumosDelProducto insumoProducto;

    //@EJB(beanName = "Producto")
    //private IFileSaveService fileSaveService;
    
    //@EJB
    //private Part imgProductos;

    @PostConstruct
    public void init() {
        this.producto = new Producto();
        this.producto.setInsumosDelProducto(new ArrayList<>());

        inicialializarInsumoProducto();

        categorias = categoriaProductoDAO.findAll();
        insumos = insumoDAO.findAll();
    }

    private void inicialializarInsumoProducto() {
        this.insumoProducto = new InsumosDelProducto();
        this.insumoProducto.setProducto(this.producto);
    }

    public void agregarInsumoAlProducto() {
        if (insumoProductoValido()) {
            producto.getInsumosDelProducto().add(insumoProducto);
            inicialializarInsumoProducto();
        } else {
            MessageUtil.sendErrorModal("Error validación insumo", "Verifice la sección de insumos, todos los campos son obligaotrios");
        }
    }

    private boolean insumoProductoValido() {
        return Objects.nonNull(this.insumoProducto.getInsumo())
                && Objects.nonNull(insumoProducto.getCantidadInsumo())
                && insumoProducto.getCantidadInsumo() > 0;
    }

    public void registrarProducto() {
        if (productoValido()) {
            productoDAO.create(producto);
            init();
            MessageUtil.sendSuccessModal("Registro exitoso", "Se ha registrado correctamente el producto con la respectiva parametrización para preparlo.");
        } else {
            MessageUtil.sendErrorModal("Error validación", "Verifice los datos ingresados");
        }
    }

    private boolean productoValido() {
        return Objects.nonNull(producto.getNombre()) && !producto.getNombre().isEmpty();
    }
    
    /*public void imgProducto() {
        /*
        String infoFile = imgPromocion.getSubmittedFileName()
                .concat(" - ")
                .concat(imgPromocion.getName())
                .concat(" - ")
                .concat(imgPromocion.getContentType())
                .concat(" - ")
                .concat(String.valueOf(imgPromocion.getSize()));
        */
       /* try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String pathImage = fileSaveService.save(imgProductos, ec.getRealPath("/"));
            //http://localhost:8080/GustoPasion
            String urlImage = buildUrl(ec).concat("/").concat(pathImage);
            MessageUtil.sendInfoModal("Se ha publicitado", "Se ha enviado la publicidad de la promoción a los usuarios del sistema.");
        } catch (BusinessException ex) {
            ex.printStackTrace();
            MessageUtil.sendErrorModal(ex.getMessage(), ex.getDetails());
        } catch (Exception e) {
            MessageUtil.sendInfo(null, "Error",
                    "", Boolean.FALSE);
            e.printStackTrace();
        }
    }*/
    
    
   /* private String buildUrl(ExternalContext ec){
        try {
            return new URL(ec.getRequestScheme(),
                    ec.getRequestServerName(),
                    ec.getRequestServerPort(),
                    ec.getRequestContextPath()).toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(EnvioPromoControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public Part getImgProductos() {
        return imgProductos;
    }

    public void setImgProductos(Part imgProductos) {
        this.imgProductos = imgProductos;
    }*/
    
    

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public List<CategoriaProducto> getCategorias() {
        return categorias;
    }

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public Producto getProducto() {
        return producto;
    }

    public InsumosDelProducto getInsumoProducto() {
        return insumoProducto;
    }

    public EstadoPedidoEnum[] getEstadosPedido() {
        return EstadoPedidoEnum.values();
    }
    //</editor-fold>

}
