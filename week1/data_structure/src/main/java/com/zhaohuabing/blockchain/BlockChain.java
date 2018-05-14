package com.zhaohuabing.blockchain;

/**
 * @author Huabing Zhao
 *
 */
public class BlockChain {
    private HashPointer head; // head points to the last block in the chain

    public HashPointer getHead() {
        return head;
    }

    public BlockChain() {
        Block genesisBlock = new Block(null, 0, new String("The very first block!"));
        head = new HashPointer();
        head.block = genesisBlock;
        head.hash = genesisBlock.getHash();
    }

    public void addBlock(Object data) {
        HashPointer pointer = new HashPointer();
        pointer.block = head.block;
        pointer.hash = head.hash;

        Block block = new Block(pointer, head.block.getPos() + 1, data);
        head.block = block;
        head.hash = block.getHash();
    }

    public Block getBlockAt(int pos) {
        HashPointer pointer = head;
        while (pointer != null) {
            int currentPos = pointer.block.getPos();
            if (currentPos == pos) {
                return pointer.block;
            }
            if (currentPos < pos) {
                return null;
            }
            pointer = pointer.block.getPre();
        }
        return null;
    }

    public void traverse() {
        Block current = head.block;
        System.out.print("Head:");
        System.out.print(" Hash:" + current.getHash());
        System.out.println(" Data:" + current.getData());

        HashPointer pointer = current.getPre();
        while (pointer != null) {
            System.out.print("Hash:" + pointer.block.getHash());
            System.out.println(" Data:" + pointer.block.getData());
            pointer = pointer.block.getPre();
        }
    }

    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain();
        blockChain.addBlock("block1");
        blockChain.addBlock("block2");
        blockChain.addBlock("block3");
        blockChain.addBlock("block4");
        blockChain.addBlock("block5");
        blockChain.traverse();
    }
}
