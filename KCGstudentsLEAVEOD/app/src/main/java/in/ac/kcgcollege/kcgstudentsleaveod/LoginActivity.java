package in.ac.kcgcollege.kcgstudentsleaveod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView splash;
    TextView reg,dob;
    Button login;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        splash=(ImageView)findViewById(R.id.splash);
        reg=(TextView)findViewById(R.id.reg);
        dob=(TextView)findViewById(R.id.birthday);
        login=(Button)findViewById(R.id.login);
        rl=(RelativeLayout)findViewById(R.id.relay);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        Animation animation2= AnimationUtils.loadAnimation(this,R.anim.mytransition3);
        splash.startAnimation(animation);
       //thread to start splash screen and change login contents
       /* Thread timer=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                }
            }
        };
        timer.start();*/

        splash.setVisibility(View.INVISIBLE);
        rl.startAnimation(animation2);



        login.setOnClickListener(this);


    }

    public void onClick(View v)
    {
        if(v==login)
        {
            Intent i=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
        }
    }
}
