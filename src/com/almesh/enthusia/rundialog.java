package com.almesh.enthusia;

import android.app.ProgressDialog;
import android.os.AsyncTask;

//new SomeTask(0).execute();

/** Inner class for implementing progress bar before fetching data **/
    class rundialog extends AsyncTask<Void, Void, Integer> 
    {
        
        @Override
        protected void onPreExecute()
        {
        
    }

    @Override
    protected Integer doInBackground(Void... params) 
    {
        //Task for doing something 

        return 0;
    }

    @Override
    protected void onPostExecute(Integer result)
        {

        if(result==0)


           {
    //do some thing


  }
// after completed finished the progressbar
        
    }
    }