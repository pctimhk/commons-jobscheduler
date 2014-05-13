package sos.scheduler.process;


import sos.spooler.Order;
import sos.spooler.Variable_set;
import sos.spooler.Web_service_operation;
import sos.spooler.Web_service_request;
import sos.spooler.Xslt_stylesheet;

import sos.util.SOSSchedulerLogger;
import sos.xml.SOSXMLXPath;

/**
 * <p>ProcessWebServiceRequestOrderMonitor implementiert ein Monitor-Script, das pro Auftrag vor bzw. nach dessen Verarbeitung gestartet wird.
 * Das Script wird f�r Standard Job-Klassen verwendet, an die Auftragsparameter per Web Service �bergeben werden.</p>
 * 
 * @author andreas.pueschel@sos-berlin.com
 * @since 1.0 2006-10-05 
 */

public class ProcessWebServiceRequestOrderMonitor extends ProcessOrderMonitor {


    /**
     * Initialisierung vor Verarbeitung eines Auftrags
     * @see sos.spooler.Monitor_impl#spooler_process_before()
     */
    public boolean spooler_process_before() {
                
        try { // to map order configuration to this job
            this.setLogger(new SOSSchedulerLogger(spooler_log));            
            
            if (!super.spooler_process_before()) return false;
            
            String xml_document = "";
            Order order = spooler_task.order();

            Web_service_operation operation = order.web_service_operation();
            if (operation == null) throw  new Exception( "no web service operation available" );
            
            Web_service_request request = operation.request();
            if (request == null) throw new Exception( "no web service request available" );
            spooler_log.debug3( "content of web service request:\n" + request.string_content() );

            // should the request be previously transformed ...
            if (spooler_task.params().value("request_stylesheet") != null && spooler_task.params().value("request_stylesheet").length() > 0) {
              Xslt_stylesheet stylesheet = spooler.create_xslt_stylesheet();
              stylesheet.load_file(spooler_task.params().value("request_stylesheet"));
              xml_document = stylesheet.apply_xml(request.string_content());
              spooler_log.debug3( "content of request transformation:\n" + xml_document );
            } else {
              xml_document = request.string_content();
            }
          
            SOSXMLXPath xpath = new sos.xml.SOSXMLXPath(new StringBuffer(xml_document));
            // add order parameters from request xml element /params
            Variable_set params = spooler.create_variable_set();
            params.set_xml( xpath.selectDocumentText("//params") );
            order.set_payload( params );

            spooler_log.info( "web service request accepted for order \"" + order.id() + "\"" );

            return true;
            
        } catch (Exception e) {
            spooler_log.warn("error occurred in spooler_process_before(): " + e.getMessage());
            return false;
        }
    }

}