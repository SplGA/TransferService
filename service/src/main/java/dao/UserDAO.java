package dao;

import entity.User;

public interface UserDAO
{
    public User getById(Long id);

    public User saveUser(User usr);

    public User updateUser(User usr);

    public void deleteUser(Long id);
}
