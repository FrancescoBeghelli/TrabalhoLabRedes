/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DCC159.ufjf;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.Socket;

/**
 *
 * @author Teco
 */
public class Cliente {
    public static void main(String argv[]) throws Exception {
        String msgcifrada = null;
        BigInteger n, e;
        int bitlen = 2048;

        String sentence;
        String modifiedSentence;
        
        BigInteger p = new BigInteger("104729");//104729;
        BigInteger q = new BigInteger("104723");//104723;
        n = p.multiply(q);

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("169.254.72.199", 6780);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence = inFromUser.readLine();
        //Compute a função totiente phi(n) = (p -1) (q -1)
        BigInteger m = (p.subtract(BigInteger.ONE))
                       .multiply(q.subtract(BigInteger.ONE));

        //Escolha um inteiro  "e"  , 1 < "e" < phi(n) ,  "e" e phi(n) sejam primos entre si.
        e = new BigInteger("3");
        e = e.add(new BigInteger("2"));//while(m.gcd(e).intValue() > 1) e = e.add(new BigInteger("2"));

        //mensagem cifrada - RSA_encrypt()
        msgcifrada = new BigInteger(sentence.getBytes()).modPow(e, n).toString();
        System.out.println("msg cifrada: "+ msgcifrada);
        BigInteger d = e.modInverse(m);
        //mensagem decifrada - RSA_decrypt()
        String msgdecifrada = new String(new BigInteger(msgcifrada).modPow(d, n).toByteArray());
        System.out.println("msg decifrada: " +msgdecifrada);
 
        outToServer.writeBytes(msgcifrada + '\n');

        modifiedSentence = inFromServer.readLine();

        System.out.println("FROM SERVER: " + modifiedSentence);

        clientSocket.close();

    }
}
