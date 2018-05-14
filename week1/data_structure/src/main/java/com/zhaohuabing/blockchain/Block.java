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
package com.zhaohuabing.blockchain;

import com.zhaohuabing.Tools;

public class Block {
    private HashPointer pre;
    private String data;// Literally, data can be anything, I'm using string for simplicity
    private int pos; // Serial number of the block

    public Block(HashPointer pre, int pos, String data) {
        super();
        this.pre = pre;
        this.pos = pos;
        this.data = data;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public HashPointer getPre() {
        return pre;
    }

    public void setPre(HashPointer pre) {
        this.pre = pre;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringBuffer("Hash: ").append(this.hashCode()).append(" Data:").append(this.data).toString();
    }

    public String getHash() {
        String content = new StringBuffer(pre.hash).append(data).toString();

        return Tools.getSHA2HexValue(content);
    }
}
