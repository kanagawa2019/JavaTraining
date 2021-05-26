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
 */
public class Java14 {

    /**
     * 入力された処理モードの処理を表示します。
     * 
     */
    public static void main(String[] args) {

        try {
            FileProcessing fp = new FileProcessing();
            List<Personal> personalList = fp.getUserInfo();

            AccountProcessing ap = new AccountProcessing();
            do {
                // --------------------------------------------------
                // 入力
                // --------------------------------------------------

                // 新規・既存かの処理モード入力
                final Account account = ap.inputAccount();

                // 新規作成の場合
                if (account == Account.NEW) {
                    ap.createAccount(personalList);
                    continue;
                }

                // 口座解約の場合
                if (account == Account.RELEASE) {
                    ap.releaseAccount(personalList);
                    continue;
                }

                // 口座操作の処理
                ap.changeAccountInfo(personalList);

            } while (Util.isContinue());

            // ユーザ情報をファイルに保存
            fp.createFile(true, personalList, 0);

        } catch (FileReadException | FileWriteException | IOException e) {
            System.out.println("処理を中断しました。システム管理者へ問い合わせしてください。");
        }
        Util.scannerClose();
    }

}
