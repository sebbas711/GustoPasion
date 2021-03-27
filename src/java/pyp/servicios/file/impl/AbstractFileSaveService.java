/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.file.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.servlet.http.Part;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;

/**
 *
 * @GAES 1
 */
public abstract class AbstractFileSaveService {

    protected void makeDir(String directoryPath) throws BusinessException {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new BusinessException(MessageException.BE_FILE_NOT_MAKE_DIR);
            }
        }
    }

    protected void savePart(Part partFile, String pathToSaveFile) throws BusinessException {
        makeDir(pathToSaveFile);
        File fileToSave = new File(pathToSaveFile.concat(getNameFile(partFile.getContentType())));
        try (InputStream isFile = partFile.getInputStream()) {
            byte[] bytesFile = new byte[isFile.available()];
            isFile.read(bytesFile);
            Files.write(fileToSave.toPath(), bytesFile);
        } catch (IOException ioe) {
            throw new BusinessException(MessageException.BE_FILE_NOT_SAVE, ioe);
        }
    }

    protected String getExtention(String mimeType) {
        switch (mimeType) {
            case "image/jpeg":
                return ".jpg";
            case "image/png":
                return ".png";
            case "application/pdf":
                return ".pdf";
            case "image/webm":
                return ".webp";
            case "image/gif":
                return ".gif";
            case "image/bmp":
                return ".bmp";
            default:
                throw new AssertionError();
        }
    }

    protected String toRealPath(String pathFile) {
        return pathFile.replace("/", File.separator);
    }

    protected abstract String getNameFile(String mimeType);

}
