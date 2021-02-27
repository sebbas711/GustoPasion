/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO;

import java.util.List;
import javax.ejb.Local;
import pyp.modelo.entidades.Cliente;
import pyp.modelo.entidades.Estadopqrs;
import pyp.modelo.entidades.Pqrs;

/**
 *
 * @GAES 5
 */
@Local
public interface IPqrsDAO extends DAO<Pqrs> {

    List<Pqrs> findByEstadoPqrs(Estadopqrs estadoPqrsFiltro);
    public List<Pqrs> finByCustomer(Cliente customer);
    
}
