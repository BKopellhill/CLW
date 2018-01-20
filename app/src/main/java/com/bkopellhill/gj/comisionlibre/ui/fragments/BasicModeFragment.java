package com.bkopellhill.gj.comisionlibre.ui.fragments;

import android.os.Bundle;
import android.view.View;
import com.bitcodeing.framework.fragments.BaseFragment;
import com.bitcodeing.framework.views.EditText;
import com.bkopellhill.gj.comisionlibre.R;
import com.bkopellhill.gj.comisionlibre.core.utils.Calculate;
import com.bkopellhill.gj.comisionlibre.core.utils.MessageUtil;

/**
 * @author Luis Hernandez
 * @version 0.0.1
 */

@SuppressWarnings("FieldCanBeLocal")
public class BasicModeFragment extends BaseFragment implements View.OnClickListener {

    private EditText entry;
    private EditText result;

    public static BasicModeFragment newInstance(){
        return new BasicModeFragment();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

        entry = (EditText) findViewById(R.id.entry);
        result = (EditText) findViewById(R.id.result);

        if (findViewById(R.id.calculate) != null)
            findViewById(R.id.calculate).setOnClickListener(this);

        if (findViewById(R.id.clean) != null)
            findViewById(R.id.clean).setOnClickListener(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_basic_mode;
    }

    public void calculateResult(){
        if (entry == null || entry.getText() == null || entry.getText().toString().isEmpty()){
            MessageUtil.with(getParent()).show(R.string.error_general,R.string.error_entry,R.color.colorPrimary);
        }else {
            result.setText(getValue());
        }
    }

    private String getValue(){
        return Calculate.with(entry.getText().toString()).getResultValue();
    }

    private void clean(){
        entry.getText().clear();
        result.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.calculate:
                getParent().hideKeyword();
                calculateResult();
                break;
            case R.id.clean:
                getParent().hideKeyword();
                clean();
                break;
        }
    }
}
