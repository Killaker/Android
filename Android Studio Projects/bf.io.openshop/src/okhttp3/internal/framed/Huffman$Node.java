package okhttp3.internal.framed;

private static final class Node
{
    private final Node[] children;
    private final int symbol;
    private final int terminalBits;
    
    Node() {
        this.children = new Node[256];
        this.symbol = 0;
        this.terminalBits = 0;
    }
    
    Node(final int symbol, final int n) {
        this.children = null;
        this.symbol = symbol;
        int terminalBits = n & 0x7;
        if (terminalBits == 0) {
            terminalBits = 8;
        }
        this.terminalBits = terminalBits;
    }
}
