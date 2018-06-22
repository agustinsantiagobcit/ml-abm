package cl.metlife.abm.persistence.dao;

import cl.metlife.abm.domain.FileType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class FileTypeDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "ABMPersistenceUnit")
    private EntityManager em;

    public FileType getById(long id) {

        try {
            FileType group = em.find(FileType.class,id);
            em.detach(group);
            return group;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<FileType> findAll() {
        Query query = em.createQuery("select i from FileType i");

        List<FileType> resultList = query.getResultList();

        for (FileType detailStatus : resultList) {
            em.detach(detailStatus);
        }

        List<FileType> returnList = new ArrayList<FileType>();
        returnList.addAll(resultList);

        return returnList;
    }

}