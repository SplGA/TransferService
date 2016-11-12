package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Transfer implements Serializable
{

    /**
     *
     */
    private Long trId;
    private User receiver;
    private User sender;
    private BigDecimal amount;
    private Date trDate;

    public Transfer()
    {
    }

    public Long getTrId()
    {
        return trId;
    }

    public void setTrId(Long trId)
    {
        this.trId = trId;
    }

    public User getReceiver()
    {
        return receiver;
    }

    public void setReceiver(User receiver)
    {
        this.receiver = receiver;
    }

    public User getSender()
    {
        return sender;
    }

    public void setSender(User sender)
    {
        this.sender = sender;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Date getTrDate()
    {
        return trDate;
    }


    public void setTrDate(Date trDate)
    {
        this.trDate = trDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Transfer)) return false;

        Transfer that = (Transfer) o;

        if (getTrId() != null ? !getTrId().equals(that.getTrId()) : that.getTrId() != null) return false;
        if (getReceiver() != null ? !getReceiver().equals(that.getReceiver()) : that.getReceiver() != null)
            return false;
        if (getSender() != null ? !getSender().equals(that.getSender()) : that.getSender() != null) return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) return false;
        return getTrDate() != null ? getTrDate().equals(that.getTrDate()) : that.getTrDate() == null;

    }

    @Override
    public int hashCode()
    {
        int result = getTrId() != null ? getTrId().hashCode() : 0;
        result = 31 * result + (getReceiver() != null ? getReceiver().hashCode() : 0);
        result = 31 * result + (getSender() != null ? getSender().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getTrDate() != null ? getTrDate().hashCode() : 0);
        return result;
    }
}
