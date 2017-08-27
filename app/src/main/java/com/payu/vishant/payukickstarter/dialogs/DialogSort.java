package com.payu.vishant.payukickstarter.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.payu.vishant.payukickstarter.R;

/**
 * Created by Vishant on 8/14/2017.
 */

public class DialogSort extends DialogFragment implements View.OnClickListener {

    private RadioButton rb_sort_alphabatically_asc;
    private RadioButton rb_sort_alphabatically_dsc;
    private RadioButton rb_sort_end_time_asc;
    private RadioButton rb_sort_end_time_dsc;
    DialogSortCallBack dialogSortCallBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.dialog_sort, null);
        rb_sort_alphabatically_asc = root_view.findViewById(R.id.rb_sort_alphabatically_asc);
        rb_sort_alphabatically_dsc = root_view.findViewById(R.id.rb_sort_alphabatically_dsc);
        rb_sort_end_time_asc = root_view.findViewById(R.id.rb_sort_end_time_asc);
        rb_sort_end_time_dsc = root_view.findViewById(R.id.rb_sort_end_time_dsc);

        rb_sort_alphabatically_asc.setOnClickListener(this);
        rb_sort_alphabatically_dsc.setOnClickListener(this);
        rb_sort_end_time_asc.setOnClickListener(this);
        rb_sort_end_time_dsc.setOnClickListener(this);

        dialogSortCallBack = (DialogSortCallBack) getContext();
        return root_view;
    }

    @Override
    public void onClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_sort_alphabatically_asc:
                if (checked) {
                    dialogSortCallBack.SortAlphabatically(true);
                }
                break;
            case R.id.rb_sort_alphabatically_dsc:
                if (checked) {
                    dialogSortCallBack.SortAlphabatically(false);
                }
                break;
            case R.id.rb_sort_end_time_dsc:
                if (checked) {
                    dialogSortCallBack.SortByEndTime(false);
                }
                break;
            case R.id.rb_sort_end_time_asc:
                if (checked) {
                    dialogSortCallBack.SortByEndTime(true);
                }
                break;

        }
        dismiss();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
         if (context instanceof DialogSortCallBack) {
            dialogSortCallBack = (DialogSortCallBack) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogSortCallBack = null;
    }

    public interface DialogSortCallBack {
        public void SortAlphabatically(boolean ASC);

        public void SortByEndTime(boolean ASC);
    }
}
