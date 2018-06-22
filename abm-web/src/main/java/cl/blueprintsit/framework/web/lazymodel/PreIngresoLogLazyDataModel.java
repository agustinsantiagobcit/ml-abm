package cl.blueprintsit.framework.web.lazymodel;

import cl.metlife.abm.domain.Log;
import cl.metlife.abm.persistence.dao.LogDAO;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by BlueprintsIT on 6/21/2016.
 */
public class PreIngresoLogLazyDataModel extends LazyDataModel<Log> {

    private LogDAO logsDAO;
    private Date from;
    private Date to;
    private Long processId;
    private String brokerRut;
    private String status;
    private String username;

    public PreIngresoLogLazyDataModel(LogDAO logsDAO, Date from, Date to, Long processId, String brokerRut, String status, String username) {
        this.logsDAO = logsDAO;
        this.from = from;
        this.to = to;
        this.processId = processId;
        this.brokerRut = brokerRut;
        this.status = status;
        this.username = username;
    }

    @Override
    public List<Log> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        if(from != null && to != null)
            filters.put("date", format.format(from) + "-" + format.format(to));
        if(brokerRut != null && !brokerRut.equals(""))
            filters.put("brokerRut", brokerRut);
        if(status != null && !status.equals(""))
            filters.put("level", status);
        if(username != null && !username.equals(""))
            filters.put("username", username);

        filters.put("processId", processId);

        List<Log> list = null;
        try {
            int rows = logsDAO.countFiltered(filters).intValue();
            this.setRowCount(rows);
            list = logsDAO.findFiltered(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder), filters);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Log getRowData(String rowKey) {
        return logsDAO.getById(Long.parseLong(rowKey));
    }

    @Override
    public Object getRowKey(Log object) {
        return object.getId();
    }
}
