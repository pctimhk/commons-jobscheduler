package com.sos.VirtualFileSystem.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import com.sos.VirtualFileSystem.Interfaces.ISOSCmdShellOptions;
import com.sos.VirtualFileSystem.common.SOSVfsMessageCodes;

/** \class cmdShell
 *
 * \brief cmdShell -
 *
 * \details
 *
 *
 * \code .... code goes here ... \endcode
 *
 * <p style="text-align:center">
 * <br />
 * --------------------------------------------------------------------------- <br />
 * APL/Software GmbH - Berlin <br />
 * ##### generated by ClaviusXPress (http://www.sos-berlin.com) ######### <br />
 * ---------------------------------------------------------------------------
 * </p>
 * \author KB \version $Id$ \see reference
 *
 * Created on 21.08.2011 14:56:56 */

/** @author KB */
public class cmdShell extends SOSVfsMessageCodes implements Runnable {

    // / Betrachtungen zum Thema Encoding:
    // http://www.torsten-horn.de/techdocs/encoding.htm#Codepage-Konsolenausgabe
    // private static final String strCharacterEncoding = "ISO-8859-1";
    // private static final String strCharacterEncoding = "windows-1252";
    private static final String strCharacterEncoding = "Cp1252";
    private final String conClassName = "cmdShell";
    @SuppressWarnings("unused")
    private static final String conSVNVersion = "$Id$";
    private static final Logger logger = Logger.getLogger(cmdShell.class);

    private String strStdOut = "";
    private String strStdErr = "";
    private int intCC = 0;
    String osn = System.getProperty("os.name");
    String fcp = System.getProperty("file.encoding");
    String ccp = System.getProperty("console.encoding");

    private ISOSCmdShellOptions objShellOptions = null;

    public cmdShell() {
        //
    }

    public cmdShell(final ISOSCmdShellOptions pobjOptions) {
        objShellOptions = pobjOptions;
    }

    public boolean isWindows() {

        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::isWindows";

        boolean flgOK = osn != null && osn.contains("Windows");

        return flgOK;
    } // private boolean isWindows

    public int getCC() {

        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::getCC";

        return intCC;
    } // private int getCC

    public String getStdOut() {

        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::getStdOut";

        return strStdOut;
    } // private String getStdOut

    public String getStdErr() {

        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::getStdErr";

        return strStdErr;
    } // private String getStdErr

    class OutputPipe implements Runnable {

        private final InputStream in;
        private final PrintStream out;

        /** Creates a new {@code OutputPipe}. */
        OutputPipe(final InputStream in1, final PrintStream out1) {
            in = in1;
            out = out1;
        }

        @Override
        public void run() {
            try {
                byte[] buffer = new byte[1024];
                for (int n = 0; n != -1; n = in.read(buffer)) {
                    out.write(buffer, 0, n);
                }
            } catch (IOException ex) {
                logger.error(ex.getLocalizedMessage());
            }
        }
    }

    private int executeCommand_(final String[] pstrCommand, final boolean showCommand) throws Exception {

        ByteArrayOutputStream bytStdOut = new ByteArrayOutputStream();
        ByteArrayOutputStream bytStdErr = new ByteArrayOutputStream();
        PrintStream psStdOut = new PrintStream(bytStdOut, true, strCharacterEncoding);
        PrintStream psStdErr = new PrintStream(bytStdErr, true, strCharacterEncoding);
        strStdOut = "";
        strStdErr = "";
        ProcessBuilder objShell = null;

        if (showCommand) {
            logger.debug(SOSVfs_D_0151.params(pstrCommand));
        }
        objShell = new ProcessBuilder(pstrCommand);

        final Process objCommand = objShell.start();
        createOutputPipe(objCommand.getInputStream(), psStdOut);
        createOutputPipe(objCommand.getErrorStream(), psStdErr);
        pipein(System.in, objCommand.getOutputStream());

        // wait for process to terminate and exit with process' return code
        intCC = objCommand.waitFor();
        strStdOut = bytStdOut.toString(strCharacterEncoding);
        strStdErr = bytStdErr.toString(strCharacterEncoding); // e.g. ISO-8859-1

        logger.info(strStdOut);
        logger.info(strStdErr);
        return intCC;
    }

    private String strCommand = "";

    public void setCommand(final String pstrcommand) throws Exception {
        strCommand = pstrcommand;
    }

    public int executeCommand(final String pstrcommand) throws Exception {
        int intRet = executeCommand_(createCommand(pstrcommand), true);
        return intRet;
    }

    public int executeCommand(final ISOSCmdShellOptions pobjOptions) throws Exception {
        objShellOptions = pobjOptions;
        String strCommand[] = createCommandUsingOptions();
        int intRet = executeCommand_(strCommand, true);
        return intRet;
    }

    private String[] createCommands(final String pstrComSpec, final String pstrComSpecDefault, final String pstrStartShellCommandParameter) {
        final String[] command = { " ", " ", " " };
        String strComSpec = "";
        int intCmdIndex = 0;

        String strStartShellCommandParameter = pstrStartShellCommandParameter; // "-c";
        if (objShellOptions.getStart_Shell_command().isDirty()) {
            strComSpec = objShellOptions.getStart_Shell_command().Value();
            if (strComSpec.equalsIgnoreCase("none") == false) {
                command[intCmdIndex++] = strComSpec;
                if (objShellOptions.getStart_Shell_command_Parameter().isDirty()) {
                    strStartShellCommandParameter = objShellOptions.getStart_Shell_command_Parameter().Value();
                    command[intCmdIndex++] = strStartShellCommandParameter;
                }
                command[intCmdIndex++] = objShellOptions.getshell_command().Value() + " " + objShellOptions.getCommand_Line_options().Value() + " "
                        + objShellOptions.getShell_command_Parameter().Value();
            } else {
                command[intCmdIndex++] = objShellOptions.getshell_command().Value();
                command[intCmdIndex++] = objShellOptions.getCommand_Line_options().Value() + " " + objShellOptions.getShell_command_Parameter().Value();
            }
        } else {
            strComSpec = System.getenv(pstrComSpec);  // "SHELL");
            if (strComSpec == null) {
                strComSpec = pstrComSpecDefault; // "/bin/sh";
            }
            command[intCmdIndex++] = strComSpec;
            command[intCmdIndex++] = strStartShellCommandParameter;
            command[intCmdIndex++] = objShellOptions.getshell_command().Value() + " " + objShellOptions.getCommand_Line_options().Value() + " "
                    + objShellOptions.getShell_command_Parameter().Value();
        }
        logger.debug(SOSVfs_D_230.params(strComSpec));
        return command;
    }

    private String[] createCommandUsingOptions() {

        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::createCommandUsingOptions";

        if (isWindows()) {
            return createCommands("comspec", "cmd.exe", "/C");
        } else { // Unix, Linux?
            return createCommands("SHELL", "bin.sh", "-c");
        }

    } // private String[] createCommandUsingOptions

    private String[] createCommand(final String pstrCommand) {

        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::createCommandUsingOptions";
        final String[] command = new String[2 + 1];

        if (isWindows()) {
            String strComSpec = System.getenv("comspec");
            logger.debug(SOSVfs_D_230.params(strComSpec));
            command[0] = strComSpec; // "cmd";
            command[1] = "/C";
            // command[2] = pstrcommand.replaceAll("/", "\\\\");
            command[2] = pstrCommand;
        } else { // Unix, Linux?
            String strComSpec = System.getenv("SHELL");
            if (strComSpec == null) {
                strComSpec = "/bin/sh";
            }
            logger.debug(SOSVfs_D_230.params(strComSpec));
            command[0] = strComSpec; // "/bin/sh";
            command[1] = "-c";
            command[2] = pstrCommand;
        }

        return command;
    } // private String[] createCommand

    public int executeCommandWithoutDebugCommand(final String pstrCommand) throws Exception {
        return executeCommand_(createCommand(pstrCommand), false);
    }

    private void pipein(final InputStream src, final OutputStream dest) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    int ret = -1;
                    while ((ret = src.read()) != -1) {
                        dest.write(ret);
                        dest.flush();
                    }
                } catch (IOException e) { // just exit
                }
            }
        }).start();

    }

    private void createOutputPipe(final InputStream in, final PrintStream out) {
        new Thread(new OutputPipe(in, out)).start();
    }

    @Override
    public void run() {

        try {
            this.executeCommand(strCommand);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

    }

    /*
     * public static void main(final String[] args) throws Exception { String
     * osn = System.getProperty("os.name"); String fcp =
     * System.getProperty("file.encoding"); String ccp =
     * System.getProperty("console.encoding"); logger.info(osn + ", fcp =  " +
     * fcp + ", ccp = " + ccp); // if (args.length < 1) { //
     * System.err.println("Syntax: BatchFileStarter command [command [...]]");
     * // System.exit(1); // } // command chain // final String[] command = new
     * String[2 + args.length]; cmdShell objShell = new cmdShell(); int intCC =
     * 0; intCC =
     * objShell.executeCommand("C:/Users/KB/Desktop/filezilla_start.bat"); //
     * intCC = objShell.executeCommand("dir"); // intCC =
     * objShell.executeCommand("dir bin"); // xcopy bleibt h�ngen. scheint von
     * stdin lesen zu wollen und bekommt nichts? // Mit echo 1 | sqlcmd
     * funktioniert es. Ohne geht es nicht bei �lteren Versionen. // intCC =
     * objShell.executeCommand("xcopy conf c:\\temp\\conf /F /Y"); // intCC =
     * objShell.executeCommand("xcopy bin c:\\temp\\bin /I /F /Y /J");
     * logger.debug(SOSVfs_D_231.params(intCC)); }
     */
}
