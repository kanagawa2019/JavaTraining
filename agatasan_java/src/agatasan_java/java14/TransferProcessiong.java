package agatasan_java.java14;

import java.io.IOException;
import java.util.List;

/**
 * Uˆ—
 * 
 * @author •H“c ”ü‹I
 * @version 1.0 2021/05/23 V‹Kì¬
 * @version 1.1 2021/05/26 No.109`113w“E‘Î‰
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
    public void transferMoney(Personal transfer, List<Personal> personalList) throws FileWriteException, FileReadException, IOException {

        do {
            // Uæ‚Ìl•¨‚Ì”Ô†‚ğæ“¾
            int payeeOfNumber = Util.getTargetNo(personalList, "‚Ç‚Ìƒ†[ƒU‚ÉU‚µ‚Ü‚·‚©H");

            // 0‚Ìê‡‚ÍAÅ‰‚É–ß‚é
            if (payeeOfNumber == 0) {
                return;
            }

            int idx = payeeOfNumber - 1;

            // “ü—Í‚³‚ê‚½”Ô†‚É•R‚Ã‚­–¼‘O‚ğæ“¾
            Personal payee = personalList.get(idx);

            // ©•ª‚ÉU‚Í•s‰Â
            if (transfer.getName().equals(payee.getName())) {
                System.out.println("‚²©•ª‚É‚ÍU‚ß‚Ü‚¹‚ñB");
                continue;
            }
            // Uî•ñæ“¾
            long inputDeposit = getTransferInfo(transfer, payee);

            // UŒ³‚Ìc‚‚ğİ’è
            transfer.setBalance(transfer.getBalance() - inputDeposit);
            // Uæ‚Ìc‚‚ğİ’è
            payee.setBalance(payee.getBalance() + inputDeposit);

            // UŒ³—š—ğ‚ÌXV
            FileProcessing fp = new FileProcessing();
            fp.writeHistory(transfer.getAccountNumber(), Bank.TRANSFER.getId(), (-inputDeposit), transfer.getBalance());
            // Uæ—š—ğ‚ÌXV
            fp.writeHistory(payee.getAccountNumber(), Bank.TRANSFER.getId(), inputDeposit, payee.getBalance());

            System.out.println(String.format("%S‚³‚ñ‚ÉUŠ®—¹‚µ‚Ü‚µ‚½B", payee.getName()));

            break;
        } while (true);

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
    private long getTransferInfo(final Personal transfer, final Personal payee) {
        long inputDeposit = 0;
        do {
            // “ü—Í’l‚ğæ“¾
            inputDeposit = Util.inputMoney("“ü‹à");

        } while (Util.isOutOfRange(inputDeposit, 1, 10000000) || Util.canPay(transfer, inputDeposit));

        return inputDeposit;
    }

}
