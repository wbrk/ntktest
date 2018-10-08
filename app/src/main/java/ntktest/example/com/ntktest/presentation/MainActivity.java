package ntktest.example.com.ntktest.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import ntktest.example.com.ntktest.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationUI.setupActionBarWithNavController(this, getNavController());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean rv = NavigationUI.onNavDestinationSelected(item, getNavController());
        return rv || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return getNavController().navigateUp();
    }

    private NavController getNavController() {
        return Navigation.findNavController(this, R.id.nav_host);
    }
}
