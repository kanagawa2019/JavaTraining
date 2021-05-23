package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

/**
 * 残高表示関係処理
 * 
 * @author 菱田 美紀
 * @version 1.0 2021/05/23 新規作成
 *
 */
public class CreateAccount {

    /**
     * 口座新規作成
     * 
     * @param personalList ユーザー情報リスト
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void createAccount(List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        // 氏名を取得
        String inputName = inputName();

        // 口座番号は新規採番
        int accountNumber = createNewAccountNo();

        // 値を設定
        Personal personal = new Personal(inputName, accountNumber, 0);
        personalList.add(personal);
        // 口座を更新
        DepositProcessiong deposit = new DepositProcessiong();
        deposit.depositMoney(personalList.size() - 1, personalList);

        System.out.println("口座を新規登録しました。");

    }

    /**
     * 口座番号新規採番
     * 
     * @return 新規採番番号
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private int createNewAccountNo() throws FileWriteException, FileReadException, IOException {

        FileProcessing fp = new FileProcessing();
        // 採番用のファイルを読み込み
        // +1する
        int nextAccountNo = sumUpAccountNo(fp.getAccountNo());

        // 採番した番号を採番用のファイルに書き込み
        fp.createAccountNoFile(nextAccountNo);

        // 採番した番号を返す
        return nextAccountNo;

    }

    /**
     * 口座番号加算処理
     * 
     * @param accountNo 口座番号
     * @return 加算した口座番号
     */
    private int sumUpAccountNo(String accountNo) {
        // 文字から数値に変換
        int retVlalue = Integer.parseInt(accountNo);
        // 加算
        return ++retVlalue;

    }

    /**
     * 入力された氏名を取得
     * 
     * @return 氏名
     */
    private String inputName() {
        return Util.inputStr("氏名を入力してください");
    }
}
