package agatasan_java.java14;

/**
 * ������舵�����j���[
 * 
 * @author �H�c ���I
 * @version 1.0 2020/12/29 �V�K�쐬
 * @version 1.1 2021/05/23 No.95�`102�w�E�Ή�
 * @version 1.2 2021/05/30 No.110�`122�w�E�Ή�
 *
 */
public enum AccountHandlingMenu {

    DEPOSIT(1, "����"), TRANSFER(2, "�U��"), WITHDRAW(3, "�o��"), BALANCE(4, "�c���\��"), HISTORY(5, "���������\��");

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
    private AccountHandlingMenu(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * ���[�h�\��������擾
     * 
     * @return ���[�h�\��������
     */
    public static String getSelectBankString() {
        final StringBuffer sb = new StringBuffer();
        for (final AccountHandlingMenu t : AccountHandlingMenu.values()) {
            sb.append(String.format(DISPLAY_FORMAT, t.id)).append(".").append(t.name).append("\n");
        }
        return sb.toString();
    }

    /**
     * ���[�h�擾
     * 
     * @param inputBank���͒l
     * @return ���[�h�����݂��Ȃ��ꍇ��null�l
     */
    public static AccountHandlingMenu convertBank(final String inputBank) {
        if (inputBank == null) {
            return null;
        }

        int bank = 0;
        try {
            bank = Integer.parseInt(inputBank);
        } catch (NumberFormatException e) {
            return null;
        }

        for (final AccountHandlingMenu t : AccountHandlingMenu.values()) {
            if (t.id == bank) {
                return t;
            }
        }
        return null;
    }

    /**
     * id���擾
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * name���擾
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

}
