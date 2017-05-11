package qf.com.shoping.activity;

import android.os.Bundle;
import android.view.View;

import qf.com.shoping.R;

public class LoginActivity extends NoActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void back(View view){
        finish();
    }
}
