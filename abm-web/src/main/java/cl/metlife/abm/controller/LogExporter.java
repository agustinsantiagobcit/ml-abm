package cl.metlife.abm.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.ExcelExporter;
import org.primefaces.component.export.ExporterOptions;

import javax.faces.context.FacesContext;
import java.io.IOException;

public class LogExporter extends ExcelExporter {

    public Workbook export(DataTable table, boolean pageOnly, boolean selectionOnly) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExporterOptions options =null;
        Workbook wb = this.createWorkBook();
        String sheetName = this.getSheetName(context, table);
        if (sheetName == null) {
            sheetName = table.getId();
        }

        sheetName = WorkbookUtil.createSafeSheetName(sheetName);
        if (sheetName.equals("empty") || sheetName.equals("null")) {
            sheetName = "Sheet";
        }

        Sheet sheet = wb.createSheet(sheetName);

        this.applyOptions(wb, table, sheet, options);
        this.exportTable(context, table, sheet, pageOnly, selectionOnly);

        for(int i = 0; i < table.getColumnsCount(); ++i) {
            sheet.autoSizeColumn((short)i);
        }

        return wb;
    }

}
