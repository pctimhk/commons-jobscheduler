package com.sos.VirtualFileSystem.local;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;

import com.sos.JSHelper.Basics.JSToolBox;
import com.sos.JSHelper.DataElements.JSDataElementDateTime;
import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.JSHelper.io.Files.JSFile;
import com.sos.VirtualFileSystem.FTP.SOSVfsFtpFile;
import com.sos.VirtualFileSystem.Interfaces.ISOSVfsFileTransfer;
import com.sos.VirtualFileSystem.Interfaces.ISOSVirtualFile;
import com.sos.VirtualFileSystem.common.SOSVfsMessageCodes;
import com.sos.i18n.annotation.I18NResourceBundle;

/**
* \class SOSVfsLocalFile
*
* \brief SOSVfsLocalFile -
*
* \details
*
* \section SOSVfsLocalFile.java_intro_sec Introduction
*
* \section SOSVfsLocalFile.java_samples Some Samples
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
* @version $Id$14.09.2010
* \see reference
*
* Created on 14.09.2010 17:36:43
 */

/**
 * @author KB
 *
 */
@I18NResourceBundle(baseName = "SOSVirtualFileSystem", defaultLocale = "en")
public class SOSVfsLocalFile extends JSFile implements ISOSVirtualFile {
	private final Logger		logger				= Logger.getLogger(SOSVfsLocalFile.class);
	private ISOSVfsFileTransfer	objVFSHandler		= null;
	private InputStream			objInputStream		= null;
	private OutputStream		objOutputStream		= null;
	private boolean				flgModeAppend		= false;
	/**
	 *
	 */
	private static final long	serialVersionUID	= 7478704922673917684L;
	private final String		conClassName		= "SOSVfsLocalFile";

	public SOSVfsLocalFile(final String pstrFileName) {
		super(pstrFileName);
	}

	/**
	 * \brief FileExists
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean FileExists() throws Exception {
		boolean flgResult = super.exists();
		return flgResult;
	}

	/**
	 * \brief delete
	 *
	 * \details
	 *
	 * \return
	 *
	 */
	@Override
	public boolean delete() {
		super.delete();
		//		SOSVfs_I_0113
		logger.debug(SOSVfsMessageCodes.SOSVfs_I_0113.params(strFileName));
		return true;
	}

	/**
	 * \brief deleteFile
	 *
	 * \details
	 *
	 * \return
	 *
	 * @throws Exception
	 */
	@Override
	public void deleteFile() {
		super.delete();

	}

	/**
	 * \brief getFile
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public ISOSVirtualFile getFile() throws Exception {
		return this;
	}

	/**
	 * \brief getFileAppendStream
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public OutputStream getFileAppendStream() {
		final String conMethodName = conClassName + "::getFileAppendStream";

		OutputStream objO = null;
		try {
			objO = new FileOutputStream(new File(strFileName), true);
		}
		catch (FileNotFoundException e) {
			String strT = SOSVfsMessageCodes.SOSVfs_E_134.params(conMethodName);
			throw new JobSchedulerException(strT, e);
		}
		return objO;
	}

	/**
	 * \brief getFileInputStream
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public InputStream getFileInputStream() {
		final String conMethodName = conClassName + "::getFileInputStream";

		//		InputStream objO = null;
		try {
			if (objInputStream == null) {
				objInputStream = new FileInputStream(new File(strFileName));
			}
		}
		catch (FileNotFoundException e) {
			String strT = SOSVfsMessageCodes.SOSVfs_E_134.params(conMethodName);
			logger.error(strT, e);
			throw new JobSchedulerException(strT + " / " + strFileName, e);
		}
		return objInputStream;
	}

	/**
	 * \brief getFileOutputStream
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public OutputStream getFileOutputStream() {
		final String conMethodName = conClassName + "::getFileOutputStream";

		// OutputStream objO = null;
		try {
			if (objOutputStream == null) {
				objOutputStream = new FileOutputStream(new File(strFileName), flgModeAppend);
			}
		}
		catch (FileNotFoundException e) {
			String strT = SOSVfsMessageCodes.SOSVfs_E_134.params(conMethodName);
			throw new JobSchedulerException(strT, e);
		}
		return objOutputStream;
	}

	/**
	 * \brief getFilePermissions
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getFilePermissions() throws Exception {

		return 0;
	}

	/**
	 * \brief getFileSize
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public long getFileSize() {
		long lngSize = super.length();
		return lngSize;
	}

	/**
	 * \brief getHandler
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public ISOSVfsFileTransfer getHandler() {
		return objVFSHandler;
	}

	/**
	 * \brief getModificationTime
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public String getModificationTime() {
		Date dteModificationTime = new Date(super.lastModified());
		String strMod = new JSDataElementDateTime(dteModificationTime).FormattedValue();
		return strMod;
	}

	/**
	 * \brief getName
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public String getName() {

		return super.getPath();
	}

	/**
	 * \brief getParent
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public String getParentVfs() {
		String strT = super.getParent();
		return strT;
	}

	/**
	 * \brief getParentFile
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public ISOSVirtualFile getParentVfsFile() {
		//File strP = super.getParentFile();
		//ISOSVirtualFile objF = new SOSVfsFtpFile(strP.getAbsolutePath());
		//Wieso war hier SOSVfsFtpFile in local class?
		ISOSVirtualFile objF = new SOSVfsLocalFile(super.getParent());
		objF.setHandler(getHandler());
		return objF;
	}

	/**
	 * \brief isDirectory
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean isDirectory() {
		boolean flgResult = super.isDirectory();
		return flgResult;
	}

	/**
	 * \brief isEmptyFile
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public boolean isEmptyFile() {
		boolean flgResult = super.length() <= 0;
		return flgResult;
	}

	/**
	 * \brief notExists
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public boolean notExists() {
		boolean flgResult = super.exists() == false;
		return flgResult;
	}

	/**
	 * \brief putFile
	 *
	 * \details
	 *
	 * \return
	 *
	 * @param fleFile
	 * @throws Exception
	 */
	@Override
	public void putFile(final File pfleFile) throws Exception {
		JSToolBox.notImplemented();
	}

	/**
	 * \brief putFile
	 *
	 * \details
	 *
	 * \return
	 *
	 * @param strFileName
	 * @throws Exception
	 */
	@Override
	public void putFile(final String pstrFileName) throws Exception {
		JSToolBox.notImplemented();
	}

	/**
	 * \brief rename
	 *
	 * \details
	 *
	 * \return
	 *
	 * @param pstrNewFileName
	 */
	@Override
	public void rename(final String pstrNewFileName) {
		String strParent = super.getParent();
		
		super.renameTo(new File(pstrNewFileName));
		logger.info(SOSVfsMessageCodes.SOSVfs_I_150.params(strFileName, pstrNewFileName));
	}

	/**
	 * \brief setFilePermissions
	 *
	 * \details
	 *
	 * \return
	 *
	 * @param pintNewPermission
	 * @throws Exception
	 */
	@Override
	public void setFilePermissions(final Integer pintNewPermission) throws Exception {
		JSToolBox.notImplemented();
	}

	/**
	 * \brief setHandler
	 *
	 * \details
	 *
	 * \return
	 *
	 * @param pobjVFSHandler
	 */
	@Override
	public void setHandler(final ISOSVfsFileTransfer pobjVFSHandler) {
		objVFSHandler = pobjVFSHandler;
	}

	@Override
	public String MakeZIPFile(final String pstrZipFileNameExtension) {
		// File fleTargetFile = new File(this.fleFile.getAbsolutePath() + pstrZipFileNameExtension);
		// String strTargetFileName = fleTargetFile.getName();
		String strSourceTransferName = "";
		File fleSourceTransferFile = null;
		try {
			fleSourceTransferFile = File.createTempFile("sos", pstrZipFileNameExtension);
			// fleSourceTransferFile.deleteOnExit();
			strSourceTransferName = fleSourceTransferFile.getAbsolutePath();
		}
		catch (Exception e) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_130.params("createTempFile()"), e);
		}
		try {
			logger.info(SOSVfsMessageCodes.SOSVfs_I_264.get());
			this.compressFile(fleSourceTransferFile);
		}
		catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_130.params("SOSGZip"), e);
		}
		return strSourceTransferName;
	}

	public void compressFile(final File outputFile) throws Exception {

		BufferedInputStream in = null;
		GZIPOutputStream out = null;

		try {
			in = new BufferedInputStream(new FileInputStream(fleFile));
			out = new GZIPOutputStream(new FileOutputStream(outputFile));
			// TODO �ber Option steuern
			byte buffer[] = new byte[60000];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}
		catch (Exception e) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_134.params("GZip"), e);
		}
		finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			}
			catch (Exception e) {
			}
		}
	}

	@Override
	public void close() {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::close";

		if (objOutputStream != null) {
			this.closeOutput();
		}
		else {
			if (objInputStream != null) {
				this.closeInput();
			}
		}
	}

	@Override
	public void closeInput() {
		final String conMethodName = conClassName + "::closeInput";

		try {
			if (objInputStream != null) {
				objInputStream.close();
			}
		}
		catch (IOException e) {
			logger.error(e.getLocalizedMessage());
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_134.params(conMethodName), e);
		}
		finally {
			objInputStream = null;
		}
	}

	@Override
	public void closeOutput() {
		final String conMethodName = conClassName + "::closeOutput";

		try {
			this.getFileOutputStream().flush();
			this.getFileOutputStream().close();
		}
		catch (IOException e) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_134.params(conMethodName), e);
		}
		finally {
			objOutputStream = null;
		}
	}

	@Override
	public void flush() {
		final String conMethodName = conClassName + "::flush";

		try {
			this.getFileOutputStream().flush();
		}
		catch (IOException e) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_134.params(conMethodName), e);
		}

	}

	@Override
	public int read(final byte[] bteBuffer) {
		final String conMethodName = conClassName + "::read";

		int lngBytesRed = 0;
		try {
			lngBytesRed = this.getFileInputStream().read(bteBuffer);
		}
		catch (IOException e) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_134.params(conMethodName), e);
		}
		return lngBytesRed;
	}

	@Override
	public int read(final byte[] bteBuffer, final int intOffset, final int intLength) {
		final String conMethodName = conClassName + "::read";

		int lngBytesRed = 0;
		try {
			lngBytesRed = this.getFileInputStream().read(bteBuffer, intOffset, intLength);
		}
		catch (IOException e) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_134.params(conMethodName), e);
		}
		return lngBytesRed;
	}

	@Override
	public void write(final byte[] bteBuffer, final int intOffset, final int intLength) {
		final String conMethodName = conClassName + "::write";

		try {
			this.getFileOutputStream().write(bteBuffer, intOffset, intLength);
		}
		catch (IOException e) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_134.params(conMethodName), e);
		}
	}

	@Override
	public void write(final byte[] bteBuffer) {
		final String conMethodName = conClassName + "::write";

		try {
			this.getFileOutputStream().write(bteBuffer);
		}
		catch (IOException e) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_134.params(conMethodName), e);
		}

	}

	@Override
	public void String2File(final String pstrContent) {

		try {
			OutputStream objOS = this.getFileOutputStream();
			objOS.write(pstrContent.getBytes());
			objOS.close();
		}
		catch (IOException e) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_130.params("String2File"), e);
		}

	}

	@Override
	public String File2String() {
		InputStream objFI = this.getFileInputStream();
		if (objFI == null) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_265.get());
		}
		StringBuffer strB = new StringBuffer((int) this.getFileSize());
		// TODO bufferSize
		int lngBufferSize = 1024;
		byte[] buffer = new byte[lngBufferSize];
		int intBytesTransferred;
		try {
			while ((intBytesTransferred = objFI.read(buffer)) != -1) {
				strB.append(new String(buffer).substring(0, intBytesTransferred));
			}
			objFI.close();
		}
		catch (Exception e) {
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_134.params("File2String"), e);
		}
		return strB.toString();
	}

	@Override
	public void putFile(final ISOSVirtualFile pobjVirtualFile) throws Exception {
		boolean flgClosingDone = false;
		long lngTotalBytesTransferred = 0;
		try {
			int lngBufferSize = 4096; // objOptions.BufferSize.value();
			byte[] buffer = new byte[lngBufferSize];
			int intBytesTransferred;
			synchronized (this) {
				while ((intBytesTransferred = pobjVirtualFile.read(buffer)) != -1) {
					try {
						this.write(buffer, 0, intBytesTransferred);
					}
					catch (JobSchedulerException e) {
						break;
					}
					// TODO in case of wrong outputbuffer tha handling of the error must be improved
					lngTotalBytesTransferred += intBytesTransferred;
				}
			}
			pobjVirtualFile.closeInput();
			this.closeOutput();
			flgClosingDone = true;
		}
		catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new JobSchedulerException(SOSVfsMessageCodes.SOSVfs_E_266.get(), e);
		}
		finally {
			if (flgClosingDone == false) {
				pobjVirtualFile.closeInput();
				this.closeOutput();
				flgClosingDone = true;
			}
		}
	}

	@Override
	public void setModeAppend(final boolean pflgModeAppend) {
		flgModeAppend = pflgModeAppend;
	}

	@Override
	public long getModificationDateTime() {
		long lngR = 0;
		try {
			lngR = new File(strFileName).lastModified();
		}
		catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			lngR = -1;
		}

		return lngR;
	}

	@Override
	public long setModificationDateTime(final long pdteDateTime) {
		long lngR = 0;
		try {
			File fleF = new File(strFileName);
			fleF.setLastModified(pdteDateTime);
			lngR = pdteDateTime;
		}
		catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			lngR = -1;
		}

		return lngR;
	}

	@Override
	public void setModeRestart(final boolean pflgModeRestart) {
//		flgModeRestart  = pflgModeRestart;
	}

	@Override
	public void setModeOverwrite(final boolean pflgModeOverwrite) {
//		flgModeOverwrite  = pflgModeOverwrite;
	}


}
