package com.itbarx.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itbarx.R;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.utils.TextSizeUtil;

/**
 * TODO: Add a class header comment!
 */
public class F_RecordFragment extends Fragment {

	private T_RecordActivity t_recordActivity;
	private TextViewRegular txtToolbarRecord;
	private TextViewBold txtStartRecord;


	public F_RecordFragment() {
	}

	public F_RecordFragment(T_RecordActivity t_recordActivity) {

		this.t_recordActivity = t_recordActivity;
	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_record_secreen, container, false);
	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		txtToolbarRecord = (TextViewRegular) t_recordActivity.findViewById(R.id.record_toolbar_text);
		txtStartRecord = (TextViewBold) t_recordActivity.findViewById(R.id.record_fragment_start_textView);
		setTextSize();
	}

	private void setTextSize() {
		txtToolbarRecord.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		txtStartRecord.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getRecordStartTextSize());

	}
}
