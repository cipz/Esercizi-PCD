package merkleServer;

import java.util.ArrayList;
import java.util.List;

public class merkleTree {

    private merkleNode root;

    public merkleTree(){

        root = new merkleNode();

    }//merkleTree constructor

    List<String> getNodesForValidation(String node){

        List<String> validationNodes = new ArrayList<>();

        // Code to get the specific node needed to validate the given node

        return validationNodes;

    }//getNodesForValidation

    private class merkleNode{

        private merkleNode leftSubTree;
        private merkleNode rightSubTree;

        public merkleNode(){

            leftSubTree = new merkleNode();
            rightSubTree = new merkleNode();

        }//merkleNode constructor

        protected merkleNode getLeftSubTree(){
            return leftSubTree;
        }//getLeftSubTree

        protected merkleNode getRightSubTree(){
            return rightSubTree;
        }//getRightSubTree

    }//merkleNode

}//merkleTree
