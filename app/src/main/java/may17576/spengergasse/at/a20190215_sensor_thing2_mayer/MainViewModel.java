package may17576.spengergasse.at.a20190215_sensor_thing2_mayer;

import android.app.Application;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import static android.content.Context.SENSOR_SERVICE;

public class MainViewModel extends AndroidViewModel implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private Sensor gravitySensor;
    private Sensor magneticfieldSensor;
    private Sensor rotationVectorSensor;
    private float[] vectors;


    private MutableLiveData<Float> light;
    private MutableLiveData<float[]> gravity;
    private MutableLiveData<float[]> field;
    private MutableLiveData<float[]> orientationVector;


    public MainViewModel(@NonNull Application application) {
        super(application);
        sensorManager = (SensorManager) application.getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        magneticfieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);


        light = new MutableLiveData<>();
        gravity = new MutableLiveData<>();
        field = new MutableLiveData<>();
        orientationVector = new MutableLiveData<>();
        vectors = new float[3];
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == lightSensor) {
            light.setValue(event.values[0]);
        } else if (event.sensor == gravitySensor) {
            gravity.setValue(event.values);
        } else if (event.sensor == magneticfieldSensor) {
            field.setValue(event.values);
        } else if (event.sensor == rotationVectorSensor) {
            SensorManager.getRotationMatrixFromVector(vectors, event.values);
            orientationVector.setValue(vectors);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    public MutableLiveData<Float> getLight() {
        return light;
    }

    public MutableLiveData<float[]> getOrientationVector() {
        return orientationVector;
    }

    public MutableLiveData<float[]> getGravity() {
        return gravity;
    }

    public MutableLiveData<float[]> getField() {
        return field;
    }

    public void resume() {
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magneticfieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void pause() {
        sensorManager.unregisterListener(this);
        sensorManager.unregisterListener(this);
        sensorManager.unregisterListener(this);
        sensorManager.unregisterListener(this);
    }
}
