package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java2�N���X Java�ۑ�Q(�W�����́E�֐����E���̓`�F�b�N�j
 *
 * @author �H�c ���I
 * @version 1.0 2020/03/08
 */
public class Java2 {

    /** ���̎n�܂�̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** ���̏I���̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** �\���`�� */
    private static final String DISPLAY_FORMAT = "%2s ";
    
    /**
     * ���͂��ꂽ���l�ŋ��̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("���l����͂��Ă��������B");
        
            while(true) {
                if (scanner.hasNextInt()) {
                    // ���͓��e�����l�̏ꍇ
                    int line = scanner.nextInt();
                    IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                        System.out.printf(DISPLAY_FORMAT, i * line, " ");
                    });
                } else {
                    // ���͓��e�����l�ȊO�̏ꍇ
                    System.out.println("�G���[�I�I���l�ȊO�����͂���܂����B");
                    System.out.println("���l����͂��Ă��������B");
                    scanner.next();
                }
            }
            
    }
}
