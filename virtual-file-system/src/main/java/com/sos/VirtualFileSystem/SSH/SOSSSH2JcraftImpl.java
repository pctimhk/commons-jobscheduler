package com.sos.VirtualFileSystem.SSH;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Vector;

import org.apache.log4j.Logger;

import sos.spooler.Variable_set;

import com.sos.JSHelper.Basics.JSJobUtilities;
import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.JSHelper.interfaces.ISOSConnectionOptions;
import com.sos.JSHelper.interfaces.ISOSDataProviderOptions;
import com.sos.VirtualFileSystem.DataElements.SOSFileList;
import com.sos.VirtualFileSystem.DataElements.SOSFolderName;
import com.sos.VirtualFileSystem.Interfaces.ISOSAuthenticationOptions;
import com.sos.VirtualFileSystem.Interfaces.ISOSConnection;
import com.sos.VirtualFileSystem.Interfaces.ISOSSession;
import com.sos.VirtualFileSystem.Interfaces.ISOSShell;
import com.sos.VirtualFileSystem.Interfaces.ISOSShellOptions;
import com.sos.VirtualFileSystem.Interfaces.ISOSVFSHandler;
import com.sos.VirtualFileSystem.Interfaces.ISOSVirtualFileSystem;
import com.sos.VirtualFileSystem.Interfaces.ISOSVirtualFolder;
import com.sos.VirtualFileSystem.Options.SOSConnection2OptionsAlternate;
import com.sos.i18n.Msg;
import com.sos.i18n.Msg.BundleBaseName;
import com.sos.i18n.annotation.I18NResourceBundle;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;


/**
* \class SOSSSH2SuperClass 
* 
* \brief SOSSSH2SuperClass - 
*  
* \details
*
* \code
*   .... code goes here ...
* \endcode
*
* <p style="text-align:center">
* <br />---------------------------------------------------------------------------
* <br /> APL/Software GmbH - Berlin
* <br />##### generated by ClaviusXPress (http://www.sos-berlin.com) #########
* <br />---------------------------------------------------------------------------
* </p>
* \author UR
* @version $Id: SOSSSH2JcraftImpl.java 27533 2014-10-10 08:40:28Z ur $16.05.2010
* \see reference
*
* Created on 16.05.2010 19:17:53
 */
 
@I18NResourceBundle(baseName = "SOSVirtualFileSystem", defaultLocale = "en")
public class SOSSSH2JcraftImpl extends SOSSSH2BaseImpl implements ISOSShell, ISOSVFSHandler, ISOSVirtualFileSystem, ISOSConnection, ISOSSession {

	private final String	conClassName	= "SOSSSH2JcraftImpl";

	final private String	strEndOfLine	= System.getProperty("line.separator");



	private ISOSConnectionOptions		objCO					= null;
	private ISOSAuthenticationOptions	objAO					= null;
	private ISOSShellOptions			objSO					= null;

    private Variable_set    params  = null;
	

	private boolean						flgIsRemoteOSWindows	= false;

	private RemoteConsumer				stdoutConsumer			= null;
	private RemoteConsumer				stderrConsumer			= null;

	private OutputStream				stdin;
	private OutputStreamWriter			stdinWriter				= null;

	private Integer						exitStatus				= null;
	private String						exitSignal				= null;

	private Vector<String>				vecFilesToDelete		= new Vector<String>();

	/** ssh connection object */
    private Channel             sshChannel                                  = null;
    /** ssh session object */
    private Session             sshSession                                  = null;
    /** SFTP Client **/
    private JSch                secureChannel                               = null;
	private String host;
	private int port;
    
    
    public SOSSSH2JcraftImpl() {
        secureChannel = new JSch();
    }
  
    
	
	@Override
	public ISOSConnection Connect(final String pstrHostName, final int pintPortNumber) throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Connect";

		this.host = pstrHostName;
        this.port = pintPortNumber;
		
		return this;
	}

	/**
	 * 
	 * \brief Connect
	 * 
	 * \details
	 *
	 * \return 
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public ISOSConnection Connect() throws Exception {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Connect";

		if (objCO == null) {
			throw new JobSchedulerException(SOSVfs_F_102.get());
		}

		try {
			host = objCO.getHost().Value();
			port = objCO.getPort().value();

			logger.debug(SOSVfs_D_0102.params(host, port));
		}
		catch (Exception e) {
			try {
				this.setSshSession(null);
			}
			catch (Exception ex) {
			}
			throw e;
		}

		return this;
	}

	
	 
	protected void setSshSession(final Session psshSession) {

		if (psshSession == null) {  // null: close connection ???
			

			if (stderrConsumer != null) {
				stderrConsumer.end();
				stderrConsumer = null;
				logger.debug(SOSVfs_D_232.params("stderrConsumer"));
			}

			if (stdoutConsumer != null) {
				stdoutConsumer.end();
				stdoutConsumer = null;
				logger.debug(SOSVfs_D_232.params("stdoutConsumer"));
			}

			if (sshSession != null) {
				sshSession.disconnect();
 				sshSession = null;
				logger.debug(SOSVfs_D_232.params("sshSession"));
			}

			if (sshChannel    != null) {
				sshChannel   .disconnect();
			
				logger.debug(SOSVfs_D_232.params("sshConnection"));
			}
		}
		sshSession    = psshSession;
	}

	/**
	 * @return Returns the sshSession.
	 */
	public Session getSshSession() {
	  		return sshSession;
	}

	
	private Vector<String> getFilesToDelete() {

        if (vecFilesToDelete == null) {
            vecFilesToDelete = new Vector<String>();
        }
        return vecFilesToDelete;
    }
	
	/**
	 * 
	 * \brief createCommandScript
	 * 
	 * \details
	 *
	 * \return File
	 *
	 * @param isWindows
	 * @return
	 * @throws Exception
	 */
	@Override
	public String createScriptFile(final String pstrContent) throws Exception {
		try {
			String commandScript = pstrContent;
			logger.info("pstrContent = " + pstrContent);
			if (flgIsRemoteOSWindows == false) {
				commandScript = commandScript.replaceAll("(?m)\r", "");
			}
			logger.info(SOSVfs_I_233.params(pstrContent));
			// TODO solve via callback
			replaceSchedulerVars(flgIsRemoteOSWindows, commandScript);
			// TODO script file prefix as option
			File fleTempScriptFile = File.createTempFile("sos-sshscript", getScriptFileNameSuffix());
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fleTempScriptFile)));
			out.write(commandScript);
			out.flush();
			out.close();
			fleTempScriptFile.deleteOnExit();
			putFile(fleTempScriptFile);
// http://www.sos-berlin.com/jira/browse/JITL-17
			
			String strFileName2Return = fleTempScriptFile.getName();
			if (flgIsRemoteOSWindows == false) {
				strFileName2Return = "./" + strFileName2Return;
			}
			add2Files2Delete(strFileName2Return);
			logger.info(SOSVfs_I_253.params(fleTempScriptFile.getAbsolutePath()));
			return strFileName2Return;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void add2Files2Delete(final String pstrFilenName2Delete) {
		this.getFilesToDelete().add(pstrFilenName2Delete);
		logger.debug(String.format(SOSVfs_D_254.params(pstrFilenName2Delete)));
	}
 

	@Override
	public ISOSConnection getConnection() {
		return this;
	}

	@Override
	public ISOSSession getSession() {
		return this;
	}

	@Override
	public ISOSConnection Connect(final ISOSConnectionOptions pobjConnectionOptions) throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Connect";
		objCO = pobjConnectionOptions;
		if (objCO != null) {
			this.Connect( );
		}
		return this;
	}

	@Override
	public void CloseConnection() throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::CloseConnection";

		this.setSshSession(null);
	}

	/**
	 * 
	 * \brief Authenticate
	 * 
	 * \details
	 *
	 * \return 
	 *
	 * @param pobjAO
	 * @return
	 * @throws Exception
	 */
	@Override
	public ISOSConnection Authenticate(final ISOSAuthenticationOptions pobjAO) throws Exception {

		final String conMethodName = conClassName + "::Authenticate";

		objAO = pobjAO;
        isConnected = false;
        this.setSshSession(secureChannel.getSession(objAO.getUser().Value(), host, port));
        
		if (objAO.getAuth_method().isPublicKey()) {
			File authenticationFile = new File(objAO.getAuth_file().Value());
			if (authenticationFile.exists() == false)
				throw new JobSchedulerException(SOSVfs_E_257.params(authenticationFile.getCanonicalPath()));
			if (authenticationFile.canRead() == false)
				throw new JobSchedulerException(SOSVfs_E_258.params(authenticationFile.getCanonicalPath()));

			 secureChannel.addIdentity(authenticationFile.getAbsolutePath());			
           
		}else{
			if (objAO.getAuth_method().isPassword()) {
	            this.getSshSession().setPassword(objAO.getPassword().Value());
			}
		}
		        

      try {
           this.getSshSession().connect();
      }catch (Exception e) {
	          if (this.getSshSession() != null) {
	          try {
	               this.getSshSession().disconnect();
	               this.setSshSession(null);
	               isAuthenticated = getSshSession().isConnected();
	          }catch (Exception ex) {}
	                 throw new Exception(e.getMessage());
	           }       
			}

		if (isAuthenticated == false) {
			throw new JobSchedulerException(SOSVfs_E_235.params(conMethodName, objAO.toString()));
		}



		logger.info(SOSVfs_D_133.params(objAO.getUser().Value()));
		return this;
	}

	@Override
	public ISOSVFSHandler getHandler() {
		return this;
	}

	/**
	 * 
	 * \brief remoteIsWindowsShell
	 * 
	 * \details
	 *
	 * \return boolean
	 *
	 * @return
	 */
	@Override
	public boolean remoteIsWindowsShell() {
		Session objSSHSession = null;
		flgIsRemoteOSWindows = false;

		try {
			// TODO the testcommand should be defined by an option
			String checkShellCommand = "echo %ComSpec%";
			logger.debug(SOSVfs_D_236.get());
			objSSHSession = this.getSshSession().openSession();
			logger.debug(SOSVfs_D_0151.params(checkShellCommand));
			objSSHSession.execCommand(checkShellCommand);

			logger.debug(SOSVfs_D_163.params("stdout", checkShellCommand));
			ipsStdOut = new StreamGobbler(objSSHSession.getStdout());
			ipsStdErr = new StreamGobbler(objSSHSession.getStderr());
			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(ipsStdOut));
			String stdOut = "";
			while (true) {
				String line = stdoutReader.readLine();
				if (line == null)
					break;
				logger.debug(line);
				stdOut += line;
			}
			logger.debug(SOSVfs_D_163.params("stderr", checkShellCommand));
			BufferedReader stderrReader = new BufferedReader(new InputStreamReader(ipsStdErr));
			while (true) {
				String line = stderrReader.readLine();
				if (line == null)
					break;
				logger.debug(line);
			}
			// TODO The expected result-string for testing the os should be defined by an option
			if (stdOut.indexOf("cmd.exe") > -1) {
				logger.debug(SOSVfs_D_237.get());
				flgIsRemoteOSWindows = true;
				return true;
			}
		}
		catch (Exception e) {
			logger.debug(SOSVfs_D_239.params(e));
		}
		finally {
			if (objSSHSession != null)
				try {
					objSSHSession.close();
				}
				catch (Exception e) {
					logger.debug(SOSVfs_D_240.params(e));
				}
		}
		return false;
	}

	private String getScriptFileNameSuffix() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::getScriptFileNameSuffix";
		// TODO �ber eine Option steuern
		String strSuffix = flgIsRemoteOSWindows ? ".cmd" : ".sh";

		return strSuffix;
	} // private String getScriptFileNameSuffix

	/**
	 * 
	 * \brief putFile
	 * 
	 * \details
	 *
	 * \return void
	 *
	 * @param pfleCommandFile
	 * @return
	 * @throws Exception
	 */
	public void putFile(File pfleCommandFile) throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::putFile";
		String strFileName = pfleCommandFile.getName();

		try {
		    Channel channel = sshSession.openChannel("sftp");
	        channel.connect();
	        ChannelSftp sftp = (ChannelSftp) channel;
	        sftp.put(pfleCommandFile.getCanonicalPath(), strFileName);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	 * \brief CloseSession
	 * 
	 * \details
	 *
	 * \return 
	 *
	 * @throws Exception
	 */
	@Override
	public void CloseSession() throws Exception {

	}

	/**
	 * 
	 * \brief OpenSession
	 * 
	 * \details
	 *
	 * \return 
	 *
	 * @param pobjShellOptions
	 * @return
	 * @throws Exception
	 */
	@Override
	public ISOSSession OpenSession(final ISOSShellOptions pobjShellOptions) throws Exception {

		objSO = pobjShellOptions;
		if (objSO == null) {
			throw new JobSchedulerException(SOSVfs_E_245.get());
		}

 
        if (objSO.getSimulate_shell().value()) {
            logger.debug(SOSVfs_D_246.params("PTY"));
            sshChannel=this.getSshSession().openChannel("shell");
        }else {
            sshChannel=this.getSshSession().openChannel("exec");
        }
        sshChannel.setInputStream(System.in);
        sshChannel.setOutputStream(System.out);
        sshChannel.connect(3*1000);     
		
		
	 
		 
		return this;
	}

	private boolean Check4TimeOutOrPrompt(final long plngLoginTimeOut, final String pstrPromptTrigger) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Check4TimeOutOrPrompt";

		long now = System.currentTimeMillis();
		if (plngLoginTimeOut > 0 && lngLastTime + plngLoginTimeOut < now) {// kommt nichts mehr
			return true;
		}
		if (pstrPromptTrigger.length() > 0 && strCurrentLine.indexOf(pstrPromptTrigger) != -1) {
			logger.debug("Found login prompt " + pstrPromptTrigger);
			strCurrentLine = "";
			return true;
		}

		return false;
	} // private boolean Check4TimeOutOrPrompt

	/**
	 * 
	 * \brief ExecuteCommand
	 * 
	 * \details
	 * Executes a command which is given as parameter.
	 * \return 
	 *
	 * @param pstrCmd
	 * @throws Exception
	 */
	@Override
	public void ExecuteCommand(final String pstrCmd) throws Exception {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::ExecuteCommand";

		exitStatus = null;
		exitSignal = null;
		String strCmd = pstrCmd;

		long loginTimeout = objSO.getSimulate_shell_login_timeout().value();
		String strPromptTrigger = objSO.getSimulate_shell_prompt_trigger().Value();

		if (objSO.getSimulate_shell().value() == true) {
			stdinWriter.write(strCmd + strEndOfLine);
			stdinWriter.flush();
			boolean prompt = false;
			while (!prompt) {
				prompt = Check4TimeOutOrPrompt(loginTimeout, strPromptTrigger);
			}
			strCurrentLine = "";
			logger.debug(SOSVfs_D_163.params("stdout", strCmd));
			logger.debug(strbStdoutOutput.toString());
			strbStdoutOutput = new StringBuffer();
		}
		else { 
			// tempor�r eingebaut um zu pr�fen ob das so mit VMS geht. ur 21.6.2013
			if (flgIsRemoteOSWindows == false && !strCmd.startsWith("@") && !strCmd.startsWith("run ")) {
				strCmd = "echo $$ && " + strCmd;
			}

			this.getSshSession().execCommand(strCmd);

			logger.debug(SOSVfs_D_163.params("stdout", strCmd));
			ipsStdOut = new StreamGobbler(this.getSshSession().getStdout());
			ipsStdErr = new StreamGobbler(this.getSshSession().getStderr());
			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(ipsStdOut));
			strbStdoutOutput = new StringBuffer();
			while (true) {
				String line = stdoutReader.readLine();
				if (line == null)
					break;
				//oh 2013-10-16: Wenn SignalInfo, dann loggt SSH-Job das Stdout zweimal (hier und in sos.net.ssh.SOSSSHJob2.java:440 (checkStdOut))
				//siehe auch http://www.sos-berlin.com/jira/browse/JITL-66
				//SignalInfo(line);
				logger.debug(line);
				strbStdoutOutput.append(line + strEndOfLine);

			}

			logger.debug(SOSVfs_D_163.params("stderr", strCmd));
			BufferedReader stderrReader = new BufferedReader(new InputStreamReader(ipsStdErr));
			strbStderrOutput = new StringBuffer();
			while (true) {
				String line = stderrReader.readLine();
				if (line == null)
					break;
				logger.debug(line);
				strbStderrOutput.append(line + strEndOfLine);
			}
		}
 
		// give the session some time to end
		// TODO waitForCondition as an Option
		@SuppressWarnings("unused")
		int res = getSshSession().waitForCondition(ChannelCondition.EOF, 30 * 1000);
		
 
		
		long timeout = (2 * 60) * 1000;

		int retval = getSshSession().waitForCondition(ChannelCondition.EXIT_STATUS, timeout);

		if ((retval & ChannelCondition.TIMEOUT) != 0){
			throw new java.util.concurrent.TimeoutException();
		} else {
			try {
				exitStatus = this.getSshSession().getExitStatus();
			}
			catch (Exception e) {
				logger.info(SOSVfs_I_250.params("exit status"));
			}
		}
		
		
		try {
			exitSignal = this.getSshSession().getExitSignal();
		}
		catch (Exception e) {
			logger.info(SOSVfs_I_250.params("exit signal"));
		}

	}

	@Override
	public Integer getExitCode() {
		if (exitStatus == null) {
		    throw new RuntimeException( "Error reading exit code from SSH-Server. No exit code is available.");
	    }
		return exitStatus;
	}

	@Override
	public String getExitSignal() {
		return exitSignal;
	}

	@Override
	public StringBuffer getStdErr() throws Exception {
		return strbStderrOutput;
	}

	@Override
	public StringBuffer getStdOut() throws Exception {
		return strbStdoutOutput;
	}

	/**
	 * This thread consumes output from the remote server puts it into fields of
	 * the main class
	 */
	class RemoteConsumer extends Thread {
		private final StringBuffer	sbuf;
		private boolean			writeCurrentline	= false;
		private final InputStream		stream;
		boolean					end					= false;

		RemoteConsumer(final StringBuffer buffer, final boolean writeCurr, final InputStream str) {
			sbuf = buffer;
			writeCurrentline = true;
			stream = str;
		}

		/**
		 * 
		 * \brief addText
		 * 
		 * \details
		 *
		 * \return void
		 *
		 * @param data
		 * @param len
		 */
		private void addText(final byte[] data, final int len) {
			lngLastTime = System.currentTimeMillis();
			String outstring = new String(data).substring(0, len);
			sbuf.append(outstring);
			if (writeCurrentline) {
				int newlineIndex = outstring.indexOf(strEndOfLine);
				if (newlineIndex > -1) {
					String stringAfterNewline = outstring.substring(newlineIndex);
					strCurrentLine = stringAfterNewline;
				}
				else {
					strCurrentLine += outstring;
				}
			}
		}

		/**
		 * 
		 * \brief run
		 * 
		 * \details
		 *
		 * \return 
		 *
		 */
		@Override
		public void run() {
			byte[] buff = new byte[64];

			try {
				while (!end) {
					buff = new byte[8];
					int len = stream.read(buff);
					if (len == -1)
						return;
					addText(buff, len);
				}
			}
			catch (Exception e) {
			}
		}

		/**
		 * 
		 * \brief end
		 * 
		 * \details
		 *
		 * \return void
		 *
		 */
		public synchronized void end() {
			end = true;
		}
	} // RemoteConsumer

    @Override
    public ISOSConnection Connect(SOSConnection2OptionsAlternate pobjConnectionOptions) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISOSConnection Connect(ISOSDataProviderOptions pobjConnectionOptions) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISOSVirtualFolder mkdir(SOSFolderName pobjFolderName) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean rmdir(SOSFolderName pobjFolderName) throws IOException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SOSFileList dir(SOSFolderName pobjFolderName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SOSFileList dir(String pathname, int flag) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void doPostLoginOperations() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setJSJobUtilites(JSJobUtilities pobjJSJobUtilities) {
        // TODO Auto-generated method stub
        
    }

	 

	
}
