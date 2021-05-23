package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

/**
 * �c���\���֌W����
 * 
 * @author �H�c ���I
 * @version 1.0 2021/05/23 �V�K�쐬
 *
 */
public class CreateAccount {

    /**
     * �����V�K�쐬
     * 
     * @param personalList ���[�U�[��񃊃X�g
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void createAccount(List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // �������擾
        String inputName = inputName();

        // �����ԍ��͐V�K�̔�
        int accountNumber = createNewAccountNo();

        // �l��ݒ�
        Personal personal = new Personal(inputName, accountNumber, 0);
        personalList.add(personal);
        // �������X�V
        DepositProcessiong deposit = new DepositProcessiong();
        deposit.depositMoney(personalList.size() - 1, personalList);

        System.out.println("������V�K�o�^���܂����B");

    }

    /**
     * �����ԍ��V�K�̔�
     * 
     * @return �V�K�̔Ԕԍ�
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private int createNewAccountNo() throws FileWriteException, FileReadException, IOException {

        FileProcessing fp = new FileProcessing();
        // �̔ԗp�̃t�@�C����ǂݍ���
        // +1����
        int nextAccountNo = sumUpAccountNo(fp.getAccountNo());

        // �̔Ԃ����ԍ����̔ԗp�̃t�@�C���ɏ�������
        fp.createAccountNoFile(nextAccountNo);

        // �̔Ԃ����ԍ���Ԃ�
        return nextAccountNo;

    }

    /**
     * �����ԍ����Z����
     * 
     * @param accountNo �����ԍ�
     * @return ���Z���������ԍ�
     */
    private int sumUpAccountNo(String accountNo) {
        // �������琔�l�ɕϊ�
        int retVlalue = Integer.parseInt(accountNo);
        // ���Z
        return ++retVlalue;

    }

    /**
     * ���͂��ꂽ�������擾
     * 
     * @return ����
     */
    private String inputName() {
        return Util.inputStr("��������͂��Ă�������");
    }
}
