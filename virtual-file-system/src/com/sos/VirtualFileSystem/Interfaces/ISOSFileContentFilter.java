package com.sos.VirtualFileSystem.Interfaces;



/**
* \class ISOSFileContentFilter
*
* \brief ISOSFileContentFilter -
*
* \details
*
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
* @version $Id$21.05.2010
* \see reference
*
 */

/**
 * @author KB
 *
 */
public interface ISOSFileContentFilter {

//	public SOSFilterOptions Options();
//	public ISOSFileContentFilter Options (final SOSFilterOptions pobjOptions);

	public void write (byte[] bteBuffer, int intOffset, int intLength );
	public void write (byte[] bteBuffer);
	public void write (String pstrBuffer);


	public int read (byte[] bteBuffer) ;
	public int read (byte[] bteBuffer, int intOffset, int intLength );

	public byte[] read() ;
	public String readString() ;
	public byte[] read(int intOffset, int intLength );

	public byte[] readBuffer() ;
	public byte[] readBuffer(int intOffset, int intLength );

	public void close () ;
	public void open () ;
	
	public byte[] getBuffer () ;
}