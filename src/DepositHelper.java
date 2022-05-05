import java.net.URL;
import javax.xml.namespace.QName;

import com.exlibris.dps.DepositWebServices_Service;
import com.exlibris.dps.SipStatusInfo;
import com.exlibris.dps.SipWebServices_Service;
import com.exlibris.dps.sdk.pds.HeaderHandlerResolver;

public class DepositHelper {
	static final String fs = System.getProperty("file.separator");

	private static String defaultRosettaURL(String rosettaInstance) throws Exception {
		if (rosettaInstance.equals("dev")) {
			return "https://rosetta.develop.lza.tib.eu";
		} else if (rosettaInstance.equals("test")) {
			return "https://rosetta.test.lza.tib.eu";
		} else if (rosettaInstance.equals("prod")) {
			return "https://rosetta.lza.tib.eu";
		} else {
			System.err.println("invalider Wert für rosettaInstance '" + rosettaInstance + "'.");
			throw new Exception();
		}
	}

	private static String defaultInstitution(String rosettaInstance) throws Exception {
		if (rosettaInstance.equals("dev")) {
			return "ZBM";
		} else if (rosettaInstance.equals("test")) {
			return "ZBMED";
		} else if (rosettaInstance.equals("prod")) {
			return "ZBMED";
		} else {
			System.err.println("invalider Wert für rosettaInstance '" + rosettaInstance + "'.");
			throw new Exception();
		}
	}

	private static String defaultMaterialflowId(String rosettaInstance) throws Exception {
		if (rosettaInstance.equals("dev")) {
			return "76661659";
		} else if (rosettaInstance.equals("test")) {
			return "247510347";
		} else if (rosettaInstance.equals("prod")) {
			return "1024144919";
		} else {
			System.err.println("invalider Wert für rosettaInstance '" + rosettaInstance + "'.");
			throw new Exception();
		}
	}

	private static String defaultUserName(String rosettaInstance) throws Exception {
		if (rosettaInstance.equals("dev")) {
			return "SubApp ZB MED";
		} else if (rosettaInstance.equals("test")) {
			return "SubApp ZB MED";
		} else if (rosettaInstance.equals("prod")) {
			return "SubApp ZB MED";
		} else {
			System.err.println("invalider Wert für rosettaInstance '" + rosettaInstance + "'.");
			throw new Exception();
		}
	}

	private static String defaultProducerId(String rosettaInstance) throws Exception {
		if (rosettaInstance.equals("dev")) {
			return "2049290";
		} else if (rosettaInstance.equals("test")) {
			return "95291948";
		} else if (rosettaInstance.equals("prod")) {
			return "141880548";
		} else {
			System.err.println("invalider Wert für rosettaInstance '" + rosettaInstance + "'.");
			throw new Exception();
		}
	}

	private static String defaultPassword(String rosettaInstance) throws Exception {
		if (rosettaInstance.equals("dev")) {
			String propertyDateiPfad = System.getProperty("user.home").concat(fs).concat("Rosetta_Properties.txt");
			PropertiesManager prop = new PropertiesManager(propertyDateiPfad);
			return prop.readStringFromProperty("SubApp_Passwort");
		} else if (rosettaInstance.equals("test")) {
			String propertyDateiPfad = System.getProperty("user.home").concat(fs).concat("Rosetta_Properties.txt");
			PropertiesManager prop = new PropertiesManager(propertyDateiPfad);
			return prop.readStringFromProperty("SubApp_Passwort");
		} else if (rosettaInstance.equals("prod")) {
			String propertyDateiPfad = System.getProperty("user.home").concat(fs).concat("Rosetta_Properties.txt");
			PropertiesManager prop = new PropertiesManager(propertyDateiPfad);
			return prop.readStringFromProperty("SubApp_Passwort");
		} else {
			System.err.println("invalider Wert für rosettaInstance '" + rosettaInstance + "'.");
			throw new Exception();
		}
	}

	private static String defaultDEPOSIT_WSDL_URL(String rosettaURL) {
		return rosettaURL.concat("/dpsws/deposit/DepositWebServices?wsdl");
	}

	private static String defaultSIP_STATUS_WSDL_URL(String rosettaURL) {
		return rosettaURL.concat("/dpsws/repository/SipWebServices?wsdl");
	}

	/*
	 * stößt deposit an
	 * versucht es so lange, bis kein Fehler
	 */
	public static RosettaDepositResult deposit(String sipId, String rosettaInstance, String depositSetId)
			throws Exception {
		final String rosettaURL = defaultRosettaURL(rosettaInstance);
		final String institution = defaultInstitution(rosettaInstance);
		final String materialflowId = defaultMaterialflowId(rosettaInstance);
		final String userName = defaultUserName(rosettaInstance);
		final String producerId = defaultProducerId(rosettaInstance);
		final String password = defaultPassword(rosettaInstance);
		final String DEPOSIT_WSDL_URL = defaultDEPOSIT_WSDL_URL(rosettaURL);

		DepositWebServices_Service depWS = new DepositWebServices_Service(new URL(DEPOSIT_WSDL_URL),
				new QName("http://dps.exlibris.com/", "DepositWebServices"));
		depWS.setHandlerResolver(new HeaderHandlerResolver(userName, password, institution));

		Boolean repeat = true;
		String retval = null;
		while (repeat) {
			try {
				retval = depWS.getDepositWebServicesPort().submitDepositActivity(null, materialflowId, sipId + "/",
						producerId, depositSetId);
				repeat = false;
			} catch (Exception e) {
				System.err.println("Submit Deposit fehlgeschlagen");
				Thread.sleep(1000);
			}
		}
		if (retval == null) {
			System.err.println("Submit Deposit ergab Nullantwort");
			throw new Exception();
		}
		RosettaDepositResult depositResult = new RosettaDepositResult(retval);
		return depositResult;
	}

	/*
	 * Parameters:
	 * materialflowId
	 * submitDateFrom
	 * submitDateTo
	 * rosettaInstance
	 * depositActivityStatus - All/In process/Rejected/Draft/Approved/Declined
	 * producerId (null für Default)
	 * producerAgentId (null für Default)
	 * startRecord
	 * endRecord
	 * Returns:
	 * an XML with the reply to the request, which includes the list of requested deposit activities according...
	 */
	public static String getDepositActivityBySubmitDateByMaterialflow(String materialflowId, String submitDateFrom,
			String submitDateTo, String rosettaInstance, String depositActivityStatus, String producerId,
			String producerAgentId, String startRecord, String endRecord) throws Exception {
		if (materialflowId == null)
			materialflowId = defaultMaterialflowId(rosettaInstance);
		final String rosettaURL = defaultRosettaURL(rosettaInstance);
		final String institution = defaultInstitution(rosettaInstance);
		final String userName = defaultUserName(rosettaInstance);
		if (producerId == null)
			producerId = defaultProducerId(rosettaInstance);
		final String password = defaultPassword(rosettaInstance);
		final String DEPOSIT_WSDL_URL = defaultDEPOSIT_WSDL_URL(rosettaURL);

		DepositWebServices_Service depWS = new DepositWebServices_Service(new URL(DEPOSIT_WSDL_URL),
				new QName("http://dps.exlibris.com/", "DepositWebServices"));
		depWS.setHandlerResolver(new HeaderHandlerResolver(userName, password, institution));

		Boolean repeat = true;
		String retval = null;
		while (repeat) {
			try {
				retval = depWS.getDepositWebServicesPort().getDepositActivityBySubmitDateByMaterialFlow(null,
						depositActivityStatus, materialflowId, producerId, producerAgentId, submitDateFrom, submitDateTo,
						startRecord, endRecord);
				repeat = false;
			} catch (Exception e) {
				System.err.println("Submit Deposit fehlgeschlagen");
				Thread.sleep(1000);
			}
		}
		if (retval == null) {
			System.err.println("Submit Deposit ergab Nullantwort");
			throw new Exception();
		}
		return retval;
	}

	/*
	 * gibt Status einer Sip aus
	 * versucht es so lange, bis kein Fehler
	 * terminiert eventuell nie
	 */
	public static RosettaSipStatusInfo getStatus(String sipId, String rosettaInstance) throws Exception {
		final String rosettaURL = defaultRosettaURL(rosettaInstance);
		final String SIP_STATUS_WSDL_URL = defaultSIP_STATUS_WSDL_URL(rosettaURL);
		SipStatusInfo status = null;
		while (true) {
			try {
				//TODO: es muss noch dafür gesorgt werden, dass es abbricht, wenn es nicht returned
				status = new SipWebServices_Service(new URL(SIP_STATUS_WSDL_URL),
						new QName("http://dps.exlibris.com/", "SipWebServices")).getSipWebServicesPort()
								.getSIPStatusInfo(sipId, false);
				break;
			} catch (Exception e) {
				System.err.println("Statusabfrage fehlgeschlagen");
				Thread.sleep(1000);
			}
		}
		RosettaSipStatusInfo rosettaStatus = new RosettaSipStatusInfo(status);
		return rosettaStatus;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("starte Tests...");
		String rosettaInstance = "prod";
		//		String sipId = "dgh2021_21dgh03";
		//		System.out.println("teste deposit mit sipId = '" + sipId + "' und rosettaInstance = '" + rosettaInstance + "'.");
		//		String depositSetId = "";//TODO welchen Set wollen wir? 54429706?
		//		RosettaDepositResult depositResult = deposit(sipId, rosettaInstance, depositSetId);
		//		System.out.println("depositResult: " + depositResult);
		String rosettaSipId = "1853188"; //String.valueOf(depositResult.getSipId()); //"1761620";
		RosettaSipStatusInfo status = getStatus(rosettaSipId, rosettaInstance);
		System.out.println("Status: " + status);
		//		String retval = getDepositActivityBySubmitDateByMaterialflow("148297166", "10/01/2017", "13/01/2017", "prod",
		//				"All", null, null, null, null);
		String retval = getDepositActivityBySubmitDateByMaterialflow("1024144919", "10/01/2017",
				"13/05/2022", rosettaInstance, "All", null, null, null, "10000000");
		if (retval.length()>800) retval = retval.substring(0, 800);
		retval = Utilities.vereinfacheGetDepositActivityErgebnis(retval);
		System.out.println("getDepositActivity: " + retval);
	}

}
