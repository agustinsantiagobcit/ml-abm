package cl.blueprintsit.cl.utils;

import javax.persistence.Query;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by BlueprintsIT on 6/21/2016.
 */
public class LazyModelUtils {

    public static void setFilterParams(Map<String, Object> filters, Query query) throws ParseException {

        if ( filters != null ) {
            for ( String field : filters.keySet() ) {
                setParameter(query, field, filters.get(field));
            }
        }
    }

    public static void setParameter(Query query, String field, Object value) throws ParseException {

        if(field.equals("processId")){
            query.setParameter(field,  Long.valueOf(String.valueOf(value)) );
            return;
        }

        if(field.equals("lot.filename")){
            query.setParameter(field,  "%" + Integer.valueOf(value.toString()) + "%" );
            return;
        }

        if(field.equals("level")){
            query.setParameter(field,   "%" + value.toString()+ "%" );
            return;
        }

        if(field.equals("step")){
            query.setParameter(field, "%" + value+ "%" );
            return;
        }

        if(field.equals("lot.process.brokerRut")){
            query.setParameter(field,"%" +  value+ "%" );
            return;
        }

        if(field.equals("brokerRut")){
            query.setParameter(field,"%" +  value+ "%" );
            return;
        }

        if(field.equals("username")){
            query.setParameter(field, value);
            return;
        }

        if(field.equals("date")){
            String[] arr = value.toString().split("-");
            if(arr.length == 1){
                DateFormat format = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date fe = format.parse(arr[0]);
                Calendar calendar =  new GregorianCalendar();
                calendar.setTimeInMillis(fe.getTime());
                query.setParameter("fechaInicial", calendar);
            } else if(arr.length == 2) {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                query.setParameter("fechaInicial", format.parse(arr[0]));
                query.setParameter("fechaFinal", format.parse(arr[1]));
            }
            return;
        }

    }



    public static Object formattedValue(String fieldName, Object fieldValue) {
        if (fieldValue == null || "".equals(fieldValue))
            return null;

        if ( isLikeField(fieldName) )
            return fieldValue + "%";

        if ( fieldName.toLowerCase().endsWith("id") )
            return Long.parseLong(String.valueOf(fieldValue));

        return fieldValue;
    }

    public static Boolean isDateField(String field) {
        if(field.equals("fechaInicio") || field.equals("fechaFin")){
            return true;
        }
        return field.toLowerCase().indexOf("date") >= 0;
    }

    public static Boolean isLikeField(String field) {
        return field.contains(".id");
    }

    public static String buildWhereClauses(Map<String, Object> filters) {

        StringBuilder whereClauses = new StringBuilder();

        if (filters != null) {
            for (String field : filters.keySet()){
                appendFieldClause(whereClauses, field,filters);
            }
        }

        return whereClauses.toString();
    }

    public static void appendFieldClause(StringBuilder whereClauses, String field,Map<String, Object> filters) {

        if(field.equals("processId")){
            whereClauses.append(whereClauses.length() <= 0 ? " where l." : " and l.");
            whereClauses.append("processId");
            whereClauses.append(" = :");
            whereClauses.append(field);
            return;
        }

        if(field.equals("lot.filename")){
            whereClauses.append(whereClauses.length() <= 0 ? " where l." : " and l.");
            whereClauses.append("lot.filename");
            whereClauses.append(" like :");
            whereClauses.append(field);
            return;
        }

        if(field.equals("level")){
            whereClauses.append(whereClauses.length() <= 0 ? " where l." : " and l.");
            whereClauses.append("level");
            whereClauses.append(" like :");
            whereClauses.append(field);
            return;
        }

        if(field.equals("step")){
            whereClauses.append(whereClauses.length() <= 0 ? " where l." : " and l.");
            whereClauses.append("step");
            whereClauses.append(" like :");
            whereClauses.append(field);
            return;
        }
        if(field.equals("lot.process.brokerRut")){
            whereClauses.append(whereClauses.length() <= 0 ? " where l." : " and l.");
            whereClauses.append("lot.process.brokerRut");
            whereClauses.append(" like :");
            whereClauses.append(field);
            return;
        }

        if(field.equals("brokerRut")){
            whereClauses.append(whereClauses.length() <= 0 ? " where l." : " and l.");
            whereClauses.append("brokerRut");
            whereClauses.append(" like :");
            whereClauses.append(field);
            return;
        }

        if(field.equals("username")){
            whereClauses.append(whereClauses.length() <= 0 ? " where l." : " and l.");
            whereClauses.append("username");
            whereClauses.append(" =:");
            whereClauses.append(field);
            return;
        }

        if(field.equals("date")){
            String test = filters.get("date").toString();
            String[] array = test.split("-");
            if(array.length == 1){
                whereClauses.append(whereClauses.length() <= 0 ? " where l." : " and l.");
                whereClauses.append("date >= :fechaInicial");
            }

            if(array.length == 2){
                whereClauses.append(whereClauses.length() <= 0 ? " where l." : " and l.");
                whereClauses.append("date BETWEEN :fechaInicial AND :fechaFinal");
            }
        }

    }

    public static String dateFieldFrom(String field) {
        return formattedField(field) + "_from";
    }

    public static String dateFieldTo(String field) {
        return formattedField(field) + "_to";
    }

    public static String formattedField(String field) {
        return field.replaceAll("\\.", "_");
    }

}
