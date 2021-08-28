import java.util.Timer;
import java.util.TimerTask;

public class PLCComm {

    S7Client Client = new S7Client();

    byte[] DataBuffer = new byte[1];

    //Timer SendTimer = new Timer();
    

    public int Connect(String IPAddress, int Rack, int Slot){
        int status;
        
        status = Client.ConnectTo(IPAddress,Rack,Slot);

        // if(Client.Connected){
        //     SendTimer.scheduleAtFixedRate(new TimerTask() {
        //         @Override
        //         public void run() {
        //           SendData();
        //         }
              
        //     }, 0, 100);
        //}

        return status;
    }
    
    
    public void SendData(int ByteOffset, int BitOffset){

        
            S7.SetBitAt(DataBuffer,0 ,BitOffset , true);
        
        
        Client.WriteArea(S7.S7AreaDB, 50, ByteOffset, 1, DataBuffer);
    }

}
