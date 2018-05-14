/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.zhaohuabing.merkletree;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Huabing Zhao
 *
 */
public class MerkleTree {
    Block root;

    /**
     * @return the root
     */
    public Block getRoot() {
        return root;
    }

    // Literally, data can be anything, I'm using string here just for simplicity
    public MerkleTree(List<String> data) {
        List<Block> thisLevel = buildLeaves(data);

        while (thisLevel.size() > 1) {
            thisLevel = buildUpperLevel(thisLevel);
        }
        root = thisLevel.get(0);
    }

    private List<Block> buildLeaves(List<String> data) {
        data.sort(Collator.getInstance());
        fixOddNumerLeaf(data);

        List<Block> leaves = new ArrayList<Block>();
        for (int i = 0; i < data.size(); i++) {
            leaves.add(new LeafBlock(data.get(i)));
        }
        return leaves;
    }

    private List<Block> buildUpperLevel(List<Block> lowerLevel) {
        fixOddNumerBlock(lowerLevel);

        List<Block> upperLevel = new ArrayList<Block>();
        int index = 0;

        while (index < lowerLevel.size()) {
            HashPointer leftPointer = createHashPointer(lowerLevel.get(index));
            HashPointer rightPointer = createHashPointer(lowerLevel.get(++index));
            NoneLeafBlock upperBlock = new NoneLeafBlock(leftPointer, rightPointer);
            upperLevel.add(upperBlock);

            index++;
        }
        return upperLevel;
    }

    /**
     * @param block
     * @return
     */
    private HashPointer createHashPointer(Block block) {
        HashPointer pointer = new HashPointer();
        pointer.ref = block;
        pointer.hash = block.getHash();
        return pointer;
    }

    /**
     * Create a leaf the same as the last one and append it to list if the number is odd
     */
    private void fixOddNumerLeaf(List<String> leaves) {
        if (leaves.size() % 2 == 1) {
            leaves.add(leaves.get(leaves.size() - 1));
        }
    }

    /**
     * Create a block the same as the last one and append it to list if the number is odd
     */
    private void fixOddNumerBlock(List<Block> blocks) {
        if (blocks.size() % 2 == 1) {
            blocks.add(blocks.get(blocks.size() - 1));
        }
    }

    /**
     * Travers the tree from left to right and get all the nodes
     */
    public List<String> getLeaves() {
        ArrayList<String> result = new ArrayList<String>();

        if (root instanceof LeafBlock) {
            result.add(((LeafBlock) root).getData());
            return result;
        }

        traverse(result, root);
        return result;

    }

    /**
     * @param result
     */
    private void traverse(ArrayList<String> result, Block block) {
        if (block instanceof LeafBlock) {
            result.add(((LeafBlock) block).getData());
            return;
        }
        traverse(result, ((NoneLeafBlock) block).getLeft().ref);
        traverse(result, ((NoneLeafBlock) block).getRight().ref);
    }

    public static void main(String[] args) {
        List<String> data = new ArrayList<String>();
        data.add("block 0");
        data.add("block 1");
        data.add("block 2");
        data.add("block 3");
        data.add("block 4");
        MerkleTree merkleTree = new MerkleTree(data);
        System.out.println(merkleTree.getRoot());

        List<String> leaves = merkleTree.getLeaves();
        for (int i = 0; i < leaves.size(); i++) {
            System.out.println(leaves.get(i));
        }
    }
}
