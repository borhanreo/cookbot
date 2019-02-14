package w3engineers.com.cookerbot.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import w3engineers.com.cookerbot.R;

/**
 * Created by Borhan on 3/9/2017.
 */

public class Box extends View{
    private Paint paint = new Paint();
    private Context context;
    public Box(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    protected void onDraw(Canvas canvas) { // Override the onDraw() Method
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);

        //center
        int x0 = canvas.getWidth()/2;
        int x14 = canvas.getWidth()/4;
        int x12_100 = (canvas.getWidth()/2)-100;
        int x34_200 = ((canvas.getWidth()/4)*3)-200;
        int x34_100 = ((canvas.getWidth()/4)*3)-100;

        int y0 = canvas.getHeight()/2;
        int y34 = (canvas.getHeight()/4)*3;
        int y4 = (canvas.getHeight()/4);
        int y34_20 = (canvas.getHeight()/4)*3;
        int dx = canvas.getHeight()/3;
        int dy = canvas.getHeight()/3;
        //draw guide box
        //canvas.drawRect(x0-dx, y0-dy, x0+dx, y0+dy, paint);
        Bitmap chickenOrPotatoOrOtherIcon = BitmapFactory.decodeResource(getResources(), R.drawable.beef1);
        canvas.drawBitmap(chickenOrPotatoOrOtherIcon,x0,y34,paint);

        Bitmap onionIcon = BitmapFactory.decodeResource(getResources(), R.drawable.onion1);
        canvas.drawBitmap(onionIcon,x34_100,y0,paint);

        Bitmap chiliIcon = BitmapFactory.decodeResource(getResources(), R.drawable.chili1);
        canvas.drawBitmap(chiliIcon,x34_200,y4,paint);

        Bitmap saltIcon = BitmapFactory.decodeResource(getResources(), R.drawable.salt1);
        canvas.drawBitmap(saltIcon,x12_100,y4,paint);

        Bitmap spiceIcon = BitmapFactory.decodeResource(getResources(), R.drawable.spice1);
        canvas.drawBitmap(spiceIcon,x14,y0,paint);
    }
    private Integer[] mThumbIds = { R.drawable.beef};
    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }
}
