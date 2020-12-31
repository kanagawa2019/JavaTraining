package agatasan_java;

/**
 * ��㏈�����[�h�̗񋓌^�N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/30 �V�K�쐬
 */
public enum DisplayMode {

    MULTIPLICATION_TABLE(1, "���\��"), MULTIPLICATION_LINE(2, "�w��̒i�̂ݕ\��"), SWITCHING(3, "���[�h�ؑ�(%S)"), END(9, "�I��");

    /** id */
    private final int id;
    /** ���� */
    private final String name;

    /**
     * �R���X�g���N�^
     * 
     * @param id
     */
    private DisplayMode(final int id, final String name) {
        this.id = id;
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
                sb.append(t.id).append(".").append(String.format(t.name, "���E�̃i�x�A�c�˒ʏ�")).append("\n");
                continue;
            }
            sb.append(t.id).append(".").append(String.format(t.name, "�ʏ�ː��E�̃i�x�A�c")).append("\n");
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
        } catch (NumberFormatException e) {
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
