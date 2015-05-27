package com.foxiczek.liskbpet.baterkaapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.hardware.Camera.*;
import android.content.pm.PackageManager;
import android.content.Context;
import android.hardware.Camera;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MyActivity extends ActionBarActivity {

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button controlButtonON = (Button) findViewById(R.id.buttonControlON);
        Button controlButtonOFF = (Button) findViewById(R.id.buttonControlOFF);
        Button controlButtonEXIT = (Button) findViewById(R.id.buttonExit);

        controlButtonON.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lightOn();

            }
        });
        controlButtonOFF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lightOff();

            }
        });
        controlButtonEXIT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                AlertDialog alertDialog = new AlertDialog.Builder(MyActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Do you really want to EXIT??");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                lightOff();
                                System.exit(0);
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "YES&Light",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        });
                alertDialog.show();

            }
        });
    }


    public void lightOn(){
        Camera c = null;
        try {
            c = Camera.open();
            Parameters param = c.getParameters();
            String statusFlash = String.valueOf(param.getFlashMode());
            param.setFlashMode(Parameters.FLASH_MODE_TORCH);
            c.setParameters(param);
            c.release();
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
    }

    public void lightOff(){
        Camera c = null;
        try {
            c = Camera.open();
            Parameters param = c.getParameters();
            String statusFlash = String.valueOf(param.getFlashMode());
            param.setFlashMode(Parameters.FLASH_MODE_OFF);
            c.setParameters(param);
            c.release();
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
    }
}
