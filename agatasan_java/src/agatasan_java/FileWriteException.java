package agatasan_java;

/**
 * �t�@�C���������ݗ�O�N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/10/18 �V�K�쐬
 * @version 1.0 2020/11/08 No.57,58�w�E�Ή�
 */
public class FileWriteException extends Exception {

    private static final long serialVersionUID = 2L;

    /**
     * �G���[���b�Z�[�W�\��
     * 
     * @param e   �G���[���
     * @param msg �\�����b�Z�[�W
     */
    public FileWriteException(Exception e, String msg) {
        super(msg);
        e.printStackTrace();
    }

}
