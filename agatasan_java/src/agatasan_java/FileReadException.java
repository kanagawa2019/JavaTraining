package agatasan_java;

/**
 * �t�@�C���ǂݍ��ݗ�O�N���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/10/18 �V�K�쐬
 */
public class FileReadException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * �G���[���b�Z�[�W�\��
     * 
     * @param strPass �ǂݍ��݃t�@�C���܂ł̃p�X
     * @param e       �G���[���
     */
    public FileReadException(String strPass, Exception e) {
        System.out.println(String.format("�t�@�C���̓ǂݍ��݂Ɏ��s���܂����B�t�@�C����:%s", strPass));
        e.printStackTrace();
    }

}
