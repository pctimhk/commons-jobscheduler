package com.sos.VirtualFileSystem.JCIFS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Map.Entry;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import jcifs.smb.SmbSession;

import org.apache.log4j.Logger;

import sos.util.SOSString;

import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.JSHelper.Options.SOSOptionFolderName;
import com.sos.VirtualFileSystem.Interfaces.ISOSAuthenticationOptions;
import com.sos.VirtualFileSystem.Interfaces.ISOSConnection;
import com.sos.VirtualFileSystem.Interfaces.ISOSVirtualFile;
import com.sos.VirtualFileSystem.Options.SOSConnection2OptionsAlternate;
import com.sos.VirtualFileSystem.common.SOSFileEntries;
import com.sos.VirtualFileSystem.common.SOSVfsTransferBaseClass;
import com.sos.i18n.annotation.I18NResourceBundle;

@I18NResourceBundle(baseName = "SOSVirtualFileSystem", defaultLocale = "en")
public class SOSVfsJCIFS extends SOSVfsTransferBaseClass {

    private static final Logger LOGGER = Logger.getLogger(SOSVfsJCIFS.class);
    private static final int DEFAULT_PORT = 445;
    private NtlmPasswordAuthentication authentication = null;
    private boolean isConnected = false;
    private String domain = null;
    private String currentDirectory = "";
    private boolean simulateShell = false;

    public SOSVfsJCIFS() {
        super();
    }

    @Override
    public ISOSConnection connect() {
        this.connect(connection2OptionsAlternate);
        return this;
    }

    @Override
    public ISOSConnection connect(final SOSConnection2OptionsAlternate options) {
        connection2OptionsAlternate = options;
        if (connection2OptionsAlternate == null) {
            raiseException(SOSVfs_E_190.params("connection2OptionsAlternate"));
        }
        int port = connection2OptionsAlternate.port.isDirty() ? connection2OptionsAlternate.port.value() : DEFAULT_PORT;
        this.doConnect(connection2OptionsAlternate.host.getValue(), port);
        return this;
    }

    @Override
    public ISOSConnection authenticate(final ISOSAuthenticationOptions options) {
        authenticationOptions = options;
        try {
            domain = connection2OptionsAlternate.domain.getValue();
            this.doAuthenticate(authenticationOptions);
        } catch (Exception ex) {
            throw new JobSchedulerException(ex);
        }
        return this;
    }

    @Override
    public void login(String user, final String password) {
        isConnected = false;
        try {
            userName = user;
            LOGGER.debug(SOSVfs_D_132.params(userName));
            smbLogin(domain, host, port, userName, password);
            isConnected = true;
            reply = "OK";
            LOGGER.info(SOSVfs_D_133.params(userName));
            this.logReply();
        } catch (Exception e) {
            raiseException(e, SOSVfs_E_134.params("authentication"));
        }
    }

    @Override
    public void disconnect() {
        reply = "disconnect OK";
        isConnected = false;
        this.logINFO(reply);
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void mkdir(final String path) {
        try {
            SOSOptionFolderName folderName = new SOSOptionFolderName(path);
            reply = "mkdir OK";
            LOGGER.debug(getHostID(SOSVfs_D_179.params("mkdir", path)));
            for (String subFolder : folderName.getSubFolderArray()) {
                subFolder = this.normalizePath(subFolder);
                LOGGER.debug(getHostID(SOSVfs_D_179.params("mkdir", subFolder)));
                if (!this.fileExists(subFolder)) {
                    SmbFile f = getSmbFile(subFolder);
                    f.mkdir();
                    LOGGER.debug(getHostID(SOSVfs_D_181.params("mkdir", subFolder, getReplyString())));
                } else {
                    if (!this.isDirectory(subFolder)) {
                        raiseException(SOSVfs_E_277.params(subFolder));
                    }
                }
            }
            logINFO(getHostID(SOSVfs_D_181.params("mkdir", path, getReplyString())));
        } catch (JobSchedulerException e) {
            throw e;
        } catch (Exception e) {
            reply = e.toString();
            raiseException(e, SOSVfs_E_134.params("[mkdir]"));
        }
    }

    @Override
    public void rmdir(String path) {
        try {
            reply = "rmdir OK";
            path = this.normalizePath(path);
            if (!path.endsWith("/")) {
                path += "/";
            }
            SmbFile f = getSmbFile(path);
            if (!f.exists()) {
                throw new JobSchedulerException(String.format("[rmdir] failed. Filepath '%1$s' does not exist.", f.getPath()));
            }
            if (!f.isDirectory()) {
                throw new JobSchedulerException(String.format("[rmdir] failed.  Filepath '%1$s' is not a directory.", f.getPath()));
            }
            f.delete();
            reply = "rmdir OK";
            LOGGER.info(getHostID(SOSVfs_D_181.params("rmdir", path, getReplyString())));
        } catch (JobSchedulerException e) {
            reply = e.toString();
            throw e;
        } catch (Exception e) {
            reply = e.toString();
            throw new JobSchedulerException(SOSVfs_E_134.params("[rmdir] " + path), e);
        }
    }

    @Override
    public boolean isDirectory(final String path) {
        SmbFile f = null;
        try {
            f = getSmbFile(this.normalizePath(path));
            return f.isDirectory();
        } catch (Exception e) {
            //
        }
        return false;
    }

    public boolean isHidden(final String path) {
        SmbFile f = null;
        try {
            f = getSmbFile(this.normalizePath(path));
            return f.isHidden();
        } catch (Exception e) {
            //
        }
        return false;
    }

    @Override
    public String[] listNames(String path) throws IOException {
        SmbFile f = null;
        try {
            if (path.isEmpty()) {
                path = ".";
            }
            if (!this.fileExists(path)) {
                throw new JobSchedulerException(SOSVfs_E_226.params(path));
            }
            if (!this.isDirectory(path)) {
                reply = "ls OK";
                return new String[] { path };
            }
            f = getSmbFile(this.normalizePath(path));
            String sep = path.endsWith("/") ? "" : "/";
            SmbFile[] lsResult = f.listFiles();
            String[] result = new String[lsResult.length];
            for (int i = 0; i < lsResult.length; i++) {
                SmbFile entry = lsResult[i];
                String fileName = path + sep + entry.getName();
                result[i] = fileName;
            }
            reply = "ls OK";
            return result;
        } catch (JobSchedulerException e) {
            reply = e.toString();
            throw e;
        } catch (Exception e) {
            reply = e.toString();
            throw new JobSchedulerException(e);
        }
    }

    @Override
    public long size(final String path) throws Exception {
        long size = -1;
        SmbFile f = null;
        try {
            f = getSmbFile(this.normalizePath(path));
            if (f.exists()) {
                size = f.length();
            }
        } catch (Exception e) {
            throw new JobSchedulerException(SOSVfs_E_161.params("checking size", e));
        }

        return size;
    }

    @Override
    public long getFile(final String remoteFile, final String localFile, final boolean append) {
        File transferFile = null;
        long remoteFileSize = -1;
        FileOutputStream fos = null;
        SmbFile f = null;
        SmbFileInputStream in = null;
        try {
            remoteFileSize = this.size(this.normalizePath(remoteFile));
            f = getSmbFile(this.normalizePath(remoteFile));
            in = new SmbFileInputStream(f);
            fos = new FileOutputStream(localFile, append);
            byte[] b = new byte[8192];
            int n = 0;
            int tot = 0;
            while ((n = in.read(b)) > 0) {
                fos.write(b, 0, n);
                tot += n;
            }
            in.close();
            in = null;
            fos.flush();
            fos.close();
            fos = null;
            transferFile = new File(localFile);
            if (!append && remoteFileSize > 0 && remoteFileSize != transferFile.length()) {
                throw new JobSchedulerException(SOSVfs_E_162.params(remoteFileSize, transferFile.length()));
            }
            remoteFileSize = transferFile.length();
            reply = "get OK";
            logINFO(getHostID(SOSVfs_I_182.params("getFile", this.normalizePath(remoteFile), localFile, getReplyString())));
        } catch (Exception ex) {
            reply = ex.toString();
            raiseException(ex, SOSVfs_E_184.params("getFile", this.normalizePath(remoteFile), localFile));
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ex) {
                    //
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception ex) {
                    //
                }
            }
        }
        return remoteFileSize;
    }

    @Override
    public long putFile(final String localFilePath, final String remoteFilePath) {
        long size = 0;
        SmbFile remoteFile = null;
        SmbFileOutputStream out = null;
        FileInputStream in = null;
        try {
            remoteFile = getSmbFile(this.normalizePath(remoteFilePath));
            in = new FileInputStream(localFilePath);
            out = new SmbFileOutputStream(remoteFile);
            byte[] b = new byte[8192];
            int n = 0;
            int tot = 0;
            while ((n = in.read(b)) > 0) {
                out.write(b, 0, n);
                tot += n;
            }
            reply = "put OK";
            logINFO(getHostID(SOSVfs_I_183.params("putFile", localFilePath, remoteFile.getPath(), getReplyString())));
            size = this.size(this.normalizePath(remoteFilePath));
        } catch (Exception e) {
            reply = e.toString();
            raiseException(e, SOSVfs_E_185.params("putFile()", localFilePath, remoteFilePath));
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    //
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    //
                }
            }
        }
        return size;
    }

    @Override
    public void delete(final String path) {
        try {
            if (this.isDirectory(this.normalizePath(path))) {
                throw new JobSchedulerException(SOSVfs_E_186.params(path));
            }
            SmbFile f = getSmbFile(this.normalizePath(path));
            f.delete();
        } catch (Exception ex) {
            reply = ex.toString();
            raiseException(ex, SOSVfs_E_187.params("delete", path));
        }
        reply = "rm OK";
        logINFO(getHostID(SOSVfs_D_181.params("delete", path, getReplyString())));
    }

    @Override
    public void rename(final String from, final String to) {
        SmbFile fromF = null;
        SmbFile toF = null;
        try {
            fromF = getSmbFile(this.normalizePath(from));
            toF = getSmbFile(this.normalizePath(to));
            fromF.renameTo(toF);
        } catch (Exception e) {
            reply = e.toString();
            throw new JobSchedulerException(SOSVfs_E_188.params("rename", from, to), e);
        }
        reply = "mv OK";
        LOGGER.info(getHostID(SOSVfs_I_189.params(from, to, getReplyString())));
    }

    @Override
    public void executeCommand(final String cmd) {
        LOGGER.debug("not implemented yet");
    }

    @Override
    public InputStream getInputStream(final String path) {
        SmbFile f = null;
        try {
            f = getSmbFile(this.normalizePath(path));
            return new SmbFileInputStream(f);
        } catch (Exception ex) {
            raiseException(ex, SOSVfs_E_193.params("getInputStream()", path));
            return null;
        }
    }

    @Override
    public OutputStream getOutputStream(final String path) {
        SmbFile f = null;
        try {
            f = getSmbFile(this.normalizePath(path));
            return new SmbFileOutputStream(f);
        } catch (Exception ex) {
            raiseException(ex, SOSVfs_E_193.params("getOutputStream()", path));
            return null;
        }
    }

    @Override
    public boolean changeWorkingDirectory(final String path) {
        SmbFile f = null;
        try {
            f = getSmbFile(this.normalizePath(path));
            if (!f.exists()) {
                reply = String.format("Filepath '%1$s' does not exist.", f.getPath());
                return false;
            }
            if (!f.isDirectory()) {
                reply = String.format("Filepath '%1$s' is not a directory.", f.getPath());
                return false;
            }
            currentDirectory = f.getPath();
            reply = "cwd OK";
        } catch (Exception ex) {
            throw new JobSchedulerException(SOSVfs_E_193.params("cwd", path), ex);
        } finally {
            LOGGER.debug(SOSVfs_D_194.params(path, getReplyString()));
        }
        return true;
    }

    @Override
    public ISOSVirtualFile getFileHandle(String fileName) {
        fileName = adjustFileSeparator(fileName);
        ISOSVirtualFile file = new SOSVfsJCIFSFile(fileName);
        file.setHandler(this);
        return file;
    }

    @Override
    public String getModificationTime(final String path) {
        SmbFile f = null;
        String dateTime = null;
        try {
            f = getSmbFile(this.normalizePath(path));
            if (f.exists()) {
                long lm = f.getLastModified();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateTime = df.format(new Date(lm));
            }
        } catch (Exception ex) {
            //
        }
        return dateTime;
    }

    @Override
    protected boolean fileExists(String filename) {
        SmbFile file = null;
        try {
            file = getSmbFile(this.normalizePath(filename));
            return file.exists();
        } catch (Exception e) {
            filename = file == null ? filename : file.getCanonicalPath();
            throw new JobSchedulerException(SOSVfs_E_226.params(filename), e);
        }
    }

    @Override
    protected String getCurrentPath() {
        return currentDirectory;
    }

    private String normalizePath(final String path) {
        return path.replaceAll("\\\\", "/");
    }

    private String getSmbFilePath(String path) {
        path = path.startsWith("/") ? path.substring(1) : path;
        return "smb://" + host + "/" + path;
    }

    private SmbFile getSmbFile(final String path) throws Exception {
        try {
            return new SmbFile(getSmbFilePath(path), authentication);
        } catch (Exception ex) {
            throw new JobSchedulerException("cannot get SmbFile: " + path);
        }
    }

    private void setConfigFromFiles() {
        if (!SOSString.isEmpty(connection2OptionsAlternate.configuration_files.getValue())) {
            String[] arr = connection2OptionsAlternate.configuration_files.getValue().split(";");
            for (int i = 0; i < arr.length; i++) {
                String file = arr[i].trim();
                LOGGER.info(String.format("use configuration file: %s", file));
                FileInputStream in = null;
                try {
                    in = new FileInputStream(file);
                    Properties p = new Properties();
                    p.load(in);
                    for (Entry<Object, Object> entry : p.entrySet()) {
                        String key = (String) entry.getKey();
                        String value = (String) entry.getValue();
                        LOGGER.debug(String.format("set configuration setting: %s = %s", key, value));
                        jcifs.Config.setProperty(key, value);
                    }
                } catch (Exception ex) {
                    LOGGER.warn(String.format("error on read configuration file[%s]: %s", file, ex.toString()));
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Exception ex) {
                            //
                        }
                    }
                }
            }
        }
    }

    private void smbLogin(String domain, String host, int port, String user, String password) throws Exception {
        setConfigFromFiles();
        UniAddress hostAddress = UniAddress.getByName(host);
        authentication = new NtlmPasswordAuthentication(domain, user, password);
        SmbSession.logon(hostAddress, port, authentication);
    }

    private ISOSConnection doAuthenticate(final ISOSAuthenticationOptions options) throws Exception {
        authenticationOptions = options;
        isConnected = false;
        userName = authenticationOptions.getUser().getValue();
        String password = authenticationOptions.getPassword().getValue();
        LOGGER.debug(SOSVfs_D_132.params(userName));
        try {
            smbLogin(domain, host, port, userName, password);
            isConnected = true;
        } catch (Exception ex) {
            throw new JobSchedulerException(ex);
        }
        reply = "OK";
        LOGGER.info(SOSVfs_D_133.params(userName));
        this.logReply();
        return this;
    }

    private void doConnect(final String phost, final int pport) {
        host = phost;
        port = pport;
        LOGGER.info(SOSVfs_D_0101.params(host, port));
        if (!this.isConnected()) {
            this.logReply();
        } else {
            logWARN(SOSVfs_D_0103.params(host, port));
        }
    }

    @Override
    public OutputStream getOutputStream() {
        return null;
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    @Override
    public SOSFileEntries getSOSFileEntries() {
        return sosFileEntries;
    }

    @Override
    public boolean isSimulateShell() {
        return this.simulateShell;
    }

    @Override
    public void setSimulateShell(boolean simulateShell) {
        this.simulateShell = simulateShell;
    }

}