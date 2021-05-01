package agatasan_java;

public enum Bank {
    DEPOSIT(1, "����"), TRANSFER(2, "�U��"), BALANCE(3, "�c���\��"), HISTORY(9, "���������\��");

    /** id */
    private final int id;
    /** ���� */
    private final String name;

    /**
     * �R���X�g���N�^
     * 
     * @param id
     */
    private Bank(final int id, final String name) {
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
        for (final Bank t : Bank.values()) {
            sb.append(t.id).append(".").append(t.name).append("\n");
        }
        return sb.toString();
    }

    /**
     * ���[�h�擾
     * 
     * @param inputBank���͒l
     * @return ���[�h�����݂��Ȃ��ꍇ��null�l
     */
    public static Bank convertBank(final String inputBank) {
        if (inputBank == null) {
            return null;
        }

        int bank = 0;
        try {
            bank = Integer.parseInt(inputBank);
        } catch (NumberFormatException e) {
            return null;
        }

        for (final Bank t : Bank.values()) {
            if (t.id == bank) {
                return t;
            }
        }
        return null;
    }

}
