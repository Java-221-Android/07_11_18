package com.sheygam.java_221_07_11_18;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MyFragment extends Fragment implements View.OnClickListener{
//    public MyFragment(String title){
//
//    }

    private MyFragmentListener listener;

    private int color;
    private float scale;

    private EditText inputText;
    private String input = "";

    public static MyFragment newInstance(int color, float scale) {
        MyFragment fragment = new MyFragment();
        fragment.color = color;
        fragment.scale = scale;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }

    public String getInput(){
        return input;
    }

    public void setOnFragmentListener(MyFragmentListener listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment,container,false);
        view.setBackgroundColor(color);
        view.setScaleX(scale);
        view.setScaleY(scale);
        TextView titleTxt = view.findViewById(R.id.title_txt);
        titleTxt.setText("My title");
        view.findViewById(R.id.clickBtn).setOnClickListener(this);
        inputText = view.findViewById(R.id.inputText);
        MainActivity activity = (MainActivity) getActivity();
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TAG", "onDetach: " );
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.clickBtn){
            if(listener!= null){
//                input = inputText.getText().toString();
                Bundle args = new Bundle();
                args.putString("INPUT",inputText.getText().toString());
                setArguments(args);
                listener.onClick("My msg");
            }
        }
    }

    interface MyFragmentListener{
        void onClick(String str);
    }
}
