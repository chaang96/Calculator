import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Encryption {

    public static String createEncryptionPass(String password){
        String hash = byteArrayToHexString(computeHash(password));
        return hash;
    }

    private static byte[] computeHash(String text) {
        MessageDigest mesDigest = null;
        try {
            mesDigest = MessageDigest.getInstance("SHA-1");
            mesDigest.update(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (mesDigest != null)
            return mesDigest.digest();
        else
            return null;
    }

    private static String byteArrayToHexString(byte[] mesDig){
        StringBuffer sb = new StringBuffer(mesDig.length);
        for (int i = 0; i < mesDig.length; i++){
            int val = mesDig[i] & 0xff;
            if (val < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(val));
        }
        String result = sb.toString().toUpperCase();
        return result;
    }
}
