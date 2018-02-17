package in.ac.kcgcollege.kcgapplyleave;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;

public class Leave extends AppCompatActivity implements View.OnClickListener {
    EditText lfrom,lto,ldays,lreason;
    Button lapply;
    ImageButton ldfrom,ldto;
    RadioGroup lrg;
    RadioButton lrm,lrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        lfrom=(EditText)findViewById(R.id.leave_from);
        lto=(EditText)findViewById(R.id.leave_to);
        ldays=(EditText)findViewById(R.id.leave_days);
        lreason=(EditText)findViewById(R.id.leave_reason);

        ldfrom=(ImageButton)findViewById(R.id.leave_datepicker_from);
        ldto=(ImageButton) findViewById(R.id.leave_datepicker_to);

        lapply=(Button)findViewById(R.id.leave_apply);

        lrg=(RadioGroup)findViewById(R.id.leave_rg);
        lrm=(RadioButton)findViewById(R.id.leave_ml);
        lrc=(RadioButton)findViewById(R.id.leave_cl);

        ldfrom.setOnClickListener(this);
        ldto.setOnClickListener(this);
        lapply.setOnClickListener(this);
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

        }
    }
}
