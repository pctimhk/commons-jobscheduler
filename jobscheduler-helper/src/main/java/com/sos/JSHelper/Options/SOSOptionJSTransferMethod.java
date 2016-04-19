package com.sos.JSHelper.Options;

/** @author KB */
public class SOSOptionJSTransferMethod extends SOSOptionStringValueList {

    private static final long serialVersionUID = 559611781725991697L;

    public enum enuJSTransferModes {
        tcp("tcp"), udp("udp"), jms("jms"), http("http"), telnet("telnet");

        public final String description;

        private enuJSTransferModes() {
            this(null);
        }

        private enuJSTransferModes(final String name) {
            String k;
            if (name == null) {
                k = this.name();
            } else {
                k = name;
            }
            description = k;
        }
    }

    public SOSOptionJSTransferMethod(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue,
            final String pPstrDefaultValue, final boolean pPflgIsMandatory) {
        super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
    }

    public SOSOptionJSTransferMethod(final JSOptionsClass pobjParent, final String pstrIndexedKey, final String pstrDescription, final String pstrDefaultValue,
            final boolean pflgIsMandatory) {
        super(pobjParent, pstrIndexedKey, pstrDescription, pstrDefaultValue, pflgIsMandatory);
    }

    public boolean isTcp() {
        return this.Value().equalsIgnoreCase(enuJSTransferModes.tcp.description);
    }

    public boolean isUdp() {
        return this.Value().equalsIgnoreCase(enuJSTransferModes.udp.description);
    }

    public boolean isJMS() {
        return this.Value().equalsIgnoreCase(enuJSTransferModes.jms.description);
    }

    public String getDescription() {
        String strT = "???";
        if (this.isTcp()) {
            strT = enuJSTransferModes.tcp.description;
        }
        if (this.isUdp()) {
            strT = enuJSTransferModes.udp.description;
        }
        if (this.isJMS()) {
            strT = enuJSTransferModes.jms.description;
        }
        return strT;
    }

}