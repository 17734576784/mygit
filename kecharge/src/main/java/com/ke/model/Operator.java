package com.ke.model;

import java.util.Date;
public class Operator {

	private Integer id;// 主键
	
	private String name;

	private String description;

	private String serialcode;

	private String logo;

	private String linkMan;

	private String telphone;

	private String faxphone;

	private String mobile;

	private String email;

	private String address;

	private String postCode;

	private Byte model;

	private String modelView;

	private Double checkRatio;

	private Byte keagentFlag;

	private String keagentFlagView;

	private Byte chargeType;

	private String chargeTypeView;

	private Short clientType;
	
	private Short infType;

	private String clientTypeView;
	
	private Short apppayType;

	private String apppayTypeView;

	private Byte creditFlag;							//是否支持信用卡支付,默认为1
	
	private Byte invoiceFlag;

	private Integer invoiceDown;

	private Integer invoiceUp;

	private Byte freeMailFlag;

	private Integer grade;

	private String treePath;

	private Integer parentId;

	private Date createDate;

	private Date updateDate;

	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getSerialcode() {
		return serialcode;
	}

	public void setSerialcode(String serialcode) {
		this.serialcode = serialcode == null ? null : serialcode.trim();
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo == null ? null : logo.trim();
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan == null ? null : linkMan.trim();
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone == null ? null : telphone.trim();
	}

	public String getFaxphone() {
		return faxphone;
	}

	public void setFaxphone(String faxphone) {
		this.faxphone = faxphone == null ? null : faxphone.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode == null ? null : postCode.trim();
	}

	public Byte getModel() {
		return model;
	}

	public void setModel(Byte model) {
		this.model = model;
	}

	/**
	 * @return the modelView
	 */
	public String getModelView() {
		return modelView;
	}

	/**
	 * @param modelView
	 *            the modelView to set
	 */
	public void setModelView(String modelView) {
		this.modelView = modelView;
	}

	public Double getCheckRatio() {
		return checkRatio;
	}

	public void setCheckRatio(Double checkRatio) {
		this.checkRatio = checkRatio;
	}

	public Byte getKeagentFlag() {
		return keagentFlag;
	}

	public void setKeagentFlag(Byte keagentFlag) {
		this.keagentFlag = keagentFlag;
	}

	/**
	 * @return the keagentFlagView
	 */
	public String getKeagentFlagView() {
		return keagentFlagView;
	}

	/**
	 * @param keagentFlagView
	 *            the keagentFlagView to set
	 */
	public void setKeagentFlagView(String keagentFlagView) {
		this.keagentFlagView = keagentFlagView;
	}

	public Byte getChargeType() {
		return chargeType;
	}

	public void setChargeType(Byte chargeType) {
		this.chargeType = chargeType;
	}

	/**
	 * @return the chargeTypeView
	 */
	public String getChargeTypeView() {
		return chargeTypeView;
	}

	/**
	 * @param chargeTypeView
	 *            the chargeTypeView to set
	 */
	public void setChargeTypeView(String chargeTypeView) {
		this.chargeTypeView = chargeTypeView;
	}

	public Short getClientType() {
		return clientType;
	}

	public void setClientType(Short clientType) {
		this.clientType = clientType;
	}

	/**
	 * @return the clientTypeView
	 */
	public String getClientTypeView() {
		return clientTypeView;
	}

	/**
	 * @param clientTypeView
	 *            the clientTypeView to set
	 */
	public void setClientTypeView(String clientTypeView) {
		this.clientTypeView = clientTypeView;
	}

	public Short getApppayType() {
		return apppayType;
	}

	public void setApppayType(Short apppayType) {
		this.apppayType = apppayType;
	}

	/**
	 * @return the apppayTypeView
	 */
	public String getApppayTypeView() {
		return apppayTypeView;
	}

	/**
	 * @param apppayTypeView
	 *            the apppayTypeView to set
	 */
	public void setApppayTypeView(String apppayTypeView) {
		this.apppayTypeView = apppayTypeView;
	}
	
	/**
	 * @return the creditFlag
	 */
	public Byte getCreditFlag() {
		return creditFlag;
	}

	/**
	 * @param creditFlag the creditFlag to set
	 */
	public void setCreditFlag(Byte creditFlag) {
		this.creditFlag = creditFlag;
	}

	public Byte getInvoiceFlag() {
		return invoiceFlag;
	}

	public void setInvoiceFlag(Byte invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}

	public Integer getInvoiceDown() {
		return invoiceDown;
	}

	public void setInvoiceDown(Integer invoiceDown) {
		this.invoiceDown = invoiceDown;
	}

	public Integer getInvoiceUp() {
		return invoiceUp;
	}

	public void setInvoiceUp(Integer invoiceUp) {
		this.invoiceUp = invoiceUp;
	}

	public Byte getFreeMailFlag() {
		return freeMailFlag;
	}

	public void setFreeMailFlag(Byte freeMailFlag) {
		this.freeMailFlag = freeMailFlag;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath == null ? null : treePath.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the infType
	 */
	public Short getInfType() {
		return infType;
	}

	/**
	 * @param infType the infType to set
	 */
	public void setInfType(Short infType) {
		this.infType = infType;
	}
	
}