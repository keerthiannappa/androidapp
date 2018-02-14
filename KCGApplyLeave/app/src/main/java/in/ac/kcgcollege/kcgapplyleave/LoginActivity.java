package in.ac.kcgcollege.kcgapplyleave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    ImageView splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        splash=(ImageView)findViewById(R.id.imageView);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.transition);
        splash.startAnimation(animation);
        Thread timer=new Thread(){
            public void run()
            {
                try{
                    sleep(6000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent i=new Intent(LoginActivity.this,LoginActivity2.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
    @Override
    public void onBackPressed()
    {

        // super.onBackPressed(); // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }
}
