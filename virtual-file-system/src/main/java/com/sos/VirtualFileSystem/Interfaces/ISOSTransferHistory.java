package com.sos.VirtualFileSystem.Interfaces;

import com.sos.VirtualFileSystem.Options.SOSFTPOptions;

/** \class ISOSTransferHistoryExport
 * 
 * \brief ISOSTransferHistoryExport -
 * 
 * \details
 *
 *
 * \code .... code goes here ... \endcode
 *
 * <p style="text-align:center">
 * <br />
 * --------------------------------------------------------------------------- <br />
 * APL/Software GmbH - Berlin <br />
 * ##### generated by ClaviusXPress (http://www.sos-berlin.com) ######### <br />
 * ---------------------------------------------------------------------------
 * </p>
 * \author KB \version $Id$ \see reference
 *
 * Created on 18.10.2011 12:42:02 */

/** @author KB */
public interface ISOSTransferHistory {

    public void setData(SOSFTPOptions pobjOptions);

    public void doTransferDetail();

    public void doTransferSummary();

    public void setJadeTransferData(IJadeTransferHistoryData jadeTransferHistoryData);

    public void setJadeTransferDetailData(IJadeTransferDetailHistoryData jadeTransferDetailHistoryData);

    public void close();

    public String getFileName();

}
