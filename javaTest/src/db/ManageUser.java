package db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import db.mapping.User;
import db.util.HibernateUtil;

public class ManageUser {
	
	private Session session;
	private Criteria criteria;
	private User userData;
	
	public ManageUser(String userName) {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("user", userName));
			List<User> res = criteria.list();
			userData = res.get(0);
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		finally{
			closeSession();
		}
	}	
	
	public User getUserData() {
		return userData;
	}

	public void setUserData(User userData) {
		this.userData = userData;
	}

	public void closeSession() {
		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}
	
	public Criteria getCriteria() {
		return criteria;
	}
	
	

}
