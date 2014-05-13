package com.sos.JSHelper.Options;


/**
* \class SOSOptionGracious 
* 
* \brief SOSOptionGracious - 
* 
* \details
*
*         <note language="en">
          <div xmlns="http://www.w3.org/1999/xhtml">
            Enables or disables error messages that are caused by a nonexistent file or directory
            being specified with the parameters <code>file</code> and respectively <code>file_spec</code>.
            <br/><br/>
            Valid values:
            <em>false, 0, off, no, n, nein, none</em>
            , 
            <em>true, 1, on, yes, y, ja, j</em>
            and
            <em>all</em>
            <br/><br/>
            The following rules apply when <code>file</code> and respectively <code>file_spec</code> contains an incorrect value:
            <table border="1" cellspacing="0">
					<tr>
						<th><code>GRACIOUS</code></th>
						<th><code>Standalone-Job</code></th>
						<th><code>Order-Job</code></th>
					</tr>
					<tr>
						<td>false, 0, off, no, n, nein, none</td>
						<td>error log,<br/>Task error</td>
						<td>error log,<br/>set_state error</td>
					</tr>
					<tr>
						<td>true, 1, on, yes, y, ja, j</td>
						<td>no error log,<br/>Task success</td>
						<td>no error log,<br/>set_state error</td>
					</tr>
					<tr>
						<td>all</td>
						<td>no error log,<br/>Task success</td>
						<td>no error log,<br/>set_state success</td>
					</tr>
				</table>
          </div>
        </note>

*         <note language="de">
          <div xmlns="http://www.w3.org/1999/xhtml">
            Schaltet Fehlermeldungen an oder aus, die aufgrund einer
            nicht existierenden Datei oder eines nicht existierenden Verzeichnisses ausgel�st werden,
            die/das mit den Parametern <code>file</code> bzw. <code>file_spec</code> spezifiziert wurde.
            <br/><br/>
            G�ltige Werte:
            <em>false, 0, off, no, n, nein, none</em>
            ,
            <em>true, 1, on, yes, y, ja, j</em>
            und
            <em>all</em>
            <br/><br/>
            Bei einer fehlerhaften Angabe in <code>file</code> bzw. <code>file_spec</code> ergibt sich folgendes Job-Verhalten:
            <table border="1" cellspacing="0">
					<tr>
						<th><code>GRACIOUS</code></th>
						<th><code>Standalone-Job</code></th>
						<th><code>Order-Job</code></th>
					</tr>
					<tr>
						<td>false, 0, off, no, n, nein, none</td>
						<td>error log,<br/>Task error</td>
						<td>error log,<br/>set_state error</td>
					</tr>
					<tr>
						<td>true, 1, on, yes, y, ja, j</td>
						<td>no error log,<br/>Task success</td>
						<td>no error log,<br/>set_state error</td>
					</tr>
					<tr>
						<td>all</td>
						<td>no error log,<br/>Task success</td>
						<td>no error log,<br/>set_state success</td>
					</tr>
				</table>
          </div>
        </note>

* \section SOSOptionGracious.java_intro_sec Introduction
*
* \section SOSOptionGracious.java_samples Some Samples
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
* \author KB
* @version $Id$28.08.2010
* \see reference
*
* Created on 28.08.2010 22:38:58
 */

public class SOSOptionGracious extends SOSOptionBoolean {
	private static final long	serialVersionUID	= -7171366137385744951L;
	protected static final String	conALL															= "all";
	protected static final String	conTRUE															= "true";

	/**
	 * Disables error messages caused by specified (source and/or target) but not existing files.
	 */
	public static final int						GRACIOUS		= 0x02;

	@SuppressWarnings("unused")
	private final String	conClassName	= "SOSOptionGracious";

	/**
	 * \brief SOSOptionGracious
	 *
	 * \details
	 *
	 * @param pPobjParent
	 * @param pPstrKey
	 * @param pPstrDescription
	 * @param pPstrValue
	 * @param pPstrDefaultValue
	 * @param pPflgIsMandatory
	 */
	public SOSOptionGracious(JSOptionsClass pPobjParent, String pPstrKey, String pPstrDescription, String pPstrValue, String pPstrDefaultValue,
			boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
		// TODO Auto-generated constructor stub
	}

	public boolean isGraciousAll() {
		return strValue != null && strValue.equalsIgnoreCase(conALL);
	}

	public boolean isGraciousTrue() {
		boolean flgResult = flgValue == true;
		return flgResult;
	}


}
