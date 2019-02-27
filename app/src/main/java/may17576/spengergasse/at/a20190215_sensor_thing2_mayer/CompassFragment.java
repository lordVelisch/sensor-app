package may17576.spengergasse.at.a20190215_sensor_thing2_mayer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class CompassFragment extends Fragment {

    private MainViewModel mainViewModel;
    private ImageView compass;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compass, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        compass = getActivity().findViewById(R.id.compass);

        Observer<float[]> orientationObserver = new Observer<float[]>() {
            @Override
            public void onChanged(@Nullable final float[] floats) {
                String zValue = String.format("" + floats[0]);


            }
        };

        mainViewModel.getOrientationVector().observe(this, orientationObserver);
    }


}
