/**
 * 
 */
package com.xhcms.lottery.commons.persist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author jiajiancheng 用户信息表
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "lt_user")
public class UserPO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "real_name", nullable = false)
	private String realname;

	@Column(name = "id_number", unique = true)
	private String idNumber;

	@Column(name = "question", nullable = false)
	private String question;

	@Column(name = "answer", nullable = false)
	private String answer;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "verify_status", nullable = false)
	private int verifyStatus;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Column(name = "gender", nullable = false)
	private int gender;

	@Column(name = "birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name = "province")
	private String province;

	@Column(name = "city")
	private String city;

	@Column(name = "status", nullable = false)
	private int status;

	@Column
	private String ip;
	
	@Column(name = "created_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;

	@Column(name = "last_login_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;

	@Column(name = "last_login_ip")
	private String lastLoginIp;

	private String referer;

	@Column(name = "login_number")
	private int loginNumber;
	
	@Column(name = "login_failure_number")
	private int loginFailureNumber;
	
	@Column(name = "islocked")
	private int isLocked;
	
	@Column(name = "locked_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date locked_time;
	
	private String pid;
	
	@Column(name = "headImageURL")
	private String headImageURL;
	
	@Column(name = "nickName")
	private String nickName;
	
	/** 唯一标识新浪微博用户的uid */
	@Column(name = "sinaWeiboUid")
	private String sinaWeiboUid;

	/** 访问新浪微博用户信息的令牌 */
	@Column(name = "sinaWeiboToken")
	private String sinaWeiboToken;

	/** 唯一标识QQ互联用户的uid */
	@Column(name = "qqConnectUid")
	private String qqConnectUid;
	
	/** 访问QQ互联用户信息的令牌 */
	@Column(name = "qqConnectToken")
	private String qqConnectToken;
	
	/** 唯一标识微信用户的uid */
	@Column(name = "weixinUid")
	private String weixinUid;
	
	@Column(name = "weixinPCUid")
	private String weixinPCUid;
	
	@Column(name = "weixinUnionId")
	private String weixinUnionId;
	
	/** 访问微信用户信息的令牌 */
	@Column(name = "weixinToken")
	private String weixinToken;
	
	/**支付宝 uid*/
	@Column(name = "alipayUid")
	private String alipayUid;
	/**支付宝令牌环 */
	@Column(name = "alipayToken")
	private String alipayToken;
	
	//是否允许查看客户端的重要内容，0为不允许，1为允许
	@Column(name = "allow_see_important_content_in_client")
	private int allowSeeImportantContentInClient=1;

	public int getAllowSeeImportantContentInClient() {
		return allowSeeImportantContentInClient;
	}

	public void setAllowSeeImportantContentInClient(
			int allowSeeImportantContentInClient) {
		this.allowSeeImportantContentInClient = allowSeeImportantContentInClient;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Date getLocked_time() {
		return locked_time;
	}

	public void setLocked_time(Date locked_time) {
		this.locked_time = locked_time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(int verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public int getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(int loginNumber) {
        this.loginNumber = loginNumber;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

	public int getLoginFailureNumber() {
		return loginFailureNumber;
	}

	public void setLoginFailureNumber(int loginFailureNumber) {
		this.loginFailureNumber = loginFailureNumber;
	}

	public int getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}

	public String getHeadImageURL() {
		return headImageURL;
	}

	public void setHeadImageURL(String headImageURL) {
		this.headImageURL = headImageURL;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSinaWeiboUid() {
		return sinaWeiboUid;
	}

	public void setSinaWeiboUid(String sinaWeiboUid) {
		this.sinaWeiboUid = sinaWeiboUid;
	}

	public String getSinaWeiboToken() {
		return sinaWeiboToken;
	}

	public void setSinaWeiboToken(String sinaWeiboToken) {
		this.sinaWeiboToken = sinaWeiboToken;
	}

	public String getQqConnectUid() {
		return qqConnectUid;
	}

	public void setQqConnectUid(String qqConnectUid) {
		this.qqConnectUid = qqConnectUid;
	}

	public String getQqConnectToken() {
		return qqConnectToken;
	}

	public void setQqConnectToken(String qqConnectToken) {
		this.qqConnectToken = qqConnectToken;
	}

	public String getWeixinUid() {
		return weixinUid;
	}

	public void setWeixinUid(String weixinUid) {
		this.weixinUid = weixinUid;
	}

	public String getWeixinToken() {
		return weixinToken;
	}

	public void setWeixinToken(String weixinToken) {
		this.weixinToken = weixinToken;
	}

	public String getAlipayUid() {
		return alipayUid;
	}

	public void setAlipayUid(String alipayUid) {
		this.alipayUid = alipayUid;
	}

	public String getAlipayToken() {
		return alipayToken;
	}

	public void setAlipayToken(String alipayToken) {
		this.alipayToken = alipayToken;
	}

	public String getWeixinPCUid() {
		return weixinPCUid;
	}

	public void setWeixinPCUid(String weixinPCUid) {
		this.weixinPCUid = weixinPCUid;
	}

	public String getWeixinUnionId() {
		return weixinUnionId;
	}

	public void setWeixinUnionId(String weixinUnionId) {
		this.weixinUnionId = weixinUnionId;
	}
}