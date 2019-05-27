package com.ignacio.lectorrss.view;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ignacio.lectorrss.R;
import com.ignacio.lectorrss.Threads.ReaderRss;
import com.ignacio.lectorrss.models.News;
import com.ignacio.lectorrss.view.fragments.HomeFragment;
import com.ignacio.lectorrss.view.interfaces.DataComunication;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class MainActivity extends AppCompatActivity implements DataComunication {

    private ACProgressFlower dialog;
    private News newSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchContent(new HomeFragment(), false);

    }



    public void switchContent(Fragment fragment, boolean hasToAddToStack) {

        try {
            if (fragment != null && !fragment.isDetached()) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();



                if (hasToAddToStack)
                    transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out);
                transaction.replace(R.id.container, fragment);
                if (hasToAddToStack) transaction.addToBackStack("back");
                else {
                    FragmentManager fm = getSupportFragmentManager();
                    for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                        fm.popBackStack();
                    }
                }
                transaction.commit();

            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void showProgress() {
        if (dialog == null)
        {
            dialog = new ACProgressFlower.Builder(MainActivity.this).direction(ACProgressConstant.DIRECT_CLOCKWISE).themeColor(Color.WHITE).fadeColor(Color.DKGRAY).build();
            dialog.show();
        }
    }

    public void dismissProgress() {
        if (dialog != null && dialog.isShowing())
        {
            dialog.dismiss();
            dialog = null;
        }
    }


    @Override
    public News getNewsSelected() {
        return this.newSelected;
    }

    @Override
    public void setNewsSelected(News news) {
        this.newSelected = news;
    }

    protected OnBackPressedListener onBackPressedListener;

    public interface OnBackPressedListener {
        void doBack();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener.doBack();
        else
            super.onBackPressed();
    }
}
