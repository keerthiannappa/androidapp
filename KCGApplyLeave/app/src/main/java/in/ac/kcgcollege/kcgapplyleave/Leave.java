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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Leave extends AppCompatActivity implements View.OnClickListener {
    EditText lfrom,lto,ldays,lreason;
    Button lapply;
    ImageButton ldfrom,ldto;
    RadioGroup lrg;
    TextView lack;
    RadioButton lrm,lrc;
    String lsf,lst,lsd,lsr,lsty,ldb_reg,ldb_name;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference("Applications");
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        user= FirebaseAuth.getInstance().getCurrentUser();

        Intent i=getIntent();
        ldb_reg=i.getStringExtra("ref");

        lfrom=(EditText)findViewById(R.id.leave_from);
        lto=(EditText)findViewById(R.id.leave_to);
        ldays=(EditText)findViewById(R.id.leave_days);
        lreason=(EditText)findViewById(R.id.leave_reason);
        lack=(TextView) findViewById(R.id.leave_ack);

        ldfrom=(ImageButton)findViewById(R.id.leave_datepicker_from);
        ldto=(ImageButton) findViewById(R.id.leave_datepicker_to);

        lapply=(Button)findViewById(R.id.leave_apply);

        lrg=(RadioGroup)findViewById(R.id.leave_rg);
        lrm=(RadioButton)findViewById(R.id.leave_ml);
        lrc=(RadioButton)findViewById(R.id.leave_cl);

        ldfrom.setOnClickListener(this);
        ldto.setOnClickListener(this);
        lapply.setOnClickListener(this);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Leave").hasChild(ldb_reg)){
                    String a=dataSnapshot.child("Leave").child(ldb_reg).child("status").getValue(String.class);
                    if(Integer.parseInt(a)<2 ){
                        lack.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(lrm.isChecked()){
                    lsty=lrm.getText().toString();
                }
                if(lrc.isChecked()){
                    lsty=lrc.getText().toString();
                }
            }
        });
    }

    public void onClick(View v)
    {
        if(v==ldfrom){
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            lfrom.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if(v==ldto){
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            lto.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if(v==lapply){
            lsf=lfrom.getText().toString();
            lst=lto.getText().toString();
            lsr=lreason.getText().toString();
            lsd=ldays.getText().toString();

            mRef.child("Leave").child(ldb_reg).child("from").setValue(lsf);
            mRef.child("Leave").child(ldb_reg).child("to").setValue(lst);
            mRef.child("Leave").child(ldb_reg).child("days").setValue(lsd);
            mRef.child("Leave").child(ldb_reg).child("type").setValue(lsty);
            mRef.child("Leave").child(ldb_reg).child("reason").setValue(lsr);
            mRef.child("Leave").child(ldb_reg).child("status").setValue("0");

            startActivity(new Intent(Leave.this,MainActivity.class));
        }
    }
}
