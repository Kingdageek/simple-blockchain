package component;

public class Block {
    String message;
    public long proofOfWork;
    public int previousHashCode;
    int hashCode;

    public Block(String msg, long proof, int previousHash) {
        this.message = msg;
        this.proofOfWork = proof;
        this.previousHashCode = previousHash;
        this.hashCode = hashCode();
    }

    @Override
    public boolean equals(Object o) {
        // compares memory addresses
        if (this == o)
            return true;
        // if memory addresses don't match, check internal attributes
        if (o == null || getClass() != o.getClass())
            return false;
        Block block = (Block) o;
        return message.equals(block.message) &&
                proofOfWork == block.proofOfWork && previousHashCode == block.previousHashCode;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    public int getStoredHashCode() {
        return hashCode;
    }

    public String toString() {
        return "['" + message + "' | " + proofOfWork + " | " + previousHashCode + "]";
    }
}
