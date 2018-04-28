package cn.com.agree.androidjsc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExampleContext ctx = new ExampleContext(
                (TextView) findViewById(R.id.textview));

        new OwenMatthewsExample(ctx).run();
    }
}