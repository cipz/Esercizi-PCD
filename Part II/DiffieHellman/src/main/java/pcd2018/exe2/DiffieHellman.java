package pcd2018.exe2;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe da completare per l'esercizio 2.
 */
public class DiffieHellman {

  public class DiffieHellmanThread implements Runnable{

    int start;
    int stop;
    long publicA;
    long publicB;

    Thread t;
    List<Integer> partialRes;

    public DiffieHellmanThread(int start, int stop, long publicA, long publicB){
      this.start = start;
      this.stop = stop;
      this.publicA = publicA;
      this.publicB = publicB;

      partialRes = new ArrayList<>();

      t = new Thread(this);
      t.start();
    }

    @Override
    public void run(){
      List<Long> aList = new ArrayList<>();
      List<Long> bList = new ArrayList<>();

      for(int i = start; i <= stop; i++){
        aList.add(DiffieHellmanUtils.modPow(publicA, i, p));
        bList.add(DiffieHellmanUtils.modPow(publicB, i, p));
      }

      for(int i = 0; i <= stop-start; i++){
        for(int j = 0; j <= stop-start; j++){
          if(aList.get(i).equals(bList.get(j))) {
            partialRes.add(start + j);
            partialRes.add(start + i);
          }
        }
      }
    }

    List<Integer> getPartialRes(){
      return partialRes;
    }

  }

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

    int nLogicCores = Runtime.getRuntime().availableProcessors();
    int partialLimit = LIMIT / (nLogicCores);

    List<DiffieHellmanThread> threads = new ArrayList<>();

    for(int i = 0; i < nLogicCores; i++)
      threads.add(new DiffieHellmanThread(partialLimit * i + 1, partialLimit * (i+1), publicA, publicB));

    for(int i = 0; i < nLogicCores; i++) {
      try {
        threads.get(i).t.join();
        res.addAll(threads.get(i).getPartialRes());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return res;
  }
}
