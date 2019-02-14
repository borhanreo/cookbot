package w3engineers.com.cookerbot.activity;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import w3engineers.com.cookerbot.R;
import w3engineers.com.cookerbot.bluetooth.DeviceListActivity;
import w3engineers.com.cookerbot.controller.OnItemSelectCallBackListener;
import w3engineers.com.cookerbot.dbhelper.DBHandler;
import w3engineers.com.cookerbot.model.RecipeModel;
import w3engineers.com.cookerbot.singleton.ProjectSingleton;


public class RecipeActivity extends AppCompatActivity implements OnItemSelectCallBackListener {
    private String TAG = "borhan RecipeActivity";

    private static final int MESH_PORT = 1166;
    private static String address;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private RecipeActivity.ConnectedThread mConnectedThread;
    final int handlerState = 0;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    TextView readSerialData;
    Handler bluetoothIn;
    private StringBuilder recDataString = new StringBuilder();
    String dataInPrint;
    int dataLength;
    DBHandler db = new DBHandler(this);
    Dialog dialog = null;
    TextView show_recipe_status = null,selected_option;
    private Button ovenOn,ovenOff,oil,water,onion,chili,salt,mixed_spice,ch_pot_other,spud_grinding,create,ch_pot_other2;
    private String globalvalue="", setStr="";
    private TextView recipeName, time;
    private EditText gradients_list;
    private Context context;
    long startTime = 0;
    long globalSecond=0;
    long previousGlobalSecond=0;
    long lastSecond=0;
    boolean timerRun = true;
    long currentPowerSecond=0, previousPowerSecond=0;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            if(timerRun)
            {
                long millis = System.currentTimeMillis() - startTime;
                //long millis = globalSecond-startTime;//System.currentTimeMillis() - startTime;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                globalSecond++;
                time.setText(String.format("%d:%02d", minutes, seconds));
                //Log.d(TAG,String.format("%2d:%02d", minutes, seconds)+ "  "+globalSecond);
                timerHandler.postDelayed(this, 1000);
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        time = (TextView)findViewById(R.id.time);
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
        context = this;
        Intent intent = getIntent();
        address = intent.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        ProjectSingleton.getInstance(context).setAddress(address);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ovenOn = (Button)findViewById(R.id.on);
        ovenOff = (Button)findViewById(R.id.off);
        oil = (Button)findViewById(R.id.oil);
        water = (Button)findViewById(R.id.water);
        onion = (Button)findViewById(R.id.onion);
        chili = (Button)findViewById(R.id.chili);
        salt = (Button)findViewById(R.id.salt);
        mixed_spice = (Button)findViewById(R.id.mixed_spice);
        ch_pot_other = (Button)findViewById(R.id.ch_pot_other);
        ch_pot_other2 = (Button)findViewById(R.id.ch_pot_other_2);
        spud_grinding = (Button)findViewById(R.id.spud_grinding);
        create = (Button)findViewById(R.id.create);

        selected_option= (TextView) findViewById(R.id.selected_option);
        recipeName = (EditText) findViewById(R.id.recipe_name);
        gradients_list = (EditText) findViewById(R.id.gradients_list);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        ch_pot_other2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                String oilApiStore=":d`"+(currentSecond-lastSecond)+":s#8";
                String oilApi=":s#8";
                globalvalue=globalvalue+oilApiStore;

                String appendStr="Being CH/PO/OT 2: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+oilApi+"\n");
                lastSecond=2;
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipename_text = recipeName.getText().toString().trim();
                String gradients_list_str = gradients_list.getText().toString();
                if(recipename_text.isEmpty() || recipename_text.length() == 0 || recipename_text.equals("") || recipename_text == null)
                {
                    //EditText is empty
                    Toast.makeText(context,"Recipe name could not be empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(context,gradients_list_str,Toast.LENGTH_LONG).show();
                    db.addRecipe(new RecipeModel(1, recipename_text, globalvalue,gradients_list_str));
                    Toast.makeText(context, "Recipe successfully created ", Toast.LENGTH_LONG).show();
                    //EditText is not empty
                    //Log.d(TAG,"c "+globalvalue);
                    globalvalue = "";
                }

            }
        });
        ovenOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                //String ovenOnApi=":d`"+currentSecond+":b=1";
                String ovenOnApi=":b=1";
                Log.d(TAG,""+ovenOnApi);
                globalvalue=globalvalue+ovenOnApi;
                String appendStr="Oven On: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+ovenOnApi+"\n");
                //ovenOn.setBackgroundColor(Color.GREEN );
            }
        });
        ovenOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                String ovenOffApiStore=":d`"+currentSecond+":b|1";
                String ovenOffApi=":b|1";
                globalvalue=globalvalue+ovenOffApiStore;
                String appendStr="Oven Off: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+ovenOffApi+"\n");

            }
        });
        oil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                String oilApiStore=":d`"+(currentSecond-lastSecond)+":o+5";
                String oilApi=":o+5";
                globalvalue=globalvalue+oilApiStore;

                String appendStr="Being Oil: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+oilApi+"\n");
                lastSecond=5;
            }
        });
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                String oilApiStore=":d`"+(currentSecond-lastSecond)+":w^1";
                String oilApi=":w^1";
                globalvalue=globalvalue+oilApiStore;
                String appendStr="Being Water: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+oilApi+"\n");
                lastSecond=1;
            }
        });
        onion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                String oilApiStore=":d`"+(currentSecond-lastSecond)+":s#1";
                String oilApi=":s#1";
                globalvalue=globalvalue+oilApiStore;

                String appendStr="Being Onion: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+oilApi+"\n");
            }
        });
        chili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                String oilApiStore=":d`"+(currentSecond)+":s#2";
                String oilApi=":s#2";
                globalvalue=globalvalue+oilApiStore;
                String appendStr="Being Chili: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+oilApi+"\n");
                lastSecond=2;
            }
        });
        salt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                String oilApiStore=":d`"+(currentSecond-lastSecond)+":s#3";
                String oilApi=":s#3";
                globalvalue=globalvalue+oilApiStore;
                String appendStr="Being Salt: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+oilApi+"\n");
                lastSecond=2;
            }
        });
        mixed_spice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                String oilApiStore=":d`"+(currentSecond-lastSecond)+":s#9";
                String oilApi=":s#9";
                globalvalue=globalvalue+oilApiStore;
                String appendStr="Being Mixed spice: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+oilApi+"\n");
                lastSecond=2;
            }
        });
        ch_pot_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                String oilApiStore=":d`"+(currentSecond-lastSecond)+":s#7";
                String oilApi=":s#7";
                globalvalue=globalvalue+oilApiStore;

                String appendStr="Being CH/PO/OT 1: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+oilApi+"\n");
                lastSecond=2;
            }
        });
        spud_grinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentSecond = globalSecond-previousGlobalSecond;
                previousGlobalSecond = globalSecond;
                String oilApiStore=":d`"+(currentSecond-lastSecond)+":t*1";
                String oilApi=":t*1";
                globalvalue=globalvalue+oilApiStore;
                String appendStr="Spud Grinding: ";
                setStr = setStr+appendStr;
                selected_option.setText(setStr);
                mConnectedThread.write("A"+oilApi+"\n");
                lastSecond=12;
            }
        });


        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                String readMessage = (String) msg.obj;                                                                // msg.arg1 = bytes from connect thread
                Log.d(TAG, "  m " + readMessage);

            }
        };

        readSerialData = (TextView) findViewById(R.id.readSerialData);

        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {

                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    Log.d(TAG," rrr "+readMessage.toString());
                    recDataString.append(readMessage);
                    int endOfLineIndex = recDataString.indexOf("~");
                    if (endOfLineIndex > 0) {

                        dataInPrint = recDataString.substring(0, endOfLineIndex);
                        dataLength = dataInPrint.length();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (recDataString.charAt(0) == '#') {
                                    try {
                                        String isCompleted = recDataString.substring(1, 16);
                                        //Log.d(TAG,"borhan 1 "+isCompleted);
                                        //Log.d(TAG,"borhan 1 "+isCompleted.length());
                                    /*String sensor1 = recDataString.substring(6, 10);
                                    String sensor2 = recDataString.substring(11, 15);
                                    String sensor3 = recDataString.substring(16, 20);
                                    //Log.d(TAG," temp "+sensor0);
                                    sensorView0.setText(" Current Temperature = " + sensor0 + " C");
                                    globalTemp = sensor0 + "";*/
                                        if (show_recipe_status != null) {
                                            show_recipe_status.setText("");
                                            show_recipe_status.setText(recDataString.toString());
                                        }
                                        if (isCompleted.equals(" COOK COMPLETED")) {
                                            //Log.d(TAG,"borhan 2 "+isCompleted);
                                            dialog.dismiss();
                                        }
                                    } catch (Exception e) {

                                    }
                                    readSerialData.setText("");
                                    readSerialData.setText(recDataString.toString());
                                    if (show_recipe_status != null) {
                                        show_recipe_status.setText("");
                                        show_recipe_status.setText(recDataString.toString());
                                    }
                                }

                                recDataString.delete(0, recDataString.length());
                                //Log.d(TAG, "borhan3 " + recDataString.toString());
                            }
                        });
                        //Log.d(TAG, " size " + userList.size());
                        //sendData();
                        dataLength = 0;
                        dataInPrint = " ";
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
        checkBTState();

    }



    public void uu() {
            /*try {
                if(message.equals("1.11"))
                {
                    mConnectedThread.write("1");
                }else if(message.equals("0.00"))
                {
                    mConnectedThread.write("0");
                }
            }catch (Exception e)
            {

            }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDeviceAddress();

    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    private void getDeviceAddress() {
        //Intent intent = getIntent();
        //address = intent.getStringExtra(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        connectbt();
        /*address = ProjectSingleton.getInstance(context).getAddress();
        BluetoothDevice device = btAdapter.getRemoteDevice(address);
        Log.d(TAG, " address " + device.getAddress());
        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }
        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                //insert code to deal with this
            }
        }
        mConnectedThread = new RecipeActivity.ConnectedThread(btSocket);
        mConnectedThread.start();
        mConnectedThread.write("x");*/

    }

    private void connectbt()
    {
        address = ProjectSingleton.getInstance(context).getAddress();
        BluetoothDevice device = btAdapter.getRemoteDevice(address);
        Log.d(TAG, " address " + device.getAddress());
        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }
        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                //insert code to deal with this
            }
        }
        mConnectedThread = new RecipeActivity.ConnectedThread(btSocket);
        mConnectedThread.start();
        mConnectedThread.write("x");
    }
    private void connectbtWith(String input)
    {

        address = ProjectSingleton.getInstance(context).getAddress();
        BluetoothDevice device = btAdapter.getRemoteDevice(address);
        Log.d(TAG, " address " + device.getAddress());
        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }
        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                //insert code to deal with this
            }
        }
        mConnectedThread = new RecipeActivity.ConnectedThread(btSocket);
        mConnectedThread.start();
        mConnectedThread.write(input);


    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        public void run() {
            byte[] buffer = new byte[256];
            int bytes;
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);            //read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application

                //Toast.makeText(getBaseContext(), "Connection Failure", Toast.LENGTH_LONG).show();
                //finish();
                //handler.removeCallbacks(myRunnable);
                //timerHandler.removeCallbacks(timerRunnable);
                connectbtWith(input);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        try {
            //Don't leave Bluetooth sockets open when leaving activity
            btSocket.close();
        } catch (IOException e2) {
            //insert code to deal with this
        }
    }

    //Checks that the Android device Bluetooth is available and prompts to be turned on if off
    private void checkBTState() {

        if (btAdapter == null) {
            Toast.makeText(getBaseContext(), "Device does not support bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    @Override
    public void back(int id, String name, String api, String gradients_list) {

    }
}
