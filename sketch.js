

let res = 20;

let crystal;
let path;

function setup() {
    createCanvas(500, 400, WEBGL);
    crystal = new Crystal(0, 25, 25, 500, 25, 25, res);
    path = new Path(createVector(0, 300, 0));
    path.addCoord(createVector(500, 0, 0));
}

function draw() {
    frameRate(1);
    background(0);

    // lights();
    // directionalLight(192, 192, 255, 0, 0, -1);

    translate(-width / 2, 0, 0);
    fill(255);
    // noStroke();
    for (let x = 0; x < crystal.res; x++) {
        for (let c = 0; c < 4; c++) {
            beginShape();
            let v0 = crystal.getVertex(x, c);
            let v1 = crystal.getVertex(x + 1, c);
            let v2 = crystal.getVertex(x + 1, c + 1);
            let v3 = crystal.getVertex(x, c + 1);
            vertex(v0.x, v0.y, v0.z);
            vertex(v1.x, v1.y, v1.z);
            vertex(v2.x, v2.y, v2.z);
            vertex(v3.x, v3.y, v3.z);
            vertex(v0.x, v0.y, v0.z);
            vertex(v3.x, v3.y, v3.z);
            endShape();
        }
    }
}

class Crystal {
    constructor(x, y, z, w, h, d, res) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.d = d;
        this.res = res
    }

    getVertex(xIndex, cornerIndex) {
        let rad = QUARTER_PI + HALF_PI * cornerIndex 
            + xIndex * HALF_PI / res + 0.05 * frameCount;
        return createVector(
            this.x + this.w * xIndex / res, 
            this.y * sin(rad) * sin(PI * xIndex / res), 
            this.z * cos(rad) * sin(PI * xIndex / res), 
        );
    }
}

class Path {
    constructor(coord) {
        this.coords = [coord];
    }

    addCoord(coord) {
        this.coords.push(coord);
    }
}