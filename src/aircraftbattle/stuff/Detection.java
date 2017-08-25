package aircraftbattle.stuff;

import aircraftbattle.game.GameFrame;
import aircraftbattle.game.GameParameter;

public class Detection extends Thread {

	private GameFrame gameFrame;
	
	public Detection(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public void run() {
		while (GameParameter.suspendFlag) {
			try {
				gameFrame.getService().collisionDetecte();// ����������ײ���
				gameFrame.getService().deathRemove(); //�Ƴ���������(�Ƴ����ƶ�������һ���߳���)
				
				gameFrame.getService().othersMove();//�ƶ�
			
				if (gameFrame.getService().gameEndDetecte())
					gameFrame.CardChange("end");
				if(gameFrame.getService().nextLevelDetecte())
					gameFrame.CardChange("go");
					
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
