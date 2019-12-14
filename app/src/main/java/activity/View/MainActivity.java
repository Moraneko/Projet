package activity.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vogella.android.projet.R;
import activity.Controler.MainActivityControler;

public class MainActivity extends AppCompatActivity {
    private MainActivityControler controler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.base);
        this.controler = new MainActivityControler(view);
    }
    public void Connect(View v) {
        controler.click();
    }

}