package com.github.wheroj.cloneevent;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.wheroj.cloneevent.common.Utils;
import com.github.wheroj.cloneevent.widget.BorderImageView;
import com.github.wheroj.cloneevent.widget.DrawBoard;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private DrawBoard drawBoard;
    private Button clear;
    private BorderImageView imageViewWrite;
    private BorderImageView imageViewClone;
    private Canvas canvas;
    private Bitmap bitmap;
    private Path path;
    private Button btnSave;
    private RecyclerView recycleView;
    private ArrayList<Bitmap> bitmaps;
    private PicAdapter picAdapter;
    private Button btnDel;
    private Button btnChange;
    private Button btnChar;
    private LinearLayout llInput;
    private boolean isLeftClone = true;
    private RecyclerView recyclerViewChar;
    private LinearLayout llChar;
    private BorderImageView imageViewChar;
    private boolean isCharInput = false;
    private int fontSize = 64;

    @Override
    protected int getContainerLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        drawBoard = (DrawBoard) findViewById(R.id.drawboard);
        clear = (Button) findViewById(R.id.button);
        clear.setOnClickListener(this);

        imageViewWrite = (BorderImageView) findViewById(R.id.imageViewWrite);
        imageViewClone = (BorderImageView) findViewById(R.id.imageViewClone);
        imageViewChar = (BorderImageView) findViewById(R.id.imageViewChar);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        llInput = (LinearLayout) findViewById(R.id.ll_input);
        llChar = (LinearLayout) findViewById(R.id.ll_char);
        recyclerViewChar = (RecyclerView) findViewById(R.id.recyclerViewChar);

        bitmaps = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(thisContext, 8, GridLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(layoutManager);

        ViewTreeObserver viewTreeObserver = imageViewWrite.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = imageViewWrite.getWidth();
                imageViewWrite.getLayoutParams().height = (int) ((4.0 / 3.0) * width);
                imageViewClone.getLayoutParams().height = (int) ((4.0 / 3.0) * width);
                if (!isCharInput) {
                    draw(imageViewWrite, imageViewClone);
                }
            }
        });

        btnDel = (Button) findViewById(R.id.btn_del);
        btnChange = (Button) findViewById(R.id.btn_change);
        btnChar = (Button) findViewById(R.id.btn_char);
        btnDel.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnChar.setOnClickListener(this);
    }

    private void draw(final BorderImageView imageWrite, final BorderImageView imageClone) {
        createCanvas(imageClone);

        imageWrite.setImageBitmap(bitmap);
        imageClone.setImageBitmap(bitmap);

        path = new Path();

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.getPx(3));
        paint.setColor(getResources().getColor(R.color.gray_3));
        imageClone.setOnTouchListener(null);
        imageWrite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (path == null) {
                            path = new Path();
                        } else {
                            path.reset();
                        }
                        path.moveTo(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        path.lineTo(event.getX(), event.getY());
                        canvas.drawPath(path, paint);
                        imageWrite.setImageBitmap(bitmap);
                        imageClone.setImageBitmap(bitmap);
                        break;
                }
                return true;
            }
        });
    }

    private void createCanvas(BorderImageView imageView) {
        int measuredWidth = imageView.getMeasuredWidth();
        int measuredHeight = imageView.getMeasuredHeight();

        System.out.println(measuredWidth + ",  -----------  " + measuredHeight);
        bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    public void clearPic() {
        if (canvas != null) {
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawPaint(paint);
            if (path != null) {
                path.reset();
            }
            imageViewClone.setImageBitmap(bitmap);
            imageViewWrite.setImageBitmap(bitmap);
            imageViewChar.setImageBitmap(bitmap);
        }
    }

    @Override
    protected boolean getHasTitle() {
        return false;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                clearPic();
                break;
            case R.id.btn_save:
                if (bitmaps != null) {
                    bitmaps.add(bitmap);
                    initShow();
                }
                break;
            case R.id.btn_del:
                if (bitmaps != null && !bitmaps.isEmpty()) {
                    bitmaps.remove(bitmaps.size() - 1);
                    initShow();
                }
                break;
            case R.id.btn_change:
                change();
                break;
            case R.id.btn_char:
                showChar();
                break;
        }
    }

    private void showChar() {
        if (!isCharInput) {
            llChar.setVisibility(View.VISIBLE);
            llInput.setVisibility(View.GONE);
            btnChar.setText("手写\n输入");
            isCharInput = true;
            recyclerViewChar.setLayoutManager(new GridLayoutManager(thisContext, 6, LinearLayoutManager.VERTICAL, false));

            ArrayList<Character> datas = new ArrayList<>();
            for (int i = 0; i < 36; i++) {
                if (i < 24) datas.add((char) (65 + i));
                else datas.add((char) ((i - 24) + 48));
            }
            CharAdapter adapter = new CharAdapter(datas);
            adapter.setOnItemCLickListener(new CharAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int position, Character character) {
                    createCanvas(imageViewChar);
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(getResources().getColor(R.color.gray_3));
                    paint.setStrokeWidth(Utils.getPx(1));
                    paint.setTextSize(Utils.getPx(fontSize));

                    int tx = (int) ((imageViewChar.getWidth() - paint.measureText(character.toString())) / 2);
                    int measuredHeight = imageViewChar.getMeasuredHeight();
                    int ty = (int) ((measuredHeight - Utils.getPx(fontSize)) / 2 + getFontLeading(paint));
                    canvas.drawText(character.toString(), tx, ty, paint);
                    imageViewChar.setImageBitmap(bitmap);
                }
            });
            recyclerViewChar.setAdapter(adapter);
        } else {
            llChar.setVisibility(View.GONE);
            llInput.setVisibility(View.VISIBLE);
            btnChar.setText("字符\n输入");
            isCharInput = false;
        }
    }

    public void change(){
        if (!isCharInput) {
            if (isLeftClone) {
                isLeftClone = false;
                draw(imageViewClone, imageViewWrite);
            } else {
                isLeftClone = true;
                draw(imageViewWrite, imageViewClone);
            }
        } else {
            View childAt0 = llChar.getChildAt(0);
            llChar.removeView(childAt0);
            llChar.addView(childAt0);
            llChar.requestLayout();
        }
    }

    private void initShow() {
        if (picAdapter == null) {
            picAdapter = new PicAdapter(thisContext, bitmaps);
            recycleView.setAdapter(picAdapter);
        } else picAdapter.notifyDataSetChanged();
        recycleView.scrollToPosition(bitmaps.size());
        if (isCharInput) createCanvas(imageViewChar);
        else createCanvas(imageViewClone);
    }

    /**
     * @return 返回指定笔离文字顶部的基准距离
     */
    public static float getFontLeading(Paint paint)  {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.leading- fm.ascent;
    }
}

class CharAdapter extends RecyclerView.Adapter<CharHolder>{

    private ArrayList<Character> datas;
    public CharAdapter(ArrayList<Character> datas) {
        this.datas = datas;
    }
    @Override
    public CharHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_char, null);
        return new CharHolder(view);
    }

    @Override
    public void onBindViewHolder(CharHolder holder, int position) {
        holder.setData(datas.get(position), position, mListener);
    }

    @Override
    public int getItemCount() {
        if (datas == null)
            return 0;
        return datas.size();
    }

    private onItemClickListener mListener;
    public void setOnItemCLickListener(onItemClickListener OnItemClickListener) {
        mListener = OnItemClickListener;
    }
    public interface onItemClickListener{
        void onItemClick(int position, Character character);
    }
}

class CharHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public CharHolder(View itemView) {
        super(itemView);
        textView = (TextView)itemView.findViewById(R.id.textView);
    }

    public void setData(final Character str, final int position, final CharAdapter.onItemClickListener mListener) {
        textView.setText(str.toString());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position, str);
            }
        });
    }
}
