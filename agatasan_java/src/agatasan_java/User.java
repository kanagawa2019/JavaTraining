package agatasan_java;

/**
 * ���[�U�[���N���X
 * 
 * @author �H�c ���I
 * @version 1.0 2020/11/03 �V�K�쐬
 */
public class User {

    /** ���� */
    private String name;
    /** ���� */
    private int sex;
    /** ���N���� */
    private String birthday;
    /** ���ӌ��� */
    private String favoriteLanguage;

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
    public int getSex() {
        return sex;
    }

    /**
     * ���ʂ�ݒ�
     * 
     * @param sex ���� ���Z�b�g����
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * ���N�������擾
     * 
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * ���N������ݒ�
     * 
     * @param birthday ���N�������Z�b�g����
     */
    public void setBirthday(String birthday) {
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
