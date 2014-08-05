package com.google.mw.android.app;

import java.io.IOException;

import com.google.mw.backend.caseApi.CaseApi;
import com.google.mw.backend.caseApi.model.CaseBean;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


public class CaseUpdateTask extends AsyncTask<Void, Integer, Integer>{
	
	ProgressDialog pd = null;
	private final String TAG = getClass().getSimpleName();
	
	private Context mContext;
	private CaseApi mCaseApi;
	private CaseBean mBean;

	public void setContext(Context ctx) {
		mContext = ctx;
	}

	public void setService(CaseApi rgtr) {		
		mCaseApi = rgtr;
	}
	
	public void setData(CaseBean bean) {		
		mBean = bean;
	}

	protected void onPreExecute()
	{
		// Show progressDialog
		pd = new ProgressDialog(mContext);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setTitle(mContext.getResources().getString(R.string.registerTitle));
		pd.setMessage(mContext.getResources().getString(R.string.registerMessage));
		pd.setMax(100);
		pd.show();
	}

	@Override
	protected Integer doInBackground(Void... params) {

		Log.d(TAG, "doInBackground");

		publishProgress( 25 );

		/* Calls the Cloud Endpoint service */
		try {
			Log.d(TAG, mBean.toString());
			mCaseApi.updateCase(mBean).execute();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		publishProgress( 100 );
		//return lastStatusCode;
		return null;
	}

	@Override
	protected void onProgressUpdate(final Integer... progress) {
		pd.setProgress(progress[0]);
	}

	@Override
	protected void onPostExecute(Integer statusCode)
	{
		Log.d(TAG, "onPostExecute");
		pd.dismiss();	
	}

}
