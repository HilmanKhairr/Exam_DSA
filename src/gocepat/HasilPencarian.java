package gocepat;

public class HasilPencarian {
    public final int driverNode;
    public final int hopDistance;
    public final int[] path;
    public final String status;

    public HasilPencarian(int driverNode, int hopDistance, int[] path, String status) {
        this.driverNode = driverNode;
        this.hopDistance = hopDistance;
        this.path = path;
        this.status = status;
    }
}
