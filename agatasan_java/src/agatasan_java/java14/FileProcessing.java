package agatasan_java.java14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import agatasan_java.FileReadException;
import agatasan_java.FileWriteException;

public class FileProcessing {
    // --------------------------------------------------
    // 定数
    // --------------------------------------------------
    /** 日付形式 ：yyyyMMdd */
    private static final String DATE_OF_BIRTH = "yyyyMMdd";
    /** ファイル保存識別子 */
    private static final String SAVE_IDENTIFIER = "'";
    /** ファイル区切り識別子 */
    private static final String SAVA_SEPARATION = ",";
    /** エスケープ識別子 */
    private static final char SAVA_ESCAPE = '\\';
    /** ファイル出力パス */
    private static final String ACCOUNT_FILE_OUTPUT_PATH = "ACCOUNT_FILE_OUTPUT_PATH";
    /** プロパティ設定パス */
    private static final String INIT_PROPERTIES_PATH = "." + File.separator + "Setting.properties";
    /** 取引履歴ファイル出力パス */
    private static final String ACCOUNT_HISTORY_FILE_OUTPUT_PATH = "ACCOUNT_HISTORY_FILE_OUTPUT_PATH";
    /** 採番用口座ファイル出力パス */
    private static final String NUMBERING_ACCOUNT_FILE_OUTPUT_PATH = "NUMBERING_ACCOUNT_FILE_OUTPUT_PATH";

    /**
     * 前回のユーザー情報を取得
     * 
     * @return 前回入力したユーザー情報
     * @throws FileReadException
     * @throws IOException
     */
    public List<Personal> getUserInfo() throws FileReadException, IOException {

        List<Personal> list = new ArrayList<>();

        String strPass = null;
        BufferedReader br = null;
        try {
            strPass = getPropertiesInfo(ACCOUNT_FILE_OUTPUT_PATH);

            File file = new File(strPass);

            // ファイルが存在しない場合(=処理1回目の場合)
            if (!file.exists()) {
                return list;
            }

            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str = br.readLine();
            while (str != null) {

                List<String> splitList = fileSplit(str);

                Personal line = new Personal();

                line.setAccountNumber(Integer.parseInt(splitList.get(0)));
                line.setName(splitList.get(1));
                line.setBalance(Integer.parseInt(splitList.get(2)));

                list.add(line);
                str = br.readLine();
            }

        } catch (IOException e) {
            throw new FileReadException(e, "前回のユーザー情報を保存したファイルを読み込めませんでした。");

        } finally {

            if (br != null) {
                // 閉じる処理
                br.close();
            }
        }
        return list;
    }

    /**
     * お取引履歴の書き込み
     * 
     * @param accountNumber     口座番号
     * @param id                取り扱い区分
     * @param transactionAmount 取引金額
     * @param balance           残高
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void writeHistory(final int accountNumber, final int id, final int transactionAmount, final int balance)
            throws FileWriteException, FileReadException, IOException {

        String strPass = null;
        BufferedWriter bw = null;
        try {
            strPass = getPropertiesInfo(ACCOUNT_HISTORY_FILE_OUTPUT_PATH);

            // 追加書き込み
            FileWriter fw = new FileWriter(strPass, true);
            bw = new BufferedWriter(fw);

            // 日付、番号、取り扱い区分、取引金額、残高
            String str = String.format("%s%s%s%s%s%d%s%s%s%d%s%s%s%d%s%s%s%d%s", SAVE_IDENTIFIER, getToday(), SAVE_IDENTIFIER, SAVA_SEPARATION,
                    SAVE_IDENTIFIER, accountNumber, SAVE_IDENTIFIER, SAVA_SEPARATION, SAVE_IDENTIFIER, id, SAVE_IDENTIFIER, SAVA_SEPARATION,
                    SAVE_IDENTIFIER, transactionAmount, SAVE_IDENTIFIER, SAVA_SEPARATION, SAVE_IDENTIFIER, balance, SAVE_IDENTIFIER);

            // 書き込み
            bw.write(str);
            // 改行
            bw.newLine();

        } catch (IOException e) {
            throw new FileWriteException(e, String.format("ファイルの書き込みに失敗しました。ファイル名:%s", strPass));

        } finally {

            if (bw != null) {
                // 閉じる処理
                bw.close();
            }
        }

    }

    /**
     * ファイルに保存
     * 
     * @param isUser        保存対象がユーザ情報リストならtrue
     * @param list          ユーザ情報リスト
     * @param nextAccountNo
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public void createFile(final boolean isUser, final List<Personal> list, final int nextAccountNo)
            throws FileWriteException, FileReadException, IOException {

        String strPass = null;
        BufferedWriter bw = null;
        try {
            strPass = getPropertiesInfo(isUser ? ACCOUNT_FILE_OUTPUT_PATH : NUMBERING_ACCOUNT_FILE_OUTPUT_PATH);

            FileWriter fw = new FileWriter(strPass);
            bw = new BufferedWriter(fw);

            if (isUser) {
                // 口座番号、ユーザー名、金額をカンマ区切りで連結
                for (Personal p : list) {
                    String str = String.format("%s%s%s%s%s%s%s%s%s%d%s", SAVE_IDENTIFIER, p.getAccountNumber(), SAVE_IDENTIFIER, SAVA_SEPARATION,
                            SAVE_IDENTIFIER, conversionEscape(p.getName()), SAVE_IDENTIFIER, SAVA_SEPARATION, SAVE_IDENTIFIER, p.getBalance(),
                            SAVE_IDENTIFIER);
                    // 書き込み
                    bw.write(str);
                    // 改行
                    bw.newLine();
                }
            } else {
                String str = String.format("%s%d%s", SAVE_IDENTIFIER, nextAccountNo, SAVE_IDENTIFIER);
                // 書き込み
                bw.write(str);
                // 改行
                bw.newLine();
            }

        } catch (IOException e) {
            throw new FileWriteException(e, String.format("ファイルの書き込みに失敗しました。ファイル名:%s", strPass));

        } finally {

            if (bw != null) {
                // 閉じる処理
                bw.close();
            }
        }

    }

    /**
     * 口座番号取得
     * 
     * @return 取得した口座番号
     * @throws FileReadException
     * @throws IOException
     */
    public String getAccountNo() throws FileReadException, IOException {

        String retValue = null;

        String strPass = null;
        BufferedReader br = null;
        try {
            strPass = getPropertiesInfo(NUMBERING_ACCOUNT_FILE_OUTPUT_PATH);

            File file = new File(strPass);

            // ファイルが存在しない場合(=処理1回目の場合)
            if (!file.exists()) {
                return "1";
            }

            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str = br.readLine();
            if (str != null) {
                // 読み取り
                retValue = fileSplit(str).get(0);
            }

        } catch (IOException e) {
            throw new FileReadException(e, "口座採番用ファイルを読み込めませんでした。");

        } finally {

            if (br != null) {
                // 閉じる処理
                br.close();
            }
        }
        return retValue;
    }

    /**
     * 取引履歴を取得
     * 
     * @return 前回入力したユーザー情報
     * @throws FileReadException
     * @throws IOException
     */
    public List<AccountHistory> getAccountHistory() throws FileReadException, IOException {

        List<AccountHistory> list = new ArrayList<>();

        String strPass = null;
        BufferedReader br = null;
        try {
            strPass = getPropertiesInfo(ACCOUNT_HISTORY_FILE_OUTPUT_PATH);

            File file = new File(strPass);

            // ファイルが存在しない場合(=処理1回目の場合)
            if (!file.exists()) {
                return list;
            }

            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str = br.readLine();
            while (str != null) {

                List<String> splitList = fileSplit(str);

                AccountHistory line = new AccountHistory();

                line.setDate(StringToDate(splitList.get(0)));
                line.setAccountNumber(Integer.parseInt(splitList.get(1)));
                line.setClassification(Bank.convertBank(splitList.get(2)));
                line.setTransactionAmount(Integer.parseInt(splitList.get(3)));
                line.setBalance(Integer.parseInt(splitList.get(4)));

                list.add(line);
                str = br.readLine();
            }

        } catch (IOException e) {
            throw new FileReadException(e, "前回の履歴を保存したファイルを読み込めませんでした。");

        } finally {

            if (br != null) {
                // 閉じる処理
                br.close();
            }
        }
        return list;
    }

    /**
     * プロパティファイルの値を取得
     * 
     * @param path 取得したいファイルのパス
     * @return 取得したファイルの格納パス
     * @throws FileNotFoundException
     * @throws FileReadException
     * @throws IOException
     */
    private String getPropertiesInfo(final String path) throws FileNotFoundException, FileReadException, IOException {
        Properties properties = new Properties();

        String strPass = null;
        try {
            InputStream istream = new FileInputStream(INIT_PROPERTIES_PATH);
            properties.load(istream);

            strPass = properties.getProperty(path);

            // 設定ファイル読み込み失敗時
            if (strPass == null) {
                throw new FileReadException(new Exception(), "プロパティファイルに記載されている定義が見つかりませんでした。");
            }

        } catch (FileNotFoundException e) {
            throw new FileReadException(e, "プロパティファイルが見つかりませんでした。");
        } catch (IOException e) {
            throw new FileReadException(e, "プロパティファイルの読み込みに失敗しました。");

        }
        return strPass;
    }

    /**
     * 本日日付の取得
     * 
     * @return 文字列型日付
     */
    private String getToday() {
        Calendar cl = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_OF_BIRTH);
        return sdf.format(cl.getTime());
    }

    /**
     * 入力値をエスケープ処理をして返す
     * 
     * @param input 入力値
     * @return エスケープ処理
     */
    private String conversionEscape(final String input) {
        char c;
        StringBuilder sb = new StringBuilder();
        char[] identifier = SAVE_IDENTIFIER.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == identifier[0]) {
                sb.append(SAVA_ESCAPE);
            }
            sb.append(c);

        }
        return sb.toString();
    }

    /**
     * 文字列の日付をDate型の日付に変換する
     * 
     * @param date 文字列の日付
     * @return Date型の日付
     */
    private Date StringToDate(final String date) {
        if (date == null) {
            return null;
        }
        // 文字列から日付型変換
        SimpleDateFormat tempSdf = new SimpleDateFormat(DATE_OF_BIRTH);
        Date formatDate = new Date();
        try {
            formatDate = tempSdf.parse(date);
        } catch (ParseException e) {
            // サーバーで設定するのでありえない
        }
        return formatDate;
    }

    /**
     * ファイルの1行分を口座番号とユーザー名と残高に分割する
     * 
     * @param line 読み込み行
     * @return 分割後のリスト
     */
    private List<String> fileSplit(final String line) {
        char c;
        StringBuilder sb = new StringBuilder();
        List<String> data = new ArrayList<String>();
        boolean singleQuoteFlag = false;
        boolean singleQuoteKeepingFlag = false;
        char[] separation = SAVA_SEPARATION.toCharArray();
        char[] identifier = SAVE_IDENTIFIER.toCharArray();

        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);
            if (c == identifier[0] && singleQuoteFlag && singleQuoteKeepingFlag) {
                singleQuoteKeepingFlag = !singleQuoteKeepingFlag;
                sb.append(c);
            } else if (c == separation[0] && !singleQuoteFlag) {
                data.add(sb.toString());
                sb.delete(0, sb.length());
            } else if (c == separation[0] && singleQuoteFlag) {
                sb.append(c);
            } else if (c == identifier[0]) {
                singleQuoteFlag = !singleQuoteFlag;
            } else if (c == SAVA_ESCAPE) {
                singleQuoteKeepingFlag = !singleQuoteKeepingFlag;
            } else {
                sb.append(c);
            }

            // 最後の項目を設定
            if (i == line.length() - 1) {
                data.add(sb.toString());
            }
        }
        return data;

    }

}
