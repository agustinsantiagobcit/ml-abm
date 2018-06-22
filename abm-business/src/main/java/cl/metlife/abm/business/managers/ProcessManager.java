package cl.metlife.abm.business.managers;

import cl.metlife.abm.business.managers.sftp.SFTPManager;
import cl.metlife.abm.business.managers.sftp.exception.SFTPConnectionException;
import cl.metlife.abm.business.scheduler.LogProcessStarter;
import cl.metlife.abm.business.scheduler.LogProcessTimerManager;
import cl.metlife.abm.business.scheduler.ProcessStarter;
import cl.metlife.abm.business.scheduler.ProcessTimerManager;
import cl.metlife.abm.domain.Process;
import cl.metlife.abm.domain.ProcessType;
import cl.metlife.abm.domain.Timer;
import cl.metlife.abm.persistence.dao.ProcessDAO;
import cl.metlife.abm.persistence.dao.TimerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class ProcessManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessManager.class);

    @EJB
    ProcessDAO processDAO;

    @EJB
    TimerDAO timerDAO;

    @EJB
    SFTPManager sftpManager;

    @EJB
    ProcessTimerManager processTimerManager;

    @EJB
    LogProcessTimerManager logProcessTimerManager;


    public void startProgrammaticExecution(Process process){
        ScheduleExpression processExpression = getScheduleExpressionByTimer(process.getProcessTimer());
        processTimerManager.addTimer(process, processExpression);

        if(process.isAutomaticSendPreingreso()){
            ScheduleExpression logProcessExpression = getScheduleExpressionByTimer(process.getLogTimer());
            logProcessTimerManager.addTimer(process, logProcessExpression);
        }
    }

    public void stopProgrammaticExecution(Process process){
        processTimerManager.cancelForProcess(process);
        logProcessTimerManager.cancelForProcess(process);
    }

    public Date getNextProcessExecutionForProcess(Process process) {
        return processTimerManager.getNextExecutionForProcess(process);
    }

    public Date getNextLogSendExecutionForProcess(Process process) {
        return logProcessTimerManager.getNextExecutionForProcess(process);
    }

    private ScheduleExpression getScheduleExpressionByTimer(Timer timer) {
        ScheduleExpression processExpression = new ScheduleExpression();

        processExpression.hour(timer.getHour());
        processExpression.minute(timer.getMinute());
        processExpression.second(timer.getSecond());
        processExpression.dayOfMonth(timer.getDay());
        processExpression.dayOfWeek(timer.getDayOfWeek());
        processExpression.month(timer.getMonth());
        processExpression.year(timer.getYear());

        return processExpression;
    }

    public Process getById(Long id) {
        Process process =  processDAO.getById(id);

        if(process.getProcessTimer() != null){
            process.setProcessTimer(formatTimers(process.getProcessTimer()));
        }

        if(process.isAutomaticSendPreingreso()){
            process.setLogTimer(formatTimers(process.getLogTimer()));
        }

        if(process.getProcessTypeId().equals(ProcessType.PROCESS_TYPE_MULTIBROKER))
            process.setMultiBroker(true);

        return process;
    }

    public Timer formatTimers(Timer timer){
        timer.setDays(new ArrayList<String>());
        timer.setMonths(new ArrayList<String>());

        if(timer.getDay().equals("*"))
            timer.setDays(allDays(timer.getDays()));
        else if(timer.getDay().equals("0")){
            timer.setDays(new ArrayList<String>());
        } else {
            String[] daysSplitted = timer.getDay().split(",");

            for (int i = 0; i < daysSplitted.length; i++)
                timer.getDays().add(daysSplitted[i]);

        }

        if(timer.getMonth().equals("*"))
            timer.setMonths(allMonths(timer.getMonths()));
        else if(timer.getMonth().equals("0")){
            timer.setMonths(new ArrayList<String>());
        } else {
            String[] monthSplitted = timer.getMonth().split(",");

            for (int i = 0; i < monthSplitted.length; i++)
                timer.getMonths().add(monthSplitted[i]);

        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            timer.setParsedDate(simpleDateFormat.parse(timer.getHour() + ":" + timer.getMinute() + ":" + timer.getSecond()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timer;
    }

    private List<String> allMonths(List<String> months) {
        for (int i = 0; i <= 12; i++) {
            months.add(""+i);
        }

        return months;
    }

    private List<String> allDays(List<String> days) {
        for (int i = 0; i <= 7; i++) {
            days.add(""+i);
        }

        return days;
    }

    public List<Process> findAll() {
        return processDAO.findAll();
    }

    public Process create(Process process) {
        Timer persistedProcessTimer = timerDAO.create(fixTimerObject(process.getProcessTimer()));
        Timer persistedLogTimer = timerDAO.create(fixTimerObject(process.getLogTimer()));

        process.setProcessTimer(persistedProcessTimer);
        process.setLogTimer(persistedLogTimer);
        process.setProcessTypeId((process.isMultiBroker() ? ProcessType.PROCESS_TYPE_MULTIBROKER : ProcessType.PROCESS_TYPE_NORMAL));

        Process createdProcess = processDAO.create(process);

        // Creating SFTP Folder by RUT.
        try {
            sftpManager.createDefaultFoldersBySFTPParameters(createdProcess.getHost(), Integer.valueOf(createdProcess.getPort()),createdProcess.getUser(), createdProcess.getPassword(), createdProcess.getBrokerRut());
        } catch (SFTPConnectionException e) {
            LOGGER.info("Hubo un error al crear la carpeta en el SFTP, pero el usuario se creó de igual forma.");
            e.printStackTrace();
        }

        return createdProcess;
    }

    private Timer fixTimerObject(Timer timer) {
        if(timer.getDays().size() == 7)
            timer.setDay("*");
        else if(timer.getDays().size() == 0)
            timer.setDay("0");
        else {
            String dayFormatted = "";
            for (String day : timer.getDays()) {
                dayFormatted += day + ",";
            }

            if(timer.getDays().size() > 0 )
                dayFormatted = dayFormatted.substring(0, dayFormatted.length() - 1);

            timer.setDay(dayFormatted);
        }

        if(timer.getMonths().size() == 12)
            timer.setMonth("*");
        else if(timer.getMonths().size() == 0)
            timer.setMonth("0");
        else {
            String monthFormatted = "";
            for (String month : timer.getMonths()) {
                monthFormatted += month + ",";
            }

            if(timer.getMonths().size() > 0 )
                monthFormatted = monthFormatted.substring(0, monthFormatted.length() - 1);

            timer.setMonth(monthFormatted);
        }

        if(timer.getParsedDate() != null){
            SimpleDateFormat hourParser = new SimpleDateFormat("HH");
            SimpleDateFormat minuteParser = new SimpleDateFormat("mm");

            timer.setHour(hourParser.format(timer.getParsedDate()));
            timer.setMinute(minuteParser.format(timer.getParsedDate()));
            timer.setSecond("00");
        }

        return timer;
    }

    public Process update(Process process) {
        Timer updatedProcessTimer = timerDAO.update(fixTimerObject(process.getProcessTimer()));
        Timer updatedLogTimer = null;
        if(process.isAutomaticSendPreingreso())
            updatedLogTimer = timerDAO.update(fixTimerObject(process.getLogTimer()));

        process.setProcessTimer(updatedProcessTimer);
        process.setProcessTimerId(updatedProcessTimer.getId());
        if(updatedLogTimer != null){
            process.setLogTimer(updatedLogTimer);
            process.setLogTimerId(updatedLogTimer.getId());
        }

        process.setProcessTypeId((process.isMultiBroker() ? ProcessType.PROCESS_TYPE_MULTIBROKER : ProcessType.PROCESS_TYPE_NORMAL));

        return processDAO.update(process);
    }

    public Process activeProcess(Process process, boolean active) {
        process.setActive(active);

        return processDAO.update(process);
    }

    public boolean delete(Process process) {
        return processDAO.delete(process);
    }

    public Process getByMultiBrokerRut(String multiBrokerRut){
        return processDAO.getByMultiBrokerRut(multiBrokerRut);
    }

    public List<Process> findActiveProcess() {
        return processDAO.findActiveProcesses();
    }

    public List<String> getAllRegisteredRuts(){
        return processDAO.getAllRegisteredRuts();
    }
}