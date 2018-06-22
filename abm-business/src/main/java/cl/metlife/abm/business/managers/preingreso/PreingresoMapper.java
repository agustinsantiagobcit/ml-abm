package cl.metlife.abm.business.managers.preingreso;

import cl.metlife.abm.domain.Detail;
import cl.metlife.abm.domain.FileType;
import cl.metlife.abm.domain.Lot;
import cl.metlife.ws.clients.preingreso.bulk.MovtoAltaBo;
import cl.metlife.ws.clients.preingreso.bulk.MovtoBajaBo;
import cl.metlife.ws.clients.preingreso.bulk.ObjectFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class PreingresoMapper {

    public MovtoAltaBo makeMovtoAltoBo(Detail detail, Lot lot, String user){
        if(lot.getFileTypeId().equals(FileType.ALTA_TITULAR))
            return makeMovtoAltoBoAltaTitular(detail, user);
        else
            return makeMovtoAltoBoAltaCarga(detail, user);
    }

    public MovtoBajaBo makeMovtoBajaBo(Detail detail, Lot lot, String user){
        if(lot.getFileTypeId().equals(FileType.BAJA_TITULAR))
            return makeMovtoAltoBoBajaTitular(detail, user);
        else
            return makeMovtoAltoBoBajaCarga(detail, user);
    }

    public MovtoAltaBo makeMovtoAltoBoAltaTitular(Detail detail, String user) {
        String formatPattern = "dd/MM/yyyy";

        DateFormat format = new SimpleDateFormat(formatPattern);

        MovtoAltaBo movtoAltaBo = null;
        try {
            movtoAltaBo = new MovtoAltaBo();

            movtoAltaBo.setPolizaNumero(Integer.valueOf(detail.getColumn1()));
            movtoAltaBo.setGrupoId(Integer.valueOf(detail.getColumn2()));

            if(detail.getColumn3() != null){
                if(detail.getColumn3().contains("-"))
                    detail.setColumn3(detail.getColumn3().replace("-", "/"));

                GregorianCalendar a = new GregorianCalendar(); a.setTime(format.parse(detail.getColumn3()));
                XMLGregorianCalendar fechaDesde = null;
                fechaDesde = DatatypeFactory.newInstance().newXMLGregorianCalendar(a);
                movtoAltaBo.setFechaInicioVigencia(fechaDesde);
            }

            if(detail.getColumn4() != null){
                if(detail.getColumn4().contains("-"))
                    detail.setColumn4(detail.getColumn4().replace("-", "/"));

                GregorianCalendar b = new GregorianCalendar(); b.setTime(format.parse(detail.getColumn4()));
                XMLGregorianCalendar fechaHasta = null;
                fechaHasta = DatatypeFactory.newInstance().newXMLGregorianCalendar(b);
                movtoAltaBo.setFechaTerminoVigencia(fechaHasta);
            }

            // Titular.
            movtoAltaBo.setTitular(new ObjectFactory().createTitularBo());
            movtoAltaBo.getTitular().setRut(Integer.valueOf(detail.getColumn5()));
            movtoAltaBo.getTitular().setDv(Integer.valueOf(detail.getColumn6()));

            // Carga.
            movtoAltaBo.setCarga(new ObjectFactory().createCargaBo());
            movtoAltaBo.getCarga().setRut(Integer.valueOf(detail.getColumn7()));
            movtoAltaBo.getCarga().setDv(Integer.valueOf(detail.getColumn8()));
            movtoAltaBo.getCarga().setApellidoPaterno(detail.getColumn9());
            movtoAltaBo.getCarga().setApellidoMaterno(detail.getColumn10());
            movtoAltaBo.getCarga().setNombre(detail.getColumn11());

            GregorianCalendar a = new GregorianCalendar();

            if(detail.getColumn12().contains("-"))
                detail.setColumn12(detail.getColumn12().replace("-", "/"));

            a.setTime(format.parse(detail.getColumn12()));
            XMLGregorianCalendar fechaNacimiento = null;
            fechaNacimiento = DatatypeFactory.newInstance().newXMLGregorianCalendar(a);

            movtoAltaBo.getCarga().setFechaNacimiento((detail.getColumn12() != null) ? fechaNacimiento : null);
            movtoAltaBo.getCarga().setRelacion(detail.getColumn13());
            movtoAltaBo.getCarga().setSexo(new Integer(detail.getColumn14().charAt(0)));

            // Titular denuevo.
            movtoAltaBo.getTitular().setRenta((detail.getColumn15() != null && !detail.getColumn15().equals("null")) ? Double.valueOf(detail.getColumn15()) : null);
            movtoAltaBo.getTitular().setCapital((detail.getColumn16() != null && !detail.getColumn16().equals("null") ? Double.valueOf(detail.getColumn16()) : null));
            movtoAltaBo.getTitular().setPeso((detail.getColumn17() != null && !detail.getColumn17().equals("null")) ? Double.valueOf(detail.getColumn17()) : null);
            movtoAltaBo.getTitular().setEstatura((detail.getColumn18() != null && !detail.getColumn18().equals("null")) ? Double.valueOf(detail.getColumn18().replaceAll(",", ".")) : null);
            movtoAltaBo.getTitular().setPreExistencia(detail.getColumn19());
            // Titular > Data banco
            movtoAltaBo.getTitular().setCuentaDeposito(new ObjectFactory().createCuentaDepositoBo());
            movtoAltaBo.getTitular().getCuentaDeposito().setBancoId((detail.getColumn20() != null && !detail.getColumn20().equals("null")) ? Integer.valueOf(detail.getColumn20()) : null);
            movtoAltaBo.getTitular().getCuentaDeposito().setTipo(detail.getColumn21());
            movtoAltaBo.getTitular().getCuentaDeposito().setNumero(detail.getColumn22());
            // Titular denuevo 2.
            movtoAltaBo.getTitular().setEmail(detail.getColumn23());
            movtoAltaBo.getTitular().setCelular(detail.getColumn24());
            movtoAltaBo.setLote(detail.getColumn25());
            movtoAltaBo.setBarcode(detail.getColumn26());

            // Corredor
            movtoAltaBo.setCorredor(new ObjectFactory().createCorredorBo());
            movtoAltaBo.getCorredor().setRutDv(detail.getColumn27());
            movtoAltaBo.getCorredor().setNombre(detail.getColumn28());

            movtoAltaBo.setPortalUsuario(user);

            return movtoAltaBo;
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public MovtoAltaBo makeMovtoAltoBoAltaCarga(Detail detail, String user) {
        String formatPattern = "dd/MM/yyyy";
        DateFormat format = new SimpleDateFormat(formatPattern);

        MovtoAltaBo movtoAltaBo = null;
        try {
            movtoAltaBo = new MovtoAltaBo();

            movtoAltaBo.setPolizaNumero(Integer.valueOf(detail.getColumn1()));
            movtoAltaBo.setGrupoId(Integer.valueOf(detail.getColumn2()));

            if(detail.getColumn3() != null){
                if(detail.getColumn3().contains("-"))
                    detail.setColumn3(detail.getColumn3().replace("-", "/"));

                GregorianCalendar a = new GregorianCalendar(); a.setTime(format.parse(detail.getColumn3()));
                XMLGregorianCalendar fechaDesde = null;
                fechaDesde = DatatypeFactory.newInstance().newXMLGregorianCalendar(a);
                movtoAltaBo.setFechaInicioVigencia(fechaDesde);
            }

            if(detail.getColumn4() != null){
                if(detail.getColumn4().contains("-"))
                    detail.setColumn4(detail.getColumn4().replace("-", "/"));

                GregorianCalendar b = new GregorianCalendar(); b.setTime(format.parse(detail.getColumn4()));
                XMLGregorianCalendar fechaHasta = null;
                fechaHasta = DatatypeFactory.newInstance().newXMLGregorianCalendar(b);
                movtoAltaBo.setFechaTerminoVigencia(fechaHasta);
            }

            // Titular.
            movtoAltaBo.setTitular(new ObjectFactory().createTitularBo());
            movtoAltaBo.getTitular().setRut(Integer.valueOf(detail.getColumn5()));
            movtoAltaBo.getTitular().setDv(Integer.valueOf(detail.getColumn6()));

            // Carga.
            movtoAltaBo.setCarga(new ObjectFactory().createCargaBo());
            movtoAltaBo.getCarga().setRut(Integer.valueOf(detail.getColumn7()));
            movtoAltaBo.getCarga().setDv(Integer.valueOf(detail.getColumn8()));
            movtoAltaBo.getCarga().setApellidoPaterno(detail.getColumn9());
            movtoAltaBo.getCarga().setApellidoMaterno(detail.getColumn10());
            movtoAltaBo.getCarga().setNombre(detail.getColumn11());

            GregorianCalendar a = new GregorianCalendar();

            if(detail.getColumn12().contains("-"))
                detail.setColumn12(detail.getColumn12().replace("-", "/"));

            a.setTime(format.parse(detail.getColumn12()));
            XMLGregorianCalendar fechaNacimiento = null;
            fechaNacimiento = DatatypeFactory.newInstance().newXMLGregorianCalendar(a);

            movtoAltaBo.getCarga().setFechaNacimiento((detail.getColumn12() != null) ? fechaNacimiento : null);
            movtoAltaBo.getCarga().setRelacion(detail.getColumn13());
            movtoAltaBo.getCarga().setSexo(new Integer(detail.getColumn14().charAt(0)));

            // Titular denuevo.
            movtoAltaBo.getTitular().setPeso((detail.getColumn15() != null && !detail.getColumn15().equals("null")) ? Double.valueOf(detail.getColumn15()) : null);
            movtoAltaBo.getTitular().setEstatura((detail.getColumn16() != null && !detail.getColumn16().equals("null")) ? Double.valueOf(detail.getColumn16()) : null);
            movtoAltaBo.getTitular().setPreExistencia(detail.getColumn17());
            movtoAltaBo.setLote(detail.getColumn18());
            movtoAltaBo.setBarcode(detail.getColumn19());

            // Corredor
            movtoAltaBo.setCorredor(new ObjectFactory().createCorredorBo());
            movtoAltaBo.getCorredor().setRutDv(detail.getColumn20());
            movtoAltaBo.getCorredor().setNombre(detail.getColumn21());

            movtoAltaBo.setPortalUsuario(user);

            return movtoAltaBo;
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public MovtoBajaBo makeMovtoAltoBoBajaTitular(Detail detail, String user) {
        String formatPattern = "dd/MM/yyyy";
        DateFormat format = new SimpleDateFormat(formatPattern);

        MovtoBajaBo movtoBajaBo = null;
        try {
            movtoBajaBo = new MovtoBajaBo();

            movtoBajaBo.setPolizaNumero(Integer.valueOf(detail.getColumn1()));
            movtoBajaBo.setGrupoId(Integer.valueOf(detail.getColumn2()));

            // Titular.
            movtoBajaBo.setTitular(new ObjectFactory().createTitularBo());
            movtoBajaBo.getTitular().setRut(Integer.valueOf(detail.getColumn3()));
            movtoBajaBo.getTitular().setDv(Integer.valueOf(detail.getColumn4()));

            GregorianCalendar a = new GregorianCalendar();

            if(detail.getColumn5().contains("-"))
                detail.setColumn5(detail.getColumn5().replace("-", "/"));

            a.setTime(format.parse(detail.getColumn5()));
            XMLGregorianCalendar fechaBaja = null;
            fechaBaja = DatatypeFactory.newInstance().newXMLGregorianCalendar(a);

            movtoBajaBo.setFechaBaja((detail.getColumn5() != null) ? fechaBaja : null);
            movtoBajaBo.setLote(detail.getColumn6());
            movtoBajaBo.setBarcode(detail.getColumn7());

            // Corredor
            movtoBajaBo.setCorredor(new ObjectFactory().createCorredorBo());
            movtoBajaBo.getCorredor().setRutDv(detail.getColumn27());
            movtoBajaBo.getCorredor().setNombre(detail.getColumn28());

            movtoBajaBo.setPortalUsuario(user);

            return movtoBajaBo;
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public MovtoBajaBo makeMovtoAltoBoBajaCarga(Detail detail, String user) {
        String formatPattern = "dd/MM/yyyy";
        DateFormat format = new SimpleDateFormat(formatPattern);

        MovtoBajaBo movtoBajaBo = null;
        try {
            movtoBajaBo = new MovtoBajaBo();

            movtoBajaBo.setPolizaNumero(Integer.valueOf(detail.getColumn1()));
            movtoBajaBo.setGrupoId(Integer.valueOf(detail.getColumn2()));

            // Titular.
            movtoBajaBo.setTitular(new ObjectFactory().createTitularBo());
            movtoBajaBo.getTitular().setRut(Integer.valueOf(detail.getColumn3()));
            movtoBajaBo.getTitular().setDv(Integer.valueOf(detail.getColumn4()));

            GregorianCalendar a = new GregorianCalendar();

            if(detail.getColumn5().contains("-"))
                detail.setColumn5(detail.getColumn5().replace("-", "/"));

            a.setTime(format.parse(detail.getColumn5()));
            XMLGregorianCalendar fechaBaja = null;
            fechaBaja = DatatypeFactory.newInstance().newXMLGregorianCalendar(a);

            movtoBajaBo.setFechaBaja((detail.getColumn5() != null) ? fechaBaja : null);
            movtoBajaBo.setLote(detail.getColumn6());
            movtoBajaBo.setBarcode(detail.getColumn7());

            // Corredor
            movtoBajaBo.setCorredor(new ObjectFactory().createCorredorBo());
            movtoBajaBo.getCorredor().setRutDv(detail.getColumn27());
            movtoBajaBo.getCorredor().setNombre(detail.getColumn28());

            movtoBajaBo.setPortalUsuario(user);

            return movtoBajaBo;
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
