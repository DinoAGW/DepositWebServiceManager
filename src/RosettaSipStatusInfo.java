import com.exlibris.dps.SipStatusInfo;

public class RosettaSipStatusInfo {
	SipStatusInfo status;

	RosettaSipStatusInfo(SipStatusInfo status) {
		this.status = status;
	}

	public String getStatus() {
		return status.getStatus();
	}

	public String getStage() {
		return status.getStage();
	}

	public String getModule() {
		return status.getModule();
	}

	public String getError() {
		return status.getError();
	}

	public String getExternalId() {
		return status.getExternalId();
	}

	public String getExternalSystem() {
		return status.getExternalSystem();
	}

	public String getIePids() {
		return status.getIePids();
	}

	public String getSipId() {
		return status.getSipId();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SipId=");
		sb.append(status.getSipId());
		sb.append("\nStatus=");
		sb.append(status.getStatus());
		sb.append("\nModule=");
		sb.append(status.getModule());
		sb.append("\nError=");
		sb.append(status.getError());		
		sb.append("\nStage=");
		sb.append(status.getStage());
		sb.append("\nExternalId=");
		sb.append(status.getExternalId());
		sb.append("\nExternalSystem=");
		sb.append(status.getExternalSystem());
		sb.append("\nIePids=");
		sb.append(status.getIePids());
		return sb.toString();
	}

	public boolean isFinal() {
		String statusString = status.getStatus();
		return statusString.equals("IN_TA") || statusString.equals("IN_HUMAN_STAGE") || statusString.equals("FINISHED");
	}
}
