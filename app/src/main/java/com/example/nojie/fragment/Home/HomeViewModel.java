package com.example.nojie.fragment.Home;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.nojie.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Inspiration>> mInspirationList=new MutableLiveData<>();

    public LiveData<List<Inspiration>> getmInspirationList() {
        if(mInspirationList == null){
            System.out.println("mInspirationList==null -----> 位置HomeViewModel.java Line19");
            mInspirationList = new MutableLiveData<>();
        }
        return mInspirationList;
    }
    public void getContentData(){
        List<Inspiration> temp = new ArrayList<Inspiration>();
        Calendar cal=Calendar.getInstance();
        Integer y=cal.get(Calendar.YEAR);
        Integer m=cal.get(Calendar.MONTH);
        Integer d=cal.get(Calendar.DATE);
        String date = y.toString()+"/"+m.toString()+"/"+d.toString();
        Inspiration exml = new Inspiration("音乐","水星记/郭顶","着迷于你眼睛 " +
                "银河有迹可循\n穿过时间的缝隙\n它依然真实地\n吸引我轨迹\n这瞬眼的光景\n最亲密的距离",null,date);
        temp.add(exml);temp.add(exml);temp.add(exml);temp.add(exml);
        mInspirationList.setValue(temp);
    }
}
