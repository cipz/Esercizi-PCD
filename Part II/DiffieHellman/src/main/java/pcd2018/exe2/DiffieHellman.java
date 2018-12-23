package pcd2018.exe2;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe da completare per l'esercizio 2.
 */
public class DiffieHellman {

  /**
   * Limite massimo dei valori segreti da cercare
   */
  //private static final int LIMIT = 65536;
  private static final int LIMIT = 1024;

  private final long p;
  private final long g;

  public DiffieHellman(long p, long g) {
    this.p = p;
    this.g = g;
  }

  /**
   * Metodo da completare
   * 
   * @param publicA valore di A
   * @param publicB valore di B
   * @return tutte le coppie di possibili segreti a,b
   */
  public List<Integer> crack(long publicA, long publicB) {
    List<Integer> res = new ArrayList<Integer>();

    /*
    * possibili opzioni:
    *
    * - un numero di thread uguale al numero di core
    * - parallelstream
    *
    * */

    System.out.println(Runtime.getRuntime().availableProcessors());

    /*
    // Senza paralellismo
    for(int a = 0; a <= LIMIT; a++){
      for(int b = 0; b <= LIMIT; b++){

        Long calc_a = DiffieHellmanUtils.modPow(g, a, p);
        Long calc_b = DiffieHellmanUtils.modPow(g, b, p);

        if((publicA == calc_a) && (publicB == calc_b)){
          res.add(a);
          res.add(b);
        }
        System.out.println();
      }
    }
    */

    return res;
  }
}
