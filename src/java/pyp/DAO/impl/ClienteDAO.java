/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.Cliente;
import pyp.DAO.IClienteDAO;

/**
 *
 * @Gaes 5
 */
@Stateless
public class ClienteDAO extends AbstractDAO<Cliente> implements IClienteDAO {

    public ClienteDAO() {
        super(Cliente.class);
    }
    
}
