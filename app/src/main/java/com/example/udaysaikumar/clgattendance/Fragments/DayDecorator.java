package com.example.udaysaikumar.clgattendance.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;

import com.example.udaysaikumar.clgattendance.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class DayDecorator implements DayViewDecorator{
    Drawable drawable;
    Context c;
  private   CalendarDay calendarDay;
    public DayDecorator(Context c,CalendarDay calendarDay) {
        drawable=ContextCompat.getDrawable(c,R.drawable.current_day_drawable);
        this.c=c;
        this.calendarDay=calendarDay;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(calendarDay);
    }

    @Override
    public void decorate(DayViewFacade view) {
        //view.addSpan(drawable);
        view.setBackgroundDrawable(drawable);
        view.addSpan(new ForegroundColorSpan(c.getResources().getColor(R.color.todaydate)));
    }
}
