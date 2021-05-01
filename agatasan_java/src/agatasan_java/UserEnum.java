package agatasan_java;

public class UserEnum {
    /** �ԍ�_���� */
    public static final int NUMBER_NAME = 1;
    /** �ԍ�_���� */
    public static final int NUMBER_SEX = 2;
    /** �ԍ�_���N���� */
    public static final int NUMBER_BIRTHDAY = 3;
    /** �ԍ�_���ӌ��� */
    public static final int NUMBER_FAVORITELANGUAGE = 4;

    /**
     * �����o�ϐ�������擾
     * 
     * @return �����o�ϐ�������擾
     */
    public static String getSelectPropertyString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(Integer.valueOf(NUMBER_NAME)).append(".����").append("\n");
        sb.append(Integer.valueOf(NUMBER_SEX)).append(".����").append("\n");
        sb.append(Integer.valueOf(NUMBER_BIRTHDAY)).append(".���N����").append("\n");
        sb.append(Integer.valueOf(NUMBER_FAVORITELANGUAGE)).append(".���ӌ���").append("\n");
        return sb.toString();
    }
}
