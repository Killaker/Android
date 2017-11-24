package bf.io.openshop.entities.delivery;

public class OpeningHours
{
    private String day;
    private String opening;
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final OpeningHours openingHours = (OpeningHours)o;
            Label_0059: {
                if (this.day != null) {
                    if (this.day.equals(openingHours.day)) {
                        break Label_0059;
                    }
                }
                else if (openingHours.day == null) {
                    break Label_0059;
                }
                return false;
            }
            if (this.opening != null) {
                if (this.opening.equals(openingHours.opening)) {
                    return true;
                }
            }
            else if (openingHours.opening == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public String getDay() {
        return this.day;
    }
    
    public String getOpening() {
        return this.opening;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.day != null) {
            hashCode = this.day.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n = hashCode * 31;
        final String opening = this.opening;
        int hashCode2 = 0;
        if (opening != null) {
            hashCode2 = this.opening.hashCode();
        }
        return n + hashCode2;
    }
    
    public void setDay(final String day) {
        this.day = day;
    }
    
    public void setOpening(final String opening) {
        this.opening = opening;
    }
    
    @Override
    public String toString() {
        return "OpeningHours{day='" + this.day + '\'' + ", opening='" + this.opening + '\'' + '}';
    }
}
