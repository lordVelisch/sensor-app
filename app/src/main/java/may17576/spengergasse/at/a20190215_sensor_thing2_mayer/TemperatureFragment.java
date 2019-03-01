package may17576.spengergasse.at.a20190215_sensor_thing2_mayer;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TemperatureFragment extends Fragment {

    private Thermometer thermometer;
    private float temperature = 30;
    private MainViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        thermometer = getActivity().findViewById(R.id.thermometer);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Observer<float[]> temperatureObserver = new Observer<float[]>() {
            @Override
            public void onChanged(float[] floats) {
                thermometer.setCurrentTemp(floats[0]);
            }
        };

        mViewModel.getTemperature().observe(this, temperatureObserver);
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
