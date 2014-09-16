/**
 * 
 */
package com.sos.JSHelper.io.Files;

import com.sos.JSHelper.Basics.JSToolBox;

/**
 * @author KB
 *
 */
public class SOSProfileBaseClass<T> extends JSToolBox {

	protected T				objParent	= null;

	protected StringBuffer	strComment	= null;
	protected boolean		flgIsDirty	= false;

	protected String		strName		= "";
	protected String		strValue;

	/**
	 * 
	 */
	public SOSProfileBaseClass() {
	}

	/**
	 * @return
	 */
	public String Name() {
		return strName;
	}

	/**
	 * @param string
	 */
	public void Name(String string) {
		strName = string;
		setDirty();
	}

	public boolean setNotDirty() {
		flgIsDirty = false;
		return flgIsDirty;
	}
	
	@SuppressWarnings("unchecked")
	public boolean setDirty() {
		this.flgIsDirty = true;
		((SOSProfileBaseClass<T>) objParent).setDirty();
		return flgIsDirty;
	}

	public boolean isDirty() {
		return flgIsDirty;
	}

	public void setComment(final StringBuffer pstrBuffer) {
		this.strComment = pstrBuffer;
	}

	public StringBuffer getComment() {
		return this.strComment;
	}

	/**
	 * @return the objParent
	 */
	public T getObjParent() {
		return objParent;
	}

	/**
	 * @param objParent the objParent to set
	 */
	public void setObjParent(T objParent) {
		this.objParent = objParent;
	}
}
