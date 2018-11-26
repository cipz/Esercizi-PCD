package merkleServer;

import java.util.ArrayList;
import java.util.List;

public class merkleTree {

    public merkleTree(){

    }//merkleTree constructor

    List<String> getNodesForValidation(String node){

        List<String> validationNodes = new ArrayList<>();

        return validationNodes;

    }//getNodesForValidation

    private class merkleNode{

        protected merkleNode leftSubTree;
        protected merkleNode rightSubTree;

        public merkleNode(){

        }//merkleNode constructor

    }//merkleNode

}//merkleTree
