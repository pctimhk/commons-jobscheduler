package com.sos.JSHelper.Options;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.sos.JSHelper.Exceptions.JobSchedulerException;

/** @author KB */
public class SOSOptionHostName extends SOSOptionElement {

    private static final Logger LOGGER = Logger.getLogger(SOSOptionHostName.class);
    private static final long serialVersionUID = 4006670598088800990L;
    public static final String conLocalHostName = "localhost";
    public SOSOptionPortNumber objPortNumber = null;
    private SOSOptionString objH = null;
    private String strPID = null;

    public SOSOptionHostName(final JSOptionsClass pobjParent, final String pstrKey, final String pstrDescription, final String pstrValue,
            final String pstrDefaultValue, final boolean pflgIsMandatory) {
        super(pobjParent, pstrKey, pstrDescription, pstrValue, pstrDefaultValue, pflgIsMandatory);
        objH = new SOSOptionString(null, this.getXMLTagName() + "_ip", "Description", this.getHostAdress(), "0.0.0.0", false);
    }

    public SOSOptionHostName(final String pstrHostName) {
        super(pstrHostName);
    }

    @Override
    public void setValue(final String pstrHostName) {
        if ("local".equalsIgnoreCase(pstrHostName)) {
            super.setValue("localhost");
        } else {
            super.setValue(pstrHostName);
        }
    }

    public String getHostAdress() {
        String strIpAdress = "";
        if (this.isNotEmpty()) {
            try {
                strIpAdress = this.getInetAddress().getHostAddress();
            } catch (RuntimeException e) {
                throw new JobSchedulerException(String.format("RunTime Exception, HostName =' %1$s' ", strValue), e);
            }
        }
        return strIpAdress;
    }

    public InetAddress getInetAddress() {
        return getInetAddress(this.getHostFromUrl());
    }

    public InetAddress getInetAddress(final String host) {
        InetAddress objInetAdress = null;
        try {
            objInetAdress = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            throw new JobSchedulerException(String.format("Unknown host, HostName = '%1$s'", strValue), e);
        } catch (RuntimeException e) {
            throw new JobSchedulerException(String.format("RuntimeException, HostName = '%1$s'", strValue), e);
        }
        return objInetAdress;
    }

    @Override
    public String toString() {
        String strToString = "";
        if (this.isNotEmpty()) {
            String strHostAdress = "n.a.";
            try {
                strHostAdress = this.getHostAdress();
            } catch (Exception e) {
                //
            }
            strToString = this.getValue() + " (" + strHostAdress + ")";
        }
        return strToString;
    }

    public boolean ping() {
        boolean flgPingSuccessfull = false;
        try {
            flgPingSuccessfull = this.getInetAddress().isReachable(10000);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return flgPingSuccessfull;
    }

    public void setPort(final SOSOptionPortNumber pobjPortNumber) {
        objPortNumber = pobjPortNumber;
    }

    public boolean checkPortAvailable() {
        Socket s;
        try {
            s = new Socket(getInetAddress(), objPortNumber.value());
            s.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public String toXML(final String pstrXMLTagName) throws Exception {
        String strRet = "";
        String strT = this.getXMLTagName();
        try {
            this.xmlTagName(pstrXMLTagName);
            strRet = toXml();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            this.xmlTagName(strT);
        }
        return strRet;
    }

    @Override
    public String toXml() throws Exception {
        String strT = "";
        if (gflgCreateShortXML) {
            strT = super.toXml();
        } else {
            strT = "<" + this.getXMLTagName();
            strT += " mandatory=" + getQuotedValue(boolean2String(this.isMandatory()));
            if (isNotEmpty(this.getDefaultValue())) {
                strT += " default=" + getQuotedValue(this.getDefaultValue());
            }
            if (isNotEmpty(this.getTitle())) {
                strT += " title=" + getQuotedValue(this.getTitle());
            }
            strT += ">";
            if (!this.getValue().isEmpty()) {
                if (isCData) {
                    strT += "<![CDATA[" + this.getFormattedValue() + "]]>";
                } else {
                    strT += this.getValue();
                }
            }
            if (objPortNumber != null) {
                strT = strT + objPortNumber.toXml();
            }
            strT += "</" + this.getXMLTagName() + ">";
            strT += objH.toXml();
        }
        return strT;
    }

    public String getPID() {
        if (strPID == null) {
            String pid = ManagementFactory.getRuntimeMXBean().getName();
            String strA[] = pid.split("@");
            strPID = strA[0];
        }
        return strPID;
    }

    public String getHostFromUrl() {
        String host = this.getValue();
        if (this.isNotEmpty() && this.getValue().matches("[^:]+:/+([^/@]+@)?([^/:]+).*")) {
            host = this.getValue().replaceFirst("[^:]+:/+([^/@]+@)?([^/:]+).*", "$2");
        }
        return host;
    }

    public String getLocalHostIfHostIsEmpty() {
        String host = this.getHostFromUrl();
        if (this.IsEmpty()) {
            try {
                host = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                throw new JobSchedulerException(String.format("Unknown host, HostName = '%1$s'", strValue), e);
            }
        }
        return host;
    }

    public static String getLocalHost() {
        String host = conLocalHostName;
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            host = conLocalHostName;
        }
        return host;
    }

    public String getLocalHostAdressIfHostIsEmpty() {
        String strIpAdress = "";
        try {
            strIpAdress = this.getInetAddress(this.getLocalHostIfHostIsEmpty()).getHostAddress();
        } catch (RuntimeException e) {
            throw new JobSchedulerException(String.format("RunTime Exception, HostName =' %1$s' ", strValue), e);
        }
        return strIpAdress;
    }

}