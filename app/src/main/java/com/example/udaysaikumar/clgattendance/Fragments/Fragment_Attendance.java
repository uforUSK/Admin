package com.example.udaysaikumar.clgattendance.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.udaysaikumar.clgattendance.R;
import com.example.udaysaikumar.clgattendance.RetrofitPack.RetroGet;
import com.example.udaysaikumar.clgattendance.RetrofitPack.RetrofitMarksServer;
import com.example.udaysaikumar.clgattendance.RetrofitPack.TimeStampClass;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Attendance extends Fragment {

    RetroGet retroGet;
    String API_KEY="AKPhEaFsE8c1f98hiX1VXa0dj5_7KFq0";
ProgressBar progressAttendance;
LinearLayout linearLayout;
    String URL_PROFILE="https://lh3.googleusercontent.com/Df4bZYWLSwgZ_TksplBpLiHLjCYl6TLQqDRwwEFfUSvZBpMfizmgBcSVZyk8cgR4pQBZvTDZuv0MfZKYvOSHfCJVijImxWbJsXXaTGQYzq1Jh8yINHb0v-mrOmeuLratj8ZG4CaUReRrXxL2e1rlJZmvVxro74p6aiGMe3QTKcrru88Sn5WgvfLNRzRsVV4aYhr2y8k4iPKOmb3CI7lGbR9Z48oenUVcBD0f895j60N59d3ZchTpQWLJU-yNHD0xpcTqRtPcL6eqKJnOFnaEj5vhxswJWXeaJp96JWiKyfVBYAr9XGOmPwyzjvJz5XICIX2WxStqLM3xbZ3Tlv-h0lZGwITOZiyabwy9GC8ugjqrFmI2KkjqgQc5R58ASkMSW5-5O-AATfFcVzXcosO-ZFkmhBV4ixzO0i0khfY4ubJ0pCFXX7y3RPUEF508ItoYBSyMgqMdVUJncs8IkKaW6aUTzHNgRFUWOTUtlmwpcIf2LKXH2oj2CAWL7MbQ1Gml4PxBhPF_9HUZOewCawggYCVAX-Bb4KNCZITQxwqvTrA7QITUA_b399pdVyo=s665-w499-h665-no";
    TableLayout tableAttendance;
    MaterialCalendarView mCV;
    String timestamp;
    View v;
    String UNAME;
    TextView bad,satisfacotry,excellent;
    String ATTENDANCE;
   // CircleDisplay cd;
    ImageView emoji;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v=inflater.inflate(R.layout.fragment_fragment__attendance, container, false);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        bad=v.findViewById(R.id.bad);
        satisfacotry=v.findViewById(R.id.satisfactory);
        excellent=v.findViewById(R.id.excellent);
        tableAttendance=v.findViewById(R.id.tableAttendance);
        progressAttendance=v.findViewById(R.id.progressAttendance);
        linearLayout=v.findViewById(R.id.attendacelayout);
        mCV=v.findViewById(R.id.calenderView);
        mCV.canScrollVertically(1);
        //cd=v.findViewById(R.id.circledisplay);
       // cd.setValueWidthPercent(10f);
        //cd.setTextSize(30f);
       // cd.setColor(Color.RED);
       // cd.setDrawText(true);
       // cd.setFormatDigits(2);
       // cd.setUnit("%");
       // cd.setStepSize(0.08f);
        progressAttendance.setIndeterminate(true);
       // progressAttendance.setVisibility(View.VISIBLE);
        SharedPreferences sharedPreferencess=v.getContext().getSharedPreferences("MyLogin",MODE_PRIVATE);
        UNAME=sharedPreferencess.getString("username","");
        String qq="{\"regno\":{$eq:\""+UNAME+"\"}}";
        retroGet = RetrofitMarksServer.getSecRetrofit().create(RetroGet.class);
        //linearLayout.setVisibility(View.INVISIBLE);
        Call<String> dataAttendance = retroGet.getPercentage("PERCENTAGE",API_KEY,qq);
        dataAttendance.enqueue(new Callback<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                assert response.body() != null;
                String json=response.body();
                assert json != null;
                if(!json.isEmpty()){
                    try {
                       JSONArray jsonArray = new JSONArray(json);
                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                        Iterator<String>iterator=jsonObject.keys();
                        String st=null;
                        while (iterator.hasNext()){
                             st=iterator.next();
                        }
                        String jsonObject1=jsonObject.get(st).toString();

                      Float f=  Float.parseFloat(jsonObject1);

                       // cd.showValue(f, 100, false);
                        if(f>=75){
                            excellent.setText(f.toString());
                            excellent.setBackground(getResources().getDrawable(R.drawable.circle_aggregade_excellent));

                        }else if(f<75 && f>65){
                            satisfacotry.setText(f.toString());
                            excellent.setBackground(getResources().getDrawable(R.drawable.circle_aggregade_satisfactory));

                        }
                        else {
                            bad.setText(f.toString());
                            excellent.setBackground(getResources().getDrawable(R.drawable.circle_aggregade_bad));

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                                   }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(v.getContext(), "please connect to active network", Toast.LENGTH_LONG).show();
               progressAttendance.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                 }
        });
       // finalattendance=v.findViewById(R.id.finalattendance);

        SharedPreferences sharedPreferences=v.getContext().getSharedPreferences("MyLogin",MODE_PRIVATE);
        UNAME=sharedPreferences.getString("username","");
        ATTENDANCE=sharedPreferences.getString("attendance","");

      //  finalattendance.setText("78 %");

retroGet= TimeStampClass.getTimestamp().create(RetroGet.class);
Call<String> stringCall=retroGet.getTimeStamp();
stringCall.enqueue(new Callback<String>() {
    @Override
    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
        String json=response.body();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("zones");
            JSONObject jsonObject1=jsonArray.getJSONObject(0);
           timestamp=jsonObject1.getString("timestamp");
           //System.out.println("dateis"+System.currentTimeMillis());
            Timestamp time=new Timestamp((Long.parseLong(timestamp)-19800)*1000L);
            Date date=new Date(time.getTime());
            System.out.println("dateis1"+date);
                mCV.setDateSelected(date,true);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    final CalendarDay calendarDay=mCV.getSelectedDate();

mCV.addDecorator(new DayDecorator(v.getContext(),calendarDay));

      //System.out.println("currentdate"+mCV.getSelectedDate());

       // callAttendance();
        mCV.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

               // Toast.makeText(v.getContext(),mCV.getSelectedDate().toString(),Toast.LENGTH_SHORT).show();

            String adate=getDate(mCV.getSelectedDate().toString());
            callAttendance(adate);

            }
        });
        progressAttendance.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
        progressAttendance.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        Toast.makeText(v.getContext(), "please connect to active network", Toast.LENGTH_LONG).show();

    }
});

        //progressAttendance.setVisibility(View.INVISIBLE);
        //linearLayout.setVisibility(View.VISIBLE);
        return v;
    }
    public String getDate(String str){
        String[] s=str.replace("CalendarDay{","").replace("}","").split("-");
        String ss=s[2]+s[1]+s[0];
        int f=Integer.parseInt(s[1])+1;
        String ff=s[0].substring(2,4);
        System.out.println("hellojson"+s[2]+"/"+f+"/"+ff);
        return s[2]+"/"+f+"/"+ff;
    }
    public void callAttendance(String adate){
        final String q="{\"regno\":{$eq:\""+UNAME+"\"},\"date\":{$eq:\""+adate+"\"}}";
        retroGet = RetrofitMarksServer.getSecRetrofit().create(RetroGet.class);
        Call<String> dataAttendance = retroGet.getAttendance(ATTENDANCE,API_KEY,q);
        dataAttendance.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                if(response.body()!=null)
                {
                    if(!Objects.requireNonNull(response.body()).isEmpty())
                    {
                        String json=response.body();
                        System.out.println("hellojson"+json);
                        FragmentManager manager=getFragmentManager();
                        try {
                            FragmentTransaction fr = null;
                            if (manager != null) {
                                fr = manager.beginTransaction();
                                MyDialogFragment fragment = new MyDialogFragment();
                                Bundle b = new Bundle();
                                b.putString("dialog", json);
                                fragment.setArguments(b);
                                fragment.show(fr, "myfrag");
                            }

                        }catch (Exception e){}

                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                Toast.makeText(v.getContext(), "please connect to active network", Toast.LENGTH_LONG).show();
            }
        });
    }


}
