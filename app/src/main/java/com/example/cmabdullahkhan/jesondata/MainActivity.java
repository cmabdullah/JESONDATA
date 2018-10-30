package com.example.cmabdullahkhan.jesondata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView show_data;
    String t1 = "";

    String n1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_data = (TextView) findViewById(R.id.showdata);
        fetchingata();

    }

    void fetchingata(){

        String myURL = "http://mrubel.com/tuntuninews/api/gettingnews.php" ;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                for(int i = 0; i<response.length(); i++){


                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);

                        t1 = t1 + jsonObject.getString("title")+ "\n\n";

                        System.out.println("Parsed data : "+t1);

                        n1 = n1 + jsonObject.getString("news")+ "\n\n";


                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }





                show_data.setText(t1+n1);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley error ", error);
            }
        });
    /****
        t1 = "hi cm";
        n1 = "Hi abdullah";

        show_data.setText(t1+n1);
****/


        com.example.cmabdullahkhan.jesondata.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        Toast.makeText(getApplicationContext(), "Data parse successfully", Toast.LENGTH_SHORT).show();

    }
}
