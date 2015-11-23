package com.wenkchan.calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenkchan.calendar.adapter.CommonAdapter;
import com.wenkchan.calendar.bean.CalendarDate;
import com.wenkchan.calendar.util.DateUtil;
import com.wenkchan.calendar.util.ViewHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private GridView mGvCalendar;
    private Context mContext;
    private View mView;
    private static ArrayList<CalendarDate> mAlDays;
    private CalendarDate mCalendarDate; // 自定义的日期，包括year,month,day，默认为当前日期
    private ImageView mIvCalendarPre, mIvCalendarNext;
    private TextView mTvYearAndDay;
    private CommonAdapter<CalendarDate> mAdapterCalendar;//如果想给item加一些状态之类的属性，可以Calendar添加一些属性值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        registerListener();
    }

    private void initView() {
        mContext = this;
        mAlDays = new ArrayList<>();
        mCalendarDate = new CalendarDate();
        mGvCalendar = (GridView) findViewById(R.id.gv_calendar);
        mIvCalendarPre = (ImageView) findViewById(R.id.iv_calendar_pre_month);
        mIvCalendarNext = (ImageView) findViewById(R.id.iv_calendar_next_month);
        mTvYearAndDay = (TextView) findViewById(R.id.tv_calendar_current_month);
    }

    private void initEvent() {
       mAdapterCalendar = new CommonAdapter<CalendarDate>(mContext, mAlDays, R.layout.item_calendar) {
            @Override
            public void convert(ViewHolder helper, CalendarDate item) {
                TextView tvDate = helper.getView(R.id.tv_calendar_item_day);
                if (0!=item.getDay()){
                    tvDate.setText(item.getDay() + "");
                }else{
                    tvDate.setText("");
                }
            }
        };
        getDate();
        mGvCalendar.setAdapter(mAdapterCalendar);
    }

    private void registerListener() {
        mIvCalendarNext.setOnClickListener(this);
        mIvCalendarPre.setOnClickListener(this);
    }

    private int mCurrentMonthDays;//当前月的天数
    private int mFirstWeek;//当前月第一天是星期几

    /**
     * 获取日历信息的相关数据
     */
    private void getDate() {
        mAlDays.clear();
        mCurrentMonthDays = DateUtil.getMonthDays(mCalendarDate.getYear(), mCalendarDate.getMonth());
        mFirstWeek = DateUtil.getWeekDayFromDate(mCalendarDate.getYear(), mCalendarDate.getMonth());
        int day = 0;
        for (int i = 0; i < 42; i++) {//月份表格的规格是6x7，所以循环42次
            if (mFirstWeek <= i && mFirstWeek + mCurrentMonthDays > i) {
                day++;
                mAlDays.add(new CalendarDate(mCalendarDate.getYear(),
                        mCalendarDate.getMonth(), day));
            } else {//空白的
                mAlDays.add(new CalendarDate(mCalendarDate.getYear(),
                        mCalendarDate.getMonth(), 0));
            }
        }
        mAdapterCalendar.notifyDataSetChanged();
        mTvYearAndDay.setText(mCalendarDate.getYear() + "年" + mCalendarDate.getMonth() + "月");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_calendar_pre_month://上一月
                mCalendarDate = CalendarDate.modifyDayForObject(new CalendarDate(mCalendarDate.getYear(),
                        mCalendarDate.getMonth() - 1, 0));
                getDate();
                break;
            case R.id.iv_calendar_next_month://下个月
                mCalendarDate = CalendarDate.modifyDayForObject(new CalendarDate(mCalendarDate.getYear(),
                        mCalendarDate.getMonth() + 1, 0));
                getDate();
                break;
        }


    }

}
