import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.fazecast.jSerialComm.SerialPort;

public class MainFrame extends JFrame implements ActionListener{


    JButton startButton;
    JButton stopButton;
    JLabel lblData;
    JTextField tbPort;

    String Text_1 = "START button activate";
    String Text_2 = "STOP button activate";

    SerialPort serialPort;

    DataListener dataListener;

    PLCComm plcComm;
    
    int ConnectStatus;

    MainFrame(){

        startButton = new JButton();
        startButton.setBounds(10, 10, 150, 30);
        startButton.setText("Start Com Port");
        startButton.addActionListener(this);

        stopButton = new JButton();
        stopButton.setBounds(170, 10, 150, 30);
        stopButton.setText("Stop Com Port");
        stopButton.addActionListener(this);

        lblData = new JLabel();
        lblData.setBounds(10, 90, 310, 30);
        lblData.setText("DEFAULT TEXT");
        lblData.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        lblData.setHorizontalTextPosition(JLabel.CENTER);
        lblData.setVerticalTextPosition(JLabel.CENTER);
        lblData.setFocusable(false);
        lblData.setVerticalAlignment(JLabel.CENTER);
        lblData.setHorizontalAlignment(JLabel.CENTER);

        tbPort = new JTextField();
        tbPort.setBounds(10, 50, 310, 30);
        tbPort.setHorizontalAlignment(JTextField.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(345, 170 );
        this.setVisible(true);

        this.add(startButton);
        this.add(stopButton);

        this.add(lblData);

        this.add(tbPort);

        plcComm = new PLCComm();

        ConnectStatus = plcComm.Connect("192.168.1.25", 0, 1);
    }

    //public void DataReceived(Object sender, Event)

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()== startButton){

            System.out.println(Text_1);
            lblData.setText(Text_1);

            serialPort = SerialPort.getCommPort(tbPort.getText());
            dataListener = new DataListener(lblData, plcComm);
            serialPort.addDataListener(dataListener);
            serialPort.openPort();

        }

        if(e.getSource()== stopButton){

            System.out.println(Text_2);
            lblData.setText(Text_2);

            serialPort.closePort();

        }
        
    }
}
