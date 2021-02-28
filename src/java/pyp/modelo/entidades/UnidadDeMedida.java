/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "unidad_de_medida")
@NamedQueries({
    @NamedQuery(name = "UnidadDeMedida.findAll", query = "SELECT u FROM UnidadDeMedida u")
    , @NamedQuery(name = "UnidadDeMedida.findById", query = "SELECT u FROM UnidadDeMedida u WHERE u.id = :id")
    , @NamedQuery(name = "UnidadDeMedida.findByNombre", query = "SELECT u FROM UnidadDeMedida u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "UnidadDeMedida.findByDescripcion", query = "SELECT u FROM UnidadDeMedida u WHERE u.descripcion = :descripcion")
    , @NamedQuery(name = "UnidadDeMedida.findBySigla", query = "SELECT u FROM UnidadDeMedida u WHERE u.sigla = :sigla")})
public class UnidadDeMedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "sigla")
    private String sigla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadDeMedida", fetch = FetchType.LAZY)
    private List<Insumo> insumoList;

    public UnidadDeMedida() {
    }

    public UnidadDeMedida(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Insumo> getInsumoList() {
        return insumoList;
    }

    public void setInsumoList(List<Insumo> insumoList) {
        this.insumoList = insumoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadDeMedida)) {
            return false;
        }
        UnidadDeMedida other = (UnidadDeMedida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pyp.modelo.entidades.UnidadDeMedida[ id=" + id + " ]";
    }
    
}
