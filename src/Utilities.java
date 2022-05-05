
public class Utilities {
	public static String entferneXmlElemente(String xml, String element) {
		int anf = xml.indexOf("<".concat(element).concat(">"));
		int end = xml.indexOf("</".concat(element).concat(">")) + element.length() + 3;
		if (anf >= 0) {
			return entferneXmlElemente(xml.substring(0, anf).concat(xml.substring(end)), element);
		}
		return xml;
	}

	public static String vereinfacheGetDepositActivityErgebnis(String retval) {
		retval = entferneXmlElemente(retval, "deposit_activity_id");
		retval = entferneXmlElemente(retval, "creation_date");
		retval = entferneXmlElemente(retval, "title");
		retval = entferneXmlElemente(retval, "producer_agent_id");
		retval = entferneXmlElemente(retval, "submit_date");
		retval = entferneXmlElemente(retval, "update_date");
		retval = entferneXmlElemente(retval, "producer_id");
		retval = entferneLeerzeilen(retval);
		return retval;
	}

	private static String entferneLeerzeilen(String str) {
		int letzteStelle = str.length();
		boolean leer = true;
		for (int i = str.length()-1; i>0; --i) {
			if (str.charAt(i) == '\n') {
				if (leer) {
					str = str.substring(0, i).concat(str.substring(letzteStelle));
				} else {
					leer = true;
				}
				letzteStelle = i;
			} else {
				if (str.charAt(i) != ' ') {
					leer = false;
				}
			}
		}
		return str;
	}
}
