package es.rafaelsf80.apps.semobiletraining;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import caseApi.model.CaseBean;

public class ListEntryAdapter extends BaseAdapter {

	private Activity activity;
	private List<CaseBean> data;
	private static LayoutInflater inflater=null;
	//public ImageLoader imageLoader; 
	
	public interface IAdapter {
    	void itemClick(CaseBean bean);
    }

	private static IAdapter mIAdapter;
	
	public static void regListener(IAdapter iTabConnect) {
    	mIAdapter = iTabConnect;
    }
    
    public static void unregisterListener () {
		mIAdapter = null;
	}
	  
	public ListEntryAdapter(Activity a, List<CaseBean> d) {
		activity = a;
		data=d;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//imageLoader=new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return Main.array.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View vi=convertView;
		final int pos = position;

		if(convertView==null)
			vi = inflater.inflate(R.layout.list_row, null);

		TextView tvCaseTitle = (TextView)vi.findViewById(R.id.tv_list_case_title); // title
		ImageView thumb_image=(ImageView)vi.findViewById(R.id.icon_image); // thumb image
		TextView tvCreatedDate = (TextView)vi.findViewById(R.id.tv_list_created_date); // job name
		
		
		tvCaseTitle.setText(Main.array.get(position).getTitle());	
		tvCreatedDate.setText( parseDateTime( Main.array.get(position).getDateCreated().toStringRfc3339() ));

		vi.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				if (mIAdapter != null) 		
					mIAdapter.itemClick((CaseBean) Main.array.get(pos));		
			}
		});


		// Setting all values in listview
		//nameExpert.setText(expert.get(ResultsListActivity.NAME_EXPERT));
		
		//get ImageURL in good dimension
//		String image_URL = expert.get(ResultsListActivity.THUMB_URL);
//		int start_sub=expert.get(ResultsListActivity.THUMB_URL).indexOf("sz=50");
//		if(start_sub>0)
//		{
//			image_URL = expert.get(ResultsListActivity.THUMB_URL).substring(0, start_sub);
//			image_URL = image_URL.concat("sz=100");
//		}
//			
//		imageLoader.DisplayImage(image_URL, thumb_image);
//		
//		System.out.println("jobTitle list " + expert.get(ResultsListActivity.JOB_TITLE));
//		jobTitle.setText(expert.get(ResultsListActivity.JOB_TITLE));

		return vi;
	}
	
	private String parseDateTime(String date) {
		
		// Parsing 2014-05-31T08:51:32.590+02:00 into 2014-05-31 08:51
		// Removes the middle "T, seconds and timezone

		String parsedDate = null;		
		parsedDate = date.replace("T", " ").substring(0, 16);
		return parsedDate;
	}	
}