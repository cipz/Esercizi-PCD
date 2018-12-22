
package test;

import main.DiffieHellman;
import org.junit.Test;
import java.util.List;

public class DiffieHellmanTest{

    @Test
    public  void testCrack(){

        final long p = 128504093;
        final long g = 10009;

        final long A = 69148740;
        final long B = 67540095;

        DiffieHellman DHObj = new DiffieHellman(p, g);
        List<Integer> crackedValues = DHObj.crack(A, B);

        crackedValues.forEach(System.out::println);

        System.out.println("OK!");

    }
}