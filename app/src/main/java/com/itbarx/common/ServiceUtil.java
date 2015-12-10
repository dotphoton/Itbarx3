package com.itbarx.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.apache.http.conn.util.InetAddressUtils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;



public class ServiceUtil {

	public static void changeWiFiStatus(Context context, boolean status)
	{
		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		wifiManager.setWifiEnabled(status);
		
	}
	
	
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public static boolean isWifiConnection(Context context){
		boolean haveConnectedWifi = false;
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mWifi.isConnected()) {
			haveConnectedWifi =true;
		}
		return haveConnectedWifi;
	}
	
	public static boolean haveWiFiNetworkConnection(Context context) {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;

		}
		return haveConnectedWifi || haveConnectedMobile;
	}

	public static boolean haveMobileNetworkConnection(Context context) {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {

			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

	public static String EncodeGZipStream(InputStream stream) {
		String responseStr = "";
		try {

			GZIPInputStream gzip = new GZIPInputStream(stream);

			byte[] buff = new byte[1024];
			byte[] emptyBuff = new byte[1024];
			StringBuffer unGzipRes = new StringBuffer();

			int byteCount = 0;
			while ((byteCount = gzip.read(buff, 0, 1024)) > 0) {

				unGzipRes.append(new String(Arrays.copyOf(buff, byteCount),
						"utf-8"));
				System.arraycopy(emptyBuff, 0, buff, 0, 1024);
			}

			responseStr = unGzipRes.toString();

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return responseStr;

	}
	public static String EncodeGZipStreamLargeData(InputStream stream) {
		String responseStr = "";
		try {

			GZIPInputStream gzip = new GZIPInputStream(stream);

			
			/*
			int bytesRead;
			long fileLength = smbFile.length();
			int i = 0;
			while ((bytesRead = smbfis.read(buffer)) != -1) 
			{
				int progress = (int) (((long) ++i * bytesRead) / fileLength) * 10;
				System.out.println(progress);

				if (progress > 10)
					progress = 10;

				fos.write(buffer, 0, bytesRead);
			}
			
			*/
			
			
			byte[] buff = new byte[1024];
			byte[] emptyBuff = new byte[1024];
			StringBuffer unGzipRes = new StringBuffer();

			int byteCount = 0;
			while ((byteCount = gzip.read(buff, 0, 1024)) > 0) {

				unGzipRes.append(new String(Arrays.copyOf(buff, byteCount),
						"utf-8"));
				System.arraycopy(emptyBuff, 0, buff, 0, 1024);
			}

			responseStr = unGzipRes.toString();
			
			/*
			byte[] bytes = new byte[1024*36];
			int numRead = 0;
			StringBuffer stringBuffer = new StringBuffer();
			while ((numRead = gzip.read(bytes)) >= 0) {
				stringBuffer.append(new String(bytes, 0, numRead));
			}
			
			responseStr = stringBuffer.toString();
			*/

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return responseStr;

	}
	
	
    public static boolean hasActiveInternetConnection(Context context,String URL) {
        if (isNetworkAvailable(context)) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL(URL).openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500); 
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e("LOG_TAG", "Error checking internet connection", e);
            }
        } else {
            Log.d("LOG_TAG", "No network available!");
        }
        return false;
    }
	

	//US-ASCII
	public static String EncodeASCIIInputStream(InputStream stream) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream, "US-ASCII"), 8);

			String line = "";

			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			stream.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return sb.toString();
	}
	
	public static String EncodeUTF8InputStream(InputStream stream) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream, "UTF8"), 8);

			String line = "";

			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			stream.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		Log.e("service response read",ex.getMessage());
		}
		return sb.toString();
	}

	public static String getWifiSSIDName(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifiManager.getConnectionInfo();
		return info.getSSID();
	}

	// Bluetooth

	public static Boolean enableBluetooth() {
		Boolean result = false;
		BluetoothAdapter bluetoothAdapter;
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (bluetoothAdapter != null) {

			if (!bluetoothAdapter.isEnabled()) {
				result = bluetoothAdapter.enable();
				while(bluetoothAdapter.getState() != BluetoothAdapter.STATE_ON)
				{
					
				}
			}

		}
		
		
		return result;
	}

	public static Boolean disableBluetooth() {
		Boolean result = false;
		BluetoothAdapter bluetoothAdapter;
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (bluetoothAdapter != null) {

			if (bluetoothAdapter.isEnabled()) {
				if (bluetoothAdapter.isDiscovering()) {

				} else {
					result = bluetoothAdapter.disable();
				}
			}
		}
		return result;

	}

	public static Set<BluetoothDevice> getPairedDevices() {
		BluetoothAdapter bluetoothAdapter;
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		enableBluetooth();
		
		return bluetoothAdapter.getBondedDevices();
		
	}

	

	
	// Bluetooth
	
	// IP Address IPv4
	public static String getIPAddress() {
		try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress().toUpperCase();
                        boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);         
                            if (isIPv4) 
                                return sAddr;
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
	}
}
