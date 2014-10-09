package com.sos.JSHelper.Options;
import java.util.Vector;

/**
 * \class JSOptionValueList - Klasse f�r eine Option mit Einem String-Array als Wertemenge
 *
 * \brief JSOptionValueList - Klasse f�r eine Option mit Einem String-Array als Wertemenge
 *
 * \details
 *
 * Mit dieser Klasse wird eine M�glichkeit implementiert, eine aufz�hlbare
 * Menge von Strings in einer Option als Wert zu halten und
 * abzufragen bzw. auszuwerten.
 *
 * \code
			JSOptionValueList objVL = new JSOptionValueList( objOptions, "ValueList", "Descr",
					"Das|ist|das|Haus|vom|Nikolaus", "", false);

			System.out.println("Nikolaus ist enthalten: " + objVL.contains("Nikolaus"));
			System.out.println("klaus ist enthalten: " + objVL.contains("kolaus"));

			objVL.Value("Das|ist|das|Haus|vom|Nikolaus");
			System.out.println("Nikolaus ist enthalten: " + objVL.contains("Nikolaus"));
			System.out.println("klaus ist enthalten: " + objVL.contains("kolaus"));

			for (int i = 0; i < objVL.value().length; i++) {
				System.out.println (String.format("Index %1$d hat den Wert '%2$s'", i, objVL.ElementAt(i)));
			}
 * \endcode
 * \code
17:04:10 Descr: changed from '' to 'Das|ist|das|Haus|vom|Nikolaus'.
Nikolaus ist enthalten: true
klaus ist enthalten: false
17:04:10 Descr: changed from 'Das|ist|das|Haus|vom|Nikolaus' to 'Das|ist|das|Haus|vom|Nikolaus'.
Nikolaus ist enthalten: true
klaus ist enthalten: false
Index 0 hat den Wert 'Das'
Index 1 hat den Wert 'ist'
Index 2 hat den Wert 'das'
Index 3 hat den Wert 'Haus'
Index 4 hat den Wert 'vom'
Index 5 hat den Wert 'Nikolaus'

 * \endcode
 *
 * <p style="text-align:center">
 * <br />---------------------------------------------------------------------------
 * <br /> APL/Software GmbH - Berlin
 * <br />##### generated by ClaviusXPress (http://www.sos-berlin.com) #########
 * <br />---------------------------------------------------------------------------
 * </p>
 * \author eqbfd
* @version $Id$26.01.2009
 * \see reference
 *
 * Created on 26.01.2009 08:02:40
 */
/**
 * @author eqbfd
 *
 */
public class JSOptionValueList extends SOSOptionString {
	public static final String	conValueListDelimiters	= "[;,|]";
	/**
	 *
	 */
	private static final long	serialVersionUID	= -402205746280480952L;
	private final String		conClassName		= "JSOptionValueList";
	protected String[]			strValueList;
	public final String			ControlType			= "combo";

	@Override public String getControlType() {
		return ControlType;
	}

	/**
	 * \brief JSOptionValueList
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
	public JSOptionValueList(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue,
			final String pPstrDefaultValue, final boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
		valueList(pPstrValue.split(conValueListDelimiters));
	}

	/**
	 *
	 * \brief JSOptionValueList
	 *
	 * \details
	 * Initialisiert das String-Array anhand des IndexedKey
	 * IndexedKey ist eine Option in den Settings, die unter diesem Key mehrfach vorkommen kann.
	 * Die Keys aller Optionen haben den Aufbau: IndexedKey, IndexedKey1 .. IndexedKeyN
	 * L�cken im Index sind nicht zugelassen.
	 *
	 * @param pPobjParent
	 * @param pPstrKey
	 * @param pPstrDescription
	 * @param pPstrDefaultValue
	 * @param pPflgIsMandatory
	 */
	public JSOptionValueList(final JSOptionsClass pobjParent, final String pstrIndexedKey, final String pstrDescription, final String pstrDefaultValue,
			final boolean pflgIsMandatory) {
		super(pobjParent, pstrIndexedKey, pstrDescription, null, pstrDefaultValue, pflgIsMandatory);
		// - <remark who='EQALS' when='Dienstag, 6. Oktober 2009' id='PublishSQLStatement' >
		if (isNotEmpty(pstrIndexedKey)) {
			IndexedKey(pstrIndexedKey);
		}
	}

	private void IndexedKey(final String pstrIndexedKey) {
		String strT;
		final Vector<String> objValueList = new Vector<String>();
		final StringBuffer sb = new StringBuffer();
		strT = objParentClass.getItem(pstrIndexedKey);
		if (strT != null) {
			objValueList.addElement(strT);
		}
		int i = 1;
		while ((strT = objParentClass.getItem(pstrIndexedKey + Integer.toString(i++))) != null) {
			objValueList.addElement(strT);
			if (i > 2) {
				sb.append(";");
			}
			sb.append(strT);
		}
		strValueList = objValueList.toArray(new String[0]);
	}

	// - </newcode>
	// - </remark> <!-- id=<PublishSQLStatement> -->
	// - </newcode>
	// - </remark> <!-- id=<MehrereExportSQL> -->
	/**
	 *
	 * \brief ElementAt - liefert das n-te Element aus der Liste
	 *
	 * \details
	 * Die Methode liefert das n-te Element aus der Liste.
	 * Falls der Index ausserhalb des g�ltigen g�ltigen Bereichs ist,
	 * wird ein leerer String geliefert.
	 *
	 * \return String - Der Inhalt des n-ten Elementes
	 *
	 * @param pintIdx
	 */
	public String ElementAt(final int pintIdx) {
		@SuppressWarnings("unused") final String conMethodName = conClassName + "::ElementAt";
		if (strValueList == null) {
			return "";
		}
		if (pintIdx >= 0 && pintIdx <= strValueList.length) {
			return strValueList[pintIdx];
		}
		return "";
	} // public String ElementAt}

	/**
	 *
	 * \brief Value -
	 *
	 * \details
	 * Mit dieser Methode wird ein einzelner Wertestring, bei dem
	 * die Einzelwerte durch ";" oder "|" getrennt sind, �bergeben.
	 *
	 * Die Einzelwerte werden in der ValueList (abzurufen �ber die Methode "value")
	 * gespeichert.
	 * Der Wertestring bleibt �ber die Methode "Value" erreichbar.
	 *
	 * Ein Beispiel:
	 * \code
	 * 			objVL.Value("Das|ist|das|Haus|vom|Nikolaus");
	 *			System.out.println("Nikolaus ist enthalten: " + objVL.contains("Nikolaus"));
	 *			System.out.println("klaus ist enthalten: " + objVL.contains("kolaus"));
	 * \endcode
	 * \return
	 *
	 * @param pstrValueList
	 * @throws Exception
	 */
	@Override public void Value(final String pstrValueList) {
		@SuppressWarnings("unused") final String conMethodName = conClassName + "::Value";
		String strT = pstrValueList;
		if (isNotEmpty(pstrValueList) && (pstrValueList.contains(";") || pstrValueList.contains("|") || pstrValueList.contains(","))) {
			strValueList = strT.split(conValueListDelimiters);
			strT = strValueList[0];
		}
		//JITL-93 oh 27.06.14, sonst strValueList immer leer nach Constructor-Aufruf bei z.B. ignore_ora_messages
		else if(isNotEmpty(pstrValueList) && (isNull(strValueList) || strValueList.length == 0)) { 
			strValueList = new String[] { pstrValueList };
		}
		super.Value(strT);
		// return void;
	} // public void Value}

	/**
	 *
	 * \brief AppendValue
	 *
	 * \details
	 *
	 * \return void
	 *
	 * @param pstrValueList
	 * @throws Exception
	 */
	public void AppendValue(final String pstrValueList) throws Exception {
		// final String strT = pstrValueList;
		if (isNotEmpty(pstrValueList)) {
			if (isNotEmpty(super.Value())) {
				strValue += ";" + pstrValueList;
				String[] strarrT = pstrValueList.split(conValueListDelimiters);
				int intLengthT = strarrT.length;
				int intLengthActual = strValueList.length;
				String[] strNew = new String[intLengthT + intLengthActual];
				System.arraycopy(strValueList, 0, strNew, 0, intLengthActual);
				System.arraycopy(strarrT, 0, strNew, intLengthActual, intLengthT);
				strValueList = strNew;
			}
			else {
				super.Value(pstrValueList);
				strValueList = pstrValueList.split(conValueListDelimiters);
			}
//			if (strValueList == null) {
//				strValueList = new String[] {};
//				super.strValue = pstrValueList;
//			}
//			final int intLengthActual = strValueList.length;
//			final String[] strNew = new String[intLengthActual + 1];
//			System.arraycopy(strValueList, 0, strNew, 0, intLengthActual);
//			strNew[intLengthActual] = pstrValueList;
//			strValueList = strNew;
//			super.strValue += ";" + pstrValueList.replace(";", "");
		}
	}

	/**
	 *
	 * \brief value
	 *
	 * \details
	 *
	 * \return void
	 *
	 * @param pstrValueArray
	 */
	public void valueList(final String[] pstrValueArray) {
		@SuppressWarnings("unused") final String conMethodName = conClassName + "::value";
		strValueList = pstrValueArray;
		// return void;
	} // public void value}

	/**
	 *
	 * \brief value - liefert die ValueList als String-Array
	 *
	 * \details
	 * Diese Methode liefert die ValueList als StringArray.
	 *
	 * Ein Beispiel:
	 * \code
	 * 			for (int i = 0; i < objVL.value().length; i++) {
	 *				System.out.println (String.format("Index %1$d hat den Wert '%2$s'", i, objVL.ElementAt(i)));
	 *			}
	 * \endcode
	 *
	 * \return String[] - die ValueList oder eine Liste mit einem leeren Element
	 *
	 * @return
	 */
	public String[] valueList() {
		@SuppressWarnings("unused") final String conMethodName = conClassName + "::value";
		if (strValueList == null) {
			strValueList = new String[] { "" };
		}
		return strValueList;
	} // public String[] value}

	/**
	 *
	 * \brief concatenatedValue
	 *
	 * \details
	 * Einzelne Strings mit angegebenen Delimiter verbinden.
	 *
	 * \return String
	 *
	 * @param pstrDelimiter
	 * @return
	 */
	// - <remark who='EQALS' when='Freitag, 8. Mai 2009' id='MehrereExportSQL' >
	/**
	 * \change Freitag, 8. Mai 2009 EQALS MehrereExportSQL
	 * Mehr als ExportSQLs verarbeiten k�nnen
	 */
	// - <newcode>
	public String concatenatedValue(final String pstrDelimiter) {
		final StringBuffer strB = new StringBuffer();
		String strT = "";
		if (strValueList == null) {
			return strT;
		}
		for (int i = 0; i < strValueList.length; i++) {
			strT = strValueList[i];
			if (i > 0) {
				if (!strValueList[i - 1].trim().endsWith(pstrDelimiter)) {
					strB.append(pstrDelimiter);
				}
			}
			strB.append(strT);
		}
		return strB.toString();
	}

	// - </newcode>
	// - </remark> <!-- id=<MehrereExportSQL> -->
	/**
	 *
	 * \brief contains
	 *
	 * \details
	 * Mit dieser Methode wird gepr�ft, ob in der ValueList ein bestimmter, als
	 * Parameter anzugebender, String enthalten ist.
	 *
	 * Der String wird im Ganzen verglichen.
	 * Regul�re Ausdr�cke sind nicht vorgesehen.
	 *
	 * Es wird nicht zwischen Gro�- und Kleinschreibung unterschieden.
	 * Ein Beispiel:
	 * \code
	 * 			objVL.Value("Das|ist|das|Haus|vom|Nikolaus");
	 *			System.out.println("Nikolaus ist enthalten: " + objVL.contains("Nikolaus"));
	 *			System.out.println("klaus ist enthalten: " + objVL.contains("kolaus"));
	 * \endcode
	 *
	 * \return boolean
	 *
	 * @param pstrValue2Find
	 * @return
	 */
	public boolean contains(final String pstrValue2Find) {
		@SuppressWarnings("unused") final String conMethodName = conClassName + "::contains";
		final boolean flgFound = false;
		if (strValueList == null) {
			return flgFound;
		}
		for (final String element : strValueList) {
			if (pstrValue2Find.equalsIgnoreCase(element)) {
				return true;
			}
		}
		return flgFound;
	} // public boolean contains

	/**
	 *
	 * \brief IndexOf - liefert den Index des Elements f�r einen Wert
	 *
	 * \details
	 * Der Index eines anzugebenden Wertes wird zur�ckgegeben.
	 * Ist der Wert nicht in der ValueList enthalten, so
	 * wird -1 zur�ckgegeben.
	 *
	 * Es wird nicht zwischen Gro�- und Kleinschreibung unterschieden.
	 * \return int - Index oder -1
	 *
	 * @param pstrValue2Find
	 */
	public int IndexOf(final String pstrValue2Find) {
		@SuppressWarnings("unused") final String conMethodName = conClassName + "::IndexOf";
		for (int i = 0; i < strValueList.length; i++) {
			if (pstrValue2Find.equalsIgnoreCase(strValueList[i])) {
				return i;
			}
		}
		return -1;
	} // public int IndexOf

	public String[] ValueList() {
		@SuppressWarnings("unused") final String conMethodName = conClassName + "::ValueList";
		return strValueList;
	} // public String[] ValueList
}
