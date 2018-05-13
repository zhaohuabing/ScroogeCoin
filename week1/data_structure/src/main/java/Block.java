import org.apache.commons.lang.builder.HashCodeBuilder;

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
	public int hashCode() {
		if (pre != null) {
			return new HashCodeBuilder(31, 15).append(data).append(pre.hash).toHashCode();
		} else {
			return data.hashCode();
		}
	}

	@Override
	public String toString() {
		return new StringBuffer("Hash: ").append(this.hashCode()).append(" Data:").append(this.data).toString();
	}

}
