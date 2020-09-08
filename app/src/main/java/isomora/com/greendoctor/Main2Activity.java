package isomora.com.greendoctor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static isomora.com.greendoctor.R.*;

public class Main2Activity extends AppCompatActivity {


    private ViewPager viewPager;
    private  Intromanager intromanager;
    private  ViewPagerAdapter viewPagerAdapter;
    private TextView[] dots;
    Button next, skip;
    private LinearLayout dotsLayout;


    private  int[] layouts;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intromanager=new Intromanager(this);
        if (!intromanager.Check()){
            intromanager.setFirst(false);
            Intent i=new Intent(Main2Activity.this,MainActivity.class);
            startActivity(i);
            finish();

        }

        if(Build.VERSION.SDK_INT>21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        setContentView(layout.activity_main2);

        viewPager= findViewById(id.view_pager);
        dotsLayout= findViewById(id.layoutDots);
        skip= findViewById(id.btn_skip);
        next= findViewById((id.btn_next));

        layouts=new int[]{layout.activity_screen1,
                layout.activity_screen2, layout.activity_screen3};

        addBottomDots(0);
        changeStatusBarColor();
        viewPagerAdapter=new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListner);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intromanager.setFirst(false);
                Intent i=new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current=getItem(+1);
                if (current<layouts.length){
                    viewPager.setCurrentItem(current);
                }
                else {
                    intromanager.setFirst(false);
                    Intent i=new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });

//        Toolbar toolbar = findViewById(id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void addBottomDots( int position){

        dots=new TextView[layouts.length];
        int[] colorActive=getResources().getIntArray(array.array_dot_active);
        int[] colorInactive=getResources().getIntArray(array.array_dot_inactive);
        dotsLayout.removeAllViews();
        for (int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(50);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);
        }

        if(dots.length>0)
            dots[position].setTextColor(colorActive[position]);

    }

    private  int getItem(int i){
        return  viewPager.getCurrentItem()+i;
    }

    ViewPager.OnPageChangeListener viewListner=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if (position==layouts.length-1) {
                next.setText("PROCEED");
                next.setTextColor(Color.parseColor("#d00300"));
                skip.setVisibility(View.GONE);
            }
            else {
                next.setText("NEXT");
                skip.setVisibility(View.VISIBLE);
            }
        }


        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private  void  changeStatusBarColor(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP);
        {
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class ViewPagerAdapter extends PagerAdapter{

        private LayoutInflater layoutInflater;

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v =layoutInflater.inflate(layouts[position], container, false);
            container.addView(v);

            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;

        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View v=(View)object;
            container.removeView(v);
        }
    }

}

