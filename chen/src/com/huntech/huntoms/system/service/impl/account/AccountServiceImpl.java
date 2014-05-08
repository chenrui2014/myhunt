package com.huntech.huntoms.system.service.impl.account;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huntech.huntoms.common.uitl.orm.DynamicSpecifications;
import com.huntech.huntoms.common.uitl.orm.SearchFilter;
import com.huntech.huntoms.common.uitl.view.Pager;
import com.huntech.huntoms.system.domain.User;
import com.huntech.huntoms.system.repository.UserDao;
import com.huntech.huntoms.system.service.account.AccountService;
@Component
//默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(String loginName, String password) {
		User user = userDao.findByLoginNameAndPassword(loginName, password);
		return user;
	}

	@Override
	public Page<User> findUserList(Map<String, Object> searchParams,Pager pager) {
		
		PageRequest pageRequest = Pager.buildPageRequest(pager);
		Specification<User> spec = buildSpecification(searchParams);
		return userDao.findAll(spec, pageRequest);
	}
	
	private Specification<User> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<User> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), User.class);
		return spec;
	}

	@Override
	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateUser(User user) {
		/*if (StringUtils.isNotBlank(user.getPassword())) {
			entryptPassword(user);
		}*/
		userDao.save(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteUser(Long userId) {
		userDao.delete(userId);
	}
	/**
	 * 按登录名查询用户.
	 */
	@Override
	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	@Override
	public User findUserByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return userDao.findByUserCode(userCode);
	}
}
