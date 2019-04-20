package com.nb.model;

import java.util.Date;

import com.nb.utils.Constant;

/** 
* @ClassName: NbCommand 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年4月18日 下午4:35:59 
*  
*/
public class NbCommand extends NbCommandKey {
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	private Byte commandClass;

    private String commandId;

    private Byte executeResult;

    private Date reportTime;

    private String commandContent;

    private Integer operatorId;
    
    private String tableName;

    /**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String date) {

		if (date.length() == Constant.TABLE_YYYYMM) {
			this.tableName = "yddata.dbo.nb_command_" + date;
		} else {
			this.tableName = date;
		}
	}

	public Byte getCommandClass() {
        return commandClass;
    }

    public void setCommandClass(Byte commandClass) {
        this.commandClass = commandClass;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId == null ? null : commandId.trim();
    }

    public Byte getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(Byte executeResult) {
        this.executeResult = executeResult;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getCommandContent() {
        return commandContent;
    }

    public void setCommandContent(String commandContent) {
        this.commandContent = commandContent == null ? null : commandContent.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}