package android.support.v7.widget;

static class UpdateOp
{
    static final int ADD = 1;
    static final int MOVE = 8;
    static final int POOL_SIZE = 30;
    static final int REMOVE = 2;
    static final int UPDATE = 4;
    int cmd;
    int itemCount;
    Object payload;
    int positionStart;
    
    UpdateOp(final int cmd, final int positionStart, final int itemCount, final Object payload) {
        this.cmd = cmd;
        this.positionStart = positionStart;
        this.itemCount = itemCount;
        this.payload = payload;
    }
    
    String cmdToString() {
        switch (this.cmd) {
            default: {
                return "??";
            }
            case 1: {
                return "add";
            }
            case 2: {
                return "rm";
            }
            case 4: {
                return "up";
            }
            case 8: {
                return "mv";
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final UpdateOp updateOp = (UpdateOp)o;
            if (this.cmd != updateOp.cmd) {
                return false;
            }
            if (this.cmd != 8 || Math.abs(this.itemCount - this.positionStart) != 1 || this.itemCount != updateOp.positionStart || this.positionStart != updateOp.itemCount) {
                if (this.itemCount != updateOp.itemCount) {
                    return false;
                }
                if (this.positionStart != updateOp.positionStart) {
                    return false;
                }
                if (this.payload != null) {
                    if (!this.payload.equals(updateOp.payload)) {
                        return false;
                    }
                }
                else if (updateOp.payload != null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * this.cmd + this.positionStart) + this.itemCount;
    }
    
    @Override
    public String toString() {
        return Integer.toHexString(System.identityHashCode(this)) + "[" + this.cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + ",p:" + this.payload + "]";
    }
}
