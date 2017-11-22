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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Teco
 */
public class Servidor {
    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String msgdecifrada = null;
        String msgcifrada = null;
        BigInteger n, d, e;
        int bitlen = 2048;
        
        BigInteger p = new BigInteger("104729");//104729;
        BigInteger q = new BigInteger("104723");//104723;
        n = p.multiply(q);
        ServerSocket welcomeSocket = new ServerSocket(6780);
        
        //Compute a função totiente phi(n) = (p -1) (q -1)
        BigInteger m = (p.subtract(BigInteger.ONE))
                       .multiply(q.subtract(BigInteger.ONE));
        
        //Escolha um inteiro  "e"  , 1 < "e" < phi(n) ,  "e" e phi(n) sejam primos entre si.
        e = new BigInteger("3");
        e = e.add(new BigInteger("2"));//while(m.gcd(e).intValue() > 1) e = e.add(new BigInteger("2"));

        // d seja inverso multiplicativo de "e"
        d = e.modInverse(m);

        while (true) {

            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();
            System.out.println(clientSentence);
            
            String texto = new BigInteger(clientSentence.getBytes()).modPow(e, n).toString();
            //mensagem decifrada - RSA_decrypt()
            msgdecifrada = new String(new BigInteger(texto).modPow(d, n).toByteArray());
            System.out.println("msg decifrada: " +msgdecifrada);
            
            //mensagem cifrada - RSA_encrypt()
            msgcifrada = new BigInteger(msgdecifrada.getBytes()).modPow(e, n).toString();
            System.out.println("msg cifrada: "+ msgcifrada);

            outToClient.writeBytes(msgcifrada);
            
            connectionSocket.close();

        }
    }
}
