package pcd2018.exe2;

import java.util.ArrayList;
import java.util.List;

public class DiffieHellmanThread implements Runnable{

    int start;
    int stop;
    long p;
    long g;
    long publicA;
    long publicB;

    Thread t;
    List<Integer> partialRes;

    public DiffieHellmanThread(int start, int stop, long p, long g, long publicA, long publicB){
        this.start = start;
        this.stop = stop;
        this.p = p;
        this.g = g;
        this.publicA = publicA;
        this.publicB = publicB;

        partialRes = new ArrayList<Integer>();

        t = new Thread(this);
        t.start();
    }

    @Override
    public void run(){

        System.out.println("Starting thread: " + t.getId() + "(" + start + ", " + stop +  ")");

        List<Long> aList = new ArrayList<>();
        List<Long> bList = new ArrayList<>();

        int partialResCount = 0;

        for(int i = start; i <= stop; i++){
            aList.add(DiffieHellmanUtils.modPow(publicA, i, p));
            bList.add(DiffieHellmanUtils.modPow(publicB, i, p));
        }

        for(int i = 0; i <= stop-start; i++){
            for(int j = 0; j <= stop-start; j++){
                if(aList.get(i).equals(bList.get(j))) {
                    partialRes.add(start + j);
                    partialRes.add(start + i);
                    partialResCount++;
                }
            }
        }
        System.out.println("Ending thread: " + t.getId() + " (" + start + ", " + stop +  "); Found matches: " + partialResCount);
    }

    List<Integer> getPartialRes(){
        return partialRes;
    }

}
