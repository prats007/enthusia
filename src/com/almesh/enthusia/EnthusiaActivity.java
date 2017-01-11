package com.almesh.enthusia;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static com.almesh.enthusia.CommonUtilities.SENDER_ID;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.google.android.gcm.GCMRegistrar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;



import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;

import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.provider.Settings.Secure;

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import 	android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Gravity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class EnthusiaActivity extends Activity {
	
	static String p1s[]=new String[200];
	static String winners[]=new String[200];
	static int p1count=0;
	static String p2s[]=new String[200];
	static int p2count=0;
	static String dates[]=new String[200];
	static int datecount=0;
	static String titles[]=new String[200];
	static int titlecount=0;
	static String contents[]=new String[200];
	static int contentcount=0;
	static int mcount=0,mycount=0;
	static String mybranches[]=new String[200];
	static String mysports[]=new String[200];
	static String golds[]=new String[20];
	static String branches[]=new String[20];
	static String silvers[]=new String[20];
	static String bronzes[]=new String[20];
	static String urls[]=new String[200];
	static int urlcount=0;
	static String url=null;
	static String times[]=new String[200];
	static int timecount=0;
	static int flag=0;
	static int upcoming_flag=0;
	static ImageView iv=null;
	ViewFlipper flipper;
	static int j=0,i=0,k=0,a=0,a1=0,p=0;
	static int mProgressStatus = 0,c=0;
	GridView gridView;
    static String winner1,winner2,win;
	static float x1,x2,y1,y2;
	static Commentsdatasource datasource;
	CommentsdatasourceRSS datasourcerss;
	Commentsdatasourcemedals datasourcemedals;
	Commentsdatasourcemyteams datasourcemyteams;
	public final static String AUTH = "authentication";
	String[] sportname={null,"Basketball","Football","Cricket","Table Tennis","Volleyball","Tennis","Badminton","Chess","Carrom","Throwball","Athletics","Swimming","Kabaddi","Box Cricket","Kho Kho","Rink Football","Dodgeball"};
	String[] teamname={"Basketball","Football","Cricket","Table Tennis","Volleyball","Tennis","Badminton","Chess","Carrom","Throwball","Athletics","Swimming","Kabaddi","Box Cricket","Kho Kho","Rink Football","Dodgeball"};
	String[] rssname={null,"Cricket","Football","Tennis","Basketball","Golf","F1"};
	static String[] branchename={"IT","EXTC","Comps","Tronix","Trical","Mech","Prod","Textile","Civil","Chemsa","Masters"};
	String currentpage=null;
	static String mybranch,mysport;
	static String thispage=null;
	DisplayMetrics metrics = new DisplayMetrics(); 
	HorizontalScrollView hoscl;
	Boolean result=false;
	ExpandableListView elv;
	
	// Example Activity to trigger a request for a registration ID to the Google
	// server
	
	 private GestureDetector gestureScanner;
	 private Animation inFromRightAnimation() {

		 Animation inFromRight = new TranslateAnimation(
		 Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
		 Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
		 );
		 inFromRight.setDuration(500);
		 inFromRight.setInterpolator(new AccelerateInterpolator());
		 return inFromRight;
		 }
		 private Animation outToLeftAnimation() {
		 Animation outtoLeft = new TranslateAnimation(
		   Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  -1.0f,
		   Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
		 );
		 outtoLeft.setDuration(500);
		 outtoLeft.setInterpolator(new AccelerateInterpolator());
		 return outtoLeft;
		 }

		 private Animation inFromLeftAnimation() {
		 Animation inFromLeft = new TranslateAnimation(
		 Animation.RELATIVE_TO_PARENT,  -1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
		 Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
		 );
		 inFromLeft.setDuration(500);
		 inFromLeft.setInterpolator(new AccelerateInterpolator());
		 return inFromLeft;
		 }
		 private Animation outToRightAnimation() {
		 Animation outtoRight = new TranslateAnimation(
		   Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  +1.0f,
		   Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
		 );
		 outtoRight.setDuration(500);
		 outtoRight.setInterpolator(new AccelerateInterpolator());
		 return outtoRight;
		 }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launchscreen);
		LinearLayout ln = (LinearLayout) findViewById(R.id.launch);
		GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        final String regId = GCMRegistrar.getRegistrationId(this);
        if (regId.equals("")) {
          GCMRegistrar.register(this, SENDER_ID);
          Log.d("regidinoncreate", regId);
          //sendRegistrationIdToServer(regId);
        } else {
          Log.v("GCM","Already registered");
        }
        Random randomGenerator = new Random();
        
        switch(randomGenerator.nextInt(5))
        {
         
        case 0:
     	   ln.setBackgroundDrawable(getResources().getDrawable(R.drawable.mainpage));
     	   break;
        case 1:
     	   ln.setBackgroundDrawable(getResources().getDrawable(R.drawable.mainpage2));
     	   break;
        case 2:
     	   ln.setBackgroundDrawable(getResources().getDrawable(R.drawable.mainpage3));
     	   break;
        case 3:
     	   ln.setBackgroundDrawable(getResources().getDrawable(R.drawable.mainpage4));
     	   break;
        case 4:
      	   ln.setBackgroundDrawable(getResources().getDrawable(R.drawable.mainpage5));
      	   break;
        }
		 new AsyncLoadXMLFeed().execute();
		
		  gestureScanner = new GestureDetector(this,simpleOnGestureListener);
		  currentpage="home";
	}
	
	 private class AsyncLoadXMLFeed extends AsyncTask<Void, Void, Void>{
	      @Override
	      protected void onPreExecute(){
	            // show your progress dialog

	      }
 
	    

	      @Override
	      protected void onPostExecute(Void params){
	            // dismiss your dialog
	            // launch your News activity
	            setContentView(R.layout.index);
	            flipper = (ViewFlipper) findViewById(R.id.flipper);
	            Log.d("c2dm","Starting");
	            //	register();
	            Log.d("c2dm", "Finished");
	           /**/
	            

	            // close this activity
	            
	      }

		@Override
		protected Void doInBackground(Void... arg0) {
			long t0,t1;
	        t0=System.currentTimeMillis();
	        do{
	            t1=System.currentTimeMillis();
	        }
	        while (t1-t0<3000);
			return null;
		}

	   }
	 
	 
	 
	 private class progressmyteams extends AsyncTask<Void, Void, Void> 
     {
         private ProgressDialog Dialog = new ProgressDialog(EnthusiaActivity.this);
         @Override
         protected void onPreExecute()
         {
         Dialog.setMessage("Updating Matches...");
         Dialog.show();
     }

     @Override
     protected Void doInBackground(Void... arg0) 
     {	
    	 
    	 Log.d("progress", "connecting");
    	 try{
    	 connect();
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    	 Log.d("progress", "connected");
 
         return null;
     }

     @Override
     protected void onPostExecute(Void params)
         {


 // after completed finished the progressbar
         Dialog.dismiss();
         show_list();
     }

	
     }
	 
	 private class progressenthu extends AsyncTask<Void, Void, Void> 
     {
         private ProgressDialog Dialog = new ProgressDialog(EnthusiaActivity.this);
         @Override
         protected void onPreExecute()
         {
         Dialog.setMessage("Updating Matches...");
         Dialog.show();
     }

     @Override
     protected Void doInBackground(Void... arg0) 
     {	
    	 
    	 Log.d("progress", "connecting");
    	 try{
    	 connect();
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    	 Log.d("progress", "connected");
 
         return null;
     }

     @Override
     protected void onPostExecute(Void params)
         {


 // after completed finished the progressbar
         Dialog.dismiss();
         //displayrss2(url);
     }

	
     }
	 	 /** Inner class for implementing progress bar before fetching data **/
     private class progress extends AsyncTask<String, Void, String> 
     {
         private ProgressDialog Dialog = new ProgressDialog(EnthusiaActivity.this);
         @Override
         protected void onPreExecute()
         {
         Dialog.setMessage("Updating Matches...");
         Dialog.show();
     }

     @Override
     protected String doInBackground(String... url) 
     {	
    	 
    	 Log.d("progress", "before call "+ url[0]);
    	 try{
    	 call(url[0]);
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    	 Log.d("progress", "after call");
 
         return url[0];
     }

     @Override
     protected void onPostExecute(String url)
         {


 // after completed finished the progressbar
         Dialog.dismiss();
         displayrss2(url);
     }

	
     }
     
     private class medalsprogress extends AsyncTask<Void, Void, Void> 
     {
         private ProgressDialog Dialog = new ProgressDialog(EnthusiaActivity.this);
         @Override
         protected void onPreExecute()
         {
         Dialog.setMessage("Obtaining Medal List...");
         Dialog.show();
     }

     @Override
     protected Void doInBackground(Void... arg0) 
     {	
    	 
    	 
    	 try{
    	 get_medals();
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    	 Log.d("progress", "after call");
		return null ;
 
         
     }

     @Override
     protected void onPostExecute(Void params)
         {


 // after completed finished the progressbar
         Dialog.dismiss();
         displaymedals2();
     }

	

	
     }
     private class enthunewsprogress extends AsyncTask<Void, Void, Void> 
     {
         private ProgressDialog Dialog = new ProgressDialog(EnthusiaActivity.this);
         @Override
         protected void onPreExecute()
         {
         Dialog.setMessage("Updating News...");
         Dialog.show();
     }

     @Override
     protected Void doInBackground(Void... arg0) 
     {	
    	 
    	 
    	 try{
    		 get_enthu_news();
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    	 Log.d("progress", "after call");
		return null ;
 
         
     }

     @Override
     protected void onPostExecute(Void params)
         {


 // after completed finished the progressbar
         Dialog.dismiss();
        show_enthu_news();
     }

	

	
     }
	

	
	 @Override
	    public void onBackPressed()
	    {
		 
		 
		if(currentpage==null)
		 	{
			 i=k=0;
			
			 flipper.setInAnimation(inFromLeftAnimation());
	         flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.zero)));   
			 currentpage="home";
		 	}
		 else if(currentpage.equals("home"))
		 {
			i=k=0;			
			finish();
		 }
		 else if(currentpage.equals("committee")||currentpage.equals("contactus")||currentpage.equals("halftime"))
		 {
			 flipper.setInAnimation(inFromLeftAnimation());
	         flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.five)));  
			 currentpage=null;
		 }
		 else if(currentpage.equals("attr"))
		 {
			 a=0;
			 flipper.setInAnimation(inFromLeftAnimation());
	         flipper.setOutAnimation(outToRightAnimation());
	         flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four)));  
			 currentpage=null;
		 }
		 else if(currentpage.equals("develop"))
		 {
			 flipper.setInAnimation(inFromLeftAnimation());
	         flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.five)));  
			 currentpage=null;
		 }
		 else if(currentpage.equals("enthu_options"))
		 {
			 flipper.setInAnimation(inFromLeftAnimation());
	         flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one)));  
			 currentpage=null;
		 }
		 else if(currentpage.equals("myteams"))
		 {
			 flipper.setInAnimation(inFromLeftAnimation());
	         flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_three)));  
			 currentpage="enthu_options";
			 show_list();
		 }
		else
		{
			i=k=0;
			 flipper.setInAnimation(inFromLeftAnimation());
	         flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_two)));  
			currentpage="enthu_options";
		}
	    
	    }
	

	
    public void initialise()
    {	
    	mcount=0;
    	titlecount=0;
    	flag=0;
    	p1count=0;
    	p2count=0;
		datecount=0;
		timecount=0;
    }
	public void register() {
		Log.d("c2dm","inregister");
		
		Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
		intent.putExtra("app",PendingIntent.getBroadcast(this, 0, new Intent(), 0));
		// Sender currently not used
		intent.putExtra("sender", "rondroid1992@gmail.com");
		Log.d("c2dm","startingservice");
		startService(intent);
	}

	public void showRegistrationId(View view) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		String string = prefs.getString(AUTH, "n/a");
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
							
	}
	public void show(String sport)
	{
	//datasource = new Commentsdatasource(this);
		//datasource.view(sport);
		//datasource.view_upcoming(sport);
	}
	public void show_enthu_news()
	{
		LinearLayout ll=(LinearLayout) findViewById(R.id.newsll);
    	if(((LinearLayout) ll).getChildCount() > 0) 
    	    ((LinearLayout) ll).removeAllViews(); 
    	
    	TextView head = new TextView(this);
		head.setText("Enthusia Updates");
		head.setTextColor(Color.WHITE);
		head.setGravity(Gravity.CENTER);
		head.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
		ll.addView(head);
		
		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
	    String i = sharedPreferences.getString("i", "");
	    Log.d("i",i);
	    for(int j=0;j<Integer.parseInt(i);j++)
	    {
		TextView line1=new TextView(this);
		line1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,1);
		line1.setBackgroundColor(Color.parseColor("#FFFFFF"));
		ll.addView(line1);
		TextView tv = new TextView(this);
		tv.setText(sharedPreferences.getString(""+j, ""));
		tv.setTextColor(Color.WHITE);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
		tv.setBackgroundColor(Color.parseColor("#483D6B"));
		ll.addView(tv);
		
		TextView line2=new TextView(this);
		line2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,1);
		line2.setBackgroundColor(Color.parseColor("#FFFFFF"));
		ll.addView(line2);
		TextView tv1 = new TextView(this);
		tv1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10);
		tv1.setText(" ");
		ll.addView(tv1);
	    }
	}
	public void get_enthu_news()
	{
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		Log.d("news", "hmm");
		 
		//http post
		try{	
		
		        HttpClient httpclient = new DefaultHttpClient();
		        
		        HttpPost httppost = new HttpPost("http://rondroid.uphero.com/news.html");
		        Log.d("medals", "hmm4");
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost);
		        Log.d("medals", "hmm5");
		        HttpEntity entity = response.getEntity();
		        InputStream is = entity.getContent();
		        
		//convert response to string
		
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		       // StringBuilder sb = new StringBuilder();
		        String news = null;
		       reader.mark(10000);
		       Log.d("medals", "hmm6");
		        while(!reader.readLine().equals("<body>"))
		        {}
		        Log.d("medals", "hmm7");
		        
        		int i=0;
        		
        		
        		
		        while ((news = reader.readLine()) != null) {
		        	
		        	if(news.equals("</body>"))
		        		break;
		        	      
		        		/*TextView line1=new TextView(this);
		        		line1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,1);
	        			line1.setBackgroundColor(Color.parseColor("#FFFFFF"));
	        			ll.addView(line1);
		        		TextView tv = new TextView(this);
		        		tv.setText(news.subSequence(0,news.lastIndexOf("<br>")));
		        		tv.setTextColor(Color.WHITE);
		        		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
		        		tv.setBackgroundColor(Color.parseColor("#483D6B"));
		        		ll.addView(tv);
		        		TextView tv1 = new TextView(this);
		        		tv1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10);
		        		tv1.setText(" ");
		        		ll.addView(tv1);
		        		*/
		        	SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		            SharedPreferences.Editor editor = sharedPreferences.edit();
		            editor.putString(""+i,news.substring(0,news.lastIndexOf("<br>")));
		            editor.commit();
		        	i++;		
		        	
		        }
		        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
	            SharedPreferences.Editor editor = sharedPreferences.edit();
	            editor.putString("i",""+i);
	            editor.commit();
		   
		     		       
		}catch(Exception e){
		}
	}
	public void get_medals()
	
	{
		datasourcemedals = new Commentsdatasourcemedals(this);
		datasourcemedals.open();
		datasourcemedals.cleardata();
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		Log.d("medals", "hmm");
		 
		//http post
		try{	
		
		        HttpClient httpclient = new DefaultHttpClient();
		        
		        HttpPost httppost = new HttpPost("http://rondroid.uphero.com/medals.html");
		        Log.d("medals", "hmm4");
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost);
		        Log.d("medals", "hmm5");
		        HttpEntity entity = response.getEntity();
		        InputStream is = entity.getContent();
		        
		//convert response to string
		
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		       // StringBuilder sb = new StringBuilder();
		        String medals = null,gold=null,silver=null,bronze=null,branch=null;
		       reader.mark(10000);
		       Log.d("medals", "hmm6");
		        while(!reader.readLine().equals("<body>"))
		        {}
		        Log.d("medals", "hmm7");
		        
		        while ((medals = reader.readLine()) != null) {
		        	
		        	if(medals.equals("</body>"))
		        		break;
		        	branch=medals.substring(0,medals.indexOf("g"));
		            gold=medals.substring(medals.lastIndexOf("g")+1,medals.lastIndexOf("s"));
		            silver=medals.substring(medals.lastIndexOf("s")+1,medals.lastIndexOf("b"));
		            bronze=medals.substring(medals.lastIndexOf("b")+1);
		            Log.d("adding medals",branch+" "+gold+" "+silver+" "+bronze+" " );
		            datasourcemedals.addmedals(branch, gold, silver, bronze);
		        }
		   
		     		       
		}catch(Exception e){
		}
	}
	public void connect()
	{	
		//setContentView(R.layout.index);
		Log.d("jhol", "inconnect");
        //flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
        datasource = new Commentsdatasource(this);
		datasource.open();
		Log.d("jhol", "opened");
		datasource.cleardata();   
		Log.d("jhol", "cleared");//First clear data then add
 
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		 
		//http post
		try{
		        HttpClient httpclient = new DefaultHttpClient();
		        Log.d("check", "started");
		        HttpPost httppost = new HttpPost("http://rondroid.uphero.com/display.php");
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost);
		        Log.d("check", "response"); 
		        HttpEntity entity = response.getEntity();
		        InputStream is = entity.getContent();
		
		//convert response to string
		
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		       // StringBuilder sb = new StringBuilder();
		        String p1 = null, p2 = null,date=null,time=null,sport=null,winner=null;
		       reader.mark(10000);
		        while(!reader.readLine().equals("<body>"))
		        {}
		        while ((p1 = reader.readLine()) != null) {
		        	
		             //   sb.append(match + "\n");
		        			
		                
		        			p2=reader.readLine();
		                	sport=reader.readLine();
		                	date=reader.readLine();
		                	time=reader.readLine();
		                	winner=reader.readLine();
		                	Log.d("check","p1="+p1+"p2= "+p2+"sport= "+sport+"date= "+date+"time= "+time+"winner= "+winner);
		                	if(!p1.equals("</body>"))
		                	{
		                		 datasource.add(p1,p2,sport,date,time,winner);
		                	}
		            
		                
		        	
		        }
		   
		     		       
		}catch(Exception e){
		}
	}
	
	
	
	public static void sendRegistrationIdToServer(Context c,String registrationId) {
		
		      
		      String deviceId = Secure.getString(c.getContentResolver(),Secure.ANDROID_ID);
		HttpClient client = new DefaultHttpClient();
		Log.d("C2DM","sending to server");
		HttpPost post = new HttpPost("http://rondroid.uphero.com/receiveandstore.php");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			// Get the deviceID
			Log.d("devid", deviceId);
			Log.d("regid", registrationId);
			nameValuePairs.add(new BasicNameValuePair("deviceid", deviceId));
			nameValuePairs.add(new BasicNameValuePair("registrationid",	registrationId));
			
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			Log.d("C2DM","sent to server");
			String line = "";
			while ((line = rd.readLine()) != null) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void show_list()
	{
		datasource = new Commentsdatasource(this);
		datasourcemyteams = new Commentsdatasourcemyteams(EnthusiaActivity.this);
		datasourcemyteams.open();
		mycount=0;
		
		datasourcemyteams.get_myteams();
		displaywatch2();
		Log.d("End", "show_list completed");
	}
	public static void displaymyteams(String branch,String sport)
	{
		mybranches[mycount]=branch;
		mysports[mycount]=sport;
		mycount++;
		Log.d("Myteams", "sport="+sport+"branch="+branch);
	}
	
	public void displaywatch2()
	{

		int idcount=0;
		int id_holder=0;
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.mymatcheslayout);
		rl.removeAllViews();
		for(int l=0;l<mycount;l++)
		{
		initialise();	
		Log.d("jhol", "check7wathclistmycount="+mycount+ " l="+l);
		datasource.view_all(mysports[l],mybranches[l]);
		Log.d("jhol", "check8");
		
		 
		TextView heading=new TextView(this);
		Log.d("jhol1", "check9");
		heading.setId(1000+l);
		Log.d("jhol1", "check10");
		RelativeLayout.LayoutParams lphead = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		Log.d("jhol1", "check11"+id_holder);
		lphead.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		heading.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
		heading.setText(mybranches[l]+"-"+mysports[l]);
		if(l!=0)
		{
			Log.d("jhol", "check12 "+id_holder+" "+l);
			lphead.addRule(RelativeLayout.BELOW,id_holder);
		}
		else
			lphead.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		heading.setTextColor(Color.parseColor("#FFFFFF"));
		
		heading.setLayoutParams(lphead);
		rl.addView(heading);
		id_holder=heading.getId();                          /// id holder heading only for l=0, ie new db entry
		
		Log.d("jhol", "p1count= "+p1count);
		
		TextView todaytxt=null;
		String date="";
		TextView dline=new TextView(this);
		RelativeLayout.LayoutParams dline1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		dline.setTextSize(TypedValue.COMPLEX_UNIT_DIP,1);
		dline1.addRule(RelativeLayout.BELOW,id_holder);
		dline.setId(2000+l);
		dline.setBackgroundColor(Color.parseColor("#483D8B"));
		dline.setLayoutParams(dline1);
		rl.addView(dline);
		id_holder=dline.getId();                          /// id holder line after heading
		
		
		for(j=0;j<p1count;j++)
		{	idcount++;
			Log.d("jhol", "winner"+winners[j]);
			
			if((winners[j]!=null) && (winners[j].contains("/")))
			{
				Log.d("jhol", "winnerasdasd");
				winners(winners[j],mysports[l]);
				
			}
			else
			{
				winner1=dates[j];
				winner2=times[j];
			}
			Log.d("result", "Inresultfor");
			if(!(date.equals(dates[j])))
			{
				Log.d("result", "Inif");
				date=dates[j];
			
			todaytxt=new TextView(this);
			todaytxt.setId(3000+idcount);
			
			RelativeLayout.LayoutParams lptoday = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			if(dates[0].equals(dates[j]))
				lptoday.addRule(RelativeLayout.BELOW,dline.getId());
			else
				lptoday.addRule(RelativeLayout.BELOW,id_holder);
			Log.d("result", "todaytxtpart");
			id_holder= todaytxt.getId();                                   /// id holder date
			todaytxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
			todaytxt.setText(dates[j]);
			lptoday.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics()),0, 0);
			todaytxt.setBackgroundColor(Color.parseColor("#483D8B"));
			todaytxt.setTextColor(Color.parseColor("#FF7722"));
			todaytxt.setLayoutParams(lptoday);
			rl.addView(todaytxt);
			
			Log.d("progress", "Till here");
							
			TextView line1=new TextView(this);
			line1.setId(4000+idcount);
			RelativeLayout.LayoutParams line11 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			line11.addRule(RelativeLayout.ALIGN_TOP,id_holder);
			line11.addRule(RelativeLayout.RIGHT_OF,id_holder);
			line1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,1);
			line11.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 15, this.getResources().getDisplayMetrics()),0, 0);
			line1.setBackgroundColor(Color.parseColor("#483D8B"));
			line1.setLayoutParams(line11);
			rl.addView(line1);
			Log.d("progress", "Till here");
			
			}
			Log.d("result", "before p1");
			TextView p1 = new TextView(this);
			p1.setTextColor(Color.parseColor("#FFFFFF"));
			p1.setId(5000+idcount);
			p1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
			p1.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 125, getResources().getDisplayMetrics()));
			p1.setText(p1s[j]);
			RelativeLayout.LayoutParams lpp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			lpp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			lpp1.addRule(RelativeLayout.BELOW,id_holder);
			id_holder= p1.getId();
			lpp1.setMargins( (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0, 0);
			p1.setLayoutParams(lpp1);
			rl.addView(p1);
			
			TextView p1score = new TextView(this);
			p1score.setTextColor(Color.parseColor("#FFFFFF"));
			p1score.setId(6000+idcount);
			p1score.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
			p1score.setText(winner1);
			p1.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 125, getResources().getDisplayMetrics()));
			RelativeLayout.LayoutParams lpp1score = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			lpp1score.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			lpp1score.addRule(RelativeLayout.ALIGN_TOP,p1.getId());
			lpp1score.setMargins(0,0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0);
			p1score.setLayoutParams(lpp1score);
			rl.addView(p1score);
			
			
			TextView p2 = new TextView(this);
			p2.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 125, getResources().getDisplayMetrics()));
			p2.setTextColor(Color.parseColor("#FFFFFF"));
			p2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
			p2.setId(7000+idcount);
			
			p2.setText(p2s[j]);
			RelativeLayout.LayoutParams lpp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			lpp2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			lpp2.addRule(RelativeLayout.BELOW,id_holder);
			lpp2.setMargins( (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0, 0);
			id_holder= p2.getId();
			p2.setLayoutParams(lpp2);
			rl.addView(p2);
			
			TextView p2score = new TextView(this);
			p2score.setTextColor(Color.parseColor("#FFFFFF"));
			p2score.setId(8000+idcount);
			p2score.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
			p2score.setText(winner2);
			p1.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 125, getResources().getDisplayMetrics()));
			RelativeLayout.LayoutParams lpp2score = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			lpp2score.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			lpp2score.addRule(RelativeLayout.ALIGN_TOP,p2.getId());
			lpp2score.setMargins(0,0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0);
			p2score.setLayoutParams(lpp2score);
			rl.addView(p2score);
			if((j==p1count-1)||(dates[j+1].equals(dates[j])))
			{
			TextView line1=new TextView(this);
			line1.setId(9000+idcount);
			RelativeLayout.LayoutParams line11 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			line11.addRule(RelativeLayout.BELOW,id_holder);
			id_holder=line1.getId();
			line1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,1);
			line11.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 20, this.getResources().getDisplayMetrics()),0, 0);
			line1.setBackgroundColor(Color.parseColor("#483D8B"));
			line1.setLayoutParams(line11);
			rl.addView(line1);
			
			Log.d("result", "after last line");
			}
			
		}
		}
		
		
	
	}
	public static void display(String sport,String p1,String p2,String date,String time,String winner)
	{
		
		p1s[p1count]=p1;
		
		p2s[p2count]=p2;
		++p2count;
		dates[datecount]=date;
		++datecount;
		times[timecount]=time;
		++timecount;
		winners[p1count]=winner;
		++p1count;
		Log.d("jhol","count="+ p1count);

				
	}
	public void display2(String sport)
	{	
		
		if (result==false)
		{
		int skip=0;   // This is required when there is no data in today and one match in upcoming  
		initialise();
		Log.d("jhol", "check7");
		datasource.view(sport);
		Log.d("jhol", "check8");
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.matcheslayout);
		rl.removeAllViews();
		//((RelativeLayout) rl).removeAllViews(); 
		TextView heading=new TextView(this);
	
		heading.setId(10000);
	
		RelativeLayout.LayoutParams lphead = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		lphead.addRule(RelativeLayout.CENTER_HORIZONTAL);
		lphead.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		heading.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
		heading.setText(sport.toUpperCase());;
		heading.setTextColor(Color.parseColor("#FFFFFF"));
		heading.setLayoutParams(lphead);
		rl.addView(heading);
		
		
		
		TextView todaytxt=new TextView(this);
		todaytxt.setId(10001);
		RelativeLayout.LayoutParams lptoday = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lptoday.addRule(RelativeLayout.BELOW,heading.getId());
		
		todaytxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
		todaytxt.setText("TODAY");
		lptoday.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics()),0, 0);
		todaytxt.setBackgroundColor(Color.parseColor("#483D8B"));
		todaytxt.setTextColor(Color.parseColor("#FF7722"));
		todaytxt.setLayoutParams(lptoday);
		rl.addView(todaytxt);
		Log.d("progress", "Till here");
		
		
		
		TextView line1=new TextView(this);
		line1.setId(10002);
		RelativeLayout.LayoutParams line11 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		line11.addRule(RelativeLayout.BELOW,heading.getId());
		line11.addRule(RelativeLayout.RIGHT_OF,todaytxt.getId());
		line1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,1);
		line11.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 20, this.getResources().getDisplayMetrics()),0, 0);
		line1.setBackgroundColor(Color.parseColor("#483D8B"));
		
		line1.setLayoutParams(line11);
		rl.addView(line1);
		Log.d("progress", "Till here");
		
		
		
		
		int newp1count;
		
		if(p1count==0)
			newp1count=1;
		else
			newp1count=p1count;
			
		for(j=0;j<newp1count;j++)
		
		{
			
			
			
			
			TextView line = new TextView(this);
		TextView tv = new TextView(this);
		tv.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 250, getResources().getDisplayMetrics()));
		tv.setId(j+1000);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
		tv.setTextColor(Color.parseColor("#FFFFFF"));
		if(p1count==0) 
		{
			
			tv.setText("No match today");
			skip=1;
		}
		
		else
		{
			if(sport.equals("Athletics") || sport.equals("Swimming"))
				tv.setText(p1s[j]+" "+p2s[j]);
			else
				tv.setText(p1s[j]+" vs "+p2s[j]);
			
		}
			
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		if(flag==0)
			lp.addRule(RelativeLayout.BELOW,todaytxt.getId());
	
		
		else
			{	lp.addRule(RelativeLayout.BELOW,j+999);
				lp.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics()),0, 0);
			}
		tv.setLayoutParams(lp);
		rl.addView(tv);	
	
		TextView tv2 = new TextView(this);
		tv2.setId(j+2000);
		if(p1count==0) 
			tv2.setText("");
		else
		tv2.setText(dates[j]);
		tv2.setTextColor(Color.parseColor("#FFFFFF"));
		RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp2.addRule(RelativeLayout.ALIGN_TOP,tv.getId());
	
			lp2.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0, 0);
	
		
		lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		tv2.setLayoutParams(lp2);
		rl.addView(tv2);	
		
		
		TextView tv3 = new TextView(this);
		tv3.setId(j+3000);
		if(p1count==0) 
			tv3.setText("");
		else
		tv3.setText(times[j]);
		tv3.setTextColor(Color.parseColor("#FFFFFF"));
		RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp3.addRule(RelativeLayout.BELOW,tv2.getId());
	
		
		lp3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		tv3.setLayoutParams(lp3);
		rl.addView(tv3);	
		flag=1;
		
		//TextView line = new TextView(this);   Declared at beginning of for 
		line.setId(j+4000);
		line.setHeight((int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP,(float) 1.9, this.getResources().getDisplayMetrics()));
		line.setBackgroundColor(Color.parseColor("#483D6B"));
		RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		lp4.addRule(RelativeLayout.BELOW,tv.getId());
		lp4.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 14, this.getResources().getDisplayMetrics()),0, 0);
		line.setLayoutParams(lp4);
		rl.addView(line);	
		
		}Log.d("progress", "Till here");
	
		datasource.view_upcoming(sport);
		//For upcoming text
	
			TextView upcoming = new TextView(this);
			upcoming.setId(5000);
			upcoming.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
			upcoming.setText("UPCOMING");
			RelativeLayout.LayoutParams upcoming_lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			upcoming.setBackgroundColor(Color.parseColor("#483D8B"));
			upcoming.setTextColor(Color.parseColor("#FF7722"));
			upcoming_lp.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0, 0);
			upcoming_lp.addRule(RelativeLayout.BELOW,j+999);
			upcoming.setLayoutParams(upcoming_lp);
			rl.addView(upcoming);	
			
			
			
			/*TextView begins = new TextView(this);
			begins.setTextColor(Color.parseColor("#FFFFFF"));
			begins.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 250, getResources().getDisplayMetrics()));
			begins.setId(j+1000);
			begins.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
			
			begins.setText("GAME BEGINS ON 20TH DECEMBER");
			
			RelativeLayout.LayoutParams lpbegins = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			lpbegins.addRule(RelativeLayout.BELOW,upcoming.getId());
			begins.setLayoutParams(lpbegins);
				
			begins.setBackgroundColor(Color.parseColor("#500033CC"));
			rl.addView(begins);	*/
		
			
		
		
		flag=0;
			
			
		if(skip==1)
			newp1count=p1count+1;
		else
			newp1count=p1count;
		for(;j<newp1count;j++)
		{	
			
			
			
		TextView line = new TextView(this);
		TextView tv = new TextView(this);
		tv.setTextColor(Color.parseColor("#FFFFFF"));
		tv.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 250, getResources().getDisplayMetrics()));
		tv.setId(j+1000);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
		
		
		if(skip==1)
		{
			
			if(sport.equals("Athletics") || sport.equals("Swimming"))
					tv.setText(p1s[j-1]+" "+p2s[j-1]);
			else
				tv.setText(p1s[j-1]+" vs "+p2s[j-1]);
		}
		else
		{
			
			if(sport.equals("Athletics") || sport.equals("Swimming"))
				tv.setText(p1s[j]+" "+p2s[j]);
			else
				tv.setText(p1s[j]+" vs "+p2s[j]);
		}
		//}
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		if(flag==0)
			lp.addRule(RelativeLayout.BELOW,upcoming.getId());
		
		
		else
			{	lp.addRule(RelativeLayout.BELOW,j+999);
				lp.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics()),0, 0);
			}
		tv.setLayoutParams(lp);
		rl.addView(tv);	
		
		TextView tv2 = new TextView(this);
		tv2.setTextColor(Color.parseColor("#FFFFFF"));
		tv2.setId(j+2000);
		if(skip==1)
			tv2.setText(dates[j-1]);
		else
			tv2.setText(dates[j]);
		RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp2.addRule(RelativeLayout.ALIGN_TOP,tv.getId());
	
			lp2.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0, 0);
	
		
		lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		tv2.setLayoutParams(lp2);
		rl.addView(tv2);	
		
		
		TextView tv3 = new TextView(this);
		tv3.setId(j+3000);
		if(skip==1)
			tv3.setText(times[j-1]);
		else
			tv3.setText(times[j]);
		tv3.setTextColor(Color.parseColor("#FFFFFF"));
		RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp3.addRule(RelativeLayout.BELOW,tv2.getId());
	
		
		lp3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		tv3.setLayoutParams(lp3);
		rl.addView(tv3);	
		flag=1;
		
		//TextView line = new TextView(this);   Declared at beginning of for 
		line.setId(j+4000);
		line.setHeight((int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, (float)1.9, this.getResources().getDisplayMetrics()));
		line.setBackgroundColor(Color.parseColor("#483D6B"));
		RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		lp4.addRule(RelativeLayout.BELOW,tv.getId());
		lp4.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 14, this.getResources().getDisplayMetrics()),0, 0);
		line.setLayoutParams(lp4);
		rl.addView(line);	
		
		}
		}
		else
		{
			
			initialise();
			Log.d("jhol", "check7");
			datasource.view_previous(sport);
			Log.d("jhol", "check8");
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.matcheslayout);
			rl.removeAllViews();
			//((RelativeLayout) rl).removeAllViews(); 
			TextView heading=new TextView(this);
			Log.d("jhol", "check9");
			heading.setId(10000);
			Log.d("jhol", "check10");
			RelativeLayout.LayoutParams lphead = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			Log.d("jhol", "check11");
			lphead.addRule(RelativeLayout.CENTER_HORIZONTAL);
			lphead.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			heading.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
			heading.setText(sport.toUpperCase());;
			heading.setTextColor(Color.parseColor("#FFFFFF"));
			heading.setLayoutParams(lphead);
			rl.addView(heading);
			Log.d("jhol", "p1count= "+p1count);
			String date="";
			TextView todaytxt=null;
			int id_holder=heading.getId();
			for(j=0;j<p1count;j++)
			{	
				Log.d("jhol", "winner"+winners[j]);
				
				if((winners[j]!=null) && (winners[j].contains("/")))
				{
					Log.d("jhol", "winnerasdasd");
					winners(winners[j],sport);
					
				}
				else
				{
					winner1=dates[j];
					winner2=times[j];
				}
				Log.d("result", "Inresultfor");
				if(!(date.equals(dates[j])))
				{
					Log.d("result", "Inif");
					date=dates[j];
				
				todaytxt=new TextView(this);
				todaytxt.setId(j+10001);
				
				RelativeLayout.LayoutParams lptoday = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				if(dates[0].equals(dates[j]))
					lptoday.addRule(RelativeLayout.BELOW,heading.getId());
				else
					lptoday.addRule(RelativeLayout.BELOW,id_holder);
				Log.d("result", "todaytxtpart");
				id_holder= todaytxt.getId();
				todaytxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
				todaytxt.setText(dates[j]);
				lptoday.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics()),0, 0);
				todaytxt.setBackgroundColor(Color.parseColor("#483D8B"));
				todaytxt.setTextColor(Color.parseColor("#FF7722"));
				todaytxt.setLayoutParams(lptoday);
				rl.addView(todaytxt);
				Log.d("progress", "Till here");
								
				TextView line1=new TextView(this);
				line1.setId(j+11001);
				RelativeLayout.LayoutParams line11 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				line11.addRule(RelativeLayout.ALIGN_TOP,id_holder);
				line11.addRule(RelativeLayout.RIGHT_OF,id_holder);
				line1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,1);
				line11.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 15, this.getResources().getDisplayMetrics()),0, 0);
				line1.setBackgroundColor(Color.parseColor("#483D8B"));
				line1.setLayoutParams(line11);
				rl.addView(line1);
				Log.d("progress", "Till here");
				
				}
				TextView p1 = new TextView(this);
				p1.setTextColor(Color.parseColor("#FFFFFF"));
				p1.setId(j+1000);
				p1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
				p1.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 125, getResources().getDisplayMetrics()));
				p1.setText(p1s[j]);
				RelativeLayout.LayoutParams lpp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				lpp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				lpp1.addRule(RelativeLayout.BELOW,id_holder);
				id_holder= p1.getId();
				lpp1.setMargins( (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0, 0);
				p1.setLayoutParams(lpp1);
				rl.addView(p1);
				
				TextView p1score = new TextView(this);
				p1score.setTextColor(Color.parseColor("#FFFFFF"));
				p1score.setId(j+2000);
				p1score.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
				p1score.setText(winner1);
				p1.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 125, getResources().getDisplayMetrics()));
				RelativeLayout.LayoutParams lpp1score = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				lpp1score.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				lpp1score.addRule(RelativeLayout.ALIGN_TOP,p1.getId());
				lpp1score.setMargins(0,0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0);
				p1score.setLayoutParams(lpp1score);
				rl.addView(p1score);
				
				
				TextView p2 = new TextView(this);
				p2.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 125, getResources().getDisplayMetrics()));
				p2.setTextColor(Color.parseColor("#FFFFFF"));
				p2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
				p2.setId(j+3000);
				
				p2.setText(p2s[j]);
				RelativeLayout.LayoutParams lpp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				lpp2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				lpp2.addRule(RelativeLayout.BELOW,id_holder);
				lpp2.setMargins( (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0, 0);
				id_holder= p2.getId();
				p2.setLayoutParams(lpp2);
				rl.addView(p2);
				
				TextView p2score = new TextView(this);
				p2score.setTextColor(Color.parseColor("#FFFFFF"));
				p2score.setId(j+4000);
				p2score.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
				p2score.setText(winner2);
				p1.setMaxWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 125, getResources().getDisplayMetrics()));
				RelativeLayout.LayoutParams lpp2score = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				lpp2score.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				lpp2score.addRule(RelativeLayout.ALIGN_TOP,p2.getId());
				lpp2score.setMargins(0,0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, this.getResources().getDisplayMetrics()),0);
				p2score.setLayoutParams(lpp2score);
				rl.addView(p2score);
				if((j==p1count-1)||(dates[j+1].equals(dates[j])))
				{
				TextView line1=new TextView(this);
				line1.setId(j+12001);
				RelativeLayout.LayoutParams line11 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				line11.addRule(RelativeLayout.BELOW,id_holder);
				id_holder=line1.getId();
				line1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,1);
				line11.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 20, this.getResources().getDisplayMetrics()),0, 0);
				line1.setBackgroundColor(Color.parseColor("#483D8B"));
				line1.setLayoutParams(line11);
				rl.addView(line1);
				}
			}
		}
		
	}
	
	
	
	
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null;
	}
	
	public void MyClick(View view) {
		String url=null;
		TextView tv=null;
		
		
		
		switch(view.getId())
		{
		case R.id.play_link:
			Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
			Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			try {
				  startActivity(goToMarket);}
			catch (ActivityNotFoundException e) {
				  Toast.makeText(this, "Couldn't launch the market", Toast.LENGTH_LONG).show();
			}
			break;
			
		case R.id.change:
			currentpage="myteams";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_six)));  
	        elv = (ExpandableListView) findViewById(R.id.expandableListView);
	        MyExpandableListAdapter expandableAdapter = new MyExpandableListAdapter();
	        elv.setAdapter((ExpandableListAdapter) expandableAdapter);
	        

			
			break;
		case R.id.dev_link:
			currentpage="develop";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.five_four)));  
			break;
		case R.id.develop:
			currentpage="develop";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.five_four)));  
			break;
		case R.id.attr_icon:
			currentpage=null;
			Log.d("jhol", "attr click");
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four)));  
			break;
		case R.id.attr1:
			currentpage="attr";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_one)));  
			a=1;
			break;
		case R.id.attr2:
			currentpage="attr";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_two)));  
			a=2;
			break;
		case R.id.attr3:
			currentpage="attr";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_three)));  
			a=3;
			break;
		case R.id.attr4:
			currentpage="attr";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_four)));  
			a=4;
			break;
		case R.id.attr5:
			currentpage="attr";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_five)));  
			a=5;
			break;
		case R.id.attr6:
			currentpage="attr";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_six)));  
			a=6;
			break;
		case R.id.about:
			currentpage=null;
			Log.d("jhol", "about click");
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.five)));  
	      
			break;
		case R.id.committee:
			currentpage="committee";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.five_one)));  
			
			break;
		case R.id.dh:
			currentpage="halftime";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.five_two)));  			
			break;
		case R.id.anthem:
			
			Intent anthem = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=HTiOF7Nc8KQ"));
			startActivity(anthem);
			
			break;
	case R.id.launch:
			
			Intent launch = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Q3i8WLXbbKY"));
			startActivity(launch);
			
			break;
		case R.id.contact:
			currentpage="committee";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.five_three)));  
			break;
		case R.id.goenthu:
			
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one)));  
			currentpage=null;
			break;
			
		case R.id.gointn:
			Log.d("jhol", "go_int click");
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.two)));  
			currentpage=null;
			k=1;			
			url="http://www.espnstar.com/cricket-rss";
			
			if(isNetworkAvailable())
				new progress().execute(url);
			else
				call(url);
			clearColor();
			tv=(TextView) findViewById(R.id.rsscricket);
			tv.setBackgroundColor(Color.parseColor("#483D6B"));
			tv.setFocusable(true);
			tv.setFocusableInTouchMode(true);
			tv.requestFocus();
			
			break;
			
		case R.id.sponsors:
			currentpage=null;
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.three)));  
			break;
			
		case R.id.my_teams:
			
			if(isNetworkAvailable())
        		new progressmyteams().execute();
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_three)));  
	        currentpage="enthu_options";
	        
	        
		
	        
			break;
			
		case R.id.schedule:
			
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_two)));  
	        currentpage="enthu_options";
	        TextView title=(TextView) findViewById(R.id.fixtures_title);
	        title.setText("Fixtures");
	        result=false;
	        if(isNetworkAvailable())
	        		new progressenthu().execute();
	        
			break;
		case R.id.results:
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_two)));
	        TextView title1=(TextView) findViewById(R.id.fixtures_title);
	        title1.setText("Results");
	        result=true;
	        currentpage="enthu_options";
	        if(isNetworkAvailable())
        		new progressenthu().execute();
			
	        
			break;
		case R.id.medals:
			
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_four))); 
	        if(isNetworkAvailable())
		    	   new medalsprogress().execute();
		       else
		        displaymedals2();
	        currentpage="enthu_options";
			break;
		case R.id.news:
			
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_five))); 
	        if(isNetworkAvailable())
		    	   new enthunewsprogress().execute();
		       else
		    	   show_enthu_news();
	        currentpage="enthu_options";
			break;
			
		case R.id.basketball:
			i=1;
			currentpage="Basketball";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			
			
			 
    		
    		
		break;
		case R.id.basketballtab:
			i=1;
			currentpage="Basketball";
			showsport(i);
			iv=(ImageView) findViewById(R.id.basketballtab);
			 
    		
    		
			
			
		break;
		case R.id.football:
			i=2;
			currentpage="Football";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.footballtab);
			 
    		
    		
			
			break;
		case R.id.footballtab:
			i=2;
			currentpage="Football";
				
			showsport(i);
			iv=(ImageView) findViewById(R.id.footballtab);
			 
    		
    		
			
		break;
			
		case R.id.cricket:
			i=3;
			currentpage="Cricket";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.crickettab);
			 
    		
    		
			
			break;
		case R.id.crickettab:
			i=3;
			currentpage="Cricket";
			showsport(i);
			iv=(ImageView) findViewById(R.id.crickettab);
			 
    		
    		
			
		break;
		
		case R.id.tt:
			i=4;
			currentpage="Table Tennis";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.tttab);
			 
    		
    		
		
			break;
		case R.id.tttab:
			i=4;
			currentpage="Table Tennis";
			showsport(i);
			iv=(ImageView) findViewById(R.id.tttab);
			 break;
		case R.id.volleyball:
			i=5;
			currentpage="Volleyball";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.volleyballtab);
			 break;
		case R.id.volleyballtab:
			i=5;
			currentpage="Volleyball";
			showsport(i);
			iv=(ImageView) findViewById(R.id.volleyballtab);
		break;
		case R.id.tennis:
			i=6;
			currentpage="Tennis";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.tennistab);
				
					break;
		case R.id.tennistab:
			i=6;
			currentpage="Tennis";
			showsport(i);
			iv=(ImageView) findViewById(R.id.tennistab);
			 
		break;
		case R.id.badminton:
			i=7;
			currentpage="Badminton";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.badmintontab);
			 
			break;
		case R.id.badmintontab:
			i=7;
			currentpage="Badminton";
			showsport(i);
			iv=(ImageView) findViewById(R.id.badmintontab);
			 
		break;
		case R.id.chess:
			i=8;
			currentpage="Chess";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.chesstab);
			 
			break;
		case R.id.chesstab:
			i=8;
			currentpage="Chess";
			showsport(i);
			iv=(ImageView) findViewById(R.id.chesstab);
			 
		break;
		case R.id.carrom:
			i=9;
			currentpage="Carrom";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.carromtab);
			 
			break;
		case R.id.carromtab:
			i=9;
			currentpage="Carrom";
			showsport(i);
			iv=(ImageView) findViewById(R.id.carromtab);
			 
			break;
		case R.id.throwball:
			i=10;
			currentpage="Throwball";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.throwballtab);
			 
			break;
		case R.id.throwballtab:
			i=10;
			currentpage="Throwball";
			showsport(i);
			iv=(ImageView) findViewById(R.id.throwballtab);
			 
		break;
		case R.id.athletics:
			i=11;
			currentpage="Athletics";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.athleticstab);
			 
			break;
		case R.id.athleticstab:
			i=11;
			currentpage="Athletics";
			showsport(i);
			iv=(ImageView) findViewById(R.id.athleticstab);
			 
		break;
		case R.id.swimming:
			i=12;
			currentpage="Swimming";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.swimmingtab);
			 
			break;
		case R.id.swimmingtab:
			i=12;
			currentpage="Swimming";
			showsport(i);
			iv=(ImageView) findViewById(R.id.swimmingtab);
			 
			break;
		case R.id.kabaddi:
			i=13;
			currentpage="Kabaddi";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.swimmingtab);
			 
			break;
		case R.id.kabadditab:
			i=13;
			currentpage="Kabaddi";
			showsport(i);
			iv=(ImageView) findViewById(R.id.kabadditab);
			 
		break;
		case R.id.box_cric:
			i=14;
			currentpage="Box Cricket";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.box_crictab);
			break;
		case R.id.box_crictab:
			i=14;
			currentpage="Box Cricket";
			showsport(i);
			iv=(ImageView) findViewById(R.id.box_crictab);
		break;
		case R.id.rink:
			i=16;
			currentpage="Rink Football";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.rinktab);
			break;
		case R.id.rinktab:
			i=16;
			currentpage="Rink Football";
			showsport(i);
			iv=(ImageView) findViewById(R.id.rinktab);
		break;
		
		case R.id.dodgeball:
			i=17;
			currentpage="Dodgeball";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.dodgeballtab);
			break;
			
		case R.id.dodgeballtab:
			i=17;
			currentpage="Dodgeball";
			showsport(i);
			iv=(ImageView) findViewById(R.id.dodgeballtab);
		break;
		case R.id.khokho:
			
			i=15;
			currentpage="Kho-kho";
			flipper.setInAnimation(inFromRightAnimation());
	        flipper.setOutAnimation(outToLeftAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.one_one)));  	
			showsport(i);
			iv=(ImageView) findViewById(R.id.khokhotab);
				break;
		case R.id.khokhotab:
			i=15;
			currentpage="Khokho";
			showsport(i);
			iv=(ImageView) findViewById(R.id.khokhotab);
			 
		break;
			
		case R.id.rsscricket:
			
	    	k=1;
    		url="http://www.espnstar.com/cricket-rss";
    		if(isNetworkAvailable())
				new progress().execute(url);
			else
				call(url);
    		clearColor();
    		tv=(TextView) findViewById(R.id.rsscricket);
    		tv.setBackgroundColor(Color.parseColor("#483D6B"));
    		tv.setFocusable(true);
    		tv.setFocusableInTouchMode(true);
    		tv.requestFocus();
    		break;
    	case R.id.rsssoccer:
    		
    		k=2;
    		url="http://www.espnstar.com/football-rss";
    		if(isNetworkAvailable())
				new progress().execute(url);
			else
				call(url);
    		clearColor();
    		tv=(TextView) findViewById(R.id.rsssoccer);
    		tv.setBackgroundColor(Color.parseColor("#483D6B"));
    		tv.setFocusable(true);
    		tv.setFocusableInTouchMode(true);
    		tv.requestFocus();
    		break;
    	case R.id.rsstennis:
    		
    		k=3;
    		url="http://www.espnstar.com/tennis-rss";
    		if(isNetworkAvailable())
				new progress().execute(url);
			else
				call(url);
    		clearColor();
    		tv=(TextView) findViewById(R.id.rsstennis);
    		tv.setBackgroundColor(Color.parseColor("#483D6B"));
    		tv.setFocusable(true);
    		tv.setFocusableInTouchMode(true);
    		tv.requestFocus();
    		break;
    	case R.id.rssbasketball:
    		
    		k=4;
    		url="http://www.nba.com/rss/nba_rss.xml";
    		
    		if(isNetworkAvailable())
				new progress().execute(url);
			else
				call(url);
    		clearColor();
    		tv=(TextView) findViewById(R.id.rssbasketball);
    		tv.setBackgroundColor(Color.parseColor("#483D6B"));
    		tv.setFocusable(true);
    		tv.setFocusableInTouchMode(true);
    		tv.requestFocus();
    		
    		
    		
    		break;
    	case R.id.rssgolf:
    		k=5;
    		
    		
    		
    		url="http://www.espnstar.com/golf-rss";
    		if(isNetworkAvailable())
				new progress().execute(url);
			else
				call(url);
    		clearColor();
    		tv=(TextView) findViewById(R.id.rssgolf);
    		tv.setBackgroundColor(Color.parseColor("#483D6B"));
    		tv.setFocusable(true);
    		tv.setFocusableInTouchMode(true);
    		tv.requestFocus();
    		break;
    	case R.id.rssF1:
    		
    		k=6;
    		url="http://www.espnstar.com/f1-rss";
    		if(isNetworkAvailable())
				new progress().execute(url);
			else
				call(url);
    		clearColor();
    		tv=(TextView) findViewById(R.id.rssF1);
    		tv.setBackgroundColor(Color.parseColor("#483D6B"));
    		tv.setFocusable(true);
    		tv.setFocusableInTouchMode(true);
    		tv.requestFocus();
    		break;
		}

	}

	  GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

          public boolean onDown(MotionEvent event) {
               return true;
            }

          public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
               
                //HorizontalScrollView hoscl=(HorizontalScrollView) findViewById(R.id.horiscroll);
		         metrics.setToDefaults();
		         //Float yd=metrics.ydpi;
                int dx = (int) (event2.getX() - event1.getX());
                // don't accept the fling if it's too short
                // as it may conflict with a button push
                if (Math.abs(dx) > metrics.xdpi && Math.abs(velocityX) > Math.abs(velocityY)  && event1.getY()>(metrics.ydpi)) {
                    if (velocityX > 0 ) {
                    	if(currentpage==null)
                    	{
                    		if(k!=1)
                        		rsscases(--k);
                    	//String str=String.valueOf(yd);
                    		
                    	}
                    	else if(currentpage.equals("attr"))
                    	{
                    		if(a!=1)
                    		{
                    			a1=a;
                    			showattr(--a);
                    		}
                    	}
                    	else
                    	{
                    		if( i!=1)
                    			showsport(--i); //code to display page
                    	}
                    		
                    	}
                    else {
                    	if(currentpage==null)
                    	{
                    		if (k!=6)
                    		{
                    			rsscases(++k);
                    		}
                    		
                    	}
                    	else if(currentpage.equals("attr"))
                    	{
                    		if(a!=6)
                    		{
                    			a1=a;
                    			showattr(++a);
                    		}
                    	}
                    	else
                    	{
                    		if(i!=17)
                    			showsport(++i);
                    	}
                    			
                    }
                    
                    return true;
                    
                }
                
                else {
                    return false;
                }
                    
            }
}; 

public void showattr(int a)
{
	switch(a)
	{
	case 1:
		flipper.setInAnimation(inFromLeftAnimation());
        flipper.setOutAnimation(outToRightAnimation());
        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_one))); 
        break;
	case 2:
		if(a1<a)
		{
		flipper.setInAnimation(inFromRightAnimation());
        flipper.setOutAnimation(outToLeftAnimation());
        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_two))); 
		}
		else
		{
			flipper.setInAnimation(inFromLeftAnimation());
	        flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_two))); 
		}
        break;
	case 3:
		if(a1<a)
		{
		flipper.setInAnimation(inFromRightAnimation());
        flipper.setOutAnimation(outToLeftAnimation());
        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_three))); 
		}
		else
		{
			flipper.setInAnimation(inFromLeftAnimation());
	        flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_three))); 
		}
        break;
	case 4:
		if(a1<a)
		{
		flipper.setInAnimation(inFromRightAnimation());
        flipper.setOutAnimation(outToLeftAnimation());
        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_four))); 
		}
		else
		{
			flipper.setInAnimation(inFromLeftAnimation());
	        flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_four))); 
		}
        break;
	case 5:
		if(a1<a)
		{
		flipper.setInAnimation(inFromRightAnimation());
        flipper.setOutAnimation(outToLeftAnimation());
        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_five))); 
		}
		else
		{
			flipper.setInAnimation(inFromLeftAnimation());
	        flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_five))); 
		}
        break;
	case 6:
		if(a1<a)
		{
		flipper.setInAnimation(inFromRightAnimation());
        flipper.setOutAnimation(outToLeftAnimation());
        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_six))); 
		}
		else
		{
			flipper.setInAnimation(inFromLeftAnimation());
	        flipper.setOutAnimation(outToRightAnimation());
	        flipper.setDisplayedChild(flipper.indexOfChild(findViewById(R.id.four_six))); 
		}
        break;
	}
}


public void rsscases(int k)
{	
	
	
	TextView tv=null;
	switch(k)
	{
	case 1:
		url="http://www.espnstar.com/cricket-rss";
		
		if(isNetworkAvailable())
			new progress().execute(url);
		else
			call(url);
		clearColor();
		tv=(TextView) findViewById(R.id.rsscricket);
		tv.setBackgroundColor(Color.parseColor("#483D6B"));
		tv.setFocusable(true);
		tv.setFocusableInTouchMode(true);
		tv.requestFocus();
		break;
	case 2:
		url="http://www.espnstar.com/football-rss";
		if(isNetworkAvailable())
			new progress().execute(url);
		else
			call(url);
		clearColor();
		tv=(TextView) findViewById(R.id.rsssoccer);
		tv.setBackgroundColor(Color.parseColor("#483D6B"));
		tv.setFocusable(true);
		tv.setFocusableInTouchMode(true);
		tv.requestFocus();
		break;
		
	case 3:
	    
		url="http://www.espnstar.com/tennis-rss";
		if(isNetworkAvailable())
			new progress().execute(url);
		else
			call(url);
		clearColor();
		tv=(TextView) findViewById(R.id.rsstennis);
		tv.setBackgroundColor(Color.parseColor("#483D6B"));
		tv.setFocusable(true);
		tv.setFocusableInTouchMode(true);
		tv.requestFocus();
		break;
	case 4:
		
		url="http://www.nba.com/rss/nba_rss.xml";
		if(isNetworkAvailable())
			new progress().execute(url);
		else
			call(url);
		clearColor();
		
		tv=(TextView) findViewById(R.id.rssbasketball);
		tv.setBackgroundColor(Color.parseColor("#483D6B"));
		tv.setFocusable(true);
		tv.setFocusableInTouchMode(true);
		tv.requestFocus();
		break;
	case 5:
		
		url="http://www.espnstar.com/golf-rss";
		if(isNetworkAvailable())
			new progress().execute(url);
		else
			call(url);
		clearColor();
		tv=(TextView) findViewById(R.id.rssgolf);
		tv.setBackgroundColor(Color.parseColor("#483D6B"));
		tv.setFocusable(true);
		tv.setFocusableInTouchMode(true);
		tv.requestFocus();
		break;
	case 6:
		
		url="http://www.espnstar.com/f1-rss";
		if(isNetworkAvailable())
			new progress().execute(url);
		else
			call(url);
		clearColor();
		tv=(TextView) findViewById(R.id.rssF1);
		tv.setBackgroundColor(Color.parseColor("#483D6B"));
		tv.setFocusable(true);
		tv.setFocusableInTouchMode(true);
		tv.requestFocus();
		break;
	}
	
}
		public void showsport(int i){
			datasource = new Commentsdatasource(this);
			//initialise();
			//show(sportname[i]);
			Log.d("jhol","check1");
			//if(isNetworkAvailable())
				//new progressenthu().execute(sportname[i]);
			//else
			display2(sportname[i]);
			Log.d("jhol","check2");
			//datasource.view(sportname[i]);  
			Log.d("jhol","check3");
			
			//display2(sportname[i]); 
			switch(i)
			{
			case 1:
				iv=(ImageView) findViewById(R.id.basketballtab);
				break;
			case 2:
				iv=(ImageView) findViewById(R.id.footballtab);
				break;
			case 3:
				iv=(ImageView) findViewById(R.id.crickettab);
				break;
			case 4:
				iv=(ImageView) findViewById(R.id.tttab);
				break;
			case 5:
				iv=(ImageView) findViewById(R.id.volleyballtab);
				break;
			case 6:
				iv=(ImageView) findViewById(R.id.tennistab);
				break;
			case 7:
				iv=(ImageView) findViewById(R.id.badmintontab);
				break;
			case 8:
				iv=(ImageView) findViewById(R.id.chesstab);
				break;
			case 9:
				iv=(ImageView) findViewById(R.id.carromtab);
				break;
				
			case 10:
				iv=(ImageView) findViewById(R.id.throwballtab);
				break;
			case 11:
				iv=(ImageView) findViewById(R.id.athleticstab);
				break;
				
			case 12:
				iv=(ImageView) findViewById(R.id.swimmingtab);
				break;
			case 13:
				iv=(ImageView) findViewById(R.id.kabadditab);
				break; 
			case 14:
				iv=(ImageView) findViewById(R.id.box_crictab);
				break;
			case 15:
				iv=(ImageView) findViewById(R.id.khokhotab);
				break;
			case 16:
				iv=(ImageView) findViewById(R.id.rinktab);
				break;
			case 17:
				iv=(ImageView) findViewById(R.id.dodgeballtab);
				break;
			}
			iv.setFocusable(true);
			iv.setFocusableInTouchMode(true);
			iv.requestFocus();
			Log.d("jhol","check4");
			//display2(sportname[i]);
			Log.d("jhol","check5");
			
			datasource.close();
			Log.d("jhol", "check6");
		}
		public void clearColor()
		{	
			TextView tv=null; 
			tv=(TextView) findViewById(R.id.rsscricket);
    		tv.setBackgroundColor(Color.parseColor("#336699"));
			tv=(TextView) findViewById(R.id.rsssoccer);
    		tv.setBackgroundColor(Color.parseColor("#336699"));
    		tv=(TextView) findViewById(R.id.rsstennis);
    		tv.setBackgroundColor(Color.parseColor("#336699"));
    		tv=(TextView) findViewById(R.id.rssbasketball);
    		tv.setBackgroundColor(Color.parseColor("#336699"));
    		tv=(TextView) findViewById(R.id.rssgolf);
    		tv.setBackgroundColor(Color.parseColor("#336699"));
    		tv=(TextView) findViewById(R.id.rssF1);
    		tv.setBackgroundColor(Color.parseColor("#336699"));
    		
		}
		public static void color_view(TextView tv,String branch)
		{
			if(branch.equals("IT"))
			{
				tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
				tv.setTextColor(Color.parseColor("#000000"));
			}
			else if(branch.equals("EXTC"))
			{
				tv.setBackgroundColor(Color.parseColor("#303030"));
				tv.setTextColor(Color.parseColor("#FFFFFF"));
			}
			else if(branch.equals("Civil"))
			{
				tv.setBackgroundColor(Color.parseColor("#7EC0EE"));
				tv.setTextColor(Color.parseColor("#000000"));
			}
			else if(branch.equals("Computer"))
			{
				tv.setBackgroundColor(Color.parseColor("#8B4513"));
				tv.setTextColor(Color.parseColor("#FFFFFF"));
			}
			else if(branch.equals("Electronics"))
			{
				tv.setBackgroundColor(Color.parseColor("#DD7500"));
				tv.setTextColor(Color.parseColor("#000000"));
			}
			else if(branch.equals("Mech"))
			{
				tv.setBackgroundColor(Color.parseColor("#800000"));
				tv.setTextColor(Color.parseColor("#FFFFFF"));
			}
			else if(branch.equals("Prod"))
			{
				tv.setBackgroundColor(Color.parseColor("#EEEE00"));
				tv.setTextColor(Color.parseColor("#000000"));
			}
			
			else if(branch.equals("Trical"))
			{
				tv.setBackgroundColor(Color.parseColor("#000080"));
				tv.setTextColor(Color.parseColor("#FFFFFF"));
			}
			else if(branch.equals("Textile"))
			{
				tv.setBackgroundColor(Color.parseColor("#008000"));
				tv.setTextColor(Color.parseColor("#000000"));
			}
			else if(branch.equals("Chemsa"))
			{
				tv.setBackgroundColor(Color.parseColor("#5E2D79"));
				tv.setTextColor(Color.parseColor("#FFFFFF"));
			}
			else if(branch.equals("Masters"))
			{
				tv.setBackgroundColor(Color.parseColor("#EE0000"));
				tv.setTextColor(Color.parseColor("#000000"));
			}
			
			
		}
		
		public static void displaymedals(String branch,String gold,String silver,String bronze)
		{
			
			branches[mcount]=branch;
			golds[mcount]=gold;
			silvers[mcount]=silver;
			bronzes[mcount]=bronze;
			Log.d("displaymedals", "branch= "+branches[mcount]+"gold= "+golds[mcount]);
			mcount++;
			
					
		}
		public void displaymedals2()
		{
			initialise();	
			datasourcemedals = new Commentsdatasourcemedals(this);
			datasourcemedals.open();
			datasourcemedals.viewmedals();
			Log.d("medals","viewcomplete");
			
			TextView tv;
			tv=(TextView) findViewById(R.id.teamone);
			Log.d("medals",branches[0]);
			tv.setText(branches[0]);
			color_view(tv,branches[0]);
			Log.d("medals","branches[0]");
			tv=(TextView) findViewById(R.id.oneg);
			tv.setText(golds[0]);
			Log.d("medals","golds[0]");
			tv=(TextView) findViewById(R.id.ones);
			tv.setText(silvers[0]);
			Log.d("medals","silvers[0]");
			tv=(TextView) findViewById(R.id.oneb);
			tv.setText(bronzes[0]);
			Log.d("medals","bronzes[0]");
			
			tv=(TextView) findViewById(R.id.teamtwo);
			tv.setText(branches[1]);
			color_view(tv,branches[1]);
			tv=(TextView) findViewById(R.id.twog);
			tv.setText(golds[1]);
			tv=(TextView) findViewById(R.id.twos);
			tv.setText(silvers[1]);
			tv=(TextView) findViewById(R.id.twob);
			tv.setText(bronzes[1]);
			
			tv=(TextView) findViewById(R.id.teamthree);
			tv.setText(branches[2]);
			color_view(tv,branches[2]);
			tv=(TextView) findViewById(R.id.threeg);
			tv.setText(golds[2]);
			tv=(TextView) findViewById(R.id.threes);
			tv.setText(silvers[2]);
			tv=(TextView) findViewById(R.id.threeb);
			tv.setText(bronzes[2]);
			
			tv=(TextView) findViewById(R.id.teamfour);
			tv.setText(branches[3]);
			color_view(tv,branches[3]);
			tv=(TextView) findViewById(R.id.fourg);
			tv.setText(golds[3]);
			tv=(TextView) findViewById(R.id.fours);
			tv.setText(silvers[3]);
			tv=(TextView) findViewById(R.id.fourb);
			tv.setText(bronzes[3]);
			
			tv=(TextView) findViewById(R.id.teamfive);
			tv.setText(branches[4]);
			color_view(tv,branches[4]);
			tv=(TextView) findViewById(R.id.fiveg);
			tv.setText(golds[4]);
			tv=(TextView) findViewById(R.id.fives);
			tv.setText(silvers[4]);
			tv=(TextView) findViewById(R.id.fiveb);
			tv.setText(bronzes[4]);
			
			tv=(TextView) findViewById(R.id.teamsix);
			tv.setText(branches[5]);
			color_view(tv,branches[5]);
			tv=(TextView) findViewById(R.id.sixg);
			tv.setText(golds[5]);
			tv=(TextView) findViewById(R.id.sixs);
			tv.setText(silvers[5]);
			tv=(TextView) findViewById(R.id.sixb);
			tv.setText(bronzes[5]);
			
			tv=(TextView) findViewById(R.id.teamseven);
			tv.setText(branches[6]);
			color_view(tv,branches[6]);
			tv=(TextView) findViewById(R.id.seveng);
			tv.setText(golds[6]);
			tv=(TextView) findViewById(R.id.sevens);
			tv.setText(silvers[6]);
			tv=(TextView) findViewById(R.id.sevenb);
			tv.setText(bronzes[6]);
			
			tv=(TextView) findViewById(R.id.teameight);
			tv.setText(branches[7]);
			color_view(tv,branches[7]);
			tv=(TextView) findViewById(R.id.eightg);
			tv.setText(golds[7]);
			tv=(TextView) findViewById(R.id.eights);
			tv.setText(silvers[7]);
			tv=(TextView) findViewById(R.id.eightb);
			tv.setText(bronzes[7]);
			
			tv=(TextView) findViewById(R.id.teamnine);
			tv.setText(branches[8]);
			color_view(tv,branches[8]);
			tv=(TextView) findViewById(R.id.nineg);
			tv.setText(golds[8]);
			tv=(TextView) findViewById(R.id.nines);
			tv.setText(silvers[8]);
			tv=(TextView) findViewById(R.id.nineb);
			tv.setText(bronzes[8]);
			
			tv=(TextView) findViewById(R.id.teamten);
			tv.setText(branches[9]);
			color_view(tv,branches[9]);
			tv=(TextView) findViewById(R.id.teng);
			tv.setText(golds[9]);
			tv=(TextView) findViewById(R.id.tens);
			tv.setText(silvers[9]);
			tv=(TextView) findViewById(R.id.tenb);
			tv.setText(bronzes[9]);
			
			tv=(TextView) findViewById(R.id.teameleven);
			tv.setText(branches[10]);
			color_view(tv,branches[10]);
			tv=(TextView) findViewById(R.id.eleveng);
			tv.setText(golds[10]);
			tv=(TextView) findViewById(R.id.elevens);
			tv.setText(silvers[10]);
			tv=(TextView) findViewById(R.id.elevenb);
			tv.setText(bronzes[10]);
			
			datasourcemedals.close();
					
		}
		public static void displayrss(String title,String content,String url,String sporturl)
		{
			
			titles[titlecount]=title;
			
			contents[titlecount]=content;
			
			urls[titlecount]=url;
			++titlecount;
			
			

					
		}
		
		
		public void displayrss2(String sporturl)

		{
			initialise();
			
			datasourcerss.viewrss(sporturl);
	
	
	LinearLayout ll=(LinearLayout) findViewById(R.id.linlayout);
	if(((LinearLayout) ll).getChildCount() > 0) 
	    ((LinearLayout) ll).removeAllViews(); 
	for(int j=0;j<titlecount;j++)
	{
		TextView tv = new TextView(this);
		tv.setText(titles[j].trim());
		tv.setTextColor(Color.WHITE);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
		tv.setBackgroundColor(Color.parseColor("#483D6B"));
		TextView content = new TextView(this);
		content.setText("\n"+contents[j]);
		content.setTextColor(Color.parseColor("#FFFFFF"));	
		content.setTypeface(null,Typeface.BOLD);
		
		final TextView link=new TextView(this);
		link.setText("For more info:\n"+urls[j]+"\n");
		link.setMovementMethod(LinkMovementMethod.getInstance());
		link.setTextColor(Color.parseColor("#FF7722"));
		link.setOnClickListener(new OnClickListener() {
		

			public void onClick(View v) {
				
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getText().toString().substring(15)));
				startActivity(browserIntent);
			}
		});
		
		TextView line=new TextView(this);
		line.setHeight((int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP,(float) 1.9, this.getResources().getDisplayMetrics()));
		line.setBackgroundColor(Color.parseColor("#483D6B"));
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 14, this.getResources().getDisplayMetrics()),0, 0);
		line.setLayoutParams(lp);
		
		ll.addView(line);	
		ll.addView(tv);
		ll.addView(content);
		ll.addView(link);
	}
			datasourcerss.close();
		}
		public void runDialog()
		{	final ProgressDialog progressDialog;

		    	progressDialog = ProgressDialog.show(this, "Please wait", "Updating");

		    	new Thread(new Runnable(){
		    		public void run(){
		    			try {
					                Thread.sleep(3 * 1000);
							progressDialog.dismiss();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    		}
		    	}).start();
		}
	    public void call(String url)
	    {	
	    	   	
    		datasourcerss = new CommentsdatasourceRSS(this);
    		datasourcerss.open();
	    	
	    	if(isNetworkAvailable())
	    	{	
	    		
	    		
	    		
	    		rssparser.parse(url);
	    		if(rssparser.netflag==0)
	    		{
	    			datasourcerss.cleardata(url);   //clear data then add
	    	
	    			/*LinearLayout ll=(LinearLayout) findViewById(R.id.linlayout);
	    	
	    	if(((LinearLayout) ll).getChildCount() > 0) 
	    	    	((LinearLayout) ll).removeAllViews();*/ 
	    			for(int j=0;j<arrays.PodcastTitle.length;j++)
	    			{
	    			    	
	    				initialise();
	    				Log.d("progress", "in for");
	    					datasourcerss.addrss(arrays.PodcastTitle[j],arrays.PodcastContent[j],arrays.PodcastURL[j],url);
	    					Log.d("progress", "till here");
	    					
	    					//	ll.addView(line);	
	    					//	ll.addView(tv);
	    					//ll.addView(tv1);
	    					//	ll.addView(link);
	    					//displayrss2 is in function post execute
	    		
	    			}
	    	
	    					//clearColor();
	    		}
	    		else
		    	{
		    		clearColor();
		    		displayrss2(url);
		    	}
	    	}
	    	
	    	else
	    	{	
	    		
	    		clearColor();
	    		displayrss2(url);
	    	}
	    	
	    }
	    
	    public void winners(String winner, String sport)
	    {
	    	win=winner.substring(1, winner.indexOf("/"));
	    		Log.d("jhol", winner);
	    		winner1=winner.substring(winner.indexOf("/")+1,winner.indexOf("-"));
	    		Log.d("jhol", winner1);
	    		winner2=winner.substring(winner.indexOf("-")+1);
	    		Log.d("jhol", winner2);
	    		
	    	
	    }
	    
	    class MyExpandableListAdapter extends BaseExpandableListAdapter {

	        // Sample data set.  children[i] contains the children (String[]) for groups[i].
	        private String[] groups =branchename;
	        private String[] children = teamname ;


	            public Object getChild(int groupPosition, int childPosition) {
	                return children[childPosition];
	            }

	            public long getChildId(int groupPosition, int childPosition) {
	                return childPosition;
	            }

	            public int getChildrenCount(int groupPosition) {
	                return children.length;
	            }

	            public TextView getGenericView() {
	                // Layout parameters for the ExpandableListView
	                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
	                        ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

	                TextView textView = new TextView(EnthusiaActivity.this);
	                textView.setLayoutParams(lp);
	                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
	                // Center the text vertically
	                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
	                // Set the text starting position
	                textView.setPadding((int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 40, EnthusiaActivity.this.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 7, EnthusiaActivity.this.getResources().getDisplayMetrics()), 0, (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 7, EnthusiaActivity.this.getResources().getDisplayMetrics()));
	                return textView;
	            }



	            

	            public Object getGroup(int groupPosition) {
	                return groups[groupPosition];
	            }

	            public int getGroupCount() {
	                return groups.length;
	            }

	            public long getGroupId(int groupPosition) {
	                return groupPosition;
	            }

	            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
	                TextView textView = getGenericView();
	                textView.setText(getGroup(groupPosition).toString());
	                return textView;
	                
	            }
	            public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
	                    View convertView, ViewGroup parent) {

	                final CheckBox chkbox = new CheckBox(EnthusiaActivity.this);
	                datasourcemyteams = new Commentsdatasourcemyteams(EnthusiaActivity.this);
					datasourcemyteams.open();
	                chkbox.setTag("" + groupPosition + "," + childPosition);
	                if(datasourcemyteams.viewmyteams(groups[groupPosition],children[childPosition]))
	                	chkbox.setChecked(true);
	                
	                	
	                chkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	                    

						@Override
						public void onCheckedChanged(CompoundButton arg0,
								boolean arg1) {
							
							// TODO Auto-generated method stub
							if (chkbox.isChecked())
								datasourcemyteams.addmyteams(groups[groupPosition],children[childPosition]);
							else
								datasourcemyteams.clearteams(groups[groupPosition],children[childPosition]);
							
						}
	                });
	                chkbox.setText(children[childPosition]);
	                

	                return chkbox;
	                
	            }


	            public boolean hasStableIds() {
	                return true;
	            }

	            public boolean isChildSelectable(int groupPosition, int childPosition) {
	                return true;
	            }
	        }
		
	    @Override
	    public boolean dispatchTouchEvent(MotionEvent me){
	    	if(p==0)
	    		{
	    		x1=me.getX(0);
	    		p=1;
	    		}
	    	else
	    	{
	         x2=me.getX(0);
	         p=0;
	    	}
	    	
	    	if(p==0)
	    	{
	         y1=me.getY(0);
	         p=1;
	    	}
	    	else
	    		{
	    		y2=me.getY(0);
	    		p=0;
	    		}
	         String xs1,xs2,ys1,ys2,pc;
	         
	         if(i!=0)
	        	 hoscl=(HorizontalScrollView) findViewById(R.id.horiscroll);
	         else if(k!=0) hoscl=(HorizontalScrollView) findViewById(R.id.horiscroll1);
	         
	         if(i!=0 || k!=0)
	         	{
	        	 if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.FROYO)
	        	 	{
	        		 if (gestureScanner.onTouchEvent(me) && hoscl.dispatchTouchEvent(me))
	        		 return true;
	        		 else
	        		 return super.dispatchTouchEvent(me); // or false (it's what you want).
	        	 	}
	        	 else
	        	 	{
	        		 if (gestureScanner.onTouchEvent(me) && !hoscl.dispatchTouchEvent(me))
	            		 return true;
	            		 else
	            		 return super.dispatchTouchEvent(me); // or false (it's what you want)
	        	 	}
	         	}
	         else if(a!=0)
	         {
	        	 if (gestureScanner.onTouchEvent(me) && ((x2-x1)>=(y2-y1)) )
	        	 {
	        	 xs1 = String.valueOf(x1);
	        	 xs2 = String.valueOf(x2);
	        	 ys1 = String.valueOf(y1);
	        	 ys2 = String.valueOf(y2);
	        	 pc= String.valueOf(me.getPointerCount());
	        	 Log.d("pc", pc);
	        		 Log.d("xs1", xs1);
	        		 Log.d("xs2", xs2);
	        		 Log.d("ys1", ys1);
	        		 Log.d("ys2", ys2);
	        		 return true;
	        	 }
	        	 else
	 	        	return super.dispatchTouchEvent(me); // or false (it's what you want).
	          }
	         else
	        	return super.dispatchTouchEvent(me); // or false (it's what you want).
	         	
	         
	    }
	    }
