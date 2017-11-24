package bf.io.openshop.entities;

public class Links
{
    private String first;
    private String last;
    private String next;
    private String prev;
    private String self;
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        boolean b2;
        if (this == o) {
            b2 = b;
        }
        else {
            final boolean b3 = o instanceof Links;
            b2 = false;
            if (b3) {
                final Links links = (Links)o;
                if (this.getFirst() != null) {
                    final boolean equals = this.getFirst().equals(links.getFirst());
                    b2 = false;
                    if (!equals) {
                        return b2;
                    }
                }
                else if (links.getFirst() != null) {
                    return false;
                }
                if (this.getLast() != null) {
                    final boolean equals2 = this.getLast().equals(links.getLast());
                    b2 = false;
                    if (!equals2) {
                        return b2;
                    }
                }
                else if (links.getLast() != null) {
                    return false;
                }
                if (this.getPrev() != null) {
                    final boolean equals3 = this.getPrev().equals(links.getPrev());
                    b2 = false;
                    if (!equals3) {
                        return b2;
                    }
                }
                else if (links.getPrev() != null) {
                    return false;
                }
                if (this.getNext() != null) {
                    final boolean equals4 = this.getNext().equals(links.getNext());
                    b2 = false;
                    if (!equals4) {
                        return b2;
                    }
                }
                else if (links.getNext() != null) {
                    return false;
                }
                if (this.getSelf() != null) {
                    if (this.getSelf().equals(links.getSelf())) {
                        return b;
                    }
                }
                else if (links.getSelf() == null) {
                    return b;
                }
                b = false;
                return b;
            }
        }
        return b2;
    }
    
    public String getFirst() {
        return this.first;
    }
    
    public String getLast() {
        return this.last;
    }
    
    public String getNext() {
        return this.next;
    }
    
    public String getPrev() {
        return this.prev;
    }
    
    public String getSelf() {
        return this.self;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.getFirst() != null) {
            hashCode = this.getFirst().hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = hashCode * 31;
        int hashCode2;
        if (this.getLast() != null) {
            hashCode2 = this.getLast().hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n2 = 31 * (n + hashCode2);
        int hashCode3;
        if (this.getPrev() != null) {
            hashCode3 = this.getPrev().hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n3 = 31 * (n2 + hashCode3);
        int hashCode4;
        if (this.getNext() != null) {
            hashCode4 = this.getNext().hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int n4 = 31 * (n3 + hashCode4);
        final String self = this.getSelf();
        int hashCode5 = 0;
        if (self != null) {
            hashCode5 = this.getSelf().hashCode();
        }
        return n4 + hashCode5;
    }
    
    public void setFirst(final String first) {
        this.first = first;
    }
    
    public void setLast(final String last) {
        this.last = last;
    }
    
    public void setNext(final String next) {
        this.next = next;
    }
    
    public void setPrev(final String prev) {
        this.prev = prev;
    }
    
    public void setSelf(final String self) {
        this.self = self;
    }
    
    @Override
    public String toString() {
        return "Links{first='" + this.first + '\'' + ", last='" + this.last + '\'' + ", prev='" + this.prev + '\'' + ", next='" + this.next + '\'' + ", self='" + this.self + '\'' + '}';
    }
}
