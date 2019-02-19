package may17576.spengergasse.at.a20190215_sensor_thing2_mayer;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView lightView;
    private TextView gravity1;
    private TextView gravity2;
    private TextView gravity3;
    private TextView field1;
    private TextView field2;
    private TextView field3;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        lightView = getActivity().findViewById(R.id.light);
        gravity1 = getActivity().findViewById(R.id.gravityX);
        gravity2 = getActivity().findViewById(R.id.gravityY);
        gravity3 = getActivity().findViewById(R.id.gravityZ);
        field1 = getActivity().findViewById(R.id.fieldX);
        field2 = getActivity().findViewById(R.id.fieldY);
        field3 = getActivity().findViewById(R.id.fieldZ);

        Observer<Float> observer = new Observer<Float>() {
            @Override
            public void onChanged(@Nullable final Float newTemp) {
                lightView.setText(newTemp.toString());
            }
        };

        Observer<float[]> observerArray = new Observer<float[]>() {


            @Override
            public void onChanged(@Nullable final float[] floats) {
                String value0 = String.format("" + floats[0]).length() < 5 ? String.format("" + floats[0]) : String.format("" + floats[0]).substring(0, 5);
                String value1 = String.format("" + floats[1]).length() < 5 ? String.format("" + floats[1]) : String.format("" + floats[1]).substring(0, 5);
                String value2 = String.format("" + floats[2]).length() < 5 ? String.format("" + floats[2]) : String.format("" + floats[2]).substring(0, 5);

                gravity1.setText(value0);
                gravity2.setText(value1);
                gravity3.setText(value2);
            }
        };

        Observer<float[]> magneticObserver = new Observer<float[]>() {
            @Override
            public void onChanged(@Nullable final float[] floats) {
                String value0 = String.format("" + floats[0]).length() < 5 ? String.format("" + floats[0]) : String.format("" + floats[0]).substring(0, 5);
                String value1 = String.format("" + floats[1]).length() < 5 ? String.format("" + floats[1]) : String.format("" + floats[1]).substring(0, 5);
                String value2 = String.format("" + floats[2]).length() < 5 ? String.format("" + floats[2]) : String.format("" + floats[2]).substring(0, 5);

                field1.setText(value0);
                field2.setText(value1);
                field3.setText(value2);
            }
        };

        mViewModel.getGravity().observe(this, observerArray);
        mViewModel.getLight().observe(this, observer);
        mViewModel.getField().observe(this, magneticObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.pause();
    }
}

