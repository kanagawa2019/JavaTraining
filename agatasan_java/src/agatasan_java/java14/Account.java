package agatasan_java.java14;

/**
 * ��������
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 *
 */
public enum Account {

    NEW(1, "������V�K�쐬"), CHANGE(2, "���łɂ����������舵��");

    /** id */
    private final int id;
    /** ���� */
    private final String name;
    /** �\���`�� */
    private static final String DISPLAY_FORMAT = "%2d";

    /**
     * �R���X�g���N�^
     * 
     * @param id
     */
    private Account(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * ���������\��������擾
     * 
     * @return ���������\��������
     */
    public static String getSelectAccountString() {
        final StringBuffer sb = new StringBuffer();
        for (final Account t : Account.values()) {
            sb.append(String.format(DISPLAY_FORMAT, t.id)).append(".").append(t.name).append("\n");
        }
        return sb.toString();
    }

    /**
     * ���������擾
     * 
     * @param inputAccount���͒l
     * @return �������������݂��Ȃ��ꍇ��null�l
     */
    public static Account convertAccount(final String inputAccount) {
        if (inputAccount == null) {
            return null;
        }

        int account = 0;
        try {
            account = Integer.parseInt(inputAccount);
        } catch (NumberFormatException e) {
            return null;
        }

        for (final Account t : Account.values()) {
            if (t.id == account) {
                return t;
            }
        }
        return null;
    }

}
