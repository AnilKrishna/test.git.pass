package com.target.plugins;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;



import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;



public class DemoPlugin extends Plugin {
 public final String ACTION_SEND_SMS = "SendSMS";
 
 @Override
 public PluginResult execute(String action, JSONArray arg1, String callbackId) {
  PluginResult result = new PluginResult(Status.INVALID_ACTION);
  
  if (action.equals(ACTION_SEND_SMS)) {
   try {
    String phoneNumber = arg1.getString(0);
    String message = arg1.getString(1);
    sendSMS(phoneNumber, message);
    result = new PluginResult(Status.OK);
   }
   catch (JSONException ex) {
    result = new PluginResult(Status.ERROR, ex.getMessage());
   }   
  }
  
  return result;
 }

 private void sendSMS(String phoneNumber, String message) {
  SmsManager manager = SmsManager.getDefault();
  
        PendingIntent sentIntent = PendingIntent.getActivity((Context) this.ctx, 0, new Intent(), 0);  
  
  manager.sendTextMessage(phoneNumber, null, message, sentIntent, null);
 }

}