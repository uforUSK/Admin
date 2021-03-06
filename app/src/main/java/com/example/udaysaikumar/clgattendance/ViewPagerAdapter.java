package com.example.udaysaikumar.clgattendance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.udaysaikumar.clgattendance.MarksFrgments.FragmentSem_11;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    List<String> list=new ArrayList<>();
    List<String> listSecond=new ArrayList<>();

    Map<String,JSONObject> map=new LinkedHashMap<>();
    //String[] keys;
    public  String[] tabs={"SEM 11","SEM 12","SEM 21","SEM 22","SEM 31","SEM 32","SEM 41","SEM 42"};
 public JSONObject job;
 public ViewPagerAdapter(FragmentManager fm,Map<String,JSONObject> map,List<String> listSecond){
     super(fm);
     this.map=map;
     this.listSecond=listSecond;
     System.out.println("changedbundle"+listSecond);
//     System.out.println("inmap" + job);
//     if (job == null) {
//     } else{
//         Iterator<String> it = job.keys();
//         while (it.hasNext()) {
//             list.add(it.next());
//
//         }
//         for (String s : list) {
//             try {
//                 map.put(s, job.getJSONObject(s));
//             } catch (JSONException e) {
//                 e.printStackTrace();
//             }
//         }
//         map.remove("_id");
//         for (Map.Entry<String, JSONObject> maps : map.entrySet()) {
//             System.out.println("inmap" + maps.getKey() + maps.getValue());
//         }
//     }
 }



    @Override
    public Fragment getItem(int i) {
//        switch (i)
//        {
//            case 0:return new FragmentSem_11(listSecond.get(i));
//           case 1:return new FragmentSem_12();
//            case 2:return  new FragmentSem_21();
//            case 3:return  new FragmentSem_22();
//            case 4:return  new FragmentSem_31();
//           case 5:return  new FragmentSem_32();
//           case 6:return  new FragmentSem_41();
//          case 7:return  new FragmentSem_42();
//        }
        Bundle bundle=new Bundle();
        bundle.putString("data",listSecond.get(i));
        FragmentSem_11 fra=new FragmentSem_11();
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    public int getCount() {
        return listSecond.size();
    }
    @Override
    public CharSequence getPageTitle(int i)
    {
       return tabs[i];
    }

}
