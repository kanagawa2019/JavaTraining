package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java2�N���X Java�ۑ�Q(�W�����́E�֐����E���̓`�F�b�N�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/03/08
 */
public class Java2_bk {

    /** ���̎n�܂�̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** ���̏I���̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /**
     * ���͂��ꂽ���l�ŋ��̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("���l����͂��Ă��������B");
        
        // �A������
        while(scanner.hasNext()) {
            
            // ���̓`�F�b�N
            String result = checkInputData(scanner);
                
            // ���l�ȊO�����͂��ꂽ�ꍇ
            if (result == null) {
                System.out.println("�G���[�I�I���l�ȊO�����͂���܂����B");
                continue;
            }
            int line = Integer.valueOf(result);
            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                System.out.printf("%2d ", i * line);
            });
            
        }
        // ctr + z �œ��͏I��
        System.out.println("");
        System.out.println("���͏I���ł��B");

    }
    
    /**
     * ���̓`�F�b�N
     * 
     * @param scanner
     * @return�@���l��Ԃ��܂��B���l�ȊO�����͂��ꂽ�ꍇ�́Anull��Ԃ��܂��B
     */
    private static String checkInputData (Scanner scanner) {
        String result = null;
        try {
            int line = scanner.nextInt();
            result = String.valueOf(line);
        } catch (Exception e) {
            return result;
        }
        return result;
    }

}
