package bf.io.openshop.entities.delivery;

import java.util.*;
import com.google.gson.annotations.*;

public class Branch
{
    private String address;
    private Coordinates coordinates;
    private long id;
    private String name;
    private String note;
    @SerializedName("opening_hours")
    private List<OpeningHours> openingHoursList;
    private List<Transport> transports;
    
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
                final Class<? extends Branch> class1 = this.getClass();
                final Class<?> class2 = o.getClass();
                b2 = false;
                if (class1 == class2) {
                    final Branch branch = (Branch)o;
                    final long n = lcmp(this.id, branch.id);
                    b2 = false;
                    if (n == 0) {
                        if (this.name != null) {
                            final boolean equals = this.name.equals(branch.name);
                            b2 = false;
                            if (!equals) {
                                return b2;
                            }
                        }
                        else if (branch.name != null) {
                            return false;
                        }
                        if (this.address != null) {
                            final boolean equals2 = this.address.equals(branch.address);
                            b2 = false;
                            if (!equals2) {
                                return b2;
                            }
                        }
                        else if (branch.address != null) {
                            return false;
                        }
                        if (this.coordinates != null) {
                            final boolean equals3 = this.coordinates.equals(branch.coordinates);
                            b2 = false;
                            if (!equals3) {
                                return b2;
                            }
                        }
                        else if (branch.coordinates != null) {
                            return false;
                        }
                        if (this.openingHoursList != null) {
                            final boolean equals4 = this.openingHoursList.equals(branch.openingHoursList);
                            b2 = false;
                            if (!equals4) {
                                return b2;
                            }
                        }
                        else if (branch.openingHoursList != null) {
                            return false;
                        }
                        if (this.note != null) {
                            final boolean equals5 = this.note.equals(branch.note);
                            b2 = false;
                            if (!equals5) {
                                return b2;
                            }
                        }
                        else if (branch.note != null) {
                            return false;
                        }
                        if (this.transports != null) {
                            if (this.transports.equals(branch.transports)) {
                                return b;
                            }
                        }
                        else if (branch.transports == null) {
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
    
    public String getAddress() {
        return this.address;
    }
    
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNote() {
        return this.note;
    }
    
    public List<OpeningHours> getOpeningHoursList() {
        return this.openingHoursList;
    }
    
    public List<Transport> getTransports() {
        return this.transports;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (int)(this.id ^ this.id >>> 32);
        int hashCode;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        }
        else {
            hashCode = 0;
        }
        final int n2 = 31 * (n + hashCode);
        int hashCode2;
        if (this.address != null) {
            hashCode2 = this.address.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int n3 = 31 * (n2 + hashCode2);
        int hashCode3;
        if (this.coordinates != null) {
            hashCode3 = this.coordinates.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n4 = 31 * (n3 + hashCode3);
        int hashCode4;
        if (this.openingHoursList != null) {
            hashCode4 = this.openingHoursList.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int n5 = 31 * (n4 + hashCode4);
        int hashCode5;
        if (this.note != null) {
            hashCode5 = this.note.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        final int n6 = 31 * (n5 + hashCode5);
        final List<Transport> transports = this.transports;
        int hashCode6 = 0;
        if (transports != null) {
            hashCode6 = this.transports.hashCode();
        }
        return n6 + hashCode6;
    }
    
    public void setAddress(final String address) {
        this.address = address;
    }
    
    public void setCoordinates(final Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setNote(final String note) {
        this.note = note;
    }
    
    public void setOpeningHoursList(final List<OpeningHours> openingHoursList) {
        this.openingHoursList = openingHoursList;
    }
    
    public void setTransports(final List<Transport> transports) {
        this.transports = transports;
    }
    
    @Override
    public String toString() {
        return "Branch{id=" + this.id + ", name='" + this.name + '\'' + ", address='" + this.address + '\'' + ", coordinates=" + this.coordinates + ", openingHoursList=" + this.openingHoursList + ", note='" + this.note + '\'' + ", transports=" + this.transports + '}';
    }
}
