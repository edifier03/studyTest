package com.pubtools.sercurity.bean;


public class LoginUserInfo {

	// 用户编号
	private int userId;
	// 用户权限(角色)
	private Role role;
	// 所属企业
	private int companyId;
	// 用户名
	private String userName;

	/**
	 * 获取登录者userId
	 * 
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 设置登录者userId
	 * 
	 * @param userId
	 *          登录者userId
	 */
	public void setUserId( int userId ) {
		this.userId = userId;
	}

	/**
	 * 获取登录者角色
	 * 
	 * @return 角色
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * 设置登录者角色
	 * 
	 * @param role
	 *          登录者角色
	 */
	public void setRole( Role role ) {
		this.role = role;
	}

	/**
	 * 获取登录者所属企业编号
	 * 
	 * @return 企业编号
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * 设置企业编号
	 * 
	 * @param companyId
	 *          企业编号
	 */
	public void setCompanyId( int companyId ) {
		this.companyId = companyId;
	}

	/**
	 * 获取登录者用户名
	 * 
	 * @return 用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名
	 * 
	 * @param userName
	 *          用户名
	 */
	public void setUserName( String userName ) {
		this.userName = userName;
	}

}
