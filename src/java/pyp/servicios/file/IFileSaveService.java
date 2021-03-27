/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.file;

import javax.ejb.Local;
import javax.servlet.http.Part;
import pyp.excepciones.BusinessException;

/**
 *
 * @GAES 1
 */
@Local
public interface IFileSaveService {
    
    String save(Part partFile, String rootPathToSave) throws BusinessException;
}
