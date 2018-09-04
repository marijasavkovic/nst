package com.master.nst.controller;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@RestController
public class ReportController {

    @Autowired
    private DataSource mysqlDataSource;

    @RequestMapping(path = "/report/pdf", method = RequestMethod.GET)
    public void reportPdf(HttpServletResponse response) throws SQLException, JRException {
        report();
        try {
            InputStream is = new FileInputStream("courseReport.pdf");
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    private void report() throws JRException, SQLException {
        InputStream employeeReportStream = getClass().getResourceAsStream("/report/courseReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);

        JRSaver.saveObject(jasperReport, "courseReport.jasper");

        InputStream emailReportStream = getClass().getResourceAsStream("/report/thematicUnitReport.jrxml");
        JRSaver.saveObject(JasperCompileManager.compileReport(emailReportStream), "thematicUnitReport.jasper");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, mysqlDataSource.getConnection());

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("courseReport.pdf"));

        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("baeldung");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);

        exporter.exportReport();
    }
}
