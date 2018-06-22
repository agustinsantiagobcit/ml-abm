package cl.metlife.abm.business.managers;

import cl.metlife.abm.domain.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DetailMapper {

    public Detail map(AltaTitular altaTitular, Lot persistedLot, boolean considerBarcodes, int index) {
        Detail detail = new Detail();

        detail.setColumn1(String.valueOf(altaTitular.getId()));
        detail.setColumn2(String.valueOf(altaTitular.getNumGru()));
        detail.setColumn3(altaTitular.getFechaDesde());
        detail.setColumn4(altaTitular.getFechaHasta());
        detail.setColumn5(String.valueOf(altaTitular.getRutTit()));
        detail.setColumn6(String.valueOf(altaTitular.getDvTit()));
        detail.setColumn7(String.valueOf(altaTitular.getRutCar()));
        detail.setColumn8(String.valueOf(altaTitular.getDvCar()));
        detail.setColumn9(String.valueOf(altaTitular.getPat()));
        detail.setColumn10(String.valueOf(altaTitular.getMat()));
        detail.setColumn11(String.valueOf(altaTitular.getNom()));
        detail.setColumn12(altaTitular.getFechaNacimiento());
        detail.setColumn13(String.valueOf(altaTitular.getRel()));
        detail.setColumn14(String.valueOf(altaTitular.getSex()));
        detail.setColumn15(String.valueOf(altaTitular.getRenta()));
        detail.setColumn16(String.valueOf(altaTitular.getCapital()));
        detail.setColumn17(String.valueOf(altaTitular.getPeso()));
        detail.setColumn18(String.valueOf(altaTitular.getEstatura()));
        detail.setColumn19(String.valueOf(altaTitular.getPreExistencia()));
        detail.setColumn20(String.valueOf(altaTitular.getBanco()));
        detail.setColumn21(String.valueOf(altaTitular.getTipoCuenta()));
        detail.setColumn22(String.valueOf(altaTitular.getNumeroCuenta()));
        detail.setColumn23(String.valueOf(altaTitular.getEmail()));
        detail.setColumn24(String.valueOf(altaTitular.getCelular()));
        detail.setColumn25(String.valueOf(altaTitular.getLote()));
        detail.setColumn26(String.valueOf(altaTitular.getCodigoBarra()));
        detail.setColumn27(String.valueOf(altaTitular.getRutCorredor()));
        detail.setColumn28(String.valueOf(altaTitular.getNombreCorredor()));
        detail.setIndex(index);

        detail.setRutaPdf(String.valueOf(altaTitular.getCodigoBarra()));
        detail.setStatusId(((considerBarcodes) ? DetailsStatus.CREATED: DetailsStatus.FINALIZED));

        detail.setLotId(persistedLot.getId());

        return detail;
    }

    public Detail map(AltaCarga altaCarga, Lot persistedLot, boolean considerBarcodes, int index) {
        Detail detail = new Detail();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        detail.setColumn1(String.valueOf(altaCarga.getId()));
        detail.setColumn2(String.valueOf(altaCarga.getNumGru()));
        detail.setColumn3(altaCarga.getFechaDesde());
        detail.setColumn4(altaCarga.getFechaHasta());
        detail.setColumn5(String.valueOf(altaCarga.getRutTit()));
        detail.setColumn6(String.valueOf(altaCarga.getDvTit()));
        detail.setColumn7(String.valueOf(altaCarga.getRutCar()));
        detail.setColumn8(String.valueOf(altaCarga.getDvCar()));
        detail.setColumn9(String.valueOf(altaCarga.getPat()));
        detail.setColumn10(String.valueOf(altaCarga.getMat()));
        detail.setColumn11(String.valueOf(altaCarga.getNom()));
        detail.setColumn12(altaCarga.getFechaNacimiento());
        detail.setColumn13(String.valueOf(altaCarga.getRel()));
        detail.setColumn14(String.valueOf(altaCarga.getSex()));
        detail.setColumn15(String.valueOf(altaCarga.getPeso()));
        detail.setColumn16(String.valueOf(altaCarga.getEstatura()));
        detail.setColumn17(String.valueOf(altaCarga.getPreExistencia()));
        detail.setColumn18(String.valueOf(altaCarga.getLote()));
        detail.setColumn19(String.valueOf(altaCarga.getCodigoBarra()));
        detail.setColumn20(String.valueOf(altaCarga.getRutCorredor()));
        detail.setColumn21(String.valueOf(altaCarga.getNombreCorredor()));
        detail.setIndex(index);

        detail.setRutaPdf(String.valueOf(altaCarga.getCodigoBarra()));
        detail.setStatusId(((considerBarcodes) ? DetailsStatus.CREATED: DetailsStatus.FINALIZED));

        detail.setLotId(persistedLot.getId());

        return detail;
    }

    public Detail map(ExclusionTitular exclusionTitular, Lot persistedLot, boolean considerBarcodes, int index) {
        Detail detail = new Detail();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        detail.setColumn1(String.valueOf(exclusionTitular.getId()));
        detail.setColumn2(String.valueOf(exclusionTitular.getNumGru()));
        detail.setColumn3(String.valueOf(exclusionTitular.getRutTit()));
        detail.setColumn4(String.valueOf(exclusionTitular.getDvTit()));
        detail.setColumn5(exclusionTitular.getFechaHasta());
        detail.setColumn6(String.valueOf(exclusionTitular.getLote()));
        detail.setColumn7(String.valueOf(exclusionTitular.getCodigoBarra()));
        detail.setColumn8(String.valueOf(exclusionTitular.getRutCorredor()));
        detail.setColumn9(String.valueOf(exclusionTitular.getNombreCorredor()));
        detail.setIndex(index);

        detail.setRutaPdf(String.valueOf(exclusionTitular.getCodigoBarra()));
        detail.setStatusId(((considerBarcodes) ? DetailsStatus.CREATED: DetailsStatus.FINALIZED));

        detail.setLotId(persistedLot.getId());

        return detail;
    }

    public Detail map(ExclusionCarga exclusionCarga, Lot persistedLot, boolean considerBarcodes, int index) {
        Detail detail = new Detail();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        detail.setColumn1(String.valueOf(exclusionCarga.getId()));
        detail.setColumn2(String.valueOf(exclusionCarga.getNumGru()));
        detail.setColumn3(String.valueOf(exclusionCarga.getRutTit()));
        detail.setColumn4(String.valueOf(exclusionCarga.getDvTit()));
        detail.setColumn5(String.valueOf(exclusionCarga.getRutCarga()));
        detail.setColumn6(String.valueOf(exclusionCarga.getDvCarga()));
        detail.setColumn7(exclusionCarga.getFechaHasta());
        detail.setColumn8(String.valueOf(exclusionCarga.getPaterno()));
        detail.setColumn9(String.valueOf(exclusionCarga.getMaterno()));
        detail.setColumn10(String.valueOf(exclusionCarga.getNombre()));
        detail.setColumn11(String.valueOf(exclusionCarga.getLote()));
        detail.setColumn12(String.valueOf(exclusionCarga.getCodigoBarra()));
        detail.setColumn13(String.valueOf(exclusionCarga.getRutCorredor()));
        detail.setColumn14(String.valueOf(exclusionCarga.getNombreCorredor()));
        detail.setIndex(index);

        detail.setRutaPdf(String.valueOf(exclusionCarga.getCodigoBarra()));
        detail.setStatusId(((considerBarcodes) ? DetailsStatus.CREATED: DetailsStatus.FINALIZED));

        detail.setLotId(persistedLot.getId());

        return detail;
    }
}
