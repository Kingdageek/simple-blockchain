import component.Block;
import component.ProofOfWork;
import ds.List;
import ds.SingleLinkedList;

public class BlockChain {

    static int REQUIRED_LENGTH = 10;
    List<Block> blockchain;

    final static int START_BLOCK_HASH = (new Block("", 0, 0)).hashCode();

    public BlockChain() {
        blockchain = new SingleLinkedList<>();
        Block first = new Block("", 0, 0);
        blockchain.addItem(first);
    }

    public BlockChain(int lengthOfProof) {
        this();
        REQUIRED_LENGTH = lengthOfProof;
    }

    public void addBlock(String message) {
        Block previousBlock = blockchain.getItem(blockchain.length() - 1);
        long proof = ProofOfWork.proofOfWork(REQUIRED_LENGTH);
        Block newBlock = new Block(message, proof, previousBlock.hashCode());
        this.blockchain.addItem(newBlock);
    }

    public boolean verify() {
        int previousHash = START_BLOCK_HASH;
        for (int i = 1; i < blockchain.length(); i++) {
            Block curBlock = blockchain.getItem(i);
            int currentHash = curBlock.hashCode(); // or: int currentHash = ("['"+curBlock.message+"' |
                                                   // "+curBlock.proofOfWork+" | "+previousHash+"]").hashCode();
            if (!ProofOfWork.verify(curBlock.proofOfWork, REQUIRED_LENGTH)) {
                return false;
            }
            if (previousHash != curBlock.previousHashCode) {
                return false;
            }
            previousHash = currentHash;
            // if you encounter any inconsistency, you may immediately call 'return false;'
            // to exit early
        }
        return true;
    }

    public static void main(String[] args) {
        BlockChain bc = new BlockChain(REQUIRED_LENGTH);
        System.out.println(bc.blockchain);
        System.out.println("bc.blockchain.length() = " + bc.blockchain.length()); // should be 1, because of the start
                                                                                  // block

        bc.addBlock("Message 1!");
        bc.addBlock("Message 2!!");
        bc.addBlock("Message 3!!!");
        System.out.println(bc.blockchain); // should print 4 blocks (empty start block + the three above)
        System.out.println(bc.verify()); // should print "true"
    }
}