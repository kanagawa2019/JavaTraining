package agatasan_java;

import java.util.Date;

/**
 * ���[�U�[���N���X
 * 
 * @author �H�c ���I
 * @version 1.0 2020/11/03 �V�K�쐬
 * @version 1.1 2020/11/07 No.60,62,63�w�E�Ή�
 */
public class User {

    /** ���� */
    private String name;
    /** ���� */
    private Sex sex;
    /** ���N���� */
    private Date birthday;
    /** ���ӌ��� */
    private String favoriteLanguage;

    /**
     * �R���X�g���N�^
     * 
     * @param name             ����
     * @param sex              ����
     * @param birthday         ���N����
     * @param favoriteLanguage ���ӌ���
     */
    public User(final String name, final Sex sex, final Date birthday, String favoriteLanguage) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.favoriteLanguage = favoriteLanguage;
    }

    /**
     * ����
     */
    public static enum Sex {

        MALE(1, "�j��"), FEMALE(2, "����"), OTHER(3, "���̑�");

        /** id */
        private final int id;
        /** ���� */
        private final String name;

        /**
         * �R���X�g���N�^
         * 
         * @param id
         */
        private Sex(final int id, final String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * ���ʕ\��������擾
         * 
         * @return ���ʕ\��������
         */
        public static String getSelectSexString() {
            final StringBuffer sb = new StringBuffer();
            int cnt = 0;
            for (final Sex t : Sex.values()) {
                sb.append(t.id).append(".").append(t.name);
                if (Sex.values().length - 1 > cnt) {
                    sb.append("�A");
                }
                cnt++;
            }
            return sb.toString();
        }

        /**
         * ���ʂ̖��̎擾
         * 
         * @param inputId ���͒l
         * @return ���ʂ̖��̂����݂��Ȃ��ꍇ��null�l
         */
        public static String getByid(final int inputId) {
            for (final Sex t : Sex.values()) {
                if (t.id == inputId) {
                    return t.name;
                }
            }
            return null;

        }

        /**
         * ���ʎ擾
         * 
         * @param inputSex ���͒l
         * @return ���ʂ����݂��Ȃ��ꍇ��null�l
         */
        public static Sex getSex(final String inputSex) {
            if (inputSex == null) {
                return null;
            }
            for (final Sex t : Sex.values()) {
                if (t.id == Integer.parseInt(inputSex)) {
                    return t;
                }
            }
            return null;
        }

        /**
         * id���擾
         * 
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * ���̂��擾
         * 
         * @return name
         */
        public String getName() {
            return name;
        }

    }

    /**
     * �������擾
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * ������ݒ�
     * 
     * @param name �������Z�b�g����
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ���ʂ��擾
     * 
     * @return sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * ���ʂ�ݒ�
     * 
     * @param sex ���� ���Z�b�g����
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * ���N�������擾
     * 
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * ���N������ݒ�
     * 
     * @param birthday ���N�������Z�b�g����
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * ���ӌ�����擾
     * 
     * @return favoriteLanguage
     */
    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    /**
     * ���ӌ����ݒ�
     * 
     * @param favoriteLanguage ���ӌ�����Z�b�g����
     */
    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

}
