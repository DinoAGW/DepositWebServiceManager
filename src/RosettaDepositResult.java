import org.apache.xmlbeans.XmlException;

import com.exlibris.digitool.deposit.service.xmlbeans.DepositResultDocument;
import com.exlibris.digitool.deposit.service.xmlbeans.DepositResultDocument.DepositResult;

public class RosettaDepositResult {
	DepositResult depositResult;

	RosettaDepositResult(String retval) throws XmlException {
		DepositResultDocument depositResultDocument = DepositResultDocument.Factory.parse(retval);
		depositResult = depositResultDocument.getDepositResult();
	}

	boolean getIsError() {
		return depositResult.getIsError();
	}

	public String toString() {
		return depositResult.toString();
	}

	public String getUserParams() {
		return depositResult.getUserParams();
	}

	public long getDepositActivityId() {
		return depositResult.getDepositActivityId();
	}

	public long getSipId() {
		return depositResult.getSipId();
	}
}
