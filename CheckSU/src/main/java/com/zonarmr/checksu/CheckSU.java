package com.zonarmr.checksu;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CheckSU {
    public static boolean checkRootAccess(Context ctx) {
        Process suProcess = null;
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            suProcess = Runtime.getRuntime().exec("su");
            bw = new BufferedWriter(new OutputStreamWriter(suProcess.getOutputStream(), "UTF-8"));
            br = new BufferedReader(new InputStreamReader(suProcess.getInputStream()));
            bw.write("id\n");
            bw.write("exit\n");
            bw.flush();
            String currUid = br.readLine();
            if(currUid == null) {
                return false;
            }else if(!currUid.contains("uid=0")) {
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            return false;
        } finally{
            if(suProcess != null){
                try{
                    suProcess.destroy();
                    bw.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
