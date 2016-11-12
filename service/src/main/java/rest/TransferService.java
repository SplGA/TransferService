package rest;


import dao.TransferDAO;
import dao.UserDAO;
import entity.Transfer;
import entity.User;
import exceptions.ClientRequestException;
import memoryImpl.TransferDAOMemoryImpl;
import memoryImpl.UserDAOMemoryImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Path("/")
public class TransferService
{
    private final TransferDAO transferDAO;
    private final UserDAO userDAO;

    public TransferService()
    {
        transferDAO = new TransferDAOMemoryImpl();
        userDAO = new UserDAOMemoryImpl();
    }
//    public TransferService(TransferDAO transferDAO, UserDAO userDAO)
//    {
//        this.transferDAO = transferDAO;
//        this.userDAO = userDAO;
//    }

    @POST
    @Path("/user/create/{info}")
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(@PathParam("info") String info)
    {
        User newUsr = new User();
        newUsr.setInfo(info);
        return userDAO.saveUser(newUsr).toString();
    }


    @GET
    @Path("/user/{id}/get")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") Long usr_id)
    {
        return userDAO.getById(usr_id);
    }

    @PUT
    @Path("/user/{id}/updateInfo")
    public void updateUserInfo(@PathParam("id") Long usr_id, @FormParam("info") String info)
    {
        User usr = getUserById(usr_id);
        synchronized (usr)
        {
            usr.setInfo(info);

        }
    }

    @PUT
    @Path("/user/{id}/deposit")
    public void deposit(@PathParam("id") Long usr_id, @FormParam("amount") BigDecimal amount)
    {
        if (amount.compareTo(BigDecimal.ZERO) < 1)
        {
            throw new ClientRequestException("Deposit amount should be positive!");
        }
        User usr = getUserById(usr_id);
    }

    @PUT
    @Path("/user/{id}/withdraw")
    public void withdraw(@PathParam("id") Long usr_id, BigDecimal amount)
    {
        if (amount.compareTo(BigDecimal.ZERO) < 1)
        {
            throw new ClientRequestException("Withdraw amount should be positive!");
        }
        User usr = getUserById(usr_id);
        synchronized (usr)
        {
            BigDecimal newBalance = usr.getBalance().subtract(amount);
            if (newBalance.compareTo(BigDecimal.ZERO) < 0)
            {
                throw new ClientRequestException("User don't have enough money for this operation");
            }

            usr.setBalance(newBalance);
            userDAO.updateUser(usr);
        }

    }


    @POST
    @Path("/transfer")
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer makeTransfer(@PathParam("send") Long sendId, @PathParam("rec") Long recId, @PathParam("amount") BigDecimal amount)
    {
        User sender = userDAO.getById(sendId);
        User receiver = userDAO.getById(recId);

        User minUsr = sendId < recId ? sender : receiver;
        User maxUsr = sendId < recId ? receiver : sender;
        synchronized (minUsr)
        {
            synchronized (maxUsr)
            {
                if (sender.getBalance().compareTo(amount) < 0)
                {
                    throw new ClientRequestException("Sender balance (" + sender.getBalance() + " is less than transfer value(" + amount + ")");
                }
                Transfer tr = new Transfer();
                tr.setAmount(amount);
                tr.setSender(sender);
                tr.setReceiver(receiver);

                sender.setBalance(sender.getBalance().subtract(amount));
                receiver.setBalance(receiver.getBalance().add(amount));
                transferDAO.saveTransfer(tr);
                userDAO.updateUser(sender);
                userDAO.updateUser(receiver);
                return tr;
            }
        }
    }


    @GET
    @Path("/transfer/{id}/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Transfer> getTransferSetByUser(@PathParam("usr_id") Long usrId)
    {
        User us = userDAO.getById(usrId);
        Set<Transfer> ret = new HashSet<>();
        ret.addAll(us.getReceiverTransfers());
        ret.addAll(us.getSenderTransfers());
        return ret;

    }

}
