package kg.azamatshekin.payment.service;


import kg.azamatshekin.payment.dao.AccountDao;
import kg.azamatshekin.payment.dao.TransactionDao;
import kg.azamatshekin.payment.dto.PaymentDto;
import kg.azamatshekin.payment.entity.Account;
import kg.azamatshekin.payment.entity.AccountTransaction;
import kg.azamatshekin.payment.entity.User;
import kg.azamatshekin.payment.utils.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountDao accountDao;
    private final TransactionDao transactionDao;
    private final DeviceService deviceService;

    public AccountService(
            AccountDao accountDao, DeviceService deviceService, TransactionDao transactionDao
    ) {
        this.accountDao = accountDao;
        this.deviceService = deviceService;
        this.transactionDao = transactionDao;
    }

    public void payment(PaymentDto paymentDto) throws  BusinessException {
        User user = deviceService.getCurrentUser(paymentDto.getToken());
        Account account = accountDao.findByUser(user);
        if (null == account) {
            account = new Account();
            account.setUser(user);
            account.setBalance(new BigDecimal("8.0"));
            accountDao.save(account);
        }
        makeTransaction(account, paymentDto.getAmount());

    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    void makeTransaction(Account account, Double amount) throws BusinessException{
        if (account.getBalance().doubleValue() < amount){
            throw new BusinessException("Insufficient amount");
        }
        account.setBalance(account.getBalance().subtract(new BigDecimal(amount)));
        accountDao.save(account);
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAccount(account);
        accountTransaction.setDate(LocalDateTime.now());
        accountTransaction.setAmount(amount);
        accountTransaction.setIs_debit(true);
        transactionDao.save(accountTransaction);
    }
}
