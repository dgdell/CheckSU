package com.zonarmr.checksu;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class CheckSU{

    private static Process p;
    private static DataOutputStream os;
    public static boolean checkRootAccess(Context ctx) {
        Process suProcess = null;
        BufferedWriter bw = null;
        BufferedReader br;
        try {
            suProcess = Runtime.getRuntime().exec("su");
            bw = new BufferedWriter(new OutputStreamWriter(suProcess.getOutputStream(), StandardCharsets.UTF_8));
            br = new BufferedReader(new InputStreamReader(suProcess.getInputStream()));
            bw.write("id\n");
            bw.write("exit\n");
            bw.flush();
            String currUid = br.readLine();
            if(currUid == null) {
                return false;
            }else return currUid.contains("uid=0");
        } catch (Exception e) {
            return false;
        } finally{
            if(suProcess != null){
                try{
                    suProcess.destroy();
                    assert bw != null;
                    bw.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
