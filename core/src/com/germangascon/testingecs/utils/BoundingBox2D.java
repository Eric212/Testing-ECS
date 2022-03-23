package com.germangascon.testingecs.utils;

public class BoundingBox2D {
    public float xLeft;
    public float xRight;
    public float yDown;
    public float yUp;

    /*
        xLeft           xRight
    ------|--------------|------- yUp
          |              |
          |              |
    ------|--------------|------- yDown
     */

    public BoundingBox2D(float xLeft, float xRight, float yDown, float yUp) {
        this.xLeft = xLeft;
        this.xRight = xRight;
        this.yDown = yDown;
        this.yUp = yUp;
    }

    public BoundingBox2D translateWorldCoord(float worldX,float worldY){
        return new BoundingBox2D(worldX+xLeft,worldX+xRight, worldY+yDown,worldY+yUp);
    }

    public boolean overlaps(BoundingBox2D box){
        return checkIntervals(xLeft,xRight, box.xLeft,box.xRight) && checkIntervals(yDown,yUp, box.yDown, box.yUp);
    }

    private boolean checkIntervals(float l1,float r1,float l2,float r2){
        if(l2>r1){
            return false;
        }
        if(l1>r2){
            return false;
        }
        return true;
    }
}
