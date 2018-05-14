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

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author Huabing Zhao
 *
 */
public class MerkleTreeTest extends TestCase {

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testTraverse() {
        List<String> data = new ArrayList<String>();
        data.add("block 0");
        data.add("block 1");
        data.add("block 2");
        data.add("block 3");
        MerkleTree merkleTree = new MerkleTree(data);
        List<String> result = merkleTree.getLeaves();
        assertEquals(data, result);

        data = new ArrayList<String>();
        data.add("block 0");
        data.add("block 1");
        data.add("block 2");
        data.add("block 3");
        data.add("block 4");

        merkleTree = new MerkleTree(data);
        result = merkleTree.getLeaves();

        // Multiple blokc 4 are appended to the tree to make a balanced binary tree
        List<String> expectResult = new ArrayList<String>();
        expectResult.add("block 0");
        expectResult.add("block 1");
        expectResult.add("block 2");
        expectResult.add("block 3");
        expectResult.add("block 4");
        expectResult.add("block 4");
        expectResult.add("block 4");
        expectResult.add("block 4");
        assertEquals(expectResult, result);
    }

}
