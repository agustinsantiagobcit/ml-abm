package cl.metlife.abm.business.managers;

import cl.metlife.abm.domain.*;
import cl.metlife.abm.persistence.dao.DetailDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class DetailManager {

    @EJB
    DetailDAO detailDAO;

    @EJB
    ABMConfigurationManager abmConfigurationManager;

    DetailMapper detailMapper;

    public Detail getById(Long id) {
        return detailDAO.getById(id);
    }

    public List<Detail> findAll() {
        return detailDAO.findAll();
    }

    public List<Detail> findByLotId(Long lotId){
        return detailDAO.findByLotId(lotId);
    }

    public List<Detail> findByLotId(Long lotId, Long statusId){
        return detailDAO.findByLotId(lotId, statusId);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Detail create(Detail detail) {
        return detailDAO.create(detail);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Detail update(Detail detail) {
        return detailDAO.update(detail);
    }

    public boolean delete(Detail detail) {
        return detailDAO.delete(detail);
    }

    public String getColumnValue(int fileType, Detail detail){
        int columnIndex = 0;

        if(FileType.ALTA_TITULAR == fileType){
            columnIndex = Integer.valueOf(abmConfigurationManager.getByKey(ABMConfiguration.ALTA_TITULAR_BARCODE_INDEX).getValor());
        } else if(FileType.ALTA_CARGA == fileType){
            columnIndex = Integer.valueOf(abmConfigurationManager.getByKey(ABMConfiguration.ALTA_CARGA_BARCODE_INDEX).getValor());
        } else if(FileType.BAJA_TITULAR == fileType){
            columnIndex = Integer.valueOf(abmConfigurationManager.getByKey(ABMConfiguration.BAJA_TITULAR_BARCODE_INDEX).getValor());
        } else {
            columnIndex = Integer.valueOf(abmConfigurationManager.getByKey(ABMConfiguration.BAJA_CARGA_BARCODE_INDEX).getValor());
        }

        return getColumnValueByIndex(columnIndex, detail);
    }

    private String getColumnValueByIndex(int columnIndex, Detail detail){
        switch (columnIndex){
            case 1: return detail.getColumn1();
            case 2: return detail.getColumn2();
            case 3: return detail.getColumn3();
            case 4: return detail.getColumn4();
            case 5: return detail.getColumn5();
            case 6: return detail.getColumn6();
            case 7: return detail.getColumn7();
            case 8: return detail.getColumn8();
            case 9: return detail.getColumn9();
            case 10: return detail.getColumn10();
            case 11: return detail.getColumn11();
            case 12: return detail.getColumn12();
            case 13: return detail.getColumn13();
            case 14: return detail.getColumn14();
            case 15: return detail.getColumn15();
            case 16: return detail.getColumn16();
            case 17: return detail.getColumn17();
            case 18: return detail.getColumn18();
            case 19: return detail.getColumn19();
            case 20: return detail.getColumn20();
            case 21: return detail.getColumn21();
            case 22: return detail.getColumn22();
            case 23: return detail.getColumn23();
            case 24: return detail.getColumn24();
            case 25: return detail.getColumn25();
            case 26: return detail.getColumn26();
            case 27: return detail.getColumn27();
            case 28: return detail.getColumn28();
        }

        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void markWithError(Detail detail) {
        detail.setStatusId(DetailsStatus.ERROR);
        detailDAO.update(detail);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void markLoadedInFilenet(Detail detail) {
        detail.setStatusId(DetailsStatus.LOADED_IN_FILENET);
        detailDAO.update(detail);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void markFinalized(Detail detail) {
        detail.setStatusId(DetailsStatus.FINALIZED);
        detailDAO.update(detail);
    }

    public Detail map(AltaTitular altaTitular, Lot persistedLot, boolean considerBarcodes, int index) {
        if(detailMapper == null)
            detailMapper = new DetailMapper();

        return detailMapper.map(altaTitular, persistedLot, considerBarcodes, index);
    }

    public Detail map(AltaCarga altaCarga, Lot persistedLot, boolean considerBarcodes, int index) {
        if(detailMapper == null)
            detailMapper = new DetailMapper();

        return detailMapper.map(altaCarga, persistedLot, considerBarcodes, index);
    }

    public Detail map(ExclusionTitular exclusionTitular, Lot persistedLot, boolean considerBarcodes, int index) {
        if(detailMapper == null)
            detailMapper = new DetailMapper();

        return detailMapper.map(exclusionTitular, persistedLot, considerBarcodes, index);
    }

    public Detail map(ExclusionCarga exclusionCarga, Lot persistedLot, boolean considerBarcodes, int index) {
        if(detailMapper == null)
            detailMapper = new DetailMapper();

        return detailMapper.map(exclusionCarga, persistedLot, considerBarcodes, index);
    }
}