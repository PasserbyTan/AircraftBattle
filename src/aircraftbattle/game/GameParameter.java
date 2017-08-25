package aircraftbattle.game;


public class GameParameter {

	// ��Ϸ����ʱ������������������������������������������������������������������������������������������������������������������������������������������������������������
	public static boolean isW, isA, isS, isD, isSpace;// �����Ƿ���
	public static boolean isWisdom;// �Ƿ����ǻ���ս��
	public static int toTalScore;// ������Ϸ�ܷ�
	public static int currentLevel;// ��ǰ�ؿ�
	public static boolean suspendFlag;//��Ϸ�߳���ͣ��־
	

	// ��Ϸ�̶�����������������������������������������������������������������������������������������������������������������������������������������������������������������������
	public static final int[] TARGET_SCORE = { 1000, 2000, 3000, 4000, 5000 };// Ŀ�����
	// ��Ҳ���
	public static final int PLAYER_SPPEED_X = 4;// ��Һ����ٶ�
	public static final int PLAYER_SPPEED_Y = 5;// ��������ٶ�
	public static final int PLAYER_SPAWN_X = 360;// ��ҷ����������
	public static final int PLAYER_SPAWN_Y = 820;// ��ҷ�����������
	public static final int START_SCORE = 0;// ��ʼ����
	public static final int START_LEVEL = 0;// ��ʼ�ؿ�
	public static final int START_HEALTH = 100;// ��ʼ����ֵ
	public static final int[] START_BULLETS = { 100, 200, 300, 400, 500 };// ��ʼ�ӵ�
	// ���˲���
	public static final int[] ENEMY_BULLETS = { 2, 5, 10, 15, 10000 };// �����ӵ�
	public static final int ENEMY_HEALTH = 3;// ��������ֵ
	public static final int ENEMY_SPEED_X = 0;// ���˺����ٶ�
	public static final int BOSS_SPEED_X = 1;// BOSS�����ٶ�
	public static final int BOSS_SPEED_Y = 1;// BOSS�����ٶ�
	public static final int[] ENEMY_SPEED_Y = { 2, 2, 3, 3, 1 };// ���������ٶ�

	// �ӵ�����
	public static final int BULLET_SPEED_X = 0;// �ӵ������ٶ�
	public static final int BULLET_SPEED_Y = 6;// �ӵ������ٶ�

	public enum BulletType {
		PLAYER, ENEMY
	}
	
	//ħ����Ʒ����
	public static final int MAGIC_SPEED_X = 0;//ħ����Ʒ�����ƶ��ٶ�
	public static final int MAGIC_SPEED_Y = 5;//ħ����Ʒ�����ƶ��ٶ�
	public static final int MAGIC_HEALTH_POINT = 5;//������
	public static final int MAGIC_BULLET_POINT = 10;//���ӵ���Ŀ
	public enum MagicType {
		HEALTH,BULLET
	}

	// ��ײ�˺�����
	public static final int BULLET_HURT_POINT = 3;// �ӵ��ͷɻ���ײ�˺�����
	public static final int AIRCRAFT_HURT_POINT = 5;// �ɻ��ͷɻ���ײ�˺�����
	//�ӵ����ӵ���ײֱ��˫���ӵ�����
	
	//�÷ֲ���
	public static final int BULLET_HURT_SCORE = 5;//�����ӵ�����
	public static final int ENEMY_HURT_SCORE = 20;//��������

	// �����������������������������������������������������������������������������������������������������������������������������������������������������������������������
	public static final int FRAME_WIDTH = 800;// �������
	public static final int FRAME_HIGHT = 1000;// ����߶�

	public static final int AIRCRAFTE_IMAGE_LENGTH = 80;// �ɻ�ͼƬ�߳� 80
	public static final int BULLET_IMAGE_WIDTH = 25;// �ӵ�ͼƬ��
	public static final int BULLET_IMAGE_HEIGHT = 15;// �ӵ�ͼƬ��
	public static final int MAGIC_IMAGE_HEIGHT = 30;// ħ����ƷͼƬ�߳� 

	public static final int NUMLABEL_WIDTH = 120;// ������ǩ����
	public static final int NUMLABEL_HIGHT = 50;// ������ǩ�߶�
	public static final String NUMLABEL_FONT = "Broadway";// ������ǩ����
	public static final int NUMLABEL_FONT_SIZE = 40;// ������ǩ�����С

}