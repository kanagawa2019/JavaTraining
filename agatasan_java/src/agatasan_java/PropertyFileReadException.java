package agatasan_java;

/**
 * �v���p�e�B�t�@�C���ǂݍ��ݗ�O�N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/10/18 �V�K�쐬
 * @version 1.0 2020/11/08 No.57�w�E�Ή�
 */
public class PropertyFileReadException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * �G���[���b�Z�[�W�\��
     * 
     * @param e   �G���[���
     * @param msg �\�����b�Z�[�W
     */
    public PropertyFileReadException(Exception e, String msg) {
        super(msg);
        e.printStackTrace();
    }

}
