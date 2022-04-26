package TwistingCrystal;

public class Axes {
    
    public float x;
    public float y;
    public float z;
    public float rx;
    public float ry;
    public float rz;

    public Axes(float x, float y, float z, float rx, float ry, float rz) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
    }

    public Axes() {
        this(0, 0, 0, 0, 0, 0);
    }

    public void initRotate() {
        this.rx = 0;
        this.ry = 0;
        this.rz = 0;
    }

    public void initShift() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public void rotateAxes(float drx, float dry, float drz) {
        this.rx += drx;
        this.ry += dry;
        this.rz += drz;
    }

    public void shiftAxes(float dx, float dy, float dz) {
        this.x += dx;
        this.y += dy;
        this.z += dz;
    }
}
