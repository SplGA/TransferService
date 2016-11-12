package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable
{

    private Long usrId;
    private String fio;
    private BigDecimal balance = BigDecimal.ZERO;

    private Set<Transfer> senderTransfers = new HashSet<Transfer>();
    private Set<Transfer> receiverTransfers = new HashSet<Transfer>();

    public User()
    {
    }

    public Set<Transfer> getSenderTransfers()
    {
        return senderTransfers;
    }

    public void setSenderTransfers(Set<Transfer> senderTransfers)
    {
        this.senderTransfers = senderTransfers;
    }


    public BigDecimal getBalance()
    {
        return balance;
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }


    public Long getUsrId()
    {
        return usrId;
    }

    public void setUsrId(Long usr_id)
    {
        this.usrId = usr_id;
    }

    public String getFio()
    {
        return fio;
    }

    public void setInfo(String fio)
    {
        this.fio = fio;
    }


    public Set<Transfer> getReceiverTransfers()
    {
        return receiverTransfers;
    }

    public void setReceiverTransfers(Set<Transfer> receiverTransfers)
    {
        this.receiverTransfers = receiverTransfers;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUsrId() != null ? !getUsrId().equals(user.getUsrId()) : user.getUsrId() != null) return false;
        if (getFio() != null ? !getFio().equals(user.getFio()) : user.getFio() != null) return false;
        if (getBalance() != null ? !getBalance().equals(user.getBalance()) : user.getBalance() != null) return false;
        if (getSenderTransfers() != null ? !getSenderTransfers().equals(user.getSenderTransfers()) : user.getSenderTransfers() != null)
        {
            return false;
        }
        return getReceiverTransfers() != null ? getReceiverTransfers().equals(user.getReceiverTransfers()) : user.getReceiverTransfers() == null;

    }

    @Override
    public int hashCode()
    {
        int result = getUsrId() != null ? getUsrId().hashCode() : 0;
        result = 31 * result + (getFio() != null ? getFio().hashCode() : 0);
        result = 31 * result + (getBalance() != null ? getBalance().hashCode() : 0);
        result = 31 * result + (getSenderTransfers() != null ? getSenderTransfers().hashCode() : 0);
        result = 31 * result + (getReceiverTransfers() != null ? getReceiverTransfers().hashCode() : 0);
        return result;
    }


    @Override
    public String toString()
    {
        return "User{" + "fio='" + fio + '\'' + ", balance=" + balance + '}';
    }
}
