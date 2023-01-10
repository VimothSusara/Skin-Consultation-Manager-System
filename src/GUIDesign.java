import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GUIDesign extends JFrame{
    private JButton docBtn; //Doctor button to show doctor list.
    private JButton consultBtn; //Button to the panel to search available doctors.
    private JButton consultRecordsBtn; //Showing consultations recorded in the system.
    private JPanel mainPanel;
    private JPanel docListMainPanel;
    private JPanel docListPanel;
    private JLabel noDocLabel;
    private JButton backBtnToMain;
    private JButton docListSortBtn;
    private JPanel sortedDocListPanel;
    private JPanel mainConsultPanel;
    private JButton backBtnToMainDocPanel;
    private JButton nextBtnToMainConsultPanel;
    private JPanel docSearchConsultPanel;
    private JLabel docSearchTitle;
    private JLabel consultDateLabel;
    private JTextField consultDateText;
    private JLabel consultTimeLabel;
    private JTextField consultTimeText;
    private JLabel consultDurationLabel;
    private JTextField consultDurationText;
    private JButton searchBtn;
    private JPanel searchedDocListPanel;
    private JButton searchedDocs;
    private JPanel consultFormMainPanel;
    private JPanel consultFormPanel;
    private JLabel choseDoctorDetailLabel;
    private JLabel consultationFormTitle;
    private JLabel patientNameLabel;
    private JTextField patientNameText;
    private JLabel patientSurnameLabel;
    private JTextField patientSurnameText;
    private JLabel patientDOBLabel;
    private JTextField patientDOBText;
    private JLabel patientMobileNoLabel;
    private JTextField patientMobileNoText;
    private JLabel patientIdLabel;
    private JTextField patientIdText;
    private JLabel consultCostLabel;
    private JTextField consultCostText;
    private JLabel consultStartTimeLabel;
    private JTextField consultStartTimeText;
    private JLabel consultEndTimeLabel;
    private JTextField consultEndTimeText;
    private JLabel consultNoteLabel;
    private JTextArea consultNoteText;
    private JButton addConsultationBtn;
    private JLabel imageInputLabel;
    private JTextArea imageInputText;
    private JButton imageBrowseBtn;
    private JPanel consultRecordsMainPanel;
    private JPanel consultRecordsPanel;
    private JButton consultRecordButtons;
    private JPanel consultRecordsDetailPanel;
    private JButton backToSearchMainPanelBtn;
    private JButton recordsToMainBtn;
    public static final byte[] key = {118, 106, 107, 122, 76, 99, 69, 83, 101, 103, 82, 101, 116, 75, 101, 127};
    ArrayList<Doctor> consEmptyDocList = new ArrayList<>();
    char[] chars = {'1','2','3','4','5','6','7','8','9','0','-','/'}; //characters to check for the text validation.
    ArrayList<String> patientCostCheck = new ArrayList<>();
    ArrayList<Doctor> doctors = new ArrayList<>();
    ArrayList<Doctor> sortedDocs = new ArrayList<>();
    ArrayList<Consultation> consultations = new ArrayList<>();
    ArrayList<String> notAvailableDoctorIds = new ArrayList<>();
    ArrayList<Doctor> availableDoctors = new ArrayList<>();
    private int consultationId = 1;
    ArrayList<Integer> consultationIdArray = new ArrayList<>();
    ArrayList<Patient> patients = new ArrayList<>();

    public GUIDesign(){

        //Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        JLabel background = new JLabel(new ImageIcon("back4.png"));
        background.setBounds(0,0,900,700);

        JLabel label1 = new JLabel("SKIN  CONSULTATION  MENU");
        label1.setBounds(170,95,570,50);
        label1.setForeground(new java.awt.Color(0, 0, 204));
        Font font1 = new Font(Font.SANS_SERIF,Font.BOLD+Font.ITALIC,37);
        label1.setFont(font1);
        mainPanel.add(label1);

        //Button to move to the doctor list panel.
        docBtn = new JButton("DOCTORS");
        docBtn.setBounds(170,230,230,100);
        docBtn.setForeground(new java.awt.Color(0, 0, 255));
        docBtn.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0,255),5));
        docBtn.setFocusPainted(false);
        docBtn.setContentAreaFilled(false);
        docBtn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));

        //Button to move to the consultation process panel.
        consultBtn = new JButton("CONSULTATION");
        consultBtn.setBounds(475,230,230,100);
        consultBtn.setForeground(new java.awt.Color(0,0,255));
        consultBtn.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0,255),5));
        consultBtn.setFocusPainted(false);
        consultBtn.setContentAreaFilled(false);
        consultBtn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));

        consultRecordsBtn = new JButton("CONSULTATION RECORDS");
        consultRecordsBtn.setBounds(265,365,340,100);
        consultRecordsBtn.setForeground(new java.awt.Color(0,0,255));
        consultRecordsBtn.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0,255),5));
        consultRecordsBtn.setFocusPainted(false);
        consultRecordsBtn.setContentAreaFilled(false);
        consultRecordsBtn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));

        //Instantiating the event handler.
        ActionHandler handle = new ActionHandler();
        MouseActionHandler mouseHandler = new MouseActionHandler();

        docBtn.addActionListener(handle);
        consultBtn.addActionListener(handle);
        docBtn.addMouseListener(mouseHandler);
        consultBtn.addMouseListener(mouseHandler);

        //Adding the main panel to the frame.
        add(mainPanel);
        mainPanel.add(docBtn);
        mainPanel.add(consultBtn);
        mainPanel.add(consultRecordsBtn);

        consultRecordsBtn.addActionListener(handle);
        consultRecordsBtn.addMouseListener(mouseHandler);

        mainPanel.add(background);

        //Main panel to view the doctor list.
        docListMainPanel = new JPanel();
        docListMainPanel.setLayout(null);
        docListMainPanel.setBorder(BorderFactory.createLineBorder(Color.blue,6));

        //sub-panel where you can view all the doctors.
        docListPanel = new JPanel();
        docListPanel.setLayout(null);
        docListPanel.setBounds(35,35,640,540);
        docListPanel.setOpaque(true);
        docListPanel.setBorder(BorderFactory.createLineBorder(Color.blue,5));

        docListMainPanel.add(docListPanel);
        printDocList(docListPanel);

        //back button to move to the main panel.
        backBtnToMain = new JButton("BACK TO MAIN PAGE");
        backButton(backBtnToMain);
        backBtnToMain.addActionListener(handle);
        backBtnToMain.addMouseListener(mouseHandler);
        docListMainPanel.add(backBtnToMain);

        //next button to move to main consultation panel.
        nextBtnToMainConsultPanel = new JButton("NEXT PAGE");
        nextBtnToMainConsultPanel.setBounds(525,600,150,40);
        nextBtnToMainConsultPanel.setForeground(Color.blue);
        nextBtnToMainConsultPanel.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        nextBtnToMainConsultPanel.setFocusPainted(false);
        nextBtnToMainConsultPanel.setContentAreaFilled(false);
        nextBtnToMainConsultPanel.addMouseListener(mouseHandler);
        nextBtnToMainConsultPanel.addActionListener(handle);

        //if doctors are not available 'next page' button will be not added.
        if(doctors.size()!=0){
            docListMainPanel.add(nextBtnToMainConsultPanel);
        }

        JLabel doctorsTitleText = new JLabel("DOCTORS");
        doctorsTitleText.setBounds(705,190,170,50);
        doctorsTitleText.setFont(new Font(Font.SANS_SERIF,Font.BOLD+Font.ITALIC,30));
        doctorsTitleText.setForeground(Color.blue);
        docListMainPanel.add(doctorsTitleText);

        //button to sort the doctor list.
        docListSortBtn = new JButton("SORT");
        docListSortBtn.setBounds(730,300,95,55);
        docListSortBtn.setForeground(Color.blue);
        docListSortBtn.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        docListSortBtn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        docListSortBtn.setFocusPainted(false);
        docListSortBtn.setContentAreaFilled(false);

        docListSortBtn.addMouseListener(mouseHandler);
        docListSortBtn.addActionListener(handle);
        docListMainPanel.add(docListSortBtn);

        //viewing the sorted doctor list in the panel.
        sortedDocListPanel = new JPanel();
        sortedDocListPanel.setLayout(null);
        sortedDocListPanel.setBounds(35,35,640,540);
        sortedDocListPanel.setBorder(BorderFactory.createLineBorder(Color.blue,5));

        mainConsultPanel = new JPanel();
        mainConsultPanel.setLayout(null);
        mainConsultPanel.setBorder(BorderFactory.createLineBorder(Color.blue,5));

        //back button to move to doctor list panel.
        backBtnToMainDocPanel = new JButton("BACK TO DOCTOR LIST");
        backButton(backBtnToMainDocPanel);
        backBtnToMainDocPanel.addActionListener(handle);
        backBtnToMainDocPanel.addMouseListener(mouseHandler);
        mainConsultPanel.add(backBtnToMainDocPanel);

        //Panel to search a doctor in specific date and time.
        docSearchConsultPanel = new JPanel();
        docSearchConsultPanel.setLayout(null);
        docSearchConsultPanel.setBounds(35,25,820,550);
        docSearchConsultPanel.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        mainConsultPanel.add(docSearchConsultPanel);

        docSearchTitle = new JLabel("SEARCH");
        docSearchTitle.setBounds(122,40,150,60);
        docSearchTitle.setForeground(Color.blue);
        docSearchTitle.setFont(new Font(Font.SERIF,Font.BOLD+Font.ITALIC,30));
        docSearchConsultPanel.add(docSearchTitle);

        consultDateLabel = new JLabel("Consultation Date");
        consultDateLabel.setBounds(105,120,180,60);
        consultDateLabel.setForeground(Color.blue);
        consultDateLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        docSearchConsultPanel.add(consultDateLabel);

        consultDateText = new JTextField(10);
        consultDateText.setBounds(110,180,150,30);
        consultDateText.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        consultDateText.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));

        docSearchConsultPanel.add(consultDateText);

        JLabel dateFormat = new JLabel("*dd/MM/yyyy*");
        dateFormat.setBounds(110,210,100,20);
        dateFormat.setForeground(new java.awt.Color(64,64,64));

        docSearchConsultPanel.add(dateFormat);

        consultTimeLabel = new JLabel("Consultation Time");
        consultTimeLabel.setBounds(102,225,180,60);
        consultTimeLabel.setForeground(Color.blue);
        consultTimeLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        docSearchConsultPanel.add(consultTimeLabel);

        consultTimeText = new JTextField(10);
        consultTimeText.setBounds(110,280,150,30);
        consultTimeText.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        consultTimeText.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));

        docSearchConsultPanel.add(consultTimeText);

        JLabel timeFormat = new JLabel("*00:00*");
        timeFormat.setBounds(110,310,100,20);
        timeFormat.setForeground(new java.awt.Color(64,64,64));

        docSearchConsultPanel.add(timeFormat);

        consultDurationLabel = new JLabel("Consultation Hours");
        consultDurationLabel.setBounds(100,325,180,60);
        consultDurationLabel.setForeground(Color.blue);
        consultDurationLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));

        docSearchConsultPanel.add(consultDurationLabel);

        consultDurationText = new JTextField(5);
        consultDurationText.setBounds(110,380,150,30);
        consultDurationText.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        consultDurationText.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));

        docSearchConsultPanel.add(consultDurationText);

        JLabel durationFormat = new JLabel("*No of Cons. Hours*");
        durationFormat.setBounds(110,410,150,20);
        durationFormat.setForeground(new java.awt.Color(64,64,64));

        docSearchConsultPanel.add(durationFormat);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(130,465,100,35);
        searchBtn.setFont(new Font(Font.SANS_SERIF,Font.PLAIN+Font.BOLD,13));
        searchBtn.setForeground(new java.awt.Color(0,128,255));
        searchBtn.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        searchBtn.setFocusPainted(false);
        searchBtn.setContentAreaFilled(false);

        searchBtn.addActionListener(handle);
        searchBtn.addMouseListener(mouseHandler);
        docSearchConsultPanel.add(searchBtn);

        searchedDocListPanel = new JPanel();
        searchedDocListPanel.setLayout(null);
        searchedDocListPanel.setBounds(370,25,425,500);
        searchedDocListPanel.setBorder(BorderFactory.createLineBorder(Color.blue,5));

        JLabel searchedDocPanelBackground = new JLabel(new ImageIcon("panelPic.png"));
        searchedDocPanelBackground.setBounds(0,0,900,700);
        mainConsultPanel.add(searchedDocPanelBackground);

        consultFormMainPanel = new JPanel();
        consultFormMainPanel.setLayout(null);
        consultFormMainPanel.setBorder(BorderFactory.createLineBorder(Color.blue,5));

        JLabel docDetailTitle = new JLabel("Doctor Details");
        docDetailTitle.setBounds(20,8,150,25);
        docDetailTitle.setForeground(Color.black);
        docDetailTitle.setFont(new Font(Font.SANS_SERIF,Font.ITALIC+Font.BOLD,18));
        consultFormMainPanel.add(docDetailTitle);

        choseDoctorDetailLabel = new JLabel("No Doctor details");
        choseDoctorDetailLabel.setBounds(20,30,250,100);
        choseDoctorDetailLabel.setForeground(new java.awt.Color(51,51,255));
        consultFormMainPanel.add(choseDoctorDetailLabel);

        consultFormPanel = new JPanel();
        consultFormPanel.setLayout(null);
        consultFormPanel.setBounds(20,145,850,460);
        consultFormPanel.setForeground(Color.blue);
        consultFormPanel.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        consultFormMainPanel.add(consultFormPanel);

        consultationFormTitle = new JLabel("Consultation Form");
        consultationFormTitle.setBounds(330,10,200,25);
        consultationFormTitle.setForeground(Color.blue);
        consultationFormTitle.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));

        consultFormPanel.add(consultationFormTitle);

        patientNameLabel = new JLabel("Patient Name");
        patientNameLabel.setBounds(65,65,120,20);
        patientNameLabel.setForeground(Color.blue);
        patientNameLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));

        consultFormPanel.add(patientNameLabel);

        patientNameText = new JTextField();
        patientNameText.setBounds(50,90,120,25);
        patientNameText.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        consultFormPanel.add(patientNameText);

        patientSurnameLabel = new JLabel("Patient Surname");
        patientSurnameLabel.setBounds(245,65,120,20);
        patientSurnameLabel.setForeground(Color.blue);
        patientSurnameLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));

        consultFormPanel.add(patientSurnameLabel);

        patientSurnameText = new JTextField();
        patientSurnameText.setBounds(230,90,130,25);
        patientSurnameText.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        consultFormPanel.add(patientSurnameText);

        patientDOBLabel = new JLabel("Date of Birth");
        patientDOBLabel.setBounds(163,140,120,20);
        patientDOBLabel.setForeground(Color.blue);
        patientDOBLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));

        consultFormPanel.add(patientDOBLabel);

        patientDOBText = new JTextField();
        patientDOBText.setBounds(137,165,130,25);
        patientDOBText.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        consultFormPanel.add(patientDOBText);

        patientMobileNoLabel = new JLabel("Mobile No");
        patientMobileNoLabel.setBounds(170,210,120,20);
        patientMobileNoLabel.setForeground(Color.blue);
        patientMobileNoLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));

        consultFormPanel.add(patientMobileNoLabel);

        patientMobileNoText = new JTextField();
        patientMobileNoText.setBounds(137,235,130,25);
        patientMobileNoText.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        consultFormPanel.add(patientMobileNoText);

        patientIdLabel = new JLabel("Patient ID");
        patientIdLabel.setBounds(170,280,120,20);
        patientIdLabel.setForeground(Color.blue);
        patientIdLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));

        consultFormPanel.add(patientIdLabel);

        patientIdText = new JTextField();
        patientIdText.setBounds(137,305,130,25);
        patientIdText.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        consultFormPanel.add(patientIdText);

        consultCostLabel = new JLabel("Consultation Cost");
        consultCostLabel.setBounds(70,365,120,20);
        consultCostLabel.setForeground(Color.blue);
        consultCostLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));

        consultFormPanel.add(consultCostLabel);

        consultCostText = new JTextField();
        consultCostText.setBounds(200,365,120,25);
        consultCostText.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        consultFormPanel.add(consultCostText);

        consultStartTimeLabel = new JLabel("Consultation Start at");
        consultStartTimeLabel.setBounds(490,85,150,20);
        consultStartTimeLabel.setForeground(Color.blue);
        consultStartTimeLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));

        consultFormPanel.add(consultStartTimeLabel);

        consultStartTimeText = new JTextField();
        consultStartTimeText.setBounds(640,85,120,25);
        consultStartTimeText.setBorder(BorderFactory.createLineBorder(Color.blue,3));
        consultStartTimeText.setEditable(false);

        consultFormPanel.add(consultStartTimeText);

        consultEndTimeLabel = new JLabel("Consultation End Time");
        consultEndTimeLabel.setBounds(485,140,150,20);
        consultEndTimeLabel.setForeground(Color.blue);
        consultEndTimeLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));

        consultFormPanel.add(consultEndTimeLabel);

        consultEndTimeText = new JTextField();
        consultEndTimeText.setBounds(640,140,120,25);
        consultEndTimeText.setBorder(BorderFactory.createLineBorder(Color.blue,3));
        consultEndTimeText.setEditable(false);

        consultFormPanel.add(consultEndTimeText);

        consultNoteLabel = new JLabel("Additional Note");
        consultNoteLabel.setBounds(575,185,120,20);
        consultNoteLabel.setForeground(Color.blue);
        consultNoteLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));

        consultFormPanel.add(consultNoteLabel);

        consultNoteText = new JTextArea(3,10);
        consultNoteText.setBounds(475,215,300,80);
        consultNoteText.setLineWrap(true);
        consultNoteText.setBorder(BorderFactory.createLineBorder(Color.blue,3));

        consultFormPanel.add(consultNoteText);

        imageInputLabel = new JLabel("Images");
        imageInputLabel.setBounds(595,305,100,20);
        imageInputLabel.setForeground(Color.blue);
        imageInputLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));

        consultFormPanel.add(imageInputLabel);

        imageInputText = new JTextArea();
        imageInputText.setBounds(475,330,300,55);
        imageInputText.setLineWrap(true);
        imageInputText.setBorder(BorderFactory.createLineBorder(Color.blue,3));
        imageInputText.setEditable(false);

        consultFormPanel.add(imageInputText);

        imageBrowseBtn = new JButton("Browse Image");
        imageBrowseBtn.setBounds(548,392,150,20);
        imageBrowseBtn.setForeground(Color.blue);
        imageBrowseBtn.setBorder(BorderFactory.createLineBorder(Color.blue,1));
        imageBrowseBtn.setContentAreaFilled(false);
        imageBrowseBtn.setFocusPainted(false);

        consultFormPanel.add(imageBrowseBtn);

        imageBrowseClick handledd = new imageBrowseClick();
        imageBrowseBtn.addActionListener(handledd);
        imageBrowseBtn.addMouseListener(mouseHandler);

        addConsultationBtn = new JButton("Add Consultation");
        addConsultationBtn.setBounds(305,415,170,35);
        addConsultationBtn.setForeground(Color.blue);
        addConsultationBtn.setFont(new Font(Font.SANS_SERIF,Font.PLAIN+Font.BOLD,15));
        addConsultationBtn.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        addConsultationBtn.setFocusPainted(false);
        addConsultationBtn.setContentAreaFilled(false);

        addConsultationBtn.addActionListener(handle);
        addConsultationBtn.addMouseListener(mouseHandler);
        consultFormPanel.add(addConsultationBtn);

        consultRecordsMainPanel = new JPanel();
        consultRecordsMainPanel.setLayout(null);
        consultRecordsMainPanel.setBorder(BorderFactory.createLineBorder(Color.blue,5));

        consultRecordsPanel = new JPanel();
        consultRecordsPanel.setLayout(null);
        consultRecordsPanel.setBounds(35,80,340,500);
        consultRecordsPanel.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        consultRecordsPanel.setForeground(Color.blue);

        consultRecordsMainPanel.add(consultRecordsPanel);

        consultRecordsDetailPanel = new JPanel();
        consultRecordsDetailPanel.setLayout(null);
        consultRecordsDetailPanel.setBounds(390,80,465,500);
        consultRecordsDetailPanel.setForeground(Color.blue);

        consultRecordsMainPanel.add(consultRecordsDetailPanel);

        backToSearchMainPanelBtn = new JButton("BACK TO SEARCH");
        backButton(backToSearchMainPanelBtn);

        backToSearchMainPanelBtn.addActionListener(handle);
        backToSearchMainPanelBtn.addMouseListener(mouseHandler);
        consultRecordsMainPanel.add(backToSearchMainPanelBtn);

        JLabel consultationRecordsTitleLabel = new JLabel("CONSULTATION RECORDS");
        consultationRecordsTitleLabel.setBounds(90,25,230,30);
        consultationRecordsTitleLabel.setForeground(new java.awt.Color(0,0,153));
        consultationRecordsTitleLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));

        consultRecordsMainPanel.add(consultationRecordsTitleLabel);

        JLabel consultationRecordDetailTitle = new JLabel("CONSULTATION DETAIL");
        consultationRecordDetailTitle.setBounds(540,25,200,30);
        consultationRecordDetailTitle.setForeground(new java.awt.Color(0,0,153));
        consultationRecordDetailTitle.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));

        consultRecordsMainPanel.add(consultationRecordDetailTitle);

        recordsToMainBtn = new JButton("MAIN MENU");
        recordsToMainBtn.setBounds(380,600,150,40);
        recordsToMainBtn.setForeground(Color.blue);
        recordsToMainBtn.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        recordsToMainBtn.setFocusPainted(false);
        recordsToMainBtn.setContentAreaFilled(false);

        consultRecordsMainPanel.add(recordsToMainBtn);
        recordsToMainBtn.addActionListener(handle);
        recordsToMainBtn.addMouseListener(mouseHandler);

    }

    //printing the available doctors and if doctors not available it shows that doctors not available right now.
    public void printDocList(JPanel docListPanel){
        doctors = (ArrayList<Doctor>)WestminsterSkinConsultationManager.doctors.clone();
        int y=22;
        int count = 0;
        if(doctors.size()>0) {
            for (Doctor doc : doctors) {
                JLabel docLabels = new JLabel(count + 1 + "." + " " + doc.getName() + " " + "|" + " " + doc.getSurName() + " " + "|" + " " +
                        doc.getDateOfBirth() + " " + "|" + " " + doc.getMobileNum() + " " + "|" + " " + doc.getMedicalLicenseNumber() +
                        " " + "|" + " " + doc.getSpecialisation() + " ");
                docLabels.setForeground(new java.awt.Color(128,128,128));
                docLabels.setFont(new Font(Font.SANS_SERIF, Font.PLAIN + Font.BOLD, 15));
                docLabels.setBounds(20, y, 580, 50);
                y += 48;
                docListPanel.add(docLabels);
                count += 1;
            }
        }
        else{
            noDocLabel = new JLabel("Doctors not available right now..............");
            noDocLabel.setBounds(50,30,300,50);
            noDocLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
            noDocLabel.setForeground(Color.red);
            docListPanel.add(noDocLabel);
        }
    }

    public void printSortedDocList(JPanel sortedDocListPanel){
        docListMainPanel.add(sortedDocListPanel);
        sortedDocs = (ArrayList<Doctor>)doctors.clone();
        sortedDocs.sort(new SurnameCompare());
        int y=22;
        int count = 0;
        if(sortedDocs.size()>0) {
            for (Doctor doc : sortedDocs) {
                JLabel docLabels = new JLabel(count + 1 + "." + " " + doc.getName() + " " + "|" + " " + doc.getSurName() +
                        " " + "|" + " " + doc.getDateOfBirth() + " " + "|" + " " + doc.getMobileNum() + " " + "|" + " " +
                        doc.getMedicalLicenseNumber() + " " + "|" + " " + doc.getSpecialisation() + " ");
                docLabels.setForeground(new java.awt.Color(128,128,128));
                docLabels.setFont(new Font(Font.SANS_SERIF, Font.PLAIN + Font.BOLD, 15));
                docLabels.setBounds(20, y, 580, 50);
                y += 48;
                sortedDocListPanel.add(docLabels);
                count += 1;
            }
        }
        else{
            noDocLabel = new JLabel("Doctors not available right now..............");
            noDocLabel.setBounds(50,30,300,50);
            noDocLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
            noDocLabel.setForeground(Color.red);
            sortedDocListPanel.add(noDocLabel);
        }
    }

    public void backButton(JButton backBtn){
        backBtn.setBounds(35,600,150,40);
        backBtn.setForeground(Color.blue);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        backBtn.setFocusPainted(false);
        backBtn.setContentAreaFilled(false);
    }

    //returning false if the time does not match the regular expression format.
    //or else true.
    public boolean consultTimeValidation(){
        return consultTimeText.getText().matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");
    }

    //converting string and returning as a LocalTime.(Has set a format)
    public LocalTime consultTime(String textType){
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(textType,timeFormatter);
    }

    //converting string to an int and returning.
    public int durationToInt(String durText){
        return Integer.parseInt(durText);
    }

    public LocalTime consultEndTime(){
         return  consultTime(consultTimeText.getText()).plusHours(durationToInt(consultDurationText.getText()));
    }

    //returning ending time of the consultation by adding number of hours to the start time.
    public String endTime(){
        return String.valueOf(consultEndTime());
    }

    //if no.of consultations are not '0' searching doctors printing.
    public void searchedDocList(JPanel searchedDocListPanel){
        searchedDocListPanel.removeAll();
        searchedDocListPanel.revalidate();
        if(consultations.size()!=0){
            for(Consultation consults : consultations) {
                //checking for the same date.
                //checking if the entered start time is before past consultations' end time and after it's start time.
                if (consultDateText.getText().equals(consults.getDate()) &&
                        LocalTime.parse(consultTimeText.getText()).isBefore(LocalTime.parse(consults.getConsultEndTime()))
                        && LocalTime.parse(consultTimeText.getText()).isAfter(LocalTime.parse(consults.getConsultStartTime()))) {
                    notAvailableDoctorIds.add(consults.getConsultDocId());
                }
                //checking if the current end time is after past consultations' start time and before it's end time.
                else if (consultDateText.getText().equals(consults.getDate()) &&
                        LocalTime.parse(endTime()).isAfter(LocalTime.parse(consults.getConsultStartTime()))
                        && LocalTime.parse(consultTimeText.getText()).isBefore(LocalTime.parse(consults.getConsultEndTime()))) {
                    notAvailableDoctorIds.add(consults.getConsultDocId());
                }
                //checking whether current time will fall on top of another consultation.
                else if (consultDateText.getText().equals(consults.getDate()) &&
                        LocalTime.parse(consultTimeText.getText()).isBefore(LocalTime.parse(consults.getConsultStartTime()))
                        && LocalTime.parse(endTime()).isAfter(LocalTime.parse(consults.getConsultEndTime()))) {
                    notAvailableDoctorIds.add(consults.getConsultDocId());
                }
            }

            //checking for the available doctors by getting rid of unavailable doctors.
            for(Doctor d : WestminsterSkinConsultationManager.doctors){
                if(!notAvailableDoctorIds.contains(d.getMedicalLicenseNumber())){
                    availableDoctors.add(d);
                }
            }

            //clearing unavailable doctor arraylist.
            notAvailableDoctorIds.clear();

            int y = 15;
            int count = 0;
            for (Doctor docs : availableDoctors) {
                searchedDocs = new JButton(count + 1 + ".  " + docs.getName() + "  " +
                        docs.getSurName() + " | " + docs.getSpecialisation() + " | " + "ID:" +
                        docs.getMedicalLicenseNumber());
                searchedDocs.setName(docs.getMedicalLicenseNumber());
                searchedDocs.setForeground(Color.gray);
                searchedDocs.setFocusPainted(false);
                searchedDocs.setContentAreaFilled(false);
                searchedDocs.setBorder(BorderFactory.createLineBorder(new java.awt.Color(224,224,224)));
                searchedDocs.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
                searchedDocs.setBounds(15, y, 395, 30);
                y += 45;
                count += 1;
                searchedDocs.addActionListener(new clicklabelHandler());
                searchedDocListPanel.add(searchedDocs);
                searchedDocListPanel.repaint();
            }

            //clearing the available doctors.
            availableDoctors.clear();

        }
        else{
            allDocsInList(searchedDocListPanel);
        }
    }

    public void allDocsInList(JPanel searchedDocListPanel){
        consEmptyDocList = (ArrayList<Doctor>)WestminsterSkinConsultationManager.doctors.clone();
        int y2 = 15;
        int docCount = 0;
        if(consEmptyDocList.size()>0) {
            for (Doctor docs : consEmptyDocList) {
                searchedDocs = new JButton(docCount + 1 + ".  " + docs.getName() + "  " + docs.getSurName() +
                         " | " + docs.getSpecialisation() + " | "+ "ID:" +docs.getMedicalLicenseNumber());
                searchedDocs.setName(docs.getMedicalLicenseNumber());   //giving each doctor buttons a unique identification.
                searchedDocs.setForeground(Color.gray);
                searchedDocs.setFocusPainted(false);
                searchedDocs.setContentAreaFilled(false);
                searchedDocs.setBorder(BorderFactory.createLineBorder(new java.awt.Color(224,224,224)));
                searchedDocs.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
                searchedDocs.setBounds(15, y2, 395, 30);
                y2 += 45;
                docCount += 1;
                searchedDocs.addActionListener(new clicklabelHandler());
                searchedDocListPanel.add(searchedDocs);
                searchedDocListPanel.repaint();
            }
        }
    }

    //printing the doctor details of which was chosen from the searched doctor.
    public void choseDoctorLabel(String specDocId){
        consultFormMainPanel.remove(choseDoctorDetailLabel);
        for(Doctor docs : doctors){
            if(docs.getMedicalLicenseNumber().equals(specDocId)){
                choseDoctorDetailLabel = new JLabel("<html>" + "Name : " + docs.getName() + " " +
                        docs.getSurName() + "<br>"+   "Mobile No : "+ docs.getMobileNum() + "<br>"+ "Medical ID : " +
                        docs.getMedicalLicenseNumber() + "<br>" + "Specialization : " + docs.getSpecialisation() + "</html>");
                choseDoctorDetailLabel.setBounds(20,30,250,100);
                choseDoctorDetailLabel.setName(docs.getMedicalLicenseNumber());
                choseDoctorDetailLabel.setBackground(Color.white);
                choseDoctorDetailLabel.setForeground(new java.awt.Color(51,51,255));
                choseDoctorDetailLabel.setFont(new Font(Font.SERIF,Font.BOLD,17));
                consultFormMainPanel.add(choseDoctorDetailLabel);
            }
        }
    }

    public String getDoctorName(String docId){
        for (Doctor d:doctors){
            if(d.getMedicalLicenseNumber().equals(docId)){
                return d.getName()+" "+d.getSurName();
            }
        }
        return null;
    }

    public String getPatientName(String patId){
        for(Patient p : patients){
            if(patId.equals(String.valueOf(p.getUniqueId()))){
                return p.getName() + " " + p.getSurName();
            }
        }
        return null;
    }

    //printing consultation records on the panel.
    public void printConsultRecords(){
        consultRecordsPanel.removeAll();
        consultRecordsPanel.revalidate();
        consultRecordsPanel.repaint();
        int y = 15;
        for (Consultation c : consultations){

            consultRecordButtons = new JButton(  "<html>"+" Dr." + getDoctorName(c.getConsultDocId()) +
                    "  |  "+"Patient: " + getPatientName(c.getConsultPatientId())+ "<br>"+ "Date: "+ c.getDate() +
                    "  |  " + c.getConsultStartTime() + " to "+c.getConsultEndTime() + "</html>");
            consultRecordButtons.setName(String.valueOf(c.getConsultationId()));   //setting name of the button as it's Id.
            consultRecordButtons.setBackground(new java.awt.Color(128,128,128));
            consultRecordButtons.setFocusPainted(false);
            consultRecordButtons.setContentAreaFilled(false);
            consultRecordButtons.setHorizontalAlignment(SwingConstants.LEFT);
            consultRecordButtons.setBorder(BorderFactory.createLineBorder(new java.awt.Color(224,224,224)));
            consultRecordButtons.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
            consultRecordButtons.setBounds(15,y,310,37);
            consultRecordsPanel.add(consultRecordButtons);
            consultRecordButtons.addActionListener(new clickRecordsHandler());
            y += 45;
        }
    }

    public void labelCreate(JLabel Name){
        Name.setForeground(Color.black);
        Name.setFont(new Font(Font.SERIF,Font.BOLD,15));
    }

    public void labelResultCreate(JLabel Name){
        Name.setForeground(Color.gray);
        Name.setFont(new Font(Font.SERIF,Font.ITALIC,15));
    }

    //consultation records form.
    public void consultRecordsData(String consultId){
        consultRecordsDetailPanel.removeAll();
        consultRecordsDetailPanel.revalidate();
        consultRecordsDetailPanel.repaint();

        for(Consultation c : consultations){
            if(String.valueOf(c.getConsultationId()).equals(consultId)){
                JLabel doctorName = new JLabel("Doctor :  ");
                doctorName.setBounds(40,35,100,20);
                labelCreate(doctorName);
                JLabel doctorNameResult = new JLabel(getDoctorName(c.getConsultDocId()));
                doctorNameResult.setBounds(100,35,170,20);
                labelResultCreate(doctorNameResult);
                JLabel doctorId = new JLabel("Doctor ID : ");
                doctorId.setBounds(310,35,100,20);
                labelCreate(doctorId);
                JLabel doctorIdResult = new JLabel(c.getConsultDocId());
                doctorIdResult.setBounds(390,35,150,20);
                labelResultCreate(doctorIdResult);
                JLabel patientName = new JLabel("Patient :  ");
                patientName.setBounds(40,90,100,20);
                labelCreate(patientName);
                JLabel patientNameResult = new JLabel(getPatientName(c.getConsultPatientId()));
                patientNameResult.setBounds(100,90,170,20);
                labelResultCreate(patientNameResult);
                JLabel patientId = new JLabel("Patient ID :  ");
                patientId.setBounds(310,90,100,20);
                labelCreate(patientId);
                JLabel patientIdResult = new JLabel(c.getConsultPatientId());
                patientIdResult.setBounds(390,90,150,20);
                labelResultCreate(patientIdResult);
                JLabel consultDate = new JLabel("Consultation Date :  ");
                consultDate.setBounds(125,140,150,20);
                labelCreate(consultDate);
                JLabel consultDateResult = new JLabel(c.getDate());
                consultDateResult.setBounds(257,140,100,20);
                labelResultCreate(consultDateResult);
                JLabel consultStartTime = new JLabel("Consultation Start Time :  ");
                consultStartTime.setBounds(40,190,200,20);
                labelCreate(consultStartTime);
                JLabel consultStartTimeResult = new JLabel(c.getConsultStartTime());
                consultStartTimeResult.setBounds(212,190,80,20);
                labelResultCreate(consultStartTimeResult);
                JLabel consultEndTime = new JLabel("Consultation End Time :  ");
                consultEndTime.setBounds(40,240,180,20);
                labelCreate(consultEndTime);
                JLabel consultEndTimeResult = new JLabel(c.getConsultEndTime());
                consultEndTimeResult.setBounds(210,240,80,20);
                labelResultCreate(consultEndTimeResult);
                JLabel consultCost = new JLabel("Cost :  ");
                consultCost.setBounds(300,215,70,20);
                labelCreate(consultCost);
                JLabel consultCostResult = new JLabel("Rs. " + c.getCost());
                consultCostResult.setBounds(345,215,80,20);
                labelResultCreate(consultCostResult);
                JLabel image = new JLabel("Image :  ");
                image.setBounds(40,330,70,20);
                labelCreate(image);
                JLabel imageResult = new JLabel();
                imageResult.setBorder(BorderFactory.createLineBorder(Color.black,2));
                imageResult.setBounds(115,273,160,140);
                ImageIcon icon = new ImageIcon(decrypting(c.getImagePath()));
                Image img = icon.getImage();
                Image newImage = img.getScaledInstance(imageResult.getWidth(),imageResult.getHeight(),Image.SCALE_SMOOTH);
                imageResult.setIcon(new ImageIcon(newImage));
                JLabel note = new JLabel("Note :  ");
                note.setBounds(40,455,50,20);
                labelCreate(note);
                JTextArea noteResult = new JTextArea(decrypting(c.getNote()));  //passing the decrypted image path to the text area.
                noteResult.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,12));
                noteResult.setBounds(90,428,350,70);
                noteResult.setForeground(Color.gray);
                noteResult.setLineWrap(true);
                noteResult.setEditable(false);
                noteResult.setBorder(BorderFactory.createLineBorder(Color.black,2));

                consultRecordsDetailPanel.add(noteResult);
                consultRecordsDetailPanel.add(note);
                consultRecordsDetailPanel.add(imageResult);
                consultRecordsDetailPanel.add(image);
                consultRecordsDetailPanel.add(consultCostResult);
                consultRecordsDetailPanel.add(consultCost);
                consultRecordsDetailPanel.add(consultEndTimeResult);
                consultRecordsDetailPanel.add(consultEndTime);
                consultRecordsDetailPanel.add(consultStartTimeResult);
                consultRecordsDetailPanel.add(consultStartTime);
                consultRecordsDetailPanel.add(consultDateResult);
                consultRecordsDetailPanel.add(consultDate);
                consultRecordsDetailPanel.add(patientIdResult);
                consultRecordsDetailPanel.add(patientId);
                consultRecordsDetailPanel.add(patientNameResult);
                consultRecordsDetailPanel.add(patientName);
                consultRecordsDetailPanel.add(doctorIdResult);
                consultRecordsDetailPanel.add(doctorId);
                consultRecordsDetailPanel.add(doctorNameResult);
                consultRecordsDetailPanel.add(doctorName);
            }
        }

    }

    //checking for invalid name inputs.
    public boolean patientNameValidation(String patientName,char[] chars){
        for(int i=0;i<patientName.length();i++){
            char chr = patientName.charAt(i);
            for (char c: chars){
                if(c == chr){
                    return false;
                }
            }
        }
        return true;
    }

    //checking if the patient ids are only numbers.
    public boolean patientIdCheck(String id){
        return id.matches("[0-9]+");
    }

    //returning first time patient cost.
    public String firstTimeCost(){
        return String.valueOf(15*Integer.parseInt(consultDurationText.getText()));
    }

    //returning second time patient cost.
    public String notFirstTimeCost(){
        return String.valueOf(25*Integer.parseInt(consultDurationText.getText()));
    }

    //checking for the real cost for the consultation and returning the value.
    public String consultCostCheck(){
        String realCost;
        if(patientCostCheck.size()==0){
            realCost = firstTimeCost();
            return realCost;
        }
        else{

            Set<String> set = new LinkedHashSet<>(patientCostCheck);

            patientCostCheck.clear();                   //checking for any duplicate components and
                                                        //clearing them
            patientCostCheck.addAll(set);

            for(String t : patientCostCheck){
                if(t.equals(patientIdText.getText())){
                    realCost = notFirstTimeCost();
                }
                else{
                    realCost = firstTimeCost();
                }
                return realCost;
            }
        }
        return null;
    }

    //checking if the patient has another allocated consultation at the same time.
    public String patientTimeAllocCheck(){
        String notAvailablePatient = "";
        if(consultations.size()!=0){
            for(Consultation consults : consultations) {
                if (consultDateText.getText().equals(consults.getDate()) && patientIdText.getText().equals(consults.getConsultPatientId()) &&
                        LocalTime.parse(consultTimeText.getText()).isBefore(LocalTime.parse(consults.getConsultEndTime()))
                        && LocalTime.parse(consultTimeText.getText()).isAfter(LocalTime.parse(consults.getConsultStartTime()))) {
                    notAvailablePatient = consults.getConsultPatientId();
                }
                else if (consultDateText.getText().equals(consults.getDate()) && patientIdText.getText().equals(consults.getConsultPatientId()) &&
                        LocalTime.parse(endTime()).isAfter(LocalTime.parse(consults.getConsultStartTime()))
                        && LocalTime.parse(consultTimeText.getText()).isBefore(LocalTime.parse(consults.getConsultEndTime()))) {
                    notAvailablePatient = consults.getConsultPatientId();
                }
                else if (consultDateText.getText().equals(consults.getDate()) && patientIdText.getText().equals(consults.getConsultPatientId()) &&
                        LocalTime.parse(consultTimeText.getText()).isBefore(LocalTime.parse(consults.getConsultStartTime()))
                        && LocalTime.parse(endTime()).isAfter(LocalTime.parse(consults.getConsultEndTime()))) {
                    notAvailablePatient = consults.getConsultPatientId();
                }
                else{
                    notAvailablePatient = ".";
                }
            }
            return notAvailablePatient;
        }
        return ".";
    }

    //clearing all the input fields.
    public void clearingAllTexts(){
        consultDateText.setText("");
        consultTimeText.setText("");
        consultDurationText.setText("");
        searchedDocListPanel.removeAll();
        searchedDocListPanel.revalidate();
        searchedDocListPanel.repaint();
        choseDoctorDetailLabel.setText("");
        patientNameText.setText("");
        patientSurnameText.setText("");
        patientDOBText.setText("");
        patientMobileNoText.setText("");
        patientIdText.setText("");
        consultCostText.setText("");
        consultStartTimeText.setText("");
        consultEndTimeText.setText("");
        consultNoteText.setText("");
        imageInputText.setText("");
    }

    //encrypting the input strings
    public String encrypting(String inputText){
        Cipher cipher;
        String encryptedString = "";

        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            final SecretKeySpec secretKey = new SecretKeySpec(key,"AES");

            cipher.init(Cipher.ENCRYPT_MODE,secretKey);

            byte[] encryptedText = cipher.doFinal(inputText.getBytes());

            encryptedString = Base64.getEncoder().encodeToString(encryptedText);
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
               InvalidKeyException e){
            System.out.println("Encrypting error caught!");
        }
        return encryptedString;
    }

    //decrypting the strings which passed.
    public String decrypting(String outputText){
        Cipher cipher;
        String decryptedString = "";

        try{
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            final SecretKeySpec secretKey = new SecretKeySpec(key,"AES");

            cipher.init(Cipher.DECRYPT_MODE,secretKey);

            byte[] decryptedText = Base64.getDecoder().decode(outputText.getBytes());

            decryptedString = new String(cipher.doFinal(decryptedText));

        }
        catch(NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
              InvalidKeyException e){
            System.out.println("Decrypting error caught!");
        }
        return decryptedString;

    }

    //comparing doctor's surname and sorting.
    class SurnameCompare implements Comparator<Doctor> {
        @Override
        public int compare(Doctor o1,Doctor o2){
            return o1.getSurName().toLowerCase().compareTo(o2.getSurName().toLowerCase());
        }
    }

    //action handling by identifying the label of a button.
    private class ActionHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String btnLabel = e.getActionCommand();
            if (btnLabel.equals("DOCTORS")) {
                remove(mainPanel);
                add(docListMainPanel);
                validate();
                repaint();
            } else if (btnLabel.equals("CONSULTATION")) {
                remove(mainPanel);
                add(mainConsultPanel);
                validate();
                repaint();
            }else if(btnLabel.equals("CONSULTATION RECORDS")){
                remove(mainPanel);
                add(consultRecordsMainPanel);
                validate();
                repaint();
            } else if (btnLabel.equals("BACK TO MAIN PAGE")) {
                add(mainPanel);
                remove(docListMainPanel);
                validate();
                repaint();
            } else if (btnLabel.equals("SORT")) {
                docListMainPanel.remove(docListPanel);
                printSortedDocList(sortedDocListPanel);
                docListSortBtn.setText("UNSORT");
                validate();
                repaint();
            } else if (btnLabel.equals("UNSORT")) {
                docListMainPanel.remove(sortedDocListPanel);
                docListMainPanel.add(docListPanel);
                docListSortBtn.setText("SORT");
                validate();
                repaint();
            } else if (btnLabel.equals("BACK TO DOCTOR LIST")) {
                remove(mainConsultPanel);
                add(docListMainPanel);
                validate();
                repaint();
            } else if (btnLabel.equals("NEXT PAGE")) {
                remove(docListMainPanel);
                add(mainConsultPanel);
                validate();
                repaint();
            } else if (btnLabel.equals("Search")) {
                //Consultation time should be 07:00 to 17:00.
                //if not a popup message will be shown.
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                String consulOpenTime = "07:00";
                LocalTime  openTimeObj = LocalTime.parse(consulOpenTime,timeFormatter);

                String consultCloseTime = "17:00";
                LocalTime closeTimeObj = LocalTime.parse(consultCloseTime, timeFormatter);

                if(consultDateText.getText().equals("")){
                    JOptionPane.showMessageDialog(docSearchConsultPanel,"Please enter a date",
                            "Blank Date Input",JOptionPane.WARNING_MESSAGE);
                }
                else if(!WestminsterSkinConsultationManager.program.dateValidation(consultDateText.getText()) ||
                        consultDateText.getText().length() > 10){
                    JOptionPane.showMessageDialog(docSearchConsultPanel,"Input Date is incorrect",
                            "Invalid Date",JOptionPane.WARNING_MESSAGE);
                }
                else if (consultTimeText.getText().equals("")){
                    JOptionPane.showMessageDialog(docSearchConsultPanel,"Please enter a time",
                            "Blank Time Input",JOptionPane.WARNING_MESSAGE);
                }
                else if (!consultTimeValidation()) {
                    JOptionPane.showMessageDialog(docSearchConsultPanel,"Invalid Time Input","Invalid Time",
                            JOptionPane.WARNING_MESSAGE);
                }
                else if (consultTime(consultTimeText.getText()).isBefore(openTimeObj) ||
                        consultTime(consultTimeText.getText()).isAfter(closeTimeObj)){
                    JOptionPane.showMessageDialog(docSearchConsultPanel,"Consultations are available " +
                            "between '07:00'-'17:00'", "Consultations Closed", JOptionPane.WARNING_MESSAGE);
                }
                else if (consultDurationText.getText().equals("")) {
                    JOptionPane.showMessageDialog(docSearchConsultPanel,"Enter hours for Consultation",
                            "Blank Duration input",JOptionPane.WARNING_MESSAGE);
                }
                else if(consultDurationText.getText().equals("0")){
                    JOptionPane.showMessageDialog(docSearchConsultPanel,"Please enter the required hours");
                }
                else {
                    docSearchConsultPanel.add(searchedDocListPanel);
                    searchedDocListPanel.validate();
                    searchedDocList(searchedDocListPanel);
                    consultStartTimeText.setText(consultTimeText.getText());
                    consultEndTimeText.setText(String.valueOf(consultEndTime()));
                }
            }
            else if(btnLabel.equals("Add Consultation")){
                if(patientNameText.getText().equals("")){
                    JOptionPane.showMessageDialog(consultFormPanel,"Please enter patient name",
                            "Blank Name Input",JOptionPane.WARNING_MESSAGE);
                }
                else if(!patientNameValidation(patientNameText.getText(),chars)){
                    JOptionPane.showMessageDialog(consultFormPanel,"Invalid Name Input",
                            "Invalid Name",JOptionPane.WARNING_MESSAGE);
                }
                else if(patientSurnameText.getText().equals("")){
                    JOptionPane.showMessageDialog(consultFormPanel,"Please enter patient  surname",
                            "Blank Surname Input",JOptionPane.WARNING_MESSAGE);
                }
                else if(!patientNameValidation(patientSurnameText.getText(),chars)){
                    JOptionPane.showMessageDialog(consultFormPanel,"Invalid Surname Input",
                            "Invalid Surname",JOptionPane.WARNING_MESSAGE);
                }
                else if(patientDOBText.getText().equals("")){
                    JOptionPane.showMessageDialog(consultFormPanel,"Please enter a Date Of Birth",
                            "Blank Date Of Birth Input",JOptionPane.WARNING_MESSAGE);
                }
                else if(!WestminsterSkinConsultationManager.program.dateValidation(patientDOBText.getText())){
                    JOptionPane.showMessageDialog(consultFormPanel,"Invalid Date Of Birth Input",
                            "Invalid Date",JOptionPane.WARNING_MESSAGE);
                }
                else if(patientMobileNoText.getText().equals("")){
                    JOptionPane.showMessageDialog(consultFormPanel,"Please enter a mobile number",
                            "Blank Mobile Number Input",JOptionPane.WARNING_MESSAGE);
                }
                else if(!WestminsterSkinConsultationManager.program.mobiNoValidation(patientMobileNoText.getText()) ||
                        (patientMobileNoText.getText()).charAt(0) != '0'){
                    JOptionPane.showMessageDialog(consultFormPanel,"Invalid Mobile Number Input",
                            "Invalid Mobile Number Input",JOptionPane.WARNING_MESSAGE);
                }
                else if(patientIdText.getText().equals("")){
                    JOptionPane.showMessageDialog(consultFormPanel,"Please enter a patient id",
                            "Blank Patient Id Input",JOptionPane.WARNING_MESSAGE);
                }
                else if(!patientIdCheck(patientIdText.getText())){
                    JOptionPane.showMessageDialog(consultFormPanel,"Invalid Patient Id Input",
                            "Invalid Id Input",JOptionPane.WARNING_MESSAGE);
                }
                else if(patientTimeAllocCheck().equals(patientIdText.getText())){
                    JOptionPane.showMessageDialog(consultFormPanel,"This patient already has a consultation to this time slot.\n" +
                            "Please select a different date or a time.");
                    remove(consultFormMainPanel);
                    add(mainConsultPanel);
                    validate();
                    repaint();
                }
                else if(consultCostText.getText().equals("")){
                    JOptionPane.showMessageDialog(consultFormPanel,"Please enter the cost",
                            "Blank Cost Input",JOptionPane.WARNING_MESSAGE);
                }
                else if(!consultCostCheck().equals(consultCostText.getText())){
                    if(patientCostCheck.size()==0){
                        JOptionPane.showMessageDialog(consultFormMainPanel,"Your cost is : "+
                                firstTimeCost(),"Invalid Cost",JOptionPane.WARNING_MESSAGE);

                    }
                    else{

                        if(patientCostCheck.contains(patientIdText.getText())){
                            JOptionPane.showMessageDialog(consultFormMainPanel,"Your cost is : "+
                                    notFirstTimeCost(),"Invalid Cost",JOptionPane.WARNING_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(consultFormMainPanel,"Your cost is : "+
                                    firstTimeCost(),"Invalid Cost",JOptionPane.WARNING_MESSAGE);
                        }

                    }
                }

                else if(consultNoteText.getText().isEmpty()){
                    JOptionPane.showMessageDialog(consultFormMainPanel,"Note is empty.",
                            "Blank Note",JOptionPane.WARNING_MESSAGE);
                }
                else if(imageInputText.getText().isEmpty()){
                    JOptionPane.showMessageDialog(consultFormMainPanel,"Please select an image.",
                            "Blank Image",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    //creating a patient object with the details.
                    Patient pat = new Patient(patientNameText.getText(),patientSurnameText.getText(),patientDOBText.getText()
                            ,patientMobileNoText.getText(),Integer.parseInt(patientIdText.getText()));
                    patients.add(pat);   //adding it to the arraylist of patients.

                    //encrypting the note input and the image path before passing to the relevant object.
                    String encryptedConsultNote = encrypting(consultNoteText.getText());
                    String encryptedConsultImage = encrypting(imageInputText.getText());

                    //creating a consultation object.
                    Consultation consult = new Consultation(consultStartTimeText.getText(),consultDateText.getText(),
                            Double.parseDouble(consultCostText.getText()),encryptedConsultNote,encryptedConsultImage,
                            Integer.parseInt(consultDurationText.getText()),endTime(),choseDoctorDetailLabel.getName(),
                            patientIdText.getText(),consultationId);
                    consultationIdArray.add(consultationId);   // adding consultation id to the arraylist contains con.ids.
                    consultations.add(consult);    //adding consultation object to the consultation arraylist
                    consultationId += 1;

                    //adding patient id input to the arraylist to check for the second time cost of the patients.
                    patientCostCheck.add(patientIdText.getText());

                    JOptionPane.showMessageDialog(consultFormMainPanel,"Consultation added successfully!");
                    printConsultRecords();
                    clearingAllTexts();
                    remove(consultFormMainPanel);
                    add(consultRecordsMainPanel);
                    validate();
                    repaint();
                }

            }
            else if(btnLabel.equals("BACK TO SEARCH")){
                remove(consultRecordsMainPanel);
                docSearchConsultPanel.remove(searchedDocListPanel);
                add(mainConsultPanel);
                validate();
                repaint();
            }
            else if(btnLabel.equals("MAIN MENU")){
                remove(consultRecordsMainPanel);
                docSearchConsultPanel.remove(searchedDocListPanel);
                add(mainPanel);
                validate();
                repaint();
            }
        }
    }

    private class clicklabelHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton o = (JButton)event.getSource();
            remove(mainConsultPanel);
            add(consultFormMainPanel);
            choseDoctorLabel(o.getName());
            validate();
            repaint();
        }
    }

    private class clickRecordsHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            JButton r = (JButton)event.getSource();
            consultRecordsData(r.getName());
        }
    }

    //To browse through folders and select an image.
    public class imageBrowseClick implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String btn = e.getActionCommand();
            if(btn.equals("Browse Image")){
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
                int results = fileChooser.showSaveDialog(null);
                if(results == JFileChooser.APPROVE_OPTION){
                    File selectedFile = fileChooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    imageInputText.setText(path);
                }
                else if(results == JFileChooser.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(consultFormPanel,"No image selected.");
                }
            }
        }
    }

    private class MouseActionHandler implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            docBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            consultBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            backBtnToMain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            docListSortBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            nextBtnToMainConsultPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            backBtnToMainDocPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addConsultationBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            consultRecordsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            backToSearchMainPanelBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            recordsToMainBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            imageBrowseBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            docBtn.setCursor(Cursor.getDefaultCursor());
            consultBtn.setCursor(Cursor.getDefaultCursor());
            backBtnToMain.setCursor(Cursor.getDefaultCursor());
            docListSortBtn.setCursor(Cursor.getDefaultCursor());
            nextBtnToMainConsultPanel.setCursor(Cursor.getDefaultCursor());
            backBtnToMainDocPanel.setCursor(Cursor.getDefaultCursor());
            searchBtn.setCursor(Cursor.getDefaultCursor());
            addConsultationBtn.setCursor(Cursor.getDefaultCursor());
            consultRecordsBtn.setCursor(Cursor.getDefaultCursor());
            backToSearchMainPanelBtn.setCursor(Cursor.getDefaultCursor());
            recordsToMainBtn.setCursor(Cursor.getDefaultCursor());
            imageBrowseBtn.setCursor(Cursor.getDefaultCursor());
        }
    }

    public static void main(String[] args){
        GUIDesign frame = new GUIDesign();
        frame.setTitle("New One");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,700);
        frame.setVisible(true);
    }

}
