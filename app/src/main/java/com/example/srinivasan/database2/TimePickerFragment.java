package com.example.srinivasan.database2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by SRINIVASAN on 4/17/2017.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
         int hour=c.get(Calendar.HOUR_OF_DAY);
        int min=c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,hour,min,true);
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        EditText texttime = (EditText) getActivity().findViewById(R.id.time);
        texttime.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute) +"\n");
    }
}
