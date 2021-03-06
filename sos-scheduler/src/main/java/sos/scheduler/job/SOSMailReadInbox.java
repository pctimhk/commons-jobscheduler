package sos.scheduler.job;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.search.SubjectTerm;

import org.apache.log4j.Logger;

import sos.net.SOSMailReceiver;
import sos.net.SOSMimeMessage;
import sos.scheduler.command.SOSSchedulerCommand;
import sos.spooler.Job_chain;
import sos.spooler.Job_impl;
import sos.spooler.Order;
import sos.spooler.Variable_set;

/** @author Uwe Risse */
public class SOSMailReadInbox extends Job_impl {

    private static final Logger LOGGER = Logger.getLogger(SOSMailReadInbox.class);
    /** Settings Attribut: mailHost: Host des mail servers */
    private String mailHost = "";
    /** Settings Attribut: mailHost: Host des mail servers */
    private String mailPort = "110";
    /** Settings Attribut: mail_user: Benutzername des Email Accounts */
    private String mailUser = "";
    /** Settings Attribut: mailPassword: Passwort des Email Accounts */
    private String mailPassword = new String("");
    /** Settings Attribut: mailMessageFolder: Ordner, der gelesen werden soll */
    private String mailMessageFolder = "";
    /** Settings Attribut: mailSubjectFilter: Filter f�r Betreff */
    private String mailSubjectFilter = "";
    /** Settings Attribut: mailSubjectPattern: Regul�rer Ausdruck f�r Betreff */
    private String mailSubjectPattern = "";
    /** Settings Attribut: mailBodyPattern: Regul�rer Ausdruck f�r Body */
    private String mailBodyPattern = "";
    /** Settings Attribut: mailServerTimeout: Timeout */
    private int mailServerTimeout = 0;
    /** Settings Attribut: mailDumpDir: Verzeichnis f�r Email, die als Datei
     * gespeichert werden */
    private String mailDumpDir = "";
    /** Settings Attribut: mailJobchain: Jobkette f�r add_order */
    private String mailJobchain = "";
    /** Settings Attribut: mailOrderId: Order-id f�r add_order */
    private String mailOrderId = "";
    /** Settings Attribut: mailOrderState: State f�r add_order */
    private String mailOrderState = "";
    /** Settings Attribut: mailOrderTitle: Title f�r add_order */
    private String mailOrderTitle = "";
    /** Settings Attribut: mailServerType: Imap oder Pop3 */
    private String mailServerType = "";
    /** Settings Attribut: mailBodyAsSchedulerCommand: Den Body als Kommand an
     * einen Scheduler senden */
    private boolean mailBodyAsSchedulerCommand = false;
    /** Settings Attribut: mailAction: delete|order|dump|command */
    private String mailAction = "";
    /** Settings Attribut: mailSchedulerHost: Scheduler Host f�r Kommando aus
     * Body und add_order */
    private String mailSchedulerHost = "";
    /** Settings Attribut: mailSchedulerPort: Scheduler Port f�r Kommando aus
     * Body und add_order */
    private int mailSchedulerPort = 0;
    /** Email nach der Ausf�hrung der Aktionen auf "gelesen" setzen */
    private boolean mailSetSeen = true;
    /** Nur Emails verarbeiten, die nicht gelesen sind. Wenn True ist mailSetSeen
     * auch True */
    private boolean mailUseSeen = true;
    private SOSMailReceiver receiver;
    private int fromMail = 1;
    private final int nextMail = 19;
    private Variable_set params;
    private Pattern subjectPattern;
    private Pattern bodyPattern;
    private int httpPort;

    @Override
    public boolean spooler_init() throws Exception {
        httpPort = SOSSchedulerCommand.getHTTPPortFromScheduler(spooler);
        params = spooler_task.params();
        mailHost = getParams("mail_host", "");
        spooler_log.debug3(".. current setting [mail_host]: " + mailHost);
        mailUser = getParams("mail_user");
        spooler_log.debug3(".. current setting [mail_user]: " + mailUser);
        mailPassword = getParams("mail_password", "");
        spooler_log.debug3(".. current setting [mail_password]: " + "**********");
        mailPort = getParams("mail_port", mailPort);
        spooler_log.debug3(".. current setting [mail_port]: " + mailPort);
        mailMessageFolder = getParams("mail_message_folder", "INBOX");
        spooler_log.debug3(".. current setting [mail_message_folder]: " + mailMessageFolder);
        mailSubjectFilter = params.value("mail_subject_filter");
        spooler_log.debug3(".. current setting [mail_subject_filter]: " + mailSubjectFilter);
        mailSubjectPattern = getParams("mail_subject_pattern", "");
        spooler_log.debug3(".. current setting [mail_subject_pattern]: " + mailSubjectPattern);
        mailBodyPattern = getParams("mail_body_pattern", "");
        spooler_log.debug3(".. current setting [mail_body_pattern]: " + mailBodyPattern);
        mailBodyAsSchedulerCommand =
                "true".equalsIgnoreCase(getParams("mail_body_as_scheduler_command", "true"))
                        || "1".equalsIgnoreCase(getParams("mail_body_as_scheduler_command", "true"))
                        || "yes".equalsIgnoreCase(getParams("mail_body_as_scheduler_command", "true"));
        spooler_log.debug3(".. current setting [mail_body_as_scheduler_command]: " + mailBodyAsSchedulerCommand);
        mailJobchain = getParams("mail_jobchain", "");
        spooler_log.debug3(".. current setting [mail_jobchain]: " + mailJobchain);
        mailOrderId = getParams("mail_order_id", "");
        spooler_log.debug3(".. current setting [mail_order_id]: " + mailOrderId);
        mailOrderTitle = getParams("mail_order_title", "");
        spooler_log.debug3(".. current setting [mail_order_title]: " + mailOrderTitle);
        mailOrderState = getParams("mail_order_state", "");
        spooler_log.debug3(".. current setting [mail_order_state]: " + mailOrderState);
        mailDumpDir = params.value("mail_dump_dir");
        spooler_log.debug3(".. current setting [mail_dump_dir]: " + mailDumpDir);
        mailAction = params.value("mail_action");
        spooler_log.debug3(".. current setting [mail_action]: " + mailAction);
        mailSchedulerHost = getParams("mail_scheduler_host", spooler.hostname());
        spooler_log.debug3(".. current setting [mail_scheduler_host]: " + mailSchedulerHost);
        mailSchedulerPort = getInt(getParams("mail_scheduler_port", String.valueOf(spooler.tcp_port())), 0);
        spooler_log.debug3(".. current setting [mail_scheduler_port]: " + mailSchedulerPort);
        mailServerTimeout = getInt(getParams("mail_server_timeout"), 0);
        spooler_log.debug3(".. current setting [mail_server_timeout]: " + mailServerTimeout);
        mailServerType = getParams("mail_server_type", "POP3");
        spooler_log.debug3(".. current setting [mail_server_type]: " + mailServerType);
        mailUseSeen =
                "true".equalsIgnoreCase(getParams("mail_use_seen", "true")) || "1".equalsIgnoreCase(getParams("mail_use_seen", "true"))
                        || "yes".equalsIgnoreCase(getParams("mail_use_seen", "true"));
        mailSetSeen =
                mailUseSeen || "true".equalsIgnoreCase(getParams("mail_set_seen", "true"))
                        || "1".equalsIgnoreCase(getParams("mail_set_seen", "true")) || "yes".equalsIgnoreCase(getParams("mail_set_seen", "true"));
        spooler_log.debug3(".. current setting [mail_set_seen]: " + mailSetSeen);
        subjectPattern = Pattern.compile(mailSubjectPattern, 0);
        bodyPattern = Pattern.compile(mailBodyPattern, 0);
        return true;
    }

    @Override
    public boolean spooler_process() throws Exception {
        try {
            ArrayList<SOSMimeMessage> messages = findMessages();
            if (messages == null) {
                return false;
            }
            if (!messages.isEmpty()) {
                Iterator<SOSMimeMessage> messageIterator = messages.iterator();
                SOSMimeMessage newestMail = null;
                while (messageIterator.hasNext()) {
                    SOSMimeMessage message = messageIterator.next();
                    spooler_log.info(message.getSubject());
                    Date messageDate = message.getSentDate();
                    if (newestMail == null || messageDate.after(newestMail.getSentDate())) {
                        newestMail = message;
                    }
                    performAction(message);
                }
            }
            if (receiver != null) {
                receiver.closeFolder(true);
            }
        } catch (Exception e) {
            String stateText = e.toString();
            spooler_log.warn("an error occurred while processing: " + stateText);
            spooler_job.set_state_text(stateText);
            spooler_log.info("Job " + spooler_job.name() + " step terminated with errors.");
            spooler_task.end();
        }
        return true;
    }

    private void performAction(final SOSMimeMessage message) throws Exception {
        String action = "";
        StringTokenizer t = new StringTokenizer(mailAction, ",");
        while (t.hasMoreTokens()) {
            action = t.nextToken();
            spooler_log.debug3("Action " + action + " will be performed.");
            action = action.toLowerCase();
            if ("dump".equals(action)) {
                dumpMessage(message);
            } else if ("order".equals(action)) {
                startOrder(message, mailSchedulerHost, mailSchedulerPort, mailJobchain, mailOrderId, mailOrderState, mailOrderTitle);
            } else if ("command".equals(action)) {
                executeCommand(message, mailSchedulerHost, mailSchedulerPort);
            } else if ("delete".equals(action)) {
                deleteMessage(message);
            }
            if (mailSetSeen) {
                message.setFlag(Flags.Flag.SEEN, true);
            }
        }
    }

    private int min(final int a, final int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    private ArrayList<SOSMimeMessage> findMessages() throws Exception {
        ArrayList<SOSMimeMessage> messages = new ArrayList<SOSMimeMessage>();
        try {
            spooler_log.debug3("Connecting to Mailserver " + mailHost + ":" + mailPort + "(" + mailServerType + ")...");
            receiver = new SOSMailReceiver(mailHost, mailPort, mailUser, mailPassword);
            receiver.connect(mailServerType);
            if (mailServerTimeout > 0) {
                receiver.setTimeout(mailServerTimeout);
            }
            spooler_log.debug3("reading " + mailMessageFolder);
            Folder folder = receiver.openFolder(mailMessageFolder, receiver.READ_WRITE);
            int max = folder.getMessageCount();
            if (fromMail > max) {
                spooler_log.debug3("all messages found.");
                return null;
            }
            SubjectTerm term = null;
            Message[] msgs = null;
            Message[] msgs2 = null;
            spooler_log.debug3(max - fromMail + 1 + " messages left.");
            term = new SubjectTerm(mailSubjectFilter);
            msgs = folder.getMessages(fromMail, min(max, fromMail + nextMail));
            spooler_log.debug3(msgs.length + " messages found.");
            if (!"".equals(mailSubjectFilter)) {
                spooler_log.debug3("looking for " + mailSubjectFilter);
                msgs2 = folder.search(term, msgs);
                spooler_log.debug3(msgs2.length + " messages found with " + mailSubjectFilter);
            } else {
                msgs2 = msgs;
                spooler_log.debug3(msgs2.length + " messages found");
            }
            if (msgs2.length > 0) {
                for (Message element : msgs2) {
                    if (mailUseSeen && element.isSet(Flags.Flag.SEEN)) {
                        spooler_log.info("message skipped, already seen: " + element.getSubject());
                        continue;
                    }
                    SOSMimeMessage message = new SOSMimeMessage(element);
                    // skip mails that do not match the subject pattern
                    if (!"".equals(mailSubjectPattern)) {
                        Matcher subjectMatcher = subjectPattern.matcher(message.getSubject());
                        if (!subjectMatcher.find()) {
                            spooler_log.info("message skipped, subject does not match [" + mailSubjectPattern + "]: " + message.getSubject());
                            continue;
                        }
                    }
                    // skip mails whose body does not match the download link
                    // pattern
                    if (!"".equals(mailBodyPattern)) {
                        Matcher bodyMatcher = bodyPattern.matcher(message.getPlainTextBody());
                        if (!bodyMatcher.find()) {
                            spooler_log.info("message skipped, no match found for  [" + mailBodyPattern + "]: " + message.getPlainTextBody());
                            continue;
                        }
                    }
                    messages.add(message);
                }
            }
            fromMail = fromMail + nextMail + 1;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new Exception("Error occured querying mail server. " + e);
        }
        return messages;
    }

    private void executeCommand(final SOSMimeMessage message, final String host_, final int port_) throws Exception {
        if (mailSchedulerHost.equals(spooler.hostname()) && mailSchedulerPort == spooler.tcp_port()) {
            spooler_log.debug3("...host/port is this host and port. Using API");
            spooler.execute_xml(message.getPlainTextBody());
        } else {
            executeXml(host_, port_, message.getPlainTextBody());
        }
    }

    private void dumpMessage(final SOSMimeMessage message) throws Exception {
        if ("".equals(mailDumpDir)) {
            throw new Exception("No output directory specified.");
        }
        File messageFile = new File(mailDumpDir, message.getMessageId());
        spooler_log.debug3("saving message to file: " + messageFile.getAbsolutePath());
        message.dumpMessageToFile(messageFile, true, false);
    }

    private void deleteMessage(final SOSMimeMessage message) throws Exception {
        spooler_log.debug3("deleting message : " + message.getSubject());
        message.deleteMessage();
    }

    private int getInt(final String s, final int d) throws Exception {
        int erg = d;
        try {
            erg = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            spooler_log.warn("Wrong value for " + s + ". Using default=" + d);
            erg = d;
        }
        return erg;
    }

    private String getParams(final String v, final String d) {
        String s = params.value(v);
        if ("".equals(s)) {
            s = d;
        }
        return s;
    }

    private String getParams(final String v) throws Exception {
        String s = params.value(v);
        if ("".equals(s)) {
            throw new Exception("missing value for parameter " + v);
        }
        return s;
    }

    private void startOrder(final SOSMimeMessage message, final String host_, final int port_, final String jobchain, final String id,
            final String state, final String title) throws Exception {
        Variable_set order_params = spooler.create_variable_set();
        spooler_log.debug3("....merge");
        order_params.merge(spooler_task.params());
        order_params.set_var("mail_from", message.getFrom());
        if (message.getFromName() != null) {
            order_params.set_var("mail_from_name", message.getFromName());
        } else {
            order_params.set_var("mail_from_name", "");
        }
        order_params.set_var("mail_message_id", message.getMessageId());
        order_params.set_var("mail_subject", message.getSubject());
        order_params.set_var("mail_body", message.getPlainTextBody());
        order_params.set_var("mail_send_at", message.getSentDateAsString());
        if (mailSchedulerHost.equals(spooler.hostname()) && mailSchedulerPort == spooler.tcp_port()) {
            spooler_log.debug3("...host/port is this host and port. Using API");
            Job_chain j = spooler.job_chain(jobchain);
            spooler_log.debug3("...jobchain " + jobchain + " created.");
            Order o = spooler.create_order();
            o.params().merge(order_params);
            spooler_log.debug3("...order " + o.id() + " created.");
            if (!"".equals(state)) {
                o.set_state(state);
            }
            if (!"".equals(title)) {
                o.set_title(title);
            }
            j.add_order(o);
            spooler_log.debug3("...order added to " + jobchain);
        } else {
            startOrderXML(host_, port_, jobchain, id, state, title, order_params);
        }
    }

    private void startOrderXML(final String host_, final int port_, final String jobchain, String id, String state, String title,
            final Variable_set params_) throws Exception {
        spooler_log.debug3("Starting order " + id + " at " + jobchain + " with xml-command");
        if ("".equals(host_)) {
            throw new Exception("Missing host while starting order.");
        }
        if (port_ == 0) {
            throw new Exception("Missing port while starting order.");
        }
        if (!"".equals(id)) {
            id = "id=" + id;
        }
        if (!"".equals(state)) {
            state = "state=" + state;
        }
        if (!"".equals(title)) {
            title = "state=" + title;
        }
        String xml = "<add_order replace=yes " + id + " " + state + " job_chain=\"" + jobchain + "\">" + "<params>";
        if (params_ != null && params_.xml() != null && params_.xml().length() > 0) {
            String paramsXml = params_.xml();
            int begin = paramsXml.indexOf("<sos.spooler.variable_set>") + 26;
            int end = paramsXml.lastIndexOf("</sos.spooler.variable_set>");
            if (begin >= 26 && end >= 26) {
                xml += paramsXml.substring(begin, end).replaceAll("variable", "param");
            }
        }
        xml += "</params></add_order>";
        executeXml(host_, port_, xml);
    }

    private void executeXml(final String host_, final int port_, final String xml) throws Exception {
        SOSSchedulerCommand command;
        command = new SOSSchedulerCommand(host_, port_);
        command.setProtocol("udp");
        spooler_log.debug3("Trying connection to " + host_ + ":" + port_);
        command.connect();
        spooler_log.debug3("...connected");
        spooler_log.debug3("Sending add_order command:\n" + xml);
        command.sendRequest(xml);
    }

}
