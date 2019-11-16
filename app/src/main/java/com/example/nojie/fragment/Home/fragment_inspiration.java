package com.example.nojie.fragment.Home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nojie.MyIndexRecyclerViewAdapter;
import com.example.nojie.R;
import com.example.nojie.fragment.fragment_article;

import java.util.List;

public class fragment_inspiration extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private Fragment currentFragment = new Fragment();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_homepage, container, false);
        initContentView(root);
        initData();
        return root;
    }

    private void initData() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getContentData();
        LiveData<List<Inspiration>> mInspirationList=homeViewModel.getmInspirationList();
        List<Inspiration> myDataset = mInspirationList.getValue();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MyIndexRecyclerViewAdapter adapter = new MyIndexRecyclerViewAdapter(myDataset);
        recyclerView.setAdapter(adapter);
        adapter.setOnRecyclerItemClickLitener(new MyIndexRecyclerViewAdapter.onRecyclerItemClickLitener() {
            @Override
            public void onRecyclerItemClick(RecyclerView.ViewHolder view, int position) {
                Toast.makeText(getContext(), "内部点击"+position, Toast.LENGTH_SHORT).show();
                showFragment(new fragment_article());
            }

            @Override
            public void onRecyclerItemLongClick(RecyclerView.ViewHolder view, int position) {
                Toast.makeText(getContext(), "内部长按"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showFragment(Fragment fragment){
        FragmentManager manager=getFragmentManager();
        if (currentFragment != fragment){//  判断传入的fragment是不是当前的currentFragmentgit
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(currentFragment);//  不是则隐藏
            currentFragment = fragment;  //  然后将传入的fragment赋值给currentFragment
            if (!fragment.isAdded()){ //  判断传入的fragment是否已经被add()过
                transaction.add(R.id.nav_host_fragment,fragment).show(fragment).commit();
            }else{
                transaction.show(fragment).commit();
            }
        }
    }


    private void initContentView(View root) {
        recyclerView = (RecyclerView) root.findViewById(R.id.index_recycler_view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        System.out.println("hahaha"+"onAttach");
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        System.out.println("hahaha"+"onResume");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        System.out.println("hahaha"+"onDestory");
        super.onDestroy();
    }
}
