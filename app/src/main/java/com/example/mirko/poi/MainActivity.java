package com.example.mirko.poi;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Locale;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts1;

    private SensorManager mSensorManager;
    private  MySensorListener mSensorListener;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     *
     * Teständerung. Und noch eine Teständerung
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensorListener = new MySensorListener((TextView) findViewById(R.id.heading));
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);


        //Für das GPS
        final LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mloclistener = new MylocationListener((TextView) findViewById(R.id.location));

        //Erlaubnis muss abgefragt werden - gibt aber -1 zurück ???
        int check;
        check = checkCallingPermission("android.permission.ACCESS_FINE_LOCATION");
        //Toast.makeText(getApplicationContext(), check + " CODE", Toast.LENGTH_LONG).show();
        //

        //GPS
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mloclistener);

        //Points of Interest
        final ArrayList<MyPoiObject> myPoiObjectArrayList = new ArrayList<MyPoiObject>();

        Location loc1 = new Location("Fakultät Informatik");
        loc1.setLatitude(51.0257778);
        loc1.setLongitude(13.722597222222223);
        MyPoiObject mpo1 = new MyPoiObject(loc1, "Herzlich willkommen an der Fakultät Informatik. " +
                "Mit über 1800 Studierenden gehört die Fakultät Informatik an der Exzellenz-Universität " +
                "TU Dresden zu den größten Ausbildungsstätten für Informatik in Deutschland. " +
                "Hier gibt es 11 verschiedene Studiengänge, die mit Bachelor, Master oder Diplom " +
                "(inzwischen eine Besonderheit in Deutschland) abgeschlossen werden können");

        myPoiObjectArrayList.add(mpo1);

        Location loc2 = new Location("Alte Mensa");
        loc2.setLatitude(51.0266944);
        loc2.setLongitude(13.72653611111111);
        MyPoiObject mpo2 = new MyPoiObject(loc2, "3 Komplettgerichte, davon eins fleischlos, Auflauf & Gratin, " +
                "Pizza & Pasta, Grill & Wok, Topf & Terrine, Salat- und Dessertbuffet, " +
                "umfangreiches Cafeteria-Sortiment");

        myPoiObjectArrayList.add(mpo2);

        Location loc3 = new Location("Münchner Platz");
        loc3.setLatitude(51.0299944);
        loc3.setLongitude(13.721733333333333);
        MyPoiObject mpo3 = new MyPoiObject(loc3, "Das zwischen 1902 und 1907 errichtete Gebäude, das als " +
                "königlich-sächsisches Landgericht eröffnet wurde, diente in der Zeit des Nationalsozialismus, " +
                "während der sowjetischen Besatzung und der DDR-Diktatur bis 1956 als Gericht, Gefängnis " +
                "und zentrale Hinrichtungsstätte. Auf dem Hof des Gebäudekomplexes gab es eine Fallschwertmaschine, " +
                "die der Vollstreckung von Todesstrafen diente. Insgesamt kamen hier mehr als 1.300 Menschen ums Leben. " +
                "Etwa zwei Drittel der Opfer kamen aus dem Reichsprotektorat Böhmen und Mähren, " +
                "da die Exekutionen der Sondergerichte Prag und Brünn hier durchgeführt wurden." +
                "Am 15. Februar 1945 wurden beim Angriff auf Dresden Teile des Gebäudes durch " +
                "Bomben beschädigt oder zerstört.");

        myPoiObjectArrayList.add(mpo3);

        Location loc4 = new Location("Nürnberger Platz");
        loc4.setLatitude(51.0321111);
        loc4.setLongitude(13.726158333333334);
        MyPoiObject mpo4 = new MyPoiObject(loc4, "An der Nürnberger Straße entstanden von 1900 bis 1905 von " +
                "der Dresdner Baugesellschaft großbürgerliche Wohnhäuser. An der Nürnberger Straße in Höhe " +
                "Liebigstraße sollte ursprünglich ein Sakralbau entstehen, wofür auch ein ovaler Platz, das " +
                "heutige Nürnberger Ei, angelegt wurde.");

        myPoiObjectArrayList.add(mpo4);

        Location loc5 = new Location("Neue Mensa");
        loc5.setLatitude(51.0288806);
        loc5.setLongitude(13.731927777777777);
        MyPoiObject mpo5 = new MyPoiObject(loc5, "Aufgrund des schlechten baulichen Zustands sind die Mensa " +
                "und auch die Cafeteria geschlossen. Ersatzweise steht Ihnen unsere Übergangsmensa " +
                "„Zeltschlösschen“ an der Nürnberger Straße zur Verfügung:");

        myPoiObjectArrayList.add(mpo5);

        Location loc6 = new Location("Heeme");
        loc6.setLongitude(13.7525732);
        loc6.setLatitude(51.0621029);
        MyPoiObject mpo6 = new MyPoiObject(loc6, "Hier ist die Zentrale der Finsternis");
        myPoiObjectArrayList.add(mpo6);

        Location loc7 = new Location("McDoof");
        loc7.setLatitude(51.049857);
        loc7.setLongitude(13.739554);
        MyPoiObject mpo7 = new MyPoiObject(loc7, "McDoof Wilsdruffer Straße 19.");
        myPoiObjectArrayList.add(mpo7);

        //TextToSpeech Listener
        android.speech.tts.TextToSpeech.OnInitListener tts_listener;
        tts_listener = new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    tts1.setLanguage(Locale.GERMANY);
                }
            }
        };

        //TextToSpeech
        tts1 = new TextToSpeech(getApplicationContext(), tts_listener);


        //Trigger auf den aktuellen Ort
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Hier soll der aktuelle Ort und die Ausrichtung getriggert werden", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                //CHECK -> keine Ahnung
                int check1 = checkCallingPermission("android.permission.ACCESS_FINE_LOCATION");
                //String lo = Location.convert(mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude(), Location.FORMAT_DEGREES);
                //String la = Location.convert(mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude(), Location.FORMAT_DEGREES);
                //Toast.makeText(getApplicationContext(), "Länge: " + lo + " Breite: " + la, Toast.LENGTH_SHORT).show();

                //Stop, falls man sich den Text nicht anhören will
                if(tts1.isSpeaking()){
                    tts1.stop();
                }else {

                    //TODO Test, ob überhaupt GPS-Signal da ist -- zurzeit Absturz
                    if(mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

                        //Entfernung Berechnen
                        for(MyPoiObject mpo : myPoiObjectArrayList){
                            float dist = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).distanceTo(mpo.getLoc());

                            //wenn man weniger als 150m vom Ziel Entfernt ist, gibts ein Ergebnis
                            if(dist < 150){

                                //Ausrichtung/Orientation

                                float bearing = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).bearingTo(mpo.getLoc());
                                //Toast.makeText(getApplicationContext(), "bearing: " + bearing, Toast.LENGTH_LONG).show();
                                TextView tv = (TextView) findViewById(R.id.bearing);
                                tv.setText("Bearing: " + Float.toString((bearing + 360) % 360));

                                TextView tv2 = (TextView) findViewById(R.id.calc_heading);

                                tv2.setText("Calculated Heading: " + Float.toString(((bearing + 360) % 360) - mSensorListener.getDegree() ));

                                //

                                float calc_heading = Math.abs(((bearing + 360) % 360) - mSensorListener.getDegree());
                                //Check Orientation - 60°
                                if( (calc_heading > 330) || (calc_heading < 30)){
                                    Toast.makeText(getApplicationContext(), "Entfernung zu " + mpo.getLoc().getProvider() + ": " + dist + " m", Toast.LENGTH_SHORT).show();
                                    tts1.speak(mpo.getLoc().getProvider(), TextToSpeech.QUEUE_ADD, null);
                                    tts1.playSilence(750, TextToSpeech.QUEUE_ADD, null);
                                    tts1.speak("Info", TextToSpeech.QUEUE_ADD, null);
                                    tts1.playSilence(750, TextToSpeech.QUEUE_ADD, null);
                                    tts1.speak(mpo.getInfo(), TextToSpeech.QUEUE_ADD, null);
                                }

                            }
                        }
                    }
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //Listener für Sensoren
    public class MySensorListener implements SensorEventListener{

        TextView textView;
        float degree;

        public MySensorListener(TextView FormTextView){

            textView = FormTextView;
            //Toast.makeText(getApplicationContext(), "MySensorListener created", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSensorChanged(SensorEvent event){

            //angle around the z-axis
            degree = Math.round(event.values[0]);

            //Toast.makeText(getApplicationContext(), "Heading: " + Float.toString(degree), Toast.LENGTH_SHORT).show();
            textView.setText("Heading: " + Float.toString(degree));
        }

        public float getDegree(){
            return degree;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy){
            //not in use
        }
    }

    //Eigener Listener fürs GPS
    public class MylocationListener implements LocationListener{

        TextView textView;

        public MylocationListener(TextView FormTextView){
            textView = FormTextView;
        }

        @Override
        public void onLocationChanged(Location loc){
            loc.getLatitude();
            loc.getLongitude();

            //TEST
            //String text = "Meine position ist: " + loc.getLatitude() + " --- " + loc.getLongitude();
            //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            //textView.setText("Latitude: " + loc.getLatitude() + " Longitude: " + loc.getLongitude());

            //Sekundenschreibweise
            //String longi = Location.convert(loc.getLatitude(), Location.FORMAT_SECONDS);
            //String lati = Location.convert(loc.getLongitude(), Location.FORMAT_SECONDS);

            //Gradschreibweise
            String longi = Location.convert(loc.getLongitude(), Location.FORMAT_DEGREES);
            String lati = Location.convert(loc.getLatitude(), Location.FORMAT_DEGREES);

            textView.setText("Länge: " + longi + " Breite: " + lati);
        }

        @Override
        public void onProviderDisabled(String provider){
            Toast.makeText(getApplicationContext(), "GPS läuft nicht", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider){
            Toast.makeText(getApplicationContext(), "GPS läuft", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras){

        }
    }



    public class MyPoiObject{
        Location loc;
        String info;

        public MyPoiObject(Location l, String text){
            loc = l;
            info = text;
        }

        public Location getLoc(){
            return loc;
        }

        public String getInfo(){
            return info;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            System.exit(0);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.mirko.poi/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.mirko.poi/http/host/path")
        );

        mSensorManager.unregisterListener(mSensorListener);

        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


}
