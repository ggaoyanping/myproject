package com.example.laladui.youximenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.laladui.R;
import com.example.laladui.SocketTool;

import static com.example.laladui.SocketTool.CREDIT;
import static com.example.laladui.youximenu.MenuView.Block.BOTTOM;
import static com.example.laladui.youximenu.MenuView.Block.NULL;
import static com.example.laladui.youximenu.MenuView.Block.LEFT;


public class MenuView extends View {
    public static   SocketTool socketTool=new SocketTool("121.196.104.55",7839);
    private Paint mPaint;
    private Block mChoseBlock = NULL;
    private float mWidth;
    private float mHeight;
    private int[] mLocation;
    private float mTriHeight;
    private float disForCenter;
    private int mLineColor;
    private float mTextSize;
    private int mArrowColor;
    private int mChoseColor;
    private int mArrowChoseColor;
    private String mText;
    public enum Block {
        LEFT, TOP, RIGHT, BOTTOM, CENTER, NULL
    }
    public MenuView(Context context) {
        this(context, null);
    }
    public MenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性。
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.menuview);
        //获取字体大小,默认大小是16dp
        mLineColor = ta.getColor(R.styleable.menuview_lineColor, Color.BLACK);
        mArrowColor = ta.getColor(R.styleable.menuview_arrowColor, Color.BLACK);
        mChoseColor = ta.getColor(R.styleable.menuview_choseColor, Color.BLACK);
        mArrowChoseColor = ta.getColor(R.styleable.menuview_arrowChoseColor, Color.WHITE);
        mText = (String) ta.getText(R.styleable.menuview_mtext);
        mTextSize = ta.getDimension(R.styleable.menuview_mtextSize, 30);
        Log.d("tag", "mTextSize" + mTextSize);
        ta.recycle();
        mPaint = new Paint();
        mPaint.setColor(mLineColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);//线宽
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(mTextSize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("tag", "onMeasure");
        //1920x1080分辨率
        mWidth = getMeasuredWidth();  //获取控件宽度和高度
        mHeight = getMeasuredHeight();//600,600
        mLocation = new int[2];  //获取控件位置
        getLocationOnScreen(mLocation);//240,685
        Log.d("tag", "mWidth" + mWidth + "mHeight" + mHeight + "mLocation" + mLocation);
    }
    //点击事件回调
    private OnTouchBlockListener mOnTouchBlockListener;
    public void setOnTouchBlockListener(
            OnTouchBlockListener onTouchBlockListener) {
        mOnTouchBlockListener = onTouchBlockListener;
    }
    public interface OnTouchBlockListener {
        void onTop();
        void onBottom();
        void onLeft();
        void onRight();
        void onCenter();
    }
    private PointF mCenterPoint = new PointF(200f, 200f);
    private float mR = mWidth * 0.5f;
    private float mr = mR * 75f / 200f;
    private float gen2 = 1.41421356f;
    private PointF mPointA = new PointF(mCenterPoint.x - mR / gen2, mCenterPoint.y - mR / gen2);
    private PointF mPointB = new PointF(mCenterPoint.x + mR / gen2, mCenterPoint.y - mR / gen2);
    private PointF mPointC = new PointF(mCenterPoint.x + mr / gen2, mCenterPoint.y - mr / gen2);
    private PointF mPointD = new PointF(mCenterPoint.x - mr / gen2, mCenterPoint.y - mr / gen2);
    private PointF mPointE = new PointF(mCenterPoint.x - mR / gen2, mCenterPoint.y + mR / gen2);
    private PointF mPointF = new PointF(mCenterPoint.x + mR / gen2, mCenterPoint.y + mR / gen2);
    private PointF mPointG = new PointF(mCenterPoint.x + mr / gen2, mCenterPoint.y + mr / gen2);
    private PointF mPointH = new PointF(mCenterPoint.x - mr / gen2, mCenterPoint.y + mr / gen2);
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("tag", "onDraw");
        /** 圆心*/
        mCenterPoint = new PointF(mWidth * 0.5f, mHeight * 0.5f);
        Log.d("tag", mCenterPoint.x + "mCenterPoint.x" + mCenterPoint.y + "mCenterPoint.y");
        /**大小半径*/
        mR = mWidth * 0.5f;
        mr = mR * 75f / 200f;
        /** 四个圆弧区域的路径相关点*/
        mPointA = new PointF(mCenterPoint.x - mR / gen2, mCenterPoint.y - mR / gen2);
        mPointB = new PointF(mCenterPoint.x + mR / gen2, mCenterPoint.y - mR / gen2);
        mPointC = new PointF(mCenterPoint.x + mr / gen2, mCenterPoint.y - mr / gen2);
        mPointD = new PointF(mCenterPoint.x - mr / gen2, mCenterPoint.y - mr / gen2);
        mPointE = new PointF(mCenterPoint.x - mR / gen2, mCenterPoint.y + mR / gen2);
        mPointF = new PointF(mCenterPoint.x + mR / gen2, mCenterPoint.y + mR / gen2);
        mPointG = new PointF(mCenterPoint.x + mr / gen2, mCenterPoint.y + mr / gen2);
        mPointH = new PointF(mCenterPoint.x - mr / gen2, mCenterPoint.y + mr / gen2);
        Log.d("tag", mPointA.x + "mPointA.X" + mPointA.y + "mPointA.y");
        Log.d("tag", mPointB.x + "mPointB.X" + mPointB.y + "mPointB.y");
        /**画圆弧块*/
        //左侧
        drawSrcBlock(mChoseBlock == Block.TOP, mPointA, -135, mPointC, -45, canvas);
        //顶部
        drawSrcBlock(mChoseBlock == Block.RIGHT, mPointB, -45, mPointG, 45, canvas);
        //右侧
        drawSrcBlock(mChoseBlock == BOTTOM, mPointF, 45, mPointH, 135, canvas);
        //下册
        drawSrcBlock(mChoseBlock == LEFT, mPointE, 135, mPointD, 225, canvas);
        /**画中心圆,方便上色*/
        drawCircle(mChoseBlock == Block.CENTER, mCenterPoint, mr, canvas);
        //三角形高
        mTriHeight = 1.0f / 3.0f * (mR - mr);
        //三角形顶端距离圆心距离
        disForCenter = mr + 2f * mTriHeight;
        /**画四个箭头*/
        PointF top1 = new PointF(mCenterPoint.x, mCenterPoint.y - disForCenter);
        PointF top2 = new PointF(mCenterPoint.x + mTriHeight,
                mCenterPoint.y - disForCenter + mTriHeight);
        PointF top3 = new PointF(mCenterPoint.x - mTriHeight,
                mCenterPoint.y - disForCenter + mTriHeight);
        drawTriangle(mChoseBlock == Block.TOP, top1, top2, top3, canvas);
        PointF left1 = new PointF(mCenterPoint.x - disForCenter, mCenterPoint.y);
        PointF left2 = new PointF(mCenterPoint.x - disForCenter + mTriHeight,
                mCenterPoint.y + mTriHeight);
        PointF left3 = new PointF(mCenterPoint.x - disForCenter + mTriHeight,
                mCenterPoint.y - mTriHeight);
        drawTriangle(mChoseBlock == LEFT, left1, left2, left3, canvas);
        PointF bottom1 = new PointF(mCenterPoint.x, mCenterPoint.y + disForCenter);
        PointF bottom2 = new PointF(mCenterPoint.x + mTriHeight, mCenterPoint.y+disForCenter - mTriHeight);
        PointF bottom3 = new PointF(mCenterPoint.x - mTriHeight,
                mCenterPoint.y + disForCenter - mTriHeight);
        drawTriangle(mChoseBlock ==  BOTTOM, bottom1, bottom2, bottom3, canvas);
        PointF right1 = new PointF(mCenterPoint.x + disForCenter,
                mCenterPoint.y);
        PointF right2 = new PointF(mCenterPoint.x + disForCenter - mTriHeight,
                mCenterPoint.y+mTriHeight);
        PointF right3 = new PointF(mCenterPoint.x + disForCenter - mTriHeight,
                mCenterPoint.y -mTriHeight);
        drawTriangle(mChoseBlock == Block.RIGHT, right1, right2, right3, canvas);

    }

    /**画箭头*/
    private void drawTriangle(boolean isSlide, PointF p1, PointF p2, PointF p3,
                              Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);  //填充中间
        if (isSlide) {
            mPaint.setColor(mArrowChoseColor);
        } else {
            mPaint.setColor(mArrowColor);
        }
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.close();
        canvas.drawPath(path, mPaint);
    }
    /** 画圆  中心小圆*/
    private void drawCircle(boolean isSlide, PointF centerPoint, float mr, Canvas canvas) {
        mPaint.setColor(mLineColor);
        if (isSlide) {
            mPaint.setStyle(Paint.Style.FILL); //填充
        } else {
            mPaint.setStyle(Paint.Style.STROKE);
        }
        canvas.drawCircle(centerPoint.x, centerPoint.y, mr, mPaint);
        Rect bounds = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), bounds);
        int textwidth = bounds.width();
        int textHeight = bounds.height();
        if (isSlide) {
            mPaint.setColor(mArrowChoseColor);
        } else {
            mPaint.setColor(mArrowColor);
        }
        canvas.drawText(mText, mCenterPoint.x - textwidth * 0.5f, mCenterPoint.y + textHeight * 0.5f,
                mPaint);
        mPaint.setColor(mLineColor);
    }
    /** 画扇形边框*/
    private void drawSrcBlock(boolean isSolid, PointF pointA, int startAngle, PointF pointC,
                              int startAngle1,
                              Canvas canvas) {
        if (isSolid) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mChoseColor);
        } else {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mLineColor);
        }
        Path path = new Path();
        path.moveTo(pointA.x, pointA.y);
        path.arcTo(new RectF(0, 0, mWidth, mWidth), startAngle, 90);
        path.lineTo(pointC.x, pointC.y);
        path.arcTo(new RectF(mWidth * 0.5f * 125f / 200f, mHeight * 0.5f * 125f / 200f,
                mWidth * 0.5f * 275f / 200f, mWidth * 0.5f * 275f / 200f), startAngle1, -90);
        path.close();
        canvas.drawPath(path, mPaint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                evaluateTouchBlock(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mChoseBlock = NULL;
                invalidate();
                break;
        }
        return true;
    }
    //计算点的触摸位置块
    private void evaluateTouchBlock(float x, float y) {
        float dis = GeometryUtil.getDistanceBetween2Points(mCenterPoint,
                new PointF(x, y));
        if (dis >= mr && dis <= mR) { //大小圆之间
            float datY = y - mCenterPoint.y;
            float datX = x - mCenterPoint.x;
            if (datX==0) {//排除斜率为0状况
                if (datY > 0) {
                    mChoseBlock = BOTTOM;
                } else {
                    mChoseBlock = Block.TOP;
                }
            } else {
                float k = datY / datX;
                if ((k >= 1 || k <= -1)) {
                    if (datY <= 0) {
                        mChoseBlock = Block.TOP; //LEFT
                    } else {
                        mChoseBlock = BOTTOM; //RIGHT
                    }
                } else {
                    if (datX <= 0) {
                        mChoseBlock = LEFT; //BOTTOM
                    } else {
                        mChoseBlock = Block.RIGHT;//TOP
                    }
                }
            }
            invalidate();
        } else if (dis < mr) {//小圆
            mChoseBlock = Block.CENTER;
            invalidate();
        } else {
        }
        //
        if (mOnTouchBlockListener != null) {

            switch (mChoseBlock) {
                case TOP:
                    //mOnTouchBlockListener.onTop();
                    Log.d("ppp","上上上");
                   if(CREDIT>=10){
                        SocketTool.setSend(mChoseBlock.TOP);
                        socketTool.send();
                    }
                    else {
                        socketTool.setNoAction(mChoseBlock.TOP);
                        socketTool.send();
                    }
                    break;
                case BOTTOM:
                    //mOnTouchBlockListener.onBottom();
                    Log.d("ppp","下下下");
                    if(CREDIT>=10){
                        SocketTool.setSend(mChoseBlock.BOTTOM);
                        socketTool.send();
                   }
                    else {
                        socketTool.setNoAction(mChoseBlock.BOTTOM);
                        socketTool.send();
                    }


                    break;
                case LEFT:
                    //mOnTouchBlockListener.onLeft();
                    Log.d("ppp","左左左");
                    if(CREDIT>=10){
                        SocketTool.setSend(mChoseBlock.LEFT);
                        socketTool.send();
                  }

                    else {
                        socketTool.setNoAction(mChoseBlock.LEFT);
                        socketTool.send();
                   }



                    break;
                case RIGHT:
                    //mOnTouchBlockListener.onRight();
                    Log.d("ppp","右右右");
                    /*
                   if(CREDIT>=10){
                        SocketTool.setSend(mChoseBlock.RIGHT);
                        socketTool.send();
                   }

                    else {
                        socketTool.setNoAction(mChoseBlock.RIGHT);
                        socketTool.send();
                    };


                     */
                    Toast.makeText(getContext(),"投放正确积分加1",Toast.LENGTH_LONG);


                    break;
                case CENTER:
                    //mOnTouchBlockListener.onCenter();
                   // Toast.makeText(getContext(),"分类正确，积分加1",Toast.LENGTH_SHORT).show();
                    /*
                   if(CREDIT>=10){
                        SocketTool.setSend(mChoseBlock.CENTER);
                        socketTool.send();
                  }

                    else {
                        socketTool.setNoAction(mChoseBlock.CENTER);
                        socketTool.send();
                    }
                    */
                    Toast.makeText(getContext(),"投放错误积分减1",Toast.LENGTH_LONG);



                    break;
                    default:
                        break;
            }
        }
    }

}
