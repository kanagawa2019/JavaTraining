package agatasan_java;

/**
 * �t�@�C���������ݗ�O�N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/10/18 �V�K�쐬
 */
public class FileWriteException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * �G���[���b�Z�[�W�\��
     * 
     * @param strPass �������݃t�@�C���܂ł̃p�X
     * @param e       �G���[���
     */
    public FileWriteException(String strPass, Exception e) {
        System.out.println(String.format("�t�@�C���̏������݂Ɏ��s���܂����B�t�@�C����:%s", strPass));
        e.printStackTrace();
    }

}
