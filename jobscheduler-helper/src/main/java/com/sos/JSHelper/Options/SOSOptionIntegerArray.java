package com.sos.JSHelper.Options;

import java.util.Vector;

import com.sos.JSHelper.Exceptions.JobSchedulerException;

/** @author KB */
public class SOSOptionIntegerArray extends SOSOptionElement {

    private static final long serialVersionUID = -7864986512950989666L;
    private Vector<Integer> result = new Vector<Integer>();

    public SOSOptionIntegerArray(JSOptionsClass pobjParent, String pstrKey, String pstrDescription, String pstrValue, String pstrDefaultValue,
            boolean pflgIsMandatory) {
        super(pobjParent, pstrKey, pstrDescription, pstrValue, pstrDefaultValue, pflgIsMandatory);
        this.setValue(pstrValue);
        this.description(pstrDescription);
    }

    public void setValue(final String pstrValue) {
        try {
            super.setValue(pstrValue);
            parseValue2Vector();
        } catch (Exception e) {
            throw new JobSchedulerException("Illegal/Invalid value '" + pstrValue + "' for Option " + this.getDescription() + ", " + this.strKey, e);
        }
    }

    public boolean contains(final int pintValue) {
        boolean flgContains = false;
        flgContains = this.getValues().contains(new Integer(pintValue));
        return flgContains;
    }

    public Vector<Integer> getValues() {
        return result;
    }

    private void parseValue2Vector() throws Exception {
        if (this.getValue() != null) {
            String[] elements = this.getValue().split("[,| |;]");
            result = new Vector<Integer>();
            for (String element : elements) {
                element = element.trim();
                if (!element.isEmpty()) {
                    if (element.indexOf("-") == -1) {
                        try {
                            result.add(new Integer(element));
                        } catch (Exception e) {
                            throw new JobSchedulerException("Illegal numeric value : " + element, e);
                        }
                    } else {
                        String[] range = element.split("-");
                        int from = 0;
                        int to = 0;
                        try {
                            from = Integer.parseInt(range[0].trim());
                            to = Integer.parseInt(range[1].trim());
                        } catch (Exception e) {
                            throw new JobSchedulerException("Illegal numeric value : " + element, e);
                        }
                        int stepSize = 1;
                        for (int j = from; j <= to; j = j + stepSize) {
                            result.add(new Integer(j));
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String strTemp = super.toString();
        if (!result.isEmpty()) {
            strTemp += " [";
            for (Integer intVal : result) {
                strTemp += intVal.toString() + " ";
            }
            strTemp += "]";
        }
        return strTemp;
    }

}