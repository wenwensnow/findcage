package com.supinfo.findcage.touchlistener;


import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class Touch implements View.OnTouchListener {
    public static Matrix matrix = new Matrix();
    public static Matrix savedMatrix = new Matrix();

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    private static final float MAX_ZOOM = (float) 3;
    private static final float MIN_ZOOM = 1;
    int mode = NONE;
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    int width, height,imageNum;
    public int result=0;
    public boolean onTouch(View v, MotionEvent event) {

        final ImageView view = (ImageView) v;
        Rect bounds = view.getDrawable().getBounds();
        width = bounds.right - bounds.left;
        height = bounds.bottom - bounds.top;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                float x = event.getEventTime() - event.getDownTime();
                System.out.println(x);
                float [] values = new float[9];
                matrix.getValues(values);
                float screenX = (event.getX() - values[2])/values[0];
                float screenY = (event.getY()-values[5])/values[4];
                System.out.println(screenX +"is ..." + screenY );
                if (x<=130)
                {
                   result=clickonCgae(screenX,screenY,imageNum);
                }
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }
        limitZoom(matrix);
        limitDrag(matrix);
        view.setImageMatrix(matrix);
        return true;
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private void limitZoom(Matrix m) {

        float[] values = new float[9];
        m.getValues(values);
        float scaleX = values[Matrix.MSCALE_X];
        float scaleY = values[Matrix.MSCALE_Y];
        if (scaleX > MAX_ZOOM) {
            scaleX = MAX_ZOOM;
        } else if (scaleX < MIN_ZOOM) {
            scaleX = MIN_ZOOM;
        }

        if (scaleY > MAX_ZOOM) {
            scaleY = MAX_ZOOM;
        } else if (scaleY < MIN_ZOOM) {
            scaleY = MIN_ZOOM;
        }

        values[Matrix.MSCALE_X] = scaleX;
        values[Matrix.MSCALE_Y] = scaleY;
        m.setValues(values);
    }


    private void limitDrag(Matrix m) {

        float[] values = new float[9];
        m.getValues(values);
        float transX = values[Matrix.MTRANS_X];
        float transY = values[Matrix.MTRANS_Y];
        float scaleX = values[Matrix.MSCALE_X];
        float scaleY = values[Matrix.MSCALE_Y];
//--- limit moving to left ---
        float minX = (-width) * (scaleX - 1);
        float minY = (-height) * (scaleY - 1);
//--- limit moving to right ---
        float maxX = minX + width * (scaleX - 1);
        float maxY = minY + height * (scaleY - 1);
        if (transX > maxX) {
            transX = maxX;
        }
        if (transX < minX) {
            transX = minX;
        }
        if (transY > maxY) {
            transY = maxY;
        }
        if (transY < minY) {
            transY = minY;
        }
        values[Matrix.MTRANS_X] = transX;
        values[Matrix.MTRANS_Y] = transY;
        m.setValues(values);
    }

    public Touch(int imageNum) {
           this.imageNum = imageNum;
    }
   private int clickonCgae(float x ,float y,int imageNum) {
       if (imageNum == 1) {
           if (x <= 1010 && x >= 980) {
               System.out.println("you click on cage");
               return 1;
           }
       } else if (imageNum == 2) {
           if (x < 550 && x > 510) {
               {
                   System.out.println("you click on cage");
                   return 1;
               }
           }
       }
       return 2;
   }
}