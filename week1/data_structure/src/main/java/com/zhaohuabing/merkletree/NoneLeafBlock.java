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

import com.zhaohuabing.Tools;


/**
 * @author Huabing Zhao
 *
 */
public class NoneLeafBlock implements Block {
    private HashPointer left;

    /**
     * @return the left
     */
    public HashPointer getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public HashPointer getRight() {
        return right;
    }

    private HashPointer right;
    private String hash;
    private String data;

    public NoneLeafBlock(HashPointer left, HashPointer right) {
        super();
        this.left = left;
        this.right = right;
        this.data = new StringBuffer().append(left.hash).append(right.hash).toString();
        this.hash = Tools.getSHA2HexValue(data);
    }

    public String getHash() {
        return hash;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer bf = new StringBuffer();
        bf.append("Hash: ").append(hash).append(System.getProperty("line.separator")).append("LeftChild:").append(left)
                        .append(System.getProperty("line.separator")).append("RightChild:").append(right);
        return bf.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.zhaohuabing.merkletree.Block#getData()
     */
    public String getData() {
        return data;
    }
}
