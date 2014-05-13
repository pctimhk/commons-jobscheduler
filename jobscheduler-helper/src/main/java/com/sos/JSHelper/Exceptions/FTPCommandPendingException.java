/* ---------------------------------------------------------------------------
 APL/Software GmbH - Berlin
##### generated by ClaviusXPress (http://www.sos-berlin.com) #########
2007-05-11, sgx1190 (sgx1190)
-------------------------------------------------------------------------------
<docu type="smcw" version="1.0">
<project></project>
<name>FTPCommandException</name>
<title>FTPCommandException
</title>
<description>
<para>
Zeigt Fehler bei ftp-command-Verarbeitung an
</para>
</description>
<params>
</params>
<keywords>
  <keyword>FTP</keyword>
  <keyword>Receive</keyword>
  <keyword>FTP command</keyword>
</keywords>
<categories>
<category>Receive</category>
<category>FTP</category>
</categories>
<date>2007-05-11</date>
<copyright>(c) 11.05.2007 by sgx1190</copyright>
<author>sgx1190</author>
<changes>
 <change who='sgx1190' when='2007-05-11' id='created'>
   <what>
   <para>
   created
   </para>
   </what>
 </change>
</changes>
</docu>
---------------------------------------------------------------------------- */
package com.sos.JSHelper.Exceptions;

public class FTPCommandPendingException extends JobSchedulerException {
 
	private static final long	serialVersionUID	= 1328332945376323290L;

public FTPCommandPendingException() {
    this("Error while process ftp command");  
  }
/**
 *  Constructor with message.
 * 
 * @param pstrMessage the message-text of the exception
 */

  public FTPCommandPendingException(String pstrMessage) {
    super(pstrMessage);
	this.Status(JobSchedulerException.PENDING);
//	this.MesFunction(pstrMesFunction);
//	this.MesType(pstrMesType);
//	this.Category(DataswitchException.CategoryIDocProcessing);
//	this.Typ(DataswitchException.TypeSplitIDocs);
    this.eMailSubject("FTP-problem occured.");        		
  }

}