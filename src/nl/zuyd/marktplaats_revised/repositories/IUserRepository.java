package nl.zuyd.marktplaats_revised.repositories;

import java.util.List;

import javax.ejb.Local;

import nl.zuyd.marktplaats_revised.entities.User; 

@Local
public interface IUserRepository
{
	User getById(String id);
	List<User> getAll();
	User getByUsername(String username);
	void addUser(User user);
	void deleteUser(User user);
	void saveUser(User user);
}