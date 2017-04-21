package com.example.srinivasan.database2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by SRINIVASAN on 3/26/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            EditText textdate = (EditText) getActivity().findViewById(R.id.date);
            textdate.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(monthOfYear) + "/" + String.valueOf(year)+"\n");
        }
}
