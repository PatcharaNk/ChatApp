package com.ui;

import com.socket.Message;
import com.socket.Renderer;
import com.socket.ChatRoomRenderer;
import com.socket.SocketClient;
import com.socket.UserList;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.ObjectInputStream;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

public class ChatFrame extends javax.swing.JFrame {

    public SocketClient client;
    public int port;
    public String serverAddr, username, password,numAvatar;
    public Thread clientThread;
    public DefaultListModel model;
    public DefaultListModel chatModel;
    public File file;
    public HistoryFrame historyFrame;
    public ObjectInputStream in;

    public ImageIcon unknowIcon = new ImageIcon(getClass().getResource("/com/ui/user_unknown.png"));
    public ImageIcon allIcon = new ImageIcon(getClass().getResource("/com/ui/img/All.png"));

    public ImageIcon notiIcon = new ImageIcon(getClass().getResource("/com/ui/noti.png"));
    public int xMouse;
    public int yMouse;

    public ChatFrame() {
        initComponents();
        this.setTitle("jMessenger");
        jLoginBLabel.requestFocus();
        model.addElement(new UserList("All", allIcon));
        jUserOnlineList.setCellRenderer(new Renderer());
        jUserOnlineList.setModel(model);  
        jUserOnlineList.setSelectedIndex(0);
        jChatList.setCellRenderer(new ChatRoomRenderer());
        jChatList.setModel(chatModel);
        jUserNameTextField.setForeground(Color.GRAY);
        jUserNameTextField.setFocusable(true);
        jPasswordField2.setForeground(Color.GRAY);
        Connect();

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    client.send(new Message("message", username, ".bye", "SERVER"));
                    clientThread.stop();
                } catch (Exception ex) {
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    public boolean isWin32() {
        return System.getProperty("os.name").startsWith("Windows");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jChatRoomPanel = new javax.swing.JPanel();
        jLogOutLabel = new javax.swing.JLabel();
        jMyNameLabel = new javax.swing.JLabel();
        jFileLabel = new javax.swing.JLabel();
        jLocaltionFileTextField = new javax.swing.JTextField();
        jChatListScrollPane = new javax.swing.JScrollPane();
        jChatList = new javax.swing.JList();
        jTabPanel2 = new javax.swing.JPanel();
        jXLabel = new javax.swing.JLabel();
        j_Label = new javax.swing.JLabel();
        jUserScrollPane = new javax.swing.JScrollPane();
        jUserOnlineList = new javax.swing.JList();
        jSelectFileButton = new javax.swing.JButton();
        jSendFileButton = new javax.swing.JButton();
        jMessageScrollPane = new javax.swing.JScrollPane();
        jMessangeTextArea = new javax.swing.JTextArea();
        jChatBGLabel = new javax.swing.JLabel();
        jLoginPanel = new javax.swing.JPanel();
        jUserNameTextField = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jXLabel1 = new javax.swing.JLabel();
        j_Label1 = new javax.swing.JLabel();
        jLoginBLabel = new javax.swing.JLabel();
        jSignupBLabel = new javax.swing.JLabel();
        jLoginBGLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jChatRoomPanel.setVisible(false);
        jChatRoomPanel.setBackground(new java.awt.Color(204, 204, 204));
        jChatRoomPanel.setToolTipText("");
        jChatRoomPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        jChatRoomPanel.setVerifyInputWhenFocusTarget(false);
        jChatRoomPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLogOutLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLogOutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLogOutLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLogOutLabelMouseEntered(evt);
            }
        });
        jChatRoomPanel.add(jLogOutLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 120, 30));

        jMyNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMyNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        jMyNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMyNameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/user_unknown.png"))); // NOI18N
        jMyNameLabel.setText("Me");
        jChatRoomPanel.add(jMyNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 32, 138, 27));

        jFileLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jFileLabel.setVisible(false);
        jFileLabel.setForeground(new java.awt.Color(102, 102, 102));
        jFileLabel.setText("File :");
        jChatRoomPanel.add(jFileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 560, -1, 20));

        jLocaltionFileTextField.setEditable(false);
        jLocaltionFileTextField.setVisible(false);
        jLocaltionFileTextField.setBackground(new java.awt.Color(255, 255, 255));
        jLocaltionFileTextField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLocaltionFileTextField.setForeground(new java.awt.Color(102, 102, 102));
        jLocaltionFileTextField.setBorder(null);
        jLocaltionFileTextField.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jChatRoomPanel.add(jLocaltionFileTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 560, 320, 20));

        jChatListScrollPane.setAutoscrolls(true);

        jChatList.setModel((chatModel = new DefaultListModel()));
        jChatListScrollPane.setViewportView(jChatList);

        jChatRoomPanel.add(jChatListScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 62, 647, 462));

        jTabPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jTabPanel2.setOpaque(false);
        jTabPanel2.setToolTipText("");
        jTabPanel2.setPreferredSize(new java.awt.Dimension(800, 20));
        jTabPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTabPanel2MouseDragged(evt);
            }
        });
        jTabPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabPanel2MousePressed(evt);
            }
        });
        jTabPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jXLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/close_white_18x18.png"))); // NOI18N
        jXLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jXLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXLabelMouseClicked(evt);
            }
        });
        jTabPanel2.add(jXLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 2, 20, 25));

        j_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        j_Label.setForeground(new java.awt.Color(255, 255, 255));
        j_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j_Label.setText("_");
        j_Label.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        j_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        j_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j_LabelMouseClicked(evt);
            }
        });
        jTabPanel2.add(j_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, -3, 20, 30));

        jChatRoomPanel.add(jTabPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 26));

        jUserScrollPane.setBackground(new java.awt.Color(255, 255, 255));

        jUserOnlineList.setBackground(new java.awt.Color(73, 73, 73));
        jUserOnlineList.setForeground(new java.awt.Color(255, 255, 255));
        jUserOnlineList.setModel((model = new DefaultListModel()));
        jUserOnlineList.setSelectionBackground(new java.awt.Color(102, 153, 255));
        jUserOnlineList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jUserOnlineListMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jUserOnlineListMouseReleased(evt);
            }
        });
        jUserScrollPane.setViewportView(jUserOnlineList);

        jUserScrollPane.getViewport();
        jUserScrollPane.setOpaque(false);

        jChatRoomPanel.add(jUserScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 62, 137, 534));

        jSelectFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/Paperclip.png"))); // NOI18N
        jSelectFileButton.setBorder(null);
        jSelectFileButton.setContentAreaFilled(false);
        jSelectFileButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSelectFileButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jSelectFileButton.setBorderPainted(false);
        jSelectFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSelectFileButtonActionPerformed(evt);
            }
        });
        jChatRoomPanel.add(jSelectFileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 549, 30, 30));

        jSendFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/send_grey_27x27.png"))); // NOI18N
        jSendFileButton.setBorder(null);
        jSendFileButton.setBorderPainted(false);
        jSendFileButton.setContentAreaFilled(false);
        jSendFileButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSendFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSendFileButtonActionPerformed(evt);
            }
        });
        jChatRoomPanel.add(jSendFileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 550, 30, -1));

        jMessageScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        jMessageScrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jMessageScrollPane.setMaximumSize(new java.awt.Dimension(550, 60));

        DefaultCaret caret2 = (DefaultCaret) jMessangeTextArea.getCaret();
        caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jMessangeTextArea.setWrapStyleWord(true);
        jMessangeTextArea.setLineWrap(true);
        jMessangeTextArea.setColumns(20);
        jMessangeTextArea.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMessangeTextArea.setForeground(new java.awt.Color(204, 204, 204));
        jMessangeTextArea.setRows(1);
        jMessangeTextArea.setText("Type your message . . .");
        jMessangeTextArea.setMargin(new java.awt.Insets(4, 4, 4, 4));
        jMessangeTextArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMessangeTextAreaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jMessangeTextAreaFocusLost(evt);
            }
        });
        jMessangeTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMessangeTextAreaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jMessangeTextAreaKeyReleased(evt);
            }
        });
        jMessageScrollPane.setViewportView(jMessangeTextArea);

        jChatRoomPanel.add(jMessageScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 544, 550, 40));

        jChatBGLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/ChatBG.jpg"))); // NOI18N
        jChatBGLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jChatRoomPanel.add(jChatBGLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        jLoginPanel.setBackground(new java.awt.Color(0, 0, 0));
        jLoginPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLoginPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        jLoginPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        jUserNameTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jUserNameTextField.setForeground(new java.awt.Color(153, 153, 153));
        jUserNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jUserNameTextField.setText("Username");
        jUserNameTextField.setBorder(null);
        jUserNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jUserNameTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUserNameTextFieldFocusLost(evt);
            }
        });
        jUserNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jUserNameTextFieldKeyPressed(evt);
            }
        });

        jPasswordField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField2.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField2.setText("Password");
        jPasswordField2.setBorder(null);
        jPasswordField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusLost(evt);
            }
        });
        jPasswordField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField2KeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 25));
        jPanel1.setOpaque(false);
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jXLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/close_white_18x18.png"))); // NOI18N
        jXLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jXLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jXLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 2, 20, 25));

        j_Label1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        j_Label1.setForeground(new java.awt.Color(255, 255, 255));
        j_Label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j_Label1.setText("_");
        j_Label1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        j_Label1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        j_Label1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j_Label1MouseClicked(evt);
            }
        });
        jPanel1.add(j_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, -3, 20, 30));

        jLoginBLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLoginBLabel.requestFocus();
        jLoginBLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLoginBLabelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLoginBLabelMousePressed(evt);
            }
        });

        jSignupBLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSignupBLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSignupBLabelMouseClicked(evt);
            }
        });

        jLoginBGLabel.setBackground(new java.awt.Color(255, 255, 255));
        jLoginBGLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ui/LoginBG.jpg"))); // NOI18N

        javax.swing.GroupLayout jLoginPanelLayout = new javax.swing.GroupLayout(jLoginPanel);
        jLoginPanel.setLayout(jLoginPanelLayout);
        jLoginPanelLayout.setHorizontalGroup(
            jLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(jLoginPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jUserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLoginPanelLayout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(jLoginBLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLoginPanelLayout.createSequentialGroup()
                .addGap(10, 372, Short.MAX_VALUE)
                .addComponent(jSignupBLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(369, 369, 369))
            .addGroup(jLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLoginBGLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLoginPanelLayout.setVerticalGroup(
            jLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLoginPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(jUserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLoginBLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jSignupBLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
            .addGroup(jLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLoginBGLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jChatRoomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jChatRoomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Connect() {
        serverAddr = "localhost";
        System.out.print("Connecting");
        port = 13000;
        System.out.print(".");
        
        if (!serverAddr.isEmpty()) {
            try {
                client = new SocketClient(this);
                System.out.print(".");
                clientThread = new Thread(client);
                System.out.print(".");
                clientThread.start();
                System.out.print(".\n");
                client.send(new Message("test", "testUser", "testContent", "SERVER"));
            } catch (Exception ex) {
                //jChatTextArea.append("[Application > Me] : Server not found\n");
                chatModel.addElement(new Message("Ex", "Application", "Server not found", username));
                JOptionPane.showMessageDialog(this, "Connect error");
                System.out.print(".");
            }
        }
    }

    private void Login() {
        username = jUserNameTextField.getText();
        password = jPasswordField2.getText();
        if (!username.isEmpty() && !password.isEmpty()) {
            client.send(new Message("login", username, password, "SERVER"));
        }
        boolean cr = false;
        while (cr == false) {
            cr = client.checkReady();
            System.out.print("");
        }
        System.out.println("ckR: " + cr + " ckL: " + client.checkLogin());
        if (client.checkLogin() == false) {
            JOptionPane.showMessageDialog(null, "Wrong password or Please Signup.", "Login Fail", JOptionPane.ERROR_MESSAGE);
        } else {
            jChatRoomPanel.setVisible(true);
            jLoginPanel.setVisible(false);
            jMyNameLabel.setText(username);
            in = client.In;

        }

    }

    private void sendMsg() {
        String msg = jMessangeTextArea.getText().trim();
        UserList user = (UserList) jUserOnlineList.getSelectedValue();
        String target = user.getUsername();
        if (!msg.isEmpty() && !target.isEmpty()) {
            jMessangeTextArea.setText("");
            client.send(new Message("message", username, msg, target));
        }
    }

    private void sendFile() {
        long size = file.length();
        UserList us = (UserList) jUserOnlineList.getSelectedValue();
        if (size < 120 * 1024 * 1024) {
            client.send(new Message("upload_req", username, file.getName(), us.getUsername()));
        } else {
            //jChatTextArea.append("[Application > Me] : File is size too large\n");
            chatModel.addElement(new Message("Ex", "Application", "File is size too large", username));
        }
        jFileLabel.setVisible(false);
        jLocaltionFileTextField.setText("");
    }

    private void jSendFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSendFileButtonActionPerformed
        if (jFileLabel.isVisible()) {
            sendFile();
        } else {
            sendMsg();
        }
    }//GEN-LAST:event_jSendFileButtonActionPerformed

    private void jMessangeTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMessangeTextAreaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !evt.isShiftDown()) {
            sendMsg();
            if (jFileLabel.isVisible()) {
                sendFile();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && evt.isShiftDown()) {
            jMessangeTextArea.append("\n");
        }
    }//GEN-LAST:event_jMessangeTextAreaKeyPressed

    private void jUserNameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jUserNameTextFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Login();
        }
    }//GEN-LAST:event_jUserNameTextFieldKeyPressed

    private void jXLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXLabelMouseClicked
        // TODO add your handling code here:
        client.send(new Message("message", username, ".bye", "SERVER"));
        clientThread.stop();
        System.exit(0);
    }//GEN-LAST:event_jXLabelMouseClicked

    private void jXLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXLabel1MouseClicked
        // TODO add your handling code here:
        clientThread.stop();       
        System.exit(0);
    }//GEN-LAST:event_jXLabel1MouseClicked

    private void j_Label1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j_Label1MouseClicked
        // TODO add your handling code here:
        setState(1);
    }//GEN-LAST:event_j_Label1MouseClicked

    private void j_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j_LabelMouseClicked
        // TODO add your handling code here:
        setState(1);
    }//GEN-LAST:event_j_LabelMouseClicked

    private void jTabPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabPanel2MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jTabPanel2MouseDragged

    private void jTabPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabPanel2MousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jTabPanel2MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);

    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jMessangeTextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMessangeTextAreaKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !evt.isShiftDown()) {
            jMessangeTextArea.setText("");
        }
    }//GEN-LAST:event_jMessangeTextAreaKeyReleased

    private void jUserNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUserNameTextFieldFocusLost
        // TODO add your handling code here:
        if (jUserNameTextField.getText().equals("")) {
            jUserNameTextField.setText("Username");
            jUserNameTextField.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jUserNameTextFieldFocusLost

    private void jPasswordField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusLost
        // TODO add your handling code here:
        if (jPasswordField2.getText().equals("")) {
            jPasswordField2.setText("Password");
            jPasswordField2.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jPasswordField2FocusLost

    private void jLoginBLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLoginBLabelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLoginBLabelMousePressed

    private void jLoginBLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLoginBLabelMouseClicked
        // TODO add your handling code here:
        Login();
    }//GEN-LAST:event_jLoginBLabelMouseClicked

    private void jSignupBLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSignupBLabelMouseClicked
        // TODO add your handling code here:
        username = jUserNameTextField.getText();
        password = jPasswordField2.getText();

        if (!username.isEmpty() && !password.isEmpty()) {
            client.send(new Message("signup", username, password, "SERVER"));
        }
        boolean cr = false;
        while (cr == false) {
            cr = client.checkReady();
            System.out.print("");
        }
//            System.out.print("");
//            System.out.println("ckR: "+cr+" ckL: "+client.checkLogin());
        if (client.checkLogin() == false) {
            JOptionPane.showMessageDialog(null, username + " is already use.", "Signup Fail", JOptionPane.ERROR_MESSAGE);
        } else {
            jChatRoomPanel.setVisible(true);
            jMyNameLabel.setText(username);
            jLoginPanel.setVisible(false);

        }
    }//GEN-LAST:event_jSignupBLabelMouseClicked

    private void jSelectFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSelectFileButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showDialog(this, "Select File");
        file = fileChooser.getSelectedFile();

        if (file != null) {
            if (!file.getName().isEmpty()) {
                jFileLabel.setVisible(true);
                jLocaltionFileTextField.setVisible(true);
                String str;

                if (jLocaltionFileTextField.getText().length() > 30) {
                    String t = file.getPath();
                    str = t.substring(0, 20) + " [...] " + t.substring(t.length() - 20, t.length());
                } else {
                    str = file.getPath();
                }
                jLocaltionFileTextField.setText(str);
            }
        }
    }//GEN-LAST:event_jSelectFileButtonActionPerformed

    private void jLogOutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogOutLabelMouseClicked
        // TODO add your handling code here:
        client.send(new Message("message", username, ".bye", "SERVER"));
        clientThread.stop();
        //jChatTextArea.setText("");
        chatModel.removeAllElements();
        model.removeAllElements();
        model.addElement(new UserList("All", allIcon));
        jChatRoomPanel.setVisible(false);
        jLoginPanel.setVisible(true);
        jUserOnlineList.setSelectedIndex(0);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            System.out.println("fuck u");
        }
        Connect();
    }//GEN-LAST:event_jLogOutLabelMouseClicked

    private void jMessangeTextAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMessangeTextAreaFocusLost
        // TODO add your handling code here:
        if(jMessangeTextArea.getText().equals("")){
            jMessangeTextArea.setText("Type your message . . .");
            jMessangeTextArea.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_jMessangeTextAreaFocusLost

    private void jMessangeTextAreaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMessangeTextAreaFocusGained
        // TODO add your handling code here:
          if(jMessangeTextArea.getText().equals("Type your message . . .")){
            jMessangeTextArea.setText("");
            jMessangeTextArea.setForeground(Color.DARK_GRAY);
        }
    }//GEN-LAST:event_jMessangeTextAreaFocusGained

    private void jPasswordField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusGained
        // TODO add your handling code here:
        if (jPasswordField2.getForeground() == Color.GRAY) {
            jPasswordField2.setText("");
            jPasswordField2.setForeground(Color.DARK_GRAY);
        }
    }//GEN-LAST:event_jPasswordField2FocusGained

    private void jUserNameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUserNameTextFieldFocusGained
        // TODO add your handling code here:
         if (jUserNameTextField.getForeground() == Color.GRAY) {
            jUserNameTextField.setText("");
            jUserNameTextField.setForeground(Color.DARK_GRAY);
        }
    }//GEN-LAST:event_jUserNameTextFieldFocusGained

    private void jPasswordField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField2KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Login();
        }
    }//GEN-LAST:event_jPasswordField2KeyPressed

    private void jUserOnlineListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jUserOnlineListMousePressed
        // TODO add your handling code here:
        UserList user = (UserList) jUserOnlineList.getSelectedValue();
        System.out.println(user.getImgUser());
        String target = user.getUsername();
        client.send(new Message("jUserlist", username, "", target));
        int index = -1;
        for (int i = 0; i < model.getSize(); i++) {
            System.out.println("Yeah = > " + ((UserList) model.get(i)).getUsername());
            if (((UserList) model.get(i)).getUsername().equals(target)) {
                index = i;
            }
        }
        UserList newUser = new UserList(target, user.getImgUser(), null);
        model.set(index, newUser);

    }//GEN-LAST:event_jUserOnlineListMousePressed

    private void jUserOnlineListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jUserOnlineListMouseReleased
        // TODO add your handling code here:
        Message msgBug = new Message("", "", "", "", allIcon);
        chatModel.addElement(msgBug);
        chatModel.removeElement(msgBug);
    }//GEN-LAST:event_jUserOnlineListMouseReleased

    private void jLogOutLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogOutLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLogOutLabelMouseEntered

    public static void main(String args[]) throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("Look & Feel exception");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ChatFrame().setVisible(true);
                } catch (Exception ex) {
                    System.out.println("ERROR");
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jChatBGLabel;
    public javax.swing.JList<String> jChatList;
    private javax.swing.JScrollPane jChatListScrollPane;
    private javax.swing.JPanel jChatRoomPanel;
    private javax.swing.JLabel jFileLabel;
    public javax.swing.JTextField jLocaltionFileTextField;
    private javax.swing.JLabel jLogOutLabel;
    private javax.swing.JLabel jLoginBGLabel;
    private javax.swing.JLabel jLoginBLabel;
    private javax.swing.JPanel jLoginPanel;
    private javax.swing.JScrollPane jMessageScrollPane;
    private javax.swing.JTextArea jMessangeTextArea;
    public javax.swing.JLabel jMyNameLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField2;
    public javax.swing.JButton jSelectFileButton;
    public javax.swing.JButton jSendFileButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jSignupBLabel;
    private javax.swing.JPanel jTabPanel2;
    public javax.swing.JTextField jUserNameTextField;
    public javax.swing.JList jUserOnlineList;
    private javax.swing.JScrollPane jUserScrollPane;
    private javax.swing.JLabel jXLabel;
    private javax.swing.JLabel jXLabel1;
    private javax.swing.JLabel j_Label;
    private javax.swing.JLabel j_Label1;
    // End of variables declaration//GEN-END:variables
}
