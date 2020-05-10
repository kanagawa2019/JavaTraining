package agatasan_java;

/**
 * �������[�h�̗񋓌^�N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/05/10 �V�K�쐬
 */
public enum Mode {

    /**
     * ���\��
     */
    MULTIPLICATION_TABLE(1),

    /**
     * �w��̒i�̂ݕ\��
     */
    MULTIPLICATION_LINE(2),

    /**
     * ���[�h�ؑ�
     */
    SWITCHING(3),

    /**
     * �I��
     */
    END(9);

    private final int num;

    /**
     * �񋓌^�̃C���X�^���X������}�~���܂��B
     * 
     * @param num
     */
    private Mode(int num) {
        this.num = num;
    }

    /**
     * �ԍ����擾����B
     * 
     * @return �ԍ�
     */
    public int getValue() {
        return this.num;
    }

    public static Mode getTypeByValue(int num) {
        for (Mode v : values()) {
            if (v.getValue() == (num)) {
                return v;
            }
        }
        throw new IllegalArgumentException("undefined : " + num);
//        return 99;
    }
}
