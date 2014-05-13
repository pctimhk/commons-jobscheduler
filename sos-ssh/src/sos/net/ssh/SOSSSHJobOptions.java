package sos.net.ssh;
import java.util.HashMap;

import com.sos.JSHelper.Exceptions.JSExceptionMandatoryOptionMissing;
import com.sos.JSHelper.Listener.JSListener;
import com.sos.i18n.annotation.I18NResourceBundle;

/**
* \class SOSSSHJobOptions
*
* \brief SOSSSHJobOptions -
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
* @version $Id$16.05.2010
* \see reference
*
* Created on 16.05.2010 20:52:28
 */
/**
 * @author KB
 *
 */
@I18NResourceBundle(baseName = "com_sos_net_messages", defaultLocale = "en")
public class SOSSSHJobOptions extends SOSSSHJobOptionsSuperClass {
	private static final long	serialVersionUID	= 2072083231341151442L;
	private final String		conClassName		= "SOSSSHJobOptions";

	public SOSSSHJobOptions() {
		objParentClass = this.getClass();
	}

	/**
	 * \brief SOSSSHJobOptions
	 *
	 * \details
	 *
	 * @param pobjListener
	 */
	public SOSSSHJobOptions(final JSListener pobjListener) {
		super(pobjListener);
		objParentClass = this.getClass();
	}

	/**
	 * \brief SOSSSHJobOptions
	 *
	 * \details
	 *
	 * @param JSSettings
	 * @throws Exception
	 */
	public SOSSSHJobOptions(final HashMap<String, String> JSSettings) throws Exception {
		super();
		objParentClass = this.getClass();
		this.setAllOptions(JSSettings);
	}

	/**
	 *
	 * \brief CheckMandatory
	 *
	 * \details
	 *
	 * \return
	 * @throws JSExceptionMandatoryOptionMissing, Exception
	 *
	 */
	@Override
	public void CheckMandatory() {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::CheckMandatory";
		command.command_delimiter.Value(command_delimiter.Value());
		super.CheckMandatory();
		if (auth_method.isPassword() || auth_method.isPublicKey()) {
			// ok
		}
		else {
			throw new JSExceptionMandatoryOptionMissing("ErrSSH010 invalid or no AuthenticationMethod specified");
		}
		if (auth_method.isPassword()) {
			if (password.IsEmpty()) {
				throw new JSExceptionMandatoryOptionMissing("ErrSSH020 AuthenticationMethod 'password' requires a Password, but no password was specified");
			}
		}
		if (auth_method.isPublicKey()) {
			if (auth_file.IsNotEmpty()) {
				auth_file.CheckMandatory(true);
			}
			else {
				throw new JSExceptionMandatoryOptionMissing("ErrSSH050 AuthenticationMethod 'publickey' requires a keyfile, but no keyfile was specified");
			}
		}
		/**
		if (command.IsEmpty() && command_script.IsEmpty() && command_script_file.IsEmpty()) {
			throw new JSExceptionMandatoryOptionMissing("ErrSSH060 no command, command_script or command_script_file has been specified");
		}
		*/
	} // private void CheckMandatory

	public boolean commandSpecified () {
		boolean flgR = true;
		flgR = command.isDirty() && command_script.isDirty() && command_script_file.isDirty();
		return flgR;
	}
	public boolean isScript() throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::isScript";
		return !command_script.IsEmpty() || !command_script_file.IsEmpty();
	} // private boolean isScript
}