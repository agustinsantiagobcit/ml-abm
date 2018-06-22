package cl.blueprintsit.framework.web.lazymodel;

import cl.metlife.abm.domain.Log;
import cl.metlife.abm.persistence.dao.LogDAO;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BlueprintsIT on 6/21/2016.
 */
public class LogLazyDataModel extends LazyDataModel<Log> {

    private LogDAO logsDAO;
    private Long processId;

    public LogLazyDataModel(LogDAO logsDAO, Long processId) {
        this.logsDAO = logsDAO;
        this.processId = processId;
    }

    @Override
    public List<Log> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        filters.put("processId", processId);

        List<Log> list = new ArrayList<Log>();
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
