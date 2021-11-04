package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * Java14クラス Java課題１４(総合演習）
 *
 * @author 菱田 美紀
 * @version 1.0 2020/12/29 新規作成
 * @version 1.1 2021/05/23 No.95～102指摘対応
 * @version 1.2 2021/05/26 No.109～113指摘対応
 * @version 1.3 2021/05/30 No.110～122指摘対応
 * @version 1.4 2021/05/31 No.123～131指摘対応
 * @version 1.5 2021/06/01 No.126,128,130,131指摘対応
 */
public class Java14 {

    /**
     * 入力された処理モードの処理を表示します。
     * 
     */
    public static void main(String[] args) {

        try {
            List<Personal> personalList = FileProcessing.getUserInfo();

            do {
                // --------------------------------------------------
                // 入力
                // --------------------------------------------------

                // 口座の処理モード入力
                final DepositBusiness account = AccountProcessing.inputAccount();

                switch (account) {
                    case NEW:
                        // 新規作成の場合
                        AccountProcessing.createAccount(personalList);
                        break;
                    case CHANGE:
                        // 口座操作の処理
                        AccountProcessing.changeAccountInfo(personalList);
                        break;
                    case RELEASE:
                        // 口座解約の場合
                        AccountProcessing.releaseAccount(personalList);
                        break;
                    default:
                        System.out.println(Util.UNEXPECTED_ERR);
                        break;
                }

            } while (Util.isContinue());

            // ユーザ情報をファイルに保存
            FileProcessing.createFile(true, personalList, 0);

        } catch (FileReadException | FileWriteException | IOException e) {
            System.out.println("処理を中断しました。システム管理者へ問い合わせしてください。");
        }
        Util.scannerClose();
    }

}
