import java.nio.charset.StandardCharsets;

import javax.swing.JLabel;
import com.fazecast.jSerialComm.*;

public class DataListener implements SerialPortDataListener {
    JLabel _label;
    PLCComm _plcComm;

    public DataListener(JLabel label, PLCComm plcComm) {
        super();
        _label = label;
        _plcComm = plcComm;
    }

    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
    
    @Override
    public void serialEvent(SerialPortEvent event)
    {
        System.out.println("???");
        try
        {
            Thread.sleep(10);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        
        byte[] buffer = new byte[event.getSerialPort().bytesAvailable()];
        event.getSerialPort().readBytes(buffer, buffer.length);
        String s = new String(buffer, StandardCharsets.UTF_8);
        _label.setText(s);

        //Vyhodnocení dat

        _plcComm.SendData(0, 4);
    }
}
