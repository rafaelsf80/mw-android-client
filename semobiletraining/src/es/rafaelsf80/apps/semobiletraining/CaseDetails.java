package es.rafaelsf80.apps.semobiletraining;

import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import caseApi.model.CaseBean;

import com.google.api.client.util.DateTime;

public class CaseDetails extends Activity {

	private final String TAG = getClass().getSimpleName();

	private CaseBean bean;

	TextView tvCaseTitle, tvCaseOwner, tvCreatedDate, tvComments;
	EditText etClosedDate;
	Button btUpdateCase;
	
	Handler mHandler;
	
	private DatePickerDialog mDatePicker;
	
	public interface ICaseDetails {
    	void updateClick(CaseBean bean);
    }

	private static ICaseDetails mICaseDetails;
	
	public static void regListener(ICaseDetails iTabConnect) {
    	mICaseDetails = iTabConnect;
    }
    
    public static void unregisterListener () {
		mICaseDetails = null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.case_details);
		Log.d(TAG, "onCreate()");

		Intent i = getIntent();
		bean = new CaseBean();
		bean.setTitle( i.getStringExtra("CASEBEAN_TITLE"));
		bean.setOwner( i.getStringExtra("CASEBEAN_OWNER"));
		bean.setDateCreated( new DateTime(i.getStringExtra("CASEBEAN_CREATED")) );
		bean.setDateClosed( new DateTime(i.getStringExtra("CASEBEAN_CLOSED")) );
		bean.setComments(i.getStringExtra("CASEBEAN_COMMENTS"));

		tvCaseTitle = (TextView) findViewById(R.id.tv_case_title);
		tvCaseOwner = (TextView) findViewById(R.id.tv_owner);
		tvCreatedDate = (TextView) findViewById(R.id.tv_created_date);
		etClosedDate = (EditText) findViewById(R.id.tv_closed_date);
		tvComments = (TextView) findViewById(R.id.tv_comments);

		btUpdateCase = (Button) findViewById(R.id.bt_update_case);

		tvCaseTitle.setText(bean.getTitle());
		tvCaseOwner.setText(bean.getOwner());

		// Parse Date and Time to show it in a more human readable format
		tvCreatedDate.setText(parseDateTime(bean.getDateCreated().toStringRfc3339()));
		etClosedDate.setText(parseDateTime(bean.getDateClosed().toStringRfc3339()));

		// Case comments
		tvComments.setText(bean.getComments());

		mHandler = new Handler(new Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				
				DateTime closedDate = (DateTime) msg.obj;
				etClosedDate.setText(parseDateTime(closedDate.toStringRfc3339()));
				bean.setDateClosed( closedDate );
				return true;
			}
		});

		etClosedDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Show DatePickerDialog
				DateTimePickerDialog dateTimePickerDialog = 
						new DateTimePickerDialog(CaseDetails.this, mHandler, bean);
				dateTimePickerDialog.show();
			}
		});

		btUpdateCase.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mICaseDetails != null) 		
					mICaseDetails.updateClick(bean);
				// Close this activity
				finish();
			}
		});		
	}

	private String parseDateTime(String date) {
		
		// Parsing 2014-05-31T08:51:32.590+02:00 into 2014-05-31 08:51
		// Removes the middle "T, seconds and timezone

		String parsedDate = null;		
		parsedDate = date.replace("T", " ").substring(0, 16);
		return parsedDate;
	}
}
