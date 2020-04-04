package agatasan_java;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Java4�N���X Java�ۑ�S(�X�e�[�^�X�Ǘ��j
 *
 * @author �H�c ���I
 * @version 1.0 2020/03/22
 */
public class Java4 {

    /** ���̎n�܂�̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_START = 1;

    /** ���̏I���̒l */
    private static final int MULTIPLICATION_TABLE_RANGE_END = 9;

    /** �\���`�� */
    private static final String DISPLAY_FORMAT = "%2s%s";
    
    /** 3�̔{�����̕\�� */
    private static final String MULTIPLES_OF_3_DISPLAY = "NA";
    
    /**
     * ���͂��ꂽ���l�ŋ��̌v�Z���s���A�R���\�[���ɕ\�����܂��B
     * 
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // �ؑփ��[�h�I���E�I�t
        boolean displaySwitching = false;
        
            while(true) {
                
                System.out.println("***********************************");
                System.out.println("�������[�h��I�����Ă�������");
                System.out.println("1.���\��");
                System.out.println("2.�w��̒i�̂ݕ\��");
                if (displaySwitching) {
                    System.out.println("3.���[�h�ؑ�(���E�̃i�x�A�c�˒ʏ�)");
                } else {
                    System.out.println("3.���[�h�ؑ�(�ʏ�ː��E�̃i�x�A�c)");
                }
                
                System.out.println("9.�I��");
                System.out.println("***********************************"); 
                
                
                if (scanner.hasNextInt()) {
                    // ���͓��e�����l�̏ꍇ
                    int line = scanner.nextInt();
                    
                    // 1�����͂��ꂽ�ꍇ
                    if (line == 1) {
                        if (displaySwitching) {
                            // �ؑփ��[�h�I���̏ꍇ
                            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {
                                    
                                    // 3�̔{���͕ʕ\��
                                    if ((i * j) % 3 == 0) {
                                        System.out.printf(DISPLAY_FORMAT, MULTIPLES_OF_3_DISPLAY, " ");
                                    } else {
                                        System.out.printf(DISPLAY_FORMAT, i * j, " ");
                                    }
                                });
                                System.out.printf("%n");
                            });
                        } else {
                            // �ؑփ��[�h�I�t�̏ꍇ
                            IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(j -> {
                                    System.out.printf(DISPLAY_FORMAT, i * j, " ");
                                });
                                System.out.printf("%n");
                            });
                        }

                    }
                    
                    // 2�����͂��ꂽ�ꍇ
                    if (line == 2) {
                        System.out.println("�\������i����͂��Ă�������");
                        
                        if (scanner.hasNextInt()) {
                            // ���͓��e�����l�̏ꍇ
                            int inputLine = scanner.nextInt();

                            
                            if (displaySwitching) {
                                // �ؑփ��[�h�I���̏ꍇ
                                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                                    
                                    // 3�̔{���͕ʕ\��
                                    if ((i * inputLine) % 3 == 0) {
                                        System.out.printf(DISPLAY_FORMAT, MULTIPLES_OF_3_DISPLAY, " ");
                                    } else {
                                        System.out.printf(DISPLAY_FORMAT, i * inputLine, " ");
                                    }
                                    
                                });
                                System.out.printf("%n");
                            } else {
                                // �ؑփ��[�h�I�t�̏ꍇ
                                IntStream.rangeClosed(MULTIPLICATION_TABLE_RANGE_START, MULTIPLICATION_TABLE_RANGE_END).forEach(i -> {
                                    System.out.printf(DISPLAY_FORMAT, i * inputLine, " ");
                                });
                                System.out.printf("%n");
                            }
                            
                        } else {
                            // ���͓��e�����l�ȊO�̏ꍇ
                            System.out.println("�G���[�I�I���l�ȊO�����͂���܂����B");
                            scanner.next();
                        }
                    }
                    
                    // 3�����͂��ꂽ�ꍇ
                    if (line == 3) {
                        // �ؑ�
                        if (displaySwitching) {
                            displaySwitching = false;
                        } else  {
                            displaySwitching = true;
                        }
                        System.out.println("���[�h��؂�ւ��܂����B");
                    }
                    
                    
                    // 9�����͂��ꂽ�ꍇ
                    if (line == 9) {
                        System.out.println("�I�����܂��B");
                        break;
                    }
                    
                } else {
                    // ���͓��e�����l�ȊO�̏ꍇ
                    System.out.println("�G���[�I�I���l�ȊO�����͂���܂����B");
                    scanner.next();
                }
            }
            scanner.close();
            
    }
}
