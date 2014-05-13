package sos.scheduler.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.sos.scheduler.model.ISOSSchedulerSocket;

/**
 * <p>Title: </p>
 * <p>Description: this class represents a simple client for the scheduler</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: SOS GmbH</p>
 * @author <a href="mailto:ghassan.beydoun@sos-berlin.com">Ghassan Beydoun</a>
 * @version $Id$
 * @Deprecated  use SOSJobSchedulerModel

 */
@Deprecated
public class SOSSchedulerCommand {

	private static final String SCHEDULER_DEFAULT_CHARSET = "ISO-8859-1";
    @SuppressWarnings("unused")
	private final String conClassName = this.getClass().getSimpleName();
	@SuppressWarnings("unused")
	private static final String conSVNVersion = "$Id$";
	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(this.getClass());
	/** host */
	private String			host		= "localhost";

	/** port: default is 44444 */
	private int				port		= 4444;

	/** protocl: default is tcp */
	private String			protocol	= "tcp";

	private Socket			socket		= null;

	private DatagramSocket	udpSocket	= null;

	/** timeout f�r getResponse in sekunden */
	private int				timeout		= 60;

	private BufferedReader	in			= null;

	private PrintWriter		out			= null;

	protected ISOSSchedulerSocket objO = null;

	public SOSSchedulerCommand() {
	}
	public SOSSchedulerCommand(final ISOSSchedulerSocket pobjOptions) {
		objO = pobjOptions;
		this.setHost(objO.getServerName());
		this.setPort(objO.getPortNumber());
		this.setProtocol(protocol);
		this.setTimeout(objO.getTCPTimeoutValue());
	}

	public SOSSchedulerCommand(final String host) {

		this.setHost(host);
	}

	public SOSSchedulerCommand(final String host, final int port) {

		this.setHost(host);
		this.setPort(port);
	}

	/**
	* @param host the hostname (or ip address) of the Job Scheduler
	* @param port the port of the Job Scheduler
	* @param protocol the connection protocol ("tcp" or "udp")
	*/
	public SOSSchedulerCommand(final String host, final int port, final String protocol) {

		this.setHost(host);
		this.setPort(port);
		this.setProtocol(protocol);
	}

	/**
	 * Sets the hostname (or ip address) of the Job Scheduler
	 * @param host
	 */
	public void setHost(final String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	/**
	 * Sets the port of the Job Scheduler
	 * @param port
	 */
	public void setPort(final int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	/**
	 * @param timeout
	 */
	public void setTimeout(final int timeout) {
		this.timeout = timeout;
	}

	/**
	 *
	 * @return Returns the protocol.
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol sets the protocol to "tcp" or "udp"
	 */
	public void setProtocol(final String protocol) {
		this.protocol = protocol;
	}

	/**
	 * connects to the scheduler
	 *
	 * @param host Job Scheduler host
	 * @param port Job Scheduler port
	 * @throws java.lang.Exception
	 */
	public void connect(final String host, final int port) throws Exception {

		if (host == null || host.length() == 0)
			throw new Exception("hostname missing.");

		if (port == 0)
			throw new Exception("port missing.");

		if (protocol.equalsIgnoreCase("udp")) {

			udpSocket = new DatagramSocket();
			udpSocket.connect(InetAddress.getByName(this.host), this.port);

		}
		else {

			socket = new Socket(host, port);
			if (this.getTimeout() > 0)
				socket.setSoTimeout(this.getTimeout() * 1000);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(),SCHEDULER_DEFAULT_CHARSET));
			out = new PrintWriter(socket.getOutputStream(), true);
		}
	}

	/**
	 * connects to the scheduler
	 *
	 * @throws java.lang.Exception
	 */
	public void connect() throws Exception {
		this.connect(host, port);
	}

	/**
	 * sends a command to the scheduler
	 *
	 * @param command XML String containing the command
	 * @throws java.lang.Exception
	 */
	public void sendRequest(String command) throws Exception {

		if (protocol.equalsIgnoreCase("udp")) {
			if (command.indexOf("<?xml") == -1) {
				command = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>" + command + "\r\n";
			}
			byte[] commandBytes = command.getBytes();
			udpSocket.send(new DatagramPacket(commandBytes, commandBytes.length, InetAddress.getByName(host), port));

		}
		else {
			if (command.indexOf("<?xml") == 0) {
				out.print(command + "\r\n");
			}
			else {
				out.print("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>" + command + "\r\n");
			}
			out.flush();
		}
	}

	/**
	 * returns the scheduler reply.
	 *
	 * @return String represents the reply of the scheduler on success,
	 * otherwise empty string
	 * @throws IOException
	 * @throws RuntimeException
	 */
	public String getResponse() throws IOException, RuntimeException {

		int b;
		StringBuffer response = new StringBuffer();

		if (in != null) {
			while ((b = in.read()) != -1) {
				if (b == 0)
					break;
				response.append((char) b);

			} // while
		}
		return response.toString();
	}

	/**
	* @param host Job Scheduler host
	* @param port Job Scheduler port
	* @param xmlCommand XML String containing the command
	* @throws Exception
	*/
	public static void sendCommand(final String host, final int port, final String xmlCommand) throws Exception {

		SOSSchedulerCommand command = null;

		try {
			command = new SOSSchedulerCommand();

			command.setHost(host);
			command.setPort(port);
			command.setProtocol("udp");

			command.connect();
			command.sendRequest(xmlCommand);

		}
		catch (Exception e) {
			throw new Exception("startJob: could not start job: " + e.getMessage());
		}
	}

	public static void addOrder(final String host, final int port, final int status, final String jobChain) throws Exception {
		sendCommand(host, port, "<add_order job_chain=\"" + jobChain + "\" state=\"" + status + "\">" + "<params></params></add_order>");
	}

	public static void startJob(final String host, final int port, final String job) throws Exception {
		sendCommand(host, port, "<job job=\"" + job + "\">");
	}
	
	public static String getResponseErrorText(final String response) throws Exception {
		String errorText = null;
		if (response != null && response.length() > 0) {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			XPath xPath = XPathFactory.newInstance().newXPath();
			Document doc = builder.parse(new InputSource(new StringReader(response)));
			errorText = (String) xPath.evaluate("/spooler/answer/ERROR/@text", doc, XPathConstants.STRING);
		}
		return errorText;
	}
	
	public static int getTCPPortFromSchedulerXML(final File objSchedulerXml) throws Exception {
		int iPort = 0;
		if (objSchedulerXml.exists()) {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			XPath xPath = XPathFactory.newInstance().newXPath();
			Document doc = builder.parse(objSchedulerXml);
			String strPort = (String) xPath.evaluate("/spooler/config/@port", doc, XPathConstants.STRING);
			if (strPort == null || strPort.length() == 0) {
				strPort = (String) xPath.evaluate("/spooler/config/@tcp_port", doc, XPathConstants.STRING);
			}
			if (strPort != null && strPort.length() > 0) {
				iPort = Integer.parseInt(strPort);
			}
		}
		return iPort;
	}

	/**
	 * close the connection
	 *
	 * @throws java.lang.Exception
	 */
	public void disconnect() throws Exception {
		if (socket != null)
			socket.close();
		if (in != null)
			in.close();
		if (out != null)
			out.close();
	}

	public static void main(final String[] args) throws Exception {

		final String USAGE = "\nUsage: java -cp com.sos.scheduler-xxx.jar:log4j-xxx.jar sos.scheduler.command.SOSSchedulerCommand"
				+ "  -host <host> -port <port> [-timeout <timeout>]  \"<xml-command>\"";
		String host = "localhost";
		String command = null;
		String schedulerXml = null;
		String errorText = null;
		int port = 0;
		int timeout = 5;
		int rc = 0;
		int argc = args.length;
		int indexEqualSign = -1;
		boolean help = false;

		for (int i = 0; i < argc; i++) {
			if (args[i].equals("-help") || args[i].equals("--help") || args[i].equals("-h")) {
				help = true;
				break;
			}
			indexEqualSign = args[i].indexOf('=');
			if (args[i].equals("-host") && i + 1 < argc) {
				host = args[i + 1];
			}
			if (args[i].startsWith("-host=")) {
				host = args[i].substring(indexEqualSign);
			}
			if (args[i].equals("-ip-address") && i + 1 < argc) {
				host = args[i + 1];
			}
			if (args[i].startsWith("-ip-address=")) {
				host = args[i].substring(indexEqualSign+1);
			}
			if (args[i].equals("-port") && i + 1 < argc) {
				port = Integer.parseInt(args[i + 1]);
			}
			if (args[i].startsWith("-port=")) {
				port = Integer.parseInt(args[i].substring(indexEqualSign+1));
			}
			if (args[i].equals("-tcp-port") && i + 1 < argc) {
				port = Integer.parseInt(args[i + 1]);
			}
			if (args[i].startsWith("-tcp-port=")) {
				port = Integer.parseInt(args[i].substring(indexEqualSign+1));
			}
			if (args[i].equals("-timeout") && i + 1 < argc) {
				timeout = Integer.parseInt(args[i + 1]);
			}
			if (args[i].startsWith("-timeout=")) {
				timeout = Integer.parseInt(args[i].substring(indexEqualSign+1));
			}
			if (args[i].equals("-config") && i + 1 < argc) {
				schedulerXml = args[i + 1];
			}
			if (args[i].startsWith("-config=")) {
				schedulerXml = args[i].substring(indexEqualSign+1);
			}
			if (args[i].startsWith("<")) {
				command = args[i];
			}
		}

		if (help == true || argc == 0) {
			System.out.println(USAGE);
			System.exit(0);
		}
		
		if(port == 0 && schedulerXml != null) {
			port = SOSSchedulerCommand.getTCPPortFromSchedulerXML(new File(schedulerXml));
		}

		if (host == null || port == 0 || command == null) {
			System.err.println("invalid parameter");
			System.err.println(USAGE);
			System.exit(2);
		}

		SOSSchedulerCommand socket = null;
		try {
			socket = new SOSSchedulerCommand();
			socket.setTimeout(timeout);

			socket.connect(host, port);
			socket.sendRequest(command);
			String response = socket.getResponse();
			System.out.println(response);

			try {
				//two reasons for empty response: <modify_spooler cmd="abort_immediately... /> or security setting
				if (command.contains("<modify_spooler") && command.contains("abort_immediately")) {
					//nothing to do
				}
				else if (response == null || response.length() == 0) {
					errorText = String.format("No response from JobScheduler [%1$s:%2$d]: Please check the security settings", host, port);
				}
				else {
					errorText = SOSSchedulerCommand.getResponseErrorText(response);
				}
				if (errorText != null && errorText.length() > 0) {
					System.err.println(errorText);
					rc = 1;
				}
			}
			catch (Exception e) {
//				System.err.println(e.getMessage());
			}
		}
		catch (Exception e) {
			System.err.println(String.format("%1$s [%2$s:%3$d]", e.getMessage(), host, port));
			rc = 3;
		}
		finally {
			if (socket != null)
				socket.disconnect();
		}

		System.exit(rc);
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

}