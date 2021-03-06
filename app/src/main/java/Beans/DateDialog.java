package Beans;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Savinda Keshan on 10/26/2016.
 */
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    EditText date;

    public DateDialog(View view){
        date = (EditText)view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year= c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int i, int i1, int i2) {
        String dateformat = i2+"-"+(i1+1)+"-"+i;
        date.setText(dateformat);
    }

}
