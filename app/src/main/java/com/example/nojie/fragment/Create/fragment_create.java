package com.example.nojie.fragment.Create;

import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.nojie.MainActivity;
import com.example.nojie.R;

public class fragment_create extends Fragment implements SearchView.OnQueryTextListener{

    private SearchView searchView;
    private FrameLayout createView1;
    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private CardView cardView4;

    private CardView work_cardView1;
    private CardView work_cardView2;
    private CardView work_cardView3;
    private CardView work_cardView4;

    private TextView myWork_title1;
    private TextView myWork_location;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create, container, false);

        initContent(root);
        initData();
        return root;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initContent(final View root) {
        searchView = root.findViewById(R.id.searchView);
        cardView1 = root.findViewById(R.id.create_view1);
        cardView2 = root.findViewById(R.id.create_view2);
        cardView3 = root.findViewById(R.id.create_view3);
        cardView4 = root.findViewById(R.id.create_view4);

        work_cardView1 = root.findViewById(R.id.create_myWork_cardView1);
        work_cardView2 = root.findViewById(R.id.create_myWork_cardView2);
//        work_cardView3 = root.findViewById(R.id.create_myWork_cardView3);
//        work_cardView4 = root.findViewById(R.id.create_myWork_cardView4);

        myWork_title1 = root.findViewById(R.id.create_myWork_title1);
        myWork_location = root.findViewById(R.id.create_myWork_location1);
        searchView.setSubmitButtonEnabled(true);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initData() {
        work_cardView1.setElevation(10);
        myWork_title1.setText("海风的声音");
        myWork_location.setText("希腊");
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
