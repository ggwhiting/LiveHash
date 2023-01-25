import java.security.*;
import java.util.*;

public class LiveHashCoord {

	private Set<String> availableHashAlgorithms;
	private MessageDigest msgDigest;

	public LiveHashCoord() throws Exception {
		this.availableHashAlgorithms = Security.getAlgorithms("MessageDigest");
	}

	public String[] getAvailableAlgorithms(){
		Object[] objArray = this.availableHashAlgorithms.toArray();
		String[] strArray = new String[objArray.length+1];
		for(int n = 0; n < objArray.length;n++){
			strArray[n] = new String();
			strArray[n] = objArray[n].toString();
		}
		return strArray;
	}

	public void setHashAlgorithm(String algo) throws Exception {
		this.msgDigest = MessageDigest.getInstance(algo);
	}

	public String getHashFromText(String text) throws Exception {
		String hexString = "";
		this.msgDigest.update(text.getBytes());
		for(byte b : this.msgDigest.digest()){
			hexString += String.format("%02X", b);
		}
		return hexString.toLowerCase();
	}
}

