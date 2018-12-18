package db;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import app.util.AppUtil;
import app.util.HibernateUtil;
import app.util.UploadFile;
import app.util.VerifyRecaptcha;
import db.mapping.User;
import db.mapping.UserRoles;

public class ManageUser {
	
	private Session session;
	private Criteria criteria;
	private User userData;
	
	public ManageUser(){}
	
	public ManageUser(String userName) {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("user", userName));
			List<?> res = criteria.list();
			userData = (User) res.get(0);
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
	
	public boolean updateUserData(User user, String userName){
		boolean res = true;
		if(!isUserDataValid(user, false))
			return false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();		
			
			String hqlUpdate = "update User u "
								+ "set "
								+ "u.firstName = :newFirstName, "
								+ "u.lastName = :newLastName, "
								+ "u.phone = :newPhone, "
								+ "u.company = :newCompany "
								+ "where u.user = :currentUser";
			int updatedEntities = session.createQuery( hqlUpdate )
			        .setString( "newFirstName", user.getFirstName() )
			        .setString( "newLastName", user.getLastName() )
			        .setString( "newCompany", user.getCompany() )
			        .setString( "newPhone", user.getPhone() )
			        .setString( "currentUser", userName )
			        .executeUpdate();
			if(updatedEntities != 1)
				res = false;
		}
		catch(HibernateException e){
			e.printStackTrace();
			res = false;
		}
		finally{
			closeSession();
		}
		return res;		
	}
	
	public boolean updateUserPhoto(HttpServletRequest request){
		boolean res = true;
		byte[] imgData = UploadFile.upload(request);
		if(imgData == null)
			return false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();		
			
			String hqlUpdate = "update User u "
								+ "set "
								+ "u.photo = :newPhoto "
								+ "where u.user = :currentUser";
			int updatedEntities = session.createQuery( hqlUpdate )
			        .setBinary( "newPhoto", imgData )
			        .setString( "currentUser", request.getUserPrincipal().getName() )
			        .executeUpdate();
			if(updatedEntities != 1)
				res = false;
		}
		catch(HibernateException e){
			e.printStackTrace();
			res = false;
		}
		finally{
			closeSession();
		}
		return res;		
	}
	
	public boolean updateUserPass(User pass, String userName){
		boolean res = true;
		if(AppUtil.isPassValid(pass.getNewPass()) && pass.getNewPass().equals(pass.getReenteredNewPass())){
			String md5HexNew = DigestUtils.md5Hex(pass.getNewPass()).toUpperCase();
			String md5HexOld = DigestUtils.md5Hex(pass.getPass()).toUpperCase();
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();				
				String hqlUpdate = "update User u "
									+ "set "
									+ "u.pass = :newpass "
									+ "where u.user = :currentUser and u.pass = :oldPass";
				int updatedEntities = session.createQuery( hqlUpdate )
				        .setString( "newpass", md5HexNew )
				        .setString( "currentUser", userName )
				        .setString( "oldPass", md5HexOld )
				        .executeUpdate();
				if(updatedEntities != 1)
					res = false;
			}
			catch(HibernateException e){
				e.printStackTrace();
				res = false;
			}
			finally{
				closeSession();
			}
		}
		else
			res = false;
		return res;
	}
	
	public boolean isEmailUnique(String email){
		boolean res = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("user", email));
			if(criteria.list() == null || criteria.list().size() < 1)
				res = true;
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		finally{
			closeSession();
		}
		return res;
	}
	
	public boolean isUserPassMatch(User pass, String userName){
		boolean res = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("user", userName));
			List<?> list = criteria.list();
			User user = (User) list.get(0);
			String passFromDB = user.getPass().toUpperCase();
			String passFromForm = DigestUtils.md5Hex(pass.getPass()).toUpperCase();
			if(passFromForm.equals(passFromDB))
				res = true;
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		finally{
			closeSession();
		}
		return res;
	}
	
	public boolean insertUser(User user, HttpServletRequest request){
		boolean res = true;
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		try {
			res = VerifyRecaptcha.verify(gRecaptchaResponse);
		} 
		catch (IOException e1) {
			e1.printStackTrace();
			res = false;
		}
		if(!res || !isUserDataValid(user, true))
			return false;
		else		
			try{
				String md5Hex = DigestUtils.md5Hex(user.getPass()).toUpperCase();
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				userData = new User();
				userData.setFirstName(user.getFirstName());
				userData.setLastName(user.getLastName());
				userData.setUser(user.getUser());
				userData.setPhone(user.getPhone());
				userData.setCompany(user.getCompany());
				userData.setPass(md5Hex);
				session.save(userData);
				
				UserRoles role = new UserRoles();
				role.setIdusers(userData.getIdusers());
				role.setUser(user.getUser());
				role.setRole("admin");
				session.save(role);
			}
		catch(HibernateException e){
			e.printStackTrace();
			res = false;
		}
		finally{
			closeSession();
		}
		return res;

	}
	
	private boolean isUserDataValid(User user, boolean isNewMember){
		if(isNewMember && !AppUtil.isEmailValid(user.getUser()))
			return false;
		if(user.getFirstName() == null)
			return false;
		if(!AppUtil.isStringLengthValid(user.getFirstName(), 3, 90) )
			return false;
		if(user.getLastName() == null)
			return false;
		if(!AppUtil.isStringLengthValid(user.getLastName(), 3, 90) )
			return false;
		if(user.getPhone() != null && !AppUtil.isPhoneValid(user.getPhone()))
			return false;
		if(user.getCompany() != null && !AppUtil.isStringLengthValid(user.getCompany(), 3, 90))
			return false;
		if(isNewMember && !AppUtil.isPassValid(user.getPass()))
			return false;
		if(isNewMember && user.getReenteredNewPass() == null)
			return false;
		if(isNewMember && !user.getPass().equals(user.getReenteredNewPass()))
			return false;
		if(isNewMember && !isEmailUnique(user.getUser()))
			return false;
		return true;
	}
	
}
