package agatasan_java;

/**
 * Display�N���X �R���\�[���\�����ۃN���X
 *
 * @author �H�c ���I
 * @version 1.0 2020/12/29 �V�K�쐬
 */
public abstract class Display {

    // �R���\�[���\��
    public static void displaySentence(boolean displaySwitching) {

        System.out.println("***********************************");
        System.out.println("�������[�h��I�����Ă�������");
        System.out.println(String.format("%s.���\��", Mode.MULTIPLICATION_TABLE.getValue()));
        System.out.println(String.format("%s.�w��̒i�̂ݕ\��", Mode.MULTIPLICATION_LINE.getValue()));

        if (displaySwitching) {
            System.out.println(String.format("%s.���[�h�ؑ�(���E�̃i�x�A�c�˒ʏ�)", Mode.SWITCHING.getValue()));
        } else {
            System.out.println(String.format("%s.���[�h�ؑ�(�ʏ�ː��E�̃i�x�A�c)", Mode.SWITCHING.getValue()));
        }

        System.out.println(String.format("%s.�I��", Mode.END.getValue()));
        System.out.println("***********************************");
    }

}
