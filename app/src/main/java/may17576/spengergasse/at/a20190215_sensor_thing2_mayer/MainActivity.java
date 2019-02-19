package may17576.spengergasse.at.a20190215_sensor_thing2_mayer;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Fragment textFragment = new MainFragment();
    private Fragment compassFragment = new CompassFragment();
    private Fragment temperatureFragment = new TemperatureFragment();
    private Fragment activeFragment = textFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    activeFragment = textFragment;
                    getSupportActionBar().setTitle(R.string.title_home);
                    break;
                case R.id.navigation_compass:
                    activeFragment = compassFragment;
                    getSupportActionBar().setTitle(R.string.title_compass);
                    break;
                case R.id.navigation_temperature:
                    activeFragment = temperatureFragment;
                    getSupportActionBar().setTitle(R.string.title_temperature);
                    break;
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, activeFragment);
            transaction.commit();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.title_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, activeFragment)
                .commit();
    }

}
