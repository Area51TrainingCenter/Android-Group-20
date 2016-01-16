package pe.area51.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentA fragmentA = new FragmentA();
        fragmentA.setFragmentAInterface(new FragmentA.FragmentAInterface() {
            @Override
            public void onClick() {
                showFragment(new FragmentB());
            }
        });
        if (savedInstanceState == null) {
            showFragment(fragmentA);
        }
    }

    private void showFragment(final Fragment fragment) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment);
        if (fragmentManager.getFragments() != null) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

}
