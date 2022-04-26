package TwistingCrystal;

import processing.core.PApplet;
import processing.core.PVector;

public class Crystal extends PApplet {
    
    public float x;
    public float y;
    public float z;
    public float w;
    public float h;
    public float d;
    public float baseTwistAngle;
    public float twistAngle;
    public float twistSpeed;
    public int verticesNum;
    public float arcLength;
    public float rotationAngle = 0;
    public float rotationSpeed;

    public Crystal(float x, float y, float z, float w, float h, float d,
                    float baseTwistAngle, int verticesNum, float arcLength, float rotationSpeed) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.d = d;
        this.baseTwistAngle = baseTwistAngle;
        this.twistSpeed = baseTwistAngle / 60;
        this.verticesNum = verticesNum;
        this.arcLength = arcLength;
        this.rotationSpeed = rotationSpeed;
    }

    public Crystal(float x, float y, float z, float w, float h, float d, 
                    float baseTwistAngle, int verticesNum) {
                        this(x, y, z, w, h, d, baseTwistAngle, verticesNum, 100, baseTwistAngle / 60);
    }

    public Crystal(float x, float y, float z, float w, float h, float d) {
        this(x, y, z, w, h, d, 0, 9);
    }

    public Crystal() {
        this(0, 0, 0, 0, 0, 0, 0, 9);
    }

    public PVector getVertices(int vertexIndex, int cornerNum) {

        if (vertexIndex < 0 || vertexIndex > verticesNum + 1) {
            return new PVector();
        }

        float vertexX = x + w / 2 + (w / verticesNum * vertexIndex);
        float vertexY = (y * sin(PI / verticesNum * vertexIndex)) 
                        * sin(baseTwistAngle / verticesNum * vertexIndex 
                        + HALF_PI * cornerNum - QUARTER_PI + twistAngle);
        float vertexZ = (z * sin(PI / verticesNum * vertexIndex)) 
                        * cos(baseTwistAngle / verticesNum * vertexIndex 
                        + HALF_PI * cornerNum - QUARTER_PI + twistAngle);

        PVector curvedVertex = curveCrystal(new PVector(vertexX, vertexY, vertexZ), 
                                            arcLength  / verticesNum * vertexIndex);
        PVector rotatedVertex = rotateCrystal(curvedVertex);
        
        return rotatedVertex;
    }

    public PVector curveCrystal(PVector vertex, float arc) {

        float curvedX;
        float curvedY;
        float curvedZ = vertex.z;

        if (arc == 0) {
            curvedX = 0;
            curvedY = w / arcLength + vertex.y;
        } else {
            curvedX = (vertex.x / arc + vertex.y) * sin(arc);
            curvedY = (vertex.x / arc + vertex.y) * cos(arc);
        }

        return new PVector(curvedX, curvedY, curvedZ);
    }

    public PVector rotateCrystal(PVector vertex) {
        return vertex.rotate(rotationAngle);
    }

    public void updateCrystal() {
        twistAngle += twistSpeed;
        rotationAngle += rotationSpeed;
    }
}
