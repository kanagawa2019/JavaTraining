package agatasan_java.java14;

/**
 * �t�@�C���ǂݍ��ݗ�O�N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/10/18 �V�K�쐬
 * @version 1.0 2020/11/08 No.57,58�w�E�Ή�
 */
public class FileReadException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * �G���[���b�Z�[�W�\��
     * 
     * @param e   �G���[���
     * @param msg �\�����b�Z�[�W
     */
    public FileReadException(Exception e, String msg) {
        super(msg);
        e.printStackTrace();
    }

}
