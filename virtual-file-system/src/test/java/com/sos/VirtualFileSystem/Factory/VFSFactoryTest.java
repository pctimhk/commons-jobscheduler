package com.sos.VirtualFileSystem.Factory;

import com.sos.VirtualFileSystem.Interfaces.ISOSVFSHandler;
import com.sos.VirtualFileSystem.Interfaces.ISOSVfsFileTransfer;
import org.apache.log4j.Logger;
import org.junit.*;

import static org.junit.Assert.fail;

/**
* \class VFSFactoryTest 
* 
* \brief VFSFactoryTest - 
* 
* \details
*
* \section VFSFactoryTest.java_intro_sec Introduction
*
* \section VFSFactoryTest.java_samples Some Samples
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
* @version $Id$27.08.2010
* \see reference
*
* Created on 27.08.2010 20:30:57
 */

/**
 * @author KB
 *
 */
public class VFSFactoryTest {

	@SuppressWarnings("unused")
	private final String	conClassName	= "VFSFactoryTest";

	@SuppressWarnings("unused")
	private static Logger		logger					= Logger.getLogger(VFSFactoryTest.class);
	private ISOSVfsFileTransfer	objFileSystemHandler	= null;


	public VFSFactoryTest() {
		//
	}

	/**
	 * \brief setUpBeforeClass
	 * 
	 * \details
	 *
	 * \return void
	 *
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * \brief tearDownAfterClass
	 * 
	 * \details
	 *
	 * \return void
	 *
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * \brief setUp
	 * 
	 * \details
	 *
	 * \return void
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * \brief tearDown
	 * 
	 * \details
	 *
	 * \return void
	 *
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.sos.VirtualFileSystem.Factory.VFSFactory#VFSFactory()}.
	 */
	@Test
	public void testVFSFactory() {
		ISOSVFSHandler objVFS = null;
		
		try {
			objVFS =  VFSFactory.getHandler("ftp://kb:kb@wilma.sos:21");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Test method for {@link com.sos.VirtualFileSystem.Factory.VFSFactory#getHandler(java.lang.String)}.
	 */
//	@Test
	public void testGetHandler() {
		fail("Not yet implemented");
	}
}