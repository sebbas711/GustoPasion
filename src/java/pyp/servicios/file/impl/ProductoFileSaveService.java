/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.file.impl;

import java.util.Date;
import javax.ejb.Stateless;
import javax.servlet.http.Part;
import pyp.excepciones.BusinessException;
import pyp.servicios.file.IFileSaveService;

/**
 *
 * @author Ismael
 */
@Stateless(name = "Producto")
public class ProductoFileSaveService extends AbstractFileSaveService implements IFileSaveService {

    private static final String PATH_IMAGES_PROMOS = "public/promos/";
    private static final String FILE_NAME = "promo-";

    @Override
    public String save(Part partFile, String rootPathToSave) throws BusinessException {
        String realPath = toRealPath(PATH_IMAGES_PROMOS);
        savePart(partFile, rootPathToSave.concat(realPath));
        return PATH_IMAGES_PROMOS.concat(getNameFile(partFile.getContentType()));
    }

    @Override
    protected String getNameFile(String mimeType) {
        String date = String.valueOf(new Date())
                .replaceAll(":", "-")
                .replaceAll(" ", "-");
        return FILE_NAME.concat(date).concat(getExtention(mimeType));
    }

}
