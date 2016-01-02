package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Four {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, DigestException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		line = br.readLine();
		MessageDigest md5digest = MessageDigest.getInstance("MD5");
		
		for (int i=0; ; i++) {
			String newLine = line + i;
			byte[] hash = md5(md5digest, newLine.getBytes());
			
			if (i%10000==0) {
				System.out.println(i);
			}
						
			if (good6(hash)) {
				System.out.println(i);
				break;
			}
		}		
	}
	
	public static boolean good5(byte[] hash) {
		String hashStr = DatatypeConverter.printHexBinary(hash);
		
		if (hashStr.startsWith("00000")) {
			return true;
		}	
		return false;
	}
	
	public static boolean good6(byte[] hash) {
		String hashStr = DatatypeConverter.printHexBinary(hash);
		
		if (hashStr.startsWith("000000")) {
			return true;
		}	
		return false;
	}
	
	public static byte[] md5(MessageDigest digest, byte[] bytes) throws DigestException {
		digest.reset();
		digest.update(bytes);
		return digest.digest();
	}

}
