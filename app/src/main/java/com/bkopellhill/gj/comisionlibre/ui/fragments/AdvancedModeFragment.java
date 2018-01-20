package com.bkopellhill.gj.comisionlibre.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.bitcodeing.framework.fragments.BaseFragment;
import com.bkopellhill.gj.comisionlibre.R;
import com.bkopellhill.gj.comisionlibre.core.utils.Calculate;
import com.bkopellhill.gj.comisionlibre.core.utils.MessageUtil;

/**
 * @author Luis Hernandez
 * @version 0.0.1
 */

public class AdvancedModeFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private EditText entry;
    private EditText entryPercentage;
    private EditText result;
    private EditText resultCommission;
    private EditText resultImport;
    private RadioGroup range;

    public static AdvancedModeFragment newInstance(){
        return new AdvancedModeFragment();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

        entry = (EditText) findViewById(R.id.entry);
        entryPercentage = (EditText) findViewById(R.id.entryPercentage);
        result = (EditText) findViewById(R.id.result);
        resultCommission = (EditText) findViewById(R.id.resultComission);
        resultImport = (EditText) findViewById(R.id.resultImport);

        range = (RadioGroup) findViewById(R.id.range);

        if (range != null){
            range.setOnCheckedChangeListener(this);
        }

        if (findViewById(R.id.calculate) != null)
            findViewById(R.id.calculate).setOnClickListener(this);

        if (findViewById(R.id.clean) != null)
            findViewById(R.id.clean).setOnClickListener(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_advance_mode;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (validate()){
            calculateResult();
        }
    }

    private void calculateResult(){
        Calculate calculate = Calculate.with(entry.getText().toString(),entryPercentage.getText().toString(),range.getCheckedRadioButtonId() == R.id.high);

        result.setText(calculate.getResultWithPercantageValue());
        resultCommission.setText(calculate.getResultComissionValue());
        resultImport.setText(calculate.getResultImportValue());
    }

    private void clean(){
        entry.getText().clear();
        entryPercentage.getText().clear();
        result.getText().clear();
        resultCommission.getText().clear();
        resultImport.getText().clear();
    }

    @Override
    public void onClick(View view) {
        getParent().hideKeyword();
        switch (view.getId()){
            case R.id.calculate:
                if (validate()){
                    calculateResult();
                }
                break;
            case R.id.clean:
                clean();
                break;
        }
    }

    private boolean validate() {
        if (entry == null || entry.getText() == null || entry.getText().toString().isEmpty()){
            MessageUtil.with(getParent()).show(R.string.error_general,R.string.error_entry,R.color.colorPrimary);
            return false;
        }

        if (entryPercentage == null || entryPercentage.getText() == null || entryPercentage.getText().toString().isEmpty()){
            MessageUtil.with(getParent()).show(R.string.error_general,R.string.error_percentage,R.color.colorPrimary);
            return false;
        }
        return true;
    }
}
