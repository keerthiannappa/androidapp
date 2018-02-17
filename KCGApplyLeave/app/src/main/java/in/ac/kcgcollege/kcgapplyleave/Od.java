package in.ac.kcgcollege.kcgapplyleave;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

public class Od extends AppCompatActivity implements View.OnClickListener {

    EditText ofrom,oto,odays,oreason;
    Button oapply;
    ImageButton odfrom,odto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_od);

        ofrom=(EditText)findViewById(R.id.od_from);
        oto=(EditText)findViewById(R.id.od_to);
        odays=(EditText)findViewById(R.id.od_days);
        oreason=(EditText)findViewById(R.id.od_reason);

        odfrom=(ImageButton)findViewById(R.id.od_datepicker_from);
        odto=(ImageButton) findViewById(R.id.od_datepicker_to);

        oapply=(Button)findViewById(R.id.od_apply);

        odfrom.setOnClickListener(this);
        odto.setOnClickListener(this);
        oapply.setOnClickListener(this);
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

        }
    }
}
