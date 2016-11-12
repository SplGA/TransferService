package memoryImpl;

import dao.UserDAO;
import entity.User;
import exceptions.ClientRequestException;

import static memoryImpl.Storage.currentUserId;
import static memoryImpl.Storage.userMap;

public class UserDAOMemoryImpl implements UserDAO
{

	@Override
	public User getById(Long id)
	{

		User user =  userMap.get(id);
		if (user == null)
		{
			throw new ClientRequestException("Can't find user with id: " + id);
		}
		return user;
	}

	@Override
	public User saveUser(User usr)
	{
		usr.setUsrId(currentUserId.incrementAndGet());
		userMap.put(usr.getUsrId(), usr);
		return usr;
	}

	@Override
	public User updateUser(User usr)
	{

		User dbUsr = getById(usr.getUsrId());
		dbUsr.setBalance(usr.getBalance());
		dbUsr.setInfo(usr.getFio());
		return dbUsr;
	}

	@Override
	public void deleteUser(Long id)
	{
		User dbUsr = getById(id);
		if (!dbUsr.getReceiverTransfers().isEmpty() || !dbUsr.getSenderTransfers().isEmpty())
		{
			throw new ClientRequestException("Can't delete user with existing transfers");
		}
		userMap.remove(dbUsr);
	}
}
