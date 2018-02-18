package in.ac.kcgcollege.kcgapplyleave;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Od extends AppCompatActivity implements View.OnClickListener {

    EditText ofrom,oto,odays,oreason;
    TextView oack;
    Button oapply;
    ImageButton odfrom,odto;
    String osf,ost,osd,osr,odb_reg;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference("Applications");
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_od);

        Intent i=getIntent();
        odb_reg=i.getStringExtra("ref");

        ofrom=(EditText)findViewById(R.id.od_from);
        oto=(EditText)findViewById(R.id.od_to);
        odays=(EditText)findViewById(R.id.od_days);
        oreason=(EditText)findViewById(R.id.od_reason);
        oack=(TextView)findViewById(R.id.od_ack);

        user= FirebaseAuth.getInstance().getCurrentUser();

        odfrom=(ImageButton)findViewById(R.id.od_datepicker_from);
        odto=(ImageButton) findViewById(R.id.od_datepicker_to);

        oapply=(Button)findViewById(R.id.od_apply);


        odfrom.setOnClickListener(this);
        odto.setOnClickListener(this);
        oapply.setOnClickListener(this);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("OD").hasChild(odb_reg)){
                    String a=dataSnapshot.child("OD").child(odb_reg).child("status").getValue(String.class);
                    if(Integer.parseInt(a)<2){
                        oack.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void onClick(View v)
    {
        if(v==odfrom){
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                           ofrom.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if(v==odto){
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            oto.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if(v==oapply){
            osf=ofrom.getText().toString();
            ost=oto.getText().toString();
            osr=oreason.getText().toString();
            osd=odays.getText().toString();

            mRef.child("OD").child(odb_reg).child("from").setValue(osf);
            mRef.child("OD").child(odb_reg).child("to").setValue(ost);
            mRef.child("OD").child(odb_reg).child("days").setValue(osd);
            mRef.child("OD").child(odb_reg).child("reason").setValue(osr);
            mRef.child("OD").child(odb_reg).child("status").setValue("0");

            startActivity(new Intent(Od.this,MainActivity.class));

        }
    }
}
