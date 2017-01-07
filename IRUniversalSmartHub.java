package Classes;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by MobileDirect on 1/7/2017.
 */


public class IRUniversalSmartHub {
    private Context context;


    public IRUniversalSmartHub(Context context) {
        this.context = context;
    }


    public void sendIR(String ip_address, String code_to_send) {
        new SendIR(ip_address, code_to_send, this.context).execute();

    }

    public void receiveIR(String ip_address, EditText editText) {
        new HubReceive(ip_address, this.context, editText).execute();
    }

    public void getIpList() {
        new FindHubs().execute();
    }



    public class SendIR extends AsyncTask<String, Void, String> {
        String ip;
        String hubSendString;
        Context context;

        public SendIR(String ip, String hubSendString, Context context) {
            this.hubSendString = hubSendString;
            this.ip = ip;
            this.context = context;
        }


        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://" + ip + "/" + hubSendString);

                urlConnection = (HttpURLConnection) url
                        .openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);

                int data = isw.read();
                while (data != -1) {
                    char current = (char) data;
                    data = isw.read();
                    System.out.print(current);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


    public class HubReceive extends AsyncTask<String, Void, String> {
        String ip;
        Context context;
        EditText et;

        public HubReceive(String ip, Context context, EditText et) {
            this.ip = ip;
            this.context = context;
            this.et = et;
        }


        @Override
        protected String doInBackground(String... params) {
            String response = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://" + ip + "/");
                urlConnection = (HttpURLConnection) url
                        .openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);

                int data = isw.read();
                while (data != -1) {
                    char current = (char) data;
                    data = isw.read();
                    response += current;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                et.setText(result.substring(23, result.indexOf("</html>") - 1).replaceAll("\n", ""));
            } catch (Exception e) {
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


    public class FindHubs extends AsyncTask<Void, Integer, String> {

        ArrayList<String> ipList = new ArrayList<>();


        protected void onPreExecute() {
        }

        protected String doInBackground(Void... arg0) {
            DhcpInfo d;
            WifiManager wifii;

            wifii = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            d = wifii.getDhcpInfo();
            String connections = "";
            InetAddress host;
            try {
                host = InetAddress.getByName(intToIp(d.dns1));
                byte[] ip = host.getAddress();

                for (int i = 1; i <= 254; i++) {
                    ip[3] = (byte) i;
                    InetAddress address = InetAddress.getByAddress(ip);

                    if (address.isReachable(100)) {
                        if (address.getHostName().contains("ESP_")) {
                            ipList.add(address.getHostAddress());
                        }
                        connections += address + "\n";
                    } else if (!address.getHostAddress().equals(address.getHostName())) {
                    }

                }
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ipList.toString();
        }

        protected void onProgressUpdate(Integer... a) {
        }

        protected void onPostExecute(String result) {


            //do what you want with list of ip addresses (ipList)


        }
    }

    public String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                ((i >> 24) & 0xFF);
    }
}
