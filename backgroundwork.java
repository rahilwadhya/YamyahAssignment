package com.example.personal.yamyah;






                        import android.app.ProgressDialog;
                        import android.content.Context;
                        import android.os.AsyncTask;
                        import android.util.Log;

                        import java.io.BufferedReader;
                        import java.io.BufferedWriter;
                        import java.io.IOException;
                        import java.io.InputStream;
                        import java.io.InputStreamReader;
                        import java.io.OutputStream;
                        import java.io.OutputStreamWriter;
                        import java.net.MalformedURLException;
                        import java.net.URL;

                        import javax.net.ssl.HttpsURLConnection;

/**
 * Created by appu on 22/7/17.
 */
public class backgroundwork extends AsyncTask{
    Context cn;
    String result="";
    //public AsyncResponse delegate = null;
    ProgressDialog progress;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progress.setTitle("Connecting Server");
        progress.setMessage("Please wait util authentication ");
        progress.setIndeterminate(true);
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    backgroundwork(Context cn1,ProgressDialog pd){
        cn=cn1;
        progress=pd;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        try {

            try {

                //Toast.makeText(cn.getApplicationContext(), "m1 pass", Toast.LENGTH_LONG).show();

                URL url;

                    url=new URL("https://appukck.000webhostapp.com/testapp/con.php");

                HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //Toast.makeText(cn.getApplicationContext(),"m2 pass", Toast.LENGTH_LONG).show();

                bufferedWriter.write(params[0].toString());
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                result = "";
                String line = "";
                //Toast.makeText(cn.getApplicationContext(), "m3 pass", Toast.LENGTH_LONG).show();
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d("result", result);
                //Toast.makeText(cn.getApplicationContext(), result, Toast.LENGTH_LONG).show();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                //Toast.makeText(cn.getApplicationContext(), e.getStackTrace().toString(), Toast.LENGTH_LONG).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(cn.getApplicationContext(), e.getStackTrace().toString(), Toast.LENGTH_LONG).show();
        }

        return result;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progress.dismiss();
    }
}
