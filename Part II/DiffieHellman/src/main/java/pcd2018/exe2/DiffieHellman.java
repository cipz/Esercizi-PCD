package pcd2018.exe2;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe da completare per l'esercizio 2.
 */
public class DiffieHellman {

  /**
   * Limite massimo dei valori segreti da cercare
   */
  private static final int LIMIT = 65536;

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

    System.out.println("\n\n\n");

    Date date = new Date();
    System.out.println(new Timestamp(date.getTime()));

    int nLogicCores = Runtime.getRuntime().availableProcessors();
    int partialLimit = LIMIT / (nLogicCores);

    List<DiffieHellmanThread> threads = new ArrayList<>();

    for(int i = 0; i < nLogicCores; i++)
      threads.add(new DiffieHellmanThread(partialLimit * i + 1, partialLimit * (i+1), p ,g, publicA, publicB));

    for(int i = 0; i < nLogicCores; i++) {
      try {
        threads.get(i).t.join();
        res.addAll(threads.get(i).getPartialRes());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println(new Timestamp(date.getTime()));

    return res;
  }
}
