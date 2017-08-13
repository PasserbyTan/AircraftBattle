package aircraftbattle.game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import aircraftbattle.util.GameUtil;

public class GameFrame extends JFrame {
	
	private JPanel startPanel, helpPanel, choosePanel, goPanel, endPanel;// 5����������
	private GamingPanel gamingPanel;// ��Ϸ����

	private PlayerThread playerThread;// ����߳�
	private EnemyThread enemyThread;// �����߳�
	private Detection detection;// ��ײ����߳�

	private GameService service;// ��Ϸ������
	private CardLayout cardLayout;// ���Ʋ���
	
	public GameFrame() {
		super();
		initialize();
	}

	private void initialize() {
		// ��Ϸ���������������
		this.setTitle("�ɻ�����ս");
		this.setSize(GameParameter.FRAME_WIDTH, GameParameter.FRAME_HIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
	
		// ������Ϸ������
		service = new GameService();

		// ����6����ͬ��JPanel
		StartPanelBuild(this);
		HelpPanelBuild(this);
		ChoosePanelBuild(this);
		GamingPanelBuild(this, 0);
		GoPanelBuild(this);
		EndPanelBuild(this);

		// ��Ӽ��̼�����
		setFocusable(true);// ����Ϊ��������Ӧ�����¼�
		addKeyListener(new KeyAdapter() {
			// ���̰���
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					GameParameter.isW = true;
					break;
				case KeyEvent.VK_S:
					GameParameter.isS = true;
					break;
				case KeyEvent.VK_A:
					GameParameter.isA = true;
					break;
				case KeyEvent.VK_D:
					GameParameter.isD = true;
					break;
				case KeyEvent.VK_SPACE:
					GameParameter.isSpace = true;
					break;
				}
			}

			// �����ɿ�
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					GameParameter.isW = false;
					break;
				case KeyEvent.VK_S:
					GameParameter.isS = false;
					break;
				case KeyEvent.VK_A:
					GameParameter.isA = false;
					break;
				case KeyEvent.VK_D:
					GameParameter.isD = false;
					break;
				case KeyEvent.VK_SPACE:
					GameParameter.isSpace = false;
					break;
				}
			}

		});
	}

	private void StartPanelBuild(GameFrame gameFrame) {
		// ������������Panel
		startPanel = GameUtil.getBackgroundJPanel();

		// ����Panel����
		JLabel title = GameUtil.getIconSizeJLabel("img\\start1.png", 100, 100);
		JButton button1 = GameUtil.getIconSizeJButton("img\\start2.png", 100, 650);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(gameFrame.getContentPane(), "choose");

			}
		});
		JButton button2 = GameUtil.getIconSizeJButton("img\\start3.png", 450, 650);
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(gameFrame.getContentPane(), "help");

			}
		});

		// �����ݼ���Panel
		startPanel.add(button1);
		startPanel.add(button2);
		startPanel.add(title);

		// ��Panel����Frame
		gameFrame.add(startPanel, "start");
	}

	private void HelpPanelBuild(GameFrame gameFrame) {
		helpPanel = GameUtil.getBackgroundJPanel();

		JLabel label1 = GameUtil.getIconSizeJLabel("img\\help1.png", 300, 50);
		JLabel label2 = GameUtil.getIconSizeJLabel("img\\help2.png", 100, 180);
		JButton button1 = GameUtil.getIconSizeJButton("img\\help3.png", 100, 850);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(gameFrame.getContentPane(), "start");
			}
		});

		helpPanel.add(label1);
		helpPanel.add(label2);
		helpPanel.add(button1);
		gameFrame.add(helpPanel, "help");
	}

	private void ChoosePanelBuild(GameFrame gameFrame) {
		choosePanel = GameUtil.getBackgroundJPanel();

		JLabel label1 = GameUtil.getIconSizeJLabel("img\\choose1.png", 80, 80);
		JButton startbutton = GameUtil.getIconSizeJButton("img\\choose2.png", 470, 800);
		startbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �л�����һ������ǰ������ҷɻ�����
				try {
					service.getPlayer().changeImage(GameParameter.isWisdom);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				GameParameter.suspendFlag = true;// �߳�ѭ������
				detection = new Detection();
				playerThread = new PlayerThread();
				enemyThread = new EnemyThread();
				detection.start();
				playerThread.start();
				enemyThread.start();

				cardLayout.show(gameFrame.getContentPane(), "gaming");
			}
		});
		// ѡ��ս��
		JLabel chooseframe = GameUtil.getIconSizeJLabel("img\\Frame.png", 50, 323);// ��ʼ��ѡ���
		JButton one = GameUtil.getIconSizeJButton("img\\ChoosePlayer1.png", 80, 350);
		one.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooseframe.setLocation(50, 323);
				GameParameter.isWisdom = false;// ѡ��������ս��
			}
		});
		JButton two = GameUtil.getIconSizeJButton("img\\ChoosePlayer2.png", 421, 350);
		two.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooseframe.setLocation(390, 323);
				GameParameter.isWisdom = true;// ѡ���ǻ���ս��
			}
		});

		choosePanel.add(label1);
		choosePanel.add(startbutton);
		choosePanel.add(one);
		choosePanel.add(two);
		choosePanel.add(chooseframe);
		gameFrame.add(choosePanel, "choose");

	}

	private void GamingPanelBuild(GameFrame gameFrame, int level) {
		gamingPanel = new GamingPanel();

		// TestButton������������������������������������������������������������������������������������������������������������������������
		JButton go = TestButton(40, 40, 0, 0);
		go.setText("G");
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(gameFrame.getContentPane(), "go");
			}
		});
		gamingPanel.add(go);

		JButton end = TestButton(40, 40, 100, 0);
		end.setText("E");
		end.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(gameFrame.getContentPane(), "end");
			}
		});
		gamingPanel.add(end);
		// ��������������������������������������������������������������������������������������������������������������������������������������������

		gameFrame.add(gamingPanel, "gaming");
	}

	private void GoPanelBuild(GameFrame gameFrame) {
		goPanel = GameUtil.getBackgroundJPanel();

		JLabel label1 = GameUtil.getIconSizeJLabel("img\\Go1.png", 100, 100);
		JButton button1 = GameUtil.getIconSizeJButton("img\\Go2.png", 400, 720);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (GameParameter.currentLevel == 4)
					System.exit(0);
				service.clear();// ����
				GamingPanelBuild(gameFrame, ++GameParameter.currentLevel);// ������һ��
				cardLayout.show(gameFrame.getContentPane(), "gaming");
			}
		});

		goPanel.add(label1);
		goPanel.add(button1);
		gameFrame.add(goPanel, "go");
	}

	private void EndPanelBuild(GameFrame gameFrame) {
		endPanel = GameUtil.getBackgroundJPanel();

		JLabel label1 = GameUtil.getIconSizeJLabel("img\\End1.png", 100, 100);
		JButton button1 = GameUtil.getIconSizeJButton("img\\End2.png", 100, 720);
		JButton button2 = GameUtil.getIconSizeJButton("img\\End3.png", 450, 720);

		// ������Ϸ
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// ���¿�ʼ
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameParameter.suspendFlag = false;// �߳�ѭ��������
				GameParameter.toTalScore = GameParameter.START_SCORE;// ���¼Ʒ�
				GameParameter.currentLevel = GameParameter.START_LEVEL;// �¹ؿ�
				GamingPanelBuild(gameFrame, GameParameter.START_LEVEL);// ������ʼ�ؿ�
				service.clear();// ����

				cardLayout.show(gameFrame.getContentPane(), "choose");
			}
		});

		endPanel.add(button1);
		endPanel.add(button2);
		endPanel.add(label1);
		gameFrame.add(endPanel, "end");
	}

	private class GamingPanel extends JPanel {
		JLabel scorelabel, targetlabel, bulletlabel, healthlabel;// ������ǩ

		public GamingPanel() {
			super();
			initialize();

			// �������ս��
			try {
				service.generatePlayer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		void initialize() {
			setLayout(null);
			setBackground(Color.white);
			setSize(GameParameter.FRAME_WIDTH, GameParameter.FRAME_HIGHT);

			JLabel label1 = GameUtil.getIconSizeJLabel("img\\gaming1.png", 50, 50);
			JLabel label2 = GameUtil.getIconSizeJLabel("img\\gaming2.png", 620, 50);
			JLabel label3 = GameUtil.getIconSizeJLabel("img\\gaming3.png", 50, 860);
			JLabel label4 = GameUtil.getIconSizeJLabel("img\\gaming4.png", 620, 860);

			scorelabel = GameUtil.getNumJLabel(180, 55);
			targetlabel = GameUtil.getNumJLabel(500, 55);
			bulletlabel = GameUtil.getNumJLabel(180, 860);
			healthlabel = GameUtil.getNumJLabel(520, 860);

			targetlabel.setForeground(Color.blue);
			scorelabel.setForeground(Color.green);
			healthlabel.setForeground(Color.red);

			// ��ʼ����ǩ��ֵ
			scorelabel.setText(String.valueOf(GameParameter.START_SCORE));
			targetlabel.setText(String.valueOf(GameParameter.TARGET_SCORE[GameParameter.currentLevel]));
			bulletlabel.setText(String.valueOf(GameParameter.START_BULLETS[GameParameter.currentLevel]));
			healthlabel.setText(String.valueOf(GameParameter.START_HEALTH));

			add(label1);
			add(label2);
			add(label3);
			add(label4);

			add(scorelabel);
			add(targetlabel);
			add(bulletlabel);
			add(healthlabel);

		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
	
			paintLabels();// ��ǩ��ֵ�仯

			try {
				service.paint(g);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		void paintLabels() {
			bulletlabel.setText(String.valueOf(service.getPlayer().getBulletsNum()));
			healthlabel.setText(String.valueOf(service.getPlayer().getHealth()));
			scorelabel.setText(String.valueOf(GameParameter.toTalScore));
		}
	}

	class PlayerThread extends Thread {
		public void run() {
			while (GameParameter.suspendFlag) {
				try {
					//�����Ϊ�Լ���һ���߳�
					service.playerBulletsGenerate();
					service.playerMove();
					service.playerBulletsMove();
								
					// �ػ�
					repaint();

					// ��ͣ
					sleep(10);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	class EnemyThread extends Thread {
		public void run() {
			while (GameParameter.suspendFlag) {
				try {
					//�Զ������ɵ����Լ�������Ϊ
					service.generateEnemy();
					service.enemyBulletsGenerate();

					// ��ͣ
					sleep(10);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	class Detection extends Thread {
		public void run() {
			while (GameParameter.suspendFlag) {
				try {
					service.collisionDetecte();// ����������ײ���
					service.deathRemove(); //�Ƴ���������(�Ƴ����ƶ�������һ���߳���)
					
					service.othersMove();//�ƶ�
				
					if (service.gameEndDetecte())
						cardLayout.show(getContentPane(), "end");
					if(service.nextLevelDetecte())
						cardLayout.show(getContentPane(), "go");
						
					//��ͣ
					sleep(10);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public JButton TestButton(int W, int H, int X, int Y) {
		JButton button = new JButton();
		button.setSize(W, H);
		button.setLocation(X, Y);
		return button;
	}
}
