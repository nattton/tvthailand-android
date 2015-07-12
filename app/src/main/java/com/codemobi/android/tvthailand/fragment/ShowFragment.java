package com.codemobi.android.tvthailand.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codemobi.android.tvthailand.R;
import com.codemobi.android.tvthailand.adapter.ShowAdapter;
import com.codemobi.android.tvthailand.dao.show.ShowCollectionDao;

/**
 * Created by nattapong on 7/10/15 AD.
 */
public class ShowFragment extends Fragment {

    public ShowFragment() {
        super();
    }

    public static ShowFragment newInstance() {
        ShowFragment fragment = new ShowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_show, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // init instance with rootView.findViewById here
        setRetainInstance(true);

        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvShow);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter mAdapter = new ShowAdapter(getActivity().getApplicationContext(), ShowCollectionDao.MockShowList());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
