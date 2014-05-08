package com.huntech.huntoms.system.service.account;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.huntech.huntoms.common.uitl.view.Pager;
import com.huntech.huntoms.system.domain.User;

public interface AccountService {
	public User login(String userName ,String password);
	
	public Page<User> findUserList(Map<String, Object> searchParams,Pager pager);
	
    public User getUser(Long id);
    
    public void updateUser(User user);
    
    public void deleteUser(Long userId);
    
    public User findUserByLoginName(String loginName);
    
    public User findUserByUserCode(String userCode);
}
