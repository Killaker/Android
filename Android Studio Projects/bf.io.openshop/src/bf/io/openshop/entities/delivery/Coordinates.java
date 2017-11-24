package bf.io.openshop.entities.delivery;

public class Coordinates
{
    private double lat;
    private double lon;
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final Coordinates coordinates = (Coordinates)o;
            if (Double.compare(coordinates.lat, this.lat) != 0) {
                return false;
            }
            if (Double.compare(coordinates.lon, this.lon) != 0) {
                return false;
            }
        }
        return true;
    }
    
    public double getLat() {
        return this.lat;
    }
    
    public double getLon() {
        return this.lon;
    }
    
    @Override
    public int hashCode() {
        final long doubleToLongBits = Double.doubleToLongBits(this.lat);
        final int n = (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(this.lon);
        return n * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32);
    }
    
    public void setLat(final double lat) {
        this.lat = lat;
    }
    
    public void setLon(final double lon) {
        this.lon = lon;
    }
    
    @Override
    public String toString() {
        return "Coordinates{lat=" + this.lat + ", lon=" + this.lon + '}';
    }
}
