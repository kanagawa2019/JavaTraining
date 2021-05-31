package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * Java14�N���X Java�ۑ�P�S(�������K�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/29 �V�K�쐬
 * @version 1.1 2021/05/23 No.95�`102�w�E�Ή�
 * @version 1.2 2021/05/26 No.109�`113�w�E�Ή�
 * @version 1.3 2021/05/30 No.110�`122�w�E�Ή�
 * @version 1.4 2021/05/31 No.123�`131�w�E�Ή�
 */
public class Java14 {

    /**
     * ���͂��ꂽ�������[�h�̏�����\�����܂��B
     * 
     */
    public static void main(String[] args) {

        try {
            FileProcessing fp = new FileProcessing();
            List<Personal> personalList = fp.getUserInfo();

            do {
                // --------------------------------------------------
                // ����
                // --------------------------------------------------

                // �����̏������[�h����
                final DepositBusiness account = AccountProcessing.inputAccount();

                switch (account) {
                    case NEW:
                        // �V�K�쐬�̏ꍇ
                        AccountProcessing.createAccount(personalList);
                        break;
                    case CHANGE:
                        // ��������̏���
                        AccountProcessing.changeAccountInfo(personalList);
                        break;
                    case RELEASE:
                        // �������̏ꍇ
                        AccountProcessing.releaseAccount(personalList);
                        break;
                    default:
                        System.out.println(Util.UNEXPECTED_ERR);
                        break;
                }

            } while (Util.isContinue());

            // ���[�U�����t�@�C���ɕۑ�
            fp.createFile(true, personalList, 0);

        } catch (FileReadException | FileWriteException | IOException e) {
            System.out.println("�����𒆒f���܂����B�V�X�e���Ǘ��҂֖₢���킹���Ă��������B");
        }
        Util.scannerClose();
    }

}
