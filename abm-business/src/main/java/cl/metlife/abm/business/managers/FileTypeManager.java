package cl.metlife.abm.business.managers;

import cl.metlife.abm.domain.FileType;
import cl.metlife.abm.domain.Lot;
import cl.metlife.abm.persistence.dao.FileTypeDAO;
import cl.metlife.abm.persistence.dao.LotDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class FileTypeManager {

    @EJB
    FileTypeDAO fileTypeDAO;

    public FileType getById(Long id) {
        return fileTypeDAO.getById(id);
    }

    public List<FileType> findAll() {
        return fileTypeDAO.findAll();
    }

}