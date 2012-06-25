package nl.zuyd.marktplaats_revised.repositories;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import nl.zuyd.marktplaats_revised.entities.User;

@Local(IUserRepository.class)
@Singleton
public class UserRepository implements IUserRepository 
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User getByPK(String id) // this is the one for the PK 
	{
		return this.em.find(User.class, id);
	}

	@Override
	public User getById(int id) 
	{
		TypedQuery<User> q = this.em.createQuery("SELECT c FROM User c WHERE c.id = :id", User.class);
		q.setParameter("id", id);
		return q.getSingleResult();
	}
	
	@Override
	public List<User> getAll()
	{
		TypedQuery<User> q = this.em.createQuery("SELECT c FROM User c", User.class);

		return q.getResultList();
	}
	
	/**
	 * Find instances of User differentiated by username
	 * @param username String
	 * @return
	 */
	@Override
	public User getByUsername(String username) {
		return this.getByPK(username);
		// old code from when ID was still the primary key
		// since then, the username has become the PK
		//TypedQuery<User> q = this.em.createQuery("SELECT c FROM User c WHERE c.username = " + username, User.class);
		//return q.getSingleResult();
	}	
	
	@Override
	public void addUser(User user)
	{
		this.em.persist(user);
		this.em.flush();
	}

	@Override
	public void deleteUser(User user)
	{
		user = this.em.merge(user); // first merge
		this.em.remove(user); // then remove
		this.em.flush();		
	}

	@Override
	public void saveUser(User user)
	{
		user = this.em.merge(user);
		this.em.flush();		
	}
}