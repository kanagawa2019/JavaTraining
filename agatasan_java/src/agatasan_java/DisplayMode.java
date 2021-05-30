package agatasan_java;

/**
 * ��㏈�����[�h�̗񋓌^�N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/30 �V�K�쐬
 * @version 1.1 2021/05/16 �\���`���������W�J
 * @version 1.2 2021/05/21 No.105�w�E�Ή�
 * @version 1.3 2021/05/29 No.108�w�E�Ή�
 */
public enum DisplayMode {

                         MULTIPLICATION_TABLE(1, "���\��"), MULTIPLICATION_LINE(2, "�w��̒i�̂ݕ\��"),
                         SWITCHING(3, "���[�h�ؑ�(%S)"), END(9, "�I��");

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
    private DisplayMode(final int id, final String name) {
        this.id   = id;
        this.name = name;
    }

    /**
     * ���[�h�\��������擾
     * 
     * @return ���[�h�\��������
     */
    public static String getSelectDisplayModeString(boolean displaySwitching) {
        final StringBuffer sb = new StringBuffer();
        for (final DisplayMode t : DisplayMode.values()) {
            if (displaySwitching) {
                sb.append(String.format(DISPLAY_FORMAT, t.id)).append(".").append(String.format(
                    t.name, "���E�̃i�x�A�c�˒ʏ�")).append("\n");
            } else {
                sb.append(String.format(DISPLAY_FORMAT, t.id)).append(".").append(String.format(t.name,
                    "�ʏ�ː��E�̃i�x�A�c")).append("\n");
            }

        }
        return sb.toString();
    }

    /**
     * ���[�h�擾
     * 
     * @param inputDiaplayMode ���͒l
     * @return ���[�h�����݂��Ȃ��ꍇ��null�l
     */
    public static DisplayMode convertDisplayMode(final String inputDiaplayMode) {
        if (inputDiaplayMode == null) {
            return null;
        }

        int displayMode = 0;
        try {
            displayMode = Integer.parseInt(inputDiaplayMode);
        }
        catch (NumberFormatException e) {
            return null;
        }

        for (final DisplayMode t : DisplayMode.values()) {
            if (t.id == displayMode) {
                return t;
            }
        }
        return null;
    }

}
