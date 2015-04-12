package app.privatebox.com.privatebox;

/**
 * Created by Yusri on 4/10/2015.
 */
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar.Tab;

import android.app.FragmentTransaction;

public class TabListener implements android.support.v7.app.ActionBar.TabListener {

    private Fragment fragment;

    // The contructor.
    public TabListener(Fragment fragment) {
        this.fragment = fragment;
    }

    // When a tab is tapped, the FragmentTransaction replaces
    // the content of our main layout with the specified fragment;
    // that's why we declared an id for the main layout.


    @Override
    public void onTabSelected(Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        //fragmentTransaction.replace(R.id.contentFrame, fragment);
    }

    @Override
    public void onTabUnselected(Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        fragmentTransaction.remove(fragment);
    }

    @Override
    public void onTabReselected(Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }
}