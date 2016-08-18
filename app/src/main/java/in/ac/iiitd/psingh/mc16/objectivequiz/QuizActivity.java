package in.ac.iiitd.psingh.mc16.objectivequiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

   // private Button mTrueButton;
   // private Button mFalseButton;

    int count =0;
    private static final String TAG = "QuizActivity";
    TextView textview;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // mTrueButton = (Button) findViewById(R.id.TrueButton);
        if(savedInstanceState!= null){
       /*When rotation occurs
         */
            String state=savedInstanceState.getString("myString");
            count=savedInstanceState.getInt("myScore");
            textview = (TextView) findViewById(R.id.textViewer);
            textview.setText(state);
        } else {
            //When onCreate is called for the first time

            textview = (TextView) findViewById(R.id.textViewer);
            int random = getRandomNumberInRange(1, 1000);
            String s1=Integer.toString(random);
            String s2=s1.concat(" is a prime number?");

            textview.setText(s2);

        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void changeMessage(View v) {                                  //action event for next button
        TextView textview = (TextView) findViewById(R.id.textViewer);
        int t = 0;
        for (int i = 0; i < 1000; i++) {
            t = getRandomNumberInRange(1, 1000);
        }
        String s1=Integer.toString(t);
        String s2=s1.concat(" is a prime number?");
        textview.setText(s2);

    }

    public void Correct(View view) {                                               // action event for true button
        // Do something in response to button
        TextView textview = (TextView) findViewById(R.id.textViewer);
        CharSequence text = textview.getText();
        String temp = text.toString();                                                     //extracting the number from question string
        String[] s = temp.split(" ");
        String n = s[0];
        int prime = Integer.parseInt(n);

        Context context = getApplicationContext();

        int i, flag = 0;
        if(prime==1)
        {
        flag=1;
        }
        for (i = 2; i <= prime / 2; ++i) {
            // condition for nonprime number
            if (prime % i == 0) {
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
            count++;
            Toast.makeText(context, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Not Correct", Toast.LENGTH_SHORT).show();
        }
    }


    public void Incorrect(View view) {                                        //action event  for false button
        // Do something in response to button
        TextView textview = (TextView) findViewById(R.id.textViewer);
        CharSequence s = textview.getText();
        String temp = s.toString();
        String[] text = temp.split(" ");
        String n = text[0];
        int prime = Integer.parseInt(n);

        Context context = getApplicationContext();

        int i, flag = 0;
        if (prime == 1) {
            flag = 1;
        }
        for (i = 2; i <= prime / 2; ++i) {
            // condition for nonprime number
            if (prime % i == 0) {
                flag = 1;
                break;
            }
        }

        if (flag != 0) {
            count++;
            Toast.makeText(context, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Not Correct", Toast.LENGTH_SHORT).show();
        }
    }

    public void score(View view)                                                              //action event for score button
    {

        Context context = getApplicationContext();
        Toast.makeText(context, "Your score is "+count , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putCharSequence("myString",textview.getText());
        savedInstanceState.putInt("myScore",count);
        Log.i(TAG, "Inside onSaveInstance");
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Log.d(TAG, "Inside OnStart");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Quiz Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://in.ac.iiitd.psingh.mc16.objectivequiz/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Inside OnPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Inside OnREsume");

    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Quiz Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://in.ac.iiitd.psingh.mc16.objectivequiz/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        Log.d(TAG, "Inside OnSTop");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
