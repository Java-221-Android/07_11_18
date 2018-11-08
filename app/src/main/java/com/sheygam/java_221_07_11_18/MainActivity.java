package com.sheygam.java_221_07_11_18;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyFragment.MyFragmentListener {
    private static float scale = (float) 1.0;
    private Button addBtn;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(this);
        findViewById(R.id.replace_btn).setOnClickListener(this);
        findViewById(R.id.remove_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_btn){
            Random rnd = new Random();
            int r = rnd.nextInt(256);
            int g = rnd.nextInt(256);
            int b = rnd.nextInt(256);
            int color = Color.rgb(r,g,b);
            MyFragment fragment = MyFragment.newInstance(color,scale);
            fragment.setOnFragmentListener(this);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
//            if(count % 2 == 0) {
                transaction.add(R.id.container, fragment,"MY_FRAG");
//            }else{
//                transaction.add(R.id.container,fragment);
//            }
//            transaction.replace(R.id.container,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            scale -= 0.1;

            count++;

        }else if(v.getId() == R.id.replace_btn){
            Random rnd = new Random();
            int r = rnd.nextInt(256);
            int g = rnd.nextInt(256);
            int b = rnd.nextInt(256);
            int color = Color.rgb(r,g,b);
            MyFragment fragment = MyFragment.newInstance(color,scale);

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.container,fragment);
            transaction.replace(R.id.container,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            scale -= 0.1;
        }else if(v.getId() == R.id.remove_btn){
            MyFragment fragment = (MyFragment) getSupportFragmentManager().findFragmentByTag("MY_FRAG");
            if(fragment != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        }
    }

    @Override
    public void onClick(String str) {
        MyFragment fragment = (MyFragment) getSupportFragmentManager().findFragmentByTag("MY_FRAG");
        if(fragment!=null){
//            Toast.makeText(this, fragment.getInput(), Toast.LENGTH_SHORT).show();
            Bundle args = fragment.getArguments();
            if(args!=null){
                Toast.makeText(this, args.getString("INPUT"), Toast.LENGTH_SHORT).show();
            }
        }
        getSupportFragmentManager().popBackStack();
    }
}
