package in.knowledgeportal.myclass.ccpt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by harsh on 04-06-2015.
 */
public class DownloadActivity extends Activity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*

        mProgressDialog = new ProgressDialog(DownloadActivity.this);
        mProgressDialog.setMessage("Currently downloading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        Log.i("Main", "finito onCreate");

        DownloadFile downloadFile = new DownloadFile();
        downloadFile.execute("http://dragon-nest.net/catalogo.pdf");

        */


        String file_path  = Environment.getExternalStorageDirectory().getAbsolutePath()+"/CCPT1/pdf/"+getString(R.string.app_name)+"/";
        String file_name = "test.pdf";
        // create a File object for the parent directory
        File wallpaperDirectory = new File(file_path);
        wallpaperDirectory.mkdirs();
        Log.v("directory", String.valueOf(wallpaperDirectory.isDirectory()));
        Log.v("app_name",getString(R.string.app_name));

    }

    private class DownloadFile extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... sUrl) {
            try {
                URL url = new URL(sUrl[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                Log.i("Main", "connected");
                // this will be useful so that you can show a typical 0-100% progress bar
                int fileLength = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());
                Log.i("Main", "input set");
                String file_path  = Environment.getExternalStorageDirectory().getAbsolutePath()+"/CCPT/";
                String file_name = "test.pdf";
                // create a File object for the parent directory
                File wallpaperDirectory = new File(file_path);
                Log.v("directory", String.valueOf(wallpaperDirectory.isDirectory()));
// have the object build the directory structure, if needed.
                wallpaperDirectory.mkdirs();
// create a File object for the output file
                File outputFile = new File(wallpaperDirectory, file_name);
// now attach the OutputStream to the file object, instead of a String representation
                FileOutputStream fos = new FileOutputStream(outputFile);

                Log.i("File", file_path);


                OutputStream output = new FileOutputStream(outputFile);
                Log.i("Main", "output set");

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            }catch(Exception e){
                Log.e("ERROR_TAG", e.getMessage());
                return "fine";
            }
            String s ="fine";
            Log.i("Main", "end download");
            return s;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
            Log.i("Main", "end preExecute");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mProgressDialog.dismiss();
            Log.i("Main", "end postExecute");
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            mProgressDialog.setProgress(progress[0]);
            Log.i("Main", "update...");
        }


    }
}
