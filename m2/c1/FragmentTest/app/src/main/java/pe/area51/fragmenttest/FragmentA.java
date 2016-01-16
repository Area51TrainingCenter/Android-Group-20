package pe.area51.fragmenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentA extends Fragment {

    private FragmentAInterface fragmentAInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_a, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentAInterface != null) {
                    fragmentAInterface.onClick();
                }
            }
        });
        return view;
    }

    public void setFragmentAInterface(final FragmentAInterface fragmentAInterface) {
        this.fragmentAInterface = fragmentAInterface;
    }

    public interface FragmentAInterface {

        public void onClick();

    }
}
