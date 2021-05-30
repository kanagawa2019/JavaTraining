package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * Uˆ—
 * 
 * @author •H“c ”ü‹I
 * @version 1.0 2021/05/23 V‹Kì¬
 * @version 1.1 2021/05/26 No.109`113w“E‘Î‰
 * @version 1.2 2021/05/29 No.111,117w“E‘Î‰
 * 
 */
public class TransferProcessiong {

    // --------------------------------------------------
    // publicŠÖ”
    // --------------------------------------------------
    /**
     * Uˆ—
     * 
     * @param transfer     UŒ³
     * @param personalList ƒ†[ƒUî•ñƒŠƒXƒg
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    public static void transferMoney(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        Personal payee;

        do {
            // Uæ‚Ìl•¨‚Ì”Ô†‚ğæ“¾
            int payeeOfNumber = Util.getTargetNo(personalList, "‚Ç‚Ìƒ†[ƒU‚ÉU‚µ‚Ü‚·‚©H");

            // 0‚Ìê‡‚ÍAÅ‰‚É–ß‚é
            if (payeeOfNumber == Util.START_NUMBER_OF_PERSONAL_LIST) {
                return;
            }

            // “ü—Í‚³‚ê‚½”Ô†‚É•R‚Ã‚­–¼‘O‚ğæ“¾
            payee = personalList.get(payeeOfNumber - 1);

            // ©•ª‚ÉU‚µ‚È‚¢ê‡
            if (!transfer.getName().equals(payee.getName())) {
                break;
            }
            System.out.println("‚²©•ª‚É‚ÍU‚ß‚Ü‚¹‚ñB");
        } while (true);

        remitMoney(transfer, payee);

    }

    // --------------------------------------------------
    // privateŠÖ”
    // --------------------------------------------------
    /**
     * Uî•ñæ“¾
     * 
     * @param transfer UŒ³ƒ†[ƒUî•ñ
     * @param payee    Uæƒ†[ƒUî•ñ
     * @return U‹àŠz
     */
//    private static long getTransferInfo(final Personal transfer, final Personal payee) {
//        long inputDeposit = 0;
//        do {
//            // “ü—Í’l‚ğæ“¾
//            inputDeposit = Util.inputMoney("“ü‹à");
//
//        } while (Util.isOutOfRange(inputDeposit, MINIMUM_AMOUNT, MAXIMUM_AMOUNT) || Util.canPay(transfer, inputDeposit) || Util.isMaxBalance(
//            inputDeposit, payee.getBalance()));
//
//        return inputDeposit;
//    }

    /**
     * ‘Šèæ‚ÉU‚Şˆ—
     * 
     * @param transfer UŒ³
     * @param payee    Uæ
     * @throws FileWriteException
     * @throws FileReadException
     * @throws IOException
     */
    private static void remitMoney(Personal transfer, Personal payee) throws FileWriteException, FileReadException, IOException {

        // Uæ‚ÌŒÀ“xŠzãŒÀ‚Ìê‡‚ÍAU’†~
        if (payee.getBalance() == Util.MAX_BALANCE) {
            System.out.println("Uæ‚Ì—a‹àãŒÀŠz‚ª’B‚µ‚Ä‚¢‚é‚½‚ßA‚¨U‚ğ’†~’v‚µ‚Ü‚·B");
            return;
        }

        // Uî•ñæ“¾
        long inputDeposit = Util.getInputMoneyInfo(AccountHandlingMenu.TRANSFER, "“ü‹à", transfer, payee);

        // UŒ³‚Ìc‚‚ğİ’è
        transfer.setBalance(transfer.getBalance() - inputDeposit);
        // Uæ‚Ìc‚‚ğİ’è
        payee.setBalance(payee.getBalance() + inputDeposit);

        // UŒ³—š—ğ‚ÌXV
        FileProcessing fp = new FileProcessing();
        fp.writeHistory(transfer.getAccountNumber(), AccountHandlingMenu.TRANSFER.getId(), (-inputDeposit), transfer.getBalance());
        // Uæ—š—ğ‚ÌXV
        fp.writeHistory(payee.getAccountNumber(), AccountHandlingMenu.TRANSFER.getId(), inputDeposit, payee.getBalance());

        System.out.println(String.format("%S‚³‚ñ‚ÉUŠ®—¹‚µ‚Ü‚µ‚½B", payee.getName()));
    }
}
