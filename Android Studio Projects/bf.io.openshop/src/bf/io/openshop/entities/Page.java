package bf.io.openshop.entities;

public class Page
{
    private long id;
    private String text;
    private String title;
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        boolean b2;
        if (this == o) {
            b2 = b;
        }
        else {
            b2 = false;
            if (o != null) {
                final Class<? extends Page> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Page page = (Page)o;
                    final long n = lcmp(this.id, page.id);
                    b2 = false;
                    if (n == 0) {
                        if (this.title != null) {
                            final boolean equals = this.title.equals(page.title);
                            b2 = false;
                            if (!equals) {
                                return b2;
                            }
                        }
                        else if (page.title != null) {
                            return false;
                        }
                        if (this.text != null) {
                            if (this.text.equals(page.text)) {
                                return b;
                            }
                        }
                        else if (page.text == null) {
                            return b;
                        }
                        b = false;
                        return b;
                    }
                }
            }
        }
        return b2;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getText() {
        return this.text;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (int)(this.id ^ this.id >>> 32);
        int hashCode;
        if (this.title != null) {
            hashCode = this.title.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        final String text = this.text;
        int hashCode2 = 0;
        if (text != null) {
            hashCode2 = this.text.hashCode();
        }
        return n2 + hashCode2;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "Page{id=" + this.id + ", title='" + this.title + '\'' + ", text='" + this.text + '\'' + '}';
    }
}
