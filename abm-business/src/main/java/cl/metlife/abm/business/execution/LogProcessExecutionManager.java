package cl.metlife.abm.business.execution;

import cl.metlife.abm.business.managers.*;
import cl.metlife.abm.business.managers.sftp.SFTPManager;
import cl.metlife.abm.domain.*;
import cl.metlife.abm.domain.Process;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by BluePrints Developer on 25-01-2017.
 */
@Stateless
public class LogProcessExecutionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogProcessExecutionManager.class);

    @EJB
    LogbookManager logbookManager;

    @EJB
    SFTPManager sftpManager;

    private static final String[] columns = {"Fecha", "Rut Corredor", "Lote", "Tipo", "Proceso", "Mensaje"};
    private static final String datePattern = "dd/MM/yyyy HH:mm:ss";
    private final SimpleDateFormat format = new SimpleDateFormat(datePattern);

    @Asynchronous
    public void execute(Process process, Date lastExecution, String username, String executionType) {
        logbookManager.info(process, Log.LOG_STEP_EJECUCION_GENERAL ,"Iniciando Proceso Automático Envío Logs PreIngreso " + executionType + " " +
                (process.getProcessTypeId().equals(ProcessType.PROCESS_TYPE_NORMAL) ? "Corredor ": " Multicorredor")
                + " " + process.getBrokerName() + " (" + process.getBrokerRut() + ")" , username);

        try{

            List<Log> preingresoLog = logbookManager.findByProcessIdFromDateAndStep(process.getId(), lastExecution, Log.LOG_STEP_PREINGRESO);

            if(!preingresoLog.isEmpty()){
                LOGGER.debug("Se encontraron logs para enviar a preingreso");

                XSSFWorkbook wb = createWorkbook(preingresoLog);

                sftpManager.uploadLogFile(process.getHost(), Integer.valueOf(process.getPort()), process.getUser(), process.getPassword(), process.getBrokerRut(), wb);

                logbookManager.info(process, Log.LOG_STEP_EJECUCION_GENERAL ,"Finalizó correctamente el Proceso Automático de Envío Logs PreIngreso " + executionType + " " +
                        (process.getProcessTypeId().equals(ProcessType.PROCESS_TYPE_NORMAL) ? "Corredor ": " Multicorredor")
                        + " " + process.getBrokerName() + " (" + process.getBrokerRut() + ")" , username);
            } else {
                logbookManager.info(process, Log.LOG_STEP_EJECUCION_GENERAL ,"No hay logs del tipo PreIngreso disponibles para ser enviados a PreIngreso", username);
            }

        } catch (Exception e) {
            logbookManager.log(process, Log.LOG_LEVEL_ERROR, Log.LOG_STEP_SFTP, "Proceso Automático: Hubo un error al enviar los log de Preingreso a SFTP", e, username);
            LOGGER.error(e.getMessage(), e);
        }
    }

    private XSSFWorkbook createWorkbook(List<Log> logs) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Log Data");

        // Creating first row (Header)
        Row headerRow = sheet.createRow(0);

        // Create header cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Create Other rows and cells with log data
        int rowNum = 1;
        for(Log log: logs) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(format.format(log.getDate()));
            row.createCell(1)
                    .setCellValue(log.getBrokerRut());
            row.createCell(2)
                    .setCellValue((log.getLot() != null) ? log.getLot().getNumber() : "");
            row.createCell(3)
                    .setCellValue(log.getLevel());
            row.createCell(4)
                    .setCellValue(log.getStep());
            row.createCell(5)
                    .setCellValue(log.getComment());
        }

        return workbook;
    }

}
