package in.ac.kcgcollege.kcgapplyleave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText signreg;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signreg=(EditText)findViewById(R.id.signup_reg);
        go=(Button)findViewById(R.id.go);

        go.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        if(v==go)
        {
            Intent i=new Intent(SignUp.this,SignUp2.class);
            i.putExtra("reg",signreg.getText().toString());
            startActivity(i);
        }
    }
}
