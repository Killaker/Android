package bf.io.openshop.entities.delivery;

public class Transport
{
    private String icon;
    private String text;
    
    public Transport() {
    }
    
    public Transport(final String icon, final String text) {
        this.icon = icon;
        this.text = text;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Transport)) {
                return false;
            }
            final Transport transport = (Transport)o;
            Label_0051: {
                if (this.getIcon() != null) {
                    if (this.getIcon().equals(transport.getIcon())) {
                        break Label_0051;
                    }
                }
                else if (transport.getIcon() == null) {
                    break Label_0051;
                }
                return false;
            }
            if (this.getText() != null) {
                if (this.getText().equals(transport.getText())) {
                    return true;
                }
            }
            else if (transport.getText() == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public String getIcon() {
        return this.icon;
    }
    
    public String getText() {
        return this.text;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.getIcon() != null) {
            hashCode = this.getIcon().hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = hashCode * 31;
        final String text = this.getText();
        int hashCode2 = 0;
        if (text != null) {
            hashCode2 = this.getText().hashCode();
        }
        return n + hashCode2;
    }
    
    public void setIcon(final String icon) {
        this.icon = icon;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return "Transport{icon='" + this.icon + '\'' + ", text='" + this.text + '\'' + '}';
    }
}
