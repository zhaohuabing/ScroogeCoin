package com.zhaohuabing.blockchain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.zhaohuabing.Tools;

public class Block {
    private HashPointer pre;
    private Object data;
    private int pos; // Serial number of the block

    public Block(HashPointer pre, int pos, Object data) {
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

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringBuffer("Hash: ").append(this.hashCode()).append(" Data:").append(this.data).toString();
    }

    public String getHash() {
        try {
            return Tools.getSHA2HexValue(getContent());
        } catch (IOException e) {
            throw new RuntimeException("This shouldn't happen!", e);
        }
    }

    private byte[] getContent() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream s = new ObjectOutputStream(byteStream);
        if (pre != null) {
            s.writeObject(pre.hash);
        }
        s.writeObject(data);
        s.flush();
        return byteStream.toByteArray();
    }
}
