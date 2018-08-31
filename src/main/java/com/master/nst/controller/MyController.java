package com.master.nst.controller;

import com.master.nst.elasticsearch.service.EmployeeElasticService;
import com.master.nst.repository.CourseRepository;
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
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.sql.SQLException;

import javax.sql.DataSource;

@RestController
public class MyController {

    @Autowired
    private DataSource mysqlDataSource;

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EmployeeElasticService employeeRepository;

    //    @RequestMapping(path = "/pdf", method = RequestMethod.GET)
    //    public ModelAndView report() {
    //
    //        JasperReportsPdfView view = new JasperReportsPdfView();
    //        view.setUrl("classpath:courseReport.jrxml");
    //        view.setApplicationContext(appContext);
    //
    //        Map<String, Object> params = new HashMap<>();
    //        params.put("datasource", courseRepository.findAll());
    //
    //        return new ModelAndView(view, params);
    //    }
    //
    //    @RequestMapping(path = "/employee/pdf", method = RequestMethod.GET)
    //    public ModelAndView employeeReport() {
    //
    //        JasperReportsPdfView view = new JasperReportsPdfView();
    //        view.setUrl("classpath:thematicUnitReport.jrxml");
    //        view.setApplicationContext(appContext);
    //
    //        Map<String, Object> params = new HashMap<>();
    //        params.put("datasource", employeeRepository.findAll());
    //
    //        return new ModelAndView(view, params);
    //    }

    @RequestMapping(path = "/pdf", method = RequestMethod.GET)
    public void report() throws JRException, SQLException {
        InputStream employeeReportStream = getClass().getResourceAsStream("/courseReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);

        JRSaver.saveObject(jasperReport, "courseReport.jasper");

        InputStream emailReportStream = getClass().getResourceAsStream("/thematicUnitReport.jrxml");
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
