package merkleServer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Class thar implements the merkleTree data structure
 *
 * */
public class merkleTree {

    private merkleNode root;

    /**
     * Sole constructor of this class - marked protected.
     * */
    protected merkleTree(){
        root = new merkleNode();
    }//merkleTree

    /**
     *
     * Given a node that requires validation thie methods return a List of strings that are required to validate it
     * @param node String: node that requires the validation nodes
     * @return list of strings that are required to validate the input value
     *
     * */
    List<String> getNodesForValidation(String node){

        List<String> validationNodes = new ArrayList<>();

        // Code to get the specific node needed to validate the given node

        return validationNodes;

    }//getNodesForValidation

    /**
     * Returns the root of the current merjleTree object
     * @return root of the current merjleTree object
     */
    protected merkleNode getRoot(){
        return root;
    }//getRoot


    /**
     *
     * Class that implements the merkleNode objects that compose the merkelTree
     *
     * */
    private class merkleNode{

        private String value;
        private merkleNode leftSubTree;
        private merkleNode rightSubTree;
        private merkleNode parentNode;

        /**
         * Sole constructor of this class - marked protected.
         * */
        public merkleNode(){

            value = null;
            leftSubTree = null;
            rightSubTree = null;
            parentNode = null;

        }//merkleNode constructor

        /**
         * @return leftSubtree of current node
         * */
        protected merkleNode getLeftSubTree(){
            return leftSubTree;
        }//getLeftSubTree

        /**
         * @return rightSubtree of current node
         * */
        protected merkleNode getRightSubTree(){
            return rightSubTree;
        }//getRightSubTree

        /**
         * @return parent of current node
         * */
        protected merkleNode getParent(){
            return parentNode;
        }//getRightSubTree

        /**
         * @return value stored in current node
         * */
        protected String getValue(){
            return value;
        }//getValue

    }//merkleNode

}//merkleTree
