package memoryImpl;


import dao.TransferDAO;
import entity.Transfer;

import static memoryImpl.Storage.currentTransferId;
import static memoryImpl.Storage.transferMap;

public class TransferDAOMemoryImpl implements TransferDAO
{
//    @Override
//    public Transfer create(Transfer object)
//    {
//
//    }
//
//    @Override
//    public Transfer getById(Long id)
//    {
//        return transferMap.get(id);
//    }
//
//    @Override
//    public void update(Transfer object)
//    {
//        if (object.getTrId() == null)
//        {
//            throw new ClientRequestException("Updated transaction doesn't have id");
//        }
//
//        Transfer tr = transferMap.get(object.getTrId());
//        if (tr ==null)
//        {
//            throw new ClientRequestException("Can't find transaction with id: " + object.getTrId());
//        }
//       transferMap.put(object.getTrId(), object);
//
//    }
//
//    @Override
//    public void delete(Transfer object)
//    {
//        if (object.getTrId() == null)
//        {
//            throw new ClientRequestException("Deleted transaction doesn't have id");
//        }
//
//        Transfer tr = transferMap.get(object.getTrId());
//        if (tr ==null)
//        {
//            throw new ClientRequestException("Can't find transaction with id: " + object.getTrId());
//        }
//        transferMap.remove(object.getTrId());
//    }

    @Override
    public Transfer saveTransfer(Transfer tr)
    {
        tr.setTrId(currentTransferId.incrementAndGet());
        tr.getSender().getSenderTransfers().add(tr);
        tr.getReceiver().getReceiverTransfers().add(tr);
        transferMap.put(tr.getTrId(), tr);
        return tr;
    }
}
