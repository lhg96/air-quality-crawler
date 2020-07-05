package app.gui;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import app.service.Service;

public class MainFrame extends JFrame  implements ActionListener {

	private static final long serialVersionUID = 1L;
	public  static MainFrame mainUI;
	public  static Service service;


	int row = 20;
	int col = 40;
	
	String DEFAULT_KEY  = 
			"ServiceKey=Ir8wq7VY661b0Ka0RfCj%2F7xNbzv8f%2FSOqIwKcqM3kdsdhEmBRa1TZfGgamQZsoLt4ZSGeACbVtCwn9v90lqEhQ%3D%3D";
	//String KEY_2 ="ServiceKey=I011ufRnTJGMZdreKe1KVTVrW%2BM9pxunMj20BuMxVw5hgaID9sM0ZqUIaIers9Nhky%2B55YGD1ssEJNfEdYYW7w%3D%3D";//세종 도담 
	
	String DEFAULT_COUNT = 
			"numOfRows=100";
	String DEFAULT_LOCAL = 
			"addr=";//+대전	
	String DEFAULT_SIDO =
			"sidoName=";//+대전
	String DEFAULT_VERSION =
			"ver=1.0";  //pm2.5
	
	String DEFAULT_URL_1 = 
			"http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList?";
	String DEFAULT_URL_2 = 
			"http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?";
	String DEFAULT_URL_3 = 
			"http://arimapi.appspot.com/api/device";

	JTextField textField1 = new JTextField(DEFAULT_URL_1+DEFAULT_COUNT+"&"+DEFAULT_KEY+"&"+DEFAULT_VERSION+"&"+DEFAULT_LOCAL,col);//DEFAULT_KEY  
	JTextField textField2 = new JTextField(DEFAULT_URL_2+DEFAULT_COUNT+"&"+DEFAULT_KEY+"&"+DEFAULT_VERSION+"&"+DEFAULT_SIDO,col);
	JTextField textField3 = new JTextField(DEFAULT_URL_3,col);
	
	JButton BTN_0 = new JButton("Load");
	JButton BTN_1 = new JButton("Crawling");
	JButton BTN_2 = new JButton("Save");
	
	JButton BTN_3 = new JButton("Load");
	JButton BTN_4 = new JButton("Crawling");
	JButton BTN_5 = new JButton("Save");
	JButton BTN_6 = new JButton("update");
	JButton BTN_7 = new JButton("exit");

	JTextArea textArea = new JTextArea(row, col);
	int loggerMaxLine = 50;

	
	public MainFrame(String title) {
		initComponents(title);
		service = new Service();
		mainUI = this;
	}

	private void initComponents(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		// TA_reader.setEditable(false);		
		JPanel north = new JPanel(new BorderLayout());
		JPanel northWest 	= new JPanel(new GridLayout(3,1));
		JPanel northCenter 	= new JPanel(new GridLayout(3,1));
		JPanel northEast 	= new JPanel(new GridLayout(3,3));		
		
		BTN_0.addActionListener(this);
		BTN_1.addActionListener(this);
		BTN_2.addActionListener(this);
		BTN_3.addActionListener(this);
		BTN_4.addActionListener(this);
		BTN_5.addActionListener(this);
		BTN_6.addActionListener(this);
		BTN_7.addActionListener(this);
		
		//labels
		northWest.add(new JLabel("측정소목록"));
		northWest.add(new JLabel("실시간정보"));
		northWest.add(new JLabel("서버업데이트"));
		
		//textfeild
		northCenter.add(textField1);
		northCenter.add(textField2);
		northCenter.add(textField3);
		
		//buttons
		northEast.add(BTN_0);
		northEast.add(BTN_1);
		northEast.add(BTN_2);
		northEast.add(BTN_3);
		northEast.add(BTN_4);
		northEast.add(BTN_5);
		northEast.add(BTN_6);
		northEast.add(BTN_7);
		
		north.add(northEast, BorderLayout.EAST);
		north.add(northCenter, BorderLayout.CENTER);
		north.add(northWest, BorderLayout.WEST);
		
		this.add(north, BorderLayout.NORTH);

		textArea.setFont(new Font("Serif", Font.ITALIC, 12));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane center = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		center.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		center.setViewportView(textArea);
		this.add(center, BorderLayout.CENTER);
	}	


	
	public void appendMessage(String message, int type) {
		String[] lines = textArea.getText().split("\n");
		if (lines == null || lines.length == 0)
			return;
		// ArrayUtils.reverse(lines);

		int lineIndex = lines.length;
		if (lines.length > loggerMaxLine)
			lineIndex = loggerMaxLine;

		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dayStr = dayTime.format(new Date(time));
		
		String newMessage;
		if(type==0) {
			newMessage = message + "\n";
		}else {
			newMessage = dayStr + " " + message + "\n";
		}
		for (int i = 0; i < lineIndex; i++) {
			String str = lines[i].trim();
			if (str != null && !str.isEmpty())
				newMessage += str;
			if (i < lineIndex - 1)
				newMessage += "\n";
		}
		textArea.setText(newMessage);

		textArea.setSelectionStart(0);
		textArea.setSelectionEnd(0);
	}

	StringBuffer stringbuffer = new StringBuffer();

	int index = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
		//String command = e.getActionCommand();
		JButton actionBTN = (JButton) e.getSource();		
		appendMessage("Button pressed:"+actionBTN.getActionCommand(), 0);
		
		//load button
		if (actionBTN==BTN_0) {
			
		}else if (actionBTN==BTN_1) {			
			//측정소 정보 가져오기
			String url = textField1.getText();		
			//appendMessage(url, 1);
			try {
				MainFrame.service.getStationList(url);
			}catch(Exception eo) {
				appendMessage(eo.toString(), 0);
			}
		}else if (actionBTN==BTN_2) {
			//save button
			try {
				MainFrame.service.saveStationList();
			}catch(Exception eo) {
				appendMessage(eo.toString(), 0);
			}
		}else if (actionBTN==BTN_3) {
			//load
		}else if (actionBTN==BTN_4) {
			//crawling
			//측정소 정보 가져오기
			String url = textField2.getText();		
			//appendMessage(url, 1);
			try {
				MainFrame.service.getRealTimeDatas(url);
			}catch(Exception eo) {
				appendMessage(eo.toString(), 0);
			}			
		}else if (actionBTN==BTN_5) {
			//save
		}else if (actionBTN==BTN_6) {
		}else if (actionBTN==BTN_7) {
			System.exit(0);//exit		
		}

	}
}