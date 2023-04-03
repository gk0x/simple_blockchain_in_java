package blockchain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    /* definicja metody getHash(), która przyjmuje obiekt typu Block jako argument i zwraca wartość hash tego bloku.
       Metoda może rzucać wyjątek NoSuchAlgorithmException, gdyż używa ona klasy MessageDigest,
       która może wymagać obsługi wyjątków.
    */
    public static String getHash(Block b) throws NoSuchAlgorithmException {
        // tworzenie nowej instancji klasy MessageDigest, używając algorytmu SHA-256.
        MessageDigest md = MessageDigest.getInstance("SHA-256");
      /* obliczenie wartości hash dla bloku b. Metoda getByteArray(b) jest wywoływana,
         aby uzyskać tablicę bajtów reprezentującą dane bloku, a następnie metoda md.digest() jest wywoływana,
         aby obliczyć wartość hash dla tych danych. Wynikowy hash jest zapisywany w postaci tablicy bajtów w
         zmiennej encodedHash.
      */
        byte[] encodedHash = md.digest(getByteArray(b));
        return hexToString(encodedHash);
    }

    /* definicję prywatnej statycznej metody pomocniczej hexToString(),
       która przyjmuje jako argument tablicę bajtów hex i
       zwraca reprezentację tego ciągu bajtów w postaci łańcucha znaków w formacie heksadecymalnym
    */

    private static String hexToString(byte[]hex) {
        BigInteger number = new BigInteger(1,hex);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length()<32){
            hexString.insert(0,"0");
        }
        return hexString.toString();
    }


    /* definicja prywatnej statycznej metody pomocniczej getByteArray(),
       która przyjmuje obiekt typu Block jako argument i zwraca tablicę bajtów reprezentującą dane bloku.
    */
    private static byte[] getByteArray(Block b) {
        String str = new String(b.data + b.previousHash + Long.toString(b.timestamp) + Integer.toString(b.nonce));
    return str.getBytes(StandardCharsets.UTF_8);
    }
}
