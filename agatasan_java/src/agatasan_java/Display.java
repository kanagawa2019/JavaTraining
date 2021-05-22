package agatasan_java;

/**
 * ��㏈�����[�h�\���̃C���^�[�t�F�[�X
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/30 �V�K�쐬
 * @version 1.1 2021/05/16 No.93�w�E�Ή�
 * @version 1.2 2021/05/21 No.105�w�E�Ή�
 */
public interface Display {

    // ���S�s�\��
    void calcMultiplicationTable();

    // ���1�s�\��
    void calcMultiplicationTable(final int number);

    // ���P�s�\���̓��͔��菈��
    int calcMultiplicationLine();

    // ���[�h���͎�t����
    DisplayMode inputDisplayMode(final boolean displaySwitching);

    // �I������
    void scannerClose();

    // �����p���m�F
    boolean isContinue();
}
